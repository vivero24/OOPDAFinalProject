import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class Polygon2D here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public abstract class Polygon2D extends Shape2D
{
    private int[] xCoords = null;
    private int[] yCoords = null;
    private int[] txCoords = null;
    private int[] tyCoords = null;
    private List <Rectangle2D> rectangles;

    Polygon2D(int fillColorIndex, int xPos, int yPos, int[] xCoords, int[] yCoords)
    {
        super(fillColorIndex, xPos, yPos);

        this.xCoords = new int[xCoords.length]; // construct (allocate memory)
        this.yCoords = new int[yCoords.length]; // construct (allocate memory)
        this.txCoords = new int[xCoords.length]; // construct (allocate memory)
        this.tyCoords = new int[yCoords.length]; // construct (allocate memory)
        this.rectangles = new ArrayList<>();
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
    
    /**
     * getXcoords - returns x coordinates of polygon
     * @param - none
     * @return - int [] xCoords
     */
    public int[] getXcoords()
    {
        return xCoords;
    }

    /**
     * setXcoords - sets x coordinates of polygon
     * 
     * @param - int [] xCoords
     * @return - none
     */
    public void setXcoords(int [] xCoords)
    {
        this.xCoords = xCoords;
    }

    
    /**
     * getYcoords - returns y coordinates of polygon
     * @param - none
     * @return - int [] yCoords
     */
    public int[] getYcoords()
    {
        return yCoords;
    }

    
    /**
     * setYcoords - sets y coordinates of polygon
     * 
     * @param - int [] yCoords
     * @return - none
     */
    public void setYcoords(int [] yCoords)
    {
        this.yCoords = yCoords;        
    }

    
    /**
     * gettXcoords - returns transformation x coordinates of polygon
     * 
     * @param - none
     * @return - int [] txCoords
     */
    
    public int[] gettXcoords()
    {
        return txCoords;
    }
    
    /**
     * settxCoords - sets transformation x coordinates of polygon
     * 
     * @param - int [] txCoords
     * @return - none
     */
    public void settXcoords(int [] txCoords)
    {
        this.txCoords = txCoords;
    }

    
    /**
     * gettYcoords - gets transformation y coordinates of polygon
     * 
     * @param - none
     * @return - int [] tyCoords
     */
    public int[] gettYcoords()
    {
        return tyCoords;
    }

    /**
     * settYcoords - sets transformation y coordinates of polygon
     * 
     * @param - int [] tyCoords
     * @return - none
     */
    public void settYcoords(int [] tyCoords)
    {
        this.tyCoords = tyCoords;        
    }

    
    /**
     * getRectangles - returns list of rectangles that make up the polygon
     * 
     * @param - none
     * @return - List <Rectangle2D> rectangles
     */
    public List <Rectangle2D> getRectangles()
    {
        return rectangles;
    }

    
    /**
     * setRectangles - sets list of rectangles that make up polygon
     * 
     * @param - List <Rectangle2D> , rectangles
     * @return - none
     */
    public void setRectangles(List<Rectangle2D> rectangles)
    {
        this.rectangles = rectangles;
    }

    /**
     * public void Draw(Graphics g)
     * 
     * Render the circle for both filled and outlined according to the states
     *
     * @param  - Graphics g is the graphics context
     * @return - void
     */
    @Override
    public void Draw(Graphics g)
    {
        Transform();
        g.setColor(super.getFillColor());
        g.fillPolygon(txCoords, tyCoords, xCoords.length);
    }

    // This is the Transform method for Polygon2D
    /**
     * Transform - rotates, increases size of Polygon and stores the new coordinates in tx and tyCoords
     * @param - none
     * @return - none
     */
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

            int tx = (int)Math.round((x * Math.cos(rads) - y * Math.sin(rads)) + centroidX + super.getXPos());
            int ty = (int)Math.round((x * Math.sin(rads) + y * Math.cos(rads)) + centroidY + super.getYPos());

            tx = (int)Math.round(tx/25.0) * 25;
            ty = (int)Math.round(ty/25.0) * 25;

            this.txCoords[i] = tx;
            this.tyCoords[i] = ty;
        }
    }

    
    /**
     * reachedBottom - checks if highest y coordinate of a polygon reaches the y coordinate of the floor
     * @param - none
     * @return - boolean
     */
    public boolean reachedBottom()
    {
        int yMax = Arrays.stream(tyCoords).max().getAsInt();
        return yMax == 425;
    }
    
    /**
     * reachedRight - checks if highest x coordinate touches the right wall.
     * @param - none
     * @return - boolean
     */

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
    
    
    /**
     * reachedLeft - checks if highest x coordinate touches the left wall.
     * @param - none
     * @return - boolean
     */
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
    
    /**
     *  collidesWith - takes a polygon's list of rectangles and compares it with this instance's list of rectangles to see if the two polygons touch/ collide
     *  @param - Polygon2D
     *  @return - boolean
    */
    public boolean collidesWith(Polygon2D other) //other represents the blocks that are processed
    {
        for(Rectangle2D r1 : this.rectangles) 
        {
            for(Rectangle2D r2 : other.getRectangles())
            {
                boolean xIntersect = intervalIntersect(r1.getXPos(), r1.getXPos() + r1.getWidth(),
                r2.getXPos(), r2.getXPos() + r2.getWidth());
                
                boolean yIntersect = intervalIntersect(r1.getYPos(), r1.getYPos() + r1.getHeight(),
                r2.getYPos(), r2.getYPos() + r2.getWidth());
                
                if(xIntersect && yIntersect)
                {
                    return true;
                }
                
            }
        }
        return false;
    }
    
    public boolean intervalIntersect(int a, int b, int c, int d)
    {
        boolean intersect = true;
        if ((a > d) || (c > b))
        {
            intersect = false;
        }
        return intersect;
    }

    /**
     * clone - creates a brand new instance of a polygon with same characteristics
     * @param - none
     * @return - Polygon2D
     */
    public abstract Polygon2D clone();

}

