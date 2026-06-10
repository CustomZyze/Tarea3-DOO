package Visual;
import Logica.*;
import java.awt.*;

public class PanelComprador {
    private int x, y;
    private static final int ANCHO = 300, ALTO = 580;

    private Enumeracion productoSeleccionado = null;
    private int monedaSeleccionada = 0;
    private int saldo = 5000;
    private String mensaje = "Selecciona producto y moneda";
    private int serieMonedas = 9000;

    private final int[][] zonasProductos = {
            {10, 40,  280, 45},  // COCA_COLA
            {10, 95,  280, 45},  // SPRITE
            {10, 150, 280, 45},  // FANTA
            {10, 205, 280, 45},  // SUPER8
            {10, 260, 280, 45},  // SNICKERS
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
            {10,  330, 70},
            {100, 330, 70},
            {190, 330, 70},
    };
    private final int[] valoresMonedas = {100, 500, 1000};
    private final Color[] coloresMonedas = {
            new Color(180, 180, 180),
            new Color(210, 170, 50),
            new Color(180, 100, 50)
    };

    // zona para retirar producto del depósito especial
    private final int[] zonaRetiro = {10, 490, 280, 45};

    public PanelComprador(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // fondo del comprador
        g2.setColor(new Color(245, 240, 220));
        g2.fillRoundRect(x, y, ANCHO, ALTO, 20, 20);
        g2.setColor(new Color(180, 160, 120));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x, y, ANCHO, ALTO, 20, 20);

        // título y saldo
        g2.setColor(Color.DARK_GRAY);
        g2.setFont(new Font("Arial", Font.BOLD, 15));
        g2.drawString("Comprador", x + 10, y + 25);
        g2.setFont(new Font("Arial", Font.BOLD, 13));
        g2.drawString("Saldo: $" + saldo, x + 170, y + 25);

        // zonas de productos
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

        // zonas de monedas
        for (int i = 0; i < zonasMonedas.length; i++) {
            int zx   = x + zonasMonedas[i][0];
            int zy   = y + zonasMonedas[i][1];
            int diam = zonasMonedas[i][2];

            boolean seleccionado = monedaSeleccionada == valoresMonedas[i];

            g2.setColor(seleccionado
                    ? coloresMonedas[i].brighter()
                    : coloresMonedas[i]);
            g2.fillOval(zx, zy, diam, diam);

            g2.setColor(seleccionado ? Color.YELLOW : Color.DARK_GRAY);
            g2.setStroke(new BasicStroke(seleccionado ? 2.5f : 1));
            g2.drawOval(zx, zy, diam, diam);

            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 12));
            g2.drawString("$" + valoresMonedas[i], zx + 6, zy + diam / 2 + 5);
        }

        // línea separadora
        g2.setColor(new Color(180, 160, 120));
        g2.setStroke(new BasicStroke(1));
        g2.drawLine(x + 10, y + 420, x + ANCHO - 10, y + 420);

        // mensaje estado
        g2.setColor(Color.DARK_GRAY);
        g2.setFont(new Font("Arial", Font.ITALIC, 12));
        g2.drawString(mensaje, x + 10, y + 440);

        // saldo disponible
        g2.setFont(new Font("Arial", Font.BOLD, 12));
        g2.drawString("Dinero disponible: $" + saldo, x + 10, y + 465);

        // zona de retiro del producto
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
    }

    public void handleClick(int cx, int cy, PanelExpendedor exp) {
        // click en zona de producto
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

        // click en zona de moneda
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
                mensaje = "Moneda: $" + valoresMonedas[i];
                intentarCompra(exp);
                return;
            }
        }

        // click en zona de retiro
        int rx = x + zonaRetiro[0];
        int ry = y + zonaRetiro[1];
        if (cx >= rx && cx <= rx + zonaRetiro[2] && cy >= ry && cy <= ry + zonaRetiro[3]) {
            Producto p = exp.retirarProducto();
            if (p != null) {
                mensaje = "Producto retirado: #" + p.getSerie();
                exp.limpiarDepositoListo();
            } else {
                mensaje = "No hay producto para retirar";
            }
        }
    }

    private void intentarCompra(PanelExpendedor exp) {
        if (productoSeleccionado == null || monedaSeleccionada == 0) return;

        if (saldo < monedaSeleccionada) {
            mensaje = "Saldo insuficiente";
            return;
        }

        Moneda moneda = switch (monedaSeleccionada) {
            case 500  -> new Moneda500(serieMonedas++);
            case 1000 -> new Moneda1000(serieMonedas++);
            default   -> new Moneda100(serieMonedas++);
        };

        saldo -= monedaSeleccionada;
        exp.realizarCompra(productoSeleccionado, moneda);

        // recoger vuelto automáticamente
        Moneda v;
        while ((v = exp.retirarVuelto()) != null) {
            saldo += v.getValor();
        }

        productoSeleccionado = null;
        monedaSeleccionada   = 0;
        mensaje = "Saldo actual: $" + saldo;
    }

    public int getX()     { return x; }
    public int getY()     { return y; }
    public int getAncho() { return ANCHO; }
    public int getAlto()  { return ALTO; }
}