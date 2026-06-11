package Visual;
import Logica.Deposito;
import Logica.Producto;
import Logica.Moneda;
import java.awt.*;
import java.util.ArrayList;

/**
 * Representación visual de un depósito del expendedor la cual
 * puede actuar como contenedor de productos (bebidas o dulces) o como
 * la bandeja de vuelto (monedas), sincronizando su contenido gráfico
 * con un depósito lógico asociado.
 */
public class PanelDeposito {
    private int x, y;
    private int ancho, alto;
    private Deposito<?> deposito;
    private String etiqueta;
    private Color color;
    private ArrayList<PanelProducto> vistasProductos = new ArrayList<>();
    private ArrayList<PanelMoneda>   vistasMonedas   = new ArrayList<>();
    private boolean esMonedas;

    private String obtenerRutaImagen() {
        if (etiqueta.toLowerCase().contains("coca")) {
            return "/imagenes/coca.png";
        } else if (etiqueta.toLowerCase().contains("sprite")) {
            return "/imagenes/sprite.png";
        } else if (etiqueta.toLowerCase().contains("fanta")) {
            return "/imagenes/fanta.png";
        } else if (etiqueta.toLowerCase().contains("super8")) {
            return "/imagenes/super.png";
        } else if (etiqueta.toLowerCase().contains("snicker")) {
            return "/imagenes/snicker.png";
        }

        return "/imagenes/producto.png";
    }

    /**
     * Constructor simplificado que asigna un tamaño por defecto al depósito.
     */
    public PanelDeposito(int x, int y, Deposito<?> dep, String etiqueta, Color color) {
        this(x, y, 270, 80, dep, etiqueta, color);
    }

    /**
     * Constructor principal para crear la vista de un depósito.
     * * @param x Coordenada X del panel.
     * @param y Coordenada Y del panel.
     * @param ancho Ancho del rectángulo del depósito.
     * @param alto Alto del rectángulo del depósito.
     * @param dep El depósito lógico que contiene los datos reales.
     * @param etiqueta Nombre que se mostrará en la esquina del depósito.
     * @param color Color base para los productos en caso de que no cargue la imagen.
     */
    public PanelDeposito(int x, int y, int ancho, int alto, Deposito<?> dep, String etiqueta, Color color) {
        this.x        = x;
        this.y        = y;
        this.ancho    = ancho;
        this.alto     = alto;
        this.deposito = dep;
        this.etiqueta = etiqueta;
        this.color    = color;
        this.esMonedas = etiqueta.equals("Vuelto");

        actualizar();
    }

    /**
     * Sincroniza la vista gráfica con el estado actual del depósito lógico.
     * Revisa la lista de elementos y genera instancias visuales (PanelProducto
     * o PanelMoneda) calculando automáticamente su posición para que no se superpongan.
     */
    public void actualizar() {
        vistasProductos.clear();
        vistasMonedas.clear();

        ArrayList<?> lista = deposito.getLista();

        for (int i = 0; i < lista.size(); i++) {
            Object item = lista.get(i);

            if (item instanceof Moneda m) {
                int px = x + 15 + (i%5) * 75;
                int py = y+ 35 + (i/5) * 50;
                PanelMoneda pm = new PanelMoneda(px, py, m.getValor(), m.getSerie());
                pm.setXY(px, py);
                vistasMonedas.add(pm);
            } else if (item instanceof Producto p) {
                int px = x + 9 + i * 30;
                int py = y + 15 ;
                String rutaImagen = obtenerRutaImagen();

                PanelProducto pp = new PanelProducto(px, py, p.getSerie(), color, rutaImagen);
                pp.setXY(px, py);
                vistasProductos.add(pp);
            }
        }
    }

    /**
     * Dibuja el marco del depósito, su etiqueta y delega el dibujo de cada
     * ítem interno a sus respectivas vistas.
     * * @param g Objeto Graphics utilizado para dibujar.
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(200, 220, 240, 160));
        g2.fillRoundRect(x, y, ancho, alto, 10, 10);
        g2.setColor(new Color(100, 150, 200));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x, y, ancho, alto, 10, 10);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 9));
        g2.drawString(etiqueta, x + 2, y + 14);

        for (PanelProducto pp : vistasProductos) pp.paintComponent(g);
        for (PanelMoneda   pm : vistasMonedas)   pm.paintComponent(g);
    }
}