import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tienda {
    static ArbolProductos arbolProductos;
    private ColaClientes colaClientes;
    private Grafo mapa;
    private String ubicacion;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public Tienda(String ubicacion) {
        arbolProductos = new ArbolProductos();
        colaClientes = new ColaClientes();
        mapa = new Grafo();
        this.ubicacion = ubicacion;

        mapa.agregarArista(ubicacion, "San José", 11);
        mapa.agregarArista("San José", "Heredia", 10);
        mapa.agregarArista("Heredia", "Alajuela", 23);
        mapa.agregarArista("San José", "Cartago", 39);
    }

    public void agregarCliente() throws IOException {
        colaClientes.insertarCliente();
    }

//    public void atenderCliente() {
//        colaClientes.atenderCliente();
//    }

    public void agregarProducto() throws IOException {
        arbolProductos.insertarProducto();
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Grafo getMapa() {
        return mapa;
    }

    public void setMapa(Grafo mapa) {
        this.mapa = mapa;
    }

    public void agregarVertice() throws IOException {


        System.out.print("Ingrese nombre de la ubicación: ");
        String ubicacion = br.readLine();

        mapa.agregarVertice(ubicacion);

        System.out.println("Ubicación agregada exitosamente");
    }

    public void agregarArista() throws IOException {

        System.out.print("Ingrese ubicación origen: ");
        String origen = br.readLine();

        System.out.print("Ingrese ubicación destino: ");
        String destino = br.readLine();

        System.out.print("Ingrese distancia: ");
        int distancia = Integer.parseInt(br.readLine());

        mapa.agregarArista(origen, destino, distancia);

        System.out.println("Arista agregada exitosamente");
    }
}
