package assignment1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Assignment1 extends JFrame {
    AppPanel panel;

    public Assignment1() {
        //Title of Window
        super("Jerome Larson");

        //Creating a window container
        Container cp = this.getContentPane();
        cp.setLayout(new BorderLayout());

        //Making the Panel Class object
        panel = new AppPanel();
        cp.add(panel, BorderLayout.CENTER);

        //Up, down, left, and right buttons
        JButton buttonLeft = new JButton("   <   ");
        JButton buttonUp = new JButton("   ^   ");
        JButton buttonDown = new JButton("   v   ");
        JButton buttonRight = new JButton("   >   ");

        //Give the arrow keys some functionality
        buttonLeft.addActionListener(e -> {
           panel.x -= 1;
           panel.repaint();
        });
        buttonRight.addActionListener(e -> {
            panel.x += 1;
            panel.repaint();
        });
        buttonUp.addActionListener(e -> {
            panel.y -= 1;
            panel.repaint();
        });
        buttonDown.addActionListener(e -> {
            panel.y += 1;
            panel.repaint();
        });

        //Create a button group for the arrow buttons
        ButtonGroup arrows = new ButtonGroup();
        arrows.add(buttonDown);
        arrows.add(buttonLeft);
        arrows.add(buttonRight);
        arrows.add(buttonUp);

        //Create the Radio buttons to change circle color
        JRadioButton red = new JRadioButton("Red");
        JRadioButton green = new JRadioButton("Green");
        JRadioButton blue = new JRadioButton("Blue");

        //Set default color selection
        red.setSelected(true);

        //Give the radio buttons functionality
        red.addActionListener(e->{
            panel.c = Color.RED;
            panel.repaint();
        });
        green.addActionListener(e->{
            panel.c = Color.GREEN;
            panel.repaint();
        });
        blue.addActionListener(e->{
            panel.c = Color.BLUE;
            panel.repaint();
        });

        //Add the radio buttons to a button group
        ButtonGroup colors = new ButtonGroup();
        colors.add(red);
        colors.add(green);
        colors.add(blue);

        //Create a panel for all of the buttons
        JPanel buttons = new JPanel();
        buttons.setBackground(Color.LIGHT_GRAY);        //Give panel a bckgrnd color
        BoxLayout arrowBox = new BoxLayout(buttons, BoxLayout.Y_AXIS);
        buttons.setLayout(arrowBox);
        buttons.add(Box.createVerticalGlue());          //Align buttons with upper bound of window

        //Add the raido buttons to the top part of the panel
        buttons.add(red);
        buttons.add(green);
        buttons.add(blue);

        //Give the radio buttons same bckgrnd as panel
        red.setBackground(Color.LIGHT_GRAY);
        green.setBackground(Color.LIGHT_GRAY);
        blue.setBackground(Color.LIGHT_GRAY);
        buttons.add(Box.createVerticalGlue());          //Bind buttons to top of arrow buttons

        //Add the directional buttons to the panel
        buttons.add(buttonLeft);
        buttons.add(buttonRight);
        buttons.add(buttonUp);
        buttons.add(buttonDown);
        buttons.add(Box.createVerticalGlue());          //Bind the buttons to the bottom of the window

        //Insert buttons into the main window on the right side
        cp.add(buttons, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        //Create a new frame
        Assignment1 frame = new Assignment1();
        frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class AppPanel extends JPanel {
    //Define some global default variables for the circle
    int x = 100;
    int y = 100;
    int w = 60;
    Color c = Color.RED;

    //Define global variable for the cross length
    int s = 30;

    //Constructor for AppPanel()
    public AppPanel() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            //Allows the circle and cross to be redrawn wherever the mouse is clicked
            public void mouseClicked(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                repaint();          //Uses the paintComponent method to redraw object
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Create a circle with the default color of red
        g.setColor(c);
        g.fillOval(x-w/3, y-w/3, 2*w/3, 2*w/3);

        //Create a black outline for the circle and cross
        g.setColor(Color.BLACK);
        g.drawOval(x-w/3, y-w/3, 2*w/3, 2*w/3);

        //Draw the cross
        g.drawLine(x-s, y, x+s, y);
        g.drawLine(x, y-s, x, y+s);
    }
}