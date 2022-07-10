package script.gui.ui;

import javax.swing.*;
import java.awt.*;

public class JHintTextField extends JTextField
{
    public String hint;

    public JHintTextField(String hint)
    {
        this.hint = hint;
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        if (getText().length() == 0)
        {
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            int height = getHeight();
            Insets insets = getInsets();
            FontMetrics metrics = g.getFontMetrics();
            int background = getBackground().getRGB();
            int foreground = getForeground().getRGB();
            int mask = 0xfefefefe;
            int mixed = ((background & mask) >>> 1) + ((foreground & mask) >>> 1);
            g.setColor(new Color(mixed, true));
            g.drawString(hint, insets.left, height / 2 + metrics.getAscent() / 2 - 2);
        }
    }
}
