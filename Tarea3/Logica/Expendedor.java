package Logica;

/**
 * Clase que representa un expendedor de productos.
 * Permite almacenar bebidas y dulces, vender productos mediante monedas
 * y entregar vuelto cuando corresponde.
 */
public class Expendedor {

    /**
     * Depósitos internos del expendedor que almacenan un tipo específico de producto disponible para la venta.
     * El depósito monVu almacena las monedas que deben ser entregadas como vuelto.
     */
    private Deposito<Bebida> coca;
    private Deposito<Bebida> sprite;
    private Deposito<Moneda> monVu;
    private Deposito<Bebida> fanta;
    private Deposito<Dulce> super8;
    private Deposito<Dulce> snicker;
    private Deposito<Moneda> monPagadas;
    private Producto productoComprado;
    private int serieMonVu;

    /**
     * Crea los depósitos de productos y los llena con la cantidad indicada.
     * Cada producto se inicializa con un número de serie distinto.
     * @param numProductos cantidad de productos que se agregan a cada depósito.
     */
    public Expendedor(int numProductos){
        coca = new Deposito<Bebida>();
        sprite = new Deposito<Bebida>();
        monVu = new Deposito<Moneda>();
        fanta = new Deposito<Bebida>();
        super8 = new Deposito<Dulce>();
        snicker = new Deposito<Dulce>();

        monPagadas = new Deposito<Moneda>();
        productoComprado = null;
        serieMonVu = 1000;

        for(int i = 0 ; i < numProductos ; i++){
            coca.addAlgo(new CocaCola(100 + i));
            sprite.addAlgo(new Sprite(200 + i));
            fanta.addAlgo(new Fanta(300 + i));
            super8.addAlgo(new Super8(400 + i));
            snicker.addAlgo(new Snickers(500 + i));

        }

    }

    /**
     * Compra un producto del expendedor usando una moneda.
     * Verifica que la moneda exista, que el producto solicitado sea válido,
     * que el pago sea suficiente y que exista stock disponible. Si la compra
     * es exitosa, retorna el producto y guarda el vuelto en monedas de 100.
     * Si la compra falla, la moneda ingresada queda disponible como vuelto.
     * @param m moneda ingresada para pagar.
     * @param cual producto solicitado mediante la enumeración.
     * @return producto comprado.
     * @throws PagoIncorrectoException si no se ingresa una moneda válida.
     * @throws PagoInsuficienteException si el valor de la moneda no alcanza para comprar el producto.
     * @throws NoHayProductoException si el producto solicitado no existe o no queda stock.
     */
    public void comprarProducto (Moneda m , Enumeracion cual)
            throws NoHayProductoException, PagoIncorrectoException, PagoInsuficienteException {


        if(m == null){
            throw new PagoIncorrectoException();
        }

        if (cual == null){
            monVu.addAlgo(m);
            throw new NoHayProductoException();
        }

        if (m.getValor()< cual.getPrecio()){
            monVu.addAlgo(m);
            throw new PagoInsuficienteException();
        }

        Producto p = null;

        switch(cual){
            case COCA_COLA -> p = coca.getAlgo();
            case FANTA -> p = fanta.getAlgo();
            case SPRITE ->  p= sprite.getAlgo();
            case SUPER8 -> p= super8.getAlgo();
            case SNICKERS -> p= snicker.getAlgo();
        }

        if (p == null){
            monVu.addAlgo(m);
            throw new NoHayProductoException();
        }

        monPagadas.addAlgo(m);
        int diferencia = m.getValor() - cual.getPrecio();

        while (diferencia >= 1000) {
            monVu.addAlgo(new Moneda1000(serieMonVu));
            serieMonVu++;
            diferencia -= 1000;
        }

        while (diferencia >= 500) {
            monVu.addAlgo(new Moneda500(serieMonVu));
            serieMonVu++;
            diferencia -= 500;
        }
        while (diferencia >= 100) {
            monVu.addAlgo(new Moneda100(serieMonVu));
            serieMonVu++;
            diferencia -= 100;
        }
        this.productoComprado = p;
    }
    /**
     * Entrega una moneda del vuelto disponible.
     * Cada llamada retorna una moneda del depósito de vuelto. Si no quedan
     * monedas disponibles, retorna null.
     * @return una moneda de vuelto, o null si no hay vuelto disponible.
     */
    public Moneda getVuelto(){
        return monVu.getAlgo();

    }
    public Producto getProducto() {
        Producto retirado = this.productoComprado;
        this.productoComprado = null;
        return retirado;
    }

    public Deposito<Bebida> getCoca() { return coca; }
    public Deposito<Bebida> getSprite() { return sprite; }
    public Deposito<Bebida> getFanta() { return fanta; }
    public Deposito<Dulce> getSuper8() { return super8; }
    public Deposito<Dulce> getSnicker() { return snicker; }
    public Deposito<Moneda> getMonVu() { return monVu; }
    public Deposito<Moneda> getMonPagadas() { return monPagadas; }
    public Producto getProductoListo() { return productoComprado; }

    public void rellenarDepositos(){
        int CantRelleno = 6;
        int factorSerie = (int)(System.currentTimeMillis() % 1000);

        if (coca.getLista().isEmpty()) {
            for (int i = 0; i < CantRelleno; i++) {
                coca.addAlgo(new CocaCola(1000 + factorSerie + i));
            }
        }
        if (sprite.getLista().isEmpty()) {
            for (int i = 0; i < CantRelleno; i++) {
                sprite.addAlgo(new Sprite(2000 + factorSerie + i));
            }
        }
        if (fanta.getLista().isEmpty()) {
            for (int i = 0; i < CantRelleno; i++) {
                fanta.addAlgo(new Fanta(3000 + factorSerie + i));
            }
        }
        if (super8.getLista().isEmpty()) {
            for (int i = 0; i < CantRelleno; i++) {
                super8.addAlgo(new Super8(4000 + factorSerie + i));
            }
        }
        if (snicker.getLista().isEmpty()) {
            for (int i = 0; i < CantRelleno; i++) {
                snicker.addAlgo(new Snickers(5000 + factorSerie + i));
            }
        }
    }
}
