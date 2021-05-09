package sound;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * 
 * @author Alex Guirao López <aguiraol2021@cepnet.net>
 */
public class Sound 
{
    /**
    * Activa el audio del programa.
    */
    public static void startAudio() 
    {
        try {
            InputStream input = new FileInputStream("sound/music.wav");
            try {
                AudioStream as = new AudioStream(input);
                AudioPlayer.player.start(as);
            } 
            catch (IOException ex) 
            {
                System.out.println("No se pudo acceder al archivo"+ex);
                ex.printStackTrace();
            }
        } 
        catch (FileNotFoundException ex) 
        {
            System.out.println("No se pudo encontrar el archivo"+ex);
            ex.printStackTrace();
        }
    }
}
