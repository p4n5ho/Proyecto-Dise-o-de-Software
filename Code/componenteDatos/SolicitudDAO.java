/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.Solicitud;
import Dominio.DetallesSolicitud;
import Excepciones.ResiduosException;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro Galindo
 * @param <T>
 */
public class SolicitudDAO<T> extends DAOResiduos<Solicitud> {

    @Override
    List<Solicitud> obtenerTodos() {
        String sql = "SELECT * FROM Solicitudes";
        List<Solicitud> solicitudes = new ArrayList<>();

        Statement stmt;
        ResultSet rs;

        try {
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                solicitudes.add(transformar(rs));
            }
            
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            throw new ResiduosException("No se pudo obtener la lista de solicitudes");
        }

        return solicitudes;
    }

    @Override
    void insertar(Solicitud entidad) {
        String sql = "{ CALL sp_InsertarSolicitud(?,?) }";
        CallableStatement cst;
        try {
            cst = Conexion.getInstance().prepareCall(sql);
            cst.setNString(1, entidad.getFecha());
            cst.setNString(2, entidad.getRFCEmpresa());
            cst.execute();
            cst.close();
            
            int IDSolicitud = obtenerIDUltimaSolicitud();
            
            if(entidad.getDetalles() != null){
                for (int i = 0; i < entidad.getDetalles().size(); i++) {
                    agregarDetallesSolicitud(IDSolicitud, entidad.getDetalles().get(i));
                }
            }
            
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(SolicitudDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    void borrar(String identificador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void actualizar(Solicitud entidad) {
        String sql = "{ CALL sp_ActualizarSolicitud(?,?) }";
        CallableStatement cst;
        try {
            cst = Conexion.getInstance().prepareCall(sql);
            cst.setInt(1, entidad.getIDSolicitud());
            
            List<DetallesSolicitud> detalles = entidad.getDetalles();
            for (DetallesSolicitud detalle : detalles) {
                if(detalle.getCantidad() != 0){
                    cst.setBoolean(2, false);
                }
            }
            cst.setBoolean(2, true);

            cst.execute();
            cst.close();
            
            for (DetallesSolicitud detalle : detalles) {
                actualizarDetallesSolicitud(detalle);
            }
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(SolicitudDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    Solicitud obtener(String identificador) {
        String sql = "SELECT * FROM Solicitudes WHERE IDSolicitud = '" + identificador + "'";

        Statement stmt;
        ResultSet rs;

        try {
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                return transformar(rs);
            }
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            throw new ResiduosException("No se pudo obtener ninguna solicitud");
        }

        return null;
    }

    @Override
    Solicitud transformar(ResultSet rs) {
        Solicitud solicitud = null;
        try {
            
            int IDSolicitud = rs.getInt(1);
            String fecha = rs.getDate(2).toLocalDate().toString();
            String empresa = rs.getNString(3);
            boolean estado = rs.getBoolean(4);
            
            solicitud = new Solicitud(IDSolicitud, fecha, empresa, estado);
            
            solicitud.setDetalles(obtenerDetallesSolicitud(String.valueOf(solicitud.getIDSolicitud())));
            
        } catch (SQLException ex) {
            Logger.getLogger(ResiduoDAO.class.getName());
        }

        return solicitud;
    }
    
    void agregarDetallesSolicitud(int identificador, DetallesSolicitud entidad){
        String sql = "{ CALL sp_InsertarDetalles(?,?,?,?) }";
        CallableStatement cst;
        try {
            cst = Conexion.getInstance().prepareCall(sql);
            cst.setInt(1, identificador);
            cst.setNString(2, entidad.getIDResiduo());
            cst.setDouble(3, entidad.getCantidad());
            cst.setNString(4, entidad.getMedida());

            cst.execute();
            cst.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(SolicitudDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    List<DetallesSolicitud> obtenerDetallesSolicitud(String identificador){
        String sql = "SELECT * FROM DetallesSolicitud WHERE IDSolicitud = " + identificador + "";

        List<DetallesSolicitud> detallesLista = new ArrayList<>();

        Statement stmt;
        ResultSet rs;
        try {
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                DetallesSolicitud detalles = new DetallesSolicitud(rs.getInt(1), rs.getNString(2), rs.getFloat(3), rs.getNString(4));
                detallesLista.add(detalles);
            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(SolicitudDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return detallesLista;
    }
    
    void actualizarDetallesSolicitud(DetallesSolicitud detalles){
        String sql = "{ CALL sp_ActualizarDetallesSolicitud(?,?,?) }";
        CallableStatement cst;
        try {
            cst = Conexion.getInstance().prepareCall(sql);
            cst.setInt(1, detalles.getIDSolicitud());
            cst.setNString(2, detalles.getIDResiduo());
            cst.setFloat(3, detalles.getCantidad());

            cst.execute();
            cst.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(SolicitudDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    int obtenerIDUltimaSolicitud(){
        String sql = "SELECT MAX(IDSolicitud) FROM Solicitudes";
        int ultimoID = 0;
        
        Statement stmt;
        ResultSet rs;
        try {
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                ultimoID = rs.getInt(1);
            }
            
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            throw new ResiduosException("No se pudo obtener el ultimo ID del traslado");
        }

        return ultimoID;
    }   
}