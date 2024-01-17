package models;
import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Receta extends Model {

    public String nombre;
    @Lob
    public String descripcion;
    public int tiempoestimado; //en minutos
    public int dificultad; // 1 a 5
    public String categoria;

    public Receta(String nombre, String descripcion, int tiempoestimado, int dificultad, String categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tiempoestimado = tiempoestimado;
        this.dificultad = dificultad;
        this.categoria = categoria;
    }
    @OneToMany (mappedBy = "valoracionReceta")
    public List <Valoracion> ValoracionesR = new ArrayList<Valoracion>();

    @OneToMany (mappedBy = "contenidoReceta")
    public List <Contenido> CantidadesR = new ArrayList<Contenido>();

}