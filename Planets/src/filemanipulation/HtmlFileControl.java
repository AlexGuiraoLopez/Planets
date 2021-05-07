package filemanipulation;

import elements.Planet;
import elements.Satellite;
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
     * @return número de ficheros que hay en el directorio.
     */
    public static int getHTMLFileAmount()
    {
        File file = new File("htmlfiles/");
        return file.listFiles().length;  //Devuelve un array con el número de archivos que hay en el directorio.
    }
    
    public static void generateHTMLFile(int planetPosition)
    {
        File file = new File(PlanetFileControl.getPath());
        RandomAccessFile planetFile; 
        BufferedWriter bf;
        Planet planet = PlanetFileControl.readPlanetList().get(planetPosition);
        String planetName=planet.getFormattedName().trim().toUpperCase();
        if (file.exists())
        {
            try {
                planetFile= new RandomAccessFile(file, "r");
                FileWriter fw = new FileWriter("htmlfiles/"+planet.getFormattedName().trim()+".html");
                bf = new BufferedWriter(fw);
                
                //Escritura del archivo linea a linea.
                writeDocType(bf);
                
                openHtmlTag(bf);
                    writeHead(bf, planet);
                    openBodyTag(bf);
                    
                        openArticleTag(bf, "objectInfo");
                            writeH1(bf, planetName);
                            writeImg(bf, planet);
                            writeH3(bf, planetName);
                            writeH3(bf, planetName);
                            writeH2(bf, "Satélites");
                            
                            openTableTag(bf);
                          /*
                        for (int i = 0;i<noseke;i++)
                        {                         
                            openTrTag(bf);
                                writeTdTag(bf, getImagePath(planetName));
                                writeTdTag(bf, satelliteInfo);
                                writeTdTag(bf, satelliteInfo);
                            closeTrTag(bf);
                          }
                        */
                          closeTableTag(bf);
                          
                        closeArticleTag(bf);
                        
                    closeBodyTag(bf);
                closeHtmlTag(bf);
                    
                
                planetFile.close();
                
                bf.close();
                fw.close();
                System.out.println(ConsoleColors.GREEN+"Archivo HTML generado correctamente.");
               
            } 
            catch (FileNotFoundException ex) 
            {
                System.out.println("No se ha podido encontrar el archivo"+ ex);
                ex.printStackTrace();
            }
            catch (IOException ex) 
            {
                System.out.println("No se ha podido acceder al archivo"+ ex);
                ex.printStackTrace();
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
  
    private static void writeH1(BufferedWriter bf, String planetName) throws IOException
    {
        bf.write("<h1>"+planetName+"</h1>"+"\n");
    }
    
    private static void writeH2(BufferedWriter bf, String planetInfo) throws IOException
    {
        bf.write("<h2>"+planetInfo+"</h2>"+"\n");
    }
    
    private static void writeH3(BufferedWriter bf, String planetInfo) throws IOException
    {
        bf.write("<h3>"+planetInfo+"</h3>"+"\n");
    }
    
      private static void openTableTag(BufferedWriter bf) throws IOException
    {
        bf.write("<table>"+"\n");
    }
    
    private static void closeTableTag(BufferedWriter bf) throws IOException
    {
        bf.write("</table>"+"\n");
    }
    
      private static void openTrTag(BufferedWriter bf) throws IOException
    {
        bf.write("<tr>"+"\n");
    }
    
    private static void writeTdTag(BufferedWriter bf, String satelliteInfo) throws IOException
    {
        bf.write("<td>"+satelliteInfo+"</td>"+"\n");
    }
    
    private static void closeTrTag(BufferedWriter bf) throws IOException
    {
        bf.write("</tr>"+"\n");
    }
    
    private static void writeImg(BufferedWriter bf, Planet planet) throws IOException
    {
        bf.write("<img src=\"../img/"+planet.getFormattedName().trim()+".png"+"\" alt=\""+planet.getFormattedName().trim()+"Image"+"\""+">"+"\n");
    }
    
    private static String getImagePath(String planetName) throws IOException
    {
        return "<img src=\"../img/"+planetName+".png"+"\" alt=\""+planetName+"Image"+"\""+">"+"\n";
    }
    
    //=====================EXECUTION============================
    public static void executeFile(int planetPosition)
    {
        File file = new File("htmlfiles\\"+PlanetFileControl.readPlanetName(planetPosition).toLowerCase()+".html");
        String absolutePath= file.getAbsolutePath();
        
        //String webBrowserPath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
        String webBrowserPath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
        
        Runtime r= Runtime.getRuntime();
        Process p=null;
        
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + absolutePath);
            //p=r.exec(webBrowserPath+" "+absolutePath);
        } 
        catch (IOException ex) 
        {
            System.out.println("No se ha podido acceder al archivo"+ ex);
        }
    }
}
