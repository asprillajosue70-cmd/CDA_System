package views;

import javax.swing.*;

import services.LoginResponse;
import config.AppDependencies;

import java.awt.*;

public class LoginView extends JFrame {

    private AppDependencies dependencies;

    public LoginView(AppDependencies dependencies) {
        this.dependencies = dependencies;

        // Configuración de la ventana
        setTitle("CDA_System - Inicio de Sesión");
        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel izquierdo
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(11, 50, 101));
        leftPanel.setPreferredSize(new Dimension(450, 0));
        
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // Cargar logo del sistema
        ImageIcon logo = new ImageIcon("resources/images/cda_logo.png");

        // Obtener imagen original
        Image imagen = logo.getImage();

        // Redimensionar imagen
        Image imagenRedimensionada = imagen.getScaledInstance(
            180, 180,
            Image.SCALE_SMOOTH
        );

        // Crear ImageIcon con la imagen redimensionada
        ImageIcon logoRedimensionado = new ImageIcon(imagenRedimensionada);

        // Cargar imagen del centro de inspección
        ImageIcon garage = new ImageIcon(
            "resources/images/cda_garage.png"
        );

        // Obtener imagen original
        Image garageImage = garage.getImage();

        // Redimensionar imagen
        Image garageResized = garageImage.getScaledInstance(
            350, 200,
            Image.SCALE_SMOOTH
        );

        // Crear ImageIcon redimensionado
        ImageIcon garageIcon = new ImageIcon(garageResized);

        // Nombre del sistema
        JLabel titleLabel = new JLabel("CDA_System");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));

        // Subtítulo
        JLabel subtitleLabel = new JLabel("Centro de Diagnóstico Automotor");
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        // Eslogan
        JLabel sloganLabel = new JLabel(
            "Seguridad en la vía, nuestro compromiso"
        );
        sloganLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sloganLabel.setForeground(Color.WHITE);
        sloganLabel.setFont(new Font("Arial", Font.ITALIC, 16));

        // Agregar componentes

        // Espacio flexible superior
        leftPanel.add(Box.createVerticalGlue());
        JLabel logoLabel = new JLabel(logoRedimensionado);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(logoLabel);

        JLabel garageLabel = new JLabel(garageIcon);
        garageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(garageLabel);

        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(titleLabel);
        leftPanel.add(Box.createVerticalStrut(15));
        leftPanel.add(subtitleLabel);
        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(sloganLabel);

        //Espacio flexible inferior
        leftPanel.add(Box.createVerticalGlue());




        // Panel derecho
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(240, 243, 248));

        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        // Título de bienvenida
        JLabel welcomeLabel = new JLabel("¡Bienvenido!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 32));
        welcomeLabel.setForeground(new Color(11, 50, 101));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Descripción
        JLabel descriptionLabel = new JLabel(
            "Ingresa tus credenciales para continuar"
        );
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionLabel.setForeground(Color.GRAY);
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Etiqueta del usuario
        JLabel usernameLabel = new JLabel("Nombre de usuario");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Campo de usuario
        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField.setMaximumSize(new Dimension(350, 40));

        //Campo de contraseña
        JLabel passwordLabel = new JLabel("Contraseña");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 15));
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setMaximumSize(new Dimension(350, 40));

        // Botón de inicio de sesión
        JButton loginButton = new JButton("Iniciar sesión");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(11, 50, 101));
        loginButton.setMaximumSize(new Dimension(350, 45));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginButton.addActionListener(e -> {

            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            LoginResponse response =
                dependencies.usuarioService.login(username, password);

            if (response.isSuccess()) {

                // Abrir el panel principal y enviar el usuario autenticado
                HomeView homeView = new HomeView(response.getUsuario(), dependencies);

                homeView.setVisible(true);

                // Cerrar la ventana de inicio de sesión
                dispose();

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    response.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });



        // Agregar componentes
        rightPanel.add(Box.createVerticalGlue());

        rightPanel.add(welcomeLabel);
        rightPanel.add(Box.createVerticalStrut(15));
        rightPanel.add(descriptionLabel);

        rightPanel.add(Box.createVerticalStrut(40));
        rightPanel.add(usernameLabel);
        rightPanel.add(Box.createVerticalStrut(8));
        rightPanel.add(usernameField);

        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.add(passwordLabel);
        rightPanel.add(Box.createVerticalStrut(8));
        rightPanel.add(passwordField);

        rightPanel.add(Box.createVerticalStrut(30));
        rightPanel.add(loginButton);

        rightPanel.add(Box.createVerticalGlue());

        // Agregar paneles a la ventana
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }
}

