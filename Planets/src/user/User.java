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
    /**
     * El usuario introduce una cadena de texto limitado a una longitud máxima.
     * @param maxLength longitud máxima de la cadena.
     * @param message mensaje de petición de datos para el usuario.
     * @return cadena de texto.
     */
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
    
    /**
     * El usuario introduce un número entero positivo y mayor a 0.
     * @param message  mensaje de petición de datos para el usuario.
     * @return número entero positivo y mayor a 0.
     */
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
    
      /**
     * El usuario introduce un número decimal positivo y mayor a 0.
     * @param message  mensaje de petición de datos para el usuario.
     * @return número decimal positivo y mayor a 0.
     */
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
    
    /**
     * El usuario crea un planeta.
     * @return planeta creado.
     */
    public static Planet createPlanet()
    {
        String name = insertText(Planet.NAME_MAX_LENGTH, "> Introduce el nombre del planeta");
        int diameter= insertUnsignedInt("> Introduce su diametro");
        float sunDistance=insertUnsignedFloat("> Introduce la distancia al sol");
        
        return new Planet(name, diameter, sunDistance);
    }
    
    /**
     * El usuario selecciona un planeta de la lista.
     * Se le da la opción de introducir 0 para retroceder.
     * @return índice del planeta seleccionado en la lista.
     */
    public static int selectPlanet()
    {
        int userAns=0;
        
        do{
            userAns=Keyboard.getInt(ConsoleColors.GREEN+"Selecciona un número de la lista");
            if (userAns<0||userAns>PlanetFileControl.getPlanetAmount())
            {
                System.out.println(ConsoleColors.RED+"Selecciona una respuesta válida");
            }
        }while(userAns<0||userAns>PlanetFileControl.getPlanetAmount());
        
        return userAns;
    }
      
    /**
     * El usuario crea un satélite.
     * @return satélite creado.
     */
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
       
    /**
     * El usuario selecciona un satélite de la lista.
     * Se le da la opción de introducir 0 para retroceder.
     * @return índice del satélite seleccionado en la lista.
     */
    public static int selectSatellite()
    {
        int userAns=0;
        
        do{
            userAns=Keyboard.getInt(ConsoleColors.GREEN+"Selecciona un número de la lista");
            if (userAns<0||userAns>SatelliteFileControl.getSatelliteAmount())
            {
                System.out.println(ConsoleColors.RED+"Selecciona una respuesta válida");
            }
        }while(userAns<0||userAns>SatelliteFileControl.getSatelliteAmount());
        
        return userAns;
    }
    
}
