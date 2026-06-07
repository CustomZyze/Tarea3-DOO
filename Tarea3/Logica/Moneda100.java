package Logica;

/**
 * Representa una moneda de valor 100.
 * Esta clase concreta extiende la clase abstracta Moneda
 * e implementa el valor específico de una moneda de 100.
 */
public class Moneda100 extends Moneda {

    /**
     * Crea una nueva moneda de valor 100.
     * Llama al constructor de la clase padre Moneda.
     */
    public Moneda100(){
        super();
    }

    /**
     * Entrega el valor fijo de esta moneda.
     * Este valor permite distinguir esta moneda de otras subclases.
     * @return el valor 100 a esta moneda.
     */
    @Override
    public int getValor(){
        return 100;
    }
}
