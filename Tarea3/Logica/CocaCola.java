package Logica;

/**
 * Clase que representa un producto de tipo CocaCola.
 * Se extiende de Bebida para heredar sus propiedades.
 */
public class CocaCola extends Bebida{

    /**
     * Constructor para crear una nueva CocaCola con un número de serie único.
     * @param serie Identificador único.
     */
    public CocaCola(int serie){
        super(serie);
    }

    /**
     * Implementación del método consumir para este producto.
     * @return Un String que contiene el nombre del producto ("cocacola").
     */
    @Override
    public String consumir() {
        return "cocacola";

    }
}
