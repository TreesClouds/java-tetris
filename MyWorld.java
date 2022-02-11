import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
import java.lang.*;
import java.util.*;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    
    // rotation (I piece clipping into pieces, wall, floor)
    // add game over/scoring
    // tile queue
    
    
    // Game Variables
    private int gameTime;
    private long realStartTime;
    private long realTime;
    private int score;
    private boolean tetrisScored;
    
    // Control Variables
    private boolean rotated;
    private boolean dropped;
    private boolean stored;
    private Tetromino storedTem;

    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        super(24, 22, 20);
        gameTime = 0;
        realStartTime = System.currentTimeMillis();
        rotated = false;
        dropped = false;
        setPaintOrder(Border.class, Tile.class);
        
        for (int i = 7; i < 17; i++) {
            addObject(new Border(true), i, 0);
            addObject(new Border(false), i, 22);
        }
        for (int i = 0; i < 22; i++) {
            addObject(new Border(false), 6, i);
            addObject(new Border(false), 17, i);
        }
        
        Tetromino t = new Tetromino();
        addObject(t, 10, 0);
        t.activate();
    }
    
    public void act() {
        gameTime++;
        realTime = (System.currentTimeMillis() - realStartTime) / 1000;
        showText("Time", 20, 1);
        showText(String.valueOf(realTime), 20, 2);
        
        boolean teActive = false;
        for (Tetromino te : getObjects(Tetromino.class)) {
            if (te.isActive()) {
                teActive = true;
                if (Greenfoot.isKeyDown("d") && !stored) {
                    stored = true;
                    Tetromino t;
                    if (storedTem != null) {
                        storedTem.clearTiles();
                        t = new Tetromino(storedTem.getType());
                    } else {
                        t = new Tetromino();
                    }
                    storedTem = new Tetromino(te.getType());
                    addObject(storedTem, 1, 2);
                    storedTem.placeTiles();
                    te.clearTiles();
                    te.clearOutlines();
                    removeObject(te);
                    addObject(t, 10, 0);
                    t.activate();
                    break;
                }
            }
        }
        
        if (!teActive) {
            
            int rowsCleared = 0;
            for (int row = 1; row < 22; row++) {
                int rowCheck = 0;
                List<Tile> rowTiles = new ArrayList<Tile>();
                for (int col = 7; col < 17; col++) {
                    if (getObjectsAt(col, row, Tile.class).size() > 0) {
                        rowCheck++;
                        rowTiles.add(getObjectsAt(col, row, Tile.class).get(0));
                    } 
                }
                if (rowCheck == 10) {
                    rowsCleared++;
                    for (Tile t : rowTiles) {
                        removeObject(t);
                    }
                    for (Tile t : getObjects(Tile.class)) {
                        if (t.getY() < row && t.isSolid()) {
                            t.setLocation(t.getX(), t.getY()+1);
                        }
                    }
                }
            }
            
            Tetromino t = new Tetromino();
            addObject(t, 10, 0);
            t.activate();
            stored = false;
        }
    }
    
    public int getGameTime() {
        return gameTime;
    }
    
    public int getScore() {
        return score;
    }
    
    public boolean getRotated() {
        return rotated;
    }
    
    public void toggleRotated() {
        rotated = !rotated;
    }
    
    public boolean getDropped() {
        return dropped;
    }
    
    public void toggleDropped() {
        dropped = !dropped;
    }
    
}
