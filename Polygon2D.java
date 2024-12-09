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
    
    public int[] getXcoords()
    {
        return xCoords;
    }
    
    public void setXcoords(int [] xCoords)
    {
        this.xCoords = xCoords;
    }
    
    public int[] getYcoords()
    {
        return yCoords;
    }
    
    public void setYcoords(int [] yCoords)
    {
        this.yCoords = yCoords;        
    }
    
    
    public int[] gettXcoords()
    {
        return txCoords;
    }
    
    public void settXcoords(int [] txCoords)
    {
        this.txCoords = txCoords;
    }
    
    public int[] gettYcoords()
    {
        return tyCoords;
    }
    
    public void settYcoords(int [] tyCoords)
    {
        this.tyCoords = tyCoords;        
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
        
        //adjusting transform to rotate polygon about its center.
        int sumX = 0;
        int sumY = 0;
        
        for (int i = 0; i < xCoords.length ; i++) //calculating sum of coords in x and y coords
        {
            sumX += xCoords[i];
            sumY += yCoords[i];
        }
        double centroidX = sumX / (double)xCoords.length; //finding average of each array which finds centroid
        double centroidY = sumY / (double)xCoords.length;
        
        
        for (int i = 0; i < xCoords.length; i++)
        {
            double x = Sx * (xCoords[i] - centroidX);
            double y = Sy * (yCoords[i] - centroidY);
            
            this.txCoords[i] = (int)Math.round((x * Math.cos(rads) - y * Math.sin(rads)) + centroidX + super.getXPos());
            this.tyCoords[i] = (int)Math.round((x * Math.sin(rads) + y * Math.cos(rads)) + centroidY + super.getYPos());
        }
    }
    
    public boolean reachedBottom()
    {
        int yMax = Arrays.stream(tyCoords).max().getAsInt();
        return yMax >= 625;
    }
    
    public boolean reachedRight()
    {
        boolean reachedRight;
        int xMax = Arrays.stream(txCoords).max().getAsInt();
        if(xMax >= 275)
        {
            reachedRight = true;
        }
        else
        {
            reachedRight = false;
        }
        return reachedRight;
    }
    
    public boolean reachedLeft()
    {
        boolean reachedLeft;
        int xMin = Arrays.stream(txCoords).min().getAsInt();
        if(xMin <= 25)
        {
            reachedLeft = true;
        }
        else
        {
            reachedLeft = false;
        }
        return reachedLeft;
    }
    
    
    public Polygon2D clone()
    {
        return new Polygon2D(super.getFillColorIndex(), super.getXPos(), super.getYPos(), this.xCoords.clone(), this.yCoords.clone());
    }
    
       

    

}


