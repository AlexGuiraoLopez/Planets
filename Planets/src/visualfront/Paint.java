package visualfront;

/**
 * Dibuja visuales utilizando púramente código ASCII.
 * @author Alex Guirao López <aguiraol2021@cepnet.net>
 */
public class Paint 
{
    /**
     * Genera un banner de bienvenida al programa.
     * @param title título del banner.
     */
    public static void drawBanner(String title)
    {
        System.out.println("╔═════════════════╗");
        System.out.println("║ "+title+"  ║        ");
        System.out.println("╚═════════════════╝");
    }
    
    /**
     * Genera una linea de separación entre secciones del programa.
     */
    public static void breakLine()
    {
        System.out.println("==========================");
    }
     
    /**
     * Dibuja la imagen principal del programa.
     */
    public static void drawMainImage()
    {
            System.out.println("   *   .                  .              .        .   *     .                .");
            System.out.println(".      .                     .       .           .      .           .       .");
            System.out.println("                             .             .      .");
            System.out.println(".              .                  .           .");
            System.out.println("     .");
            System.out.println(".          .                 ,                ,    ,");
            System.out.println("          \\          .              .           .");
            System.out.println("   .   \\   ,");
            System.out.println("      .         .                 .                   .            .");
            System.out.println(".         \\                 ,             .                .");
            System.out.println("  .             #\\##\\#      .                              .        .");
            System.out.println("        .    #  #O##\\###                .                        .");
            System.out.println("  .         #*#  #\\##\\###                       .                     ,");
            System.out.println("        .   ##*#  #\\##\\##               .                     .");
            System.out.println(" .          ##*#  #o##\\#         .                             ,       .");
            System.out.println("    .         *#  #\\#     .                    .             .          ,");
            System.out.println("\\          .                         .");
            System.out.println("____^/\\___^--____/\\____O______________/\\/\\---/\\___________---______________");
            System.out.println("/\\^   ^  ^    ^                  ^^ ^  '\\ ^          ^       ---");
            System.out.println("--           -            --  -      -         ---  __       ^");
            System.out.println("--  __                      ___--  ^  ^                         --  __");
     }
     
}
