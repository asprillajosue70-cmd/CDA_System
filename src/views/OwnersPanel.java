package views;

import javax.swing.*;
import javax.swing.table.TableRowSorter;

import models.Propietario;

import javax.swing.table.DefaultTableModel;

import services.PropietarioService;
import models.Propietario;

import java.awt.*;

public class OwnersPanel extends JPanel {

    private PropietarioService propietarioService;
    public OwnersPanel(PropietarioService propietarioService) {
        this.propietarioService = propietarioService;

        // Configuración del panel
        setBackground(new Color(240, 243, 248));
        setLayout(new BorderLayout());

        // Panel superior (encabezado)
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(240, 243, 248));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        // Título
        JLabel titleLabel = new JLabel("Gestión de propietarios");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(11, 50, 101));

        // Descripción
        JLabel descriptionLabel = new JLabel(
            "Administra la información de los propietarios registrados."
        );
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        descriptionLabel.setForeground(Color.GRAY);

        // Agregar componentes al encabezado
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(8));
        headerPanel.add(descriptionLabel);

        // Panel superior que contiene el encabezado y las acciones
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(240, 243, 248));

        // Agregar encabezado
        topPanel.add(headerPanel, BorderLayout.NORTH);

        // Panel de acciones
        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionsPanel.setBackground(new Color(240, 243, 248));

        // Botón para registrar propietarios
        JButton registerButton = new JButton("Registrar propietario");

        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(new Color(11, 50, 101));
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Agregar botón al panel de acciones
        actionsPanel.add(registerButton);

        // Agregar acciones al panel superior
        topPanel.add(actionsPanel, BorderLayout.SOUTH);

        // Agregar encabezado al panel principal
        add(topPanel, BorderLayout.NORTH);

        // Panel central (tabla de propietarios)
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(new Color(240, 243, 248));

        // Columnas de la tabla
        String[] columns = {
            "Nombre",
            "Documento",
            "Teléfono",
            "Correo electrónico"
        };

        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        // Crear tabla
        JTable ownersTable = new JTable(tableModel);

        // Obtener los propietarios registrados
        for (Propietario propietario : propietarioService.findAllOwners()) {

            tableModel.addRow(new Object[] {
                propietario.getName(),
                propietario.getDocument(),
                propietario.getPhone(),
                propietario.getEmail()
            });

        }

        // Personalización de la tabla
        ownersTable.setRowHeight(35);
        ownersTable.setFont(new Font("Arial", Font.PLAIN, 14));
        ownersTable.setForeground(new Color(40, 40, 40));
        ownersTable.setBackground(Color.WHITE);

        // Personalización del encabezado
        ownersTable.getTableHeader().setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        ownersTable.getTableHeader().setBackground(new Color(11, 50, 101));
        ownersTable.getTableHeader().setForeground(Color.WHITE);
        ownersTable.getTableHeader().setPreferredSize(new Dimension(0, 40));

        // Personalización de las líneas
        ownersTable.setGridColor(new Color(225, 230, 238));

        // Color de selección
        ownersTable.setSelectionBackground(new Color(219, 234, 254));
        ownersTable.setSelectionForeground(new Color(11, 50, 101));

        // Ocupar el espacio disponible
        ownersTable.setFillsViewportHeight(true);

        // Panel de búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(new Color(240, 243, 248));

        // Campo de búsqueda
        JTextField searchField = new JTextField(25);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Texto descriptivo
        JLabel searchLabel = new JLabel("Buscar por documento:");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Agregar componentes
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);

        // Agregar buscador al panel central
        tablePanel.add(searchPanel, BorderLayout.NORTH);

        // Agregar desplazamiento a la tabla
        JScrollPane scrollPane = new JScrollPane(ownersTable);

        // Modelo de ordenamiento y filtrado
        TableRowSorter<javax.swing.table.TableModel> sorter =
            new TableRowSorter<>(ownersTable.getModel());

        // Asignar el modelo a la tabla
        ownersTable.setRowSorter(sorter);

        // Detectar cambios en el campo de búsqueda
        searchField.getDocument().addDocumentListener(
            new javax.swing.event.DocumentListener() {

                private void filterTable() {
                    String text = searchField.getText().trim();

                    if (text.isEmpty()) {
                        sorter.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(
                            RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(text), 1)
                        );
                    }
                }

                @Override
                public void insertUpdate(javax.swing.event.DocumentEvent e) {
                    filterTable();
                }

                @Override
                public void removeUpdate(javax.swing.event.DocumentEvent e) {
                    filterTable();
                }

                @Override
                public void changedUpdate(javax.swing.event.DocumentEvent e) {
                    filterTable();
                }
            }
        );

        // Agregar tabla al panel central
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Agregar panel central al panel principal
        add(tablePanel, BorderLayout.CENTER);
        
        
    }

}