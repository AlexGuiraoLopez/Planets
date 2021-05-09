package elements;
/**
 * Clase de la que derivan los planetas y los satélites.
 * @author Alex Guirao López <aguiraol2021@cepnet.net>
 */
public abstract class SpaceElement 
{
    protected String name;
    protected int diameter;

    public SpaceElement(String name, int diameter) 
    {
        this.name = name;
        this.diameter=diameter;
    }
    
    //######GET & SET######
    public String getName() 
    {
        return name;
    }

    public int getDiameter() 
    {
        return diameter;
    }
    
}
