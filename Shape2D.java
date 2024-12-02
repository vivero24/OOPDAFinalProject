
/**
 * Write a description of class Shape2D here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.geom.*;
public abstract class Shape2D
{
    public final static int RED = 0;
    public final static int GREEN = 1;
    public final static int BLUE = 2;
    public final static int BLACK = 3;
    public final static int GREY = 4;
    public final static int WHITE = 5;
    public final static int YELLOW = 6;
    public final static int CYAN = 7;
    public final static int MAGENTA = 8;
    public final static int BROWN = 9; 
    
    
    // RGB color table
    public static final Color[] COLORS = {
            //         R     G    B
            new Color(255,   0,   0),  // Red     0
            new Color(  0, 255,   0),  // Green   1
            new Color(  0,   0, 255),  // Blue    2
            new Color(  0,   0,   0),  // Black   3
            new Color(128, 128, 128),  // Grey    4
            new Color(255, 255, 255),  // White   5
            new Color(255, 255,   0),  // Yellow  6
            new Color(  0, 255, 255),  // Cyan    7
            new Color(255,   0, 255),  // Magenta 8 
            new Color(165,  42,  42),  // Brown   9
            new Color(255,  38,  38),
            new Color(255, 168,  38),
            new Color(212, 255,  38),
            new Color( 82, 255,  38),
            new Color( 38, 255, 125),
            new Color( 38, 255, 255),
            new Color( 38, 125, 255),
            new Color( 82,  38, 255),
            new Color(212,  38, 255),
            new Color(255,  38, 168),
        };   
        
    private int     xPos;               // xPos
    private int     yPos;               // yPos
    private Color   fillColor;          // the color of the sphere
    private int     fillColorIndex;     //the index of the color of the sphere in the COlORS array
    
    
    
    private double sX ; //Scale X
    private double sY; //Scale Y
    private double rotAngleZ =1; //Rotation about the z axis
    
    private int xVel; //xVel
    private int yVel; //yVel
    
    private boolean outline;
    private Color outlineColor; 
    
    /**
     * Constructor for objects of class Shape2D
     */
    public Shape2D(int fillColorIndex, int xPos, int yPos)
    {
        this.fillColorIndex = fillColorIndex;
        this.xPos = xPos;
        this.yPos = yPos;
        this.fillColor = COLORS[fillColorIndex];
        
        this.sX = 1.0;
        this.sY = 1.0;
        this.rotAngleZ = 0.0;
        this.xVel = 0;
        this.yVel = 0;
        
        
    }
    
    /**
     * Moves the shape by an amount (xDelta, yDelta)
     *
     * Move - translates the shape by an amount (xDelta, yDelta)
     *
     * @param  xDelta - amount to translate along the x axis
     *         yDelta - amount to translate along the y axis
     * @return None
     */
    public void Move(int xDelta, int yDelta)
    {
        //move the shape
        this.xPos += xDelta;
        this.yPos += yDelta;
    }
    
    
    public int getXPos()
    {
        return xPos;
    }
    
    public int getYPos()
    {
        return yPos;
    }

    
    public Color getFillColor()
    {
        return fillColor;
    }
    
    
    public void setColor(Color color)
    {
        this.fillColor = color;
    }
    //getters and setters for sX,sY
    
    public double getScaleX()
    {
        return sX;
    }
    
    public void setScaleX(double sX)
    {
        this.sX = sX;
    }
    
    public double getScaleY()
    {
        return sY;
    }
    
    public void setScaleY(double sY)
    {
        this.sY = sY;
    }
    
    //getter and setter for rotAngleZ
    public double getZRotate()
    {
        return rotAngleZ;
    }
    
    public void setZRotate()
    {
        this.rotAngleZ = rotAngleZ;
    }
    
    //getter and setter for x and y velocities;
    public int getXvel()
    {
        return xVel;
    }
    
    public void setXvel(int xVel)
    {
        this.xVel = xVel;
    }
    
    
    public int getYvel()
    {
        return yVel;
    }
    
    public void setYvel(int yVel)
    {
        this.yVel = yVel;
    }
    
    

    
    /**
     * public void Draw(Graphics g)
     * 
     * Render the circle for both filled and outlined according to the states
     *
     * @param  - Graphics g is the graphics context
     * @return - void
     */
    public abstract void Draw(Graphics g);
    
    
    

    
}
