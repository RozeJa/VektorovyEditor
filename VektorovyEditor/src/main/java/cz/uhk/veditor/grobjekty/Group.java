package cz.uhk.veditor.grobjekty;

import java.awt.Graphics2D;
import java.util.List;

public class Group extends AbstractGeomObject {
    
    protected List<AbstractGeomObject> objects;
  
    public Group(List<AbstractGeomObject> objects) {
        this.objects = objects;
    }

    public Group() {}


    @Override
    public boolean contains(int x, int y) {
        for (AbstractGeomObject abstractGeomObject : objects) {
            if (abstractGeomObject.contains(x, y)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void draw(Graphics2D g) {
        for (AbstractGeomObject abstractGeomObject : objects) {
            abstractGeomObject.draw(g);
        }
    }

    
}
