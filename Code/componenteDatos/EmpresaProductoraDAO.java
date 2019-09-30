/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.EmpresaProductora;
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
class EmpresaProductoraDAO<T> extends DAOResiduos<EmpresaProductora> {

    @Override
    List<EmpresaProductora> obtenerTodos() {
        String sql = "SELECT ep.RFC, u.Password, u.Tipo, ep.Nombre\n"
                + "FROM Usuarios u\n"
                + "INNER JOIN EmpresasProductoras ep\n"
                + "ON u.CodigoAcceso = ep.RFC";
        List<EmpresaProductora> listaEmpresas = new ArrayList<>();

        Statement stmt;
        ResultSet rs;
        try {
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                listaEmpresas.add(transformar(rs));
            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            throw new ResiduosException("No se pudo obtener la lista de empresas productoras");
        }

        return listaEmpresas;
    }

    @Override
    void insertar(EmpresaProductora entidad) {
        //No funcional, aun, creo
        String sql = "{ CALL sp_CrearUsuario(?,?,?,?) }";
        CallableStatement cst;
        try {
            cst = Conexion.getInstance().prepareCall(sql);
            cst.setNString(1, entidad.getCodigoAcceso());
            cst.setNString(2, entidad.getPassword());
            cst.setNString(3, entidad.getNombre());

            cst.execute();
            cst.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            throw new ResiduosException("No se pudo registrar a la empresa productora");
        }
    }

    @Override
    void borrar(String identificador) {
        //No funcional
        String sql = "DELETE FROM EmpresasProductoras WHERE RFC = " + identificador;
        Statement stmt;
        ResultSet rs;
        try {
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(ElementoQuimicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    void actualizar(EmpresaProductora entidad) {
        //No funcional
        String sql = "UPDATE EmpresasProductoras\n"
                + "SET RFC = '" + entidad.getRFC() + "',"
                + "NombreEmpresaProductora = '" + entidad.getNombreProductora() + "',"
                + "Password = " + entidad.getPassword() + "', "
                + "WHERE RFC = " + entidad.getRFC();
        Statement stmt;
        ResultSet rs;
        try {
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(ElementoQuimicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    EmpresaProductora obtener(String identificador) {
        String sql = "SELECT ep.RFC, u.Password, u.Tipo, ep.Nombre\n"
                + "FROM Usuarios u\n"
                + "INNER JOIN EmpresasProductoras ep\n"
                + "ON u.CodigoAcceso = ep.RFC\n"
                + "WHERE ep.RFC = '" + identificador + "'";

        Statement stmt;
        ResultSet rs;
        try {
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                return transformar(rs);
            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            throw new ResiduosException("No se pudo obtener a la empresa Productora");
        }
        
        return null;
    }

    @Override
    protected EmpresaProductora transformar(ResultSet rs) {
        EmpresaProductora empresa = null;

        try {
            empresa = new EmpresaProductora(rs.getNString(1), rs.getNString(2), rs.getInt(3), rs.getNString(4));
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaProductoraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return empresa;
    }
}