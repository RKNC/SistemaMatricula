package processes;

import entities.Docente;
import entities.Curso;
import entities.Estudiante;
import entities.Seccion;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Enumeration;
import java.util.UUID;
import java.util.Vector;

import java.security.PublicKey;

public class MatriculaConfig {

    private Vector<Docente> docentes;
    private Vector<Curso> cursos;
    private Vector<Estudiante> estudiantes;

    public int MatriculaConfig(){
        this.docentes = new Vector<Docente>();
        this.cursos = new Vector<Curso>();
        this.estudiantes = new Vector<Estudiante>();
    }

    public int crearCurso(Curso c){
        Enumeration<Curso> cursos = this.cursos.elements();
        while (cursos.hasMoreElements()){
            Curso curso = cursos.nextElement();
            if ( curso.getNombre().equals(c.getNombre())) //Curso ya existe
                return 1;
        }
        this.cursos.add((Curso) c);
        return 0;
    }

    public int crearDocente(Docente docente){
        Enumeration<Docente> ds = this.docentes.elements();
        while (ds.hasMoreElements()){
            Docente d = ds.nextElement();
            if ( docente.NombreCompleto().equals(d.NombreCompleto())) //Docente ya existe
                return 1;
        }
        this.docentes.add((Docente) docente);
        return 0;
    }

    public int crearEstudiante(Estudiante estudiante){
        Enumeration<Estudiante> es = this.estudiantes.elements();
        while (es.hasMoreElements()){
            Estudiante e = es.nextElement();
            if ( e.NombreCompleto().equals(estudiante.NombreCompleto())) //Docente ya existe
                return 1;
        }
        this.estudiantes.add((Estudiante) estudiante);
        return 0;
    }

    public int agregarSeccion(Curso curso, Docente docente, int capacidad){
        Docente d = existeDocente(docente);
        if(d == null){
            return 1;
        }
        Curso c = existeCurso(curso);
        if (c == null){
            return 1;
        }

        Seccion s = new Seccion(c.cantidadSecciones()+1, capacidad, d);
        c.agregarSeccion(s);
        // TODO: valirdar que un mismo docente no puede tener a cargo dos secciones del mismo curso
        return 0;
    }

    public Docente buscarDocente(String uuid){
        UUID u = UUID.fromString(uuid);
        Enumeration<Docente> ds = this.docentes.elements();
        while (ds.hasMoreElements()){
            Docente d = ds.nextElement();
            if (d.getUuid().equals(u))
                return d;
        }
        return  null;
    }

    public JSONArray docentesToJSON(){
        JSONArray arrayDocentes = new JSONArray();
        Enumeration<Docente> ds = this.docentes.elements();
        while (ds.hasMoreElements()){
            Docente d =ds.nextElement();
            JSONObject obj = new JSONObject();
            obj.put("nombre", d.getNombres());
            obj.put("apellidoPaterno", d.getapellidoPaterno());
            obj.put("apellidoMaterno", d.getapellidoMaterno());
            obj.put("uuid", d.getUuid().toString());
            arrayDocentes.add(obj);
        }
        return  arrayDocentes;
    }

    public JSONArray estudiantesToJSON(){
        JSONArray arrayEstudiantes = new JSONArray();
        Enumeration<Estudiante> es = this.estudiantes.elements();
        while ((es.hasMoreElements())){
            Estudiante e = es.nextElement();
            JSONObject obj = new JSONObject();
            obj.put("nombre", d.getNombres());
            obj.put("apellidoPaterno", d.getapellidoPaterno());
            obj.put("apellidoMaterno", d.getapellidoMaterno());
            obj.put("uuid", d.getUuid().toString());
            arrayEstudiantes.add(obj);
        }
        return arrayEstudiantes;
    }

    public JSONArray cursosToJSON(){
        JSONArray arrayCursos = new JSONArray();
        Enumeration<Curso> cs = this.cursos.elements();
        while ((cs.hasMoreElements())){
            Curso c = cs.nextElement();
            JSONObject JSONcurso = new JSONObject();
            JSONcurso.put("nombre", c.getNombre());
            JSONcurso.put("semestre", c.getSemestre());

            JSONArray arraySecciones = new JSONArray();
            Enumeration<Seccion> ss = c.getSecciones().elements();
            while (ss.hasMoreElements()){
                Seccion s = ss.nextElement();
                JSONObject JSONseccion = new JSONObject();
                JSONseccion.put("grupo", s.getGrupo());
                JSONseccion.put("capacidad", s.getCapacidad());
                JSONseccion.put("matriculados", s.getMatriculados());
                JSONseccion.put("docente_uuid", s.getDocente().getUuid().toString());

                arraySecciones.add(JSONseccion);
            }
            JSONcurso.put("secciones", arraySecciones);
            arrayCursos.add(JSONcurso);
        }
        return arrayCursos;
    }
    private Docente existeDocente(Docente docente){
        Enumeration<Docente> ds = this.docentes.elements();
        while (ds.hasMoreElements()){
            Docente d = ds.nextElement();
            if(d.NombreCompleto().equals(docente.NombreCompleto())) //el docente ya existe
                return d;
        }
        return null;
    }

    private Curso existeCurso(Curso curso){
        Enumeration<Curso> cs = this.cursos.elements();
        while (cs.hasMoreElements()){
            Curso c = cs.nextElement();
            if(c.getNombre().equals(curso.getNombre()))//curso existente
                return c;
        }
        return null;
    }

    public void mostrarDocentes(){
        Enumeration<Docente> ds = this.docentes.elements();
        while (ds.hasMoreElements()){
            Docente d = ds.nextElement();
            System.out.println(d.NombreCompleto());
            d.mostrarSecciones();
        }
    }

    public void mostrarEstudiantes(){
        Enumeration<Estudiante> es = this.estudiantes.elements();
        while (es.hasMoreElements()){
            Estudiante e = es.nextElement();
            System.out.println(e.NombreCompleto());
            e.mostrarSecciones();
        }
    }

    public void mostrarCursos(){
        Enumeration<Curso> cursos = this.cursos.elements();
        while (cursos.hasMoreElements()){
            Curso c = cursos.nextElement();
            System.out.println(c.getNombre());
        }
    }

    public void mostrarSecciones(){
        Enumeration<Curso> cursos = this.cursos.elements();
        int index = 0;
        while (cursos.hasMoreElements()){
            Curso c = cursos.nextElement();

            Enumeration<Seccion> secciones = c.getSecciones().elements();
            while (secciones.hasMoreElements()){
                Seccion s = secciones.nextElement();

                System.out.println(index + ".: " + c.getNombre() + " seccion: " + s.SeccionmToString());
                index ++;
            }
        }
    }

    public Seccion matriculaSeccion(int idx){
        Enumeration<Curso> cursos = this.cursos.elements();
        int index = 0;
        while ((cursos.hasMoreElements())){
            Curso c = cursos.nextElement();

            Enumeration<Seccion> secciones = c.getSecciones().elements();
            while (secciones.hasMoreElements()){
                Seccion s = secciones.nextElement();
                if (index == idx){
                    if (!s.matricular()){
                        System.out.println("lo sentimos, no hay capacidad");
                        return null;
                    }
                    return s;
                }
                index++;
            }
        }
        return  null;
    }
}
