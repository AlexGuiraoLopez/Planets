package mainclass;

import visualfront.Menu;

/**
 * @author Alex Guirao López <aguiraol2021@cepnet.net>
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
        Menu mainMenu = new Menu(mainMenuLines);
        mainMenu.showMenu();
    }
}
