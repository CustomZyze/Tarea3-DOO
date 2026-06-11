package Visual;

import javax.swing.*;
import java.awt.*;

/**
 * Representación visual de un producto individual dentro de la interfaz gráfica.
 * Se encarga de cargar y dibujar la imagen correspondiente al producto o, en su defecto,
 * un rectángulo de color alternativo junto con su número de serie.
 */
public class PanelProducto {
    private int x, y;
    private static final int ANCHO = 45, ALTO = 48;
    private int numSerie;
    private Color color;
    private Image imagen;

    /**
     * Constructor que inicializa la vista del producto en coordenadas específicas.
     * Intenta cargar la imagen del producto desde la ruta dada; si falla, guarda
     * el color de respaldo para dibujarlo manualmente.
     * @param x Coordenada X inicial.
     * @param y Coordenada Y inicial.
     * @param numSerie Número de serie único del producto lógico asociado.
     * @param color Color de respaldo a utilizar si la imagen no se encuentra.
     * @param rutaImagen Ruta del archivo de imagen (ej. "/imagenes/coca.png").
     */
    public PanelProducto(int x, int y, int numSerie, Color color, String rutaImagen) {
        this.x = x;
        this.y = y;
        this.numSerie = numSerie;
        this.color = color;

        try {
            ImageIcon icon = new ImageIcon(
                    getClass().getResource(rutaImagen)
            );
            imagen = icon.getImage();
        } catch (Exception e) {
            System.out.println("No se pudo cargar imagen: " + rutaImagen);
            imagen = null;
        }
    }

    /**
     * Actualiza las coordenadas de la vista del producto.
     * @param x Nueva coordenada X.
     * @param y Nueva coordenada Y.
     */
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Dibuja el producto en la pantalla.
     * Si la imagen cargó con éxito, la dibuja. Si no
     * dibuja un rectangulo representativo del
     * producto con su numero de serie
     * @param g objeto Graphics utilizado para dibujar el producto.
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (imagen != null) {

            g2.drawImage(imagen, x-12, y-7,ANCHO+13, ALTO+13, null);
        } else {

            g2.setColor(color);
            g2.fillRoundRect(x, y, ANCHO, ALTO, 5, 5);


            g2.setColor(color.darker());
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(x, y, ANCHO, ALTO, 5, 5);


            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 11));
            g2.drawString("#" + numSerie, x + 5, y + 48);
        }
    }
}
