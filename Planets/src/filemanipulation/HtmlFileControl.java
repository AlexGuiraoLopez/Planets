package filemanipulation;

import elements.Planet;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import visualfront.ConsoleColors;

/**
 * @author Alex Guirao Lopez <aguiraol2021@cepnet.net>
 */
public class HtmlFileControl 
{
    /**
     * 
     * @param planetPosition 
     */
    public static void generatePlanetFile(int planetPosition)
    {
        File file = new File(PlanetFileControl.getPath());
        RandomAccessFile planetFile; 
        BufferedWriter bf;
        Planet planet = PlanetFileControl.readPlanetList().get(planetPosition);
        
        if (file.exists())
        {
            try {
                planetFile= new RandomAccessFile(file, "r");
                bf = new BufferedWriter(new FileWriter("htmlFiles/"+planet.getFormattedName().trim()+".html"));
                writeDocType(bf);
                
                openHtmlTag(bf);
                writeHead(bf, planet);
                openBodyTag(bf);
                
                writeImg(bf, planet);
                
                closeBodyTag(bf);
                closeHtmlTag(bf);
                
                
                planetFile.close();
                bf.close();
                System.out.println(ConsoleColors.GREEN+"Archivo HTML generado correctamente.");
               
            } 
            catch (FileNotFoundException ex) 
            {
                System.out.println("No se ha podido encontrar el archivo"+ ex);
            }
            catch (IOException ex) 
            {
                System.out.println("No se ha podido acceder al archivo"+ ex);
            }   
        }
    }
    
    private static void writeDocType(BufferedWriter bf)
    {
        try{
            bf.write("<!DOCTYPE html>");
           
        }
        catch (IOException ex) 
        {
              System.out.println("No se ha podido acceder al archivo");
        }
    }
    
    private static void openHtmlTag(BufferedWriter bf)
    {
        try {
            bf.write("<html lang=\"es\" xmlns=\"http://www.w3.org/1999/xhtml\">");
   
        } 
        catch (IOException ex) 
        {
            System.out.println("No se ha podido acceder al archivo");
        }
    }
    
    private static void closeHtmlTag(BufferedWriter bf)
    {
        try {
            bf.write("</html>");
    
        } 
        catch (IOException ex) 
        {
            System.out.println("No se ha podido acceder al archivo");
        }
    }
    
    private static void writeHead(BufferedWriter bf, Planet planet)
    {
        try {
            bf.write("<head>"+"\n");
            bf.write("<meta charset=\"utf-8\"/>"+"\n");
            bf.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"+"\n");
            bf.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"+"\n");
            bf.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"cssfiles/normalize.css\">"+"\n");
            bf.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"cssfiles/style.css\">"+"\n");
            bf.write("<title>"+planet.getFormattedName().trim().toUpperCase()+"</title>"+"\n");
            bf.write("</head>"+"\n");
        } 
        catch (IOException ex) 
        {
            System.out.println("No se ha podido acceder al archivo");
        }
    }
    
    private static void openBodyTag(BufferedWriter bf){
        try {
            bf.write("<body>"+"\n");
        } 
        catch (IOException ex) 
        {
              System.out.println("No se ha podido acceder al archivo");
        }
    }
    
    private static void closeBodyTag(BufferedWriter bf){
        try {
            bf.write("</body>"+"\n");
        } 
        catch (IOException ex) 
        {
              System.out.println("No se ha podido acceder al archivo");
        }
    }
    
    private static void writeImg(BufferedWriter bf, Planet planet)
    {
        try {
            bf.write("<img src=\"../img/"+planet.getFormattedName().trim()+".png"+"\" alt=\""+planet.getFormattedName().trim()+"Image"+"\""+">");
        } 
        catch (IOException ex) 
        {
            System.out.println("No se ha podido acceder al archivo");
        }
    }
}
