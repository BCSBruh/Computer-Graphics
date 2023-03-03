package assignment2;

import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

public class Assignment2 {
    public static Shape createYingYang() {
        int diameter = 200;
        int radius = diameter/2;

        /*Creating each piece that needs to be defined. The Ying Yang can be broken up into
        two hemispheres. Left side is white and right side is black

        First I will split a circle into two halves. Then create two circles in the center one on top
        of the other. Top circle is white and bottom is black. Finally two more circles
        act as the central dots.
         */
//        Shape c1 = new Ellipse2D.Double(-100, -100, diameter, diameter);
//        Shape arc1 = new Arc2D.Double(-99, -99, diameter-2, diameter-2, 90, 180, 2);
//        Shape c2 = new Ellipse2D.Double(-49, -99, radius, radius);
//        Shape c3 = new Ellipse2D.Double(-49, 0, radius, radius-1);
//        Shape c4 = new Ellipse2D.Double(-radius/16, -radius/2, radius/12, radius/12);
//        Shape c5 = new Ellipse2D.Double(-radius/16, radius/2, radius/12, radius/12);
//
//        //Creating the areas for each object in order to cut out and add pieces
//        Area a1 = new Area(c1);
//        Area arcArea1 = new Area(arc1);
//        Area a2 = new Area(c2);
//        Area a3 = new Area(c3);
//        Area a4 = new Area(c4);
//        Area a5 = new Area(c5);
//
//        //By subtracting the left semicircle and top circle I can create the white part of the Ying Yang
//        a1.subtract(arcArea1);
//        a1.subtract(a2);
//
//        //Adding the bottom circle and the small dot in the white top circle
//        a1.add(a3);
//        a1.add(a4);
//
//        //Creating the small dot in the bottom black circle
//        a1.subtract(a5);

        Ellipse2D c1 = new Ellipse2D.Double(0, 0, 400, 400);
        Ellipse2D c2 = new Ellipse2D.Double(200, 0, 400, 400);
        Ellipse2D c3 = new Ellipse2D.Double(100, 200, 400, 400);
        Area a1 = new Area(c1);
        Area a2 = new Area(c2);
        Area a3 = new Area(c3);
        Area a4 = new Area(c1);
        a1.exclusiveOr(a2);
        a2.subtract(a3);
        a3.intersect(a1);
        a4.intersect(a2);
        a4.add(a3);

        return a3;
    }

    public static Shape createSpirograph() {
        //Create Path2D object (uses the Shape interface)
        Path2D.Double spiro = new Path2D.Double(Path2D.WIND_EVEN_ODD, 1000);

        //Defining the variables needed in the equation
        double r1 = 30, r2 = 40, p = 60, t;
        int x1 = (int)(r1+r2 - p), y1 = 0, x2, y2;

        //Centering the spirograph in the center
        spiro.moveTo(-200, -200);

        //Creating each line segment
        for (int i = 0; i < 1000; i++) {
            //125 comes from dividing 8π into 1000 pieces (basically)
            //8π = 1000π / x -> x = 125
            t = i*Math.PI / 125;

            //Creating the endpoints of the line segment
            x2 = (int)((r1+r2)*Math.cos(t) - p*Math.cos(((r1+r2)*t) / r2));
            y2 = (int)((r1+r2)*Math.sin(t) - p*Math.sin(((r1+r2)*t) / r2));

            //Connecting the starting points of the line segment to the endpoints
            spiro.quadTo(x1, y1, x2, y2);

            //Making endpoints the new starting points
            x1 = x2;
            y1 = y2;
        }

        return spiro;
    }
}
