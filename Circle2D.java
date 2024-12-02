
/**
 * Circle Class
 *
 * @author (Prof R)
 * @version (v1.0, 3/26/24)
 */
import java.awt.*;
import java.awt.geom.*;
public class Circle2D extends Shape2D
{
    

    private int diameter;
    /**
     * Constructor for objects of class Circle
     */
    public Circle2D(int fillColorIndex, int xPos, int yPos, int diameter)
    {
        super(fillColorIndex,xPos,yPos);
        this.diameter = diameter;
    }

    @Override
    public void Draw(Graphics g)
    {
        g.setColor(this.getFillColor());
        g.fillOval(this.getXPos(), this.getYPos(), this.diameter, this.diameter);
    }

    
}
