/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.Traslado;
import Dominio.Vehiculo;
import Excepciones.ResiduosException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro Galindo
 */
class TrasladoDAO<T> extends DAOResiduos<Traslado> {

    @Override
    List<Traslado> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void insertar(Traslado entidad) {
        String sql = "{ CALL sp_InsertarTraslado(?,?,?,?,?,?,?) }";
        CallableStatement cst;
        try {
            cst = Conexion.getInstance().prepareCall(sql);
            cst.setInt(1, entidad.getIDAsignacion());
            cst.setNString(2, entidad.getFechaTermino());
            cst.setNString(3, entidad.getTratamiento());
            cst.setNString(4, entidad.getDestino());
            cst.setFloat(5, entidad.getCosto());
            cst.setNString(6, entidad.getIDEmpresaT());
            cst.setFloat(7, entidad.getKilometros());
            
            cst.execute();
            cst.close();
            
            List<Vehiculo> vehiculos = entidad.getVehiculos();
            for (Vehiculo vehiculo : vehiculos) {
                registrarFlotilla(String.valueOf(obtenerUltimoTraslado()), vehiculo.getMatricula());
            }
            
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(TrasladoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void registrarFlotilla(String IDTraslado, String matricula){
        String sql = "{ CALL sp_InsertarFlotilla(?,?) }";
        CallableStatement cst;
        try {
            cst = Conexion.getInstance().prepareCall(sql);
            cst.setInt(1, Integer.valueOf(IDTraslado));
            cst.setNString(2, matricula);
            
            cst.execute();
            cst.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(TrasladoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    int obtenerUltimoTraslado(){
        String sql = "SELECT MAX(IDTraslado) FROM Traslados";
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

    @Override
    void borrar(String identificador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void actualizar(Traslado entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    Traslado obtener(String identificador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Traslado transformar(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}