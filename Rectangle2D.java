
/**
 * Write a description of class Rectangle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.geom.*;
public class Rectangle2D extends Shape2D
{
    private int height;
    private int width;

    public Rectangle2D(int fillColorIndex, int xPos, int yPos, int width, int height)
    {
        super(fillColorIndex, xPos, yPos);
        this.height = height;
        this.width = width;
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
        g.setColor(this.getFillColor());
        g.fillRect(this.getXPos(), this.getYPos(), this.width, this.height);
    }

    /**
     * getHeight - returns rectangle height
     * @param - none
     * @return - int, height
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * setHeight - sets height of rectangle
     * @param - int height
     * @return - none
     */
    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
     * getWidth - gets width of rectangle
     * @param - none
     * @return - int, width
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * setWidth - sets width of rectangle
     * 
     * @param - int width
     * @return - none
     */
    public void setWidth(int width)
    {
        this.width = width;
    }

    public boolean intersects(Rectangle2D r) {
        int width1 = this.width;
        int height1 = this.height;

        int width2 = r.getWidth();
        int height2 = r.getHeight();

        // Check for non-positive dimensions
        if (width1 <= 0  || height1 <= 0  || width2 <= 0  ||height2 <= 0) {
            return false;
        }

        int x1 = getXPos();
        int y1 = getYPos();

        int x2 = r.getXPos();
        int y2 = r.getYPos();

        // Compute the bottom-right corners of both rectangles
        int right1 = x1 + width1;
        int bottom1 = y1 + height1;

        int right2 = x2 + width2;
        int bottom2 = y2 + height2;

        // Check for overlap or edge touching
        return !(right1 <= x2 || right2 <= x1 || bottom1 <= y2 || bottom2 <= y1);
    }
}
