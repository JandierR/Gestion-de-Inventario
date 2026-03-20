import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ArbolProductos {
    private Producto raiz;

    public ArbolProductos() {
        raiz = null;
    }

    public Producto getRaiz() {
        return raiz;
    }

    public void setRaiz(Producto raiz) {
        this.raiz = raiz;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public Producto buscarProducto(int id) {
        if (estaVacio()) {
            System.out.println("El inventario esta vacío!");
        }

        Producto productoTemp = raiz;

        while (productoTemp.getId() != id) {
            if (productoTemp.getId() > id) productoTemp = productoTemp.getIzquierda();
            else productoTemp = productoTemp.getDerecha();

            if (productoTemp == null) {
                System.out.println("El producto que busca no esta en el inventario!");
                return null;
            }

        }
        return productoTemp;
    }

    public void insertarProducto() throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print("Ingrese el nombre del producto: ");
        String nombre = br.readLine();

        System.out.print("Ingrese el precio del producto: ");
        double precio = Double.parseDouble(br.readLine());

        System.out.print("Ingrese la categoria del producto: ");
        String categoria = br.readLine();

        System.out.print("Ingrese la fecha de vencimiento del producto: ");
        String fechaVencimiento = br.readLine();

        System.out.print("Ingrese la cantidad del producto: ");
        int cantidad = Integer.parseInt(br.readLine());

        Producto nuevoProducto = new Producto(nombre, precio, categoria,
                fechaVencimiento, cantidad);

        if (estaVacio()) {
            raiz = nuevoProducto;
            return;
        }

        Producto temp = raiz;
        Producto padreTemp;
        while (true) {
            padreTemp = temp;
            if (temp.getId() > nuevoProducto.getId()) {
                temp = temp.getIzquierda();
                if (temp == null) {
                    padreTemp.setIzquierda(nuevoProducto);
                    return;
                }
            } else if (temp.getId() < nuevoProducto.getId()) {
                temp = temp.getDerecha();
                if (temp == null) {
                    padreTemp.setDerecha(nuevoProducto);
                    return;
                }
            }else {
                System.out.println("El producto ya esta en el inventario");
                return;
            }
        }
    }


    public void enOrden(Producto raizTemp) {
        if (raizTemp != null) {
            enOrden(raizTemp.getIzquierda());
            System.out.println(raizTemp.getId() + " ");
            enOrden(raizTemp.getDerecha());
        }
    }

    public void preOrden(Producto raizTemp) {
        if (raizTemp != null) {
            System.out.print(raizTemp.getId() + " ");
            enOrden(raizTemp.getIzquierda());
            enOrden(raizTemp.getDerecha());
        }
    }

    public void postOrden(Producto raizTemp) {
        if (raizTemp != null) {
            enOrden(raizTemp.getIzquierda());
            enOrden(raizTemp.getDerecha());
            System.out.print(raizTemp.getId() + " ");
        }
    }

    private Producto getSucesor(Producto producto) {
        Producto padreSucesor = producto;
        Producto sucesor = producto;
        Producto temp = producto.getDerecha();

        while (temp != null) {
            padreSucesor = sucesor;
            sucesor = temp;
            temp = temp.getIzquierda();
        }

        if (sucesor != producto.getDerecha()) {
            padreSucesor.setIzquierda(sucesor.getDerecha());
            sucesor.setDerecha(producto.getDerecha());

        }
        return sucesor;
    }

    private Producto getPadre(int id) {
        Producto nodoTemp = raiz;
        Producto padreTemp = raiz;
        while (nodoTemp.getId() != id) {
            padreTemp = nodoTemp;
            if (nodoTemp.getId() > id) nodoTemp = nodoTemp.getIzquierda();
            else nodoTemp = nodoTemp.getDerecha();
        }
        return padreTemp;
    }

    public Producto eliminarProducto(int id) {
        if (estaVacio()) {
            System.out.println("El inventario esta vacío");
            return null;
        }

        Producto nodo = buscarProducto(id);
        if (nodo == null) {
            return null;
        }

        if (nodo == raiz) {
            // Si el nodo buscado no es null, entonces:

            // Hay que valorar la posibilidad de que el nodo por borrar sea la raíz
            if (nodo.getDerecha() == null && nodo.getIzquierda() == null) raiz = null;
            else if (nodo.getDerecha() == null) raiz = raiz.getIzquierda();
            else if (nodo.getIzquierda() == null) raiz = raiz.getDerecha();
            else {
                Producto sucesor = getSucesor(raiz);
                sucesor.setIzquierda(raiz.getIzquierda());
                raiz = sucesor;
            }
            return nodo;
        }

        // Si no es la raíz, se debe buscar su padre
        Producto padreNodo = getPadre(id);

        if (nodo.getDerecha() == null && nodo.getIzquierda() == null) {
            if (nodo == padreNodo.getIzquierda()) padreNodo.setIzquierda(null);
            else padreNodo.setIzquierda(null);
        } else if (nodo.getDerecha() == null) {
            if (nodo == padreNodo.getIzquierda()) padreNodo.setIzquierda(nodo.getIzquierda());
            else padreNodo.setDerecha(nodo.getIzquierda());
        } else if (nodo.getIzquierda() == null) {
            if (nodo == padreNodo.getIzquierda()) padreNodo.setIzquierda(nodo.getDerecha());
            else padreNodo.setDerecha(nodo.getDerecha());

        }else {
            Producto sucesor = getSucesor(raiz);
            sucesor.setIzquierda(nodo.getIzquierda());

            if (nodo == padreNodo.getIzquierda()) padreNodo.setIzquierda(sucesor);
            else padreNodo.setDerecha(sucesor);
        }
        return nodo;
    }
}
