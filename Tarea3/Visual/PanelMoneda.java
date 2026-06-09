package Visual;
import javax.swing.*;
import java.awt.*;

public class PanelMoneda {
    private int x, y;
    private static final int DIAM = 30;
    private int valor;
    private int numSerie;
    private Image imagen;

    public PanelMoneda(int x, int y, int valor, int numSerie) {
        this.x        = x;
        this.y        = y;
        this.valor    = valor;
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
}