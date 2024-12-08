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
    private List <Shape2D> processedShapes;
    
    private int frameNumber;
    private RandomInteger shapeRng;
    private RandomInteger colorRng;
    private Shape2D currentShape = null;
    private double negativeShapeAngle = 0.0;
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
        
        this.shapesList = new ArrayList<>();
        this.processedShapes = new ArrayList<>();
        
        //adding necessary shapes 
        //t block coords
        int [] tblockXcoords = {75, 150, 150, 125, 125, 100, 100, 75};
        int [] tblockYcoords = {-50, -50, -25, -25,0, 0, -25, -25};
        shapesList.add(new Polygon2D(Shape2D.BLACK, 0, 0, tblockXcoords, tblockYcoords));
        
        //s block coords
        int [] sblockXcoords = {100, 150, 150, 125, 125, 75, 75, 100};
        int [] sblockYcoords = {-50, -50, -25, -25, 0, 0, -25, -25};
        shapesList.add(new Polygon2D(Shape2D.BLACK, 0, 0, sblockXcoords, sblockYcoords));
        
        //z block coords
        int [] zblockXcoords = {50,100, 100, 125, 125, 75, 75, 50};
        int [] zblockYcoords = {-50, -50, -25, -25, 0, 0, -25, -25};
        shapesList.add(new Polygon2D(Shape2D.BLACK, 0, 0, zblockXcoords, zblockYcoords));
        
        //L block coords
        int [] LblockXcoords = {75, 100, 100, 125, 125, 75};
        int [] LblockYcoords = {-75, -75, -25, -25, 0, 0};
        
        //j block coords
        int [] jblockXcoords = {100, 125, 125, 75, 75, 100};
        int [] jblockYcoords = {-75, -75, 0, 0, -25, -25};
        
        //adding o block
        shapesList.add(new Rectangle2D(Shape2D.BLACK, 200, -50, 50, 50));
        
        //adding I block
        shapesList.add(new Rectangle2D(Shape2D.BLACK, 200, -100, 25, 100));
        
          
        
        this.shapeRng = new RandomInteger(0,shapesList.size());
        this.colorRng = new RandomInteger(0, Shape2D.COLORS.length);
        
    }
    
    public void Simulate()
    {   
            //generate a random number, grab a shape from the list, then move it down
            if(currentShape == null)
            {
                int shapeRandNum = shapeRng.Compute();
                currentShape = shapesList.get(shapeRandNum);
                int colorRandNum = colorRng.Compute();
                currentShape.setfillColor(colorRandNum);
            
            }   
        
            
            if(currentShape.reachedBottom() == false)
            {
                currentShape.Move(0,5);
            }
            else
            {
                processedShapes.add(currentShape);
                int randnum = shapeRng.Compute();
                currentShape = shapesList.get(randnum).clone();
                int colorRandNum = colorRng.Compute();
                currentShape.setfillColor(colorRandNum);
                
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

        
        
        for (Shape2D s: this.processedShapes)
        {
            s.Draw(g);
        }
        
        if(currentShape != null)
        {
            currentShape.Draw(g);
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
                case KeyEvent.VK_S:
                    System.out.println("pressed 's' key ");
                    currentShape.Move(0,25);
                    break;
                case KeyEvent.VK_A:
                    System.out.println("pressed 'a' key");
                    currentShape.Move(-25,0);
                    break;
                case KeyEvent.VK_D:
                    System.out.println("pressed 'd' key");
                    currentShape.Move(25,0);
                    break;
                case KeyEvent.VK_Q:
                    System.out.println("pressed 'q' key");
                    negativeShapeAngle -= 90.0;
                    currentShape.setZRotate(negativeShapeAngle);
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