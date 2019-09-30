/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Alejandro Galindo
 */
abstract class DAOResiduos<T> {
    abstract List<T> obtenerTodos();
    abstract void insertar(T entidad);
    abstract void borrar(String identificador);
    abstract void actualizar(T entidad);
    abstract T obtener(String identificador);
    abstract T transformar(ResultSet rs);
    
}
