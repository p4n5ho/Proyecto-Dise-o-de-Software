/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.ElementoQuimico;
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
class ElementoQuimicoDAO<T> extends DAOResiduos<ElementoQuimico> {

    @Override
    List<ElementoQuimico> obtenerTodos() {
        String sql = "SELECT * FROM ElementosQuimicos";
        List<ElementoQuimico> listaElementosQuimicos = new ArrayList<>();

        Statement stmt;
        ResultSet rs;
        try {
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                listaElementosQuimicos.add(transformar(rs));
            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(ElementoQuimicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaElementosQuimicos;
    }

    @Override
    void insertar(ElementoQuimico entidad) {
        String sql = "{ CALL sp_InsertarElementoQuimico(?,?,?) }";
        CallableStatement cst;
        try {
            cst = Conexion.getInstance().prepareCall(sql);
            cst.setNString(1, entidad.getNombreElemento());
            cst.setNString(2, entidad.getSimbolo());
            cst.setInt(3, entidad.getnAtomico());
            
            cst.execute();
            cst.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(ElementoQuimicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    void borrar(String identificador) {
        String sql = "DELETE FROM ElementosQuimicos WHERE CodigoEQ = " + identificador;
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
    void actualizar(ElementoQuimico entidad) {
        String sql = "UPDATE ElementosQuimicos\n"
                + "SET NombreElemento = '" + entidad.getNombreElemento() + "',"
                + "Simbolo = '" + entidad.getSimbolo() + "',"
                + "NAtomico = " + entidad.getnAtomico() + ","
                + "WHERE CodigoEQ = " + entidad.getIDElemento();
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
    ElementoQuimico obtener(String identificador) {
        String sql = "SELECT * FROM ElementosQuimicos WHERE CodigoEQ = " + identificador;

        Statement stmt;
        ResultSet rs;
        try {
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                return transformar(rs);
            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(ElementoQuimicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    protected ElementoQuimico transformar(ResultSet rs) {
        ElementoQuimico elemento = null;
        try {
            elemento = new ElementoQuimico(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(1));
        } catch (SQLException ex) {
            Logger.getLogger(ElementoQuimicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return elemento;
    }

    List<ElementoQuimico> obtenerDeResiduo(String identificador) {
        String sql = "SELECT eq.CodigoEQ, eq.NombreElemento, eq.Simbolo, eq.NAtomico "
                + "FROM Composiciones c "
                + "INNER JOIN ElementosQuimicos eq "
                + "ON c.CodigoEQ = eq.CodigoEQ WHERE c.CodigoR = '" + identificador + "'";

        List<ElementoQuimico> listaEQResiduo = new ArrayList<>();

        Statement stmt;
        ResultSet rs;
        try {
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                listaEQResiduo.add(transformar(rs));
            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(ElementoQuimicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaEQResiduo;
    }

    void agregarComposicion(ElementoQuimico entidad, String identificador) {
        String sql = "{ CALL sp_InsertarComposicion(?,?) }";
        CallableStatement cst;
        try {
            cst = Conexion.getInstance().prepareCall(sql);
            cst.setInt(1, entidad.getIDElemento());
            cst.setNString(2, identificador);

            cst.execute();
            cst.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(ElementoQuimicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}