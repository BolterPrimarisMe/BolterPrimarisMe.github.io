import javax.swing.*;
import java.awt.*;

public class FaceDrawing extends JFrame {

    public FaceDrawing() {
        setTitle("Face Drawing");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a custom canvas
        FaceCanvas canvas = new FaceCanvas();
        add(canvas);

        setVisible(true);
    }

    // Inner class to define the canvas for drawing
    class FaceCanvas extends Canvas {

        @Override
        public void paint(Graphics g) {
            // Draw the face (circle)
            g.setColor(Color.YELLOW);
            g.fillOval(100, 100, 200, 200); // x, y, width, height

            // Draw the eyes (small circles)
            g.setColor(Color.BLACK);
            g.fillOval(150, 150, 30, 30); // left eye
            g.fillOval(220, 150, 30, 30); // right eye

            // Draw the mouth (arc for a smile)
            g.setColor(Color.RED);
            g.drawArc(150, 220, 100, 50, 0, -180); // x, y, width, height, startAngle, arcAngle

            // Draw the name below the face
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("My Happy Face", 130, 350); // string, x, y
        }
    }

    public static void main(String[] args) {
        new FaceDrawing(); // Create and display the frame
    }
}
