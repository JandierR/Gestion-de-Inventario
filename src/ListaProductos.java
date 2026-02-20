import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListaProductos {
    //Atributos
    private Producto primerProducto;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public ListaProductos() {
        this.primerProducto = null;
    }

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

    public void insertarProductoInicio() throws IOException {

        System.out.print("Nombre del producto: ");
        String nombre = br.readLine();

        System.out.print("Precio del producto: ");
        double precio = Double.parseDouble(br.readLine());

        System.out.print("Categoria del producto: ");
        String categoria = br.readLine();

        System.out.print("Fecha de vencimiento del producto: ");
        String fechaVencimiento = br.readLine();

        System.out.print("Cantidad del producto: ");
        int cantidad = Integer.parseInt(br.readLine());




        Producto nuevoProducto = new Producto(nombre, precio, categoria,
                fechaVencimiento, cantidad);
        nuevoProducto.setSiguienteProducto(primerProducto);
        setPrimerProducto(nuevoProducto);

    }

//    public void insertarNodoFinal(String nombre, double precio,
//                                  String categoria, String fechaVencimiento,
//                                  int cantidad, ArrayList<String> listaImagenes,
//                                  int id) {
//
//        Producto productoInsertar = new Producto(nombre, precio, categoria,
//                fechaVencimiento, cantidad, listaImagenes);
//
//        if (estaVacio()) {
//            setPrimerProducto(productoInsertar);
//            return;
//        }
//        Producto temp = primerProducto;
//        while (temp.getSiguienteProducto() != null) {
//            temp = temp.getSiguienteProducto();
//        }
//        temp.setSiguienteProducto(productoInsertar);
//    }

    public Producto eliminarNodo() throws IOException {
        //Checar si la lista esta vacia
        if (estaVacio()) {
            System.out.println("La lista esta vacía.");
            return null;
        }

        System.out.print("Digite el ID del producto a eliminar: ");
        int id = Integer.parseInt(br.readLine());

        Producto productoActual = primerProducto;
        Producto productoAnterior = null;

        //checar que producto actual no sea null y el id del producto no sea igual al ingresado por el usuario
        while (productoActual != null && productoActual.getId() != id) {
            productoAnterior = productoActual;
            productoActual = productoActual.getSiguienteProducto();
        }

        //Si llego hasta el final de la lista, entonces no se encontro
        if (productoActual == null) {
            System.out.println("Producto no encontrado");
            return null;

        }

        //Checa si el anterior producto es nulo
        if (productoAnterior == null) {
            primerProducto = productoActual.getSiguienteProducto();
        } else {
            productoAnterior.setSiguienteProducto(productoActual.getSiguienteProducto());
        }
            System.out.println("Producto eliminado.");
        return productoActual;
    }

    public Producto buscarProducto(int id) throws IOException {
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

    public Producto modificarProducto() throws IOException {
        if (primerProducto == null) {
            System.out.println("Lista vacía");
            return null;
        }

        System.out.print("Digite el ID del producto a modificar: ");
        int id = Integer.parseInt(br.readLine());

        //buscarProducto busca y retorna el producto con el Id pasado como argumento, por lo que,
        //Si se encuentra, se retorna .
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
