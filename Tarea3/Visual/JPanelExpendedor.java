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
    private Image imagenFondo;

    public JPanelExpendedor(int x, int y, int ancho, int alto) {
        setBounds(x, y, ancho, alto);
        setLayout(null);

        // cargar imagen de fondo
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/expendedor_fondo.png"));
            imagenFondo = icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            imagenFondo = null;
        }

        // depósitos
        depositos = new JPanelDeposito[3];
        for (int i = 0; i < 3; i++) {
            depositos[i] = new JPanelDeposito(20 + i * 110, 20, 100, 280);
            add(depositos[i]);
        }

        // label estado
        lblEstado = new JLabel("", SwingConstants.CENTER);
        lblEstado.setBounds(20, 310, ancho - 40, 40);
        lblEstado.setForeground(Color.WHITE);
        lblEstado.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblEstado);

        // botón Comprar
        btnComprar = new JButton("Comprar");
        btnComprar.setBounds(20, 370, 160, 45);
        btnComprar.setBackground(new Color(60, 160, 80));
        btnComprar.setForeground(Color.WHITE);
        btnComprar.setFont(new Font("Arial", Font.BOLD, 14));
        btnComprar.setFocusPainted(false);
        btnComprar.addActionListener(e -> realizarCompra());
        add(btnComprar);

        // botón Cancelar
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(200, 370, 160, 45);
        btnCancelar.setBackground(new Color(180, 50, 50));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(e -> cancelarCompra());
        add(btnCancelar);

        expendedor = new Expendedor();
    }

    private void realizarCompra() {
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

    public void handleClick(int cx, int cy) {}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (imagenFondo != null) {
            g2.drawImage(imagenFondo, 0, 0, this);
        } else {
            // fallback: rectángulo oscuro
            g2.setColor(new Color(40, 60, 80));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            g2.setColor(new Color(20, 40, 60));
            g2.drawRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        }
    }
}