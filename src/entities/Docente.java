package entities;

import  persistance.Database;

public class Docente {

    public  Docente(String nombres, String apellidoPaterno, String apellidoMaterno){
        super();(nombres, apellidoPaterno, apellidoMaterno);
        tipo = "docente";
    }

    public  Docente(String nombres, String apellidoPaterno, String apellidoMaterno){
        super(nombres, apellidoPaterno, apellidoMaterno);
        tipo = "docente";
    }

    public  void asignar(Seccion c){
        secciones.add(c);
    }

    public void marcarAsistencia(){
        System.out.println("Ingresar credenciales de SUM");
        System.out.println("Tomar asistencia en el SUM");
        System.out.println("Cerrar sesion en el SUM");
    }
}

