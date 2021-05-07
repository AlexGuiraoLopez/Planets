package mainclass;

import elements.Planet;
import elements.Satellite;
import filemanipulation.HtmlFileControl;
import filemanipulation.PlanetFileControl;
import filemanipulation.SatelliteFileControl;
import input.Keyboard;
import java.io.File;
import java.util.ArrayList;
import time.Time;
import user.User;
import visualfront.ConsoleColors;
import visualfront.Menu;
import visualfront.Paint;

/**
 * @author Alex Guirao López <aguiraol2021@cepnet.net>
 */
//Alt + shift + m => Comando para encapsular metodos.
public class MainClass 
{
    final static String [] mainMenuLines = new String [] {" --- Menú principal ---",
                                                            ConsoleColors.BLUE+"1-Introducir planeta",
                                                                ConsoleColors.PURPLE+"2-Introducir satelite",
                                                                   ConsoleColors.BLUE+ "3-Mostrar planeta",
                                                                        ConsoleColors.PURPLE+"4-Mostrar satelite",
                                                                            ConsoleColors.YELLOW+"5-Generar HTML",
                                                                                ConsoleColors.YELLOW+"6-Ejecutar web",
                                                                                    ConsoleColors.RED+"0-Salir",
                                                                                        ConsoleColors.RED+"-1 => Eliminar (DANGER!)"};
    
    /**
     * Bienvenido al programa.
     * Sigue los (números) con el orden de la lógica del programa.
     * @param args 
     */
    public static void main(String[] args) 
    {
        Paint.drawMainImage(); //(0.) Dibuja la imagen de presentación.
        Menu mainMenu = new Menu(mainMenuLines);
        int userAns;
        
        do{
            //(1.) Se muestra el menú principal para que el usuario elija una opción.
            mainMenu.showMenu();
            userAns=Keyboard.readInt();
            switch(userAns)
            {
                case 1: //Introducir planeta. 
                    System.out.println(ConsoleColors.BLUE+"    --Introducir planeta--");
                    Planet planet = User.createPlanet(); //(2.) El usuario crea un planeta.
                    PlanetFileControl.writePlanet(planet);//(3.) La info del planeta se registra en el fichero.
                break;
                    
                case 2: //Introducir satélite. 
                    System.out.println(ConsoleColors.PURPLE+"   --Introducir satélite--");
                    Satellite satellite = User.createSatellite();//(4.) El usuario crea un satélite.
                    SatelliteFileControl.writeSatellite(satellite); //(5.) La info del satélite se registra en el fichero.
                    break;
                    
                case 3: //Mostrar planeta.
                    showPlanetNames();
                    if (PlanetFileControl.getPlanetAmount()>0)
                    {
                        //(6.) La info del planeta se recoge.
                        String planetInfo=PlanetFileControl.getPlanetInfo(User.selectPlanet()-1); 
                        System.out.println(planetInfo);//(7.) Muestra la info del planeta.
                    }
                    Time.waitForSeconds(750);
                    break;
                    
                case 4: //Mostrar satélite.
                    showSatelliteNames();
                    if (SatelliteFileControl.getSatelliteAmount()>0)
                    {
                        //(8.)La info del satélite se recoge.
                        String sateliteInfo=SatelliteFileControl.getSatelliteInfo(User.selectSatellite()-1); 
                        System.out.println(sateliteInfo);//(9.) Muestra la info del satelite.
                    }
                    Time.waitForSeconds(750);
                    break;
                    
                case 5: //Generar HTML.
                    showPlanetNames();
                    if (PlanetFileControl.getPlanetAmount()>0)
                    {
                        //(10.) Genera archivo HTML del planeta seleccionado.
                        HtmlFileControl.generateHTMLFile(User.selectPlanet()-1); 
                    }
                break;
                
                case 6: //Ejecutar HTML.
                    showHtmlFileNames();
                    if (HtmlFileControl.getHTMLFileAmount()>0)
                    {
                    HtmlFileControl.executeFile(User.selectPlanet()-1);
                    }else{
                        System.out.println(ConsoleColors.RED+"No hay archivos para ejecutar.");
                    }
                    break;
                    
                case -1:eraseAllInfo(); //(X.) ELIMINA TODA LA INFORMACIÓN
                    break;
                    
                default: System.out.println(ConsoleColors.RED+"Opción errónea");
            }
            
            
            
            Paint.breakLine();
            
        }while(userAns!=0);
        System.out.println("¡No olvides que tus datos han sido registrados!");
        System.out.println("Vuelve a acceder cuando te apetezca ;)");
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
    
    public static void showSatelliteNames()
    {
        if (SatelliteFileControl.getSatelliteAmount()==0)
        {
            System.out.println(ConsoleColors.RED+"No hay satélites todavía");
            Time.waitForSeconds(500);
        }else{
            ArrayList<String> satelliteNameList=SatelliteFileControl.getSatelliteNameList();
            int pos=1;
            for (String s:satelliteNameList)
            {
                System.out.println(pos+"- "+s);
                pos++;
            }
        }
    }
    
    /**
     * Elimina todos los  ficheros de datos.
     */
    public static void eraseAllInfo()
    {
        File planetFile = new File(PlanetFileControl.PATH);
        File satelliteFile = new File (SatelliteFileControl.PATH);
        
        planetFile.delete();
        satelliteFile.delete();
        
    }
}
