import java.util.ArrayList;

public class Producto {
    private String nombre;
    private double precio;
    private String categoria;
    private String fechaVencimiento;
    private int cantidad;
    private ArrayList<String> listaImagenes;
    private Producto siguienteProducto;
    private static int contador = 1;
    private int id;


    public Producto(String nombre, double precio, String categoria,
                    String fechaVencimiento, int cantidad,
                    ArrayList<String> listaImagenes) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        this.listaImagenes = listaImagenes;
        this.siguienteProducto = null;
        this.id = contador++;
    }

    public Producto(String nombre, double precio, String categoria,
                    String fechaVencimiento, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        this.siguienteProducto = null;
        this.listaImagenes = new ArrayList<>();
        this.id = contador++;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ArrayList<String> getListaImagenes() {
        return listaImagenes;
    }

    public void setListaImagenes(ArrayList<String> listaImagenes) {
        this.listaImagenes = listaImagenes;
    }

    public void agregarImagen(String path) {
        listaImagenes.add(path);
    }

    public Producto getSiguienteProducto() {
        return siguienteProducto;
    }

    public void setSiguienteProducto(Producto siguienteProducto) {
        this.siguienteProducto = siguienteProducto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "-Producto- -->" +
                "[Nombre = '" + nombre + '\'' +
                "]--> [Precio = '" + precio + '\'' +
                "]--> [cantidad = '" + cantidad + '\'' +
                "]--> [categoria ='" + categoria + '\'' +
                "]--> [fecha de vencimiento = '" + fechaVencimiento + '\'' +
                "]--> [imágenes = '" + getListaImagenes() + '\'' +
                "]--> [ID = '#" + id + '\'' +
                ']';
    }
}
