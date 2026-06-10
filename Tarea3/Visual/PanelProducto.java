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
    private static final int ANCHO = 65, ALTO = 48;
    private int numSerie;
    private Color color;
    private Image imagen;

    /**
     * Constructor que inicializa la vista del producto en coordenadas específicas.
     * Intenta cargar la imagen del producto desde la ruta dada; si falla, guarda
     * el color de respaldo para dibujarlo manualmente.
     *
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
            imagen = icon.getImage()
                    .getScaledInstance(ANCHO, ALTO, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            System.out.println("No se pudo cargar imagen: " + rutaImagen);
            imagen = null;
        }
    }

    /**
     * Actualiza las coordenadas de la vista del producto.
     * Útil para reubicar gráficamente el producto dentro de los depósitos.
     *
     * @param x Nueva coordenada X.
     * @param y Nueva coordenada Y.
     */
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void paintComponent(Graphics g) {
    }
}
/**
 * Dibuja el producto en la pantalla.
 * Si la imagen cargó con éxito, la dibuja. Si no
 */