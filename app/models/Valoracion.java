package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Valoracion extends Model {

    public String comentario;
    public int puntuacion;

    public Valoracion(String comentario, int puntuacion) {
        this.comentario = comentario;
        this.puntuacion = puntuacion;
    }

    @ManyToOne
    public Usuari valoracionUsuario;

    @ManyToOne
    public Receta valoracionReceta;


}
