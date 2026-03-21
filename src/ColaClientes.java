import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ColaClientes {
    private static NodoCola frente;
    private BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));

    public ColaClientes() {
        frente = null;
    }

    public static boolean estaVacio() {
        return frente == null;
    }


    public void insertarCliente() throws IOException {


        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = br.readLine();

        System.out.print("Ingrese la prioridad del cliente: ");
        int prioridad= Integer.parseInt(br.readLine());


        Cliente clienteNuevo = new Cliente(nombre, prioridad);

        int opcion;

        System.out.println();
        System.out.println("Comprando productos...");
        do {
            System.out.print("Ingresar #ID del producto: ");
            int id = Integer.parseInt(br.readLine());

            Producto productoInventario = Tienda.arbolProductos.buscarProducto(id);

            if (productoInventario != null) {
                System.out.print("Ingrese cantidad que desea comprar: ");
                int cantidad = Integer.parseInt(br.readLine());


                Producto productoCarrito = new Producto(
                        productoInventario.getNombre(),
                        productoInventario.getPrecio(),
                        productoInventario.getCategoria(),
                        productoInventario.getFechaVencimiento(),
                        cantidad,
                        productoInventario.getId()
                );
                clienteNuevo.getCarrito().insertarProducto(productoCarrito);

                System.out.println("El producto se ha agregado al carrito.");
            } else {
                //Aqui tengo que retornar porque si no, aunque no exista el producto,
                // la instancia del cliente creado se guarda si no retorno.
                System.out.println("El producto no existe!");
                return;
            }

            System.out.println();
            System.out.println("""
                    ¿Agregar otro producto?
                    -1. Si
                    -2. No""");
            opcion = Integer.parseInt(br.readLine());

        } while (opcion != 2);

        NodoCola nuevoCliente = new NodoCola(clienteNuevo);

        if (estaVacio()) {
            frente = nuevoCliente;
            imprimirDatosCliente(clienteNuevo);

            return;
        }

        if (clienteNuevo.getPrioridad() < frente.cliente.getPrioridad()) {
            nuevoCliente.siguiente = frente;
            frente = nuevoCliente;
            imprimirDatosCliente(clienteNuevo);
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
        imprimirDatosCliente(clienteNuevo);
    }

    private void imprimirDatosCliente(Cliente clienteNuevo) {
        System.out.println("Cliente '" + clienteNuevo.getNombre() +
                " con prioridad = " + clienteNuevo.getPrioridad() + " fue agregado exitosamente!");
        Producto productoCliente = clienteNuevo.getCarrito().getPrimerProducto();
        System.out.println("Productos comprados -->");
        while (productoCliente != null) {
            System.out.println(productoCliente);
            productoCliente = productoCliente.getSiguienteProducto();
        }
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
