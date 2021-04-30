package elements;

import java.io.File;

/**
 * @author Alex Guirao Lopez <aguiraol2021@cepnet.net>
 */
public class Planet 
{
    public final static int NAME_MAX_LENGTH=10;
    
    public static int planetCount;
    private String name;
    private int diameter;
    private File imgFile;
    private int sunDistance;
    private int satelliteAmount;
    private int[] satellitePosList = new int[satelliteAmount];

    public Planet(String name,int diameter, File imgFile, int sunDistance, int satelliteAmount) 
    {
        this.name=name;
        this.diameter = diameter;
        this.imgFile = imgFile;
        this.sunDistance = sunDistance;
        this.satelliteAmount = satelliteAmount;
    }
    
    private File getImageFile()
    {
        return new File(this.name+".png");
    }
    
}
