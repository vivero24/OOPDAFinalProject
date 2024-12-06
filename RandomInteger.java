     
/**
 * Write a description of class RandomInteger here.
 *
 * @author (Cristian Vivero)
 * @version (a version number or a date)
 */
public class RandomInteger
{
    private int min;
    private int max;
    private int range; //compute only when min/max change.
    
    //default constructor
    public RandomInteger()
    {
        this.min = 0;
        this.max = 1;
    }
    
    //parametric constructor
    public RandomInteger(int min, int max)
    {
        this.min = min;
        this.max = max;
    }
    
    // setter for min
    /**
     * void setMin()- alters min variable.
     */
    public void setMin(int min)
    {
        this.min = min;
    }
    
    //getter for min.
    /**
     * int getMin()- returns min variable which is an integer.
     */
    public int getMin()
    {
        return this.min;
    }
    
    //setter for max
    /**
     * void setMax()- alters max variable
     */
    public void setMax(int max)
    {
        this.max = max;
    }
    
    //getter for max
    /**
     * int getMax()- returns max variable which is an integer.
     */
    public int getMax()
    {
        return this.max;
    }
    //generate method
    /**
     * int Compute()- caluclates range variable and then returns a random number using range/
     */
    public int Compute()
    {
        this.range = (this.max-this.min);
        int randNum = (int)(Math.random() * this.range) + this.min ;
        
        return randNum;
        
    }
    
    
    
    
}
