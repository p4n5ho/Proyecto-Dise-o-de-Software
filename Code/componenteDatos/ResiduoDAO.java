/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.ElementoQuimico;
import Dominio.EmpresaProductora;
import Dominio.Residuo;
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
class ResiduoDAO<T> extends DAOResiduos<Residuo> {

    @Override
    List<Residuo> obtenerTodos() {
        String sql = "SELECT * FROM Residuos";
        List<Residuo> listaResiduos = new ArrayList<>();

        Statement stmt;
        ResultSet rs;
        try {
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                listaResiduos.add(transformar(rs));
            }
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            throw new ResiduosException("No se pudo obtener la lista de Residuos");
        }

        return listaResiduos;
    }

    @Override
    void insertar(Residuo entidad) {
        String sql = "{ CALL sp_InsertarResiduo(?,?) }";
        CallableStatement cst;
        try {
            cst = Conexion.getInstance().prepareCall(sql);
            cst.setNString(1, entidad.getIDResiduo());
            cst.setNString(2, entidad.getNombreResiduo());
            cst.execute();
            cst.close();
            
            for (int i = 0; i < entidad.getElementosQuimicos().size(); i++) {
                agregarComposicion(entidad.getIDResiduo(), entidad.getElementosQuimicos().get(i));
            }
            
            for (int i = 0; i < entidad.getEmpresasProductoras().size(); i++) {
                agregarProduccion(entidad.getEmpresasProductoras().get(i), entidad.getIDResiduo());
            }
            
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            throw new ResiduosException("No se pudo insertar el Residuo");
        }
    }

    @Override
    void borrar(String identificador) {
        //No implementado
    }

    @Override
    void actualizar(Residuo entidad) {
        //No implementado
    }

    @Override
    Residuo obtener(String identificador) {
        String sql = "SELECT * FROM Residuos WHERE IDResiduo = '" + identificador + "'";

        Statement stmt;
        ResultSet rs;

        try {
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                return transformar(rs);
            }
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            throw new ResiduosException("No se pudo obtener ningun residuo");
        }

        return null;
    }

    @Override
    protected Residuo transformar(ResultSet rs) {
        Residuo residuo = null;
        try {
            //Transforma el residuo obtenido de la tabla de Residuos
            residuo = new Residuo(rs.getString(1), rs.getString(2));
            
            //Le asigna los elementos quimicos al residuo obtenido utilizando la tabla de Composiciones
            residuo.setElementosQuimicos((ArrayList<ElementoQuimico>) obtenerElementosDeResiduo(residuo.getIDResiduo()));
            
            //Le asigna las empresa productoras al residuo obtenido utilizando la tabla de Producciones
            residuo.setEmpresasProductoras((ArrayList<EmpresaProductora>) obtenerProductorasDeResiduo(residuo.getIDResiduo()));
        } catch (SQLException ex) {
            Logger.getLogger(ResiduoDAO.class.getName());
        }

        return residuo;
    }

    void agregarComposicion(String identificador, ElementoQuimico entidad) {
        String sql = "{ CALL sp_InsertarComposicion(?,?) }";
        CallableStatement cst;
        try {
            cst = Conexion.getInstance().prepareCall(sql);
            cst.setNString(1, identificador);
            cst.setInt(2, entidad.getIDElemento());
            
            cst.execute();
            cst.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(ResiduoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    List<ElementoQuimico> obtenerElementosDeResiduo(String identificador) {
        String sql = "SELECT eq.IDElemento, eq.Nombre, eq.Simbolo, eq.NAtomico \n"
                + "FROM Composiciones c \n"
                + "INNER JOIN ElementosQuimicos eq \n"
                + "ON c.IDElemento = eq.IDElemento WHERE c.IDResiduo = '" + identificador + "'";

        List<ElementoQuimico> listaEQResiduo = new ArrayList<>();

        Statement stmt;
        ResultSet rs;
        try {
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ElementoQuimico elemento = new ElementoQuimico(rs.getInt(1), rs.getNString(2), rs.getNString(3), rs.getInt(4));
                listaEQResiduo.add(elemento);
            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(ResiduoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaEQResiduo;
    }

    void agregarProduccion(EmpresaProductora entidad, String identificador) {
        String sql = "{ CALL sp_InsertarProduccion(?,?) }";
        CallableStatement cst;
        try {
            cst = Conexion.getInstance().prepareCall(sql);
            cst.setNString(1, identificador);
            cst.setNString(2, entidad.getRFC());

            cst.execute();
            cst.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(ResiduoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    List<EmpresaProductora> obtenerProductorasDeResiduo(String identificador) {
        String sql = "SELECT ep.RFC, u.Password, u.Tipo, ep.Nombre\n"
                + "FROM Producciones p \n"
                + "INNER JOIN EmpresasProductoras ep \n"
                + "ON p.RFCEmpresaProductora = ep.RFC \n"
                + "INNER JOIN Usuarios u\n"
                + "ON p.RFCEmpresaProductora = u.CodigoAcceso\n"
                + "WHERE p.IDResiduo = '" + identificador + "'";

        List<EmpresaProductora> listaEQResiduo = new ArrayList<>();

        Statement stmt;
        ResultSet rs;
        try {
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                EmpresaProductora empresa = new EmpresaProductora(rs.getNString(1), rs.getNString(2), rs.getInt(3), rs.getNString(4));
                listaEQResiduo.add(empresa);
            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(ResiduoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaEQResiduo;
    }
}
