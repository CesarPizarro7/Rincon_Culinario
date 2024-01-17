package controllers;

import play.mvc.*;

import java.util.*;
import models.*;

public class Application extends Controller {

    static String USUARIO;

    @Before
    static void connectedUser() {
        USUARIO = session.get("user");
    }

    public static void index() {
        if (USUARIO != null) {
            IniciFormLogged(USUARIO, "Bienvenido de vuelta " + USUARIO);
        }
        IniciForm("");
    }

    public static void TancaSessio() {
        session.clear();
        String mensaje = "Se ha cerrado sesion correctamente";
        IniciForm(mensaje);
    }

    //FUNCIONES

    public static void IniciForm(String mensaje) {
        List<Receta> r = Receta.findAll();
        // Crear el mapa de recetas y puntuaciones medias
        Map<Receta, Double> RecetasConPuntuacionMedia = new HashMap<>();
        for (Receta receta : r) {
            double puntuacionMedia = ValoracionMedia(receta.nombre);
            RecetasConPuntuacionMedia.put(receta, puntuacionMedia);
        }
        renderArgs.put("recetasConPuntuacionMedia", RecetasConPuntuacionMedia);
        renderArgs.put("recetas", r);

        Map<String, List<String>> categoriasRecetas = Categorias();
        renderArgs.put("categoriasRecetas", categoriasRecetas);
        renderArgs.put("mensaje", mensaje);

        renderTemplate("Application/Inici.html");
    }

    public static void IniciFormLogged(String nombre, String mensaje) {
        renderArgs.put("UserName", nombre);
        List<Usuari> admins = Usuari.find("byIsAdmin", true).fetch();
        List<String> nombresAdmins = new ArrayList<>();
        for (Usuari admin : admins) {
            nombresAdmins.add(admin.nombre);
        }
        renderArgs.put("nombresAdmins", nombresAdmins);
        List<Receta> r = Receta.findAll();
        String nombresRecetas = "";
        for (Receta receta : r) {
            nombresRecetas += "'" + receta.nombre + "',";
        }
        renderArgs.put("listarecetas", nombresRecetas);

        // Crear el mapa de recetas y puntuaciones medias
        Map<Receta, Double> RecetasConPuntuacionMedia = new HashMap<>();
        for (Receta receta : r) {
            double puntuacionMedia = ValoracionMedia(receta.nombre);
            RecetasConPuntuacionMedia.put(receta, puntuacionMedia);
        }
        renderArgs.put("recetasConPuntuacionMedia", RecetasConPuntuacionMedia);
        renderArgs.put("recetas", r);

        Map<String, List<String>> categoriasRecetas = Categorias();
        renderArgs.put("categoriasRecetas", categoriasRecetas);
        renderArgs.put("mensaje", mensaje);

        renderTemplate("Application/IniciLogueado.html");
    }

    public static void RegistreForm() {
        renderTemplate("Application/Registro.html");
    }

    public static void SobreMi() {
        Map<String, List<String>> categoriasRecetas = Categorias();
        renderArgs.put("categoriasRecetas", categoriasRecetas);
        renderTemplate("Application/SobreMi.html");
    }

    public static void SobreMiLogged(String nombre) {
        List<Receta> r = Receta.findAll();
        String nombresRecetas = "";
        for (Receta receta : r) {
            nombresRecetas += "'" + receta.nombre + "',";
        }
        renderArgs.put("listarecetas", nombresRecetas);

        renderArgs.put("UserName", nombre);
        Map<String, List<String>> categoriasRecetas = Categorias();
        renderArgs.put("categoriasRecetas", categoriasRecetas);
        renderTemplate("Application/SobreMiLogged.html");
    }

    public static void LoginForm() {
        renderTemplate("Application/Login.html");
    }

    public static void RecetaForm(String nombreReceta, String nombreUsuario, String mensaje) {
        List<Receta> r = Receta.findAll();
        String nombresRecetas = "";
        for (Receta receta : r) {
            nombresRecetas += "'" + receta.nombre + "',";
        }
        renderArgs.put("listarecetas", nombresRecetas);

        List<Usuari> admins = Usuari.find("byIsAdmin", true).fetch();
        List<String> nombresAdmins = new ArrayList<>();
        for (Usuari admin : admins) {
            nombresAdmins.add(admin.nombre);
        }
        renderArgs.put("nombresAdmins", nombresAdmins);

        Receta receta1 = Receta.find("byNombre", nombreReceta).first();
        // Crear el mapa de recetas y puntuaciones medias
        Map<Receta, Double> RecetasConPuntuacionMedia = new HashMap<>();
        double puntuacionMedia = ValoracionMedia(receta1.nombre);
        RecetasConPuntuacionMedia.put(receta1, puntuacionMedia);

        renderArgs.put("recetasConPuntuacionMedia", RecetasConPuntuacionMedia);
        renderArgs.put("recetaprincipal", receta1);

        Map<String, List<String>> categoriasRecetas = Categorias();
        renderArgs.put("categoriasRecetas", categoriasRecetas);
        renderArgs.put("UserName", nombreUsuario);

        // Crear el mapa de usuarios y valoraciones
        Map<String, List<Valoracion>> usuariosValoraciones = new HashMap<>();
        List<Valoracion> valoraciones = receta1.ValoracionesR;
        for (Valoracion valoracion : valoraciones) {
            String usuario = valoracion.valoracionUsuario.nombre;
            usuariosValoraciones.computeIfAbsent(usuario, k -> new ArrayList<>()).add(valoracion);
        }
        renderArgs.put("usuariosValoraciones", usuariosValoraciones);
        renderArgs.put("mensaje", mensaje);

        renderTemplate("Application/Receta.html");
    }

    public static void RecetasPorCategoriaForm(String categoria, String nombreUsuario) {
        List<Receta> a = Receta.findAll();
        String nombresRecetas = "";
        for (Receta receta : a) {
            nombresRecetas += "'" + receta.nombre + "',";
        }
        renderArgs.put("listarecetas", nombresRecetas);

        List<Receta> r = Receta.find("byCategoria", categoria).fetch();
        // Crear el mapa de recetas y puntuaciones medias
        Map<Receta, Double> RecetasConPuntuacionMedia = new HashMap<>();
        for (Receta receta : r) {
            double puntuacionMedia = ValoracionMedia(receta.nombre);
            RecetasConPuntuacionMedia.put(receta, puntuacionMedia);
        }
        renderArgs.put("recetasConPuntuacionMedia", RecetasConPuntuacionMedia);
        renderArgs.put("recetas", r);

        Map<String, List<String>> categoriasRecetas = Categorias();
        renderArgs.put("categoriasRecetas", categoriasRecetas);
        renderArgs.put("UserName", nombreUsuario);
        renderArgs.put("categoria", categoria);

        renderTemplate("Application/RecetasPorCategoria.html");
    }

    public static void RecetasPorCategoriaNotLoggedForm(String categoria) {

        List<Receta> r = Receta.find("byCategoria", categoria).fetch();
        // Crear el mapa de recetas y puntuaciones medias
        Map<Receta, Double> RecetasConPuntuacionMedia = new HashMap<>();
        for (Receta receta : r) {
            double puntuacionMedia = ValoracionMedia(receta.nombre);
            RecetasConPuntuacionMedia.put(receta, puntuacionMedia);
        }
        renderArgs.put("recetasConPuntuacionMedia", RecetasConPuntuacionMedia);
        renderArgs.put("recetas", r);

        Map<String, List<String>> categoriasRecetas = Categorias();
        renderArgs.put("categoriasRecetas", categoriasRecetas);
        renderArgs.put("categoria", categoria);

        renderTemplate("Application/RecetasPorCategoriaNotLogged.html");
    }

    public static void RecetasFacilesForm(String nombreUsuario) {
        List<Receta> b = Receta.findAll();
        String nombresRecetas = "";
        for (Receta receta : b) {
            nombresRecetas += "'" + receta.nombre + "',";
        }
        renderArgs.put("listarecetas", nombresRecetas);
        List<Receta> a = Receta.findAll();
        List<Receta> r = new ArrayList<>();
        Map<Receta, Double> RecetasConPuntuacionMedia = new HashMap<>();
        for (Receta receta : a) {
            if (receta.dificultad <= 2) {
                r.add(receta);
                double puntuacionMedia = ValoracionMedia(receta.nombre);
                RecetasConPuntuacionMedia.put(receta, puntuacionMedia);
            }
        }

        renderArgs.put("recetasConPuntuacionMedia", RecetasConPuntuacionMedia);
        renderArgs.put("recetas", r);

        Map<String, List<String>> categoriasRecetas = Categorias();
        renderArgs.put("categoriasRecetas", categoriasRecetas);
        renderArgs.put("UserName", nombreUsuario);
        renderTemplate("Application/RecetasFaciles.html");
    }

    public static void RecetasFacilesNotLoggedForm() {

        List<Receta> a = Receta.findAll();
        List<Receta> r = new ArrayList<>();
        Map<Receta, Double> RecetasConPuntuacionMedia = new HashMap<>();
        for (Receta receta : a) {
            if (receta.dificultad <= 2) {
                r.add(receta);
                double puntuacionMedia = ValoracionMedia(receta.nombre);
                RecetasConPuntuacionMedia.put(receta, puntuacionMedia);
            }
        }

        renderArgs.put("recetasConPuntuacionMedia", RecetasConPuntuacionMedia);
        renderArgs.put("recetas", r);

        Map<String, List<String>> categoriasRecetas = Categorias();
        renderArgs.put("categoriasRecetas", categoriasRecetas);

        renderTemplate("Application/RecetasFacilesNotLogged.html");
    }

    public static void GuardarComentario(String UserName, int rating, String comentario, String receta) {
        Usuari usuario = Usuari.find("byNombre", UserName).first();
        Receta recetaObj = Receta.find("byNombre", receta).first();

        if (usuario != null && recetaObj != null) {
            boolean yaComentado = false;
            for (Valoracion valoracion : recetaObj.ValoracionesR) {
                if (valoracion.valoracionUsuario.equals(usuario)) {
                    yaComentado = true;
                    String mensaje = "Vaya, parece que ya has comentado. Solo se permite 1 comentario por usuario en la misma receta.";
                    RecetaForm(receta, UserName, mensaje);
                }
            }
            if (!yaComentado) {
                Valoracion valoracion = new Valoracion(comentario, rating);
                valoracion.valoracionUsuario = usuario;
                valoracion.valoracionReceta = recetaObj;
                valoracion.save();
                recetaObj.ValoracionesR.add(valoracion);
                recetaObj.save();
                String mensaje = "Comentario guardado correctamente";
                RecetaForm(receta, UserName, mensaje);
            }
        } else {
            String mensaje = "Parece que hubo un error interno, lamentamos las molestias";
            RecetaForm(receta, UserName, mensaje);
        }
    }

    public static void BorrarComentario(String nombreReceta, String nombreUsuarioComentario, String nombreUsuario) {
        Valoracion valoracion = Valoracion.find("byValoracionUsuario.nombreAndValoracionReceta.nombre", nombreUsuarioComentario, nombreReceta).first();
        String mensaje;
        if (valoracion != null) {
            valoracion.delete();
            mensaje = "Comentario eliminado correctamente";
        } else {
            mensaje = "No se ha conseguido eliminar el comentario";
        }
        RecetaForm(nombreReceta, nombreUsuario, mensaje);
    }

    public static void BorrarReceta(String nombreReceta, String nombreUsuario) {
        Receta receta = Receta.find("byNombre", nombreReceta).first();
        String mensaje;

        if (receta != null) {
            List<Valoracion> comentarios = Valoracion.find("byValoracionReceta.nombre", nombreReceta).fetch();
            for (Valoracion comentario : comentarios) {
                comentario.delete();
            }
            List<Contenido> contenidos = Contenido.find("byContenidoReceta.nombre", nombreReceta).fetch();
            for (Contenido contenido : contenidos) {
                contenido.delete();
            }
            receta.delete();
            mensaje = "Receta: " + nombreReceta + " eliminada correctamente";
        } else {
            mensaje = "No se ha conseguido eliminar la receta";
        }

        IniciFormLogged(nombreUsuario, mensaje);
    }


    public void Registro(String nombre, String password, boolean isAdmin) {
        Usuari u = Usuari.find("byNombre", nombre).first();
        if (u == null) {
            new Usuari(nombre, password, isAdmin).save();
            session.put("user", nombre);
            IniciFormLogged(nombre, "El usuario " + nombre + " se ha registrado correctamente");
        } else {
            renderArgs.put("missatgeError", "Vaya, parece que ese nombre de usuario ya esta en uso");
            RegistreForm();
        }
    }

    public void Login(String nombre, String password) {
        Usuari u = Usuari.find("byNombre", nombre).first();
        if (u != null) {
            if (u.password.equals(password)) {
                session.put("user", nombre);
                IniciFormLogged(nombre, "Se ha iniciado sesion correctamente");
            } else {
                renderArgs.put("missatgeError", "Seguro que has escrito bien la contraseña? :/");
                LoginForm();
            }
        } else {
            renderArgs.put("missatgeError", "Lo siento no estás registrado... (todavía). ¿Te gustaría registrarte?");
            LoginForm();
        }
    }

    //Necesita modificaciones, aunque funciona por ahora
    public void delete(String nombreUsuario) {
        Usuari u = Usuari.find("byNombre", nombreUsuario).first();
        if (u == null) {
            String mensaje = "Parece que hubo un problema interno, por favor vuelva a iniciar sesion y pruebe otra vez";
            session.clear();
            IniciForm(mensaje);
        } else {
            List<Valoracion> valoraciones = Valoracion.find("byValoracionUsuario.nombre", nombreUsuario).fetch();
            if (valoraciones != null) {
                for (Valoracion valoracion : valoraciones) {
                    valoracion.delete();
                }
            }
            session.clear();
            u.delete();
            String mensaje = "El usuario " + nombreUsuario + " y sus valoraciones han sido eliminados correctamente";
            IniciForm(mensaje);
        }
    }


    public static double ValoracionMedia(String nombre) {
        // Obtener todas las valoraciones
        List<Valoracion> v = Valoracion.findAll();
        // Variable puntuacion final
        double puntuacion = 0;
        int contador = 0;
        // Quedarse con las valoraciones cuya valoracion.valoracionReceta.nombre==nombre;
        for (Valoracion valoracion : v) {
            if (valoracion.valoracionReceta.nombre.equals(nombre)) {
                puntuacion = puntuacion + valoracion.puntuacion;
                contador++;
            }
        }
        double puntuacionmedia = puntuacion / contador;
        return puntuacionmedia;
    }

    public static Map<String, List<String>> Categorias() {
        // Obtener todas las recetas
        List<Receta> recetas = Receta.findAll();

        // Mapa para almacenar las categorías y las listas de recetas
        Map<String, List<String>> categoriasRecetas = new HashMap<>();

        // Iterar sobre todas las recetas
        for (Receta receta : recetas) {
            // Obtener la categoría de la receta y el nombre de la receta
            String categoria = receta.categoria;
            String nombreReceta = receta.nombre;

            // Verificar si ya hay una lista de recetas para esa categoría en el mapa
            if (categoriasRecetas.containsKey(categoria)) {
                // Si existe, obtener la lista existente y agregar el nombre de la receta
                List<String> nombresRecetas = categoriasRecetas.get(categoria);
                nombresRecetas.add(nombreReceta);
            } else {
                // Si no existe, crear una nueva lista y agregar el nombre de la receta
                List<String> nombresRecetas = new ArrayList<>();
                nombresRecetas.add(nombreReceta);
                categoriasRecetas.put(categoria, nombresRecetas);
            }
        }

        // Devolver el mapa con las categorías y las listas de recetas
        return categoriasRecetas;
    }

    public static void MejorValoradas(String nombreUsuario) {
        List<Receta> recetas = Receta.findAll();
        recetas.sort((r1, r2) -> {
            double puntuacionMedia1 = ValoracionMedia(r1.nombre);
            double puntuacionMedia2 = ValoracionMedia(r2.nombre);
            return Double.compare(puntuacionMedia2, puntuacionMedia1);
        });

        List<Receta> a = Receta.findAll();
        String nombresRecetas = "";
        for (Receta receta : a) {
            nombresRecetas += "'" + receta.nombre + "',";
        }
        renderArgs.put("listarecetas", nombresRecetas);

        // Crear el mapa de recetas y puntuaciones medias
        Map<Receta, Double> RecetasConPuntuacionMedia = new HashMap<>();
        for (Receta receta : recetas) {
            double puntuacionMedia = ValoracionMedia(receta.nombre);
            RecetasConPuntuacionMedia.put(receta, puntuacionMedia);
        }
        renderArgs.put("recetasConPuntuacionMedia", RecetasConPuntuacionMedia);
        renderArgs.put("recetas", recetas);

        Map<String, List<String>> categoriasRecetas = Categorias();
        renderArgs.put("categoriasRecetas", categoriasRecetas);
        renderArgs.put("UserName", nombreUsuario);

        renderTemplate("Application/MejorValoradasLogged.html");
    }

    //Funciones de ANDROID

    public static int LoginANDROID(String usuario, String passwd)
    {
        Usuari u = Usuari.find("byNombre", usuario).first();
        if (u != null) {
            if (u.password.equals(passwd)) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return -2;
        }
    }

    public static int RegistroANDROID(String nombre, String passwd, String passwd2) {
        if(!passwd.equals(passwd2)){
            return -2;
        }else{
            Usuari u = Usuari.find("byNombre", nombre).first();
            if (u == null) {
                new Usuari(nombre, passwd, false).save();
                return 0;
            } else {
                return -1;
            }
        }
    }

    public static int BajaANDROID(String nombre, String passwd, String passwd2) {
        if(!passwd.equals(passwd2)){
            return -2;
        }else{
            Usuari u = Usuari.find("byNombre", nombre).first();
            if (u != null) {
                if (u.password.equals(passwd)) {
                    List<Valoracion> valoraciones = Valoracion.find("byValoracionUsuario.nombre", nombre).fetch();
                    if (valoraciones != null) {
                        for (Valoracion valoracion : valoraciones) {
                            valoracion.delete();
                        }
                    }
                    session.clear();
                    u.delete();
                    return 0;
                } else {
                    return -3;
                }
            } else {
                return -1;
            }
        }
    }

}