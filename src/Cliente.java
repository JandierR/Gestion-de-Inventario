public class Cliente {
    private String nombre;
    private int prioridad;
    private ListaProductos carrito;



    public Cliente(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        carrito = new ListaProductos();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public ListaProductos getCarrito() {
        return carrito;
    }

    public void setCarrito(ListaProductos carrito) {
        this.carrito = carrito;
    }
}
