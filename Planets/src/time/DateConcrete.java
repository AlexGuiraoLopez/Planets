package time;
/** 
 * @author Alex Guirao López <aguiraol2021@cepnet.net>
 */
public class DateConcrete 
{
    private byte year;
    private byte month;
    private byte day;

    public DateConcrete(byte year, byte month, byte day) 
    {
        if (checkDateFormat(month, day))
        {
            this.year=year;
            this.month=month;
            this.day=day;
        }
    }

    /**
     * Comprueba el formato de la fecha.
     * De momento no se tiene en cuenta los meses con menos de 31 días.
     * @param month mes a comprobar.
     * @param day día a comprobar.
     * @return TRUE si el formato es correcto.
     */
    private boolean checkDateFormat(byte month, byte day)
    {
        boolean isCorrect=false;
        
        if (month>=1&&month<=12&&day>=1&&day<=31)
        {
            isCorrect=true;
        }
        
        return isCorrect;
    }
    
    
    @Override
    public String toString() 
    {
        return year + "//" + month + "//" + day ;
    }
}
