package entities;

import java.util.UUID;

public class Seccion {

    private int grupo;

    private  int capacidad;

    private int matriculados;

    private Docente docente;

    private UUID uuid;

    public Seccion(int grupo, int capacidad, Docente d){
        this.grupo = grupo;
        this.capacidad = capacidad;
        this.docente = d;
    }

    public boolean hayCapacidad(){
        return capacidad - matriculados > 0;
    }

    public String SeccionmToString(){
        return "grupo: "+ this.grupo + " capacidad: "+ this.capacidad + " matriculados: " + this.matriculados + " docente: " + docente.NombreCompleto();
    }

    public int getGrupo(){
        return this.grupo;
    }

    public int getCapacidad(){
        return this.capacidad;
    }

    public int getMatriculados(){
        return this.matriculados;
    }

    public Docente getDocente(){
        return this.docente;
    }

    public boolean matricular(){
        if (hayCapacidad()){
            this.matriculados++;
            return true;
        }
        return false;
    }
}
