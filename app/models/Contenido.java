package models;
import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Contenido extends Model {
    public double cantidad;

    public Contenido(double cantidad) {
        this.cantidad = cantidad;
    }
    @ManyToOne
    public Receta contenidoReceta;

    @ManyToOne
    public Ingrediente contenidoIngredientes;
}
