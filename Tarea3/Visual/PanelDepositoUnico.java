package Visual;

import Logica.Producto;
import java.awt.*;

/**
 * Clase que representa visualmente un depósito especial para un solo producto.
 * Este depósito se utiliza para mostrar el producto que queda listo para ser retirado
 * después de una compra exitosa.
 */
public class PanelDepositoUnico {
    private int x, y;
    private static final int ANCHO = 75, ALTO = 75;
    private Producto producto;
    private PanelProducto vistaProducto;

    /**
     * Constructor que define la posición del depósito único dentro del panel.
     *
     * @param x coordenada horizontal donde se dibuja el depósito.
     * @param y coordenada vertical donde se dibuja el depósito.
     */
    public PanelDepositoUnico(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Coloca un producto en el depósito especial y crea su vista gráfica.
     * Si el producto recibido es {@code null}, el depósito queda vacío.
     *
     * @param p producto que se mostrará en el depósito de retiro.
     */
    public void setProducto(Producto p) {
        this.producto = p;

        if (p != null) {
            vistaProducto = new PanelProducto(
                    x + 5,
                    y + 20,
                    p.getSerie(),
                    new Color(80, 160, 80),
                    "/imagenes/producto.png"
            );
        } else {
            vistaProducto = null;
        }
    }

    /**
     * Dibuja el depósito único en pantalla, incluyendo su fondo, borde,
     * etiqueta y el producto si existe uno listo para retirar.
     *
     * @param g objeto gráfico utilizado para dibujar el depósito.
     */
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
        if (vistaProducto != null) {
            vistaProducto.paintComponent(g);
        }
    }
}