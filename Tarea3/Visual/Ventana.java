package Visual;

import javax.swing.*;
import java.awt.*;

public class PanelProducto {
    private int x, y;
    private static final int ANCHO = 65, ALTO = 48;
    private int numSerie;
    private Color color;
    private Image imagen;

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

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (imagen != null) {
            g2.drawImage(imagen, x, y, null);
        } else {
            g2.setColor(color);
            g2.fillRoundRect(x, y, ANCHO, ALTO, 8, 8);
            g2.setColor(color.darker());
            g2.drawRoundRect(x, y, ANCHO, ALTO, 8, 8);
        }

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 10));
        g2.drawString("#" + numSerie, x + 4, y + 28);
    }
}