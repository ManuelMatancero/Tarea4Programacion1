package datos;

import java.sql.*;
import javax.swing.JOptionPane;
//Esta clase me permite la conexion a la base de datos de MySQL
public class Conexion {

    /*Estas variables contienen las cadenas de texto que utilizare para conectarme a  la base de datos MySQL, url, usuario y contrasena
    las declare estaticas porque las utilizare en el metodo estatico obtenerConexion*/
    private static final String CADENA_CONEXION = "jdbc:mysql://localhost:3306/tarea4?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USUARIO_MYSQL = "root";
    private static final String CONTRA_MYSQL = "admin";
    private static Connection conexion;
    

    /*Este metodo devuelve un objeto de tipo conexion en el cual se crea un objeto de tipo Connection al cual se le aplicara el metodo estatico
    getConnection de la clase DriveManager que recibe por parametro el url, usuario y contrasena de la base de datos a la cual nos conectaremos
    esta operacion esta envuelta en un try-catch porque puede que arroje una excepcion*/
    public static Connection obtenerConexion() {
        //EN ESTE METODO UTILICE EL PATRON DE DISEÑO SINGLETON PARA SOLO CREAR UN OBJETO GLOBAL DE TIPO CONEXION EN LA CLASE UsuarioDAO
        try {
            if(conexion==null){
                
               conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO_MYSQL, CONTRA_MYSQL); 
            }     

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Ocurrio un error de conexion en el metodo obtenerConexion()");

        }

        return conexion;

    }

    /*En estos metrodos utilice polimorfismo parametrico para cerrar cada una de las conexiones con la base de datos al terminar de ejecutar
    cada operacion del CRUD ya que se usaran objetos Connection, ResultSet, Statement y PreparedStatement para manipular los registros, con el metodo close()
    nos aseguramos que no se abra ningun flujo o cambio de datos alternativos en la base de datos, cada uno de estos envueltos en un metodo Try catch ya que 
    pueden arrojar excepciones*/
    public static void close(ResultSet resultset) {
        try {
            resultset.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un ERROR");
        }
    }

    public static void close(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un ERROR");
        }
    }

    public static void close(PreparedStatement preparedstatement) {
        try {
            preparedstatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un ERROR");
        }
    }

 

}
