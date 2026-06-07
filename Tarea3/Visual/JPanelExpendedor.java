package Visual;
import modelo.Expendedor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JPanelExpendedor extends JPanel {
    private Expendedor expendedor;
    private JButton btnComprar;
    private JButton btnCancelar;
    private JLabel  lblEstado;
    private JPanelDeposito[] depositos;

    public JPanelExpendedor(int x, int y, int ancho, int alto) {
        setBounds(x, y, ancho, alto);
        setLayout(null);
        setBackground(new Color(40, 60, 80));   // color máquina
        setBorder(BorderFactory.createLineBorder(new Color(20, 40, 60), 3));

        // Panel de depósitos (vidrio interior)
        int depX = 20, depY = 20;
        depositos = new JPanelDeposito[3];
        for (int i = 0; i < 3; i++) {
            depositos[i] = new JPanelDeposito(depX + i * 110, depY, 100, 280);
            add(depositos[i]);
        }

        // Label de estado (¡Compra exitosa!, etc.)
        lblEstado = new JLabel("", SwingConstants.CENTER);
        lblEstado.setBounds(20, 310, ancho - 40, 40);
        lblEstado.setForeground(Color.WHITE);
        lblEstado.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblEstado);

        // Botón Comprar
        btnComprar = new JButton("Comprar");
        btnComprar.setBounds(20, 370, 160, 45);
        btnComprar.setBackground(new Color(60, 160, 80));
        btnComprar.setForeground(Color.WHITE);
        btnComprar.setFont(new Font("Arial", Font.BOLD, 14));
        btnComprar.setFocusPainted(false);
        btnComprar.addActionListener(e -> realizarCompra());
        add(btnComprar);

        // Botón Cancelar
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(200, 370, 160, 45);
        btnCancelar.setBackground(new Color(180, 50, 50));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(e -> cancelarCompra());
        add(btnCancelar);

        // Inicializar modelo
        expendedor = new Expendedor();
    }

    private void realizarCompra() {
        // lógica de compra con el modelo
        lblEstado.setText("¡Compra exitosa!");
        lblEstado.setForeground(new Color(100, 220, 100));
        actualizarDepositos();
    }

    private void cancelarCompra() {
        lblEstado.setText("Compra cancelada");
        lblEstado.setForeground(new Color(220, 100, 100));
    }

    private void actualizarDepositos() {
        for (JPanelDeposito dep : depositos) {
            dep.actualizar();
            dep.repaint();
        }
    }

    public void handleClick(int cx, int cy) {
        // clicks directos sobre el panel (fuera de botones)
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        // aquí puedes dibujar detalles visuales extra de la máquina
    }
}