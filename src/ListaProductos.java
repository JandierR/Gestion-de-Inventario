import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ListaProductos {
    //Atributos
    private Producto primerProducto;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public Producto getPrimerProducto() {
        return primerProducto;
    }

    public void setPrimerProducto(Producto primerProducto) {
        this.primerProducto = primerProducto;
    }

    //Revisa si el nuevoProducto que se paso, esta vacio, o sea, null.
    private boolean estaVacio() {
        return primerProducto == null;
    }

    public void insertarProductoInicio(String nombre, double precio,
                                       String categoria, String fechaVencimiento,
                                       int cantidad, ArrayList<String> listaImagenes,
                                       int id) {

        Producto nuevoProducto = new Producto(nombre, precio, categoria,
                fechaVencimiento, cantidad, listaImagenes, id);
        nuevoProducto.setSiguienteProducto(primerProducto);
        setPrimerProducto(nuevoProducto);

    }

    public void insertarNodoFinal(String nombre, double precio,
                                  String categoria, String fechaVencimiento,
                                  int cantidad, ArrayList<String> listaImagenes,
                                  int id) {

        Producto productoInsertar = new Producto(nombre, precio, categoria,
                fechaVencimiento, cantidad, listaImagenes, id);

        if (estaVacio()) {
            setPrimerProducto(productoInsertar);
            return;
        }
        Producto temp = primerProducto;
        while (temp.getSiguienteProducto() != null) {
            temp = temp.getSiguienteProducto();
        }
        temp.setSiguienteProducto(productoInsertar);
    }

    public Producto eliminarNodo(int id) {
        if (estaVacio()) {
            System.out.println("La lista esta vacía.");
            return null;
        }
        Producto productoActual = primerProducto;
        Producto anteriorTemp = productoActual;
        while (productoActual != null && productoActual.getId() != id) {
            anteriorTemp = productoActual;
            productoActual = productoActual.getSiguienteProducto();
        }

        if (productoActual != null) {
            System.out.println("Producto encontrado.");
            anteriorTemp.setSiguienteProducto(productoActual.getSiguienteProducto());
        } else {
            System.out.println("Producto no encontrado");
        }
        return productoActual;
    }

    public Producto buscarProducto(int id) {
        if (estaVacio()) {
            System.out.println("La lista esta vacía.");
            return null;
        }

        Producto productoActual = primerProducto;
        while (productoActual != null && productoActual.getId() != id) {
            productoActual = productoActual.getSiguienteProducto();
        }
        if (productoActual == null) {
            System.out.println("El producto no se encontró en la lista");
            return null;
        }
        System.out.println("El producto se encontró en la lista");
        return productoActual;
    }

    //Este metodo de mostrarLista tengo que verificar si la pongo aqui o en Main.
    //No se si deben haber dos metodos similares
    public void mostrarLista() {
        if (primerProducto == null) {
            System.out.println("Lista vacía");
            return;
        }

        Producto productoActual = primerProducto;

        //Recorre mientras producto no sea null, o sea, hasta que llegue al final que es null
        while (productoActual != null) {
            System.out.println(productoActual);

            //El siguiente producto
            productoActual = productoActual.getSiguienteProducto();
        }
    }

    public Producto modificarProducto(int id) throws IOException {
        if (primerProducto == null) {
            System.out.println("Lista vacía");
            return null;
        }

        //buscarProducto busca y retorna el producto con el Id pasado como argumento, por lo que,
        //Si se encuentra, se retorna a productoModificado.
        Producto productoModificado = buscarProducto(id);


        if (productoModificado != null) {

            System.out.println("Digite los siguientes nuevas modificaciones:");
            System.out.print("Digite nuevo nombre: ");
            String nuevoNombre = br.readLine();
            productoModificado.setNombre(nuevoNombre);

            System.out.print("Digite nuevo precio: ");
            int nuevoPrecio = Integer.parseInt(br.readLine());
            productoModificado.setPrecio(nuevoPrecio);

            System.out.print("Digite nueva categoria: ");
            String nuevaCategoria = br.readLine();
            productoModificado.setCategoria(nuevaCategoria);

            System.out.print("Digite nueva fecha de vencimiento: ");
            String nuevaFechaVencimiento = br.readLine();
            productoModificado.setFechaVencimiento(nuevaFechaVencimiento);

        }
        return productoModificado;
    }
}
