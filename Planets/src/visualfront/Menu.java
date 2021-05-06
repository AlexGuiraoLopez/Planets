package visualfront;


/**
 * @author Alex Guirao López <aguiraol2021@cepnet.net>
 */
public class Menu 
{
    private String[] menuOptions; /*Texto para los menús*/
    
    public Menu(String[] menuOptions)
    {
        this.menuOptions=menuOptions;
    }
    
    /**
     * Muestra el menú con su formato.
     * El primer elemento del array corresponde al header y el resto sus opciones.
     */
    public void showMenu()
    {
        System.out.println(menuOptions[0]); //Cabecera del menú
        
        for (int i = 1; i < menuOptions.length; i++) 
        {
            System.out.println("  "+menuOptions[i]);  //Opciones del menú
        }
        Paint.breakLine();
        System.out.print("Introduce una opcion: ");
    }
}
