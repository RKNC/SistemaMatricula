package persistance;

import processes.MatriculaConfig;

public class Database implements Persistance{
    public void guardarConfig(MatriculaConfig config){
        System.out.println("Guardando en db. . .");
    }

    public void leerConfig(MatriculaConfig config){
        //Todo: implementation depends on the db i choose
        System.out.println("Leyendo de db. . .");
    }
}
