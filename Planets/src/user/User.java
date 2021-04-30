package user;
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
    
    public static int insertUnsigned(String message)
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
    
}
