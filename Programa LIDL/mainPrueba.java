import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.*;
import javax.swing.border.*;

public class mainPrueba {
    public static void main(String[] args) {
        StockArticulosPrueba leche = new StockArticulosPrueba("LECHE", 1.20, true, 170);
        StockArticulosPrueba.inventario.add(leche);

        StockArticulosPrueba pan = new StockArticulosPrueba("PAN", 0.80, false, 150);
        StockArticulosPrueba.inventario.add(pan);

        StockArticulosPrueba huevos = new StockArticulosPrueba("HUEVOS", 1.80, true, 210);
        StockArticulosPrueba.inventario.add(huevos);

        StockArticulosPrueba arroz = new StockArticulosPrueba("ARROZ", 1.50, false, 200);
        StockArticulosPrueba.inventario.add(arroz);

        StockArticulosPrueba pasta = new StockArticulosPrueba("PASTA", 1.40, true, 190);
        StockArticulosPrueba.inventario.add(pasta);

        StockArticulosPrueba tomates = new StockArticulosPrueba("TOMATES", 2.20, false, 180);
        StockArticulosPrueba.inventario.add(tomates);

        StockArticulosPrueba patatas = new StockArticulosPrueba("PATATAS", 2.00, true, 220);
        StockArticulosPrueba.inventario.add(patatas);

        StockArticulosPrueba manzanas = new StockArticulosPrueba("MANZANAS", 1.60, false, 200);
        StockArticulosPrueba.inventario.add(manzanas);

        StockArticulosPrueba pollo = new StockArticulosPrueba("POLLO", 4.50, true, 190);
        StockArticulosPrueba.inventario.add(pollo);

        StockArticulosPrueba queso = new StockArticulosPrueba("QUESO", 3.20, false, 200);
        StockArticulosPrueba.inventario.add(queso);

        // PRUEBA PARA NO AÑADIR MÁS DE 20
        
        // StockArticulosPrueba cerveza = new StockArticulosPrueba("CERVEZA", 1.80, true, 180);
        // StockArticulosPrueba.inventario.add(cerveza);

        // StockArticulosPrueba vino = new StockArticulosPrueba("VINO", 8.50, true, 170);
        // StockArticulosPrueba.inventario.add(vino);

        // StockArticulosPrueba carne = new StockArticulosPrueba("CARNE", 10.00, true, 160);
        // StockArticulosPrueba.inventario.add(carne);

        // StockArticulosPrueba yogur = new StockArticulosPrueba("YOGUR", 0.90, true, 220);
        // StockArticulosPrueba.inventario.add(yogur);

        // StockArticulosPrueba refresco = new StockArticulosPrueba("REFRESCO", 1.20, true, 190);
        // StockArticulosPrueba.inventario.add(refresco);

        // StockArticulosPrueba cafe = new StockArticulosPrueba("CAFÉ", 5.00, true, 200);
        // StockArticulosPrueba.inventario.add(cafe);

        // StockArticulosPrueba aceite = new StockArticulosPrueba("ACEITE", 3.50, false, 210);
        // StockArticulosPrueba.inventario.add(aceite);

        // StockArticulosPrueba chocolate = new StockArticulosPrueba("CHOCOLATE", 2.00, false, 220);
        // StockArticulosPrueba.inventario.add(chocolate);

        // StockArticulosPrueba galletas = new StockArticulosPrueba("GALLETAS", 1.30, false, 200);
        // StockArticulosPrueba.inventario.add(galletas);

        // StockArticulosPrueba agua = new StockArticulosPrueba("AGUA", 0.60, false, 230);
        // StockArticulosPrueba.inventario.add(agua);

        ventana();
    }

    public static void ventana() {
        StringBuilder productosTXT = new StringBuilder("");
        JFrame frame = new JFrame("Almacen LIDL");
        frame.setSize(1000, 1000);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints posicion = new GridBagConstraints();
        posicion.fill = GridBagConstraints.BOTH; // Rellenar vertical y horizontalmente
        posicion.weightx = 1; // Permitir que los componentes se expandan horizontalmente
        posicion.weighty = 1; // Permitir que los componentes se expandan verticalmente

        JPanel tituloLIDL = new JPanel();
        JPanel botones = new JPanel();
        JPanel productos = new JPanel();

        panelTitulo(tituloLIDL);
        panelProductos(productos, productosTXT);
        panelBotones(frame, botones, productos, productosTXT);

        posicion.gridy = 0;
        panelPrincipal.add(tituloLIDL, posicion);

        posicion.gridy = 1;
        panelPrincipal.add(botones, posicion);

        posicion.gridy = 2;
        panelPrincipal.add(productos, posicion);

        frame.add(panelPrincipal, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void panelTitulo(JPanel tituloLIDL) {
        tituloLIDL.setBackground(Color.BLUE);
        JLabel titulArticulos = new JLabel("L I D L");
        titulArticulos.setFont(new Font("Arial", Font.BOLD, 55));
        titulArticulos.setForeground(Color.YELLOW);
        titulArticulos.setAlignmentX(Component.CENTER_ALIGNMENT);
        tituloLIDL.add(titulArticulos);
    }

    public static void panelProductos(JPanel productos, StringBuilder productosTXT) {
        productos.setBackground(Color.LIGHT_GRAY);
        productos.setLayout(new BoxLayout(productos, BoxLayout.Y_AXIS));
        productos.setBorder(new EmptyBorder(5, 10, 7, 10));
        DecimalFormat decimales = new DecimalFormat("0.00");
        for (int i = 0; i < StockArticulosPrueba.inventario.size(); i++) {
            StockArticulosPrueba articulo = StockArticulosPrueba.inventario.get(i);
            String producto = articulo.getNombre();
            double precio = articulo.getPrecio();
            String precioFormateado = decimales.format(precio);
            int stock = articulo.getCantidad();

            JLabel labelProductos = new JLabel(
                    "Articulo " + (i+1) + " >  " + producto + ": " + precioFormateado + "€ / " + stock
                            + " unidades en stock");
            productosTXT.append(
                    "Articulo: '" + producto + "'\n\t" + precioFormateado + "€(EUROS)\n\t" + stock
                            + " unidades en stock\n");
            productos.add(labelProductos);
            productos.add(Box.createVerticalStrut(5)); // Agregar relleno vertical entre los componentes
        }
    }

    public static void panelBotones(JFrame frame, JPanel botones, JPanel productos, StringBuilder productosTXT) {
        botones.setLayout(new GridLayout(0, 4));
        botones.setBackground(Color.GRAY);

        JButton anadir = new JButton(escalarImagen("Iconos\\paquetes.png"));
        modificarBoton(anadir);
        JButton comprar = new JButton(escalarImagen("Iconos\\carrito-compra.png"));
        modificarBoton(comprar);
        JButton lista = new JButton(escalarImagen("Iconos\\lista-colores.png"));
        modificarBoton(lista);
        JButton lupa = new JButton(escalarImagen("Iconos\\buscar-compra-escalada.png"));
        modificarBoton(lupa);

        accionesLista(lista, productosTXT);
        accionesGestionStock(frame, anadir, productos, productosTXT);
        accionesComprar(frame, comprar, productos, productosTXT);
        accionesLupa(lupa);

        botones.add(comprar);
        botones.add(anadir);
        botones.add(lista);
        botones.add(lupa);
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


    //METODO ACCIONESBUSCAR

    public static void accionesLupa(JButton lupa) {
        lupa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entradaDeTexto = String.valueOf(JOptionPane.showInputDialog(null, "¿Qué artículo busca?", "Búsqueda de artículos", JOptionPane.QUESTION_MESSAGE));
                StringBuilder productosCoincidentes = new StringBuilder("PRODUCTOS COINCIDENTES\n\n");
                AtomicBoolean hayCoincidentes = new AtomicBoolean(false);
                StockArticulosPrueba.buscarArticulos(entradaDeTexto, productosCoincidentes, hayCoincidentes);
                if (hayCoincidentes.get()) {
                    JOptionPane.showMessageDialog(null, productosCoincidentes.toString(), "Artículos coincidentes", JOptionPane.PLAIN_MESSAGE);                    
                } else {
                    JOptionPane.showMessageDialog(null, "No se han encontrado artíclos", "error 404", 0);
                }
            }
        });
    }

    public static void accionesLista(JButton lista, StringBuilder productosTXT) {
        lista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(productosTXT.toString());
            }
        });
    }

    public static void accionesGestionStock(JFrame frame, JButton anadir, JPanel productos, StringBuilder productosTXT) {
        anadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog gestion = new JDialog();
                gestion.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                gestion.setTitle("Gestión del stock");
                gestion.setModal(true);

                JPanel panelOpcionesGestion = new JPanel(new GridBagLayout());
                panelOpcionesGestion.setBackground(Color.GRAY);
                GridBagConstraints posicion = new GridBagConstraints();
                posicion.fill = GridBagConstraints.BOTH;
                posicion.weightx = 1;
                posicion.weighty = 1;

                posicion.gridy = 0;
                JLabel selecciona = new JLabel("GESTIÓN DE PRODUCTOS");
                selecciona.setFont(new Font("Arial", Font.PLAIN, 20));
                selecciona.setHorizontalAlignment(SwingConstants.CENTER);
                selecciona.setForeground(Color.BLACK);

                posicion.gridx = 0;
                posicion.anchor = GridBagConstraints.CENTER;
                posicion.gridwidth = GridBagConstraints.REMAINDER;
                panelOpcionesGestion.add(selecciona, posicion);

                JPanel panelBotones = new JPanel(new GridLayout(0, 2));
                panelBotones.setBackground(null);
                JButton agregar = new JButton(escalarImagen("Iconos\\agregar.png"));
                modificarBoton(agregar);
                agregar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gestion.dispose();

                        panelAgregarArticulo(frame, productos, productosTXT);
                    }
                });
                panelBotones.add(agregar);

                JButton eliminar = new JButton(escalarImagen("Iconos\\eliminar.png"));
                modificarBoton(eliminar);
                eliminar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gestion.dispose();

                        panelEliminarArticulos(frame, productos, productosTXT);
                    }
                });
                panelBotones.add(eliminar);

                posicion.gridy = 1;
                panelOpcionesGestion.add(panelBotones, posicion);

                gestion.add(panelOpcionesGestion);

                gestion.pack();
                gestion.setLocationRelativeTo(null);
                gestion.setVisible(true);
            }
        });
    }

    public static void panelEliminarArticulos(JFrame frame, JPanel productos, StringBuilder productosTXT) {
        Color color1 = new Color(Color.DARK_GRAY.getRed()+25, Color.DARK_GRAY.getGreen()+25, Color.DARK_GRAY.getBlue()+25);
        Color color2 = new Color(Color.DARK_GRAY.getRed()+50, Color.DARK_GRAY.getGreen()+50, Color.DARK_GRAY.getBlue()+50);
        Color color3 = new Color(Color.DARK_GRAY.getRed()+100, Color.DARK_GRAY.getGreen()+100, Color.DARK_GRAY.getBlue()+100);

        JDialog dialogo = new JDialog(frame, "Eliminar artículos", true);
        dialogo = parametrosDialog(dialogo, productos, color1, color2, color3, frame, productosTXT);
    }

    public static JDialog parametrosDialog(JDialog dialogo, JPanel productos, Color color1, Color color2, Color color3, JFrame frame, StringBuilder productosTXT) {
        dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panelArticulos = new JPanel();
        panelArticulos.setLayout(new GridBagLayout());
        panelArticulos.setBackground(Color.BLACK);

        GridBagConstraints posicion = new GridBagConstraints();
        posicion.fill = GridBagConstraints.BOTH;

        panelArticulos = parametrosPanelArticulos(panelArticulos, productos, posicion, color1, color2, color3, frame, productosTXT);

        dialogo.add(panelArticulos);

        dialogo.pack();
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);

        return dialogo;
    }

    public static JPanel parametrosPanelArticulos(JPanel panelArticulos, JPanel productos, GridBagConstraints posicion, Color color1, Color color2, Color color3, JFrame frame, StringBuilder productosTXT) {
        String texto = "Nombre del artículo, Cantidad en stock";
        JLabel ejemploTexto = parametrosLabel(texto, color1);
 
        String textoCheck = "X cantidad";
        JPanel check1 = new JPanel();
        check1 = parametrosPanelCheck(check1, color1, textoCheck);

        String CheckTodos = "Eliminar artículo";
        JPanel check2 = new JPanel();
        check2 = parametrosPanelCheck(check2, color1, CheckTodos);

        JLabel eliminarTxt = new JLabel("Eliminar");
        JPanel eliminarPanel = new JPanel();
        eliminarPanel.setLayout(new BoxLayout(eliminarPanel, BoxLayout.Y_AXIS));
        eliminarPanel.setBackground(color1);
        eliminarPanel.add(eliminarTxt);
        eliminarPanel.setBorder(new EmptyBorder(4, 4, 4, 4));
        eliminarPanel.setOpaque(true); 

        posicion.insets = new Insets(4, 4, 4, 2); // Añadir un espacio entre componentes de un GridBagLayout

        posicion.gridy = 0;
        posicion.gridx = 0;
        panelArticulos.add(ejemploTexto, posicion);
        posicion.insets = new Insets(4, 1, 4, 1);
        posicion.gridx = 1;
        panelArticulos.add(check1, posicion);
        posicion.insets = new Insets(4, 1, 4, 4);
        posicion.gridx = 2;
        panelArticulos.add(check2, posicion);

        int indices[] = new int[1];

        for (int i = 0; i < StockArticulosPrueba.inventario.size(); i++) {
            int posicionVertical = i + 1;
            indices[0] = i;

            JPanel panelTxt = new JPanel();
            panelTxt.setLayout(new BoxLayout(panelTxt, BoxLayout.Y_AXIS));
            panelTxt.setBackground(color2);
            panelTxt.setAlignmentX(Component.LEFT_ALIGNMENT);
            String nombre = StockArticulosPrueba.inventario.get(i).getNombre();
            int cantidad = StockArticulosPrueba.inventario.get(i).getCantidad();
            String articulo_cantidad = nombre + ", con " + cantidad + " en el almacen";
            JLabel textoArticulos = parametrosLabel(articulo_cantidad, color2);
            panelTxt.add(textoArticulos);

            JPanel checkXSuprimir = new JPanel();
            checkXSuprimir.setBackground(color3);
            JTextField cantidadTxt = new JTextField(10);
            checkXSuprimir.add(cantidadTxt);

            JPanel checkTodosSuprimir = new JPanel();
            checkTodosSuprimir.setBackground(color3);
            JCheckBox eliminarArticulo = new JCheckBox();
            eliminarArticulo.setBackground(color3);
            eliminarArticulo.setHorizontalAlignment(SwingConstants.CENTER);
            checkTodosSuprimir.add(eliminarArticulo);

            JButton suprimir = new JButton("Eliminar");
            suprimir.setBackground(color2);
            suprimir.setForeground(Color.BLACK);
            suprimir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int indice = StockArticulosPrueba.indiceProducto(nombre);
                    if (eliminarArticulo.isSelected()) {
                        StockArticulosPrueba.inventario.remove(indice);
                        cantidadTxt.setText("");
                        eliminarArticulo.setSelected(false);
                        actualizarProductos(frame, productos, productosTXT);
                        repintarPanelBorrar(indices, panelArticulos, productos, posicion, color1, color2, color3, frame, productosTXT);
                    } else if (!eliminarArticulo.isSelected() && !cantidadTxt.getText().isEmpty()) {
                        String can = cantidadTxt.getText();
                        int cantidadInsertada = Integer.parseInt(can);
                        int cantidad = StockArticulosPrueba.inventario.get(indice).getCantidad();
                        cantidadTxt.setText("");
                        eliminarArticulo.setSelected(false);
                        if (cantidadInsertada < cantidad) {
                            StockArticulosPrueba.inventario.get(indice).setCantidad(cantidad - cantidadInsertada);
                            actualizarProductos(frame, productos, productosTXT);
                            repintarPanelBorrar(indices, panelArticulos, productos, posicion, color1, color2, color3, frame, productosTXT);
                        }
                    }
                }
            });

            posicion.insets = new Insets(0, 4, 3, 2);

            posicion.gridy = posicionVertical;
            posicion.gridx = 0;
            panelArticulos.add(panelTxt, posicion);
            posicion.insets = new Insets(0, 1, 3, 1);
            posicion.gridx = 1;
            panelArticulos.add(checkXSuprimir, posicion);
            posicion.insets = new Insets(0, 1, 3, 1);
            posicion.gridx = 2;
            panelArticulos.add(checkTodosSuprimir, posicion);
            posicion.insets = new Insets(0, 1, 3, 4);
            posicion.gridx = 3;
            panelArticulos.add(suprimir, posicion);
        }

        return panelArticulos;
    }

    public static void repintarPanelBorrar(int indices[], JPanel panelArticulos, JPanel productos, GridBagConstraints posicion, Color color1, Color color2, Color color3, JFrame frame, StringBuilder productosTXT) {
        panelArticulos.setVisible(false);
        panelArticulos.removeAll();
        
        for (int i = 0; i < StockArticulosPrueba.inventario.size(); i++) {
            int posicionVertical = i + 1;
            indices[0] = i;

            JPanel panelTxt = new JPanel();
            panelTxt.setLayout(new BoxLayout(panelTxt, BoxLayout.Y_AXIS));
            panelTxt.setBackground(color2);
            panelTxt.setAlignmentX(Component.LEFT_ALIGNMENT);
            String nombre = StockArticulosPrueba.inventario.get(i).getNombre();
            int cantidad = StockArticulosPrueba.inventario.get(i).getCantidad();
            String articulo_cantidad = nombre + ", con " + cantidad + " en el almacen";
            JLabel textoArticulos = parametrosLabel(articulo_cantidad, color2);
            panelTxt.add(textoArticulos);

            JPanel checkXSuprimir = new JPanel();
            checkXSuprimir.setBackground(color3);
            JTextField cantidadTxt = new JTextField(10);
            checkXSuprimir.add(cantidadTxt);

            JPanel checkTodosSuprimir = new JPanel();
            checkTodosSuprimir.setBackground(color3);
            JCheckBox eliminarArticulo = new JCheckBox();
            eliminarArticulo.setBackground(color3);
            eliminarArticulo.setHorizontalAlignment(SwingConstants.CENTER);
            checkTodosSuprimir.add(eliminarArticulo);

            JButton suprimir = new JButton("Eliminar");
            suprimir.setBackground(color2);
            suprimir.setForeground(Color.BLACK);
            suprimir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int indice = StockArticulosPrueba.indiceProducto(nombre);
                    if (eliminarArticulo.isSelected()) {
                        StockArticulosPrueba.inventario.remove(indice);
                        actualizarProductos(frame, productos, productosTXT);
                    } else if (!eliminarArticulo.isSelected() && !cantidadTxt.getText().isEmpty()) {
                        String can = cantidadTxt.getText();
                        int cantidadInsertada = Integer.parseInt(can);
                        int cantidad = StockArticulosPrueba.inventario.get(indice).getCantidad();
                        if (cantidadInsertada < cantidad) {
                            StockArticulosPrueba.inventario.get(indice).setCantidad(cantidad - cantidadInsertada);
                            actualizarProductos(frame, productos, productosTXT);
                        }
                    }
                }
            });

            posicion.insets = new Insets(0, 4, 3, 2);

            posicion.gridy = posicionVertical;
            posicion.gridx = 0;
            panelArticulos.add(panelTxt, posicion);
            posicion.insets = new Insets(0, 1, 3, 1);
            posicion.gridx = 1;
            panelArticulos.add(checkXSuprimir, posicion);
            posicion.insets = new Insets(0, 1, 3, 1);
            posicion.gridx = 2;
            panelArticulos.add(checkTodosSuprimir, posicion);
            posicion.insets = new Insets(0, 1, 3, 4);
            posicion.gridx = 3;
            panelArticulos.add(suprimir, posicion);

            panelArticulos.revalidate();
            panelArticulos.repaint();
            panelArticulos.setVisible(true);
        }
    }

    public static JPanel parametrosPanelCheck(JPanel check, Color color, String texto1) {
        check.setLayout(new GridLayout(2, 0));
        check.setBackground(color);

        JLabel check1 = parametrosLabel(texto1, color);
        check1.setOpaque(false);

        check.add(check1);
        check.setBorder(new EmptyBorder(4, 4, 4, 4));
        check.setOpaque(true); 

        return check;
    }

    public static JLabel parametrosLabel(String texto, Color color) {
        JLabel ejemploTexto = new JLabel(texto);
        ejemploTexto.setBackground(color);
        ejemploTexto.setHorizontalAlignment(SwingConstants.CENTER);
        ejemploTexto.setBorder(new EmptyBorder(2, 2, 2, 4));
        ejemploTexto.setForeground(Color.BLACK);
        ejemploTexto.setOpaque(true);

        return ejemploTexto;
    }

    public static void panelAgregarArticulo(JFrame frame, JPanel productos, StringBuilder productosTXT) {
        JPanel panelIndicaCantidad = new JPanel(new GridLayout(0, 2));

        panelIndicaCantidad.add(new JLabel("Cantidad de productos a agregar: "));
        JTextField cuantosArticulos = new JTextField(5);
        panelIndicaCantidad.add(cuantosArticulos);

        int respuesta = JOptionPane.showConfirmDialog(null, panelIndicaCantidad, "Ingrese la cantidad de artículos a agregar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        boolean error = false;
        int qty = 0;
        try {
            qty = Integer.parseInt(cuantosArticulos.getText());
            error = false;
        } catch (Exception et) {
            error = true; 
        }  

        if (respuesta == JOptionPane.CANCEL_OPTION || respuesta == JOptionPane.CLOSED_OPTION || error) {
            JOptionPane.showMessageDialog(null, "No se ha introducido una cantidad válida", "ERROR 404", JOptionPane.ERROR_MESSAGE);
        } else {
            for (int i = 1; i <= qty; i++) {
                JPanel infoArticulos = new JPanel(new GridLayout(0, 2));

                infoArticulos.add(new JLabel("Nombre del producto: "));
                JTextField productoTxt = new JTextField(10);
                infoArticulos.add(productoTxt);

                infoArticulos.add(new JLabel("Precio del producto: "));
                JTextField precioTxt = new JTextField(10);
                infoArticulos.add(precioTxt);

                infoArticulos.add(new JLabel("Cantidad:"));
                JTextField cantidadTxt = new JTextField(10);
                infoArticulos.add(cantidadTxt);

                infoArticulos.add(new JLabel("¿Artículo esencial?"));
                JCheckBox articuloEsencialCheck = new JCheckBox();
                infoArticulos.add(articuloEsencialCheck);

                int opcion = JOptionPane.showConfirmDialog(null, infoArticulos, "Características artículo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (opcion != JOptionPane.CANCEL_OPTION && opcion != JOptionPane.CLOSED_OPTION) {
                    boolean esencial = articuloEsencialCheck.isSelected() ? true : false;
                    String producto = "";
                    double precio = 0.0;
                    int cantidad = 0;

                    StockArticulosPrueba.agregarArticulo(producto, precio, cantidad, productoTxt, precioTxt, cantidadTxt, esencial);
                } else {
                    break;
                }
            }
            actualizarProductos(frame, productos, productosTXT);
        }
    }

    public static void accionesComprar(JFrame frame, JButton comprar, JPanel productos, StringBuilder productosTXT) {
        ArrayList<String> arrayProductos = new ArrayList<>();
        HashMap<String, Integer> cesta = new HashMap<>();
        ArrayList<Double> precios = new ArrayList<>();
        DecimalFormat dosDecimales = new DecimalFormat("0.00");
        StringBuilder ticket = new StringBuilder("");

        comprar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ticket.setLength(0);
                ticket.append("TICKET !");
                int qty = 0;
                
                //Cambiar texto predeterminado a los botones del JOptionPane.YES_NO_CANCEL_OPTION
                UIManager.put("OptionPane.yesButtonText", "Sí");
                UIManager.put("OptionPane.noButtonText", "No");

                int respuesta = JOptionPane.showConfirmDialog(null, "¿Quieres definir la longitud de tu compra?", "Cesta Lidl", JOptionPane.YES_NO_CANCEL_OPTION);

                boolean error = false;
                if (respuesta == JOptionPane.CANCEL_OPTION || respuesta == JOptionPane.CLOSED_OPTION) {
                    JOptionPane.showMessageDialog(null, "Operación cancelada", "Error 404", JOptionPane.ERROR_MESSAGE);
                } else if (respuesta == JOptionPane.NO_OPTION) {
                    rellenarArray(arrayProductos);

                    panelesGenerativos(error, dosDecimales, ticket, cesta, arrayProductos, precios);
                    if (!error) {
                        StockArticulosPrueba.crearTicket(frame, cesta, dosDecimales, ticket, precios);
                        cesta.clear();
                        arrayProductos.clear();
                        precios.clear();
                    }
                } else if (respuesta == JOptionPane.YES_OPTION) {
                    rellenarArray(arrayProductos);

                    qty = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de articulos a comprar", "Comprar varios articulos", JOptionPane.QUESTION_MESSAGE));
                    if (qty > arrayProductos.size()) {
                        JOptionPane.showInputDialog(null, "No tenemos tanta variedad de productos, cantidad máxima posible: "+arrayProductos.size(), "Error 404", JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (int i = 1; i <= qty; i++) {
                            panelUnicoCompra(error, dosDecimales, ticket, cesta, arrayProductos, precios);
                            if (error) {
                                break;
                            }
                        }
                        if (!error) {
                            StockArticulosPrueba.crearTicket(frame, cesta, dosDecimales, ticket, precios);
                            cesta.clear();
                            arrayProductos.clear();
                            precios.clear();
                        }
                    }
                }

                actualizarProductos(frame, productos, productosTXT);
            }
        });
    }

    public static ArrayList<String> rellenarArray(ArrayList<String> arrayProductos) {
        for (StockArticulosPrueba producto : StockArticulosPrueba.inventario) {
            arrayProductos.add(producto.getNombre());
        }
        return arrayProductos;
    }

    public static void panelUnicoCompra(boolean error, DecimalFormat dosDecimales, StringBuilder ticket, HashMap<String, Integer> cesta, ArrayList<String> arrayProductos, ArrayList<Double> precios) {
        JPanel panelUnico = new JPanel(new GridLayout(0, 2));

        panelUnico.add(new JLabel("Nombre del producto: "));
        JComboBox<String> seleccionable = crearSeleccionable(arrayProductos);
        panelUnico.add(seleccionable);

        JLabel textoPrecioPanel = new JLabel("Precio   /+IVA            ➟");
        panelUnico.add(textoPrecioPanel);
        JLabel precioPanel = new JLabel("0.0   /0.0");
        panelUnico.add(precioPanel);
        // Hacer que muestre el precio del artículo seleccionado según se selecciona
        mostrarPrecio(dosDecimales, seleccionable, precioPanel);

        panelUnico.add(new JLabel("Cantidad:"));
        JTextField cantidadTxt = new JTextField(10);
        panelUnico.add(cantidadTxt);

        while (true) {
            int respuesta = JOptionPane.showConfirmDialog(null, panelUnico, "Compra de artículo", JOptionPane.OK_CANCEL_OPTION);

            error = erroresPanelesCompra(error, respuesta, seleccionable, cantidadTxt);

            if (respuesta == JOptionPane.CANCEL_OPTION || respuesta == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Operación cancelada", "Error 404", JOptionPane.ERROR_MESSAGE);
                break;
            } else if (!error) {
                String productoStr = (String) seleccionable.getSelectedItem();
                int cantidad = Integer.parseInt(cantidadTxt.getText());
                cesta.put(productoStr, cantidad);
                StockArticulosPrueba producto = buscar(productoStr);
                int stockProducto = producto.getCantidad();
                stockProducto -= cantidad;
                producto.setCantidad(stockProducto);
                arrayProductos.remove(productoStr);
                
                ticket.append("Artículo: \"" + productoStr + "\"!    Cantidad comprada: " + cantidad + "!");
                precio_precioCantidad(ticket, dosDecimales, productoStr, cantidad, precios);
                break;
            }
        }
    }
    
    public static void panelesGenerativos(boolean error, DecimalFormat dosDecimales, StringBuilder ticket, HashMap<String, Integer> cesta, ArrayList<String> arrayProductos, ArrayList<Double> precios) {
        boolean masArticulos = true;
        while (!error && masArticulos) {
            if (arrayProductos.size() == 0) {
                JOptionPane.showMessageDialog(null, "¡ ¡ ÚLTIMO ARTÍCULO ! !", "Máximo de productos", JOptionPane.WARNING_MESSAGE);
                break;
            }
            JPanel panelComprar = new JPanel(new GridLayout(0, 2));

            panelComprar.add(new JLabel("Nombre del producto: "));
            JComboBox<String> seleccionable = crearSeleccionable(arrayProductos);
            panelComprar.add(seleccionable);

            JLabel textoPrecioPanel = new JLabel("Precio   /+IVA            ➟");
            JLabel precioPanel = new JLabel("0.0   /0.0");
            
            // Hacer que muestre el precio del artículo seleccionado según se selecciona
            mostrarPrecio(dosDecimales, seleccionable, precioPanel);

            panelComprar.add(textoPrecioPanel);
            panelComprar.add(precioPanel);

            panelComprar.add(new JLabel("Cantidad:"));
            JTextField cantidadTxt = new JTextField(10);
            panelComprar.add(cantidadTxt);

            panelComprar.add(new JLabel("¿Más articulos?"));
            JCheckBox masArticulosCheck = new JCheckBox();
            panelComprar.add(masArticulosCheck);

            while (true) {
                int confirmacion = JOptionPane.showConfirmDialog(null, panelComprar, "Compra de artículo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                masArticulos = masArticulosCheck.isSelected();
    
                error = erroresPanelesCompra(error, confirmacion, seleccionable, cantidadTxt);
                if (confirmacion == JOptionPane.CANCEL_OPTION || confirmacion == JOptionPane.CLOSED_OPTION) {
                    JOptionPane.showMessageDialog(null, "Operación cancelada", "Error 404", JOptionPane.ERROR_MESSAGE);
                    break;
                } else if (!error) {
                    String productoStr = (String) seleccionable.getSelectedItem();
                    int cantidad = Integer.parseInt(cantidadTxt.getText());
                    cesta.put(productoStr, cantidad);
                    StockArticulosPrueba producto = buscar(productoStr);
                    int stockProducto = producto.getCantidad();
                    stockProducto -= cantidad;
                    producto.setCantidad(stockProducto);
                    arrayProductos.remove(productoStr);
                    
                    ticket.append("Artículo: \"" + productoStr + "\"!    Cantidad comprada: " + cantidad + "!");
                    precio_precioCantidad(ticket, dosDecimales, productoStr, cantidad, precios);
                    break;
                }
            }
        }
    }

    public static boolean erroresPanelesCompra(boolean error, int confirmacion, JComboBox<String> seleccionable, JTextField cantidadTxt) {
        if (confirmacion != JOptionPane.CANCEL_OPTION || confirmacion != JOptionPane.CLOSED_OPTION) {
            String productoStr = (String) seleccionable.getSelectedItem();
            if (productoStr != null && !productoStr.equals("- Seleccionar producto -")) {
                Policias poliCantidad = new Policias(cantidadTxt, "enteros");
                if (poliCantidad.getBoolean()) {
                    int cantidad = Integer.parseInt(cantidadTxt.getText());
                    StockArticulosPrueba producto = buscar(productoStr);
                    if (cantidad > producto.getCantidad()) {
                        JOptionPane.showMessageDialog(null, "El stock del producto es de " + producto.getCantidad()
                        + " y usted esta intentando comprar " + cantidad, "Error 404", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    } else {
                        error = false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La cantidad debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    error = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione un producto válido.", "Error", JOptionPane.ERROR_MESSAGE);
                error = true;
            }
        } else {
            error = true;
        }
        return error;
    }

    public static JComboBox<String> crearSeleccionable(ArrayList<String> inventario) {
        String[] productos = new String[(inventario.size() + 1)];
        productos[0] = "- Seleccionar producto -";

        for (int i = 0; i < inventario.size(); i++) {
            String producto = inventario.get(i);
            productos[(i+1)] = producto;
        }
        JComboBox<String> seleccionable = new JComboBox<>(productos);

        return seleccionable;
    }

    public static void mostrarPrecio(DecimalFormat dosDecimales, JComboBox<String> seleccionable, JLabel precioPanel) {
        seleccionable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreProducto = (String) seleccionable.getSelectedItem();
                StockArticulosPrueba producto = buscar(nombreProducto);

                double precio = producto.getPrecio();
                String precioFormateado = dosDecimales.format(precio);
                double iva = producto.getIVA();
                double precioIVA = precio * iva;
                String precioConIva = dosDecimales.format(precioIVA);
                precioPanel.setText(precioFormateado + "€   /"+precioConIva+"€");
            }
        });
    }

    public static void precio_precioCantidad(StringBuilder ticket, DecimalFormat dosDecimales, String productoStr, int cantidad, ArrayList<Double> precios) {
        StockArticulosPrueba articulo = buscar(productoStr);
        double precio = articulo.getPrecio();
        double iva = articulo.getIVA();
        double precioIVA = precio * iva;
        double precioCantidad = precioIVA * cantidad;
        precios.add(precioCantidad);
        ticket.append("    PrecioIVA  /* Cantidad: "+ dosDecimales.format(precioIVA) +"€   / " + dosDecimales.format(precioCantidad)+"€!");
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

    public static void actualizarProductos(JFrame frame, JPanel productos, StringBuilder productosTXT) {
        frame.setVisible(false);
        frame.setSize(1000, 1000);
        productos.removeAll();
        DecimalFormat decimales = new DecimalFormat("0.00");
        for (int i = 0; i < StockArticulosPrueba.inventario.size(); i++) {
            StockArticulosPrueba articulo = StockArticulosPrueba.inventario.get(i);
                if (i <= 20) {
                    String producto = articulo.getNombre();
                    double precio = articulo.getPrecio();
                    String precioFormateado = decimales.format(precio);
                    int stock = articulo.getCantidad();


                    JLabel labelProductos = new JLabel(
                            "Articulo " + (i+1) + " >  " + producto + ": " + precioFormateado + "€ / " + stock
                                    + " unidades en stock");
                    labelProductos.setForeground(Color.BLACK);
                    productosTXT.append(
                            "Articulo: '" + producto + "'\n\t" + precioFormateado + "€(EUROS)\n\t" + stock
                                    + " unidades en stock\n");
                    productos.add(labelProductos);
                    productos.add(Box.createVerticalStrut(5));
                } else {
                    JOptionPane.showMessageDialog(null, "Has llegado al máximo de artículos posible");
                }
            }
            productos.revalidate();
            productos.repaint();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}