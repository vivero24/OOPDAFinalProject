/**
 * 2D CanvasPanel
 * 
 *
 * @author (Prof R)
 * @version (v1.0 11-17-22)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CanvasPanel extends JPanel
{
    private final static int X_CORNER = 25;
    private final static int Y_CORNER = 25;
    private final static int CANVAS_WIDTH = 600;
    private final static int CANVAS_HEIGHT = 800;
    private final static int GAME_WIDTH = 400;
    private final static int GAME_HEIGHT = 800;
    private List <Shape2D> shapesList;
    private int frameNumber;
    private RandomInteger rng;
    private Shape2D currentShape = null;
    
    private int gridRows;
    private int gridColumns;
    private int gridBoxSize;

    private Thread musicThread;
    
    
    public CanvasPanel(int columns)
    {

        gridColumns = columns;
        gridBoxSize = GAME_WIDTH / gridColumns;
        gridRows = GAME_HEIGHT / gridBoxSize;

        // Create some shapes, they should be in a List
              
        // Callback for keyboard events
        this.setFocusable(true);
        this.addKeyListener(new myActionListener());
        System.out.println("keyboard event registered");
        
        // Create a render loop
        // Create a Swing Timer that will tick 30 times a second
        // At each tick the ActionListener that was registered via the lambda expression will be invoked
        Timer renderLoop = new Timer(30, (ActionEvent ev) -> {frameNumber++; Simulate(); repaint();}); // lambda expression for ActionListener implements actionPerformed
        renderLoop.start();
        this.shapesList = new ArrayList<>();
        
        
        int [] tBlockXcoord = {50,80,80,70,70,60,60,50};
        int [] tBlockYcoord = {50,50,60,60,70,70,60,60};
        
        int [] sBlockXcoord = {100,120,120,110,110,90,90,100};
        int [] sBlockYcoord = {50, 50, 60, 60, 70, 70, 60, 60};
          
        shapesList.add(new Polygon2D(Shape2D.RED, 0, 0, tBlockXcoord, tBlockYcoord));
        shapesList.add(new Polygon2D(Shape2D.BLUE, 0, 0, sBlockXcoord, sBlockYcoord));
        BufferedImage[] TetrisJBlock = new BufferedImage[1];
        try
        {
            TetrisJBlock[0] = ImageIO.read(new File("Tetris_J_1.png"));
        }
        catch(IOException ie)
        {
            ie.printStackTrace();
        }
        shapesList.add(new Sprite2D(350,200,TetrisJBlock)); 
        this.rng = new RandomInteger(0,shapesList.size());
       
    }
    
    public void Simulate()
    {   
            //generate a random number, grab a shape from the list, then move it down
            if(currentShape == null)
            {
                int randnum = rng.Compute();
                currentShape = shapesList.get(randnum);
            }
            
            if(currentShape  != null)
            {
                currentShape.Move(0,5);
                if(currentShape.reachedBottom(725) == true)
                {
                    currentShape = null;
                }
            }
            
                   
        }
        

    // This method is called by renderloop
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        for (int y = 0; y < gridRows; y++)
        {
            for (int x = 0; x < gridColumns; x++)
            {
              g.Rectangle2D(x + gridBoxSize, y * gridBoxSize, gridBoxSize, gridBoxSize)  
            }
        }

        // Set window background to black
        g.setColor(Color.BLACK);
        g.fillRect(0,0,CANVAS_WIDTH + 2 * X_CORNER, CANVAS_HEIGHT + 2 * Y_CORNER); //draw the black border
        
        // Set canvas background to grey
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(X_CORNER, Y_CORNER, CANVAS_WIDTH, CANVAS_HEIGHT); //make the canvas white

        
        
        for (Shape2D s: this.shapesList)
        {
            s.Draw(g);
        }
        
    }
    
    public void playMusic()
{
audioPlayer musicPlayer = new audioPlayer("TetrisTheme.wav");
//audioPlayer musicPlayer = new audioPlayer("accoustic-guitar.wav");
musicThread = new Thread(musicPlayer);
musicThread.start();
}
public void stopMusic()
{
if (musicThread != null)
{
musicThread.interrupt();
musicThread = null;
}
}
    
    public static int getCanvasWidth()
    {
        return CANVAS_WIDTH;
    }

    public static int getCanvasHeight()
    {
        return CANVAS_HEIGHT;
    }

    public static int getCanvasXBorder()
    {
        return X_CORNER;
    }

    public static int getCanvasYBorder()
    {
        return Y_CORNER;
    }
    
    
    
    public class myActionListener extends KeyAdapter 
    {
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    System.out.println("press up arrow");
                break;
                case KeyEvent.VK_DOWN:
                    System.out.println("press down arrow");
                    currentShape.Move(0,10);
                break;
                case KeyEvent.VK_LEFT:
                    System.out.println("press left arrow");
                    currentShape.Move(-10,0);
                    
                break;
                case KeyEvent.VK_RIGHT:
                    System.out.println("press right arrow");
                    currentShape.Move(10,0);
                break;
                case KeyEvent.VK_SPACE:
                    System.out.println("press space key");
                default:
                    System.out.println("press some other key besides the arrow keys");
            }
        }
        public void keyReleased(KeyEvent e)
        {
            System.out.println("released");
        }
    }
    
    
    
    
    

}