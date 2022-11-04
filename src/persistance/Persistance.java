package persistance;

import processes.MatriculaConfig;

public interface Persistance {
    //define comportamientos enumerando una serie de prototipos de metodos
    void guardarConfig(MatriculaConfig config);
    void leerConfig(MatriculaConfig config);
}
