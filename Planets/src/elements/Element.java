package elements;
/**
 * @author Alex Guirao López <aguiraol2021@cepnet.net>
 */
public abstract class Element 
{
    protected String name;
    protected int diameter;

    public Element(String name, int diameter) 
    {
        this.name = name;
        this.diameter=diameter;
    }
    
    //######GET & SET######
    public String getName() {
        return name;
    }

    public int getDiameter() {
        return diameter;
    }
    
}
