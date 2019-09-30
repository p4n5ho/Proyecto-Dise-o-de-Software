/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Dominio.Usuario;
import Excepciones.ResiduosException;
import java.sql.CallableStatement;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Alejandro Galindo
 */
class UsuarioDAO<T> extends DAOResiduos<Usuario> {

    @Override
    List<Usuario> obtenerTodos() {
        String sql = "SELECT * FROM Usuarios";
        List<Usuario> listaUsuarios = new ArrayList<>();

        Statement stmt;
        ResultSet rs;
        try {
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                listaUsuarios.add(transformar(rs));
            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            throw new ResiduosException("No se pudo obtener la lista de usuarios");
        }

        return listaUsuarios;
    }

    @Override
    void insertar(Usuario entidad) {
        String sql = "{ CALL sp_CrearUsuario(?,?,?,?) }";
        CallableStatement cst;
        try {
            cst = Conexion.getInstance().prepareCall(sql);
            cst.setNString(1, entidad.getCodigoAcceso());
            cst.setNString(2, entidad.getPassword());
            cst.setInt(3, entidad.getTipo());
            cst.setNString(4, entidad.getNombre());

            cst.execute();
            cst.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            throw new ResiduosException("No se pudo registrar el usuario");
        }
    }

    @Override
    void borrar(String identificador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void actualizar(Usuario entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    Usuario obtener(String identificador) {
        String sql = "SELECT * FROM Usuarios WHERE CodigoAcceso = '" + identificador + "'";

        Statement stmt;
        ResultSet rs;
        try {
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                return transformar(rs);
            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            throw new ResiduosException("No se pudo obtener al usuario con el identificador: " + identificador);
        }
        
        return null;
    }

    @Override
    protected Usuario transformar(ResultSet rs) {
        Usuario usuario = null;

        try {
            usuario = new Usuario(rs.getNString(1), rs.getNString(2), rs.getInt(3));
        } catch (SQLException ex) {
            Logger.getLogger(ElementoQuimicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuario;
    }

}
