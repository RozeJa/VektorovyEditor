package cz.uhk.veditor.grobjekty;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Triangle extends AbstractGeomObject {
    protected int a;

    public Triangle(Point position, int a, Color color) {
        super(position, color);
        this.a = a;
    }

    public Triangle(int a) {
        super();
        this.a = a;
    }

    @Override
    public boolean contains(int x, int y) { 
        // výška musí být nezáporná
        if (position.y > y || position.y + getHeight() < y) 
            return false;

        /**
         * 
         *          A
         *   
         *              2
         *                  
         *          C  1  B
         */

        if (position.x > x) {
            int width = position.x - x;
            int height = y - position.y;
            return width * 2 <= height; 
        } else {
            int width = x - position.x;
            int height = y - position.y;
            return width * 2 <= height; 
        }
    }

    @Override
    public void draw(Graphics2D g) {

        /**
         *          A
         *      
         * 
         *      C       B
         */

        int h = getHeight();

        int ax = position.x;
        int ay = position.y;

        int bx = position.x + a/2;
        int by = position.y + h;

        int cx = position.x - a/2;
        int cy = position.y + h;

        g.setColor(color);
        g.drawLine(ax, ay, bx, by);
        g.drawLine(bx, by, cx, cy);
        g.drawLine(cx, cy, ax, ay);
    }

    public int getHeight() {
        double x = (Math.sqrt(3)/2.);
        return (int) (x * a);
    }
}
