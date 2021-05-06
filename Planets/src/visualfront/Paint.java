package visualfront;

/**
 * Dibuja figuras utilizando púramente código ASCII.
 * @author Alex Guirao López <aguiraol2021@cepnet.net>
 */
public class Paint 
{
    public static void drawBanner(String _message)
    {
        System.out.println("╔═════════════════╗");
        System.out.println("║ "+_message+"  ║        ");
        System.out.println("╚═════════════════╝");
    }
    
     public static void breakLine()
    {
        System.out.println("=========================");
    }
}
