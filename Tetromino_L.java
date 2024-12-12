import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class Tetromino_L here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tetromino_L extends Polygon2D
{
    
    private List <Rectangle2D> lRectangles;
    public Tetromino_L(int fillColorIndex, int xPos, int yPos, int[] xCoords, int[] yCoords)
    {
        super(fillColorIndex, xPos, yPos,xCoords, yCoords);
        this.lRectangles = new ArrayList<>();
        lRectangles.add(new Rectangle2D(super.getFillColorIndex(), super.gettXcoords()[0], super.gettYcoords()[0], 25, 75));
        lRectangles.add(new Rectangle2D(super.getFillColorIndex(), super.gettXcoords()[2], super.gettYcoords()[2], 25,25));
    }
    
    public List <Rectangle2D> getRectangles()
    {
        return lRectangles;
    }

    
}
