package models;
import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Ingrediente extends Model {
    public String nombre;
    public double precio;
    public Ingrediente(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    @OneToMany (mappedBy = "contenidoIngredientes")
    public List <Contenido> CantidadesIngrediente = new ArrayList<Contenido>();
}
