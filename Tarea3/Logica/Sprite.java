package Logica;

/**
 * Clase que representa un producto de tipo Sprite.
 * Se extiende de Bebida para heredar sus propiedades.
 */
public class Sprite extends Bebida {

    /**
     * Constructor para crear una nueva Sprite con un número de serie único.
     * @param serie Identificador único.
     */
     public Sprite(int serie){
         super(serie);
    }

    /**
     * Implementación del método consumir para este producto.
     * @return Un String que contiene el nombre del producto ("sprite").
     */
    @Override
    public String consumir() {
        return "sprite";
    }
}
