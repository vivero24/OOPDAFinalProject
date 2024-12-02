
/**
 * Write a description of class StarPoly2D here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StarPoly2D extends Polygon2D
{
    private static int[] xCoords = {0, 2 , 10, 2, 0, -2, -10, -2};
    private static int[] yCoords = {-10, -2, 0 ,2, 10, 2, 0, -2};
    /**
     * Constructor for objects of class StarPoly2D
     */
    public StarPoly2D(int colorIndex, int xPosition, int yPosition)
    {
        super(colorIndex, xPosition, yPosition, xCoords, yCoords);        
    }

    
}
