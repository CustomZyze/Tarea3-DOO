package Logica;

import java.util.ArrayList;

/**
 * Clase que representa a un comprador dentro del sistema.
 * El comprador intenta comprar un producto en el expendedor utilizando su dinero (monedero),
 */
public class Comprador {
    /**
     * Resultado de consumir el producto comprado.
     */
    private String sabor;
    private ArrayList<Moneda> monedero;
    private int generadorSerie = 9000;

    /**
     * Constructor por defecto del Comprador.
     * Inicializa un monedero vacío y el sabor en nulo.
     * Con un ciclo rellena 5 monedas de 1000 al
     * monedero inicialmente.
     */
    public Comprador() {
        this.monedero = new ArrayList<>();
        this.sabor = null;

        for (int i = 0; i < 5; i++) {
            recargarMonedero();
        }
    }
    /**
     * Intenta comprar un producto en el expendedor especificado usando una moneda.
     * Al terminar el intento , el comprador recoge
     * automáticamente el vuelto de la maquina y lo guarda en su monedero.
     * @param m Moneda con la que se intentará pagar.
     * @param cualProducto Enumeración que indica el tipo de producto deseado.
     * @param exp El expendedor al cual se le realizará la transacción.
     * @throws PagoIncorrectoException Si la moneda ingresada es nula.
     * @throws NoHayProductoException Si el producto no existe o no queda stock en el expendedor.
     * @throws PagoInsuficienteException Si el valor de la moneda es menor al precio del producto.
     */

    public void hacerCompra(Moneda m, Enumeracion cualProducto, Expendedor exp)
            throws PagoIncorrectoException, NoHayProductoException, PagoInsuficienteException{
        this.sabor = null;
        exp.comprarProducto(m,cualProducto);

    }

    /**
     * Retira el producto de la ventanilla de la máquina (si hay) y lo consume,
     * guardando su sabor en la memoria del comprador.
     * @param exp El expendedor del cual se retirará el producto.
     */
    public void retirarProducto(Expendedor exp) {
        Producto p = exp.getProducto();
        if (p != null) {
            this.sabor = p.consumir();
        }
        Moneda vuelto;
        while ((vuelto = exp.getVuelto()) != null ){
            this.monedero.add(vuelto);
        }
    }

    /**
     * Añade una moneda directamente al monedero del comprador.
     */
    public void agregarMoneda (Moneda m){
        if (m != null){
            this.monedero.add(m);
        }
    }

    /**
     * Recorre el monedero hasta encontrar una moneda que tenga el mismo valor
     * recibido por parámetro. Si la encuentra, la elimina del monedero y la retorna.
     * Si no existe retorna null.
     * @param valor valor de la moneda que se desea sacar del monedero.
     * @return la moneda retirada del monedero, o null si no se encuentra.
     */
    public Moneda sacarMoneda(int valor) {
        for (int i = 0; i < monedero.size(); i++) {
            if (monedero.get(i).getValor() == valor) {
                return monedero.remove(i);
            }
        }
        return null;
    }

    /**
     * Recarga el monedero agregando una nueva moneda de $1000 luego
     * crea una moneda con el número de serie actual, la agrega al monedero
     * y luego aumenta el generador de serie para la próxima moneda.
     */
    public void recargarMonedero() {
        Moneda nuevaMoneda = new Moneda1000(generadorSerie);
        this.agregarMoneda(nuevaMoneda);
        generadorSerie++;
    }

    /**
     * Calcula la suma del valor de todas las monedas actuales en el monedero.
     * @return El dinero total disponible.
     */
    public ArrayList<Moneda> getMonedero(){
        return monedero;
    }

    /**
     * Calcula la cantidad total de dinero almacenada en el monedero.
     * @return el monto total de dinero disponible en el monedero.
     */
    public int cuantaPlata(){
        int totalPlata = 0;
        for (Moneda m: monedero){
            totalPlata += m.getValor();
        }
        return totalPlata;
    }

    /**
     * Retorna el resultado de consumir el producto comprado.
     * El valor retornado corresponde al texto entregado por el metodo consumir()
     * del producto comprado.
     * * @return texto asociado al producto consumido.
     */
    public String queConsumiste(){
        return sabor;
    }
}
