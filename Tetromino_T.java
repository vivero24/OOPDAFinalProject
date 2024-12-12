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
    
    /**
     * Constructor for objects of class Tetronimo_T
     */
    public Tetromino_T(int fillColorIndex, int xPos, int yPos, int[] xCoords, int[] yCoords)
    {
        super(fillColorIndex, xPos, yPos,xCoords, yCoords);
        super.getRectangles().add(new Rectangle2D(super.getFillColorIndex(), super.gettXcoords()[0], super.gettYcoords()[0], 75, 25));
        super.getRectangles().add(new Rectangle2D(super.getFillColorIndex(),  super.gettXcoords()[6], super.gettYcoords()[6], 25, 25));
    }

    @Override
    public Polygon2D clone()
    {
        return new Tetromino_T(super.getFillColorIndex(), super.getXPos(), super.getYPos(), super.getXcoords(), super.getYcoords());
    }
    
}
