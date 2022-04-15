package pt.isec.pa.apoio_poe.utils;

import java.io.IOException;

public class Utils {

    private Utils() {}

    public static void clearScreen(){
        for(int i = 0; i < 20; i++){
            System.out.println();
        }
    }
    public static void pressToContinue(){
        System.out.print("[·] Prima qualquer tecla para continuar...");
        try{
            System.in.reset();
            System.in.read();
        } catch (IOException ignored) {}
    }

    public static void mostrarASCII(){
        System.out.println( "  ____       _____   ____  _____ ___ ____  \n" +
                            " |  _ \\ ___ | ____| |  _ \\| ____|_ _/ ___|\n" +
                            " | |_) / _ \\|  _|   | | | |  _|  | |\\___ \\ \n" +
                            " |  __/ (_) | |___  | |_| | |___ | | ___) |\n" +
                            " |_|   \\___/|_____| |____/|_____|___|____/ \n");
    }
}
