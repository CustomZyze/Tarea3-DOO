package Visual;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa visualmente una moneda dentro de la interfaz.
 * Se encarga de cargar la imagen correspondiente según el valor de la moneda
 * y dibujarla en pantalla. Si la imagen no se encuentra, dibuja un círculo
 * de respaldo con un color asociado al valor.
 */
public class PanelMoneda {
    private int x, y;
    private static final int DIAM = 30;
    private int valor;
    private int numSerie;
    private Image imagen;

    /**
     * Constructor que inicializa la vista gráfica de una moneda.
     * Define su posición, valor, número de serie y carga la imagen asociada.
     *
     * @param x coordenada horizontal donde se dibuja la moneda.
     * @param y coordenada vertical donde se dibuja la moneda.
     * @param valor valor monetario de la moneda.
     * @param numSerie número de serie de la moneda.
     */
    public PanelMoneda(int x, int y, int valor, int numSerie) {
        this.x = x;
        this.y = y;
        this.valor = valor;
        this.numSerie = numSerie;

        String nombreImg = switch (valor) {
            case 100  -> "/imagenes/moneda_100.png";
            case 500  -> "/imagenes/moneda_500.png";
            case 1000 -> "/imagenes/moneda_1000.png";
            default   -> "/imagenes/moneda_100.png";
        };

        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(nombreImg));
            imagen = icon.getImage().getScaledInstance(DIAM, DIAM, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            imagen = null;
        }
    }

    /**
     * Reposiciona la moneda dentro del depósito.
     * Se debe llamar cada vez que se agrega o saca una moneda del depósito.
     *
     * @param x nueva coordenada horizontal de la moneda.
     * @param y nueva coordenada vertical de la moneda.
     */
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Dibuja la moneda en pantalla.
     * Si existe una imagen cargada, la dibuja. En caso contrario,
     * dibuja un círculo de respaldo con el valor de la moneda.
     *
     * @param g objeto gráfico utilizado para dibujar la moneda.
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (imagen != null) {
            g2.drawImage(imagen, x, y, null);
        } else {
            // fallback: círculo de color según valor
            Color color = switch (valor) {
                case 100  -> new Color(200, 200, 200); // gris
                case 500  -> new Color(210, 170, 50);  // dorado
                case 1000 -> new Color(180, 100, 50);  // cobre
                default   -> Color.YELLOW;
            };

            g2.setColor(color);
            g2.fillOval(x, y, DIAM, DIAM);

            g2.setColor(color.darker());
            g2.drawOval(x, y, DIAM, DIAM);

            // valor encima
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 8));
            g2.drawString("$" + valor, x + 2, y + 18);
        }

        // número de serie
        g2.setColor(new Color(255, 255, 255, 180));
        g2.setFont(new Font("Arial", Font.PLAIN, 7));
        g2.drawString("#" + numSerie, x + 2, y + DIAM - 2);
    }
}