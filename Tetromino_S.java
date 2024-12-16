import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class Tetronimo_S here.
 *
 * @author (Cristian Vivero and Tymon Muzyk)
 * @version (12/16/24)
 */
public class Tetromino_S extends Polygon2D
{
    
    
    public Tetromino_S(int fillColorIndex, int xPos, int yPos, int[] xCoords, int[] yCoords)
    {
        super(fillColorIndex, xPos, yPos,xCoords, yCoords);
        super.getRectangles().add(new Rectangle2D(super.getFillColorIndex(), super.gettXcoords()[0], super.gettYcoords()[0], 50, 25));
        super.getRectangles().add(new Rectangle2D(super.getFillColorIndex(), super.gettXcoords()[6], super.gettYcoords()[6],50, 25));
    }
    
    @Override
    public Polygon2D clone()
    {
        return new Tetromino_S(super.getFillColorIndex(), super.getXPos(), super.getYPos(), super.getXcoords(), super.getYcoords());
    }

    
}
