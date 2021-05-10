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
 * Librería de control de los archivos binarios de los planetas.
 * @author Alex Guirao Lopez <aguiraol2021@cepnet.net>
 */
public class PlanetFileControl
{
    public final static String PATH="datafiles/planets.bin";
    public static File file = new File(PATH);
    
    //========================================================
    //=======================READ============================
    //=========================================================
    /**
     * Calcula la cantidad de planetas que hay en el archivo.
     * @return cantidad de planetas ya escritos en el archivo.
     */
    public static int getPlanetAmount()
    {
        int planetAmount=0;
        
        if (file.exists())
        {
            long fileLength=file.length();
            int planetSize = Planet.recordSize();
            planetAmount= (int)(fileLength/planetSize);
        }
        return planetAmount;
    }
    
    /**
     * Busca la posición de un planeta en los registros.
     * @param planetName nombre del planeta a encontrar.
     * @return posición del planeta en los registros (si es -1 no está en el fichero).
     */
    public static int getPlanetPosition(String planetName)
    {
        int planetPosition=-1; //Empieza en -1 como indicador de que el planeta no está en el archivo.
        
        try {
            if (file.exists())
            {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            String checkName;
            byte [] bName;
            int planetAmount=(int)file.length()/Planet.recordSize();
            
            int loopIterator=0; 
            
            while(loopIterator<planetAmount && planetPosition==-1)
            {
                raf.seek(loopIterator*Planet.recordSize());
                bName=new byte[Planet.NAME_MAX_LENGTH];
                raf.read(bName);
                checkName=new String(bName);
                if (checkName.equalsIgnoreCase(planetName))
                {
                    planetPosition=loopIterator;
                }
                loopIterator++;
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
     * Obtén un planeta del archivo basado en su posición del registro en el archivo.
     * @param planetPosition posición del planeta en el archivo.
     * @return instancia de un planeta.
     */
    public static Planet readPlanet(int planetPosition)
    {
        Planet planet=null;
        
        try {
            RandomAccessFile raf;
            byte [] bName;
            String name;
            int diameter;
            float sunDistance;
            int [] satellitePosList = new int[Planet.MAX_SATELLITE];
            
            raf = new RandomAccessFile(PATH,"r");
            raf.seek(planetPosition*Planet.recordSize());
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
            planet = new Planet(name, diameter, sunDistance, satellitePosList);
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("No se pudo encontrar el archivo" + ex);
        }
        catch (IOException ex)
        {
            System.out.println("No se pudo acceder al archivo" + ex);
        }
        
        return planet;
    }
    
    /**
     * Guarda una lista con los planetas registrados en el archivo.
     * @return lista de los planetas.
     */
    public static ArrayList<Planet> readPlanetList()
    {
        ArrayList<Planet> planetList = new ArrayList();
        
        if (file.exists())
        {
            int planetAmount= getPlanetAmount();
          
                for (int i = 0;i<planetAmount;i++)
                {
                    planetList.add(readPlanet(i));
                }
        }
        return planetList;
    }
   

    //========================================================
    //=======================WRITE============================
    //=========================================================
    /**
     * Escribe la información de un planeta en el archivo.
     * @param planet planeta que se va a registrar.
     */
    public static void writePlanet(Planet planet)
    {
            int planetAmount = getPlanetAmount();
        try {
            if (getPlanetPosition(planet.getFormattedName())==-1)
            {
            RandomAccessFile raf = new RandomAccessFile(PATH, "rw");
            raf.seek(planetAmount*Planet.recordSize());
            
            raf.write(planet.getFormattedName().getBytes(Charset.defaultCharset()));
            raf.writeInt(planet.getDiameter());
            raf.writeFloat(planet.getSunDistance());
            
            for (int i = 0;i<planet.getSatellitePosList().length;i++)
            {
                raf.writeInt(planet.getSatellitePosList()[i]);
            }
            
            raf.close();
            
            }else{
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
     * Éste método se lanza siempre que se crea un satélite nuevo.
     * @param planetName nombre del planeta
     * @param satellitePosition 
     */
    public static void updateSatellitePosList(String planetName, int satellitePosition)
    {
        /*Posición del registro para el planeta con ese nombre.
        (Cuando se registra un nuevo planeta se tiene el control para evitar nombres duplicados)*/
        int planetPosition = getPlanetPosition(planetName); 
        
        int currentSatelliteValue; //Valor de la posición del satélite que se está comprobando actualmente.
        int pointer; //Puntero para desplazarse por el archivo.
        
        try {
            RandomAccessFile raf = new RandomAccessFile (PATH,"rw");
            //Situa el puntero en el campo donde inicia la lista de posiciones para los satélites
            pointer=planetPosition*Planet.recordSize()+Planet.satelliteListStartIndex();
            raf.seek(pointer);
            
            int loopIndex=0;
            do
            {
                currentSatelliteValue=raf.readInt();
                pointer+=Integer.BYTES; //Avanza el puntero al siguiente satélite de la lista.
                
                if (currentSatelliteValue==-1) //Si la posición que se acaba de leer está vacía.
                {
                    pointer-=Integer.BYTES; //Retrocede el puntero al inicio del campo.
                    raf.seek(pointer);  //Posiciona el puntero.
                    raf.writeInt(satellitePosition); //Escribe la posición del satélite ocupando el primer espacio vacío (-1).
                }
                loopIndex++;
            }while(currentSatelliteValue!=-1&&loopIndex<Planet.MAX_SATELLITE);
            
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
    
    
//=====================================================================================
//=====================================================================================
//=====================================================================================
    
    //=================DEPRECATED============================
     /**
     * Obtén el nombre de un planeta según su posición en los registros.
     * @param planetPosition posición del planeta en los registros.
     * @return  nombre del planeta.
     * @deprecated con el método que devuelve una lista puedo hacer lo mismo desde Main basando
     * el índice del Array con la posición del planeta en el registro.
     */
    public static String readPlanetName(int planetPosition)
    {
        File file = new File (PATH);
        String name=null;
        byte [] bName;
        if (file.exists())
        {
            try {
                RandomAccessFile raf = new RandomAccessFile(file, "r");
               
                raf.seek(planetPosition*Planet.recordSize());
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
     * Guarda la lista de nombres para los planetas del archivo.
     * @return lista con los nombres de los planetas en formato vertical y numerado.
     * @deprecated Es preferible obtener toda la información y desde Main obtener el nombre con un getter.
     */
    public static ArrayList<String> readPlanetNameList()
    {
        ArrayList<String> nameList = new ArrayList();
        File file = new File (PATH);
        String name;
        byte [] bName;
        if (file.exists())
        {
            int planetAmount= (int)file.length()/Planet.recordSize();
            try {
                RandomAccessFile raf = new RandomAccessFile(file, "r");
                for (int i = 0;i<planetAmount;i++)
                {
                    raf.seek(i*Planet.recordSize());
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
     * Busca el nombre de un planeta en un archivo y comprueba si ya existe.
     * @param planetName nombre del planeta a comprobar.
     * @return TRUE si el planeta ya se encuentra.
     * @deprecated Con el método de obtener la posición ya podemos comprobar si se encuentra dentro del archivo.
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
            
            int planetAmount=(int)file.length()/Planet.recordSize();
            
            while(pos<planetAmount && found==false)
            {
                raf.seek(pos*Planet.recordSize());
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

}