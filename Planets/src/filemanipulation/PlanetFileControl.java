package filemanipulation;
import elements.Planet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/** 
 * @author Alex Guirao Lopez <aguiraol2021@cepnet.net>
 */
public class PlanetFileControl 
{
    final static String PATH="datafiles/planets.bin";
    
    /**
     * Busca el nombre de un planeta en un archivo y comprueba si ya existe.
     * @param planetName nombre del planeta a comprobar.
     * @return TRUE si el planeta ya está en el archivo.
     */
    private static boolean seekPlanetName(String planetName)
    {
        boolean found=false;
        String checkName;
        byte [] bName;
        int pos=0;
        File file=new File(PATH);
        try {
            if (file.exists())
            {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            int planetAmount;
            if (Planet.planetCount!=0) //El planet count es local, al para el programa se resetea.
            {
                planetAmount=(int)file.length()/Planet.size();
            }
            else
            {
                planetAmount=0;
            }
            
            while(pos<planetAmount && found==false)
            {
                raf.seek(pos*Planet.size());
                bName=new byte[Planet.NAME_MAX_LENGTH];
                raf.read(bName);
                checkName=new String(bName);
                pos++;
                if (checkName.equalsIgnoreCase(planetName))
                {
                    found=true;
                }
            }
            raf.close();
            }
        }
        catch (FileNotFoundException ex) 
        {
            System.out.println("No se puede encontrar el archivo" + ex);
        }
        catch(IOException ex)
        {
            System.out.println("No se puede acceder al archivo" + ex);
        }
        return found;
    }
    
    public static void writePlanet(Planet planet)
    {
        try {
            if (!seekPlanetName(planet.getFormattedName()))
            {
            RandomAccessFile raf = new RandomAccessFile(PATH, "rw");
            raf.seek(Planet.planetCount*Planet.size());
            raf.write(planet.getFormattedName().getBytes(Charset.defaultCharset()));
            raf.writeInt(planet.getDiameter());
            raf.writeFloat(planet.getSunDistance());
            Planet.planetCount++;
            raf.close();
            }
            else
            {
                System.out.println("ERROR: Un planeta con el mismo nombre está registrado.");
            }
        } 
        catch (IOException ex) 
        {
            System.out.println("No se ha podido acceder al archivo");
        }
    }
    
    public static ArrayList<String> readPlanetNameList()
    {
        ArrayList<String> nameList = new ArrayList();
        File file = new File (PATH);
        String name;
        byte [] bName;
        if (file.exists())
        {
            int planetAmount= (int)file.length()/Planet.size();
            try {
                RandomAccessFile raf = new RandomAccessFile(file, "r");
                for (int i = 0;i<planetAmount;i++)
                {
                    raf.seek(i);
                    bName=new byte[Planet.NAME_MAX_LENGTH]; //ERROR EN OPCION 3 
                    raf.read(bName);
                    name= new String(bName).trim();
                    nameList.add(name);
                }
                
                raf.close();
            } 
            catch (IOException ex)
            {
                System.out.println("No se pudo acceder al archivo.");
            }
        }
        
        
        return nameList;
    }
    
}
