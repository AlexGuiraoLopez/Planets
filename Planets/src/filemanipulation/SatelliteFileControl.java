package filemanipulation;

import elements.Planet;
import elements.Satellite;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import visualfront.ConsoleColors;

/** 
 * @author Alex Guirao Lopez <aguiraol2021@cepnet.net>
 */
public class SatelliteFileControl 
{
    public final static String PATH="datafiles/satellites.bin";
    
    //=======================READ============================
    /**
     * Busca el nombre de un planeta en un archivo y comprueba si ya existe.
     * @param planetName nombre del planeta a comprobar.
     * @return TRUE si el planeta ya se encuentra.
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
     * Calcula la cantidad de satelites que hay en el archivo.
     * @return cantidad de satelites ya escritos en el archivo.
     */
    public static int getSatelliteAmount()
    {
        int satelliteAmount=0;
        File file = new File(PATH);
      
        if (file.exists())
        {
            satelliteAmount= (int)file.length()/Satellite.size();
        }
        return satelliteAmount;
    }
    
    /**
     * Guarda la lista de nombres para los satelites del archivo.
     * @return lista con los nombres de los satelites en formato vertical y numerado.
     */
    public static ArrayList<Satellite> getSatelliteList()
    {
        ArrayList<Satellite> satelliteList = new ArrayList();
        File file = new File (PATH);
        String satelliteName;
        String planetName;
        int diameter;
        int planetDistance;
        byte [] bName;
        if (file.exists())
        {
            int satelliteAmount= getSatelliteAmount();
            try {
                RandomAccessFile raf = new RandomAccessFile(file, "r");
                for (int i = 0;i<satelliteAmount;i++)
                {
                    raf.seek(i*Planet.size());
                    bName=new byte[Satellite.NAME_MAX_LENGTH]; 
                    raf.read(bName);
                    satelliteName= new String(bName).trim();
                    
                    bName=new byte[Satellite.NAME_MAX_LENGTH]; 
                    raf.read(bName);
                    planetName= new String(bName).trim();
                    
                    diameter = raf.readInt();
                    planetDistance = raf.readInt();
                    
                    satelliteList.add(new Satellite(satelliteName, planetName, diameter, planetDistance));
                }
                raf.close();
            } 
            catch (IOException ex)
            {
                System.out.println("No se pudo acceder al archivo.");
            }
        }
        return satelliteList;
    }
    
     /**
     * Guarda la lista de nombres para los satelites del archivo.
     * @return lista con los nombres de los satelites en formato vertical y numerado.
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
    
    /**
     * Obtiene la información de un satelite especifico según su posición.
     * @param satellitePosition posición del satelite (basada en el orden de registro en el archivo binario)
     * @return información del satelite.
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
    
    public static String getSatelliteNames(int[] satellitePosList)
    {
        String satelliteNames="";
        File file = new File(PATH);
        int pos=0;
        byte [] bName;
        
        try {
            if (file.exists())
            {
                RandomAccessFile raf = new RandomAccessFile(file, "r");
                
                while(satellitePosList[pos]!=-1)
                {
                    raf.seek(satellitePosList[pos]*Satellite.size());
                    bName=new byte[Satellite.NAME_MAX_LENGTH];  
                    raf.read(bName);
                    satelliteNames+= new String(bName).trim()+"\n";
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
        
        if (satelliteNames.equals(""))
        {
            satelliteNames="Lista de satelites vacía";
        }
        
        
        return satelliteNames; 
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
            if (!seekSatelliteName(satellite.getFormattedName()))
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
                System.out.println(ConsoleColors.RED+"ERROR: Un planeta con el mismo nombre está registrado.");
                time.Time.waitForSeconds(500);
            }
        } 
        catch (IOException ex) 
        {
            System.out.println("No se ha podido acceder al archivo");
        }
    }
}
