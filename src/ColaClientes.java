import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ColaClientes {
    private NodoCola frente;

    public ColaClientes() {
        frente = null;
    }

    public boolean estaVacio() {
        return frente == null;
    }


    public void insertarCliente() throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = br.readLine();

        System.out.print("Ingrese el nombre del cliente: ");
        int prioridad= Integer.parseInt(br.readLine());

        Cliente clienteNuevo = new Cliente(nombre, prioridad);

        NodoCola nuevoCliente = new NodoCola(clienteNuevo);

        if (estaVacio()) {
            frente = nuevoCliente;
            return;
        }

        if (clienteNuevo.getPrioridad() > frente.cliente.getPrioridad()) {
            nuevoCliente.siguiente = frente;
            frente = nuevoCliente;
            return;
        }

        NodoCola nodoActual = frente;

        while (nodoActual.siguiente != null
                && nodoActual.siguiente.cliente.getPrioridad()
                >= clienteNuevo.getPrioridad()) {
            nodoActual = nodoActual.siguiente;
        }
        nuevoCliente.siguiente = nodoActual.siguiente;
        nodoActual.siguiente = nuevoCliente;
    }

    public Cliente atenderCliente() {
        if (estaVacio()) {
            System.out.println("La cola clientes esta vacia!");
            return null;
        }
        Cliente cliente = frente.cliente;
        frente = frente.siguiente;
        return cliente;
    }
}
