package mainclass;

import elements.SpaceElement;
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
     * Programa de gestión para planetas y satélites.
     * Permite registrar y leer información sobre ellos.
     * También permite generar y ejecutar archivos html 
     * con los datos correspondientes para ver 
     * la información más detallada.
     * @param args 
     */
    public static void main(String[] args) 
    {
        Sound.startAudio(); 
        Paint.drawMainImage(); 
        Paint.breakLine();
        Menu mainMenu = new Menu(MAIN_MENU_LINES);
        
        int userAns;
        
        do{
            //Se muestra el menú principal para que el usuario elija una opción.
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
                    break;
                    
                case 4: //Mostrar satélite.
                    showSatellite();
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
                    
                case 0: //Salir
                    System.out.println("¡No olvides que los datos persisten!");
                    System.out.println("Vuelve a acceder cuando te apetezca ;)");
                    break;
                    
                default: 
                    System.out.println(ConsoleColors.RED+"Opción errónea");
                    break;
            }
            Paint.breakLine();
            
        }while(userAns!=0);
    }

    //========================MÉTODOS PRINCIPALES=========================

    /**
     * Inserta un planeta en el sistema.
     * (Opción 1 del programa)
     */
    private static void insertPlanet() 
    {
        System.out.println(ConsoleColors.CYAN+"    --Introducir planeta--");
        Planet planet = User.createPlanet(); //El usuario crea un planeta.
        PlanetFileControl.writePlanet(planet);//La info del planeta se registra en el fichero.
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
            Satellite satellite = User.createSatellite(); //El usuario crea un satélite.
            SatelliteFileControl.writeSatellite(satellite); //La info del satélite se registra en el fichero.
        }else{
            System.out.println(ConsoleColors.RED+"Inserta mínimo un planeta antes de satélites");
        }
    }
    
     /**
     * Muestra una lista numerada con nombres de un elemento (satélite o planeta)
     * en el orden de su archivo binario correspondiente.
     * También añade una opción para salir.
     * @param elementList lista de elementos a mostrar.
     */
    public static void showElementNames(ArrayList<SpaceElement> elementList)
    {
        int nameIndex=1;

        if (!elementList.isEmpty())
        {
            for (SpaceElement element:elementList)
            {
                System.out.println(nameIndex+"- "+element.getName());
                nameIndex++;
            }
            System.out.println("0- Salir");
        }
    }
    
    /**
     * Muestra la información de un planeta.
     * (Opción 3 del programa)
     */
    private static void showPlanet() 
    {
        System.out.println(ConsoleColors.CYAN+ "   --Mostrar planeta--");
        //Guarda en una lista los planetas del archivo en orden.
        ArrayList planetList = PlanetFileControl.readPlanetList(); 
        showElementNames(planetList); //Muestra los nombres de los planetas al usuario.
        
        if (PlanetFileControl.getPlanetAmount()>0)
        {
            /*El usuario selecciona el planeta escribiendo el número de su posición 
            (-1 para igualar al sistema de posicionamiento de los registros).*/
            int userAns= User.selectPlanet();
            if(userAns!=0)
            {
                Planet planet=PlanetFileControl.readPlanet(userAns-1);
                String planetInfo= planet.toString();//La info del planeta se recoge.
                System.out.println(planetInfo);//Muestra la info del planeta.
                Time.waitForSeconds(750);
            }
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
        System.out.println(ConsoleColors.PURPLE+ "   --Mostrar satélite--");
        //Guarda en una lista los satélites del archivo en orden.
        ArrayList satelliteList = SatelliteFileControl.readSatelliteList();
        showElementNames(satelliteList);
        
        if (SatelliteFileControl.getSatelliteAmount()>0)
        {
            /*El usuario selecciona el satélite escribiendo el número de su posición 
            (-1 para igualar al sistema de posicionamiento de los registros).*/
            int userAns=User.selectSatellite();
            if (userAns!=0)
            {
                Satellite planet=SatelliteFileControl.readSatellite(userAns-1);
                String satelliteInfo= planet.toString();//La info del planeta se recoge.
                System.out.println(satelliteInfo);//Muestra la info del planeta.
                Time.waitForSeconds(750);
            }
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
        System.out.println(ConsoleColors.YELLOW+ "   --Generar HTML--");
        //Guarda en una lista los planetas del archivo en orden.
        ArrayList planetList = PlanetFileControl.readPlanetList();
        showElementNames(planetList);
        
        if (PlanetFileControl.getPlanetAmount()>0)
        {
            //Genera archivo HTML del planeta seleccionado.
            HtmlFileControl.generateHTMLFile(User.selectPlanet()-1);
            System.out.println(ConsoleColors.GREEN+"Archivo HTML generado correctamente.");
        }else{
            System.out.println(ConsoleColors.RED+"No hay planetas todavía");
        }
    }
     
     /**
      * Ejecuta un archivo HTML para abrir la web del planeta en el navegador.
      * (Opción 6 del programa)
      */
     private static void executeWeb()
     {
         System.out.println(ConsoleColors.YELLOW+ "   --Ejecutar web--");
         File [] fileList = HtmlFileControl.file.listFiles();
         
         if (fileList.length>0)
         {
            int loopIndex=1;
            int userAns;

            do{ //Muestra los archivos html existentes.
                for (File f:fileList)
               {
                    System.out.println(loopIndex+"- "+f.getName());
                    loopIndex++;
                }
                
               userAns=User.insertUnsignedInt("Selecciona un archivo");

               if (userAns>fileList.length)
               {
                   System.out.println(ConsoleColors.RED+"Selecciona un archivo válido...");
               }
            }while(userAns>fileList.length);

            HtmlFileControl.executeFile(userAns); //Ejecuta el archivo HTML.
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
        System.out.println(ConsoleColors.RED+ "   --Eliminar datos--");
        char userAns;
        //Avisos de seguridad antes de eliminar los datos.
        System.out.println(ConsoleColors.RED+"¿Estás seguro de eliminar todos los datos?");
        System.out.println(ConsoleColors.RED+"Introduce S para aceptar. Cualquier otro carácter para cancelar. ");
        userAns=Keyboard.readChar();
        
        if (userAns==Character.toLowerCase('s'))
        {
            //Elimina todos los ficheros de datos.
            PlanetFileControl.file.delete();
            SatelliteFileControl.file.delete();
            File [] file = HtmlFileControl.file.listFiles();

            for (File f:file)
            {
                f.delete();
            }
            
            System.out.println(ConsoleColors.RED+"Los datos han sido eliminados correctamente.");
        }else{
            System.out.println(ConsoleColors.RED+"Operación cancelada.");
        }
    }
}
