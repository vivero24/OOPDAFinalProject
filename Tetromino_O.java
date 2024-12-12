import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class Tetronimo_O here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tetromino_O extends Polygon2D
{
    
    public Tetromino_O(int fillColorIndex, int xPos, int yPos, int[] xCoords, int[] yCoords)
    {
        super(fillColorIndex, xPos, yPos,xCoords, yCoords);
        super.getRectangles().add(new Rectangle2D(super.getFillColorIndex(), super.gettXcoords()[0], super.gettYcoords()[0], 50, 50));
    }
    
    @Override
    public Polygon2D clone()
    {
        return new Tetromino_O(super.getFillColorIndex(), super.getXPos(), super.getYPos(), super.getXcoords(), super.getYcoords());
    }

    
}
