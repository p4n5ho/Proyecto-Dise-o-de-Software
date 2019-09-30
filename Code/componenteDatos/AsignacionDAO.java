/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.Asignacion;
import Excepciones.ResiduosException;
import java.sql.CallableStatement;
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
 */
public class AsignacionDAO<T> extends DAOResiduos<Asignacion> {

    @Override
    List<Asignacion> obtenerTodos() {
        String sql = "SELECT * FROM Asignaciones";
        List<Asignacion> asignaciones = new ArrayList<>();

        Statement stmt;
        ResultSet rs;

        try {
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                asignaciones.add(transformar(rs));
            }
            
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            throw new ResiduosException("No se pudo obtener la lista de solicitudes");
        }

        return asignaciones;
    }

    @Override
    void insertar(Asignacion entidad) {
        String sql = "{ CALL sp_InsertarAsignacion(?,?,?,?,?,?) }";
        CallableStatement cst;
        try {
            cst = Conexion.getInstance().prepareCall(sql);
            cst.setInt(1, entidad.getIDSolicitud());
            cst.setNString(2, entidad.getIDResiduo());
            cst.setFloat(3, entidad.getCantidadReparto());
            cst.setNString(4, entidad.getMedida());
            cst.setNString(5, entidad.getRFCEmpresaT());
            cst.setBoolean(6, entidad.isEstado());

            cst.execute();
            cst.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(SolicitudDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    void borrar(String identificador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void actualizar(Asignacion entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    Asignacion obtener(String identificador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    Asignacion transformar(ResultSet rs) {
        Asignacion asignacion = null;
        try {
            asignacion = new Asignacion(rs.getInt(1), rs.getInt(2), rs.getNString(6), rs.getNString(3), rs.getFloat(4), rs.getNString(5), rs.getBoolean(7));
            
        } catch (SQLException ex) {
            Logger.getLogger(ResiduoDAO.class.getName());
        }

        return asignacion;
    }
    
}
