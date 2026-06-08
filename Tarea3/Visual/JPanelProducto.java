package Visual;
import javax.swing.*;
import java.awt.*;

public class JPanelProducto extends JPanel {
    private int numSerie;
    private Image imagen;

    public JPanelProducto(int x, int y, int numSerie) {
        setBounds(x, y, 76, 50);
        this.numSerie = numSerie;
        setOpaque(false);
        setToolTipText("Producto #" + numSerie);

        // cargar imagen
        ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/producto.png"));
        imagen = icon.getImage().getScaledInstance(72, 46, Image.SCALE_SMOOTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            g.drawImage(imagen, 2, 2, this);
        } else {
            // fallback si no encuentra la imagen
            g.setColor(new Color(80, 160, 80));
            g.fillRoundRect(2, 2, 72, 46, 8, 8);
        }
        // número de serie encima de la imagen
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 11));
        g.drawString("#" + numSerie, 8, 28);
    }
}