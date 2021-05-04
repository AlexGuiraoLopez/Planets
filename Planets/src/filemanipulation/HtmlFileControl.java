package filemanipulation;

import elements.Planet;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
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
    public static int HTMLFileAmount()
    {
        //Cómo puedo saber cuántos archivos hay en una carpeta?
        return 1; //Provisional
    }
    
    public static void generateHTMLFile(int planetPosition)
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
                    
                        openArticleTag(bf, "objectInfo");
                        
                            writeImg(bf, planet);
                            writeH1(bf, planet);
                        closeArticleTag(bf);
                        
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
    
    
    private static void writeDocType(BufferedWriter bf) throws IOException
    {
        bf.write("<!DOCTYPE html>");
    }
    
    private static void openHtmlTag(BufferedWriter bf) throws IOException
    {
        bf.write("<html lang=\"es\" xmlns=\"http://www.w3.org/1999/xhtml\">");
    }
    
    private static void closeHtmlTag(BufferedWriter bf) throws IOException
    {
        bf.write("</html>");
    }
    
    private static void writeHead(BufferedWriter bf, Planet planet) throws IOException
    {
        bf.write("<head>"+"\n");
        bf.write("<meta charset=\"utf-8\"/>"+"\n");
        bf.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"+"\n");
        bf.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"+"\n");
        bf.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"../cssfiles/normalize.css\">"+"\n");
        bf.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"../cssfiles/style.css\">"+"\n");
        bf.write("<title>"+planet.getFormattedName().trim().toUpperCase()+"</title>"+"\n");
        bf.write("</head>"+"\n");
    }
    
    private static void openBodyTag(BufferedWriter bf) throws IOException
    {
        bf.write("<body>"+"\n");
    }
    
    private static void closeBodyTag(BufferedWriter bf) throws IOException
    {
        bf.write("</body>"+"\n");
    }
    
    private static void openArticleTag(BufferedWriter bf, String className) throws IOException
    {
       bf.write("<article class=\""+className+"\">"+"\n");
    }
    
    private static void closeArticleTag(BufferedWriter bf) throws IOException
    {
        bf.write("</article>"+"\n");
    }
    
    private static void writeH1(BufferedWriter bf, Planet planet) throws IOException
    {
        bf.write("<h1>"+planet.getFormattedName().trim().toUpperCase()+"</h1>"+"\n");
    }
    
    private static void writeImg(BufferedWriter bf, Planet planet) throws IOException
    {
        bf.write("<img src=\"../img/"+planet.getFormattedName().trim()+".png"+"\" alt=\""+planet.getFormattedName().trim()+"Image"+"\""+">"+"\n");
    }
    
    //=====================EXECUTION============================
    public static void executeFile(int planetPosition)
    {
        
        //String planetFilePath = "C:\\Users\\Aresu\\Desktop\\Aresu\\DAM\\M03Programacion\\Projects\\Retos\\Planets\\Planets\\htmlfiles\\"+PlanetFileControl.readPlanetName(planetPosition).toLowerCase()+".html";
        //String webBrowserPath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
        //String planetFilePath = "C:\\Users\\nexus\\Documents\\Estudios\\DAM\\M03Programacion\\Projects\\Retos\\Planetas\\Planets\\htmlfiles\\"+PlanetFileControl.readPlanetName(planetPosition).toLowerCase()+".html";
        String planetFilePath = "D:\\DAM\\M03Programacion\\Projects\\Retos\\Planets\\Planets\\htmlfiles\\"+PlanetFileControl.readPlanetName(planetPosition).toLowerCase()+".html";
        String webBrowserPath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
        
        Runtime r= Runtime.getRuntime();
        Process p=null;
        
        String [] comando = {webBrowserPath, planetFilePath};
        
        try {
            p=r.exec(comando);
        } 
        catch (IOException ex) 
        {
            System.out.println("No se ha podido acceder al archivo"+ ex);
        }
    }
}
