
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
    
    @Override
    public void Draw(Graphics g)
    {
        g.setColor(this.getFillColor());
        g.fillRect(this.getXPos(), this.getYPos(), this.width, this.height);
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public void setHeight()
    {
        this.height = height;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public void setWidth()
    {
        this.width = width;
    }
    
    
}
