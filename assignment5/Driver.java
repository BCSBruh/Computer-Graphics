package assignment5;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Driver extends JPanel {
    AffineTransform tx = new AffineTransform();

    public Driver() {
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.blue);
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(200, 200);
        g2.fill(image());
    }
    public static void main(String[] s) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new Driver();
        frame.getContentPane().add(panel);
        frame.setTitle("Assignment 5");
        frame.pack();
        frame.setVisible(true);
    }

    Shape image() {
        tx.translate(0,0);
        Shape eagle = tx.createTransformedShape(new Eagle());
        Area eagleArea = new Area(eagle);

        Shape ellipse = new Ellipse2D.Double(600, 600, 600, 600);
        Area shape = new Area (ellipse);

        Shape upperCircle = ellipse;
        Area upperC = new Area(upperCircle);

        Shape lowerCircle = ellipse;
        Area lowerC = new Area(lowerCircle);

        upperC.subtract(eagleArea);
        lowerC.subtract(eagleArea);
        upperC.subtract(lowerC);

        shape.add(eagleArea);
        shape.exclusiveOr(upperC);

        return shape;
    }
}
