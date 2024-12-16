import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class Tetronimo_I here.
 *
 * @author (Cristian Vivero and Tymon Muzyk)
 * @version (12/16/24)
 */
public class Tetromino_I extends Polygon2D
{
    
    public Tetromino_I(int fillColorIndex, int xPos, int yPos, int[] xCoords, int[] yCoords)
    {
        super(fillColorIndex, xPos, yPos,xCoords, yCoords);
        super.getRectangles().add(new Rectangle2D(super.getFillColorIndex(), super.gettXcoords()[0], super.gettXcoords()[0], 25, 100));
    }
    
    @Override
    public Polygon2D clone()
    {
        return new Tetromino_I(super.getFillColorIndex(), super.getXPos(), super.getYPos(), super.getXcoords(), super.getYcoords());
    }

    
}
