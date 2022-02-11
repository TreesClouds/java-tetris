import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tile extends Actor
{
    private Tetromino parent;
    
    public Tile(Tetromino initParent) {
        String color;
        switch (initParent.getType()) {
            // O
            case 0: color = "yellow";
                    break;
            // I
            case 1: color = "lightblue";
                    break;
            // Z
            case 2: color = "red";
                    break;
            // S
            case 3: color = "green";
                    break;
            // T 
            case 4: color = "purple";
                    break;
            // L
            case 5: color = "orange";
                    break;
            // J
            case 6: color = "darkblue";
                    break;
            
            default:    color = "red";
                        break;
        }
        setImage(color + ".png");
        parent = initParent;
    }
    
    public void move(String direction) {
        if (direction.equals("left")) {
            setLocation(getX()-1, getY());
            if (borderCheck() || tileCheck()) {
                parent.moveAll("right");
            }
        } else if (direction.equals("right")) {
            setLocation(getX()+1, getY());
            if (borderCheck() || tileCheck()) {
                parent.moveAll("left");
            }
        }
    }
    
    public boolean borderCheck() {
        return getIntersectingObjects(Border.class).size() > 0 && !getIntersectingObjects(Border.class).get(0).isPassable();
    }
    
    public boolean tileCheck() {
        return getIntersectingObjects(Tile.class).size() > 0 && getIntersectingObjects(Tile.class).get(0).isSolid();
    }
    public void fall() {
        setLocation(getX(), getY()+1);
        if (borderCheck()) {
            parent.solidify(true);
        }
        
        if (tileCheck()) {
            parent.solidify(true);
        }
    }
    
    public void remove() {
        getWorld().removeObject(this);
    }
    
    public boolean isSolid() {
        return parent.isSolid();
    }
    
    public boolean isActive() {
        return parent.isActive();
    }
}
