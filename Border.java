import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameBorder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Border extends Actor
{
    /**
     * Act - do whatever the GameBorder wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private boolean passable;
    
    public Border(boolean initPassable) {
        passable = initPassable;
    }
    
    public boolean isPassable() {
        return passable;
    }
}
