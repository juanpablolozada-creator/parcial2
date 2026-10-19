package red;

import sensores.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Nodo IoT que COMPONE sensores.
 * Los sensores se crean dentro del nodo (composición real).
 */
public class NodoIoT {

    private String id;
    private ArrayList<Sensor> sensores;

    public NodoIoT(String id) {
        this.id = id;
        this.sensores = new ArrayList<>();
    }

    /**
     * Crea y agrega un sensor del tipo indicado dentro del nodo.
     * Tipo: "TEMPERATURA", "HUMEDAD", "LUMINOSIDAD".
     * Retorna el sensor creado, o null si el tipo es inválido.
     */
    public Sensor agregarSensor(String tipo, String idSensor, int capacidad) {
        if (tipo == null) {
            return null;
        }
        Sensor nuevo;
        switch (tipo) {
            case "TEMPERATURA":
                nuevo = new SensorTemperatura(idSensor, capacidad);
                break;
            case "HUMEDAD":
                nuevo = new SensorHumedad(idSensor, capacidad);
                break;
            case "LUMINOSIDAD":
                nuevo = new SensorLuminosidad(idSensor, capacidad);
                break;
            default:
                return null;
        }
        sensores.add(nuevo);
        return nuevo;
    }

    /**
     * Promedio de temperatura del nodo. Retorna 0 si no hay sensores de temperatura.
     * Pista: usar instanceof para filtrar.
     */
    public double promedioTemperatura() {
        double suma = 0;
        int contador = 0;
        for (Sensor s : sensores) {
            if (s instanceof SensorTemperatura) {
                suma += s.promedioUltimasLecturas();
                contador++;
            }
        }
        if (contador == 0) {
            return 0;
        }
        return suma / contador;
    }

    /**
     * Lista de sensores cuya última lectura está fuera de rango.
     */
    public List<Sensor> sensoresFueraDeRango() {
        List<Sensor> resultado = new ArrayList<>();
        for (Sensor s : sensores) {
            if (s.getCantidadLecturas() > 0) {
                int cap = s.getUltimasLecturas().length;
                int ultimoIndice = (s.getIndiceActual() - 1 + cap) % cap;
                int ultimaLectura = s.getUltimasLecturas()[ultimoIndice];
                if (s.fueraDeRango(ultimaLectura)) {
                    resultado.add(s);
                }
            }
        }
        return resultado;
    }

    public String getId() { return id; }
    public ArrayList<Sensor> getSensores() { return sensores; }
}