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

    /**
     * Sincroniza las vistas con el contenido real del depósito.
     * Llama a setXY en cada vista para reposicionarlas.
     */
    public void actualizar() {
        vistasProductos.clear();
        vistasMonedas.clear();

        ArrayList<?> lista = deposito.getLista();

        for (int i = 0; i < lista.size(); i++) {
            int px = x + 5;
            int py = y + 25 + i * 55;
            Object item = lista.get(i);

            if (item instanceof Moneda m) {
                PanelMoneda pm = new PanelMoneda(px, py, m.getValor(), m.getSerie());
                pm.setXY(px, py); // reposicionar explícitamente
                vistasMonedas.add(pm);
            } else if (item instanceof Producto p) {
                PanelProducto pp = new PanelProducto(px, py, p.getSerie(), color);
                pp.setXY(px, py); // reposicionar explícitamente
                vistasProductos.add(pp);
            }
        }
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // fondo del depósito (vidrio)
        g2.setColor(new Color(200, 220, 240, 160));
        g2.fillRoundRect(x, y, ANCHO, ALTO, 10, 10);
        g2.setColor(new Color(100, 150, 200));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x, y, ANCHO, ALTO, 10, 10);

        // etiqueta del depósito
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 9));
        g2.drawString(etiqueta, x + 2, y + 14);

        // dibujar productos o monedas
        for (PanelProducto pp : vistasProductos) pp.paintComponent(g);
        for (PanelMoneda   pm : vistasMonedas)   pm.paintComponent(g);
    }
}