package entities;

public class Estudiante extends Persona{

    public  Estudiante(String nombres, String apellidoPaterno, String apellidoMaterno){
        super(nombres, apellidoPaterno, apellidoMaterno);
        tipo = "estudiante";
    }

    public Estudiante(String nombres, String apellidoPaterno, String apellidoMaterno, String uuid){
        super(nombres, apellidoPaterno, apellidoMaterno);
        tipo = "estudiante";
    }

    public void matricular(Seccion seccion){
        secciones.add(seccion);
    }

    public void retirarCurso(Seccion seccion){
        secciones.remove(seccion);
    }

    public void marcarAsistencia(){
        System.out.println("Esperar al llamado del profe");
    }
}
