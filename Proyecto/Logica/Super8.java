/**
 * Clase que representa un producto de tipo Snickers.
 * Se extiende de Dulce para heredar sus propiedades.
 */
public class Super8 extends Dulce{

    /**
     * Constructor para crear un nuevo Super8 con un número de serie único.
     * @param serie Identificador único.
     */
    public Super8(int serie){
        super(serie);
    }

    /**
     * Implementación del método consumir para este producto.
     * @return Un String que contiene el nombre del producto ("super8").
     */
    @Override
    public String consumir(){
        return "super8";
    }
}