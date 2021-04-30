package visualfront;

import java.util.ArrayList;

/**
 * @author Alex Guirao López <aguiraol2021@cepnet.net>
 */
public class Menu 
{
    private ArrayList<String> menuOptions; /*Texto para los menús*/
    
    public Menu(ArrayList menuOptions)
    {
        this.menuOptions=menuOptions;
    }
    
    /**
     * Muestra el menú con su formato.
     * El primer elemento del array corresponde al header y el resto sus opciones.
     */
    public void showMenu()
    {
        System.out.println(menuOptions.get(0)); //Cabecera del menú
        
        for (int i = 1; i < menuOptions.size(); i++) 
        {
            System.out.println("  "+menuOptions.get(i));  //Opciones del menú
        }
        System.out.print("Introduce una opcion: ");
    }
}
