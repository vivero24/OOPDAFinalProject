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
    //private final static int CANVAS_WIDTH = 400;
    //private final static int CANVAS_HEIGHT = 400;
    private final static int CANVAS_WIDTH = 600;
    private final static int CANVAS_HEIGHT = 600;
    
    // private [] Shapes;   // ????????????????
    private List <Shape2D> shapesList;
    private int frameNumber;
    
    
    public CanvasPanel()
    {
        // Create some shapes, they should be in a List
       //circle1 = new Circle();  // Construct a circle with the default color
                           //col x  y   dia
        //circle2 = new Circle(7, 20, 20, 40); // Construct a circle with color index 7
        
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
        // Mountain
        int [] xCoords = {25, 150, 200, 300, 400, 500, 625};
        int [] yCoords = {490, 390, 420, 350, 430, 300, 490};
        shapesList.add(new Polygon2D(Shape2D.BROWN, 0, 0, xCoords ,yCoords)); // Mountain, shape 0
        // Grass and road
        shapesList.add(new Rectangle2D(Shape2D.GREEN, 25, 575, 600, 50)); // Green rectangle, shape 1
        shapesList.add(new Rectangle2D(Shape2D.BLACK, 25, 525, 600, 50)); // Black rectangle, shape 2
        shapesList.add(new Rectangle2D(Shape2D.WHITE, 25, 520, 600, 10)); // White rectangle, shape 3
        shapesList.add(new Rectangle2D(Shape2D.BLACK, 25, 490, 600, 30)); // Black rectangle, shape 4
        shapesList.add(new StarPoly2D(6, 200, 300)); // Star shape 5
        //shapesList.add(new Circle2D()); // Circle shape 6
        // color xpos ypos diamater
        shapesList.add(new Circle2D(Shape2D.BLUE, 20, 20, 50)); // Circle shape 7
        shapesList.add(new Rectangle2D(Shape2D.BLUE, 100, 470, 100, 50)); // A blue reactangle, shape 8
        //shapesList.get(6).SetOutline(true);
        //shapesList.get(6).SetOutlineColor(Shape2D.WHITE);
        shapesList.add(new Oval2D(Shape2D.YELLOW, 400, 100, 40, 30)); // Yellow Oval shape 9
        // Sonic Sprite
        BufferedImage[] Sonic_Sprites = new BufferedImage[4];
        try {
            Sonic_Sprites[0] = ImageIO.read(new File("Sonic1.png"));
            Sonic_Sprites[1] = ImageIO.read(new File("Sonic2.png"));
            Sonic_Sprites[2] = ImageIO.read(new File("Sonic3.png"));
            Sonic_Sprites[3] = ImageIO.read(new File("Sonic4.png"));
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        shapesList.add(new Sprite2D(200, 515, Sonic_Sprites)); // shape 10
             
        
        
       
    }
    
    public void Simulate()
    {
        //circle1.Move(1, 2); // move the shape along via a delta in x and y
        //circle2.Move(2, 1); // move the shape along via a delta in x and y
        
        
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

        // Need to make draw polymorphic and in a List
        //circle1.Draw(g);
        //circle2.Draw(g);
        
        for (Shape2D s: this.shapesList)
        {
            s.Draw(g);
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
                break;
                case KeyEvent.VK_LEFT:
                    System.out.println("press left arrow");
                break;
                case KeyEvent.VK_RIGHT:
                    System.out.println("press right arrow");
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
