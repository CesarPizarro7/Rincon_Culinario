package models;
import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Usuari extends Model {

    public String nombre;
    public String password;
    public boolean isAdmin;

    public Usuari(String nombre, String password, boolean isAdmin) {
        this.nombre = nombre;
        this.password = password;
        this.isAdmin=isAdmin;
    }
    @OneToMany (mappedBy = "valoracionUsuario")
    public List <Valoracion> ValoracionesU = new ArrayList<Valoracion>();

}