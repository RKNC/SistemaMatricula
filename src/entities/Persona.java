package entities;


import java.util.Enumeration;
import java.util.UUID;
import java.util.Vector;

public class Persona {
    private  String nombres;

    private String apellidoPaterno;

    private  String apeliidoMaterno;

    private UUID uuid;

    protected  String tipo; //Indica si es estudiante o docente
    protected Vector<Seccion> secciones;

    public Persona(String nombres, String apellidoPaterno, String apeliidoMaterno){
        this.nombres = nombres; //this sirve para referirse a la clase
        this.apellidoPaterno = apellidoPaterno;
        this.apeliidoMaterno = apeliidoMaterno;
        this.uuid = UUID.randomUUID();
        this.secciones = new Vector<Seccion>();
    }

    public Persona(String nombres, String apellidoPaterno, String apeliidoMaterno, String uuid){
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apeliidoMaterno = apeliidoMaterno;
        this.uuid = UUID.fromString(uuid);
        this.secciones = new Vector<Seccion>();
    }

    public String NombreCompleto(){
        return nombre + " " + apellidoPaterno + " " + apellidoPaterno;
    }

    public void mostrarSecciones(){
        Enumeration<Seccion> s = secciones.elements();
        System.out.println("El " + this.tipo + " " + NombreCompleto() + " tiene los siguientes cursos");
        while (s.hasMoreElements()){
            Seccion seccion = s.nextElement();
            System.out.println(seccion.SeccionToString());
        }
    }

    public UUID getUuid(){
        return uuid;
    }

    public String getNombres(){
        return this.nombres;
    }

    public String getApellidoPaterno(){
        return this.apellidoPaterno;
    }

    public String getApeliidoMaterno(){
        return this.apeliidoMaterno;
    }

    public abstract void  marcarAsistencia();
}
