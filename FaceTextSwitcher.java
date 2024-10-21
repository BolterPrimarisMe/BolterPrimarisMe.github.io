import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class FaceTextSwitcher extends JFrame {
    private DrawingPanel drawingPanel;
    private JButton switchButton;
    private boolean showingFace = true;
    private Random random;
    
    // Array of programming concepts to display
    private String[] programmingConcepts = {
        "Event-driven programming: Programs that react to user inputs.",
        "Button: A GUI element that can be clicked to trigger an action.",
        "Canvas: A component for drawing graphics on the screen.",
        "AWT: Abstract Window Toolkit, used for building GUIs in Java.",
        "Swing: A more powerful GUI toolkit built on top of AWT.",
        "JPanel: A container for organizing and displaying GUI components."
    };
    
    public FaceTextSwitcher() {
        random = new Random();
        
        // Set up the frame
        setTitle("Face and Text Switcher");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create the drawing panel
        drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);
        
        // Create the button
        switchButton = new JButton("Switch");
        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle between showing face and text
                showingFace = !showingFace;
                drawingPanel.repaint(); // Request a redraw
            }
        });
        add(switchButton, BorderLayout.SOUTH);
    }

    // Panel for drawing faces or text
    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (showingFace) {
                drawFace(g, random.nextInt(3) + 1);  // Random face between 1 and 3
            } else {
                drawText(g);
            }
        }

        // Draws one of three faces based on the random number
        private void drawFace(Graphics g, int faceNumber) {
            g.setColor(Color.BLACK);
            switch (faceNumber) {
                case 1:
                    g.drawOval(100, 50, 200, 200); // Head
                    g.fillOval(150, 100, 20, 20);  // Left Eye
                    g.fillOval(230, 100, 20, 20);  // Right Eye
                    g.drawArc(150, 150, 100, 50, 0, -180); // Smile
                    break;
                case 2:
                    g.drawOval(100, 50, 200, 200); // Head
                    g.fillOval(150, 100, 20, 20);  // Left Eye
                    g.fillOval(230, 100, 20, 20);  // Right Eye
                    g.drawLine(150, 170, 250, 170); // Straight mouth
                    break;
                case 3:
                    g.drawOval(100, 50, 200, 200); // Head
                    g.fillOval(150, 100, 20, 20);  // Left Eye
                    g.fillOval(230, 100, 20, 20);  // Right Eye
                    g.drawArc(150, 170, 100, 50, 0, 180); // Frown
                    break;
            }
        }

        // Draws random programming concept text
        private void drawText(Graphics g) {
            String text = programmingConcepts[random.nextInt(programmingConcepts.length)];
            g.setFont(new Font("Serif", Font.PLAIN, 16));
            g.drawString(text, 50, 200);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FaceTextSwitcher().setVisible(true);
            }
        });
    }
}

