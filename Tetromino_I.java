import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class Tetronimo_I here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tetromino_I extends Polygon2D
{
    private List <Rectangle2D> iRectangles;
    public Tetromino_I(int fillColorIndex, int xPos, int yPos, int[] xCoords, int[] yCoords)
    {
        super(fillColorIndex, xPos, yPos,xCoords, yCoords);
        this.iRectangles = new ArrayList<>();
        iRectangles.add(new Rectangle2D(super.getFillColorIndex(), super.gettXcoords()[0], super.gettXcoords()[0], 25, 100));
    }
    
    public List <Rectangle2D> getRectangles()
    {
        return iRectangles;
    }

    
}
