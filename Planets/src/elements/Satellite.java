package elements;


/** 
 * @author Alex Guirao Lopez <aguiraol2021@cepnet.net>
 */
public class Satellite 
{
    public final static int NAME_MAX_LENGTH=10;
    
    public static int satelliteCount;
    private String name;
    private int planetDistance;
    private String planetName;
    
    
    
    private String nameFormat()
    {
        return "%-"+NAME_MAX_LENGTH+"."+NAME_MAX_LENGTH+"s";
    }
    
    public String getFormattedName()
    {
        return String.format(nameFormat(), name);
    }
}
