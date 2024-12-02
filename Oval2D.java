
/**
 * Write a description of class Oval here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */


import java.awt.*;
import java.awt.geom.*;
public class Oval2D extends Shape2D
{
    private int diameter1;
    private int diameter2;
    
    public Oval2D(int fillColorIndex, int xPos, int yPos, int diameter1, int diameter2)
    {
        super(fillColorIndex, xPos, yPos);
        this.diameter1 = diameter1; 
        this.diameter2 = diameter2;
    }
    
    @Override
    public void Draw(Graphics g)
    {
       g.setColor(this.getFillColor());
       g.fillOval(this.getXPos(), this.getYPos(), this.diameter1, this.diameter2);
    }
}
