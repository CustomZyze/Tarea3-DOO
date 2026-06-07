package Logica;

/**
 * clase abstracta que representa moneda abstracta
 */
public abstract class Moneda implements Comparable<Moneda> {
    /**
     * Crea una nueva moneda.
     */
    public Moneda(){

    }
    /**
     * @return una representación en texto de la serie y el valor de la moneda.
     */
    public String toString(){
        return "Serie: " + this.hashCode() + " | Valor: " + this.getValor();
    }
    /**
     * @return el valor de la moneda como entero.
     */
    public abstract int getValor();

    /**
     *  Compara esta moneda con otra segun su valor.
     *  @return un número negativo si esta moneda vale menos que s,
     *  cero si tienen el mismo valor,
     *  o un número positivo si esta moneda vale más que s.
     */
    @Override
    public int compareTo(Moneda s){
        return Integer.compare(this.getValor(), s.getValor());
    }
}