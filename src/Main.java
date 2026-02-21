import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ListaProductos listaProductos = new ListaProductos();

    public static void main(String[] args) throws IOException {
        int opcion;

        do {
            imprimirMenu();
            opcion = leerOpcion();
            procesarOpcion(opcion);

        } while (opcion != 0);
    }

    public static void imprimirMenu() {

        System.out.println("""
                      --Menu--
                1- Agregar Producto
                2- Eliminar Producto
                3- Mostrar Lista
                4- Modificar Producto
                5- Agregar imagen a producto
                0- Salir""");


    }

    public static int leerOpcion() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    public static void procesarOpcion(int opcion) throws IOException {
        switch (opcion) {
            case 1 -> listaProductos.insertarProductoInicio();
            case 2 -> listaProductos.eliminarNodo();
            case 3 -> listaProductos.mostrarLista();
            case 4 -> listaProductos.modificarProducto();
            case 5 -> listaProductos.agregarImagenProducto();
            case 0 -> System.out.println("Saliendo del sistema...");
            default -> System.out.println("Valor invalido");
        }
    }
}
