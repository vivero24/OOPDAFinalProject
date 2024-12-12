import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class Tetronimo_S here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tetromino_S extends Polygon2D
{
    private List <Rectangle2D> sRectangles;
    
    public Tetromino_S(int fillColorIndex, int xPos, int yPos, int[] xCoords, int[] yCoords)
    {
        super(fillColorIndex, xPos, yPos,xCoords, yCoords);
        this.sRectangles = new ArrayList<>();
        sRectangles.add(new Rectangle2D(super.getFillColorIndex(), super.gettXcoords()[0], super.gettYcoords()[0], 50, 25));
        sRectangles.add(new Rectangle2D(super.getFillColorIndex(), super.gettXcoords()[6], super.gettYcoords()[6],50, 25));
    }
    
    public List <Rectangle2D> getRectangles()
    {
        return sRectangles;
    }

    
}
