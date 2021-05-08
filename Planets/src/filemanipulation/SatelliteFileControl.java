package filemanipulation;

import elements.Planet;
import elements.Satellite;
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
public class SatelliteFileControl 
{
    public final static String PATH="datafiles/satellites.bin";
    public static File file = new File(PATH);
    
    //=======================READ============================
    /**
     * Calcula la cantidad de satelites que hay en el archivo.
     * @return cantidad de satelites ya escritos en el archivo.
     */
    public static int getSatelliteAmount()
    {
        int satelliteAmount=0;
      
        if (file.exists())
        {
            satelliteAmount= (int)file.length()/Satellite.size();
        }
        return satelliteAmount;
    }
    
        /**
     * Busca la posición de un satélite en los registros.
     * @param satelliteName nombre del satélite a encontrar.
     * @return posición del satélite en los registros (si es -1 no está en el fichero).
     */
    private static int getSatellitePosition(String satelliteName)
    {
        int satellitePosition=-1; //Empieza en -1 como indicador de que el planeta no está en el archivo.
        
        try {
            if (file.exists())
            {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            String checkName;
            byte [] bName;
            int satelliteAmount=(int)file.length()/Satellite.size();
            
            int loopIterator=0; 
            
            while(loopIterator<satelliteAmount && satellitePosition==-1)
            {
                raf.seek(loopIterator*Satellite.size());
                bName=new byte[Satellite.NAME_MAX_LENGTH];
                raf.read(bName);
                checkName=new String(bName);
                if (checkName.equalsIgnoreCase(satelliteName))
                {
                    satellitePosition=loopIterator;
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
        return satellitePosition;
    }
        
          /**
     * Obtén un satélite del archivo basado en su posición.
     * @param satellitePosition posición del satélite en el archivo.
     * @return instancia de un satélite.
     */
    public static Satellite readSatellite(int satellitePosition)
    {
        Satellite satellite=null;
        
        try {
            RandomAccessFile raf;
            byte [] bName;
            String name;
            String planetName;
            int diameter;
            int planetDistance;
            
            raf = new RandomAccessFile(PATH,"r");
            
            raf.seek(satellitePosition*Satellite.size());
            
            bName=new byte[Satellite.NAME_MAX_LENGTH];
            raf.read(bName);
            name= new String(bName).trim();
            
            bName=new byte[Satellite.NAME_MAX_LENGTH];
            raf.read(bName);
            planetName= new String(bName).trim();
            
            diameter=raf.readInt();
            
            planetDistance=raf.readInt();
            
            raf.close();
            
            satellite = new Satellite(name, planetName ,diameter, planetDistance);
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("No se pudo encontrar el archivo" + ex);
        }
        catch (IOException ex)
        {
            System.out.println("No se pudo acceder al archivo" + ex);
        }
        
        return satellite;
    }
    
    /**
     * Guarda una lista con los satélites registrados en el archivo.
     * @return lista de los satélites.
     */
    public static ArrayList<Satellite> readSatelliteList()
    {
        ArrayList<Satellite> satelliteList = new ArrayList();
        
        if (file.exists())
        {
            int satelliteAmount= getSatelliteAmount();
          
                for (int i = 0;i<satelliteAmount;i++)
                {
                    satelliteList.add(readSatellite(i));
                }
        }
        return satelliteList;
    }
    
    /**
     * Obtén una lista de satélites correspondiente a la lista de posiciones para los satélites
     * que orbitan a un planeta.
     * @param satellitePosList lista de posiciones a obetener sus satélites.
     * @return satélites de las posiciones.
     */
    public static ArrayList<Satellite> readSatelliteList(int[] satellitePosList)
    {
        ArrayList<Satellite>satelliteList= new ArrayList<Satellite>();
        
        int pos=0;
        String name;
        String planetName;
        byte [] bName;
        int diameter;
        int planetDistance;
        
        try {
            if (file.exists())
            {
                RandomAccessFile raf = new RandomAccessFile(file, "r");
                
                while(satellitePosList[pos]!=-1)
                {
                    raf.seek(satellitePosList[pos]*Satellite.size());
                    bName=new byte[Satellite.NAME_MAX_LENGTH];  
                    raf.read(bName);
                    name= new String(bName);
                    
                    bName=new byte[Satellite.NAME_MAX_LENGTH];  
                    raf.read(bName);
                    planetName= new String(bName);
                    
                    diameter=raf.readInt();
                    
                    planetDistance=raf.readInt();
                    
                    satelliteList.add(new Satellite(name, planetName, diameter, planetDistance));
                    
                    pos++;
                }

                raf.close();
            }
        }
        catch (FileNotFoundException ex) 
        {
            System.out.println("No se ha podido encontrar el archivo" + ex);
        }
        catch (IOException ex) 
        {
            System.out.println("No se ha leer encontrar el archivo" + ex);
        }
        
        if (satelliteList.isEmpty())
        {
            System.out.println("No contiene satélites");
        }
        
        return satelliteList; 
    }
    
    //=======================WRITE============================
    /**
     * Escribe la información de un planeta en el archivo.
     * @param satellite planeta que se va a registrar.
     */
    public static void writeSatellite(Satellite satellite)
    {
        File file = new File(PATH);
        int satelliteAmount = getSatelliteAmount();
        try {
            if (getSatellitePosition(satellite.getFormattedName())==-1)
            {
            RandomAccessFile raf = new RandomAccessFile(PATH, "rw");
            raf.seek(satelliteAmount*Satellite.size());
            raf.write(satellite.getFormattedName().getBytes(Charset.defaultCharset()));
            raf.write(satellite.getFormattedPlanetName().getBytes(Charset.defaultCharset()));
            raf.writeInt(satellite.getDiameter());
            raf.writeInt(satellite.getPlanetDistance());
            raf.close();
            
            //IMPORTANTE => Actualiza la lista de posiciones de satelites en los planetas.
            PlanetFileControl.updateSatellitePosList(satellite.getFormattedPlanetName(),satelliteAmount); 
            }
            else
            {
                System.out.println(ConsoleColors.RED+"ERROR: Un satélite con el mismo nombre está registrado.");
                time.Time.waitForSeconds(500);
            }
        } 
        catch (IOException ex) 
        {
            System.out.println("No se ha podido acceder al archivo");
        }
    }
    
    
//=====================================================================================
//=====================================================================================
//=====================================================================================
    
    
    //========================DEPCRECATED============================
    /**
     * Busca el nombre de un planeta en un archivo y comprueba si ya existe.
     * @param planetName nombre del planeta a comprobar.
     * @return TRUE si el planeta ya se encuentra.
     * @deprecated sustituida por getSatellitePosition()
     */
    private static boolean  seekSatelliteName(String satelliteName)
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
            
            int satelliteAmount=(int)file.length()/Satellite.size();
            
            while(pos<satelliteAmount && found==false)
            {
                raf.seek(pos*Satellite.size());
                bName=new byte[Satellite.NAME_MAX_LENGTH];
                raf.read(bName);
                checkName=new String(bName);
                if (checkName.equalsIgnoreCase(satelliteName))
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
     * Obtiene la información de un satelite especifico según su posición.
     * @param satellitePosition posición del satelite (basada en el orden de registro en el archivo binario)
     * @return información del satelite.
     * @deprecated sustituido por el toString() de la clase Satellite.
     */
    public static String getSatelliteInfo(int satellitePosition) 
    {
        RandomAccessFile raf;
        byte [] bName;
        String name;
        String planetName;
        String info="";
        int diameter;
        int planetDistance;
        
        try {
            raf = new RandomAccessFile(PATH,"r");
            raf.seek(satellitePosition*Satellite.size());
            bName=new byte[Satellite.NAME_MAX_LENGTH];  
            raf.read(bName);
            name= new String(bName).trim();
            bName=new byte[Planet.NAME_MAX_LENGTH]; 
            raf.read(bName);
            planetName= new String(bName).trim();
            
            diameter=raf.readInt();
            planetDistance=raf.readInt();
            raf.close();
            
            info="Nombre: " + name+"\n"+"Nombre del planeta: " + planetName+"\n"+"Diametro: " + diameter+"\n"+
                    "Distancia al planeta: " + planetDistance;
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
    
    /**
     * Guarda la lista de nombres para los satelites del archivo.
     * @return lista con los nombres de los satelites en formato vertical y numerado.
     * @deprecated  sustituido por readSatelliteList()
     */
    public static ArrayList<String> getSatelliteNameList()
    {
        ArrayList<String> nameList = new ArrayList();
        File file = new File (PATH);
        String name;
        byte [] bName;
        if (file.exists())
        {
            int satelliteAmount= getSatelliteAmount();
            try {
                RandomAccessFile raf = new RandomAccessFile(file, "r");
                for (int i = 0;i<satelliteAmount;i++)
                {
                    raf.seek(i*Satellite.size());
                    bName=new byte[Satellite.NAME_MAX_LENGTH]; 
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
