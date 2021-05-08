package user;
import elements.Planet;
import elements.Satellite;
import filemanipulation.PlanetFileControl;
import filemanipulation.SatelliteFileControl;
import input.Keyboard;
import java.util.InputMismatchException;
import visualfront.ConsoleColors;
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
                System.out.println(ConsoleColors.RED+"El tamaño máximo para el nombre es de "+maxLength+" caracteres.");
            }
        }while(name.length()>maxLength);
        
        return name;
    }
    
    public static int insertUnsignedInt(String message)
    {
        int number=0;
        
        do{
            System.out.println(message);
            try{
            number=Keyboard.readInt();
            
                if(number<=0)
                {
                    System.out.println(ConsoleColors.RED+"El dato debe ser mayor a 0");
                }
            }
            catch (InputMismatchException ex)
            {
                System.out.println(ConsoleColors.RED+"El dato introducido no es un número");
            }
            
        }while(number<=0);
        
        return number;
    }
    
    public static float insertUnsignedFloat(String message)
    {
        float number=0;
        
        do{
            try{
                System.out.println(message);
                number=Keyboard.readFloat();
                if(number<=0)
                {
                    System.out.println(ConsoleColors.RED+"El dato debe ser mayor a 0");
                }
            }
            catch(InputMismatchException ex)
            {
                System.out.println(ConsoleColors.RED+"El dato introducido no es un número");
            }
        }while(number<=0);
        
        return number;
    }
    
    public static Planet createPlanet()
    {
        String name = insertText(Planet.NAME_MAX_LENGTH, "> Introduce el nombre del planeta");
        int diameter= insertUnsignedInt("> Introduce su diametro");
        float sunDistance=insertUnsignedFloat("> Introduce la distancia al sol");
        
        return new Planet(name, diameter, sunDistance);
    }
    
      public static int selectPlanet()
    {
        int userAns=0;
        
        do{
            userAns=Keyboard.getInt(ConsoleColors.GREEN+"Selecciona el planeta");
            if (userAns<1||userAns>PlanetFileControl.getPlanetAmount())
            {
                System.out.println(ConsoleColors.RED+"Selecciona una respuesta válida");
            }
        }while(userAns<1||userAns>PlanetFileControl.getPlanetAmount());
        
        return userAns;
    }
      
       public static Satellite createSatellite()
    {
        String name = insertText(Satellite.NAME_MAX_LENGTH, "> Introduce el nombre del satélite:");
        String planetName;
        do{
            planetName=insertText(Planet.NAME_MAX_LENGTH,"> Introduce el nombre del planeta al que orbita:");
            if (!PlanetFileControl.seekPlanetName(Planet.formatName(planetName)))
            {
                System.out.println(ConsoleColors.RED+"ERROR: No hay un planeta registrado con ese nombre");
            }
        }while(!PlanetFileControl.seekPlanetName(Planet.formatName(planetName)));
        
        int diameter= insertUnsignedInt(">Introduce su diametro:");
        int planetDistance=insertUnsignedInt(">Introduce la distancia al planeta:");
        
        return new Satellite(name, planetName, diameter, planetDistance);
    }
       
    public static int selectSatellite()
    {
        int userAns=0;
        
        do{
            userAns=Keyboard.getInt(ConsoleColors.GREEN+"Selecciona el satelite");
            if (userAns<1||userAns>SatelliteFileControl.getSatelliteAmount())
            {
                System.out.println(ConsoleColors.RED+"Selecciona una respuesta válida");
            }
        }while(userAns<1||userAns>SatelliteFileControl.getSatelliteAmount());
        
        return userAns;
    }
    
}
