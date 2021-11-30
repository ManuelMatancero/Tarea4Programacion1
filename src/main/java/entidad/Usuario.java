package entidad;

//Estudiante: Manuel Antonio Sarante Sanchez
//Matricula: 2021-0785
//Esta es la clase que tiene la forma de la entidad o la tabla en la base de datos
public class Usuario {

    private int idusuario;
    private String nombre_de_usuario;
    private String nombre;
    private String apellido;
    private String numero_de_telefono;
    private String correo;
    private String contrasena;

    // aqui ejecute sobrecarga del metodo constructor ya que utilizare diferentes constructores para las diferentes operacuiones con el CRUD
    public Usuario() {
    }

    public Usuario(String nombre_de_usuario) {
        this.nombre_de_usuario = nombre_de_usuario;
    }

    public Usuario(String nombre_de_usuario, String contrasena) {
        this.nombre_de_usuario = nombre_de_usuario;
        this.contrasena = contrasena;
    }

    public Usuario(String nombre_de_usuario, String nombre, String apellido,
            String numero_de_telefono, String correo, String contrasena) {
        this.nombre_de_usuario = nombre_de_usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numero_de_telefono = numero_de_telefono;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public Usuario(String nombre_de_usuario, String nombre,
            String apellido, String numero_de_telefono, String correo) {
        this.idusuario = idusuario;
        this.nombre_de_usuario = nombre_de_usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numero_de_telefono = numero_de_telefono;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public Usuario(int idusuario, String nombre_de_usuario, String nombre, String apellido, String numero_de_telefono, String correo, String contrasena) {
        this.idusuario = idusuario;
        this.nombre_de_usuario = nombre_de_usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numero_de_telefono = numero_de_telefono;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    //Metodos Getter y Setter 
    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre_de_usuario() {
        return nombre_de_usuario;
    }

    public void setNombre_de_usuario(String nombre_de_usuario) {
        this.nombre_de_usuario = nombre_de_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumero_de_telefono() {
        return numero_de_telefono;
    }

    public void setNumero_de_telefono(String numero_de_telefono) {
        this.numero_de_telefono = numero_de_telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    //Metodo toString()
    @Override
    public String toString() {
        return "Usuario{" + "idusuario=" + idusuario + ", nombre_de_usuario=" + nombre_de_usuario + ", nombre=" + nombre + ", apellido=" + apellido + ", numero_de_telefono=" + numero_de_telefono + ", correo=" + correo + ", contrasena=" + contrasena + '}';
    }

}
