package Logica;

/**
 * Clase abstracta que representa una moneda cualquiera en el sistema.
 * Define el comportamiento base para todas las monedas, incluyendo
 * valor, número de serie y comparación.
 */
public abstract class Moneda implements Comparable<Moneda> {
    private int serie;

    /**
     * Constructor que asigna el número de serie de la moneda.
     *
     * @param serie identificador único de la moneda.
     */
    public Moneda(int serie) {
        this.serie = serie;
    }

    /**
     * Obtiene el número de serie de la moneda.
     *
     * @return número de serie de la moneda.
     */
    public int getSerie() {
        return this.serie;
    }

    /**
     * Retorna una representación en texto de la moneda.
     *
     * @return una representación en texto de la serie y el valor de la moneda.
     */
    @Override
    public String toString() {
        return "Serie: " + this.serie + " | Valor: " + this.getValor();
    }

    /**
     * Obtiene el valor monetario de la moneda.
     *
     * @return el valor de la moneda como entero.
     */
    public abstract int getValor();

    /**
     * Compara esta moneda con otra según su valor monetario.
     *
     * @param s la moneda con la que se va a comparar.
     * @return un número negativo si esta moneda vale menos que {@code s},
     *         cero si tienen el mismo valor,
     *         o un número positivo si esta moneda vale más que {@code s}.
     */
    @Override
    public int compareTo(Moneda s) {
        return Integer.compare(this.getValor(), s.getValor());
    }
}