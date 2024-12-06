import java.awt.Graphics;
import java.util.Arrays;
/**
 * Write a description of class Polygon2D here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Polygon2D extends Shape2D
{
    private int[] xCoords = null;
    private int[] yCoords = null;
    private int[] txCoords = null;
    private int[] tyCoords = null;
    
    
    
    Polygon2D(int fillColorIndex, int xPos, int yPos, int[] xCoords, int[] yCoords)
    {
        super(fillColorIndex, xPos, yPos);
        
        this.xCoords = new int[xCoords.length]; // construct (allocate memory)
        this.yCoords = new int[yCoords.length]; // construct (allocate memory)
        this.txCoords = new int[xCoords.length]; // construct (allocate memory)
        this.tyCoords = new int[yCoords.length]; // construct (allocate memory)
        // Deep Copy
        for (int i = 0; i < xCoords.length; i++)
        {
            this.xCoords[i] = xCoords[i];
            this.txCoords[i] = xCoords[i] + xPos;
        }
        // Deep Copy
        for (int i = 0; i < yCoords.length; i++)
        {
            this.yCoords[i] = yCoords[i];
            this.tyCoords[i] = yCoords[i] + yPos;
        }
    }
    
    @Override
    public void Draw(Graphics g)
    {
        Transform();
        g.setColor(super.getFillColor());
        g.fillPolygon(txCoords, tyCoords, xCoords.length);
        
    }
    
        
    
    // This is the Transform method for Polygon2D
    private void Transform()
    {
        double degs = super.getZRotate();
        double rads = Math.toRadians(degs);
        double Sx = super.getScaleX();
        double Sy = super.getScaleY();
        for (int i = 0; i < xCoords.length; i++)
        {
            double x = Sx * this.xCoords[i];
            double y = Sy * this.yCoords[i];
            this.txCoords[i] = (int)(((x * Math.cos(rads) - y * Math.sin(rads)) +
            super.getXPos()) + 0.5);
            this.tyCoords[i] = (int)(((x * Math.sin(rads) + y * Math.cos(rads)) +
            super.getYPos() + 0.5));
        }
    }
    
    @Override
    public boolean reachedBottom(int borderY)
    {
        boolean reachedBottom;
        int yMax = Arrays.stream(tyCoords).max().getAsInt();
        
        
        if (yMax >= borderY)
        {
            reachedBottom = true;
        }
        else
        {
            reachedBottom = false;
        }
        return reachedBottom;
    }


    

}


