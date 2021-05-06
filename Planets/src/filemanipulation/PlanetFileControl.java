package filemanipulation;
import elements.Planet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import visualfront.ConsoleColors;

/** 
 * @author Alex Guirao Lopez <aguiraol2021@cepnet.net>
 */
public class PlanetFileControl 
{
    final private static String PATH="datafiles/planets.bin";
    
    public static String getPath()
    {
        return PATH;
    } 
     //===================READ===================
    /**
     * Busca el nombre de un planeta en un archivo y comprueba si ya existe.
     * @param planetName nombre del planeta a comprobar.
     * @return TRUE si el planeta ya se encuentra.
     */
    public static boolean seekPlanetName(String planetName)
    {
        boolean found = false;
        String checkName;
        byte [] bName;
        int pos=0;
        File file=new File(PATH);
        try {
            if (file.exists())
            {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            
            int planetAmount=(int)file.length()/Planet.size();
            
            while(pos<planetAmount && found==false)
            {
                raf.seek(pos*Planet.size());
                bName=new byte[Planet.NAME_MAX_LENGTH];
                raf.read(bName);
                checkName=new String(bName);
                if (checkName.equalsIgnoreCase(planetName))
                {
                    found =true;
                }
                pos++;
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
    
    /**
     * Busca la posición de un planeta en los registros.
     * @param planetName planeta a buscar
     * @return posición del planeta en los registros.
     */
    private static int getPlanetPosition(String planetName)
    {
        int planetPosition=-1;
        String checkName;
        byte [] bName;
        int pos=0;
        File file=new File(PATH);
        try {
            if (file.exists())
            {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            
            int planetAmount=(int)file.length()/Planet.size();
            
            while(pos<planetAmount && planetPosition==-1)
            {
                raf.seek(pos*Planet.size());
                bName=new byte[Planet.NAME_MAX_LENGTH];
                raf.read(bName);
                checkName=new String(bName);
                if (checkName.equalsIgnoreCase(planetName))
                {
                    planetPosition=pos;
                }
                pos++;
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
        return planetPosition;
    }
        
    /**
     * Guarda la lista de nombres para los planetas del archivo.
     * @return lista con los nombres de los planetas en formato vertical y numerado.
     */
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
                    raf.seek(i*Planet.size());
                    bName=new byte[Planet.NAME_MAX_LENGTH]; 
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
      /**
     * Guarda la lista de nombres para los planetas del archivo.
     * @return lista con los nombres de los planetas en formato vertical y numerado.
     */
    public static ArrayList<Planet> readPlanetList()
    {
        ArrayList<Planet> planetList = new ArrayList();
        File file = new File (PATH);
        Planet planet;
        String name;
        int diameter;
        float sunDistance;
        byte [] bName;
        if (file.exists())
        {
            int planetAmount= (int)file.length()/Planet.size();
            try {
                RandomAccessFile raf = new RandomAccessFile(file, "r");
                for (int i = 0;i<planetAmount;i++)
                {
                    raf.seek(i*Planet.size());
                    bName=new byte[Planet.NAME_MAX_LENGTH]; 
                    raf.read(bName);
                    name= new String(bName).trim();
                    diameter = raf.readInt();
                    sunDistance = raf.readFloat();
                    
                    planetList.add(new Planet(name,diameter, sunDistance));
                }
                raf.close();
            } 
            catch (IOException ex)
            {
                System.out.println("No se pudo acceder al archivo.");
            }
        }
        return planetList;
    }
    
    public static String readPlanetName(int planetPosition)
    {
        File file = new File (PATH);
        String name=null;
        byte [] bName;
        if (file.exists())
        {
            try {
                RandomAccessFile raf = new RandomAccessFile(file, "r");
               
                raf.seek(planetPosition*Planet.size());
                bName=new byte[Planet.NAME_MAX_LENGTH]; 
                raf.read(bName);
                name= new String(bName).trim();
                raf.close();
            } 
            catch (FileNotFoundException ex)
            {
                System.out.println("No se pudo encontrar el archivo." +ex);
            }
            catch (IOException ex)
            {
                System.out.println("No se pudo acceder al archivo."+ex);
            }
        }
        return name;
    }
    
    /**
     * Calcula la cantidad de planetas que hay en el archivo.
     * @return cantidad de planetas ya escritos en el archivo.
     */
    public static int getPlanetAmount()
    {
        int planetAmount=0;
        File file = new File(PATH);
      
        if (file.exists())
        {
            planetAmount= (int)file.length()/Planet.size();
        }
        return planetAmount;
    }
    
    /**
     * Obtiene la información de un planeta a partir de su posición en el archivo.
     * @param planetPosition posición del planeta
     * @return Cadena de texto con la información del planeta (en formato vertical).
     */
    public static String getPlanetInfo(int planetPosition) 
    {
        RandomAccessFile raf;
        byte [] bName;
        String name;
        int diameter;
        float sunDistance;
        int [] satellitePosList = new int[Planet.MAX_SATELLITE];
        String info=null;
        try {
            raf = new RandomAccessFile(PATH,"r");
            raf.seek(planetPosition*Planet.size());
            bName=new byte[Planet.NAME_MAX_LENGTH];
            raf.read(bName);
            name= new String(bName).trim();
            diameter=raf.readInt();
            sunDistance=raf.readFloat();
            
            for (int i = 0; i<satellitePosList.length;i++)
            {
                satellitePosList[i]=raf.readInt();
            }
            raf.close();
            
            info=("Nombre: " + name+"\n"+"Diametro: " + diameter+"\n"+"Distancia al sol: " + sunDistance+"\n"+
                    "    - Satélites -"+"\n"+SatelliteFileControl.getSatelliteNames(satellitePosList));
        }
        catch (FileNotFoundException ex) 
        {
            System.out.println("No se ha podido encontrar el archivo" + ex);
        }
        catch (IOException ex) 
        {
            System.out.println("No se ha leer encontrar el archivo" + ex);
        }
        
        return info;
    }
    //===================WRITE===================
    
    /**
     * Escribe la información de un planeta en el archivo.
     * @param planet planeta que se va a registrar.
     */
    public static void writePlanet(Planet planet)
    {
        File file = new File(PATH);
        int planetAmount = getPlanetAmount();
        try {
            if (!seekPlanetName(planet.getFormattedName()))
            {
            RandomAccessFile raf = new RandomAccessFile(PATH, "rw");
            raf.seek(planetAmount*Planet.size());
            raf.write(planet.getFormattedName().getBytes(Charset.defaultCharset()));
            raf.writeInt(planet.getDiameter());
            raf.writeFloat(planet.getSunDistance());
            
            for (int i = 0;i<planet.getSatellitePosList().length;i++)
            {
                raf.writeInt(planet.getSatellitePosList()[i]);
            }
            
            raf.close();
            }
            else
            {
                System.out.println(ConsoleColors.RED+"ERROR: Un planeta con el mismo nombre está registrado.");
                time.Time.waitForSeconds(500);
            }
        } 
        catch (IOException ex) 
        {
            System.out.println("No se ha podido acceder al archivo");
        }
    }
    
    /**
     * Actualiza la lista de posiciones de satélites para un planeta.
     * @param planetName nombre del planeta
     */
    public static void updateSatellitePosList(String planetName, int satelliteListPosition)
    {
        int planetPosition = getPlanetPosition(planetName);
        int satelliteListValue;
        int pointer; //Puntero para desplazar por el archivo.
        int i=0;
        try {
            RandomAccessFile raf = new RandomAccessFile (PATH,"rw");
            pointer=planetPosition*Planet.size()+Planet.satelliteListStartIndex();
            raf.seek(pointer);
            
            do
            {
                satelliteListValue=raf.readInt();
                pointer+=Integer.BYTES;
                
                if (satelliteListValue==-1)
                {
                    pointer-=Integer.BYTES;
                    raf.seek(pointer);
                    raf.writeInt(satelliteListPosition);
                }
                i++;
            }while(satelliteListValue!=-1&&i<Planet.MAX_SATELLITE);
            
            //TRABAJAR AQUI
            
            
            
            raf.close();
        }
        catch (FileNotFoundException ex) 
        {
            System.out.println("No se ha podido encontrar el archivo");
        }
        catch (IOException ex) 
        {
            System.out.println("No se ha podido acceder al archivo");
        }
        
    }
}
