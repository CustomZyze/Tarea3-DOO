package Visual;
import Logica.Deposito;
import Logica.Producto;
import Logica.Moneda;
import java.awt.*;
import java.util.ArrayList;

public class PanelDeposito {
    private int x, y;
    private static final int ANCHO = 75, ALTO = 340;
    private Deposito<?> deposito;
    private String etiqueta;
    private Color color;
    private ArrayList<PanelProducto> vistasProductos = new ArrayList<>();
    private ArrayList<PanelMoneda>   vistasMonedas   = new ArrayList<>();
    private boolean esMonedas;

    public PanelDeposito(int x, int y, Deposito<?> dep, String etiqueta, Color color) {
        this.x        = x;
        this.y        = y;
        this.deposito = dep;
        this.etiqueta = etiqueta;
        this.color    = color;

        // detectar si el depósito es de monedas o productos
        // según la etiqueta (puedes cambiarlo si prefieres otro criterio)
        this.esMonedas = etiqueta.equals("Vuelto");

        actualizar();
    }