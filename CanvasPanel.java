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
import java.util.Arrays;
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
    
    private List <Polygon2D> blocksList;
    private List <Polygon2D> processedBlocks;
    
    private int frameNumber;
    private RandomInteger blockRng;
    private RandomInteger colorRng;
    private Polygon2D currentBlock = null;
    private double negativeAngle;
    private double positiveAngle;
    
    
    private int gridRows;
    private int gridColumns;
    private int gridBoxSize;

    private Thread musicThread;
    
    
    public CanvasPanel()
    {

        //gridColumns = columns;
        //gridBoxSize = GAME_WIDTH / gridColumns;
        //gridRows = GAME_HEIGHT / gridBoxSize;

        // Create some shapes, they should be in a List
              
        // Callback for keyboard events
        this.setFocusable(true);
        this.addKeyListener(new myActionListener());
        System.out.println("keyboard event registered");
        
        // Create a render loop
        // Create a Swing Timer that will tick 30 times a second
        // At each tick the ActionListener that was registered via the lambda expression will be invoked
        playMusic();        
        Timer renderLoop = new Timer(30, (ActionEvent ev) -> {frameNumber++; Simulate(); repaint();}); // lambda expression for ActionListener implements actionPerformed
        renderLoop.start();
        
        this.blocksList = new ArrayList<>();
        this.processedBlocks = new ArrayList<>();
        this.negativeAngle = 0.0;
        this.positiveAngle = 0.0;
        
        //adding necessary shapes 
        //t block coords
        int [] tblockXcoords = {75, 150, 150, 125, 125, 100, 100, 75};
        int [] tblockYcoords = {-50, -50, -25, -25,0, 0, -25, -25};
        blocksList.add(new Polygon2D(Shape2D.BLACK, 0, 0, tblockXcoords, tblockYcoords));
        
        //s block coords
        int [] sblockXcoords = {100, 150, 150, 125, 125, 75, 75, 100};
        int [] sblockYcoords = {-50, -50, -25, -25, 0, 0, -25, -25};
        blocksList.add(new Polygon2D(Shape2D.BLACK, 0, 0, sblockXcoords, sblockYcoords));
        
        //z block coords
        int [] zblockXcoords = {50,100, 100, 125, 125, 75, 75, 50};
        int [] zblockYcoords = {-50, -50, -25, -25, 0, 0, -25, -25};
        blocksList.add(new Polygon2D(Shape2D.BLACK, 0, 0, zblockXcoords, zblockYcoords));
        
        //L block coords
        int [] LblockXcoords = {75, 100, 100, 125, 125, 75};
        int [] LblockYcoords = {-75, -75, -25, -25, 0, 0};
        blocksList.add(new Polygon2D(Shape2D.BLACK, 0, 0, LblockXcoords, LblockYcoords));
        
        //j block coords
        int [] jblockXcoords = {100, 125, 125, 75, 75, 100};
        int [] jblockYcoords = {-75, -75, 0, 0, -25, -25};
        blocksList.add(new Polygon2D(Shape2D.BLACK, 0, 0, jblockXcoords, jblockYcoords));
        
        
        
          
        
        this.blockRng = new RandomInteger(0,blocksList.size());
        this.colorRng = new RandomInteger(0, Shape2D.COLORS.length);
        
    }
    
    public void Simulate()
    {   
            //generate a random number, grab a shape from the list, then move it down
            if(currentBlock == null)
            {
                int blockRandNum = blockRng.Compute();
                currentBlock = blocksList.get(blockRandNum);
                int colorRandNum = colorRng.Compute();
                currentBlock.setfillColor(colorRandNum);
            
            } 
            
            if(currentBlock.reachedRight())
            {
                currentBlock.Move(425 - Arrays.stream(currentBlock.gettXcoords()).max().getAsInt(), 0);
            }
            
            if(currentBlock.reachedLeft())
            {
                currentBlock.Move(25 - Arrays.stream(currentBlock.gettXcoords()).min().getAsInt() , 0);
            }
        
            
            if(currentBlock.reachedBottom()) //checking if shape reaches bottom
            {
                currentBlock.Move(0,725 -  Arrays.stream(currentBlock.gettYcoords()).max().getAsInt());
                processedBlocks.add(currentBlock); // allows shapes to stay put at the bottom
                int randnum = blockRng.Compute(); 
                currentBlock = blocksList.get(randnum).clone(); //pick a new shape and create a seperate instance
                int colorRandNum = colorRng.Compute(); //generate a random color
                currentBlock.setfillColor(colorRandNum);
                
            }
            else
            {
                currentBlock.Move(0,5);
                
                
            }
                
                   
        }
        

    // This method is called by renderloop
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        

        // Set window background to black
        g.setColor(Color.BLACK);
        g.fillRect(0,0,CANVAS_WIDTH + 2 * X_CORNER, CANVAS_HEIGHT + 2 * Y_CORNER); //draw the black border
        
        // Set canvas background to grey
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(X_CORNER, Y_CORNER, CANVAS_WIDTH, CANVAS_HEIGHT); //make the canvas white

        
        
        for (Polygon2D p: this.processedBlocks)
        {
            p.Draw(g);
        }
        
        if(currentBlock != null)
        {
            currentBlock.Draw(g);
        }
        
        
        
    }
    
    public void playMusic()
    {
        audioPlayer musicPlayer = new audioPlayer("TetrisTheme.wav");
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
                case KeyEvent.VK_S:
                    System.out.println("pressed 's' key ");
                    currentBlock.Move(0,25);
                    break;
                case KeyEvent.VK_A:
                    System.out.println("pressed 'a' key");
                    currentBlock.Move(-25,0);
                    break;
                case KeyEvent.VK_D:
                    System.out.println("pressed 'd' key");
                    currentBlock.Move(25,0);
                    break;
                case KeyEvent.VK_Q:
                    System.out.println("pressed 'q' key");
                    negativeAngle -= 90.0;
                    negativeAngle = (negativeAngle - 360) % 360;
                    currentBlock.setZRotate(negativeAngle);
                    break;
                case KeyEvent.VK_E:
                    System.out.println("pressed 'e' key");
                    positiveAngle += 90.0;
                    positiveAngle = (positiveAngle + 360) % 360;
                    currentBlock.setZRotate(positiveAngle);
                    break;                    
                case KeyEvent.VK_SPACE:
                    System.out.println("press space key");
                    break;
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