package entities;

import  java.util.Vector;

public class Curso {

    private String nombre; //referencia

    private  String semestre; //referencia

    private  Vector<Seccion> secciones;

    public Curso(String nombre, String semestre){
        this.nombre = nombre;
        this.semestre = semestre;
        this.secciones = new Vector<Seccion>();
    }

    public String getNombre(){
        return nombre;
    }

    public  int cantidadSecciones(){
        return this.secciones.size();
    }

    public void agregarSeccion(Seccion s){
        this.secciones.add(s);
    }

    public Vector<Seccion> getSecciones(){
        return secciones;
    }

    public  String getSemestre(){
        return this.semestre;
    }
}
