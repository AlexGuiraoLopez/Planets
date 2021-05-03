package mainclass;

import elements.Planet;
import filemanipulation.HtmlFileControl;
import filemanipulation.PlanetFileControl;
import input.Keyboard;
import java.util.ArrayList;
import time.Time;
import user.User;
import visualfront.ConsoleColors;
import visualfront.Menu;

/**
 * @author Alex Guirao López <aguiraol2021@cepnet.net>
 */
//Alt + shift + m
public class MainClass 
{
    final static String [] mainMenuLines = new String [] {"- Bienvenido -",
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
                case 1: Planet planet = User.createPlanet();
                            PlanetFileControl.writePlanet(planet);
                    break;
                case 2:
                    break;
                case 3: 
                    showPlanetNames();
                    PlanetFileControl.readPlanetInfo(User.selectPlanet()-1);
                    break;
                case 4:
                    break;
                case 5:
                    showPlanetNames();
                    HtmlFileControl.generatePlanetFile(User.selectPlanet()-1);
                break;
                case 6:
                    showPlanetNames();
                    HtmlFileControl.executeFile(User.selectPlanet()-1);
                   
            }
        }while(userAns!=0);
    }
    
    public static void showPlanetNames()
    {
        if (PlanetFileControl.getPlanetAmount()==0)
        {
            System.out.println(ConsoleColors.RED+"No hay planetas todavía");
            Time.waitForSeconds(500);
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
}
