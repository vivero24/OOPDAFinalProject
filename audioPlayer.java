/*
 *
 * @author (Cristian Vivero and Tymon Muzyk)
 * @version (12/16/24)
 */
import javax.sound.sampled.*;
import java.io.*;

public class audioPlayer implements Runnable {
    private String soundFilePath;

    public audioPlayer(String soundFilePath) 
    {
        this.soundFilePath = soundFilePath;
    }

    @Override
    public void run() 
    {
        playSound(soundFilePath);
    }

    private void playSound(String filePath) 
    {
        try 
        {
            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.addLineListener(new LineListener()
                {
                    @Override
                    public void update(LineEvent event) 
                    {
                        if (event.getType() == LineEvent.Type.STOP)
                        {
                            clip.close();
                        }
                    }
                });

            while (clip.isOpen()) 
            {
                if (Thread.currentThread().isInterrupted()) 
                {
                    clip.close();
                    Thread.currentThread().interrupt(); 
                    return;
                }
            }
        } catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    }
}

