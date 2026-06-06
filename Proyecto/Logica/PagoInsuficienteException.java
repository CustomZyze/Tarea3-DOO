/**
 * Excepción que se lanza cuando el pago ingresado no alcanza para realizar la compra.
 * Permite identificar el caso en que sí se ingresó una moneda,
 * pero su valor es menor al precio del producto solicitado.
 */
public class PagoInsuficienteException extends Exception {

    /**
     * Constructor de la excepción PagoInsuficienteException.
     * Define el mensaje de error asociado a un pago insuficiente.
     */
    public PagoInsuficienteException(){
        super("No ingreso cantidad necesaria");
    }
}
