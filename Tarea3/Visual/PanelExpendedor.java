package Visual;

import Logica.*;
import java.awt.*;
import javax.swing.*;

/**
 * Clase que representa visualmente el expendedor dentro del panel principal.
 * Se encarga de dibujar la imagen del expendedor, sus depósitos de productos,
 * el depósito de vuelto y el depósito especial donde queda el producto listo
 * para ser retirado.
 */
public class PanelExpendedor {
    // posición de los depósitos y posición independiente de la imagen
    private int x, y, imgX, imgY;

    private static final int ANCHO = 1080, ALTO = 1180;

    private Expendedor expendedor;

    // vistas de los depósitos
    private PanelDeposito depCoca;
    private PanelDeposito depSprite;
    private PanelDeposito depFanta;
    private PanelDeposito depSuper8;
    private PanelDeposito depSnicker;
    private PanelDeposito depVuelto;
    private PanelDepositoUnico depProductoListo;

    private Image imagenFondo;
    private String mensajeEstado = "";

    /**
     * Constructor que inicializa el panel visual del expendedor.
     * Recibe la posición base, el modelo lógico del expendedor y crea
     * todos los depósitos visuales asociados.
     *
     * @param x coordenada horizontal base para ubicar los depósitos.
     * @param y coordenada vertical base para ubicar los depósitos.
     * @param exp expendedor lógico que contiene los productos, monedas y depósitos.
     */
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
            imagenFondo = icon.getImage();
        } catch (Exception e) {
            imagenFondo = null;
        }

        // depósitos con posiciones relativas al expendedor
        depCoca = new PanelDeposito(
                x + 97, y + 85, 176, 70,
                exp.getCoca(), "Coca $1000", new Color(220, 50, 50)
        );

        depSprite = new PanelDeposito(
                x + 97, y + 317, 176, 70,
                exp.getSprite(), "Sprite $800", new Color(50, 180, 50)
        );

        depFanta = new PanelDeposito(
                x + 97, y + 200, 176, 70,
                exp.getFanta(), "Fanta $800", new Color(255, 140, 0)
        );

        depSuper8 = new PanelDeposito(
                x + 98, y + 516, 176, 60,
                exp.getSuper8(), "Super8 $300", new Color(150, 80, 20)
        );

        depSnicker = new PanelDeposito(
                x + 98, y + 420, 176, 67,
                exp.getSnicker(), "Snickers $600", new Color(100, 60, 20)
        );

        // depósito de vuelto
        depVuelto = new PanelDeposito(
                x + 380, y + 600, 300, 120,
                exp.getMonVu(), "Vuelto", new Color(200, 200, 50)
        );

        // depósito especial: un solo producto listo para retirar
        depProductoListo = new PanelDepositoUnico(x + 129, y + 654);
    }

    /**
     * Limpia el depósito especial donde se muestra el producto listo.
     * Se utiliza cuando el comprador retira el producto.
     */
    public void limpiarDepositoListo() {
        depProductoListo.setProducto(null);
    }

    /**
     * Dibuja el expendedor completo en pantalla.
     * Primero dibuja la imagen del expendedor y luego los depósitos,
     * el producto listo, el vuelto y el mensaje de estado.
     *
     * @param g objeto gráfico utilizado para dibujar el expendedor.
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // dibujar cuerpo del expendedor
        if (imagenFondo != null) {
            g2.drawImage(imagenFondo, imgX, imgY, ANCHO, ALTO,null);
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

        // mensaje de estado
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.drawString(mensajeEstado, x + 380, y + 590);
    }

    /**
     * Procesa un click dentro del área del expendedor.
     * Si el click ocurre dentro de la zona del expendedor, se rellenan
     * los depósitos vacíos.
     *
     * @param cx coordenada horizontal del click.
     * @param cy coordenada vertical del click.
     */
    public void handleClick(int cx, int cy) {
        if (cx >= x+50 && cx <= x + 350 && cy >= y+50 && cy <= y + 800) {
            rellenarDepositosVacios();
        }
    }

    /**
     * Rellena los depósitos del expendedor usando el modelo lógico.
     * Luego actualiza las vistas de los depósitos y cambia el mensaje de estado.
     */
    private void rellenarDepositosVacios() {
        expendedor.rellenarDepositos();
        actualizarDepositos();
        mensajeEstado = "Depósitos rellenados";
    }

    /**
     * Actualiza visualmente todos los depósitos del expendedor.
     * Se debe llamar cuando cambia el contenido de los depósitos.
     */
    public void actualizarDepositos() {
        depCoca.actualizar();
        depSprite.actualizar();
        depFanta.actualizar();
        depSuper8.actualizar();
        depSnicker.actualizar();
        depVuelto.actualizar();
    }

    /**
     * Obtiene la coordenada horizontal base del expendedor.
     *
     * @return coordenada x del expendedor.
     */
    public int getX() {
        return x;
    }

    /**
     * Obtiene la coordenada vertical base del expendedor.
     *
     * @return coordenada y del expendedor.
     */
    public int getY() {
        return y;
    }

    /**
     * Obtiene el ancho usado para escalar la imagen del expendedor.
     *
     * @return ancho del expendedor.
     */
    public int getAncho() {
        return ANCHO;
    }

    /**
     * Obtiene el alto usado para escalar la imagen del expendedor.
     *
     * @return alto del expendedor.
     */
    public int getAlto() {
        return ALTO;
    }

    /**
     * Obtiene el expendedor lógico asociado a esta vista.
     *
     * @return expendedor lógico usado por el panel.
     */
    public Expendedor getExpendedor() {
        return expendedor;
    }

    /**
     * Modifica el mensaje de estado que se muestra en el expendedor.
     *
     * @param s nuevo mensaje de estado.
     */
    public void setMensajeEstado(String s) {
        this.mensajeEstado = s;
    }
}