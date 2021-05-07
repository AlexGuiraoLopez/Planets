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
     
     public static void drawMainImage()
     {
            System.out.println("              *   .                  .              .        .   *     .                .");
            System.out.println(".         .                     .       .           .      .           .       .");
            System.out.println("o                             .             .      .");
            System.out.println(".              .                  .           .");
            System.out.println("0     .");
            System.out.println(".          .                 ,                ,    ,");
            System.out.println("          \\          .              .           .");
            System.out.println("   .   \\   ,");
            System.out.println("      .    o     .                 .                   .            .");
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
