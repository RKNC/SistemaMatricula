import persistance.JSONConfigFile;
import persistance.Persistance;
import processes.MatriculaConfig;

import java.util.Scanner;

public class SistemaMatricula {
    public static void main(String args[]){
        MatriculaConfig config = new MatriculaConfig();
        System.out.println("Bienvenido: ");

        Scanner s = new Scanner(System.in);
        System.out.println("Ingrese semestre para el que se esta configurando la matricula");
        String semestre = s.next();

        boolean setupDone = false;

        //leer configuración actual
        Persistance p = new JSONConfigFile();
        p.leerConfig(config);
    }
}
