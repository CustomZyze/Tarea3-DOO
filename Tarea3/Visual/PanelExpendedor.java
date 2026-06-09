package Visual;
import Logica.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class PanelExpendedor {
    // posición y tamaño dentro de PanelPrincipal
    private int x, y;
    private static final int ANCHO = 480, ALTO = 580;


    private Expendedor expendedor;

    // vistas de los depósito
    private PanelDeposito depCoca;
    private PanelDeposito depSprite;
    private PanelDeposito depFanta;
    private PanelDeposito depSuper8;
    private PanelDeposito depSnicker;
    private PanelDeposito depVuelto;
    private PanelDepositoUnico depProductoListo;

    private Image imagenFondo;
    private String mensajeEstado = "";

    public PanelExpendedor(int x, int y, Expendedor exp) {
        this.x = x;
        this.y = y;
        this.expendedor = exp;

        // cargar imagen de fondo
        try {
            ImageIcon icon = new ImageIcon(
                    getClass().getResource("/imagenes/expendedor_fondo.png"));
            imagenFondo = icon.getImage()
                    .getScaledInstance(ANCHO, ALTO, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            imagenFondo = null;
        }

        // depósitos con posiciones relativas al expendedor
        depCoca    = new PanelDeposito(x + 10,  y + 50, exp.getCoca(),    "Coca $1000",    new Color(220, 50, 50));
        depSprite  = new PanelDeposito(x + 90,  y + 50, exp.getSprite(),  "Sprite $800",   new Color(50, 180, 50));
        depFanta   = new PanelDeposito(x + 170, y + 50, exp.getFanta(),   "Fanta $800",    new Color(255, 140, 0));
        depSuper8  = new PanelDeposito(x + 250, y + 50, exp.getSuper8(),  "Super8 $300",   new Color(150, 80, 20));
        depSnicker = new PanelDeposito(x + 330, y + 50, exp.getSnicker(), "Snickers $600", new Color(100, 60, 20));

        // depósito de vuelto
        depVuelto = new PanelDeposito(x + 10, y + 430, exp.getMonVu(), "Vuelto", new Color(200, 200, 50));

        // depósito especial: un solo producto (compra exitosa)
        depProductoListo = new PanelDepositoUnico(x + 380, y + 430);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // dibujar cuerpo del expendedor
        if (imagenFondo != null) {
            g2.drawImage(imagenFondo, x, y, null);
        } else {
            g2.setColor(new Color(40, 60, 80));
            g2.fillRoundRect(x, y, ANCHO, ALTO, 20, 20);
            g2.setColor(new Color(20, 40, 60));
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(x, y, ANCHO, ALTO, 20, 20);
        }

        // dibujar todos los depósitos
        depCoca.paintComponent(g);
        depSprite.paintComponent(g);
        depFanta.paintComponent(g);
        depSuper8.paintComponent(g);
        depSnicker.paintComponent(g);
        depVuelto.paintComponent(g);
        depProductoListo.paintComponent(g);

        // mensaje estado
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.drawString(mensajeEstado, x + 10, y + 420);
    }

    /**
     * Procesa un click dentro del expendedor.
     * Si el click fue dentro del área, rellena depósitos vacíos.
     */
    public void handleClick(int cx, int cy) {
        if (cx >= x && cx <= x + ANCHO && cy >= y && cy <= y + ALTO) {
            rellenarDepositosVacios();
        }
    }

    private void rellenarDepositosVacios() {
        expendedor.rellenarDepositos();  // delega al modelo
        actualizarDepositos();
        mensajeEstado = "Depósitos rellenados";
    }

    /**
     * Realiza la compra usando el modelo, llamado desde PanelComprador.
     */
    public void realizarCompra(Enumeracion producto, Moneda moneda) {
        try {
            expendedor.comprarProducto(moneda, producto);
            depProductoListo.setProducto(expendedor.getProductoListo());
            mensajeEstado = "¡Compra exitosa!";
            actualizarDepositos();
        } catch (PagoInsuficienteException e) {
            mensajeEstado = "Pago insuficiente";
        } catch (NoHayProductoException e) {
            mensajeEstado = "Sin stock";
        } catch (PagoIncorrectoException e) {
            mensajeEstado = "Moneda inválida";
        }
    }

    private void actualizarDepositos() {
        depCoca.actualizar();
        depSprite.actualizar();
        depFanta.actualizar();
        depSuper8.actualizar();
        depSnicker.actualizar();
        depVuelto.actualizar();
    }

    public Moneda retirarVuelto()   { return expendedor.getVuelto(); }
    public Producto retirarProducto() { return expendedor.getProducto(); }

    public int getX()     { return x; }
    public int getY()     { return y; }
    public int getAncho() { return ANCHO; }
    public int getAlto()  { return ALTO; }
}