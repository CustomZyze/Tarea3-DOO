package Visual;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JPanelPrincipal extends JPanel implements MouseListener {
    private JPanelExpendedor panelExpendedor;
    private JPanelBilletera  panelBilletera;

    public JPanelPrincipal() {
        setLayout(null);  // posicionamiento absoluto
        setBackground(Color.WHITE);

        panelExpendedor = new JPanelExpendedor(50, 30, 400, 550);
        panelBilletera  = new JPanelBilletera(480, 30, 350, 550);

        add(panelExpendedor);
        add(panelBilletera);

        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // aquí puedes dibujar fondo u otros elementos globales
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        panelExpendedor.handleClick(e.getX(), e.getY());
        panelBilletera.handleClick(e.getX(), e.getY());
        repaint();
    }
    public void mousePressed(MouseEvent e)  {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e)  {}
    public void mouseExited(MouseEvent e)   {}
}