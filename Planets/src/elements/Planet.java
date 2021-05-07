package elements;
/**
 * @author Alex Guirao Lopez <aguiraol2021@cepnet.net>
 */
public class Planet 
{
    public final static int NAME_MAX_LENGTH=10;
    public final static int MAX_SATELLITE=10;
    
    private String name;
    private int diameter;
    private float sunDistance;
    private int[] satellitePosList = new int[MAX_SATELLITE];
    private int hasWeb;

    public Planet(String name,int diameter, float sunDistance) 
    {
        this.name=name;
        this.diameter = diameter;
        this.sunDistance = sunDistance;
        resetSatelliteList(); //Fija el valor de las posiciones a -1 para indicar espacios vacíos.
        this.hasWeb=0;
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
    public static int size()
    {
        return NAME_MAX_LENGTH + Integer.BYTES + Float.BYTES+Integer.BYTES*MAX_SATELLITE+Integer.BYTES;
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
     * Tamaño de los campos anteriores al check de web para posicionar el puntero en ese punto.
     * @return tamaño de los registros anteriores al check de web.
     */
    public static int hasWebStartIndex()
    {
        return NAME_MAX_LENGTH + Integer.BYTES + Float.BYTES+Integer.BYTES;
    }
    
    /**
     * @return Formato del nombre de los planetas.
     */
    public static String nameFormat()
    {
        return "%-"+NAME_MAX_LENGTH+"."+NAME_MAX_LENGTH+"s";
    }
    
    /**
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

    
    //######### GET & SET#########
    public int getDiameter() {
        return diameter;
    }

    public float getSunDistance() {
        return sunDistance;
    }

    public int[] getSatellitePosList() {
        return satellitePosList;
    }
    
    
    
}
