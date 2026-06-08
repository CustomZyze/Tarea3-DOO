package Visual;
import javax.swing.*;
import java.awt.*;

public class JPanelMoneda extends JPanel {
    private int valor;
    private int numSerie;
    private Image imagen;

    public JPanelMoneda(int x, int y, int valor, int numSerie) {
        setBounds(x, y, 36, 36);
        this.valor    = valor;
        this.numSerie = numSerie;
        setOpaque(false);
        setToolTipText("Moneda $" + valor + " | #" + numSerie);

        // cargar imagen según valor
        String nombreImagen = switch (valor) {
            case 100  -> "/imagenes/moneda_100.png";
            case 500  -> "/imagenes/moneda_500.png";
            case 1000 -> "/imagenes/moneda_1000.png";
            default   -> "/imagenes/moneda_100.png";
        };

        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(nombreImagen));
            imagen = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            imagen = null; // usará el fallback
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            g.drawImage(imagen, 2, 2, this);
        } else {
            // fallback: círculo de color según valor
            Color color = switch (valor) {
                case 100  -> new Color(200, 200, 200);
                case 500  -> new Color(210, 170, 50);
                case 1000 -> new Color(180, 100, 50);
                default   -> Color.YELLOW;
            };
            g.setColor(color);
            g.fillOval(2, 2, 32, 32);
            g.setColor(Color.DARK_GRAY);
            g.drawOval(2, 2, 32, 32);
        }
    }
}