import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
import java.lang.*;
import java.util.*;
  
/**
 * Write a description of class Tetromino here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tetromino extends Actor
{
    /**
     * Act - do whatever the Tetromino wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private List<Tile> tiles = new ArrayList<Tile>();
    private List<Outline> outlines = new ArrayList<Outline>();
    private int type, rotation;
    private boolean solid, active, outlinesPlaced;
    private int[][][] tp;
    
    
    public Tetromino() {
        getImage().setTransparency(0);
        solid = false;
        active = false;
        outlinesPlaced = false;
        
        type = (int)(Math.random()*7);
        rotation = 0;
        
        switch (type) {
            // O
            case 0: tp = new int[][][] {
                    {{0, 1, 1}, {0, 1, 1}, {0, 0, 0}}
                    };
                    break;
            // I
            case 1: tp = new int[][][] {
                    {{0, 0, 0, 0}, {1, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                    {{0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}},
                    {{0, 0, 0, 0}, {0, 0, 0, 0}, {1, 1, 1, 1}, {0, 0, 0, 0}},
                    {{0, 1, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}}
                    };
                    break;
            // Z
            case 2: tp = new int[][][] {
                    {{1, 1, 0}, {0, 1, 1}, {0, 0, 0}},
                    {{0, 0, 1}, {0, 1, 1}, {0, 1, 0}},
                    {{0, 0, 0}, {1, 1, 0}, {0, 1, 1}},
                    {{0, 1, 0}, {1, 1, 0}, {1, 0, 0}}
                    };
                    break;
            // S
            case 3: tp = new int[][][] {
                    {{0, 1, 1}, {1, 1, 0}, {0, 0, 0}},
                    {{0, 1, 0}, {0, 1, 1}, {0, 0, 1}},
                    {{0, 0, 0}, {0, 1, 1}, {1, 1, 0}},
                    {{1, 0, 0}, {1, 1, 0}, {0, 1, 0}}
                    };
                    break;
            // T 
            case 4: tp = new int[][][] {
                    {{0, 1, 0}, {1, 1, 1}, {0, 0, 0}},
                    {{0, 1, 0}, {0, 1, 1}, {0, 1, 0}},
                    {{0, 0, 0}, {1, 1, 1}, {0, 1, 0}},
                    {{0, 1, 0}, {1, 1, 0}, {0, 1, 0}}
                    };
                    break;
            // L
            case 5: tp = new int[][][] {
                    {{0, 0, 1}, {1, 1, 1}, {0, 0, 0}},
                    {{0, 1, 0}, {0, 1, 0}, {0, 1, 1}},
                    {{0, 0, 0}, {1, 1, 1}, {1, 0, 0}},
                    {{1, 1, 0}, {0, 1, 0}, {0, 1, 0}},
                    };
                    break;
            // J
            case 6: tp = new int[][][] {
                    {{1, 0, 0}, {1, 1, 1}, {0, 0, 0}},
                    {{0, 1, 1}, {0, 1, 0}, {0, 1, 0}},
                    {{0, 0, 0}, {1, 1, 1}, {0, 0, 1}},
                    {{0, 1, 0}, {0, 1, 0}, {1, 1, 0}},
                    };
                    break;
                    
            default: break;
        }
    }
    
    public Tetromino(int initType) {
        getImage().setTransparency(0);
        solid = false;
        active = false;
        outlinesPlaced = false;
        
        type = initType;
        rotation = 0;
        
        switch (type) {
            // O
            case 0: tp = new int[][][] {
                    {{0, 1, 1}, {0, 1, 1}, {0, 0, 0}}
                    };
                    break;
            // I
            case 1: tp = new int[][][] {
                    {{0, 0, 0, 0}, {1, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                    {{0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}},
                    {{0, 0, 0, 0}, {0, 0, 0, 0}, {1, 1, 1, 1}, {0, 0, 0, 0}},
                    {{0, 1, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}}
                    };
                    break;
            // Z
            case 2: tp = new int[][][] {
                    {{1, 1, 0}, {0, 1, 1}, {0, 0, 0}},
                    {{0, 0, 1}, {0, 1, 1}, {0, 1, 0}},
                    {{0, 0, 0}, {1, 1, 0}, {0, 1, 1}},
                    {{0, 1, 0}, {1, 1, 0}, {1, 0, 0}}
                    };
                    break;
            // S
            case 3: tp = new int[][][] {
                    {{0, 1, 1}, {1, 1, 0}, {0, 0, 0}},
                    {{0, 1, 0}, {0, 1, 1}, {0, 0, 1}},
                    {{0, 0, 0}, {0, 1, 1}, {1, 1, 0}},
                    {{1, 0, 0}, {1, 1, 0}, {0, 1, 0}}
                    };
                    break;
            // T 
            case 4: tp = new int[][][] {
                    {{0, 1, 0}, {1, 1, 1}, {0, 0, 0}},
                    {{0, 1, 0}, {0, 1, 1}, {0, 1, 0}},
                    {{0, 0, 0}, {1, 1, 1}, {0, 1, 0}},
                    {{0, 1, 0}, {1, 1, 0}, {0, 1, 0}}
                    };
                    break;
            // L
            case 5: tp = new int[][][] {
                    {{0, 0, 1}, {1, 1, 1}, {0, 0, 0}},
                    {{0, 1, 0}, {0, 1, 0}, {0, 1, 1}},
                    {{0, 0, 0}, {1, 1, 1}, {1, 0, 0}},
                    {{1, 1, 0}, {0, 1, 0}, {0, 1, 0}},
                    };
                    break;
            // J
            case 6: tp = new int[][][] {
                    {{1, 0, 0}, {1, 1, 1}, {0, 0, 0}},
                    {{0, 1, 1}, {0, 1, 0}, {0, 1, 0}},
                    {{0, 0, 0}, {1, 1, 1}, {0, 0, 1}},
                    {{0, 1, 0}, {0, 1, 0}, {1, 1, 0}},
                    };
                    break;
                    
            default: break;
        }
    }
    
    public void placeTiles() {
        int yCount = tp[rotation].length;
        int xCount = tp[rotation][0].length;
        for (int y = 0; y < yCount; y++) {
            for (int x = 0; x < xCount; x++) {
            if (tp[rotation][y][x] == 1) {
                    Tile tl = new Tile(this);
                    tiles.add(tl);
                    getWorld().addObject(tl, getX()+x, getY()+y);
                }
            }
        }
    }
    
    public void activate() {
        active = true;
        placeTiles();
    }
    
    public void rotate(boolean rLeft) {
        if (rLeft) {
            rotation++;
            if (rotation == tp.length) {
                rotation = 0;
            }
        } else {
            rotation--;
            if (rotation == -1) {
                rotation = tp.length-1;
            }
        }
        clearTiles();
        placeTiles();
        for (Tile t : tiles) {
            if (t.borderCheck()) {
                if (t.getX()-this.getX()-this.tp[0].length/2 > 0) {
                    moveAll("left");
                    for (Tile nt : tiles) {
                        if (nt.borderCheck()) {
                            if (t.getX()-this.getX()-this.tp[0].length/2 < 0) {
                                rotate(!rLeft);
                            }
                        }
                    }
                } else {
                    moveAll("right");
                    for (Tile nt : tiles) {
                        if (nt.borderCheck()) {
                            if (t.getX()-this.getX()-this.tp[0].length/2 > 0) {
                                rotate(!rLeft);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void act()
    {
        MyWorld w = (MyWorld)getWorld();
        if (!solid && active) { 
            clearOutlines();
            placeOutlines();
            if (Greenfoot.isKeyDown("a")) {
                if (!w.getRotated()) {
                    w.toggleRotated();
                    rotate(false);
                }
            } else if (Greenfoot.isKeyDown("s")) {
                if (!w.getRotated()) {
                    w.toggleRotated();
                    rotate(true);
                }
            } else {
                if (w.getRotated()) {
                    w.toggleRotated();
                }
            }
            if (Greenfoot.isKeyDown("Right")) {
                if (w.getGameTime() % 4 == 0) {
                    moveAll("right");
                }
            } else if (Greenfoot.isKeyDown("Left")) {
                if (w.getGameTime() % 4 == 0) {
                    moveAll("left");
                }
            }
            if (Greenfoot.isKeyDown("Space")) {
                if (!w.getDropped()) {
                    while (active) {
                        moveAll("down");
                        for (Tile t : tiles) {
                            t.fall();
                        }
                    }
                    w.toggleDropped();
                }
            } else {
                if (w.getDropped()) {
                    w.toggleDropped();
                }
                // 50, 5
                if (w.getGameTime() % 10 == 0 || (w.getGameTime() % 3 == 0 && Greenfoot.isKeyDown("Down"))) {
                    moveAll("down");
                    for (Tile t : tiles) {
                        t.fall();
                    }
                }   
            }
        }
    }
        
    public int getType() {
        return type;
    }
    
    public void solidify(boolean fallen) {
        if (fallen && active) {
            for (Tile t : tiles) {
                t.setLocation(t.getX(), t.getY()-1);
            }
            clearOutlines();
        }
        solid = true;
        active = false;
    }
    
    public void moveAll(String direction) {
        if (direction.equals("left")) {
            setLocation(getX()-1, getY());
        } else if (direction.equals("right")) {
            setLocation(getX()+1, getY());
        } else {
            setLocation(getX(), getY()+1);
        }
        for (Tile t : tiles) {
            t.move(direction);
        }
    }
    
    public boolean isSolid() {
        return solid;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void placeOutlines() {
        int yCount = tp[rotation].length;
        int xCount = tp[rotation][0].length;
        for (int y = 0; y < yCount; y++) {
            for (int x = 0; x < xCount; x++) {
                if (tp[rotation][y][x] == 1) {
                    Outline o = new Outline(this);
                    outlines.add(o);
                    getWorld().addObject(o, getX()+x, getY()+y);
                }
            }
        }
        
        while (!outlinesPlaced) {
            for (Outline o : outlines) {
                o.fall();
            }
        }
    }
    
    public void clearTiles() {
        for (Tile t : tiles) {
            getWorld().removeObject(t);
        }
        tiles = new ArrayList<Tile>();
    }
    
    public void solidifyOutlines() {
        if (!outlinesPlaced) {
            for (Outline o : outlines) {
                o.setLocation(o.getX(), o.getY()-1);
                outlinesPlaced = true;
            }
        }    
    }
    
    public void clearOutlines() {
        for (Outline o : outlines) {
            getWorld().removeObject(o);
        }
        outlinesPlaced = false;
        outlines = new ArrayList<Outline>();
    }
}
