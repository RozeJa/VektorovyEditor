package cz.uhk.veditor.gui;

import cz.uhk.veditor.grobjekty.AbstractGeomObject;
import cz.uhk.veditor.grobjekty.Circle;
import cz.uhk.veditor.grobjekty.Rectangle;
import cz.uhk.veditor.grobjekty.Square;
import cz.uhk.veditor.grobjekty.Triangle;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Hlavni okno aplikace
 */
public class MainWindow extends JFrame {

    private List<AbstractGeomObject> objekty = new ArrayList<>();
    private JToolBar toolBar;
    private JToggleButton btSquare;
    private JToggleButton btCircle;
    private JToggleButton btRectangle;
    private JToggleButton btTriangle;
    private JToggleButton btSelect;
    
    private AbstractGeomObject selectedObject;
    
    public MainWindow() {
        super("Vektorový editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initTestData();

        createToolBar();

        GraphPanel panel = new GraphPanel(objekty);
        add(panel, BorderLayout.CENTER);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (btCircle.isSelected()) {
                        Circle circle = new Circle(new Point(e.getX(), e.getY()), 50, Color.RED);
                        objekty.add(circle);
                    } else if (btSquare.isSelected()) {
                        Square square = new Square(new Point(e.getX(), e.getY()), 50, Color.BLUE);
                        objekty.add(square);
                    } else if (btRectangle.isSelected()) {
                        Rectangle rectangle = new Rectangle(new Point(e.getX(), e.getY()), 25, 40, Color.GREEN);
                        objekty.add(rectangle);
                    } else if (btTriangle.isSelected()) {
                        Triangle triangle = new Triangle(new Point(e.getX(), e.getY()), 35, Color.ORANGE);
                        objekty.add(triangle);
                    }

                    panel.repaint();
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (selectedObject != null) {
                    selectedObject.setPosition(e.getX(), e.getY());

                    panel.repaint();
                    selectedObject = null;
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if (btSelect.isSelected()) {
                    selectedObject = panel.selectObject(e.getX(), e.getY());
                }
            }
        });
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedObject != null) {
                    selectedObject.setPosition(e.getX(), e.getY());

                    panel.repaint();
                }
            }
        });

        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void createToolBar() {
        toolBar = new JToolBar(JToolBar.HORIZONTAL);
        add(toolBar, BorderLayout.NORTH);
        
        btSquare = new JToggleButton("Ctverec");
        btCircle = new JToggleButton("Kruznice");
        btRectangle = new JToggleButton("Obdelnik");
        btTriangle = new JToggleButton("Trojuhelnik");
        btSelect = new JToggleButton("Vyber");

        toolBar.add(btSquare);
        toolBar.add(btCircle);
        toolBar.add(btRectangle);
        toolBar.add(btTriangle);
        toolBar.add(btSelect);

        ButtonGroup gr = new ButtonGroup();
        gr.add(btCircle);
        gr.add(btSquare);
        gr.add(btRectangle);
        gr.add(btTriangle);
        gr.add(btSelect);
    }

    private void initTestData() {
        objekty.add(new Circle(new Point(100,100),50,Color.BLUE));
        objekty.add(new Square(new Point(200,100),10,Color.RED));
        objekty.add(new Circle(new Point(100,300),60,Color.GREEN));
        objekty.add(new Circle(new Point(500,100),70,Color.YELLOW));
        objekty.add(new Square(new Point(200,600),40,Color.MAGENTA));
        objekty.add(new Circle(new Point(300,300),70,Color.ORANGE));
        objekty.add(new Square(new Point(600,400),30,Color.BLACK));
    }
}
