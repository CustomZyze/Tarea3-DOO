package Visual;
import Logica.Deposito;
import Logica.Producto;
import Logica.Moneda;
import java.awt.*;
import java.util.ArrayList;

public class PanelDeposito {
    private int x, y;
    private int ancho, alto;
    private Deposito<?> deposito;
    private String etiqueta;
    private Color color;
    private ArrayList<PanelProducto> vistasProductos = new ArrayList<>();
    private ArrayList<PanelMoneda>   vistasMonedas   = new ArrayList<>();
    private boolean esMonedas;

    // constructor sin tamaño (usa 75x340 por defecto)
    public PanelDeposito(int x, int y, Deposito<?> dep, String etiqueta, Color color) {
        this(x, y, 270, 80, dep, etiqueta, color);
    }

    // constructor con tamaño personalizado
    public PanelDeposito(int x, int y, int ancho, int alto, Deposito<?> dep, String etiqueta, Color color) {
        this.x        = x;
        this.y        = y;
        this.ancho    = ancho;
        this.alto     = alto;
        this.deposito = dep;
        this.etiqueta = etiqueta;
        this.color    = color;
        this.esMonedas = etiqueta.equals("Vuelto");
        actualizar();
    }

    public void actualizar() {
        vistasProductos.clear();
        vistasMonedas.clear();

        ArrayList<?> lista = deposito.getLista();

        for (int i = 0; i < lista.size(); i++) {
            Object item = lista.get(i);

            if (item instanceof Moneda m) {
                int px = x + 5 + i * 35;  // horizontal: px cambia
                int py = y + 45;               // vertical: py fijo
                PanelMoneda pm = new PanelMoneda(px, py, m.getValor(), m.getSerie());
                pm.setXY(px, py);
                vistasMonedas.add(pm);
            } else if (item instanceof Producto p) {
                int px = x + 5 + i * 55;
                int py = y + 25 ; // productos siguen vertical
                PanelProducto pp = new PanelProducto(px, py, p.getSerie(), color);
                pp.setXY(px, py);
                vistasProductos.add(pp);
            }
        }
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(200, 220, 240, 160));
        g2.fillRoundRect(x, y, ancho, alto, 10, 10);
        g2.setColor(new Color(100, 150, 200));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x, y, ancho, alto, 10, 10);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 9));
        g2.drawString(etiqueta, x + 2, y + 14);

        for (PanelProducto pp : vistasProductos) pp.paintComponent(g);
        for (PanelMoneda   pm : vistasMonedas)   pm.paintComponent(g);
    }
}