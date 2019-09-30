/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.Vehiculo;
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
public class VehiculoDAO<T> extends DAOResiduos<Vehiculo> {

    @Override
    List<Vehiculo> obtenerTodos() {
        String sql = "SELECT * FROM Vehiculos";
        List<Vehiculo> vehiculos = new ArrayList<>();

        Statement stmt;
        ResultSet rs;

        try {
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                vehiculos.add(transformar(rs));
            }
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vehiculos;
    }

    @Override
    void insertar(Vehiculo entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void borrar(String identificador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void actualizar(Vehiculo entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    Vehiculo obtener(String identificador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    Vehiculo transformar(ResultSet rs) {
        Vehiculo vehiculo = null;
        try {
            vehiculo = new Vehiculo(rs.getNString(1), rs.getNString(2), rs.getNString(3));
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoDAO.class.getName());
        }
        
        return vehiculo;
    }

}
