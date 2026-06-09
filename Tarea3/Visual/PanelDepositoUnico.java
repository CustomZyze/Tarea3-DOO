package Visual;
import Logica.Producto;
import java.awt.*;

public class PanelDepositoUnico {
    private int x, y;
    private static final int ANCHO = 75, ALTO = 75;
    private Producto producto;
    private PanelProducto vistaProducto;

    public PanelDepositoUnico(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Coloca un producto en el depósito especial y crea su vista.
     */
    public void setProducto(Producto p) {
        this.producto = p;
        if (p != null)
            vistaProducto = new PanelProducto(x + 5, y + 20, p.getSerie(), new Color(80, 160, 80));
        else
            vistaProducto = null;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // fondo amarillo para distinguirlo
        g2.setColor(new Color(240, 200, 100, 200));
        g2.fillRoundRect(x, y, ANCHO, ALTO, 10, 10);
        g2.setColor(new Color(180, 140, 40));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x, y, ANCHO, ALTO, 10, 10);

        // etiqueta
        g2.setColor(Color.DARK_GRAY);
        g2.setFont(new Font("Arial", Font.BOLD, 10));
        g2.drawString("Retiro", x + 5, y + 14);

        // dibujar producto si hay uno listo
        if (vistaProducto != null) vistaProducto.paintComponent(g);
    }
}