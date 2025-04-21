import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Crosshair extends JFrame {
    private Color crosshairColor = Color.cyan;
    private int crosshairSize = 5;

    public Crosshair() {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0)); // Transparent background
        setAlwaysOnTop(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        CrosshairPanel crosshairPanel = new CrosshairPanel();
        setContentPane(crosshairPanel);

        // Key listener for toggling crosshair visibility
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    setVisible(false); // Hide crosshair on ESC key
                }
            }
        });
        setFocusable(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Crosshair overlay = new Crosshair();
            overlay.setVisible(true);
        });
    }

    class CrosshairPanel extends JPanel {
        public CrosshairPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            int centerX = w / 2;
            int centerY = h / 2;

            g2.setColor(crosshairColor);

            // Draw horizontal line
            g2.drawLine(centerX - crosshairSize, centerY, centerX + crosshairSize, centerY);
            // Draw vertical line
            g2.drawLine(centerX, centerY - crosshairSize, centerX, centerY + crosshairSize);
        }    
    } 
}
