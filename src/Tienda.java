import java.io.IOException;

public class Tienda {
    private ArbolProductos arbolProductos;
    private ColaClientes colaClientes;

    public Tienda() {
        arbolProductos = new ArbolProductos();
        colaClientes = new ColaClientes();
    }

    public void agregarCliente() throws IOException {
        colaClientes.insertarCliente();
    }

    public void atenderCliente() {
        colaClientes.atenderCliente();
    }

    public void agregarProducto() throws IOException {
        arbolProductos.insertarProducto();
    }

}
