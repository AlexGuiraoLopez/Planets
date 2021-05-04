package elements;
import java.io.File;
/**
 * @author Alex Guirao Lopez <aguiraol2021@cepnet.net>
 */
public class Planet 
{
    public final static int NAME_MAX_LENGTH=10;
    public final static int MAX_SATELLITE=10;
    
    private String name;
    private int diameter;
    private File imgFile;
    private float sunDistance;
    private int[] satellitePosList = new int[MAX_SATELLITE];

    public Planet(String name,int diameter, float sunDistance) 
    {
        this.name=name;
        this.diameter = diameter;
        this.sunDistance = sunDistance;
    }
    
    private File getImageFile()
    {
        return new File(this.name+".png");
    }
    
    /**
     * Tamaño de los registros para los atributos de los planetas.
     * @return tamaño de cada registro.
     */
    public static int size()
    {
        return NAME_MAX_LENGTH + Integer.BYTES + Float.BYTES ;
    }
    
    public static String nameFormat()
    {
        return "%-"+NAME_MAX_LENGTH+"."+NAME_MAX_LENGTH+"s";
    }
    
    public String getFormattedName()
    {
        return String.format(nameFormat(), name);
    }
    
    /**
     * Formatea el nombre de un planeta al formato del campo en el archivo binario.
     * @param nameToFormat nombre a formatear.
     * @return nombre formateado.
     */
    public static String formatName(String nameToFormat)
    {
        return String.format(nameFormat(),nameToFormat);
    }

    public int getDiameter() {
        return diameter;
    }

    public float getSunDistance() {
        return sunDistance;
    }
    
    
}
