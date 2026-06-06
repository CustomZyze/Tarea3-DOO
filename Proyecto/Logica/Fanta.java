/**
 * Clase que representa un producto de tipo Fanta.
 * Se extiende de Bebida para heredar sus propiedades.
 */
public class Fanta extends Bebida{

    /**
     * Constructor para crear una nueva Fanta con un número de serie único.
     * @param serie Identificador único.
     */
    public Fanta(int serie){
        super(serie);
    }

    /**
     * Implementación del método consumir para este producto.
     * @return Un String que contiene el nombre del producto ("fanta").
     */
    @Override
    public String consumir() {
        return "fanta";

    }
}
