package elements;
/** 
 * @author Alex Guirao Lopez <aguiraol2021@cepnet.net>
 */
public class Satellite extends Element
{
    public final static int NAME_MAX_LENGTH=10;
    
    private String planetName;
    private int planetDistance;
    
    public Satellite(String name, String planetName, int diameter, int planetDistance)
    {
        super(name, diameter);
        this.planetDistance = planetDistance;
        this.planetName = planetName;
    }
    
    public static int size()
    {
        return NAME_MAX_LENGTH + Integer.BYTES+Integer.BYTES+NAME_MAX_LENGTH;
    }

    @Override
    public String toString() 
    {
        return "Nombre: " + name+"\n"+"Nombre del planeta: " + planetName+"\n"+"Diametro: " + diameter+"\n"+
                    "Distancia al planeta: " + planetDistance;
    }
    
    //######### GET & SET#########
    public String getName()
    {
        return name;
    }
     
    public static String getNameFormat() 
    {
        return "%-"+NAME_MAX_LENGTH+"."+NAME_MAX_LENGTH+"s";
    }
    
    public String getFormattedName()
    {
        return String.format(getNameFormat(), name);
    }
    
    public String getFormattedPlanetName()
    {
        return String.format(Planet.nameFormat(), planetName);
    }
    
    public int getDiameter() 
    {
        return diameter;
    }

    public int  getPlanetDistance() 
    {
        return planetDistance;
    }
    
    
}
