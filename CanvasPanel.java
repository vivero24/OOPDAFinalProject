/**
 * 2D CanvasPanel
 * 
 *
 * @author (Enter a name)
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
import javax.swing.Timer;

public class CanvasPanel extends JPanel
{
    private final static int X_CORNER = 25;
    private final static int Y_CORNER = 25;

    private final static int GAME_WIDTH = 250;
    private final static int CANVAS_WIDTH = 450;
    private final static int CANVAS_HEIGHT = 400;

    //ADDED
    private final static int GRID_ROWS = 20;
    private final static int GRID_COLUMNS = 10;
    public final static int BOX_SIZE = GAME_WIDTH / GRID_COLUMNS;

    private List <Polygon2D> blocksList;
    private List <Polygon2D> processedBlocks;

    private int frameNumber;
    private String scoreText = "Score:";
    private int score = 0;
    private RandomInteger blockRng;
    private RandomInteger colorRng;
    private Polygon2D currentBlock = null;
    private double negativeAngle;
    private double positiveAngle;

    private Thread musicThread;

    public CanvasPanel()
    {

        //gridColumns = columns;
        //gridBoxSize = GAME_WIDTH / gridColumns;
        //gridRows = GAME_HEIGHT / gridBoxSize;

        // Callback for keyboard events
        this.setFocusable(true);
        this.addKeyListener(new myActionListener());
        System.out.println("keyboard event registered");


        this.blocksList = new ArrayList<>();
        this.processedBlocks = new ArrayList<>();
        this.negativeAngle = 0.0;
        this.positiveAngle = 0.0;

        //adding necessary shapes 
        //t block coords
        int [] tblockXcoords = {75, 150, 150, 125, 125, 100, 100, 75};
        int [] tblockYcoords = {-50, -50, -25, -25,0, 0, -25, -25};
        blocksList.add(new Tetromino_T(Shape2D.BLACK, 0, 0, tblockXcoords, tblockYcoords));

        //s block coords
        int [] sblockXcoords = {100, 150, 150, 125, 125, 75, 75, 100};
        int [] sblockYcoords = {-50, -50, -25, -25, 0, 0, -25, -25};
        blocksList.add(new Tetromino_S(Shape2D.BLACK, 0, 0, sblockXcoords, sblockYcoords));

        //z block coords
        int [] zblockXcoords = {50,100, 100, 125, 125, 75, 75, 50};
        int [] zblockYcoords = {-50, -50, -25, -25, 0, 0, -25, -25};
        blocksList.add(new Tetromino_Z(Shape2D.BLACK, 0, 0, zblockXcoords, zblockYcoords));

        //L block coords
        int [] LblockXcoords = {75, 100, 100, 125, 125, 75};
        int [] LblockYcoords = {-75, -75, -25, -25, 0, 0};
        blocksList.add(new Tetromino_L(Shape2D.BLACK, 0, 0, LblockXcoords, LblockYcoords));

        //j block coords
        int [] jblockXcoords = {100, 125, 125, 75, 75, 100};
        int [] jblockYcoords = {-75, -75, 0, 0, -25, -25};
        blocksList.add(new Tetromino_J(Shape2D.BLACK, 0, 0, jblockXcoords, jblockYcoords));

        //o block coords
        int [] oblockXcoords = {75, 125, 125, 75};
        int [] oblockYcoords = {-50, -50, 0, 0};
        blocksList.add(new Tetromino_O(Shape2D.BLACK, 0, 0, oblockXcoords, oblockYcoords));

        //i block coords
        int [] iblockXcoords = {100, 125, 125, 100};
        int [] iblockYcoords = {-100, -100, 0, 0};
        blocksList.add(new Tetromino_I(Shape2D.BLACK, 0, 0, iblockXcoords, iblockYcoords));

        this.blockRng = new RandomInteger(0,blocksList.size());
        this.colorRng = new RandomInteger(0, Shape2D.COLORS.length);

        this.currentBlock = generateShape();
        
        playMusic();
        Timer renderLoop = new Timer(30, (ActionEvent ev) -> {frameNumber++; Simulate(); repaint();});
        renderLoop.start();
    }

    public void Simulate()
    {   
        currentBlock.Move(0,5);
        boolean collided = false;

        for(Polygon2D p : processedBlocks)
        {
            if(currentBlock.collidesWith(p))
            {
                collided = true;
                System.out.println("Collided");
                break;
            }
        }

        if(collided || currentBlock.reachedBottom())
        {    
            currentBlock.Move(0,-5);
            if(currentBlock.reachedBottom())
            {
                currentBlock.Move(0,425 - Arrays.stream(currentBlock.gettYcoords()).max().getAsInt());
            }
            
            processedBlocks.add(currentBlock);
            generateShape();
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
        g.fillRect(0,0,GAME_WIDTH + 2 * X_CORNER, CANVAS_HEIGHT + 2 * Y_CORNER); //draw the black border

        // Extend canvas to the right with new width and fill the top and bottom borders
        g.setColor(Color.BLACK);
        g.fillRect(X_CORNER + GAME_WIDTH, Y_CORNER - 25, CANVAS_WIDTH - GAME_WIDTH, CANVAS_HEIGHT + 50);

        // Set canvas background to grey
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(X_CORNER, Y_CORNER, GAME_WIDTH, CANVAS_HEIGHT); //make the canvas white

        // Draw the grid (10 columns x 20 rows)
        g.setColor(Color.DARK_GRAY); // Grid color
        int cellWidth = GAME_WIDTH / 10;  // 10 columns
        int cellHeight = CANVAS_HEIGHT / 20; // 20 rows

        //Added temp grid
        for (int i = 0; i <= GRID_ROWS; i++) {
            g.drawLine(X_CORNER, Y_CORNER + i * BOX_SIZE, 
                X_CORNER + GAME_WIDTH, Y_CORNER + i * BOX_SIZE);
        }
        for (int j = 0; j <= GRID_COLUMNS; j++) {
            g.drawLine(X_CORNER + j * BOX_SIZE, Y_CORNER, 
                X_CORNER + j * BOX_SIZE, Y_CORNER + CANVAS_HEIGHT);
        }

        // Set the font for drawing text
        g.setFont(new Font("Consolas", Font.PLAIN, 25));
        g.setColor(Color.WHITE);
        g.drawString("Score:", 350, 100);

        for (Polygon2D p: this.processedBlocks)
        {
            p.Draw(g);
        }

        currentBlock.Draw(g);
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

    public Polygon2D generateShape()
    {
        int blockRandNum = blockRng.Compute();
        currentBlock = blocksList.get(blockRandNum).clone();

        int colorRandNum = colorRng.Compute();
        currentBlock.setfillColor(colorRandNum);

        return currentBlock;
    }

    public static int getCanvasWidth()
    {
        return GAME_WIDTH;
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
                    if(currentBlock.reachedLeft())
                    {
                        currentBlock.Move(25,0);
                    }
                    break;
                case KeyEvent.VK_D:
                    System.out.println("pressed 'd' key");
                    currentBlock.Move(25,0);
                    if(currentBlock.reachedRight())
                    {
                        currentBlock.Move(-25,0);
                    }
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