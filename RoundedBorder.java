package DSA;

import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

class RoundedBorder implements Border {
    private int radius;

    RoundedBorder(int radius) {
        this.radius = radius;
    }

    public Insets getBorderInsets(java.awt.Component c) {
        return new Insets(this.radius, this.radius, this.radius, this.radius);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(c.getBackground());
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}
