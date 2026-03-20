import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ColaClientes {
    private static NodoCola frente;

    public ColaClientes() {
        frente = null;
    }

    public static boolean estaVacio() {
        return frente == null;
    }


    public void insertarCliente() throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = br.readLine();

        System.out.print("Ingrese la prioridad del cliente: ");
        int prioridad= Integer.parseInt(br.readLine());


        Cliente clienteNuevo = new Cliente(nombre, prioridad);

        /*El siguiente código lo cree para no tener que preguntar al usuario los productos...
         que desea y asi enforcarme en los requerimientos principales de la consigna */

        //A continuación creo productos random
        Producto producto1 = new Producto("Leche", 5, "Lacteo", "23/05/3423", 10);
        Producto producto2 = new Producto("Bistec de res", 9, "Carne", "06/12/3423", 3);
        Producto producto3 = new Producto("Arroz", 3, "Granel", "09/05/3423", 7);

        //Crep una instancia de lista productos que me servirá de puente para pasar los productos hacia el carrito
        ListaProductos listaProductos = new ListaProductos();

        //Cree un producto unico para resolver este problema, insertarProducto(Producto producto)
        listaProductos.insertarProducto(producto1);
        listaProductos.insertarProducto(producto2);
        listaProductos.insertarProducto(producto3);

        //Después de crear y agregar los productos a una ListaProductos, puedo finalmente
        //ingresar esta lista al carrito del cliente para tener productos default.
        clienteNuevo.setCarrito(listaProductos);

        NodoCola nuevoCliente = new NodoCola(clienteNuevo);

        if (estaVacio()) {
            frente = nuevoCliente;
            System.out.println("Cliente '" + clienteNuevo.getNombre() +
                    " con prioridad = " + clienteNuevo.getPrioridad() + " fue agregado exitosamente!");
            return;
        }

        if (clienteNuevo.getPrioridad() < frente.cliente.getPrioridad()) {
            nuevoCliente.siguiente = frente;
            frente = nuevoCliente;
            System.out.println("Cliente '" + clienteNuevo.getNombre() +
                    " con prioridad = " + clienteNuevo.getPrioridad() + " fue agregado exitosamente!");
            return;
        }

        NodoCola nodoActual = frente;

        while (nodoActual.siguiente != null
                && nodoActual.siguiente.cliente.getPrioridad()
                <= clienteNuevo.getPrioridad()) {
            nodoActual = nodoActual.siguiente;
        }
        nuevoCliente.siguiente = nodoActual.siguiente;
        nodoActual.siguiente = nuevoCliente;
        System.out.println("Cliente '" + clienteNuevo.getNombre() +
                " con prioridad = " + clienteNuevo.getPrioridad() + " fue agregado exitosamente!");
    }

    public static void atenderCliente() {
        if (estaVacio()) {
            System.out.println("La cola clientes esta vacia!");
            return;
        }
        Cliente cliente = frente.cliente;
        frente = frente.siguiente;
        System.out.println("Se atendió al cliente " + cliente.getNombre() + " con prioridad = " + cliente.getPrioridad());

        ListaProductos.mostrarFactura(cliente);
    }
}
