/**
 * Enumeración que centraliza los precios de los productos disponibles en el expendedor.
 * Cada constante define un producto específico junto con su precio de venta.
 */
public enum Enumeracion{
    /** Representa una bebida Coca-Cola con un precio de 1000. */
    COCA_COLA(1000),
    /** Representa una bebida Sprite con un precio de 800. */
    SPRITE(800),
    /** Representa una bebida Fanta con un precio de 800. */
    FANTA(800),
    /** Representa un dulce Super8 con un precio de 300. */
    SUPER8(300),
    /** Representa un dulce Snickers con un precio de 600. */
    SNICKERS(600);

    /** Valor monetario requerido para adquirir el producto. */
    private final int precio;

    /**
     * Constructor del enumerador que asigna el precio a cada producto.
     * @param precio Valor entero del producto.
     */
    Enumeracion(int precio){
        this.precio=precio;
    }

    /**
     * Permite consultar el precio asociado a una constante de producto.
     * @return El precio del producto como un entero.
     */
    public int getPrecio(){
        return precio;
    }
}