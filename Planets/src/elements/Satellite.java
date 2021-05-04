package elements;
/**
 * Permetrà introduir les dades d’un satèl·lit. Demanarà a l’usuari el nom del satèl·lit, 
 * el nom del planeta al que orbita, el diàmetre i la distància al planeta (tots dos en km)
 * i guardarà el satèl·lit a l’arxiu satellites.bin. Cas que a l’arxiu ja hi hagi un satèl·lit 
 * amb el nom introduït o bé que el planeta no estigui a l’arxiu planetes.bin, 
 * el programa no guardarà el satèl·lit i tornarà al menú principal
 */
/** 
 * @author Alex Guirao Lopez <aguiraol2021@cepnet.net>
 */
public class Satellite 
{
    public final static int NAME_MAX_LENGTH=10;
    
    private String name;
    private String planetName;
    private int diameter;
    private int planetDistance;
    

    public Satellite(String name, String planetName, int diameter, int planetDistance)
    {
        this.name = name;
        this.diameter = diameter;
        this.planetDistance = planetDistance;
        this.planetName = planetName;
    }
    
    
    public static int size()
    {
        return NAME_MAX_LENGTH + Integer.BYTES+Integer.BYTES+NAME_MAX_LENGTH;
    }
    
    public static String nameFormat()
    {
        return "%-"+NAME_MAX_LENGTH+"."+NAME_MAX_LENGTH+"s";
    }
    
    public String getFormattedName()
    {
        return String.format(nameFormat(), name);
    }
    
    public String getFormattedPlanetName()
    {
        return String.format(Planet.nameFormat(), planetName);
    }
    
    public int getDiameter() {
        return diameter;
    }

    public int  getPlanetDistance() {
        return planetDistance;
    }
    
    
}
