package views;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {

    private int radius;
    private Color borderColor;

    public RoundedPanel(int radius, Color borderColor) {
        this.radius = radius;
        this.borderColor = borderColor;

        // Permitimos dibujar nuestra propia forma
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        // Suavizamos los bordes
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        // Dibujamos el fondo redondeado
        g2.setColor(getBackground());
        g2.fillRoundRect(
            0, 0, getWidth() - 1, getHeight() - 1,
            radius, radius
        );

        // Dibujamos el contorno
        g2.setColor(borderColor);
        g2.drawRoundRect(
            0, 0, getWidth() - 1, getHeight() - 1,
            radius, radius
        );

        g2.dispose();
    }
}
  
