package Logica;

/**
 * Clase que representa una excepción cuando no hay producto disponible.
 * Se utiliza para indicar que el expendedor no tiene stock del producto solicitado.
 */
public class NoHayProductoException extends Exception{

    /**
     * Constructor para crear una nueva excepción NoHayProductoException.
     * Inicializa la excepción con un mensaje que indica que no hay producto disponible.
     */
    public NoHayProductoException(){
        super("no hay producto disponible");
    }
}
