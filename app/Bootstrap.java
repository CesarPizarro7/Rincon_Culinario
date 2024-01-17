import play.test.*;
import play.jobs.*;
import models.*;

@OnApplicationStart
public class Bootstrap extends Job {

    public void doJob() {
        // Load default data if the database is empty
            if(Usuari.count() == 0) {
            //Inicialitzar la base de dades
            //Creamos los usuarios
            Usuari u0 = new Usuari("admin", "admin", true).save();
            Usuari u1 = new Usuari("Cesar", "0108", true).save();
            Usuari u2 = new Usuari("Laura", "1909", false).save();
            Usuari u3 = new Usuari("Janice", "0111", false).save();

            //Creamos las recetas
            Receta r1 = new Receta("Tortilla de Patata","Preparación de la tortilla de patatas:\n" +
                    "1.- Pelamos las patatas, las lavamos para quitar restos de suciedad y muy importante, las secamos. Cortamos en láminas semifinas, a mí no me gusta que se deshagan sino que al freírlas se tuesten un poco. Las colocamos en un bol grande, donde luego vamos a mezclar con el huevo y añadimos sal al gusto. Removemos bien y reservamos.\n" +
                    "2.- Elegimos nuestra sartén más grande y antiadherente. La ponemos al fuego y añadimos un buen aceite de oliva virgen extra. No tengáis miedo en gastaros un poco de dinero en aceite, le va a dar ese punto de sabor que distingue vuestra tortilla de las demás, podéis emplear muchas variedades: arbequina, picual, cornicabra, hojiblanca, royal… el que más os guste, pero de calidad.\n" +
                    "3.- Introducimos las patatas cortadas y ya saladas y dejamos que se cocinen durante aproximadamente de veinte a treinta minutos a fuego bajo. El tema del grosor de las patatas también va a gustos. Hay quien prefiere cortarlas a trozos muy pequeños, en láminas muy finas que casi se rompan al freír y o más bien grandes.\n" +
                    "4.- Mientras se están friendo las patatas, en el bol donde luego vamos a echar las patatas batimos los huevos, reservamos. Pelamos la cebolla y cortamos lo más fino posible.\n" +
                    "5.- En otra sartén calentamos aceite de oliva y añadimos los trozos de cebolla. Pochamos hasta que tenga un color dorado, que tenga un punto de caramelización pero sin llegar a quemarse. La cebolla se hará antes que las patatas, así que escurrimos y añadimos al bol con el huevo batido.\n" +
                    "Preparación final de la tortilla de patatas con cebolla:\n" +
                    "1.- Os dejo con un truco que le dará a la tortilla un toque diferente y muy rico, es totalmente opcional. Si tenéis en la nevera cebolla caramelizada o la queréis hacer, sustituid la cebolla pochada por la caramelizada. Quedará impresionante.\n" +
                    "2.- Quitamos con una espumadera de la sartén, dejando las patatas con el menor resto de aceite posible, bien escurridas. Si no queremos nada de aceite extra podemos emplear un colador grande. Las dejamos escurrir y luego las introducimos al bol con la cebolla y el huevo.\n" +
                    "3.- Reposamos la futura tortilla durante 15 minutos para que se junten bien todos los sabores. Pasados esos minutos esta mezcla ya está deliciosa, probad a tostar un poco de pan y añadidle una capita con esta mezcla, increíble.\n" +
                    "4.- En la misma sartén en la que hemos frito las patatas y una vez retirado el aceite. Cocinamos la mezcla que tenemos en reposo. A mí me gusta poco hecha, que al partirla con el tenedor salga un poco de huevo líquido. Para este tipo de tortilla sólo necesitamos 4 minutos a fuego medio-alto por cada lado. Depende de lo cuajada que queramos que quede la tortilla.\n" +
                    "5.- Para darle la vuelta yo empleo un plato llano grande que tengo para las ensaladas. Pero se puede usar una tapadera de borde liso, incluso ahora he visto que venden tapaderas especiales para dar la vuelta a la tortilla.\n" +
                    "6.- Emplead el método más cómodo y que más fácil os sea para que no se os desparrame, con cuidado. No desesperéis si no os sale, en ese caso tendréis una tortilla más cuajada, pero igual de rica.",50,3, "Tortillas y Huevos").save();
            Receta r2 = new Receta("Macarrones con tomate y atun","Preparación de la salsa de los macarrones. El atún con tomate:\n" +
                    "1.- Pelamos la cebolla y picamos finamente. Calentamos un poco de aceite de oliva en una cazuela y pochamos la cebolla picada a fuego suave durante aproximadamente 10 minutos o hasta que esté traslúcida. No dejéis que se dore porque puede amargar la salsa.\n" +
                    "2.- A continuación añadimos el tomate triturado, todo de golpe. Si usamos tomate de lata, no tenemos más que abrir la lata y punto, pero si usamos tomate natural necesitaremos lavar bien cada pieza, triturar y pasar por un chino o colador para retirar los restos de pieles y pepitas.\n" +
                    "3.- Sazonamos y cocemos a medio-bajo suave durante 15 o 20 minutos o hasta obtener el punto de espesor que más os guste. A mí me gusta la salsa de tomate espesa, así que la reduzco bastante. Al gusto.\n" +
                    "4.- Sí salpica mucho la salsa colocamos la tapa de la cacerola, pero sin cerrar y dejando una pequeña abertura. Esto permitirá que se vaya evaporando el líquido y que los salpicones no pongan todo perdido.\n" +
                    "Cocción de la pasta y presentación final de los macarrones:\n" +
                    "1.- Mientras se hace la salsa de tomate ponemos a hervir abundante agua con sal en una cacerola. Usamos 1 litro y 10 gramos de sal por cada 100 gramos de pasta utilizados, según marcan los cánones italianos.\n" +
                    "2.- Añadimos los macarrones y dejamos cocer un minuto menos del tiempo que nos recomienda el  fabricante. En este el fabricante recomienda 8 minutos, así que los cocemos solo 7 minutos. Escurrimos los macarrones y los refrescamos bajo el grifo de agua fría para cortar la cocción.\n" +
                    "3.- Volvemos a la salsa para comprobar el punto de la misma y añadir el atún, bien escurrido, y cocer un par de minutos más. Probamos el punto de sal y rectificamos si fuera necesario.\n" +
                    "4.- Cuando la salsa esté lista añadimos los macarrones a la misma, mezclamos bien para que la pasta se empape del sabor de la salsa y damos un hervor corto de un minuto. Servimos caliente en la mesa y acompañamos cada plato con un poco de atún y queso rallado al gusto.",30,1, "Pasta").save();

            Receta r3 = new Receta("Crema Catalana","Preparación de la leche aromatizada y crema:\n" +
                    "1.-El primer paso es preparar los ingredientes con los que vamos a aromatizar la leche. Lavamos muy bien el limón y la naranja y pelamos su piel de manera fina, sin mucho blanco que luego nos amargue el postre.\n" +
                    "2.-Abrimos la vaina de vainilla  y sacamos las semillas que reservaremos para añadir más tarde a la leche. Calentamos la leche a fuego medio casi hasta el punto de ebullición.\n" +
                    "3.-Bajamos la temperatura y retiramos del fuego, añadimos las semillas de vainilla, la piel de la naranja y el limón, y por último la rama de canela. Dejamos todo en reposo durante 5 minutos mientras batimos los huevos.\n" +
                    "4.-Separamos las claras de las yemas y las ponemos en un bol con el azúcar (250 g). Batimos enérgicamente con una batidora eléctrica o unas varillas hasta lograr una mezcla espumosa.\n" +
                    "Preparación de la crema catalana:\n" +
                    "1.-Añadimos al bol que contiene la crema de huevo y azúcar la harina fina de maíz, para que se mezcle mejor nos podemos ayudar con un poco de la leche caliente del paso 2. Batimos todo muy bien.\n" +
                    "2.-Calentamos la leche de nuevo a temperatura media durante 10 minutos con la vainilla, la piel de naranja-limón y la canela. Después de la cocción pasamos por un colador para eliminar impurezas.\n" +
                    "3.-Ponemos la cazuela con la leche a fuego lento, añadimos la mezcla de crema de huevo, azúcar y harina. Removemos continuamente con una cuchara de madera o unas varillas (acabo de comprar unas de silicona que son buenísimas) sin que se formen grumos y cuidando que nunca llegue a hervir. Cuando empiece a espesar retiramos del fuego y continuamos moviendo un minuto más. Tened en cuenta que aunque la crema esté un poco líquida quedará más espesa cuando se enfríe.\n" +
                    "4.-Llenamos unas cazuelas o cuencos chulos que tengáis por casa. Yo he usado las únicas que tengo, de cerámica blanca, sencillas y tamaño justo para un postre individual. Dejamos que reposen 1/2 hora y luego guardamos en la nevera durante 2-3 horas.\n" +
                    "Caramelizar la superficie y presentación final de la crema:\n" +
                    "1.-Cuando vayamos a servir la crema catalana quemamos la superficie. Echamos una cucharada de azúcar en la parte superior y quemamos con un soplete de cocina. Se puede usar también una pala de quemar especial para este postre, pero no es fácil de conseguir y ocupa mucho en una cocina pequeña como la mía. Así que prefiero el soplete que mantiene la crema fría y genera perfectamente esa famosa placa de azúcar crujiente.\n" +
                    "2.-Se suele servir en cazuelitas de barro, esto de forma tradicional, aunque también puede servirse en distintos platos de postre. El contraste caliente del azúcar con la crema fría es delicioso, y os aconsejo romper primero con la cucharilla y cuando haga crack, zamparse la mezcla con una buena porción de crema, todo un arte.",30,2, "Postres").save();

            //Creamos las Valoracioes
            Valoracion v1 = new Valoracion("no está mal",3).save();
            //Asignamos al usuario 1 la valoracion 1
            u1.ValoracionesU.add(v1);
            u1.save();
            //Asignamos la valoracion 1 a la receta 1
            v1.valoracionReceta=r1;
            v1.save();
            //Asignamos a la valoracion 1 el usuario 1 (quien la ha hecho)
            v1.valoracionUsuario=u1;
            v1.save();
            //Creamos la valoracion 2
            Valoracion v2 = new Valoracion("yo podria hacerlo bastante mejor",2).save();
            //Asignamos al usuario 2 la valoracion 2
            u2.ValoracionesU.add(v2);
            u2.save();
            //Asignamos la valoracion 2 a la receta 1
            v2.valoracionReceta=r1;
            v2.save();
            //Asignamos a la valoracion 2 el usuario 2 (quien la ha hecho)
            v2.valoracionUsuario=u2;
            v2.save();
            //Creamos la valoracion 3
            Valoracion v3 = new Valoracion("Me salió genial, a mí Daniel le gustó mucho",5).save();
            //Iniciamos el usuario 3
            u2.ValoracionesU.add(v3);
            u3.save();
            //Asignamos la valoracion 3 a la receta 2
            v3.valoracionReceta=r2;
            v3.save();
            //Asignamos a la valoracion 3 el usuario 3 (quien la ha hecho)
            v3.valoracionUsuario=u3;
            v3.save();
            //Creamos las Valoracioes
            Valoracion v4 = new Valoracion("Esta rica y no me resultó difícil",4).save();
            //Asignamos al usuario 1 la valoracion 1
            u1.ValoracionesU.add(v4);
            u1.save();
            //Asignamos la valoracion 1 a la receta 1
            v4.valoracionReceta=r3;
            v4.save();
            //Asignamos a la valoracion 1 el usuario 1 (quien la ha hecho)
            v4.valoracionUsuario=u1;
            v4.save();

            //Asignamos a la receta 1 las valoraciones 1, 2 y a la recete 2 la valoracion 3                                 //Usuario    -> Hecho
            r1.ValoracionesR.add(v1);                                                                                       //Valoracion -> Hecho
            r1.save();                                                                                                      //Receta     -> Hecho
            r1.ValoracionesR.add(v2);                                                                                       //Ingrediente->
            r1.save();                                                                                                      //Contenido  ->
            r2.ValoracionesR.add(v3);
            r2.save();
            r3.ValoracionesR.add(v4);
            r3.save();

            //Voy a crear una lista de ingredientes
            Ingrediente i1 = new Ingrediente("Patata", 0.42).save(); //0.42 €/ud. (patata 220g aprox. Mercadona)
            Ingrediente i2 = new Ingrediente("Cebolla", (2.38/4)).save(); //2.38 €/kg (cebolla 250g aprox. Mercadona)
            Ingrediente i3 = new Ingrediente("Huevo", (2.20/12)).save(); //2.20 €/ud. (12 huevos/ud. Mercadona)
            Ingrediente i4 = new Ingrediente("Aceite", 0).save(); //Coste 0 ya que no es una gran cantidad (no sabria aproximar un precio
            Ingrediente i5 = new Ingrediente("Sal", 0).save(); //Coste 0 ya que no es una gran cantidad (no sabria aproximar un precio
            Ingrediente i6 = new Ingrediente("Macarrones", 1.24).save(); //1.24 €/kg
            Ingrediente i7 = new Ingrediente("Tomate Frito", 1.125).save(); //1.125 €/kg
            Ingrediente i8 = new Ingrediente("Atún", 12.50).save(); //12.50 €/kg
            Ingrediente i9 = new Ingrediente("Queso Rallado",1.73).save(); //1.73 €/ud
            Ingrediente i10 = new Ingrediente("Canela",1.35).save(); //1.35 €/ud
            Ingrediente i11 = new Ingrediente("Piel de Naranja",0.56).save(); //0.56 €/ud
            Ingrediente i12 = new Ingrediente("Azúcar",1.35).save(); //1.35 €/kg
            Ingrediente i13 = new Ingrediente("Leche Entera",0.91).save(); //0.91 €/brick
            Ingrediente i14 = new Ingrediente("Cáscara de Limón",0.55).save(); //0.55 €/ud
            Ingrediente i15 = new Ingrediente("Rama de Vainilla",0).save();
            Ingrediente i16 = new Ingrediente("Maicena",(1.25/6)).save();

            //Creamos los contenidos "TORTILLA DE PATATA" y asociamos los ingredientes a la cantidad y a la receta
            Contenido ContenidoTortillaPatata1 = new Contenido(3).save();
            ContenidoTortillaPatata1.contenidoReceta=r1;
            ContenidoTortillaPatata1.contenidoIngredientes=i1;
            ContenidoTortillaPatata1.save();
            i1.CantidadesIngrediente.add(ContenidoTortillaPatata1);
            i1.save();
            r1.CantidadesR.add(ContenidoTortillaPatata1);
            r1.save();
            Contenido ContenidoTortillaPatata2 = new Contenido(1).save();
            ContenidoTortillaPatata2.contenidoReceta=r1;
            ContenidoTortillaPatata2.contenidoIngredientes=i2;
            ContenidoTortillaPatata2.save();
            i2.CantidadesIngrediente.add(ContenidoTortillaPatata2);
            i2.save();
            r1.CantidadesR.add(ContenidoTortillaPatata2);
            r1.save();
            Contenido ContenidoTortillaPatata3 = new Contenido(5).save();
            ContenidoTortillaPatata3.contenidoReceta=r1;
            ContenidoTortillaPatata3.contenidoIngredientes=i3;
            ContenidoTortillaPatata3.save();
            i3.CantidadesIngrediente.add(ContenidoTortillaPatata3);
            i3.save();
            r1.CantidadesR.add(ContenidoTortillaPatata3);
            r1.save();
            Contenido ContenidoTortillaPatata4 = new Contenido(1).save();
            ContenidoTortillaPatata4.contenidoReceta=r1;
            ContenidoTortillaPatata4.contenidoIngredientes=i4;
            ContenidoTortillaPatata4.save();
            i4.CantidadesIngrediente.add(ContenidoTortillaPatata4);
            i4.save();
            r1.CantidadesR.add(ContenidoTortillaPatata4);
            r1.save();
            Contenido ContenidoTortillaPatata5 = new Contenido(1).save();
            ContenidoTortillaPatata5.contenidoReceta=r1;
            ContenidoTortillaPatata5.contenidoIngredientes=i5;
            ContenidoTortillaPatata5.save();
            i5.CantidadesIngrediente.add(ContenidoTortillaPatata5);
            i5.save();
            r1.CantidadesR.add(ContenidoTortillaPatata5);
            r1.save();

            //Macarrones
            Contenido ContenidoMacarronesAtun1 = new Contenido(0.4).save();
            ContenidoMacarronesAtun1.contenidoReceta=r2;
            ContenidoMacarronesAtun1.contenidoIngredientes=i6;
            ContenidoMacarronesAtun1.save();
            i6.CantidadesIngrediente.add(ContenidoMacarronesAtun1);
            i6.save();
            r2.CantidadesR.add(ContenidoMacarronesAtun1);
            r2.save();
            Contenido ContenidoMacarronesAtun2 = new Contenido(1).save();
            ContenidoMacarronesAtun2.contenidoReceta=r2;
            ContenidoMacarronesAtun2.contenidoIngredientes=i2;
            ContenidoMacarronesAtun2.save();
            i2.CantidadesIngrediente.add(ContenidoMacarronesAtun2);
            i2.save();
            r2.CantidadesR.add(ContenidoMacarronesAtun2);
            r2.save();
            Contenido ContenidoMacarronesAtun3 = new Contenido(0.8).save();
            ContenidoMacarronesAtun3.contenidoReceta=r2;
            ContenidoMacarronesAtun3.contenidoIngredientes=i7;
            ContenidoMacarronesAtun3.save();
            i7.CantidadesIngrediente.add(ContenidoMacarronesAtun3);
            i7.save();
            r2.CantidadesR.add(ContenidoMacarronesAtun3);
            r2.save();
            Contenido ContenidoMacarronesAtun4 = new Contenido(0.2).save();
            ContenidoMacarronesAtun4.contenidoReceta=r2;
            ContenidoMacarronesAtun4.contenidoIngredientes=i8;
            ContenidoMacarronesAtun4.save();
            i8.CantidadesIngrediente.add(ContenidoMacarronesAtun4);
            i8.save();
            r2.CantidadesR.add(ContenidoMacarronesAtun4);
            r2.save();
            Contenido ContenidoMacarronesAtun5 = new Contenido(1).save();
            ContenidoMacarronesAtun5.contenidoReceta=r2;
            ContenidoMacarronesAtun5.contenidoIngredientes=i4;
            ContenidoMacarronesAtun5.save();
            i4.CantidadesIngrediente.add(ContenidoMacarronesAtun5);
            i4.save();
            r2.CantidadesR.add(ContenidoMacarronesAtun5);
            r2.save();
            Contenido ContenidoMacarronesAtun6 = new Contenido(1).save();
            ContenidoMacarronesAtun6.contenidoReceta=r2;
            ContenidoMacarronesAtun6.contenidoIngredientes=i5;
            ContenidoMacarronesAtun6.save();
            i5.CantidadesIngrediente.add(ContenidoMacarronesAtun6);
            i5.save();
            r2.CantidadesR.add(ContenidoMacarronesAtun6);
            r2.save();
            Contenido ContenidoMacarronesAtun7 = new Contenido(0.25).save();
            ContenidoMacarronesAtun7.contenidoReceta=r2;
            ContenidoMacarronesAtun7.contenidoIngredientes=i9;
            ContenidoMacarronesAtun7.save();
            i9.CantidadesIngrediente.add(ContenidoMacarronesAtun7);
            i9.save();
            r2.CantidadesR.add(ContenidoMacarronesAtun7);
            r2.save();

            //Crema Catalana
            Contenido ContenidoCremaCatalana1 = new Contenido(10).save();
            ContenidoCremaCatalana1.contenidoReceta=r3;
            ContenidoCremaCatalana1.contenidoIngredientes=i3;
            ContenidoCremaCatalana1.save();
            i3.CantidadesIngrediente.add(ContenidoCremaCatalana1);
            i3.save();
            r3.CantidadesR.add(ContenidoCremaCatalana1);
            r3.save();
            Contenido ContenidoCremaCatalana2 = new Contenido(0.1).save();
            ContenidoCremaCatalana2.contenidoReceta=r3;
            ContenidoCremaCatalana2.contenidoIngredientes=i16;
            ContenidoCremaCatalana2.save();
            i16.CantidadesIngrediente.add(ContenidoCremaCatalana2);
            i16.save();
            r3.CantidadesR.add(ContenidoCremaCatalana2);
            r3.save();
            Contenido ContenidoCremaCatalana3 = new Contenido(1).save();
            ContenidoCremaCatalana3.contenidoReceta=r3;
            ContenidoCremaCatalana3.contenidoIngredientes=i10;
            ContenidoCremaCatalana3.save();
            i10.CantidadesIngrediente.add(ContenidoCremaCatalana3);
            i10.save();
            r3.CantidadesR.add(ContenidoCremaCatalana3);
            r3.save();
            Contenido ContenidoCremaCatalana4 = new Contenido(1).save();
            ContenidoCremaCatalana4.contenidoReceta=r3;
            ContenidoCremaCatalana4.contenidoIngredientes=i11;
            ContenidoCremaCatalana4.save();
            i11.CantidadesIngrediente.add(ContenidoCremaCatalana4);
            i11.save();
            r3.CantidadesR.add(ContenidoCremaCatalana4);
            r3.save();
            Contenido ContenidoCremaCatalana5 = new Contenido(0.4).save();
            ContenidoCremaCatalana5.contenidoReceta=r3;
            ContenidoCremaCatalana5.contenidoIngredientes=i12;
            ContenidoCremaCatalana5.save();
            i12.CantidadesIngrediente.add(ContenidoCremaCatalana5);
            i12.save();
            r3.CantidadesR.add(ContenidoCremaCatalana5);
            r3.save();
            Contenido ContenidoCremaCatalana6 = new Contenido(1.5).save();
            ContenidoCremaCatalana6.contenidoReceta=r3;
            ContenidoCremaCatalana6.contenidoIngredientes=i13;
            ContenidoCremaCatalana6.save();
            i13.CantidadesIngrediente.add(ContenidoCremaCatalana6);
            i13.save();
            r3.CantidadesR.add(ContenidoCremaCatalana6);
            r3.save();
            Contenido ContenidoCremaCatalana7 = new Contenido(1).save();
            ContenidoCremaCatalana7.contenidoReceta=r3;
            ContenidoCremaCatalana7.contenidoIngredientes=i14;
            ContenidoCremaCatalana7.save();
            i14.CantidadesIngrediente.add(ContenidoCremaCatalana7);
            i14.save();
            r3.CantidadesR.add(ContenidoCremaCatalana7);
            r3.save();
            Contenido ContenidoCremaCatalana8 = new Contenido(1).save();
            ContenidoCremaCatalana8.contenidoReceta=r3;
            ContenidoCremaCatalana8.contenidoIngredientes=i15;
            ContenidoCremaCatalana8.save();
            i15.CantidadesIngrediente.add(ContenidoCremaCatalana8);
            i15.save();
            r3.CantidadesR.add(ContenidoCremaCatalana8);
            r3.save();
        }
    }

}