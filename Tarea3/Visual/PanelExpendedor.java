package Visual;
import Logica.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class PanelExpendedor {
    // posición y tamaño dentro de PanelPrincipal
    private int x, y, imgX , imgY;
    private static final int ANCHO = 1080, ALTO = 1180;
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

        this.imgX = x - 230;
        this.imgY = y - 270;

        // cargar imagen de fondo
        try {
            ImageIcon icon = new ImageIcon(
                    getClass().getResource("/imagenes/expendedor_vacio.png"));
            imagenFondo = icon.getImage()
                    .getScaledInstance(ANCHO, ALTO, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            imagenFondo = null;
        }

        // depósitos con posiciones relativas al expendedor
        depCoca    = new PanelDeposito(x + 20,  y + 40, exp.getCoca(),    "Coca $1000",    new Color(220, 50, 50));
        depSprite  = new PanelDeposito(x + 20,  y + 240, exp.getSprite(),  "Sprite $800",   new Color(50, 180, 50));
        depFanta   = new PanelDeposito(x + 20, y + 140, exp.getFanta(),   "Fanta $800",    new Color(255, 140, 0));
        depSuper8  = new PanelDeposito(x + 20, y + 440, exp.getSuper8(),  "Super8 $300",   new Color(150, 80, 20));
        depSnicker = new PanelDeposito(x + 20, y + 340, exp.getSnicker(), "Snickers $600", new Color(100, 60, 20));

        // depósito de vuelto
        depVuelto = new PanelDeposito(x + 500, y + 600, 300, 120, exp.getMonVu(), "Vuelto", new Color(200, 200, 50));

        // depósito especial: un solo producto (compra exitosa)
        depProductoListo = new PanelDepositoUnico(x + 150, y + 650);
    }

    public void limpiarDepositoListo() {
        depProductoListo.setProducto(null);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // dibujar cuerpo del expendedor
        if (imagenFondo != null) {
            g2.drawImage(imagenFondo, imgX, imgY, null);
        } else {
            g2.setColor(new Color(40, 60, 80));
            g2.fillRoundRect(x, y, ANCHO, ALTO, 20, 20);
            g2.setColor(new Color(20, 40, 60));
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(x, y, ANCHO, ALTO, 20, 20);
        }

        depProductoListo.setProducto(expendedor.getProductoListo());

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

    public void actualizarDepositos() {
        depCoca.actualizar();
        depSprite.actualizar();
        depFanta.actualizar();
        depSuper8.actualizar();
        depSnicker.actualizar();
        depVuelto.actualizar();
    }

    public int getX()     { return x; }
    public int getY()     { return y; }
    public int getAncho() { return ANCHO; }
    public int getAlto()  { return ALTO; }

    public Expendedor getExpendedor() {
        return expendedor;
    }

    public void setMensajeEstado(String s) {
        this.mensajeEstado = s;
    }
}