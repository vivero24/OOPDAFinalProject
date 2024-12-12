import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class Tetronimo_Z here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tetromino_Z extends Polygon2D
{
    private List <Rectangle2D> zRectangles;
    
    public Tetromino_Z(int fillColorIndex, int xPos, int yPos, int[] xCoords, int[] yCoords)
    {
        super(fillColorIndex, xPos, yPos,xCoords, yCoords);
        this.zRectangles = new ArrayList<>();
        zRectangles.add(new Rectangle2D(super.getFillColorIndex(), super.gettXcoords()[0], super.gettYcoords()[0], 50, 25));
        zRectangles.add(new Rectangle2D(super.getFillColorIndex(), super.gettXcoords()[7], super.gettYcoords()[7],50 , 25));
    }

    public List <Rectangle2D> getRectangles()
    {
        return zRectangles;
    }
}
