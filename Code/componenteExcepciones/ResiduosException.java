/**
 * PersistenciaException.java
 *
 * Creada el 29 de marzo de 2018 a las 11:57AM
 */
package Excepciones;

/**
 * Esta clase representa a las excepciones lanzadas por las clases que se
 * encargan de acceder a los datos en el mecanismo de persistencia.
 *
 * @author Alejandro Galindo
 */
public class ResiduosException extends RuntimeException {

    /**
     * Constructor por omisión. Construye una excepción con un mensaje de error
     * nulo.
     */
    public ResiduosException() {}

    /**
     * Construye una excepción con el mensaje de error del parámetro.
     * 
     * @param msj Mensaje de error.
     */
    public ResiduosException(String msj) {
        super(msj);
    }

    /**
     * Construye una excepción con el mensaje de error del parámetro.
     *
     * @param msj Mensaje de error.
     * @param causa Causa original del error.
     */
    public ResiduosException(String msj, Throwable causa) {
        super(msj, causa);
    }

    /**
     * Construye una excepción la causa original del error.
     *
     * @param causa Causa original del error.
     */
    public ResiduosException(Throwable causa) {
        super(causa);
    }
}