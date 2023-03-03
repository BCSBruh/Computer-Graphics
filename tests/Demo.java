package tests;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Demo extends JPanel {
    Color color = Color.RED;
    AffineTransform tx = new AffineTransform();

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Hello 2D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new Demo();
        frame.getContentPane().add(panel);
        frame.setBounds(100, 200, 500, 400);
        frame.setVisible(true);
    }

    public Demo() {
        Timer timer = new Timer(300, e -> {
            float r = (float)Math.random();
            float g = (float)Math.random();
            float b = (float)Math.random();
            color = new Color(r, g, b);
            tx.scale(0.99,0.99);
            repaint();
        });
        timer.start();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Mouse clicked");
                BufferedImage bi = new BufferedImage(500, 400, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2 = bi.createGraphics();
                paintComponent(g2);
                try {
                    ImageIO.write(bi, "PNG", new File("demo.png"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        Shape shape = createShape();
        g2.setColor(color); //new Color(240, 174, 0));
        g2.setTransform(tx);
        g2.fill(shape);
//        g2.translate(200, 200);/
//        g2.setColor(new Color(200, 3, 43));
//        g2.fill(shape);
//        g2.translate(-200, 200);
//        g2.setColor(new Color(0, 81, 157));
//        g2.fill(shape);

    }

    Shape createShape() {
        Path2D path = new Path2D.Double(Path2D.WIND_EVEN_ODD);
        path.moveTo(100, 100);
        path.lineTo(150, 30);
        path.lineTo(200, 300);
        path.closePath();
        path.moveTo(50, 200);
        path.quadTo(250, 40, 300,200);
        path.curveTo(150, 250, 350, 300, 300, 400);
        path.closePath();
//        path.setWindingRule(Path2D.WIND_NON_ZERO);
        return path;
    }

    Shape createShape1() {
        Shape c1 = new Ellipse2D.Double(200, 200, 200, 200);
        Area a1 = new Area(c1);
        Shape square = new Rectangle2D.Double(100, 100, 200, 200);
        Area shape = new Area(square);
        shape.subtract(a1);
        Shape c2 = new Ellipse2D.Double(0, 0, 200, 200);
        Area a2 = new Area(c2);
        shape.subtract(a2);
        Shape c3 = new Ellipse2D.Double(200, 0, 200, 200);
        Area a3 = new Area(c3);
        shape.subtract(a3);
        Shape c4 = new Ellipse2D.Double(0, 200, 200, 200);
        Area a4 = new Area(c4);
        shape.subtract(a4);
        return shape;
    }
}
