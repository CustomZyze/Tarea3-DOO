package Logica;

/**
 * Representa una moneda de valor 1000.
 * Esta clase concreta extiende la clase abstracta Moneda
 * e implementa el valor específico de una moneda de 500.
 */
public class Moneda1000 extends Moneda {

    /**
     * Crea una nueva moneda de valor 1000.
     * Llama al constructor de la clase padre Moneda.
     */
    public Moneda1000(){
        super();
    }

    /**
     * Entrega el valor fijo de esta moneda.
     * Este valor permite distinguir esta moneda de otras subclases.
     * @return el valor 1000 a esta moneda.
     */
    @Override
    public int getValor(){
        return 1000;
    }
}
