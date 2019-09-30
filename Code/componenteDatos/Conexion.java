package Datos;

import static java.lang.Class.forName;
import java.sql.*;
import static java.sql.DriverManager.getConnection;

/**
 * Esta clase establece una conexión con una base de datos SQLServer.
 */
public class Conexion {

    /**
     * Conector a la base de datos.
     */
    private static Connection instance;

    private static String servidor = "localhost"; //Nombre del servidor 
    private static String puerto = "1433"; //IP
    private static String user = "sa"; //usuario loggin SQL Server
    private static String password = "admin"; //Contraseña
    private static String baseDatos = "DB_ResiduosP"; //Nombre de la base de datos
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    /**
     * Establece una conexion con la BD
     *
     * @return Conexion con la BD
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Connection getInstance() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (instance == null) {
            
            // Se crea una instancia de la clase manejadora de SQLServer
            forName(driver).newInstance();

            // Se crea la conexion con la base de datos deseada
            instance = getConnection("jdbc:sqlserver://" + servidor + ":" + puerto + ";" + "databaseName=" + baseDatos
                    + ";user=" + user + ";password=" + password + ";");
        }
        return instance;
    }
}