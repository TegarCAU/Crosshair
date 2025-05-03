import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CrosshairOverlay extends JFrame {
    private static final int DOT_SIZE = 5;
    private Color dotColor = Color.cyan;
    private int dotSize = DOT_SIZE;

    public CrosshairOverlay() {
        // Set up the frame
        setUndecorated(true); // Remove window decorations
        setAlwaysOnTop(true); // Always stay on top
        setFocusableWindowState(false); // Prevent window from receiving focus
        setFocusable(false); // Prevent window from receiving focus
        
        // Set window to be transparent
        setBackground(new Color(0, 0, 0, 0));
        setUndecorated(true);
        setAlwaysOnTop(true);
        
        // Set window size and position
        setSize(100, 100);
        setLocationRelativeTo(null); // Center the window
        
        // Create a custom component for the dot
        JPanel dotPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(dotColor);
                g2d.fillOval(getWidth() / 2 - dotSize / 2, 
                            getHeight() / 2 - dotSize / 2, 
                            dotSize, dotSize);
            }
        };
        
        // Make the panel transparent
        dotPanel.setOpaque(false);
        
        // Add the panel to the frame
        add(dotPanel);
        
        // Add window listener to center the dot when window is moved
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaint();
            }
        });
    }
    
    public void setDotColor(Color color) {
        this.dotColor = color;
        repaint();
    }
    
    public void setDotSize(int size) {
        this.dotSize = size;
        repaint();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CrosshairOverlay overlay = new CrosshairOverlay();
            overlay.setVisible(true);
        });
    }
}
