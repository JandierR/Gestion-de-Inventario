import java.util.ArrayList;

public class ListaProductos {
    //Atributos
    private Producto primerProducto;

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
        Producto temp = primerProducto;
        Producto anteriorTemp = temp;
        while (temp != null && temp.getId() != id) {
            anteriorTemp = temp;
            temp = temp.getSiguienteProducto();
        }

        if (temp == null) {
            System.out.println("Producto no encontrado");
        } else {
            System.out.println("Producto encontrado.");
            anteriorTemp.setSiguienteProducto(temp.getSiguienteProducto());

        }
        return temp;
    }

    public Producto buscarNodo(int id) {
        if (estaVacio()) {
            System.out.println("La lista esta vacía.");
            return null;
        }

        Producto temp = primerProducto;
        while (temp != null && temp.getId() != id) {
            temp = temp.getSiguienteProducto();
        }
        if (temp == null) {
            System.out.println("El producto no se encontró en la lista");
            return null;
        }
        System.out.println("El producto se encontró en la lista");
        return temp;
    }

    //Este metodo de mostrarLista tengo que verificar si la pongo aqui o en Main.
    //No se si deben haber dos metodos similares
    public void mostrarLista() {

    }
}
