package Visual;
import Logica.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Representación visual del panel de control del comprador.
 * Permite al usuario interactuar con la interfaz para seleccionar un producto,
 * elegir una moneda del monedero para pagar, efectuar la compra y retirar el producto.
 */
public class PanelComprador {
    private int x, y;
    private static final int ANCHO = 300, ALTO = 880;
    private ArrayList<PanelMoneda> vistasMonedas = new ArrayList<>();


    private PanelMoneda btn100;
    private PanelMoneda btn500;
    private PanelMoneda btn1000;

    private Comprador comprador;
    private Enumeracion productoSeleccionado = null;
    private int monedaSeleccionada = 0;
    private String mensaje = "Selecciona producto y moneda";

    private final int[][] zonasProductos = {
            {10, 40,  280, 45},
            {10, 95,  280, 45},
            {10, 150, 280, 45},
            {10, 205, 280, 45},
            {10, 260, 280, 45},
    };
    private final Enumeracion[] productos = Enumeracion.values();
    private final Color[] coloresProductos = {
            new Color(220, 50,  50),
            new Color(50,  180, 50),
            new Color(255, 140, 0),
            new Color(150, 80,  20),
            new Color(100, 60,  20)
    };

    private final int[][] zonasMonedas = {
            {10,  330, 65},
            {100, 330, 65},
            {190, 330, 65},
    };
    private final int[] valoresMonedas = {100, 500, 1000};
    private final Color[] coloresMonedas = {
            new Color(180, 180, 180),
            new Color(210, 170, 50),
            new Color(180, 100, 50)
    };

    private final int[] zonaRecarga = {10, 550, 280, 45};
    private final int[] zonaRetiro = {10, 490, 280, 45};

    /**
     * Constructor que inicializa el panel interactivo del comprador.
     * @param x Coordenada X inicial del panel.
     * @param y Coordenada Y inicial del panel.
     * @param comprador El modelo lógico del comprador asociado a esta vista.
     */
    public PanelComprador(int x, int y, Comprador comprador) {
        this.x = x;
        this.y = y;
        this.comprador = comprador;

        btn100  = new PanelMoneda(x + zonasMonedas[0][0], y + zonasMonedas[0][1], 100, 0);
        btn500  = new PanelMoneda(x + zonasMonedas[1][0], y + zonasMonedas[1][1], 500, 0);
        btn1000 = new PanelMoneda(x + zonasMonedas[2][0], y + zonasMonedas[2][1], 1000, 0);

        btn100.setDiametro(70);
        btn500.setDiametro(70);
        btn1000.setDiametro(70);
    }

    /**
     * Limpia la lista de vistas actuales y crea un nuevo PanelMoneda
     * por cada moneda existente en el monedero, ubicándolas en filas y columnas.
     */
    private void actualizarVistasMonedas() {
        vistasMonedas.clear();
        ArrayList<Moneda> monedero = comprador.getMonedero();
        for (int i = 0; i < monedero.size(); i++) {
            Moneda m = monedero.get(i);
            int px = x + 10 + (i % 5) * 50;  // 8 monedas por fila
            int py = y + 624 + (i / 5) * 55;  // nueva fila cada 8
            vistasMonedas.add(new PanelMoneda(px, py, m.getValor(), m.getSerie()));
        }
    }

    /**
     * Dibuja todos los elementos visuales del panel del comprador, incluyendo
     * los botones de productos, las monedas disponibles, el saldo actual,
     * los mensajes de estado y la zona de retiro.
     * @param g Objeto Graphics utilizado para dibujar en el panel.
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(245, 240, 220));
        g2.fillRoundRect(x, y, ANCHO, ALTO, 20, 20);
        g2.setColor(new Color(180, 160, 120));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x, y, ANCHO, ALTO, 20, 20);

        g2.setColor(Color.DARK_GRAY);
        g2.setFont(new Font("Arial", Font.BOLD, 15));
        g2.drawString("Comprador", x + 10, y + 25);
        g2.setFont(new Font("Arial", Font.BOLD, 13));
        g2.drawString("Saldo: $" + comprador.cuantaPlata(), x + 170, y + 25);

        for (int i = 0; i < zonasProductos.length; i++) {
            int zx = x + zonasProductos[i][0];
            int zy = y + zonasProductos[i][1];
            int zw = zonasProductos[i][2];
            int zh = zonasProductos[i][3];

            boolean seleccionado = productoSeleccionado == productos[i];

            g2.setColor(seleccionado
                    ? coloresProductos[i].brighter()
                    : coloresProductos[i]);
            g2.fillRoundRect(zx, zy, zw, zh, 10, 10);

            g2.setColor(seleccionado ? Color.YELLOW : Color.WHITE);
            g2.setStroke(new BasicStroke(seleccionado ? 2.5f : 1));
            g2.drawRoundRect(zx, zy, zw, zh, 10, 10);

            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 12));
            g2.drawString(productos[i].name() + "  $" + productos[i].getPrecio(),
                    zx + 8, zy + 28);
        }

        for (int i = 0; i < zonasMonedas.length; i++) {
            int zx   = x + zonasMonedas[i][0];
            int zy   = y + zonasMonedas[i][1];
            int diam = zonasMonedas[i][2];

            boolean seleccionado = monedaSeleccionada == valoresMonedas[i];

            if (i == 0) btn100.paintComponent(g);
            if (i == 1) btn500.paintComponent(g);
            if (i == 2) btn1000.paintComponent(g);

            if (seleccionado) {
                g2.setColor(Color.YELLOW);
                g2.setStroke(new BasicStroke(3));
                g2.drawOval(zx - 3, zy - 3, diam + 6, diam + 6);
            }
        }

        g2.setColor(new Color(180, 160, 120));
        g2.setStroke(new BasicStroke(1));
        g2.drawLine(x + 10, y + 420, x + ANCHO - 10, y + 420);

        g2.setColor(Color.DARK_GRAY);
        g2.setFont(new Font("Arial", Font.ITALIC, 12));
        g2.drawString(mensaje, x + 10, y + 440);

        g2.setFont(new Font("Arial", Font.BOLD, 12));
        g2.drawString("Dinero disponible: $" + comprador.cuantaPlata(), x + 10, y + 465);

        g2.setColor(new Color(80, 130, 200));
        g2.fillRoundRect(x + zonaRetiro[0], y + zonaRetiro[1],
                zonaRetiro[2], zonaRetiro[3], 10, 10);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(x + zonaRetiro[0], y + zonaRetiro[1],
                zonaRetiro[2], zonaRetiro[3], 10, 10);
        g2.setFont(new Font("Arial", Font.BOLD, 13));
        g2.drawString("Retirar producto", x + zonaRetiro[0] + 8,
                y + zonaRetiro[1] + 28);


        g2.setColor(new Color(140, 180, 140));
        g2.fillRoundRect(x + zonaRecarga[0], y + zonaRecarga[1], zonaRecarga[2], zonaRecarga[3], 10, 10);
        g2.setColor(Color.WHITE);
        g2.drawRoundRect(x + zonaRecarga[0], y + zonaRecarga[1], zonaRecarga[2], zonaRecarga[3], 10, 10);
        g2.drawString("+ Añadir Moneda $1000", x + zonaRecarga[0] + 70, y + zonaRecarga[1] + 28);


        actualizarVistasMonedas();
        g2.setFont(new Font("Arial", Font.BOLD, 15));
        g2.setColor(Color.DARK_GRAY);
        g2.drawString("Monedero:", x + 10, y + 608);
        for (PanelMoneda pm : vistasMonedas) {
            pm.paintComponent(g);
        }
    }

    /**
     * Detecta las interacciones del usuario (clics) dentro del panel.
     * Identifica si el usuario hizo clic en un producto, en una moneda o en
     * el botón de retiro, preparando los datos o ejecutando la acción final.
     * @param cx Coordenada X del clic del mouse.
     * @param cy Coordenada Y del clic del mouse.
     * @param exp Referencia al panel visual del expendedor para sincronizar la interfaz y acceder a su modelo.
     */
    public void handleClick(int cx, int cy, PanelExpendedor exp) {
        for (int i = 0; i < zonasProductos.length; i++) {
            int zx = x + zonasProductos[i][0];
            int zy = y + zonasProductos[i][1];
            int zw = zonasProductos[i][2];
            int zh = zonasProductos[i][3];

            if (cx >= zx && cx <= zx + zw && cy >= zy && cy <= zy + zh) {
                productoSeleccionado = productos[i];
                mensaje = "Producto: " + productos[i].name();
                intentarCompra(exp);
                return;
            }
        }

        for (int i = 0; i < zonasMonedas.length; i++) {
            int zx    = x + zonasMonedas[i][0];
            int zy    = y + zonasMonedas[i][1];
            int diam  = zonasMonedas[i][2];
            int radio = diam / 2;
            int cx0   = zx + radio;
            int cy0   = zy + radio;

            double dist = Math.sqrt(Math.pow(cx - cx0, 2) + Math.pow(cy - cy0, 2));
            if (dist <= radio) {
                monedaSeleccionada = valoresMonedas[i];
                mensaje = "Moneda seleccionada: $" + valoresMonedas[i];
                intentarCompra(exp);
                return;
            }
        }

        int rx = x + zonaRetiro[0];
        int ry = y + zonaRetiro[1];
        if (cx >= rx && cx <= rx + zonaRetiro[2] && cy >= ry && cy <= ry + zonaRetiro[3]) {
            Expendedor expMod = exp.getExpendedor();
            if (expMod.getProductoListo() != null) {
                Producto p = expMod.getProductoListo();
                comprador.retirarProducto(expMod);
                mensaje = "Consumiste: " + comprador.queConsumiste() + " #" + p.getSerie();
                exp.limpiarDepositoListo();
                exp.actualizarDepositos();
            } else {
                mensaje = "No hay producto para retirar";
            }
            return;
        }
        int tx = x + zonaRecarga[0];
        int ty = y + zonaRecarga[1];
        if (cx >= tx && cx <= tx + zonaRecarga[2] && cy >= ty && cy <= ty + zonaRecarga[3]) {
            comprador.recargarMonedero();
            mensaje = "Moneda añadida al monedero";
        }

    }

    /**
     * Intenta realizar la compra de un producto utilizando la moneda y el
     * producto previamente seleccionados. Se comunica con la lógica del sistema
     * y maneja las excepciones, reflejando el resultado en los mensajes visuales.
     * @param panelExp Vista del expendedor para invocar métodos lógicos y actualizar gráficos.
     */
    private void intentarCompra(PanelExpendedor panelExp) {
        if (productoSeleccionado == null || monedaSeleccionada == 0) return;

        Moneda monedaParaPagar = comprador.sacarMoneda(monedaSeleccionada);

        if (monedaParaPagar == null) {
            mensaje = "No tienes monedas de $" + monedaSeleccionada;
            productoSeleccionado = null;
            monedaSeleccionada = 0;
            return;
        }

        try {
            Expendedor expModel = panelExp.getExpendedor();
            comprador.hacerCompra(monedaParaPagar, productoSeleccionado, expModel);
            panelExp.setMensajeEstado("¡Compra exitosa!");
            mensaje = "Retirar Producto.";
        } catch (PagoInsuficienteException e) {
            panelExp.setMensajeEstado("Pago insuficiente");
            mensaje = "Dinero insuficiente.";
            comprador.agregarMoneda(monedaParaPagar);
        } catch (NoHayProductoException e) {
            panelExp.setMensajeEstado("Sin stock");
            mensaje = "No queda stock disponible.";
            comprador.agregarMoneda(monedaParaPagar);
        } catch (PagoIncorrectoException e) {
            panelExp.setMensajeEstado("Moneda inválida");
            mensaje = "Error con la moneda.";
            comprador.agregarMoneda(monedaParaPagar);
        }

        panelExp.actualizarDepositos();
        productoSeleccionado = null;
        monedaSeleccionada   = 0;
    }

    public int getX()     { return x; }
    public int getY()     { return y; }
    public int getAncho() { return ANCHO; }
    public int getAlto()  { return ALTO; }
}