package Visual;
import Logica.Expendedor;
import Logica.Comprador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Panel principal de la interfaz gráfica que contiene y coordina las vistas
 * del expendedor y del comprador. Capta los eventos del mouse y los delega.
 */
public class PanelPrincipal extends JPanel implements MouseListener {
    private PanelExpendedor panelExpendedor;
    private PanelComprador  panelComprador;

    /**
     * Constructor que inicializa los componentes visuales de la simulación.
     * Crea un comprador con un saldo inicial de 5 monedas de 1000 y posiciona
     * los paneles del expendedor y comprador en la ventana.
     * * @param expendedor El modelo lógico del expendedor que se va a representar.
     */
    public PanelPrincipal(Expendedor expendedor) {
        Comprador comprador = new Comprador();

        setBackground(new Color(85,85,85));
        setLayout(null);


        panelExpendedor = new PanelExpendedor(30, 30, expendedor);
        panelComprador  = new PanelComprador(730, 30, comprador);

        addMouseListener(this);
    }

    /**
     * Dibuja el fondo y le pide a los sub-paneles que se dibujen a sí mismos.
     * * @param g Objeto Graphics utilizado para dibujar en el panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        panelExpendedor.paintComponent(g);
        panelComprador.paintComponent(g);
    }

    /**
     * Captura los clics del mouse en la ventana y los transfiere a las
     * vistas del expendedor y del comprador para que procesen la interacción.
     * * @param e El evento del mouse registrado.
     */
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