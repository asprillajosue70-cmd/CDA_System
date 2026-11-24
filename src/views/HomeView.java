package views;

import javax.swing.*;
import java.awt.*;

import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import models.Usuario;
import config.AppDependencies;

public class HomeView extends JFrame {

    public HomeView(Usuario usuario, AppDependencies dependencies) {

        // Configuración de la ventana
        setTitle("CDA_System - Panel Principal");
        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Administrador de las diferentes vistas
        CardLayout cardLayout = new CardLayout();

        // Panel que contiene las vistas
        JPanel contentPanel = new JPanel(cardLayout);

        // Crear las vistas del sistema
        HomePanel homePanel = new HomePanel(usuario);

        // Agregar las vistas al administrador
        contentPanel.add(homePanel, "home");
        
        OwnersPanel ownersPanel = new OwnersPanel(
            dependencies.propietarioService
        );

        contentPanel.add(ownersPanel, "owners");

        // Panel izquierdo (menú de navegación)
        JPanel leftPanel = new JPanel();

        leftPanel.setBackground(new Color(11, 50, 101));
        leftPanel.setPreferredSize(new Dimension(250, 0));

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // Título del sistema
        JLabel titleLabel = new JLabel("CDA_System");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Opciones del menú
        JButton homeButton = new JButton("Inicio");
        homeButton.setIcon( FontIcon.of(FontAwesomeSolid.HOME, 20, Color.WHITE));
        homeButton.setHorizontalAlignment(SwingConstants.LEFT);
        homeButton.setIconTextGap(10);

        JButton ownersButton = new JButton("Propietarios");
        ownersButton.setIcon( FontIcon.of(FontAwesomeSolid.USERS, 20, Color.WHITE));
        ownersButton.setHorizontalAlignment(SwingConstants.LEFT);
        ownersButton.setIconTextGap(10);

        JButton vehiclesButton = new JButton("Vehículos");
        vehiclesButton.setIcon( FontIcon.of(FontAwesomeSolid.CAR, 20, Color.WHITE));
        vehiclesButton.setHorizontalAlignment(SwingConstants.LEFT);
        vehiclesButton.setIconTextGap(10);

        JButton finesButton = new JButton("Comparendos");
        finesButton.setIcon( FontIcon.of(FontAwesomeSolid.EXCLAMATION_TRIANGLE, 20, Color.WHITE));
        finesButton.setHorizontalAlignment(SwingConstants.LEFT);
        finesButton.setIconTextGap(10);

        JButton appointmentsButton = new JButton("Citas");
        appointmentsButton.setIcon( FontIcon.of(FontAwesomeSolid.CALENDAR_ALT, 20, Color.WHITE));
        appointmentsButton.setHorizontalAlignment(SwingConstants.LEFT);
        appointmentsButton.setIconTextGap(10);

        JButton queriesButton = new JButton("Consultas");
        queriesButton.setIcon( FontIcon.of(FontAwesomeSolid.QUESTION_CIRCLE, 20, Color.WHITE));
        queriesButton.setHorizontalAlignment(SwingConstants.LEFT);
        queriesButton.setIconTextGap(10);

        // Navegación entre las diferentes vistas

        homeButton.addActionListener(e -> {
            cardLayout.show(contentPanel, "home");
        });

        ownersButton.addActionListener(e -> {
            cardLayout.show(contentPanel, "owners");
        });

        styleMenuButton(homeButton);
        styleMenuButton(ownersButton);
        styleMenuButton(vehiclesButton);
        styleMenuButton(finesButton);
        styleMenuButton(appointmentsButton);
        styleMenuButton(queriesButton);

        // Agregar componentes al panel izquierdo
        leftPanel.add(Box.createVerticalStrut(35));
        leftPanel.add(titleLabel);
        leftPanel.add(Box.createVerticalStrut(50));

        leftPanel.add(homeButton);
        leftPanel.add(Box.createVerticalStrut(10));

        leftPanel.add(ownersButton);
        leftPanel.add(Box.createVerticalStrut(10));

        leftPanel.add(vehiclesButton);
        leftPanel.add(Box.createVerticalStrut(10));

        leftPanel.add(finesButton);
        leftPanel.add(Box.createVerticalStrut(10));

        leftPanel.add(appointmentsButton);
        leftPanel.add(Box.createVerticalStrut(10));

        leftPanel.add(queriesButton);

        // Agregar paneles al principal
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Establecer panel principal
        setContentPane(mainPanel);

    }


    private void styleMenuButton(JButton button) {

        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(11, 50, 101));

        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);

        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMaximumSize(new Dimension(220, 45));
        button.setPreferredSize(new Dimension(220, 45));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto al pasar el cursor
        button.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(30, 75, 130));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(11, 50, 101));
            }
        });
    }
}