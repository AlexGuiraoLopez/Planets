package elements;
import filemanipulation.SatelliteFileControl;
import java.util.ArrayList;

/**
 * Clase para crear y obtener información de planetas.
 * @author Alex Guirao Lopez <aguiraol2021@cepnet.net>
 */
public class Planet extends SpaceElement
{
    public final static int NAME_MAX_LENGTH=10; //Longitud máxima para el nombre.
    public final static int MAX_SATELLITE=10; //Longitud máxima de posiciones para sus planetas.
    private float sunDistance;
    private int[] satellitePosList = new int[MAX_SATELLITE];

    public Planet(String name,int diameter, float sunDistance) 
    {
        super(name,diameter);
        this.sunDistance = sunDistance;
        resetSatelliteList(); //Fija el valor de las posiciones a -1 para indicar espacios vacíos.
    }
    
     public Planet(String name,int diameter, float sunDistance, int[] satellitePosList) 
    {
        super(name, diameter);
        this.sunDistance = sunDistance;
        this.satellitePosList=satellitePosList;
    }

    /**
     * Resetea la lista de satélites para que todas sus posiciones sean el valor por defecto -1.
     * Ese valor indica que no hay satélites en dicha posición.
     * De este modo evito posibles futuros errores si lee varios 0 como índices por defecto.
     */
    private void resetSatelliteList()
    {
        for (int i=0;i<satellitePosList.length;i++)
        {
            satellitePosList[i]=-1;
        }
    }
    
    /**
     * Tamaño de los registros para los atributos de los planetas.
     * @return tamaño de cada registro.
     */
    public static int recordSize()
    {
        return NAME_MAX_LENGTH + Integer.BYTES + Float.BYTES+Integer.BYTES*MAX_SATELLITE;
    }
    
    /**
     * Tamaño de los campos anteriores a la lista de satelites para posicionar la lectura/escritura en ese punto.
     * @return tamaño de los registros anteriores a la lista de satelites.
     */
    public static int satelliteListStartIndex()
    {
        return NAME_MAX_LENGTH + Integer.BYTES + Float.BYTES;
    }
    
   
    /**
     * @return Formato del nombre de los planetas.
     */
    public static String nameFormat()
    {
        return "%-"+NAME_MAX_LENGTH+"."+NAME_MAX_LENGTH+"s";
    }
    
    /**
     * Obtén el nombre del planeta rellenado con espacios si no llega a su máxima longitud.
     * @return Nombre formateado. 
     */
    public String getFormattedName()
    {
        return String.format(nameFormat(), name);
    }
    
    /**
     * Formatea el nombre de un planeta en el archivo binario.
     * @param nameToFormat nombre a formatear.
     * @return nombre formateado.
     */
    public static String formatName(String nameToFormat)
    {
        return String.format(nameFormat(),nameToFormat);
    }

    /**
     * Escribe una lista de nombres de los satélites dentro de la lista de posiciones.
     * @return cadena de texto con los nombres de los satélites en formato vertical sin numerar.
     */
    private String getSatellitesName()
    {
        String satellitesName="";
        
        ArrayList<Satellite> satelliteList=SatelliteFileControl.readSatelliteList(satellitePosList);
       
        if (!satelliteList.isEmpty()) 
        {            
            for (Satellite s: satelliteList)
            {
                satellitesName+=s.getName()+"\n";
            }
        }else{
            satellitesName="Todavía no hay satélites";
        }
        
        return satellitesName;
    }
    
    @Override
    public String toString() 
    {
        return "Nombre: " + name+"\n"+"Diametro: " + diameter+"\n"+"Distancia al sol: " + sunDistance+"\n"+
                    "    - Satélites -"+"\n"+getSatellitesName();
    }

    
    //######### GET & SET#########
    public float getSunDistance() 
    {
        return sunDistance;
    }

    public int[] getSatellitePosList() 
    {
        return satellitePosList;
    }
}
