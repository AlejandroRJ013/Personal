import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.*;

public class StockArticulosPrueba {
    //	ATRIBUTOS
    private String nombre;
    private double precioBruto;
    private int cantidad;
    private double IVA;
    private final double IVAesencial = 1.04;
    private final double IVAnormal = 1.21;

    // Lista para almacenar los productos
    public static ArrayList<StockArticulosPrueba> inventario = new ArrayList<>();

    //	CONSTRUCTORES
    public StockArticulosPrueba() {
        this.nombre = "##";
        this.precioBruto = 0.0;
        this.cantidad = 0;
        this.IVA = IVAnormal;
    }

    public StockArticulosPrueba(String nombre, double precioBruto, boolean esencial, int cantidad) {
        this.nombre = nombre;
        this.precioBruto = precioBruto;
        this.cantidad = cantidad;
        if (esencial) {
            this.IVA = IVAesencial;            
        } else {
        this.IVA = IVAnormal;
        }
    }

    // METODOS
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precioBruto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getIVA() {
        return IVA;
    }

    public String toString() {
        return "  -  Productos:\n\t{" +
                " Artículo='" + nombre + '\'' +
                ", Precio='" + precioBruto + '\'' +
                ", Cantidad=" + cantidad + '\'' +
                ", IVA=" + IVA + '\'' +
                '}';
    }

    public static StockArticulosPrueba buscar(String nombreProducto) {
        StockArticulosPrueba esteEs = new StockArticulosPrueba();
        for (StockArticulosPrueba producto : StockArticulosPrueba.inventario) {
            String objetoProducto = producto.getNombre();
            if (nombreProducto.equals(objetoProducto)) {
                esteEs = producto;
                break;
            }
        }
        return esteEs;
    }

    public static void crearTicket(JFrame frame, HashMap<String, Integer> cesta, DecimalFormat dosDecimales, StringBuilder ticket, ArrayList<Double> precios) {
        double totalCompra = 0;
        for (Double precioCantidad : precios) {
            totalCompra += precioCantidad;
        }

        String laMulta = dosDecimales.format(totalCompra);

        JPanel ticketPanel = new JPanel();
        ticketPanel.setLayout(new GridBagLayout());

        GridBagConstraints posicion = new GridBagConstraints();
        posicion.fill = GridBagConstraints.BOTH;
        posicion.weightx = 1;
        posicion.weighty = 1;

        String[] titulo_productos = ticket.toString().split("!");

        //PANEL TITULO
        JPanel tituloPanel = new JPanel(new GridLayout(0, 1));
        tituloPanel.setBackground(Color.GRAY);
        JLabel titulo = new JLabel(titulo_productos[0]);
        titulo.setBorder(new EmptyBorder(5, 15, 5, 15));
        titulo.setForeground(Color.BLACK);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        tituloPanel.add(titulo);
        //PANEL TITULO
        
        //PANEL ARTÍCULOS
        JPanel todosLosProductos = new JPanel(new GridLayout(0, 1));
        todosLosProductos.setBorder(new EmptyBorder(2, 15, 5, 15));        
        int contador = 0;
        for (int i = 1; i < titulo_productos.length; i++) {
            JLabel ticketLineaProducto = new JLabel(titulo_productos[i]);
            ticketLineaProducto.setForeground(Color.BLACK);
            todosLosProductos.add(ticketLineaProducto);
            contador++;
            if (contador == 3) {
                todosLosProductos.add( new JLabel("  "));
                contador = 0;
            }
        }
        //PANEL ARTÍCULOS

        //PANEL TOTAL PAGAR
        JPanel totalPagarPanel = new JPanel(new GridBagLayout());
        totalPagarPanel.setBackground(new Color(Color.LIGHT_GRAY.getRed()-15, Color.LIGHT_GRAY.getGreen()-15, Color.LIGHT_GRAY.getBlue()-15));
        totalPagarPanel.setBorder(new EmptyBorder(0, 7, 0, 7));

        GridBagConstraints posicionamiento = new GridBagConstraints();
        posicionamiento.fill = GridBagConstraints.BOTH;
        posicionamiento.weightx = 1;
        posicionamiento.weighty = 1;

        posicionamiento.gridx = 0;
        JLabel totalPagar = new JLabel("Total a pagar:");
        totalPagar.setBorder(new EmptyBorder(3, 0, 3, 0));
        totalPagar.setFont(new Font("Arial", Font.PLAIN, 20));
        totalPagar.setHorizontalAlignment(SwingConstants.CENTER);
        totalPagar.setForeground(Color.BLACK);
        totalPagarPanel.add(totalPagar, posicionamiento);

        posicionamiento.gridx = 1;
        JLabel dineroCant = new JLabel(laMulta + "€");
        dineroCant.setFont(new Font("Arial", Font.BOLD, 20));
        dineroCant.setHorizontalAlignment(SwingConstants.CENTER);
        dineroCant.setForeground(Color.BLACK);
        totalPagarPanel.add(dineroCant, posicionamiento);
        //PANEL TOTAL PAGAR

        //PANEL BOTONES
        JPanel metodoDePago = new JPanel(new GridBagLayout());
        GridBagConstraints posicionPago = new GridBagConstraints();
        posicionPago.fill = GridBagConstraints.BOTH;
        posicionPago.weightx = 1;
        posicionPago.weighty = 1;

        JPanel metodoDePagoStr = new JPanel();
        metodoDePagoStr.setBackground(new Color(Color.LIGHT_GRAY.getRed()-30, Color.LIGHT_GRAY.getGreen()-30, Color.LIGHT_GRAY.getBlue()-30));
        JLabel metodoPagoLabel = new JLabel("Método de pago:");
        metodoPagoLabel.setBorder(new EmptyBorder(2, 15, 2, 15));
        metodoPagoLabel.setForeground(Color.BLACK);
        metodoPagoLabel.setFont(new Font("Arial", Font.BOLD, 15));
        metodoPagoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        metodoDePagoStr.add(metodoPagoLabel);

        posicionPago.gridy = 0;
        metodoDePago.add(metodoDePagoStr, posicionPago);

        JPanel botones = new JPanel(new GridLayout(0, 2));
        botones.setBackground(Color.GRAY);
        JButton datafono = new JButton(escalarImagen("Iconos\\datafono.png"));
        modificarBoton(datafono);
        JButton cartera = new JButton(escalarImagen("Iconos\\cartera.png"));
        modificarBoton(cartera);
        botones.add(datafono);
        botones.add(cartera);

        posicionPago.gridy = 1;
        metodoDePago.add(botones, posicionPago);
        //PANEL BOTONES

        posicion.gridy = 0;
        ticketPanel.add(tituloPanel, posicion);

        posicion.gridy = 1;
        ticketPanel.add(todosLosProductos, posicion);
        
        posicion.gridy = 2;
        ticketPanel.add(totalPagarPanel, posicion);
        
        posicion.gridy = 3;
        ticketPanel.add(metodoDePago, posicion);

        JDialog ticketDialog = new JDialog();
        ticketDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        ticketDialog.setModal(true); // Hace que no puedas utilizar otras ventanas hasta terminar en esta
        ticketDialog.setTitle("Ticket");

        accionesCartera(cartera, totalCompra, laMulta, dosDecimales, ticketDialog);
        accionesDatafono(frame, datafono, ticketDialog, laMulta);

        ticketDialog.add(ticketPanel);

        ticketDialog.pack();
        ticketDialog.setLocationRelativeTo(null);
        ticketDialog.setVisible(true);
    }

    public static void modificarBoton(JButton boton) {
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public static ImageIcon escalarImagen(String ruta) {
        ImageIcon imagen = new ImageIcon(ruta);
        ImageIcon imagenEscalada = new ImageIcon(imagen.getImage().getScaledInstance(42, 42, Image.SCALE_SMOOTH));
        return imagenEscalada;
    }

    public static void accionesCartera(JButton cartera, double totalCompra, String laMulta, DecimalFormat dosDecimales, JDialog ticketDialog) {
        cartera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean tacano = false;
                ticketDialog.dispose();

                do {
                    double losBilletes = Double.parseDouble(JOptionPane.showInputDialog(null, "Total a pagar: " + laMulta + "€", "A pagar, a pagar", JOptionPane.PLAIN_MESSAGE));
                    if (losBilletes < totalCompra) {
                        JOptionPane.showMessageDialog(null, "Cantidad insertada inferior al precio", "Tacaño", JOptionPane.WARNING_MESSAGE);
                        tacano = true;
                    } else {
                        double cambio = losBilletes - totalCompra;
                        String laCalderilla = dosDecimales.format(cambio);
                        JOptionPane.showMessageDialog(null, "Has pagado " + laMulta + "€ con " + losBilletes + "€\nTus vueltas son ==> " + laCalderilla, "Cambio", JOptionPane.INFORMATION_MESSAGE);
                        tacano = false;
                    }
                } while (tacano);
            }
        });
    }

    public static void accionesDatafono(JFrame frame, JButton datafono, JDialog ticketDialog, String laMulta) {
        datafono.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ticketDialog.dispose();

                try {
                    File rutaTarjeta = new File("Iconos\\tarjeta.png");
                    BufferedImage cursorTarjeta = ImageIO.read(rutaTarjeta);
                    
                    File tarjetaEspera = new File("Iconos\\tarjeta_wait.png");
                    BufferedImage cursorTarjetaEspera = ImageIO.read(tarjetaEspera);
        
                    // Obtener el objeto Toolkit para crear el cursor personalizado
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    Cursor cursorTarjetaPersonalizado = toolkit.createCustomCursor(cursorTarjeta, new Point(0, 0), "CustomCursor");
                    
                    Cursor cursorTarjetaEsperaPersonalizado = toolkit.createCustomCursor(cursorTarjetaEspera, new Point(0, 0), "CustomCursor");
                
                    dialogoNFC(frame, cursorTarjetaPersonalizado, cursorTarjetaEsperaPersonalizado, laMulta);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void dialogoNFC(JFrame frame, Cursor cursorPersonalizado, Cursor cursorTarjetaEsperaPersonalizado, String laMulta) {
        JDialog dialogo = new JDialog(frame, "Pago con NFC", true);
        dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogo.setCursor(cursorPersonalizado); // Establecer el cursor personalizado

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(Color.DARK_GRAY, 15));
        panel.setBackground(new Color(Color.DARK_GRAY.getRed()+15, Color.DARK_GRAY.getGreen()+15, Color.DARK_GRAY.getBlue()+15));

        JPanel panelNFC = new JPanel();
        panelNFC.setBackground(Color.GRAY);

        ImageIcon icono = new ImageIcon("Iconos\\nfc75.png");       
        JLabel labelIcono = new JLabel(icono);
        
        // Crear temporizador
        Timer timer = new Timer(1200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogo.dispose();
                JOptionPane.showMessageDialog(null, "Se ha pagado la cantidad de " + laMulta + "€ correctamente", "Pago realizado", JOptionPane.PLAIN_MESSAGE);
                ((Timer)e.getSource()).stop();
            }
        });

        // Agregar mouse listener al JLabel
        labelIcono.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                timer.start();
                dialogo.setCursor(cursorTarjetaEsperaPersonalizado);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                timer.stop();
                dialogo.setCursor(cursorPersonalizado);
            }
        });

        panelNFC.add(labelIcono);

        panel.add(panelNFC);
        dialogo.add(panel);

        dialogo.pack();
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }

    public static void agregarArticulo(String producto, double precio, int cantidad, JTextField productoTxt, JTextField precioTxt, JTextField cantidadTxt, boolean esencial) {
        Policias poliProducto = new Policias(productoTxt, "letras");
        Policias poliPrecio = new Policias(precioTxt, "decimales");
        Policias poliCantidad = new Policias(cantidadTxt, "enteros");

        if (poliProducto.getBoolean()) {
            producto = productoTxt.getText().toUpperCase();

            if (poliPrecio.getBoolean()) {
                precio = Double.parseDouble(precioTxt.getText());

                if (poliCantidad.getBoolean()) {

                    if (inventario.size() <= 20) {
                        if (poliProducto.getBoolean() && poliPrecio.getBoolean() && poliCantidad.getBoolean()) {
                            cantidad = Integer.parseInt(cantidadTxt.getText());

                            StockArticulosPrueba articulos = new StockArticulosPrueba(producto, precio, esencial, cantidad);
                            inventario.add(articulos);

                            JOptionPane.showMessageDialog(null, "¡Artículo agregado correctamente!", "Añadido al stock", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "El artículo no se ha podido agregar", "Error 404", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "¡El inventario contiene 20 o más productos!", "Error 404", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cantidad del producto debe ser un número entero y superior a 0.", "Error 404", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El precio del producto deben ser números decimales y superior a 0.", "Error 404", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El nombre del producto solo puede tener letras.", "Error 404", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void buscarArticulos(String introducido, StringBuilder productosCoincidentes, AtomicBoolean hayCoincidentes) {
        for (StockArticulosPrueba articulo : inventario) {
            if (articulo.getNombre().toLowerCase().contains(introducido.toLowerCase())) {
                productosCoincidentes.append(" - " + articulo.getNombre() + "\n");
                hayCoincidentes.set(true);
            }
        }
    }

    public static int indiceProducto(String nombre) {
        int indice = 0;
        for (int i = 0; i < inventario.size(); i++) {
            if (nombre.equals(nombre)) {
                indice = i;
                break;
            }
        }
        return indice;
    }
}