package bukc.project;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class ModernScrollBarUI extends BasicScrollBarUI {

    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = new Color(50, 60, 80); // Indigo thumb
        this.trackColor = new Color(32, 41, 56);   // Track color (matches your sidebar)
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

    private JButton createZeroButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0)); // Hides arrow buttons
        button.setMinimumSize(new Dimension(0, 0));
        button.setMaximumSize(new Dimension(0, 0));
        return button;
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        // Optional: paint track background if you want subtle contrast
        g.setColor(trackColor);
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        if (!scrollbar.isEnabled() || thumbBounds.isEmpty()) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(thumbColor);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 5, 5);
        g2.dispose();
    }

    @Override
    protected Dimension getMaximumThumbSize() {
        return new Dimension(8, Integer.MAX_VALUE); // Narrow thumb width
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        return new Dimension(8, 30); // Minimum height of thumb
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return (scrollbar.getOrientation() == JScrollBar.VERTICAL)
                ? new Dimension(8, Integer.MAX_VALUE) // 8px width
                : new Dimension(Integer.MAX_VALUE, 8); // 8px height for horizontal scrollbar (if used)
    }

}
