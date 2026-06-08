package Logica;

/**
 * Clase abstracta que define la estructura base para cualquier artículo vendible
 * en el expendedor.
 * Proporciona un identificador único y obliga a las subclases a implementar
 * su propia lógica de consumo.
 */
public abstract class Producto{
    /**
     * Número de serie único del producto.
     */
    private int serie;

    /**
     * Constructor para inicializar un producto con su identificador.
     * @param serie Entero que representa el número de serie único.
     */
    public Producto(int serie){
        this.serie=serie;

    }

    /**
     * Método abstracto que define la acción de consumir el producto.
     * @return Un String con la descripción del sabor o tipo de producto.
     */
    public abstract String consumir();

    /**
     * Permite obtener el número de serie del producto.
     * @return El número de serie entero.
     */
    public int getSerie(){
        return serie;
    }
    @Override
    public String toString(){
        return this.getClass().getSimpleName() + " [N° de Serie: " + this.serie + "]";
    }
}