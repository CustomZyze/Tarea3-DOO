package Visual;
import Logica.Expendedor;
import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
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

    public static void main(String[] args) {
        new Ventana();
    }
}