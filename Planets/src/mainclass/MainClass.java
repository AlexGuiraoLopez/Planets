package mainclass;

import elements.Planet;
import filemanipulation.PlanetFileControl;
import input.Keyboard;
import java.util.ArrayList;
import user.User;
import visualfront.Menu;

/**
 * @author Alex Guirao López <aguiraol2021@cepnet.net>
 */

/*ANOTACIONES: 
CREAR UNA CLASE QUE COMPRUEBE EL ESTADO DE LOS ARCHIVOS JUSTO AL EMPEZAR EL PROGRAMA
PARA EVITAR QUE LAS VARIABLES ESTATICAS SE RESETEEN AL PARARLO (O NO TENER EN CUENTA ESAS VARIABLES)
*/
public class MainClass 
{
    final static String [] mainMenuLines = new String [] {"Bienvenido",
                                                            "1-Introducir planeta",
                                                                "2-Introducir satelite",
                                                                    "3-Mostrar planeta",
                                                                        "4-Mostrar satelite",
                                                                            "5-Generar HTML",
                                                                                "6-Ejecutar web",
                                                                                    "0-Salir"};
    public static void main(String[] args) 
    {
        int userAns;
        Menu mainMenu = new Menu(mainMenuLines);
        
        do{
            mainMenu.showMenu();
            userAns=Keyboard.readInt();
            switch(userAns)
            {
                case 1: Planet planet = User.insertPlanet();
                            PlanetFileControl.writePlanet(planet);
                    break;
                case 2:
                    break;
                case 3: 
                    if (Planet.planetCount==0)
                    {
                        System.out.println("No hay planetas todavía");
                    }else{
                        ArrayList<String> planetNameList=PlanetFileControl.readPlanetNameList();
                        int pos=1;
                        for (String s:planetNameList)
                        {
                            System.out.println(pos+"- "+s);
                            pos++;
                        }
                    }
            }
        }while(userAns!=0);
    }
}
