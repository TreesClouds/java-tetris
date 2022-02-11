import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Outline here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Outline extends Actor
{
    private Tetromino parent;
    
    public Outline(Tetromino initParent) {
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
        getImage().setTransparency(50);
        parent = initParent;
    }

    public void fall() {
        setLocation(getX(), getY()+1);
        if (getIntersectingObjects(Border.class).size() > 0 && !getIntersectingObjects(Border.class).get(0).isPassable()) {
            parent.solidifyOutlines();
        }
        
        if (getIntersectingObjects(Tile.class).size() > 0 && getIntersectingObjects(Tile.class).get(0).isSolid()) {
            parent.solidifyOutlines();
        }
    }
}
