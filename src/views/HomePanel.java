package views;

import javax.swing.*;
import java.awt.*;

import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import models.Usuario;

public class HomePanel extends JPanel {

    public HomePanel(Usuario usuario) {

        // Configuración del panel
        setBackground(new Color(240, 243, 248));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Mensaje de bienvenida
        JLabel welcomeLabel = new JLabel(
            "¡Bienvenido, " + usuario.getName() + "!"
        );

        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setForeground(new Color(11, 50, 101));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Descripción del panel
        JLabel descriptionLabel = new JLabel(
            "Panel de administración del Centro de Diagnóstico Automotor"
        );

        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionLabel.setForeground(Color.GRAY);
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Agregar componentes al panel
        add(Box.createVerticalStrut(30));
        add(welcomeLabel);
        add(Box.createVerticalStrut(15));
        add(descriptionLabel);
        add(Box.createVerticalStrut(30));

        // Panel de tarjetas informativas
        JPanel cardsPanel = new JPanel(new GridLayout(2, 2, 20, 20));

        cardsPanel.setBackground(new Color(240, 243, 248));

        cardsPanel.setPreferredSize(new Dimension(600, 350));
        cardsPanel.setMaximumSize(new Dimension(750, 350));

        // Crear tarjetas del dashboard
        JPanel vehiclesCard = createDashboardCard( "Vehículos registrados", "125",
        FontAwesomeSolid.CAR, new Color(59, 130, 246));
        
        JPanel ownersCard = createDashboardCard( "Propietarios registrados", "98",
        FontAwesomeSolid.USERS, new Color(16, 185, 129));

        JPanel appointmentsCard = createDashboardCard( "Citas registradas", "32",
        FontAwesomeSolid.CALENDAR_ALT, new Color(245, 158, 11));

        JPanel finesCard = createDashboardCard( "Comparendos pendientes", "14",
        FontAwesomeSolid.EXCLAMATION_TRIANGLE, new Color(239, 68, 68));
        
        // Agregar tarjetas al panel
        cardsPanel.add(vehiclesCard);
        cardsPanel.add(ownersCard);
        cardsPanel.add(appointmentsCard);
        cardsPanel.add(finesCard);

        // Agregar las tarjetas al panel principal
        add(Box.createVerticalStrut(30));
        add(cardsPanel);

    }

    private JPanel createDashboardCard( String title, String value, FontAwesomeSolid icon, Color accentColor ) {
        RoundedPanel card = new RoundedPanel(
            20,
            new Color(225, 230, 238)
        );

        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);

        // Espacio interno de la tarjeta
        card.setBorder(BorderFactory.createEmptyBorder(
            20, 20, 20, 20
        ));

        FontIcon cardIcon = FontIcon.of( icon, 24, accentColor);
        JLabel iconLabel = new JLabel(cardIcon);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        iconLabel.setForeground(accentColor);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        titleLabel.setForeground(Color.GRAY);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 30));
        valueLabel.setForeground(accentColor);

        card.add(iconLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(valueLabel);

        return card;
    }
}
