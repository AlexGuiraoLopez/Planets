package user;
import elements.Planet;
import input.Keyboard;
/**
 * @author Alex Guirao Lopez <aguiraol2021@cepnet.net>
 */
public class User 
{
    public static String insertText(int maxLength, String message)
    {
        String name;
        do{
            System.out.println(message);
            name=Keyboard.readString();
            if (name.length()>maxLength)
            {
                System.out.println("El tamaño máximo para el nombre es de "+maxLength+" caracteres.");
            }
        }while(name.length()>maxLength);
        
        return name;
    }
    
    public static int insertUnsignedInt(String message)
    {
        int number;
        
        do{
            System.out.println(message);
            number=Keyboard.readInt();
            if(number<=0)
            {
                System.out.println("El dato debe ser mayor a 0");
            }
        }while(number<=0);
        
        return number;
    }
    
    public static float insertUnsignedFloat(String message)
    {
        float number;
        
        do{
            System.out.println(message);
            number=Keyboard.readFloat();
            if(number<=0)
            {
                System.out.println("El dato debe ser mayor a 0");
            }
        }while(number<=0);
        
        return number;
    }
    
    public static Planet insertPlanet()
    {
        String name = insertText(Planet.NAME_MAX_LENGTH, "Introduce el nombre del planeta");
        int diameter= insertUnsignedInt("Introduce su diametro");
        float sunDistance=insertUnsignedFloat("Introduce la distancia al sol");
        
        return new Planet(name, diameter, sunDistance);
    }
    
}
