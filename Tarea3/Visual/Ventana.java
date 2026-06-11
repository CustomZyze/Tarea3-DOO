package Visual;
import Logica.Expendedor;
import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal de la aplicación del expendedor la cual
 * configura la ventana JFrame, crea el expendedor lógico y agrega
 * el panel principal donde se muestra la interfaz gráfica.
 */
public class Ventana extends JFrame {

    /**
     * Constructor de la ventana principal.
     * Inicializa la ventana, define su tamaño, título, comportamiento de cierre
     * y agrega el panel principal de la aplicación.
     */
    public Ventana() {
        setTitle("Expendedor");
        setSize(1050, 970);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Expendedor expendedor = new Expendedor(5);
        PanelPrincipal panelPrincipal = new PanelPrincipal(expendedor);
        add(panelPrincipal, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Metodo principal que inicia la aplicación.
     * @param args argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        new Ventana();
    }
}