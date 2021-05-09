package time;

/**
 * @author  Alex Guirao López <aguiraol2021@cepnet.net>
 */
public class Time 
{
    /***
     * Este metodo genera una pausa en la ejecucion del código indicado en milisegundos.
     * @param time Tiempo que espera el codigo para seguir ejecutandose .
     */
    public static void waitForSeconds(int time)
    {
        try {
            Thread.sleep(time);
        } 
        catch (InterruptedException ex) 
        {
            System.out.println("El proceso se ha interrumpido"+ex);
        }
    }
}
