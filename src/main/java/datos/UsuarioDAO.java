package datos;

import entidad.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//esta es la clase con la denominacion Data Acces Object que brinda acceso a los datos de la base de datos para manipularlos
public class UsuarioDAO {

    //Estas son la variables privadas, estaticas y constantes que contienen las sentencias SQL que manipulan los datos de la base de datos en MySQL
    //Las declare estaticas para poder usarlas en otras clases sin tener que instanciar la clase en caso de que sea necesario
    private static final String SQL_SELECTALL = "SELECT * FROM usuarios";
    private static final String SQL_INSERTARUSUARIO = "INSERT INTO usuarios (nombre_de_usuario, nombre, apellido, numero_de_telefono, correo, contraseña)VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuarios SET nombre_de_usuario= ?, nombre = ?, apellido= ?, numero_de_telefono= ?, correo= ?, contraseña= ? WHERE nombre_de_usuario= ?";
    public static final String SQL_ELIMINAR = "DELETE FROM usuarios WHERE nombre_de_usuario= ?";
    public static final String SQL_MOSTRARDATOS = "SELECT nombre_de_usuario, nombre, apellido, numero_de_telefono, correo FROM usuarios";
    //Variable Conexion
    public static Connection conexion;

// Este es el metodo que me devuelve un objeto de tipo ArrayList con todos los registros de la base de datos
    public List<Usuario> seleccionar() {
// declaro las variables 
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = null;  
        // variable que ejecuta las sentencias en la base de datos
        Statement sentenciaSQL = null;
        // variable que me trae el resultado
        ResultSet resultado = null;
        //dentro de este try establezco la conexion
        try {
            conexion = Conexion.obtenerConexion();
            sentenciaSQL = conexion.createStatement();
            resultado = sentenciaSQL.executeQuery(SQL_SELECTALL);
//la variable resultado me iterara cada registro de la base de datos y lo almacenara en el ArrayList
            while (resultado.next()) {
                int idusuario = resultado.getInt("idusuario");
                String nombreUsuario = resultado.getString("nombre_de_usuario");
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                String numeroTelefono = resultado.getString("numero_de_telefono");
                String correo = resultado.getString("correo");
                String contrasena = resultado.getString("contraseña");

                usuario = new Usuario(idusuario, nombreUsuario, nombre, apellido,
                        numeroTelefono, correo, contrasena);
//Aqui se almacenan los registros uno por uno en el ArrayList
                usuarios.add(usuario);

            }

        } catch (SQLException e) {

//Si la ejecucion de este metodo arroja una excepcion me muestra este mensaje
            JOptionPane.showMessageDialog(null, "Ocurrio un ERROR");
        }
        //Aqui se cierran las conexiones a la base de datos aplicando el metodo estatico Close de la clase Conexion
        Conexion.close(resultado);
        Conexion.close(sentenciaSQL);
        //me devuelve el ArrayList con todos los registros
        return usuarios;

    }

    //Metodo insertar a traves de este metodo se insertan los datos en la base de datos
    public void insertar(Usuario usuario) {
//Declaro una variable de tipo preparedStatement
        PreparedStatement resultado = null;

        try {
            //establezco la conexion 
            conexion = Conexion.obtenerConexion();
            resultado = conexion.prepareStatement(SQL_INSERTARUSUARIO);

            // el objeto PreparedStatement me agrega cada uno de los valores que recibio por parametro el objeto de tipo Usuario y los va almacenando en una posicion
            resultado.setString(1, usuario.getNombre_de_usuario());
            resultado.setString(2, usuario.getNombre());
            resultado.setString(3, usuario.getApellido());
            resultado.setString(4, usuario.getNumero_de_telefono());
            resultado.setString(5, usuario.getCorreo());
            resultado.setString(6, usuario.getContrasena());

            // con este metodo se agregan los valores obtenidos a la base de datos cada elemento se agrega en el valor que se encuentre en la sentencia SQL
            resultado.executeUpdate();

        } catch (SQLException e) {
            //Mensaje de error si lo anterior falla
            JOptionPane.showMessageDialog(null, "Ocurrio un ERROR");
        }

        //se cierran las conexiones
        Conexion.close(resultado);
  
    }

    //Metodo actualizar que actualiza un registro a partir del nombre de usuario 
    public void actualizar(Usuario usuario) {

        //como anteriormente se declaran estas variables
        PreparedStatement resultado = null;

        try {
            // Se crea la conexion
            conexion = Conexion.obtenerConexion();
            resultado = conexion.prepareStatement(SQL_UPDATE);

            // y se actualiza el registro a partir del nombre de usuario que queda en la posicion 7 de la sentencia SQL
            resultado.setString(1, usuario.getNombre_de_usuario());
            resultado.setString(2, usuario.getNombre());
            resultado.setString(3, usuario.getApellido());
            resultado.setString(4, usuario.getNumero_de_telefono());
            resultado.setString(5, usuario.getCorreo());
            resultado.setString(6, usuario.getContrasena());
            resultado.setString(7, usuario.getNombre_de_usuario());
            resultado.executeUpdate();

        } catch (SQLException e) {
            //Mensaje de erro
            JOptionPane.showMessageDialog(null, "Ocurrio un ERROR");
        }

        //Se cierra la conexion
        Conexion.close(resultado);

    }

    //Eliminar usuario, este metodo elimina el usuario a partir del nombre de usuario
    public void eliminar(Usuario usuario) {

        PreparedStatement resultado = null;
        try {
            conexion = Conexion.obtenerConexion();
            resultado = conexion.prepareStatement(SQL_ELIMINAR);

            //se ejecita la sentencia y se actualiza el usuario a partir del nombre de usuario que se le dio al objeto usuario en el parametro
            resultado.setString(1, usuario.getNombre_de_usuario());
            resultado.executeUpdate();

        } catch (SQLException e) {
            //mensaje de error
            JOptionPane.showMessageDialog(null, "Ocurrio un ERROR");

        }

        //Se cierran las conexiones
        Conexion.close(resultado);

    }
//Este es el metodo que utilizo para guardar todos los registros de la base de ddatos en un objeto de tipo DefaultTableModel

    public DefaultTableModel mostrarDatos() {

        //Creo un arreglo con los nombres de las columnas que quiero mostrar en la tabla
        String[] nombreColumnas = {"nombre_de_usuario", "nombre", "apellido", "numero_de_telefono", "correo"};
        //Creo otro arreglo con 5 espacios pero sin valor
        String[] datos = new String[5];
        //Creo un objeto de tipo DefaultTableModel que recibe por parametro los valores de las filas y los valores de las columnas
        //le paso por parametro en las filas null y en las columnas el arreglo con los valores de las columnas
        DefaultTableModel tabla = new DefaultTableModel(null, nombreColumnas);

        //declaro las variables para realizar las manipulaciones a las abases de datos
        PreparedStatement resultado = null;
        ResultSet rs = null;

        try {
            //Establezco la conexion y le aplico la sentencia que me trae los datos que quiero mostrar en la tabla
            conexion = Conexion.obtenerConexion();
            resultado = conexion.prepareStatement(SQL_MOSTRARDATOS);
            rs = resultado.executeQuery();
// voy iterando cada registro con el metodo next() y voy agregando cada valor a la fila correspondiente en el arreglo que solo le asigne espacios
            while (rs.next()) {
                datos[0] = rs.getString("nombre_de_usuario");
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("apellido");
                datos[3] = rs.getString("numero_de_telefono");
                datos[4] = rs.getString("correo");
                //aqui se van a ir agregando los registros en las filas del objeto de tipo DefaultTableModel tabla
                tabla.addRow(datos);
            }

        } catch (SQLException e) {
            //mensaje de error en caso de que lo anterior no funcione
            JOptionPane.showMessageDialog(null, "Ocurrio un ERROR");
        }

        //cierro las conexiones
        Conexion.close(resultado);
// devuelvo el objeto tabla de tipo DefaultTableModel
        return tabla;

    }

}
