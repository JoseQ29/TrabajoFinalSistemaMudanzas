/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemademudanzascompartidas;
import java.util.*;
import sistemademudanzascompartidas.estructurasParaManejoDeDatos.*;
import sistemademudanzascompartidas.lecturaEscrituraTXT.escribirTexto;
/**
 *
 * @author Razor-PC V.3
 */
public class Mudanza {
    
    private HashMap<String,Cliente> clientes;
    private DiccionarioAVL ciudades;
    private Grafo rutas;
    private MapeoAMuchos solicitudes;
    private escribirTexto escribir;
    
    public Mudanza(){
        this.clientes = new HashMap<>();
        this.ciudades = new DiccionarioAVL(null);
        this.rutas = new Grafo(null);
        this.solicitudes = new MapeoAMuchos(null);
        this.escribir = new escribirTexto();
    }
    
    public void borrarLog(){
        escribir.borrarTexto();
    }
    
    public boolean cargarClienteInicial(StringTokenizer st){
        //se usa para cargar los clientes que estan en el txt inicial
        boolean salida = false;
        String tipoDoc = st.nextToken();
        int numeroDoc = Integer.parseInt(st.nextToken());
        String apellido = st.nextToken();
        String nombre = st.nextToken();
        String numeroTel = st.nextToken();
        String clave = tipoDoc + numeroDoc;
        
        Cliente cliente = new Cliente(tipoDoc, numeroDoc, apellido, nombre, numeroTel);
        if(clientes.containsKey(clave) == false){
            clientes.put(clave, cliente);
            escribir.escribirTexto("Se cargo al sistema el cliente: " + tipoDoc +numeroDoc);
            salida = true;
        }else{
            escribir.escribirTexto("No se pudo cargar el cliente: " + tipoDoc + numeroDoc);
        }
        return salida;
    }
    
    public boolean cargarCiudadInicial(StringTokenizer st){
        //se usa para cargar las ciudades que estan en el txt inicial
        
        int codigoPostal = Integer.parseInt(st.nextToken());
        String nombre = st.nextToken();
        String nombreProvincia = st.nextToken();
        
        Ciudad ciudad = new Ciudad(codigoPostal, nombre, nombreProvincia);
        
            ciudades.insertar(codigoPostal, ciudad);
            escribir.escribirTexto("Se cargo al sistema la ciudad: " + codigoPostal);
        
        return true;
    }
    
    public boolean cargarRutaInicial(StringTokenizer st){
        //se usa para cargar las rutas que estan en el txt inicial
        int ciudadO = Integer.parseInt(st.nextToken());
        int ciudadD = Integer.parseInt(st.nextToken());
        double distancia = Double.parseDouble(st.nextToken());
        
        //necesito insertar arco y eliminar
        rutas.insertarVertice(ciudadO);
        rutas.insertarVertice(ciudadD);
        rutas.insertarArco(ciudadO, ciudadD, distancia);
        escribir.escribirTexto("Se inserto una ruta de: " + ciudadO + " a: " + ciudadD + " con un peso de:" + distancia);
        
        return true;
    }
    
    public boolean cargarSolicitudInicial(StringTokenizer st){
        //se usa para cargar las solicitudes que estan en el txt inicial
        boolean salida = false;
        
        int ciudadO = Integer.parseInt(st.nextToken());
        int ciudadD = Integer.parseInt(st.nextToken());
        //fecha: dd/mm/aa
        String fecha = st.nextToken();
        String tipoDoc = st.nextToken();
        int numeroDoc = Integer.parseInt(st.nextToken());
        int metrosCubicos = Integer.parseInt(st.nextToken());
        int cantLugares = Integer.parseInt(st.nextToken());
        String calleR = st.nextToken();
        String calleE = st.nextToken();
        boolean estaPago = Boolean.parseBoolean(st.nextToken());
        int clave = generarClaveSolicitud(ciudadO, ciudadD);
        
        Solicitud solicitud = new Solicitud(ciudadO, ciudadD, fecha, tipoDoc, numeroDoc, metrosCubicos, cantLugares, calleR, calleE, estaPago);

        solicitudes.asociar(clave, solicitud);
        escribir.escribirTexto("Se cargo al sistema la solicitud: " + clave );
        salida = true;
         
        return salida;
        }
    
    private boolean verificarFecha(String fecha){
        boolean verificado = false;
        
        StringTokenizer st = new StringTokenizer(fecha, "/");
        int dia = Integer.parseInt(st.nextToken());
        int mes = Integer.parseInt(st.nextToken());
        int anio = Integer.parseInt(st.nextToken());
        
        if(anio > 2000){
            if(mes >0 && mes <13){
                switch(mes){
                    case 1,3,5,7,8,10,12:
                        if(dia <= 31){
                            verificado = true;
                        }
                        ;break;
                    case 2:
                        if(dia <= 28){
                            verificado = true;
                        }
                        ;break;
                    case 4,6,9,11:
                        if(dia <= 30){
                            verificado = true;
                        }
                        ;break;
                }
            }
        }
        return verificado;
    }
    
    private int generarClaveSolicitud(int ciudadO, int ciudadD){
        //genera la clave de la solicitud
        String cadena = Integer.toString(ciudadO) + Integer.toString(ciudadD);
        int clave = Integer.parseInt(cadena);
        return clave;
    }
    
    public void menu(){
        //menu de entradas principal
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                           Bienvenid@ al sistema de mudanzas compartidas!
                           Ingrese el numero correspondiente a la opcion que desea seleccionar:""");
        int eleccion = -1;
        while(eleccion != 0){
        System.out.println("""
                           1) Operaciones sobre ciudades. 
                           2) Operaciones sobre redes de rutas. 
                           3) Operaciones sobre clientes. 
                           4) Operaciones sobre pedidos.
                           5) Consultas sobre clientes
                           6) Consultas sobre ciudades.
                           7) Consultas sobre viajes.
                           8) Mostrar sistema.
                           0) Terminar ejecucion""");
         eleccion = sc.nextInt();
         if(!(eleccion >-1 && eleccion < 9)){
             System.out.println("Numero fuera de rango, ingrese una de las opciones numericas mostradas.");
         }else{
             switch(eleccion){
                 case 1: operacionesCiudades();break;
                 case 2: operacionesRutas();break;
                 case 3: operacionesClientes();break;
                 case 4: operacionesPedidos();break;
                 case 5: consultasClientes();break;
                 case 6: colsultasCiudades();break;
                 case 7: consultasViajes();break;
                 case 8: mostrarSistema();break; 
             }
         }
         escribir.escribirTexto("Diccionario AVL de ciudades: " + "\n" + ciudades.toString() + "\n" + "Grafo de rutas: " + "\n" + rutas.toString() + "\n"+"Mapeo a muchos de solicitudes: " + "\n" + solicitudes.toString() + "\n" + "Hash map de clientes: " + "\n" + clientes.toString());
        }
    }
    public void mostrarSistema(){
        String cadena = "Diccionario AVL de ciudades: " + "\n" + ciudades.toString() + "\n" + "Grafo de rutas: " + "\n" + rutas.toString() + "\n"+"Mapeo a muchos de solicitudes: " + "\n" + solicitudes.toString() + "\n" + "Hash map de clientes: " + "\n" + clientes.toString();
        System.out.println(cadena);
        System.out.println("");
    }
    
    public void consultasViajes(){
        Scanner sc = new Scanner(System.in);
        
        int eleccion = -1;
        while(eleccion != 0){
            System.out.println("""
                               Elija una de las siguientes opciones:
                               1)Mostrar ciudades ingresando un prefijo.
                               2)Obtener el camino con menos ciudades entre medio.
                               3)Obtener el camino con menor cantidad de km.
                               4)Mostrar mostrar todos los pedidos entre dos ciudades y el espacio total necesario para realizarlos.
                               5)Verificar camino perfecto.
                               0)Volver al menu anterior.""");
            
            eleccion = sc.nextInt();
            while(!(eleccion > -1 && eleccion < 6)){
                System.out.println("Numero fuera de rango, ingrese una de las opciones numericas mostradas.");
                eleccion = sc.nextInt();
            }
            switch(eleccion){
                case 1: mostrarCiudadesConPrefijo();break;
                case 2: obtenerCaminoConMenosCiudades();break;
                case 3: obtenerCaminoConMenosPeso();break;
                case 4: mostrarPedidosYespacio();break;
                case 5: esCaminoPerfecto();break;
            }
        }
    }
    
    public void obtenerCaminoConMenosPeso(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la ciudad a");
        int ciudadA = sc.nextInt();
        while(!ciudades.existeClave(ciudadA)){
            System.out.println("ingrese un codigo correcto");
            ciudadA = sc.nextInt();
        }
        System.out.println("Ingrese la ciudad b");
        int ciudadB = sc.nextInt();
        while(!ciudades.existeClave(ciudadB)){
            System.out.println("Ingrese un codigo correcto");
            ciudadA = sc.nextInt();
        }
        Lista lista = rutas.caminoMasCortoPorPesos(ciudadA, ciudadB);
        
        System.out.println(lista.toString());
    }
    public void mostrarPedidosYespacio(){
        Scanner sc = new Scanner(System.in);
        int codigoA,codigoB,i = 1,metros = 0;
        System.out.println("Ingrese el codigo postal de la ciudad A:");
        codigoA = sc.nextInt();
        while(!ciudades.existeClave(codigoA)){
            System.out.println("Ingrese un codigo correcto: ");
            codigoA = sc.nextInt();
        }
        System.out.println("Ingrese el codigo postal de la ciudad B:");
        codigoB = sc.nextInt();
        while(!ciudades.existeClave(codigoB)){
            System.out.println("Ingrese un codigo correcto: ");
            codigoB = sc.nextInt();
        }
        String valor = "" + codigoA + codigoB;
        int clave = Integer.parseInt(valor);
        Lista solicitudesCiudades = solicitudes.obtenerValores(clave);
        Solicitud solicitud;
        while(i <= solicitudesCiudades.longitud()){
            solicitud = (Solicitud)solicitudesCiudades.recuperar(i);
            metros = metros + solicitud.getMetrosCubicos();
            i++;
        }
        System.out.println("Pedidos: " + solicitudesCiudades.toString() + " cantidad de metros cubicos necesarios: " + metros);
    }
    
    public void esCaminoPerfecto(){
        Scanner sc = new Scanner(System.in);
        Lista camino = new Lista();
        int codigo =  -2, metros;
        while(codigo != -1){
        System.out.println("Ingrese un codigo postal al camino. (-1 para continuar)");
        codigo = sc.nextInt();
            if (codigo != -1) {
                if (ciudades.existeClave(codigo)) {
                    camino.insertar(ciudades.obtenerDato(codigo), camino.longitud() + 1);
                } else {
                    System.out.println("Codigo incorrecto.");
                }
            }
        }
        System.out.println("Ingrese los metros cubicos maximos: ");
        metros = sc.nextInt();
        while(!(metros > 0)){
            System.out.println("Ingrese un numero positivo: ");
            metros = sc.nextInt();
        }
        System.out.println(esCaminoPerfectoAux(camino, metros));
    }
    public boolean esCaminoPerfectoAux(Lista camino, double metrosCubicos) {
        int i = 1, j, k = 1;
        double metrosCubicosNecesarios=0;
        boolean salida = caminoPosible(camino);
        int clave;
        Lista lasSolicitudes;
        Solicitud solicitud = null;
        if (salida) {
            while( i < camino.longitud() && salida){
                String junto = "" + ((Ciudad) camino.recuperar(i)).getCodigoPostal() + ((Ciudad) camino.recuperar(i + 1)).getCodigoPostal();
                clave = Integer.parseInt(junto);
                lasSolicitudes = solicitudes.obtenerValores(clave);
                j=i+1;
                
                if(lasSolicitudes != null){
                do {
                    solicitud = (Solicitud)lasSolicitudes.recuperar(k);
                    j++;
                } while (solicitud == null && j <= camino.longitud());
                if (solicitud != null) {
                    metrosCubicosNecesarios+=solicitud.getMetrosCubicos();
                } else {
                    salida = false;
                }
                
                }else{
                    salida = false;
                }
                i++;
            }
            if(!salida || metrosCubicosNecesarios>metrosCubicos){
                salida=false;
            }
        }
        return salida;
    }
    public boolean caminoPosible(Lista camino) {
        boolean salida = true;
        int i = 1;
        NodoVert recorrido;
        NodoAdy ady;
        while (i < camino.longitud() && salida) {
            Ciudad ciudad = (Ciudad)camino.recuperar(i), ciudad2 = (Ciudad)camino.recuperar(i + 1);
            recorrido = rutas.ubicarVertice(ciudad.getCodigoPostal());
            
            ady = recorrido.getPrimerAdy();
            while (ady != null && !ady.getVertice().getElem().equals(ciudad2.getCodigoPostal())) {
                ady = ady.getSigAdyacente();
            }
            if (ady == null) {
                salida = false;
            }
            i++;
        }
        return salida;
    }

    public void obtenerCaminoConMenosCiudades(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la ciudad a");
        int ciudadA = sc.nextInt();
        while(!ciudades.existeClave(ciudadA)){
            System.out.println("ingrese un codigo correcto");
            ciudadA = sc.nextInt();
        }
        System.out.println("Ingrese la ciudad b");{
        int ciudadB = sc.nextInt();
        while(!ciudades.existeClave(ciudadB)){
            System.out.println("Ingrese un codigo correcto");
            ciudadA = sc.nextInt();
        }
        Lista caminoMenor = rutas.caminoMasCorto(ciudadA,ciudadB);
        System.out.println(caminoMenor.toString());
        
    }
    }
    public void mostrarCiudadesConPrefijo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el prefijo: ");
        int prefijo = sc.nextInt(), valorMayor, valorMenor;
        while (!(prefijo > 0 && prefijo < 10000)) {
            System.out.println("Ingrese un prefijo valido: ");
            prefijo = sc.nextInt();
        }
        String menor = "" + prefijo, mayor ="" + prefijo ;
         System.out.println(menor.length());
        switch(menor.length()){
            case 1: 
                menor += ""+0+0+0;
                mayor += ""+9+9+9;
                ;break;
            case 2: 
                menor += ""+0+0;
                mayor += ""+9+9;
                ;break;
            case 3:
                menor +=""+ 0;
                mayor += ""+9;
        }
        valorMayor = Integer.parseInt(mayor);
        valorMenor = Integer.parseInt(menor);
        Lista lista = ciudades.listarRango(valorMenor, valorMayor);
        System.out.println(lista.toString());
        
    }
    public void operacionesRutas(){
        Scanner sc = new Scanner(System.in);
        
        int eleccion = -1;
        while(eleccion != 0){
            System.out.println("""
                               Elija una de las siguientes opciones:
                               1)Agregar ruta.
                               2)Eliminar ruta.
                               0)Volver al menu anterior.""");
            
            eleccion = sc.nextInt();
            while(!(eleccion > -1 && eleccion < 3)){
                System.out.println("Numero fuera de rango, ingrese una de las opciones numericas mostradas.");
                eleccion = sc.nextInt();
            }
            switch(eleccion){
                case 1: agregarRuta();break;
                case 2: eliminarRuta();break;
            }
        }
    }
    
    public void eliminarRuta(){
        Scanner sc = new Scanner(System.in);
        int ciudadO, ciudadD;
        double etiqueta;
        
        System.out.println("Ingrese el codigo postal de la ciudad origen: ");
        ciudadO = sc.nextInt();
        while(!(ciudades.existeClave(ciudadO))){
            System.out.println("Ingrese un codigo postal valido: ");
            ciudadO = sc.nextInt();
        }
        System.out.println("Ingrese el codigo postal de la ciudad destino: ");
        ciudadD = sc.nextInt();
        while(!ciudades.existeClave(ciudadD)){
            System.out.println("Ingrese un codigo postal valido: ");
            ciudadD = sc.nextInt();
        }
        System.out.println("Ingrese un peso para la ruta: ");
        etiqueta = sc.nextDouble();
        while(!(etiqueta > 0)){
            System.out.println("Ingrese un numero positivo");
            etiqueta = sc.nextDouble();
        }
        rutas.eliminarArco(ciudadO, ciudadD, etiqueta);
        int clave = generarClaveSolicitud(ciudadO, ciudadD);
        solicitudes.eliminar(clave);
        escribir.escribirTexto("Se eliminio una ruta de: " + ciudadO + " a: " + ciudadD + " con un peso de: " + etiqueta);
    }
    public void agregarRuta(){
        Scanner sc = new Scanner(System.in);
        int ciudadO, ciudadD;
        double etiqueta;
        
        System.out.println("Ingrese el codigo postal de la ciudad origen: ");
        ciudadO = sc.nextInt();
        while(!ciudades.existeClave(ciudadO)){
            System.out.println("Ingrese un codigo postal valido: ");
            ciudadO = sc.nextInt();
        }
        System.out.println("Ingrese el codigo postal de la ciudad destino: ");
        ciudadD = sc.nextInt();
        while(!ciudades.existeClave(ciudadD)){
            System.out.println("Ingrese un codigo postal valido: ");
            ciudadD = sc.nextInt();
        }
        System.out.println("Ingrese un peso para la ruta: ");
        etiqueta = sc.nextInt();
        while(!(etiqueta > 0)){
            System.out.println("Ingrese un numero positivo");
            etiqueta = sc.nextInt();
        }
        rutas.insertarVertice(ciudadO);
        rutas.insertarVertice(ciudadD);
        rutas.insertarArco(ciudadO, ciudadD, etiqueta);
        escribir.escribirTexto("Se inserto una ruta de: " + ciudadO + " a: " + ciudadD + " con un peso de:" + etiqueta);
        
        
    }
    
    public void operacionesCiudades(){
        //ABM de ciudades
        Scanner sc = new Scanner(System.in);
       
        int eleccion = -1;
        while(eleccion != 0){
        System.out.println("""
                           Elija una de las siguientes opciones:
                           1)Agregar ciudad.
                           2)Eliminar ciudad.
                           0)Volver al menu anterior.
                               """);
        
        eleccion = sc.nextInt();
        if(eleccion >-1 && eleccion < 3){
            switch(eleccion){
                case 1: cargarCiudad();break;
                case 2: eliminarCiudad();break;
            }
        }else{
            System.out.println("Numero fuera de rango, ingrese una de las opciones numericas mostradas.");
        }
        }
    }
    
    public void cargarCiudad(){
        Scanner sc = new Scanner(System.in);
        int codigoPostal;
        String nombre, nombreProvincia;
        
        System.out.println("Ingrese el codigo postal: ");
        codigoPostal = sc.nextInt();

        while(!(codigoPostal > -1 && codigoPostal < 10000)){
            System.out.println("Ingrese un codigo postal valido: ");
            codigoPostal = sc.nextInt();
            }
        System.out.println("Ingrese el nombre de la ciudad: ");
        nombre = sc.next();
        nombre = nombre.toUpperCase();
        System.out.println("Ingrese el nombre de la provincia: ");
        nombreProvincia = sc.next();
        nombreProvincia = nombreProvincia.toUpperCase();
        
        Ciudad ciudad = new Ciudad(codigoPostal, nombre, nombreProvincia);
        if(ciudades.existeClave(codigoPostal) == false){
            ciudades.insertar(codigoPostal, ciudad);
            rutas.insertarVertice(codigoPostal);
            escribir.escribirTexto("Se cargo al sistema la ciudad: " + codigoPostal);
        }else{
            escribir.escribirTexto("No se pudo cargar la ciudad: " + codigoPostal);
        }
        
    }
   
    
    public void eliminarCiudad(){
        Scanner sc = new Scanner(System.in);
        int codigoPostal = -1, i = 1;
        
        System.out.println("Ingrese el codigo postal de la ciudad que desea eliminar: ");
        codigoPostal = sc.nextInt();
            while(!(codigoPostal > -1 && codigoPostal < 10000)){
                System.out.println("Ingrese un codigo postal valido: ");
                codigoPostal = sc.nextInt();
            }
        ciudades.eliminar(codigoPostal);
        rutas.eliminarVertice(codigoPostal);
        Lista valores = solicitudes.obtenerValores(codigoPostal);
        while(valores != null && !valores.esVacia()){
            solicitudes.desasociar(codigoPostal,i);
            i++;
        }
        System.out.println("La ciudad: " + codigoPostal + " se elimino.");
        escribir.escribirTexto("La ciudad: " + codigoPostal + " se elimino.");
    }
    
    public void operacionesClientes(){
        Scanner sc = new Scanner(System.in);
        int eleccion = -1;
        
        while(eleccion != 0){
        System.out.println("""
                           1) Agregar cliente.
                           2) Eliminar cliente.
                           3) Modificar cliente.
                           0) Volver al menu anterior.
                           """);
        
        eleccion = sc.nextInt();
        if(eleccion > -1 && eleccion < 4){
            switch(eleccion){
                case 1: agregarCliente();break;
                case 2: eliminarCliente();break;
                case 3: modificarCliente();break;
            }
        }else{
            System.out.println("Numero fuera de rango, ingrese una de las opciones numericas mostradas.");
        }
      }
    }
            
    public void agregarCliente(){
        
        Cliente cliente = generarCliente();
        String clave = cliente.getTipoDocumento() + cliente.getTipoDocumento();
        if(clientes.containsKey(clave) == false){
            clientes.put(clave, cliente);
            escribir.escribirTexto("Se cargo al sistema el cliente: " + cliente.getTipoDocumento() + cliente.getNumeroDoc());
        }else{
            escribir.escribirTexto("No se pudo cargar el cliente: " + cliente.getTipoDocumento() + cliente.getNumeroDoc());
        }
    }
    
    private Cliente generarCliente(){
        Scanner sc = new Scanner(System.in);
        String tipoDoc = "", nombre, apellido, numeroTelefono;
        int numeroDoc;
        
        System.out.println("Ingrese un numero de documento: ");
        numeroDoc = sc.nextInt();
        while(!(numeroDoc > 1 && numeroDoc < 100000000)){
                System.out.println("Ingrese un numero de dni valido: ");
                numeroDoc = sc.nextInt();
        }
        System.out.println("Ingrese el tipo de documento: (DNI o PAS). ");
        tipoDoc = sc.next();
        while(!(tipoDoc.compareTo("DNI") == 0 || tipoDoc.compareTo("PAS") == 0)){
            System.out.println("Ingrese un tipo de documento valido: ");
            tipoDoc = sc.next();
        }
        tipoDoc = tipoDoc.toUpperCase();
        System.out.println("Ingrese el nombre: ");
        nombre = sc.next();
        nombre = nombre.toUpperCase();
        System.out.println("Ingrese el apellido: ");
        apellido = sc.next();
        apellido = apellido.toUpperCase();
        System.out.println("Ingrese el numero de telefono: ");
        numeroTelefono = sc.next();
        numeroTelefono = numeroTelefono.toUpperCase();
        
        Cliente cliente = new Cliente(tipoDoc,numeroDoc,nombre,apellido,numeroTelefono);
        return cliente;
    }
    
    public void eliminarCliente(){
        Scanner sc = new Scanner(System.in);
        int numeroDoc;
        String tipoDoc = "";
        System.out.println("Ingrese el tipo de documento: (DNI o PAS)");
        tipoDoc = sc.next();
        while(!(tipoDoc.compareTo("DNI") == 0 || tipoDoc.compareTo("PAS") == 0)){
            System.out.println("Ingrese un tipo de documento valido: ");
            tipoDoc = sc.next();
        }
        tipoDoc = tipoDoc.toUpperCase();
        System.out.println("Ingrese un numero de documento: ");
        numeroDoc = sc.nextInt();
        while(!(numeroDoc > 1 && numeroDoc < 100000000)){
                System.out.println("Ingrese un numero de dni valido: ");
                numeroDoc = sc.nextInt();
        }
        String clave = tipoDoc + numeroDoc;
        if(clientes.containsKey(clave) == true){
            clientes.remove(clave);
            escribir.escribirTexto("Se elimino el cliente" + tipoDoc + numeroDoc);
        }else{
            escribir.escribirTexto("No se pudo eliminar el cliente: " + tipoDoc + numeroDoc);
        }
    }
    
    public void modificarCliente(){
         Scanner sc = new Scanner(System.in);
        int eleccion = -1, numeroDoc;
        String tipoDoc = "";
        
        System.out.println("Ingrese el tipo de documento: ");
        tipoDoc = sc.next();
        while(!(tipoDoc.compareTo("DNI") == 0 || tipoDoc.compareTo("PAS") == 0)){
            System.out.println("Ingrese un tipo de documento valido: ");
            tipoDoc = sc.next();
        }
        tipoDoc = tipoDoc.toUpperCase();
        System.out.println("Ingrese un numero de documento: ");
        numeroDoc = sc.nextInt();
        while(!(numeroDoc > 1 && numeroDoc < 100000000)){
                System.out.println("Ingrese un numero de dni valido: ");
                numeroDoc = sc.nextInt();
        }
        String clave = tipoDoc + numeroDoc;
        if (clientes.containsKey(clave)) {
            Cliente cliente = clientes.get(clave);
            while (eleccion != 0) {
                System.out.println("""
                           1) Modificar nombre.
                           2) Modificar apellido.
                           3) Modificar numero de telefono.
                           0) Volver al menu anterior.""");
                eleccion = sc.nextInt();
                while (!(eleccion > -1 && eleccion < 4)) {
                    System.out.println("Numero fuera de rango, ingrese una de las opciones numericas mostradas.");
                    eleccion = sc.nextInt();
                }
                switch (eleccion) {
                    case 1:
                        System.out.println("Ingrese el nuevo nombre: ");
                        cliente.setNombre(sc.next());break;
                    case 2:
                        System.out.println("Ingrese el nuevo apellido: ");
                        cliente.setApellido(sc.next());break;
                    case 3:
                        System.out.println("Ingrese el nuevo numero de telefono: ");
                        cliente.setNumeroTelefono(sc.next());break;
                }
            }
        }
    }
    
    public void operacionesPedidos(){
        Scanner sc = new Scanner(System.in);
        int eleccion = -1;
        
        while(eleccion != 0){
            System.out.println("""
                               Ingrese el numero correspondiente a la opcion requerida:
                               1) Agregar solicitud.
                               2) Eliminar solicitud.
                               3) Modificar solicitud.
                               0) Volver al menu anterior.""");
            eleccion = sc.nextInt();
            while (!(eleccion > -1 && eleccion < 4)) {
                    System.out.println("Numero fuera de rango, ingrese una de las opciones numericas mostradas.");
                    eleccion = sc.nextInt();
                }
            switch(eleccion){
                case 1: agregarSolicitud();break;
                case 2: eliminarSolicitud();break;
                case 3: modificarSolicitud();break;
            }
        }
    }
    
    public void agregarSolicitud(){
        Scanner sc = new Scanner(System.in);
        int clave;
        Solicitud solicitud = cargarSolicitud();
        clave = generarClaveSolicitud(solicitud.getCiudadOrigen(), solicitud.getCiudadDestino());
        solicitudes.asociar(clave, solicitud);
        escribir.escribirTexto("Se cargo la solicitud: " + solicitud.getCiudadOrigen() + solicitud.getCiudadDestino());
    }
    
    private Solicitud cargarSolicitud(){
        Scanner sc = new Scanner(System.in);
        int ciudadOrigen,ciudadDestino,metrosCubicos,numeroDoc, cantBultos;
        String fecha,tipoDoc,calleRetiro,calleEntrega;
        boolean estaPago;
        
        System.out.println("Ingrese el codigo postal de la ciudad origen: ");
        ciudadOrigen = sc.nextInt();
        while(!ciudades.existeClave(ciudadOrigen)){
            System.out.println("Ingrese un codigo que exista en el sistema: ");
            ciudadOrigen = sc.nextInt();
        }
        System.out.println("Ingrese el codigo postal de la ciudad destino: ");
        ciudadDestino = sc.nextInt();
        while(!ciudades.existeClave(ciudadDestino)){
            System.out.println("Ingrese un codigo que exista en el sistema: ");
            ciudadDestino = sc.nextInt();
        }
        System.out.println("Ingrese la cantidad de metros cubicos necesarios: ");
        metrosCubicos = sc.nextInt();
        while(!(metrosCubicos > 0)){
            System.out.println("Ingrese un valor positivo: ");
            metrosCubicos = sc.nextInt();
        }
        System.out.println("Ingrese una cantidad de bultos");
        cantBultos = sc.nextInt();
        while(!(cantBultos > 0)){
            System.out.println("Ingrese un valor positivo: ");
            cantBultos = sc.nextInt();
        }
        System.out.println("Ingrese una fecha: (dd/mm/aa)");
        fecha = sc.next();
        while(!verificarFecha(fecha)){
            System.out.println("Ingrese una fecha valida: ");
            fecha = sc.next();
        }
        System.out.println("Ingrese el tipo de documento: ");
        tipoDoc = sc.next();
        while(!(tipoDoc.compareTo("DNI") == 0 || tipoDoc.compareTo("PAS") == 0)){
            System.out.println("Ingrese un tipo de documento valido: ");
            tipoDoc = sc.next();
        }
        tipoDoc = tipoDoc.toUpperCase();
        System.out.println("Ingrese un numero de documento: ");
        numeroDoc = sc.nextInt();
        while(!(numeroDoc > 1 && numeroDoc < 100000000)){
                System.out.println("Ingrese un numero de dni valido: ");
                numeroDoc = sc.nextInt();
        }
        System.out.println("Ingrese la calle de retiro: ");
        calleRetiro = sc.next().toUpperCase();
        System.out.println("Ingrese la calle de entrega: ");
        calleEntrega = sc.next().toUpperCase();
        System.out.println("Esta pagado? (true o false)");
        estaPago = sc.nextBoolean();
        if(rutas.existeCamino(ciudadOrigen, ciudadDestino));
        Solicitud solicitud = new Solicitud(ciudadOrigen,ciudadDestino,fecha,tipoDoc,numeroDoc,metrosCubicos,cantBultos,calleRetiro,calleEntrega,estaPago);
        return solicitud;
    }
    
    public void eliminarSolicitud(){
        Solicitud solicitud = cargarSolicitud();
        int clave = solicitud.getCiudadOrigen() + solicitud.getCiudadDestino();
        solicitudes.desasociar(clave, solicitud);
        escribir.escribirTexto("Se elimino la solicitud: " + solicitud.getCiudadOrigen() + solicitud.getCiudadDestino());
    }
    
    public void modificarSolicitud(){
        //menu para modificar los parametros de una solicitud
        Scanner sc = new Scanner(System.in);
        Solicitud solicitud = cargarSolicitud();
        int clave = solicitud.getCiudadOrigen() + solicitud.getCiudadDestino();
        Lista lista = solicitudes.obtenerValores(clave);
        int pos = lista.localizar(solicitud);
        int eleccion = -1;
        if (pos != -1) {
            solicitud =(Solicitud)lista.recuperar(pos);
            while (eleccion != 0) {
                System.out.println("""
                           Ingrese el numero correspondiente a la opcion requerida: 
                           1) Modificar fecha.
                           2) Modificar estado de pago.
                           3) Modificar cantidad de bultos.
                           4) Modificar metros cubicos.
                           0) Volver al menu anterior.""");
                eleccion = sc.nextInt();
                if (eleccion > -1 && eleccion < 5) {
                    switch (eleccion) {
                        case 1: 
                            System.out.println("Ingrese la nueva fecha:(dd/mm/aa) ");
                            String fecha = sc.next();
                            if(verificarFecha(fecha)){
                            solicitud.setFecha(fecha);
                            }else{
                                System.out.println("La fecha es incorrecta");
                            };break;
                        case 2:
                            System.out.println("Ingrese el nuevo estado de pago: (true o false)");
                            boolean estado = sc.nextBoolean();
                            solicitud.setEstaPago(estado);
                        case 3:
                            System.out.println("Ingrese la nueva cantidad de bultos: ");
                            int cantBultos = sc.nextInt();
                            if(cantBultos > 0){
                                solicitud.setCantBultos(cantBultos);
                            }else{
                                System.out.println("El numero es negativo y no se pudo cargar.");
                            }
                        case 4:
                            System.out.println("Ingrese los nuevos metros cubicos: ");
                            int metros = sc.nextInt();
                            if(metros > 0){
                                solicitud.setMetrosCubicos(metros);
                            }else{
                                System.out.println("El numero es negativo y no se pudo cargar.");
                            }
                    }
                }
            }
        }
    }
    
    public void consultasClientes(){
        Scanner sc = new Scanner(System.in);
        int eleccion = -1;
        while(eleccion != 0){
        System.out.println("""
                           Ingrese el numero correspondiente a la opcion requerida: 
                           1) Mostrar informacion de un cliente.
                           0) Volver al menu anterior.""");
        eleccion = sc.nextInt();
        if(eleccion == 1){
            mostrarInfoCliente();
        }
        }
    }
    
    public void mostrarInfoCliente(){
        Scanner sc = new Scanner(System.in);
        int numeroDoc;
        String tipoDoc = "";
        
        System.out.println("Ingrese el tipo de documento: ");
        tipoDoc = sc.next();
        while(!(tipoDoc.compareTo("DNI") == 0 || tipoDoc.compareTo("PAS") == 0)){
            System.out.println("Ingrese un tipo de documento valido: ");
            tipoDoc = sc.next();
        }
        tipoDoc = tipoDoc.toUpperCase();
        System.out.println("Ingrese un numero de documento: ");
        numeroDoc = sc.nextInt();
        while(!(numeroDoc > 1 && numeroDoc < 100000000)){
                System.out.println("Ingrese un numero de dni valido: ");
                numeroDoc = sc.nextInt();
        }
        String clave = tipoDoc + numeroDoc;
        Cliente cliente = clientes.get(clave);
        System.out.println(cliente.toString());
    }
    
    public void colsultasCiudades(){
        Scanner sc = new Scanner(System.in);
        int eleccion = -1;
        while(eleccion != 0){
        System.out.println("""
                           Ingrese el numero correspondiente a la opcion requerida: 
                           1) Mostrar informacion de una ciudad.
                           0) Volver al menu anterior.""");
        eleccion = sc.nextInt();
        if(eleccion == 1){
            mostrarInfoCiudad();
        }
        }
    }
    
    public void mostrarInfoCiudad(){
        Scanner sc = new Scanner(System.in);
        int codigo;
        System.out.println("Ingrese el codigo postal de la ciudad: ");
        codigo = sc.nextInt();
        while(!ciudades.existeClave(codigo)){
            System.out.println("Codigo incorrecto, intente otra vez: ");
            codigo = sc.nextInt();
        }
        Ciudad ciudad = (Ciudad)ciudades.obtenerDato(codigo);
        System.out.println(ciudad.toString());
    }  
    
}
