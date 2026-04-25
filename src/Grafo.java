import java.util.*;

public class Grafo {

    private final Map<String, List<Arista>> listaAdyacencia;

    public Grafo() {
        listaAdyacencia = new HashMap<>();
    }

    public void agregarVertice(String nuevaVertice) {
        listaAdyacencia.putIfAbsent(nuevaVertice, new ArrayList<>());
    }

    public void agregarArista(String origen, String nuevoDestino, int pesoArista) {
        agregarVertice(origen);
        agregarVertice(nuevoDestino);
        listaAdyacencia.get(origen).add(new Arista(nuevoDestino, pesoArista));
        listaAdyacencia.get(nuevoDestino).add(new Arista(origen, pesoArista));
    }

    public void algoritmoDijkstra(String inicio,
                                  Map<String, Integer> distancias,
                                  Map<String, String> predecesores) {

        PriorityQueue<Vertice> colaVertices = new PriorityQueue<>(Comparator.comparingInt(Vertice::getDistancia));

        for (String vertice : listaAdyacencia.keySet()) {
            distancias.put(vertice, Integer.MAX_VALUE);
            predecesores.put(vertice, null);
        }

        distancias.put(inicio, 0);
        colaVertices.add(new Vertice(inicio, 0));

        while (!colaVertices.isEmpty()) {
            Vertice v = colaVertices.poll();
            String verticeActual = v.getNombre();

            for (Arista arista : listaAdyacencia.get(verticeActual)) {
                String vecino = arista.getDestino();
                int nuevaDistancia = distancias.get(verticeActual) + arista.getPeso();

                if (nuevaDistancia < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDistancia);
                    predecesores.put(vecino, verticeActual);
                    colaVertices.add(new Vertice(vecino, nuevaDistancia));
                }
            }
        }
    }

    public List<String> reconstruirCamino(String inicio, String destino, Map<String, String> predecesores) {
        List<String> camino = new ArrayList<>();

        for (String verticeActual = destino; verticeActual != null; verticeActual = predecesores.get(verticeActual)) {
            camino.add(verticeActual);
        }

        Collections.reverse(camino);

        if (camino.getFirst().equals(inicio)) return camino;

        return new ArrayList<>();
    }

    public void calcularRuta(String inicio, String destino) {
        Map<String, Integer> distancias = new HashMap<>();

        Map<String, String> predecesores = new HashMap<>();

        algoritmoDijkstra(inicio, distancias, predecesores);

        List<String> camino = reconstruirCamino(inicio, destino, predecesores);

        if (camino.isEmpty()) System.out.println("No existe un camino disponible.");
        else {
            System.out.println("Ruta: " + camino);
            System.out.println("Distancia total: " + distancias.get(destino));
        }
    }

    public boolean existeCamino(String inicio, String destino) {

        Map<String, Integer> distancias = new HashMap<>();

        Map<String, String> predecesores = new HashMap<>();

        algoritmoDijkstra(inicio, distancias, predecesores);

        return distancias.get(destino) != Integer.MAX_VALUE;
    }

    public void mostrarGrafo() {
        if (listaAdyacencia.isEmpty()) {
            System.out.println("El mapa está vacío");
            return;
        }

        System.out.println();
        System.out.println("---Mapa de Rutas---");
        for (Map.Entry<String, List<Arista>> entry : listaAdyacencia.entrySet()) {

            String origen = entry.getKey();
            List<Arista> conexiones = entry.getValue();

            System.out.println();
            System.out.println("Ubicación: " + origen);

            if (conexiones.isEmpty()) {
                System.out.println("No hay conexiones");
            } else {
                for (Arista arista : conexiones) {
                    System.out.println(" -> " + arista.getDestino() +
                            " (Distancia: " + arista.getPeso() + " )");
                }
            }
        }
    }
}
