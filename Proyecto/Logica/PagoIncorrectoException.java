package Logica;

/**
 * Excepción que se lanza cuando el pago ingresado no es válido.
 * Permite identificar el caso en que no se ingresó una moneda
 * o el pago recibido no cumple con las condiciones necesarias para realizar la compra.
 */
public class PagoIncorrectoException extends Exception{

    /**
     * Constructor de la excepción PagoIncorrectoException.
     * Define el mensaje de error asociado a un pago incorrecto.
     */
    public PagoIncorrectoException(){
        super("No Ingreso Moneda");
    }
}
