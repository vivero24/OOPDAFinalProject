import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class Tetronimo_T here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tetromino_T extends Polygon2D
{
    private List <Rectangle2D> tRectangles;
    
    /**
     * Constructor for objects of class Tetronimo_T
     */
    public Tetromino_T(int fillColorIndex, int xPos, int yPos, int[] xCoords, int[] yCoords)
    {
        super(fillColorIndex, xPos, yPos,xCoords, yCoords);
        this.tRectangles = new ArrayList<>();
        this.tRectangles.add(new Rectangle2D(super.getFillColorIndex(), super.gettXcoords()[0], super.gettYcoords()[0], 75, 25));
        this.tRectangles.add(new Rectangle2D(super.getFillColorIndex(),  super.gettXcoords()[6], super.gettYcoords()[6], 25, 25));
    }

    public List <Rectangle2D> getRectangles()
    {
        return this.tRectangles;
    }
    
}
