package Visual;
import Logica.Expendedor;
import Logica.Comprador;
import Logica.Moneda1000;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelPrincipal extends JPanel implements MouseListener {
    private PanelExpendedor panelExpendedor;
    private PanelComprador  panelComprador;
    private Comprador comprador;

    public PanelPrincipal(Expendedor expendedor) {
        Comprador comprador = new Comprador();

        for (int i = 0; i < 5; i++) {
            comprador.agregarMoneda(new Moneda1000(9000 + i));
        }

        setBackground(Color.WHITE);
        setLayout(null);

        // crear vistas pasando el modelo
        panelExpendedor = new PanelExpendedor(30, 30, expendedor);
        panelComprador  = new PanelComprador(530, 30, comprador);

        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // pinta fondo blanco
        panelExpendedor.paintComponent(g);  // dibuja expendedor
        panelComprador.paintComponent(g);   // dibuja comprador
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        panelExpendedor.handleClick(e.getX(), e.getY());
        panelComprador.handleClick(e.getX(), e.getY(), panelExpendedor);
        repaint();
    }
    public void mousePressed(MouseEvent e)  {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e)  {}
    public void mouseExited(MouseEvent e)   {}
}