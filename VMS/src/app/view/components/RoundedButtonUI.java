package app.view.components;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedButtonUI extends BasicButtonUI {
    private final Color backgroundColor;
    private final Color pressedColor;

    public RoundedButtonUI(Color backgroundColor, Color pressedColor) {
        this.backgroundColor = backgroundColor;
        this.pressedColor = pressedColor;
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        ((AbstractButton) c).setOpaque(false); // Make sure the button is not opaque
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton button = (AbstractButton) c;
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Enable anti-aliasing

        // Set the button's shape and background
        int width = button.getWidth();
        int height = button.getHeight();
        int arcSize = 30; // Radius for rounded corners

        if (button.getModel().isPressed()) {
            g2.setColor(pressedColor); // Change color when pressed
        } else {
            g2.setColor(backgroundColor); // Normal background color
        }

        g2.fill(new RoundRectangle2D.Float(0, 0, width, height, arcSize, arcSize));

        // Paint button's text and border
        super.paint(g2, c);

        g2.dispose();
    }

    @Override
    protected void paintText(Graphics g, JComponent c, Rectangle textRect, String text) {
        AbstractButton button = (AbstractButton) c;
        ButtonModel model = button.getModel();
        Graphics2D g2 = (Graphics2D) g.create();

        // Set white text color for the button
        g2.setColor(Color.WHITE);
        FontMetrics fm = g.getFontMetrics();
        int x = (button.getWidth() - fm.stringWidth(text)) / 2;
        int y = (button.getHeight() + fm.getAscent()) / 2 - 2;
        g2.drawString(text, x, y);

        g2.dispose();
    }
}
