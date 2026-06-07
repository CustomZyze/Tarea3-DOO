package Logica;

/**
 * Clase abstracta que representa la categoría de productos tipo bebida.
 * Sirve como nexo entre la clase general Producto y las bebidas como CocaCola, Sprite o Fanta.
 */
public abstract class Bebida extends Producto{

    /**
     * Constructor que transfiere el número de serie a la superclase Producto.
     * @param serie Identificador único.
     */
    public Bebida(int serie){
        super(serie);
    }
}