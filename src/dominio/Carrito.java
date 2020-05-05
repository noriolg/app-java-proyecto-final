package dominio;

import java.util.ArrayList;

public class Carrito {
    ArrayList<ClaseProducto> carrito;

    public Carrito() {
        carrito = new ArrayList<ClaseProducto>();
    }

    public Carrito(ArrayList<ClaseProducto> carrito) {
        this.carrito = carrito;
    }

    public ArrayList<ClaseProducto> getCarrito() {
        return carrito;
    }

    public void setCarrito(ArrayList<ClaseProducto>carrito) {
        this.carrito = carrito;
    }
    public void addClase(ClaseProducto c){
            this.carrito.add(c);
    }
    public void clear()
    {
        this.carrito.clear();
    }
}

