package persistance;

import entities.Curso;
import entities.Docente;
import entities.Estudiante;
import entities.Seccion;
import exceptions.DocenteNotFoundException;
import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import processes.MatriculaConfig;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;

public class JSONConfigFile implements Persistance{

    private String filename;

    public JSONConfigFile(){
        this.filename = "config.json"; // JAVA SCRIPT OBJECT NOTATION
    }
    public void guardarConfig(MatriculaConfig config){
        JSONObject JSONconfig = new JSONObject();
        JSONconfig.put("docentes", config.docentesToJSON());
        JSONconfig.put("cursos", config.cursosToJSON());
        JSONconfig.put("estudiantes", config.estudiantesToJSON());
        try {
            FileWriter writer = new FileWriter(this.filename); //lectura del archivo

            writer.write(JSONconfig.toJSONString());
            writer.flush();
        }
        catch (IOException e){
            System.out.println("Exception" + e);
            e.printStackTrace();
        }
        System.out.println("Configuracion guardada");
    }

    public void leerConfig(MatriculaConfig config){
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader(this.filename); //lectura del archivo

            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            LeerDocentes(jsonObject, config);
            LeerCursos(jsonObject, config);
            LeerEstudiantes(jsonObject, config);
        }
        catch (FileNotFoundException e){
            System.out.println("Exception" + e);
        }
        catch (DocenteNotFoundException e){
            System.out.println("Exception" + e);
        }
        catch (IOException e){
            System.out.println("Exception" + e);
        }
        catch (ParseException e){
            System.out.println("Exception" + e);
        }
    }
    /* Objetivo del metodo:
        {
            "docentes":[
              {
                "nombre": "Edson",
                "apellidoPaterno": "Ticona",
                "apellidoMaterno": "Zegarra",
                "edad": 33
              }
            ]
        }
         */
    private void LeerDocentes(JSONObject jsonObject, MatriculaConfig config){
        JSONArray docentesJSONArray = (JSONArray) jsonObject.get("docentes");

        if ( docentesJSONArray == null)
            return;
        Iterator it = docentesJSONArray.iterator;
        while (it.hasNext()){
            JSONObject docente = (JSONObject) it.next();
            String nombre = (String) docente.get("nombre");
            String apellidoPaterno = (String) docente.get("apellidoPaterno");
            String apellidoMaterno = (String) docente.get("apellidoMaterno");
            String uuid = (String) docente.get("uuid");

            Docente d = new Docente(nombre, apellidoPaterno, apellidoMaterno, uuid);
            config.crearDocente(d);
            System.out.println(d.NombreCompleto());
        }
    }
    private void  LeerEstudiantes(JSONObject jsonObject, MatriculaConfig config){
        JSONArray estudiantesJSONArray = (JSONArray) jsonObject.get("estudiantes");

        if (estudiantesJSONArray == null)
            return;
        Iterator it = estudiantesJSONArray.iterator();
        while (it.hasNext()){
            JSONObject estudiante = (JSObject) it.next();
            String nombre = (String) estudiante.get("nombre");
            String apellidoPaterno = (String) estudiante.get("apellidoPaterno");
            String apellidoMaterno = (String) estudiante.get("apellidoMaterno");
            String uuid = (String) estudiante.get("uuid");

            Estudiante e = new Estudiante(nombre, apellidoPaterno, apellidoMaterno);
            config.crearEstudiante(e);
            System.out.println(e.NombreCompleto());
        }
    }
    private void LeerCursos(JSONObject jsonObject, MatriculaConfig config) throws DocenteNotFoundException{
        JSONArray cursosJSONArray = (JSONArray) jsonObject.get("cursos");

        Iterator it = cursosJSONArray.iterator();
        while (it.hasNext()){
            System.out.println("agregando curso");
            JSObject curso = (JSObject) it.next();
            String nombre = (String)curso.get("nombre");
            String semestre = (String)curso.get("semestre");
            Curso c = new Curso(nombre, semestre);
            JSONArray secciones = (JSONArray) curso.get("secciones");
            Iterator it_c = secciones.iterator();
            while (it_c.hasNext()){
                System.out.println("agregando seccion");
                JSONObject seccion = (JSONObject) it_c.next();
                Long grupo = (Long)seccion.get("capacidad");
                Long capacidad = (Long)seccion.get("capacidad");
                Long matriculados = (Long)seccion.get("matriculados");
                String docenteUUID = (String)seccion.get("docente_uuid");

                Docente d = config.buscarDocente(docenteUUID);
                if (d == null){
                    System.out.println("Docente con uuid %s no encontrado, saltando al siguiente", docenteUUID);
                    DocenteNotFoundException e = new DocenteNotFoundException();
                    throw e;
                        continue;
                }
                Seccion s = new Seccion(grupo.intValue(), capacidad.intValue(), d);
                c.agregarSeccion(s);
            }
            System.out.println("curso creado");
            config.crearCurso(c);
         }
    }
}
