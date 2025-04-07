package cz.uhk.veditor.grobjekty;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Rectangle extends AbstractGeomObject {
    protected int a;
    protected int b;

    public Rectangle(Point position, int a, int b, Color color) {
        super(position, color);
        this.a = a;
        this.b = b;
    }

    public Rectangle(int a, int b) {
        super();
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean contains(int x, int y) {
        return x >= position.x && x <= position.x + a
                && y >= position.y && y <= position.y + b;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.drawRect(position.x, position.y, a, b);
    }
}
