import entities.Curso;
import entities.Docente;
import entities.Estudiante;
import entities.Seccion;
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

        while (!setupDone){
            imprimirOpciones();
            p.guardarConfig(config);
            int option = s.nextInt();
            switch (option){
                case 1:
                    System.out.println("Ingresar nombres y apellidos del docente");
                    String nombre = s.next();
                    String apellido1 = s.next();
                    String apellido2 = s.next();
                    Docente d = new Docente(nombre, apellido1, apellido2);
                    if (config.crearDocente(d) == 0){
                        System.out.println("Docente agregado");
                    }
                    else{
                        System.out.println("Docente ya existe");
                    }
                    break;
                case 2:
                    System.out.println("Ingresar nombre del curso: ");
                    String nombreCurso = s.next();

                    Curso c =new Curso(nombreCurso, semestre);
                    if (config.crearCurso(c) == 0){
                        System.out.println("Curso agregado");
                    }
                    else{
                        System.out.println("Curso ya existe");
                    }
                    break;
                case 3:
                    System.out.println("Ingresar nombre del curso al que quiere agregar seccion: ");
                    String nombreCursoSeccion = s.next();
                    System.out.println("Ingresar la capacidad de la seccion: ");
                    int capacidad = s.nextInt();
                    System.out.println("Ingresar nombre del docente: ");
                    String dnombre = s.next();
                    String dap1 = s.next();
                    String dap2 = s.next();

                    Curso cs = new Curso(nombreCursoSeccion, semestre);
                    Docente ds =new Docente(dnombre, dap1, dap2);
                    if (config.agregarSeccion(cs,ds,capacidad) == 0)
                        System.out.println("Seccion agregada");
                    else System.out.println("Seccion no agregada");
                    break;
                case 4:
                    config.mostrarDocentes();
                    break;
                case 5:
                    config.mostrarCursos();
                    break;
                case 6:
                    config.mostrarSecciones();
                    break;
                case 7:
                    config.mostrarEstudiantes();
                    break;
                case 8:
                    setupDone = true;
                default:
                    break;
            }
        }
        while (true){
            System.out.println("Ingresar datos del estudiante: ");
            Estudiante e = leerEstudiante(s);
            config.crearEstudiante(e);
            config.mostrarSecciones();
            p.guardarConfig(config);
            System.out.println("Escoja la seleccion en la que se quiere matricular");
            int opcion = s.nextInt();
            Seccion seccion = config.matriculaSeccion(opcion);
            //FIXME: calidad que la seccion no sea null
            if (seccion != null){
                e.matricular(seccion);
            }

        }
    }
    public static void imprimirOpciones(){
        System.out.println("");
        System.out.println("Ingrese 0 para habilitar la matricula a los estudiantes");
        System.out.println("Ingrese 1 para agregar docente");
        System.out.println("Ingrese 2 para agregar curso");
        System.out.println("Ingrese 3 para agregar seccion");
        System.out.println("Ingrese 4 para mostrar docentes");
        System.out.println("Ingrese 5 para mostrar cursos");
        System.out.println("Ingrese 6 para mostrar secciones");
        System.out.println("Ingrese 7 para mostrar estudiantes");

        System.out.println("Ingrese 8 para finalizar la configuracion");
    }
    public static Estudiante leerEstudiante(Scanner s){
        String nombre = s.next();
        String apellidoPaterno = s.next();
        String apellidoMaterno = s.next();
        Estudiante e = new Estudiante(nombre, apellidoPaterno, apellidoMaterno);
        return e;
    }
}
