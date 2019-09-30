/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.EmpresaProductora;
import Dominio.EmpresaTransporte;
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
class EmpresaTransporteDAO<T> extends DAOResiduos<EmpresaTransporte> {

    @Override
    List<EmpresaTransporte> obtenerTodos() {
        String sql = "SELECT ep.RFC, u.Password, u.Tipo, ep.Nombre\n"
                + "FROM Usuarios u\n"
                + "INNER JOIN EmpresasTransportadoras ep\n"
                + "ON u.CodigoAcceso = ep.RFC";
        List<EmpresaTransporte> listaEmpresas = new ArrayList<>();

        Statement stmt;
        ResultSet rs;
        try {
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                listaEmpresas.add(transformar(rs));
            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            throw new ResiduosException("No se pudo obtener la lista de empresas de transporte");
        }

        return listaEmpresas;
    }

    @Override
    void insertar(EmpresaTransporte entidad) {
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
            throw new ResiduosException("No se pudo registrar a la empresa transportadora");
        }
    }

    @Override
    void borrar(String identificador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void actualizar(EmpresaTransporte entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    EmpresaTransporte obtener(String identificador) {
        String sql = "SELECT ep.RFC, u.Password, u.Tipo, ep.Nombre\n"
                + "FROM Usuarios u\n"
                + "INNER JOIN EmpresasTransportadoras ep\n"
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
            throw new ResiduosException("No se pudo obtener a la empresa de transporte");
        }
        
        return null;
    }

    @Override
    protected EmpresaTransporte transformar(ResultSet rs) {
        EmpresaTransporte empresa = null;

        try {
            empresa = new EmpresaTransporte(rs.getNString(1), rs.getNString(2), rs.getInt(3), rs.getNString(4));
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaTransporteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return empresa;
    }
}
