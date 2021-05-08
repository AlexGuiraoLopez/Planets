package mainclass;

import elements.Element;
import elements.Planet;
import elements.Satellite;
import filemanipulation.HtmlFileControl;
import filemanipulation.PlanetFileControl;
import filemanipulation.SatelliteFileControl;
import input.Keyboard;
import java.io.File;
import java.util.ArrayList;
import sound.Sound;
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
    final static String [] MAIN_MENU_LINES = new String [] {" --- Menú principal ---",
                                                            ConsoleColors.CYAN+"1-Introducir planeta",
                                                                ConsoleColors.PURPLE+"2-Introducir satelite",
                                                                   ConsoleColors.CYAN+ "3-Mostrar planeta",
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
        Sound.startAudio();
        Paint.drawMainImage(); //(0.) Dibuja la imagen de presentación.
        Menu mainMenu = new Menu(MAIN_MENU_LINES);
        int userAns;
        
        do{
            //(1.) Se muestra el menú principal para que el usuario elija una opción.
            mainMenu.showMenu();
            userAns=Keyboard.readInt();
            switch(userAns)
            {
                case 1: //Introducir planeta.                  
                    insertPlanet();
                break;
                    
                case 2: //Introducir satélite.         
                    insertSatellite();
                    break;
                    
                case 3: //Mostrar planeta.                  
                    showPlanet();
                    Time.waitForSeconds(750);
                    break;
                    
                case 4: //Mostrar satélite.
                    showSatellite();
                    Time.waitForSeconds(750);
                    break;
                    
                case 5: //Generar HTML.     
                    generateWeb();
                    Time.waitForSeconds(750);
                break;
                
                case 6: //Ejecutar HTML.
                   executeWeb();
                    break;
                    
                case -1:eraseAllInfo(); //ELIMINA TODA LA INFORMACIÓN
                    break;
                    
                default: System.out.println(ConsoleColors.RED+"Opción errónea");
            }
            
            
            
            Paint.breakLine();
            
        }while(userAns!=0);
        System.out.println("¡No olvides que tus datos han sido registrados!");
        System.out.println("Vuelve a acceder cuando te apetezca ;)");
    }

    //========================MÉTODOS PRINCIPALES=========================

    /**
     * Inserta un planeta en el sistema.
     * (Opción 1 del programa)
     */
    private static void insertPlanet() 
    {
        System.out.println(ConsoleColors.CYAN+"    --Introducir planeta--");
        Planet planet = User.createPlanet(); //(2.) El usuario crea un planeta.
        PlanetFileControl.writePlanet(planet);//(3.) La info del planeta se registra en el fichero.
    }
    
    /**
     * Inserta un satélite en el sistema.
     * (Opción 2 del programa)
     */
    private static void insertSatellite() 
    {
        if (PlanetFileControl.getPlanetAmount()>0)
        {
            System.out.println(ConsoleColors.PURPLE+"   --Introducir satélite--");
            Satellite satellite = User.createSatellite();//(4.) El usuario crea un satélite.
            SatelliteFileControl.writeSatellite(satellite); //(5.) La info del satélite se registra en el fichero.
        }else{
            System.out.println(ConsoleColors.RED+"Inserta mínimo un planeta antes de satélites");
        }
    }
    
    /**
     * Muestra la información de un planeta.
     * (Opción 3 del programa)
     */
    private static void showPlanet() 
    {
        ArrayList planetList = PlanetFileControl.readPlanetList();
        showElementNames(planetList);
        
        if (PlanetFileControl.getPlanetAmount()>0)
        {
            Planet planet=PlanetFileControl.readPlanet(User.selectPlanet()-1);
            String planetInfo= planet.toString();//(6.) La info del planeta se recoge.
            System.out.println(planetInfo);//(7.) Muestra la info del planeta.
        }else{
            System.out.println(ConsoleColors.RED+"No hay planetas todavía");
        }
    }
    
    /**
     * Muestra la información de un satélite.
     * (Opción 4 del programa)
     */
    private static void showSatellite() 
    {
        ArrayList satelliteList = SatelliteFileControl.readSatelliteList();
        showElementNames(satelliteList);
        
        if (SatelliteFileControl.getSatelliteAmount()>0)
        {
            Satellite planet=SatelliteFileControl.readSatellite(User.selectSatellite()-1);
            String satelliteInfo= planet.toString();//(6.) La info del planeta se recoge.
            System.out.println(satelliteInfo);//(7.) Muestra la info del planeta.
        }else{
            System.out.println(ConsoleColors.RED+"No hay satélites todavía");
        }
    }
    
    /**
     * Genera el archivo html de un planeta.
     * (Opcion 5 del programa)
     */
     private static void generateWeb()
    {
        ArrayList planetList = PlanetFileControl.readPlanetList();
        showElementNames(planetList);
        if (PlanetFileControl.getPlanetAmount()>0)
        {
            //(10.) Genera archivo HTML del planeta seleccionado.
            HtmlFileControl.generateHTMLFile(User.selectPlanet()-1);
            System.out.println(ConsoleColors.GREEN+"Archivo HTML generado correctamente.");
        }else{
            System.out.println(ConsoleColors.RED+"No hay planetas todavía");
        }
    }
     
     /**
      * Ejecuta una web.
      * (Opción 6 del programa)
      */
     private static void executeWeb()
     {
         File [] file = HtmlFileControl.file.listFiles();
         
         if (file.length>0)
         {
            int loopIndex=1;
            int userAns;

            do{
                for (File f:file)
               {
                    System.out.println(loopIndex+"- "+f.getName());
                    loopIndex++;
                }
               userAns=User.insertUnsignedInt("Selecciona un archivo");

               if (userAns>file.length)
               {
                   System.out.println(ConsoleColors.RED+"Selecciona un archivo válido...");
               }
            }while(userAns>file.length);

            HtmlFileControl.executeFile(userAns);
            System.out.println(ConsoleColors.GREEN+"Se ha ejecutado el archivo correctamente.");
         }else{
             System.out.println(ConsoleColors.RED+"Todavía no existen archivos HTML...");
         }
         
     }
     
    /**
     * Elimina todos los ficheros de datos.
     * (Opción -1 del programa)
     */
    public static void eraseAllInfo()
    {
        PlanetFileControl.file.delete();
        SatelliteFileControl.file.delete();
        
        File [] file = HtmlFileControl.file.listFiles();
        
        for (File f:file)
        {
            f.delete();
        }
    }
    
       /**
     * Muestra una lista numerada con nombres de algún elemento
     * en el orden de su archivo binario correspondiente.
     * @param elementList lista de elementos a mostrar.
     */
    public static void showElementNames(ArrayList<Element> elementList)
    {
        int nameIndex=1;

        for (Element element:elementList)
        {
            System.out.println(nameIndex+"- "+element.getName());
            nameIndex++;
        }
    }
}
