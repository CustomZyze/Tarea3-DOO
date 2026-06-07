package Logica;

/**
 * Clase abstracta que representa la categoría de productos tipo dulce.
 * Sirve como nexo entre la clase general Producto y los dulces como Snickers y Super8.
 */
public abstract class Dulce extends Producto{

    /**
     * Constructor que transfiere el número de serie a la superclase Producto.
     * @param serie Identificador único.
     */
    public Dulce(int serie){
        super(serie);
    }
}
