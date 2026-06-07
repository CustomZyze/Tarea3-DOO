package Logica;

/**
 * Representa una moneda de valor 500.
 * Esta clase concreta extiende la clase abstracta Moneda
 * e implementa el valor específico de una moneda de 500.
 */
public class Moneda500 extends Moneda {

    /**
     * Crea una nueva moneda de valor 500.
     * Llama al constructor de la clase padre Moneda.
     */
    public Moneda500(){
        super();
    }

    /**
     * Entrega el valor fijo de esta moneda.
     * Este valor permite distinguir esta moneda de otras subclases.
     * @return el valor 500 a esta moneda.
     */
    @Override
    public int getValor(){
        return 500;
    }
}
