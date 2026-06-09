package Logica;

import java.util.ArrayList;

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
    private ArrayList<Moneda> monedero;


    public Comprador() {
        this.monedero = new ArrayList<>();
        this.sabor = null;
    }

    public void hacerCompra(Moneda m, Enumeracion cualProducto, Expendedor exp)
            throws PagoIncorrectoException, NoHayProductoException, PagoInsuficienteException{
        this.sabor = null;
        try {
            exp.comprarProducto(m,cualProducto);
            Producto p = exp.getProducto();

            if (p != null){
                this.sabor = p.consumir();
            }
        } finally {
            Moneda vuelto;
            while ((vuelto = exp.getVuelto()) != null){ //Si vamos a sacar vuelto de la maquina y no esta vacia, seguimos
                this.monedero.add(vuelto);
            }
        }
    }

    public void agregarMoneda (Moneda m){
        if (m != null){
            this.monedero.add(m);
        }
    }

    public ArrayList<Moneda> getMonedero(){
        return monedero;
    }

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
     * @return texto asociado al producto consumido.
     */
    public String queConsumiste(){
        return sabor;
    }
}
