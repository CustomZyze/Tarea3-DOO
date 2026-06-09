package Logica;

/**
 * Clase que representa a un comprador dentro del sistema.
 * El comprador intenta comprar un producto en el expendedor utilizando una moneda.
 * Si la compra se realiza correctamente, guarda el sabor o resultado de consumir
 * el producto, y también calcula el vuelto recibido.
 */
public class Comprador {
    /**
     * Resultado de consumir el producto comprado.
     */
    private String sabor;

    /**
     * Suma total del vuelto recibido por el comprador.
     */
    private int vuelto;

    /**
     * Constructor del comprador el cual realiza la compra de un producto en el expendedor usando una moneda.
     * Si recibe un producto válido, lo consume y guarda el resultado.
     * Luego obtiene todas las monedas de vuelto disponibles y suma sus valores.
     * @param m moneda utilizada para realizar la compra.
     * @param cualProducto producto que se desea comprar, indicado mediante una enumeración.
     * @param exp expendedor donde se realiza la compra.
     * @throws PagoIncorrectoException si no se ingresa una moneda válida.
     * @throws NoHayProductoException si el producto solicitado no existe o no queda stock.
     * @throws PagoInsuficienteException si el valor de la moneda no alcanza para comprar el producto.
     */
    public Comprador(Moneda m, Enumeracion cualProducto, Expendedor exp) throws PagoIncorrectoException, NoHayProductoException, PagoInsuficienteException{
        this.vuelto = 0;
        try {
            exp.comprarProducto(m , cualProducto);
            Producto p = exp.getProducto();

            if( p != null){
                this.sabor = p.consumir();
            }
        } finally {
            Moneda v;
            while((v = exp.getVuelto()) != null){
                this.vuelto += v.getValor();
            }
        }
    }

    /**
     * Retorna el total del vuelto recibido por el comprador.
     * @return suma total del vuelto recibido.
     */
    public int cuantoVuelto(){
        return vuelto;
    }

    /**
     * Retorna el resultado de consumir el producto comprado.
     * El valor retornado corresponde al texto entregado por el metodo consumir()
     * del producto comprado.
     * @return texto asociado al producto consumido.
     */
    public String queConsumiste(){
        return sabor;
    }
}
