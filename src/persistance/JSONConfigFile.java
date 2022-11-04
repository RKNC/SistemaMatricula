package persistance;

import exceptions.DocenteNotFoundException;
import processes.MatriculaConfig;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

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

}
