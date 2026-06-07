package Logica;

/**
 * Clase que representa un producto de tipo Snickers.
 * Se extiende de Dulce para heredar sus propiedades.
 */
public class Snickers extends Dulce{

    /**
     * Constructor para crear un nuevo Snickers con un número de serie único.
     * @param serie Identificador único.
     */
    public Snickers(int serie){
        super(serie);
    }

    /**
     * Implementación del método consumir para este producto.
     * @return Un String que contiene el nombre del producto ("snickers").
     */
    @Override
    public String consumir(){
        return "snickers";
    }
}