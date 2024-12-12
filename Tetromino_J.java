import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class Tetromino_J here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tetromino_J extends Polygon2D
{
    
    private List <Rectangle2D> jRectangles;

    /**
     * Constructor for objects of class Tetromino_J
     */
    public Tetromino_J(int fillColorIndex, int xPos, int yPos, int[] xCoords, int[] yCoords)
    {
        super(fillColorIndex, xPos, yPos,xCoords, yCoords);
        this.jRectangles = new ArrayList<>();
        jRectangles.add(new Rectangle2D(super.getFillColorIndex(), super.gettXcoords()[0], super.gettYcoords()[0], 25, 75));
        jRectangles.add(new Rectangle2D(super.getFillColorIndex(), super.gettXcoords()[5], super.gettYcoords()[5], 25, 25));
        
    }

    
}
