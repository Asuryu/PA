package pt.isec.pa.apoio_poe.utils;

import java.io.IOException;

/**
 * Classe com várias funções de utilidade
 */
public class Utils {

    private Utils() {}

    /**
     * Limpa o ecrã
     */
    public static void clearScreen(){
        for(int i = 0; i < 20; i++){
            System.out.println();
        }
    }

    /**
     * Espera que o utilizador clique numa tecla para continuar
     * funcionamento normal do programa
     */
    public static void pressToContinue(){
        System.out.print("[·] Prima qualquer tecla para continuar...");
        try{
            System.in.read();
        } catch (IOException ignored) {}
    }

    /**
     * Mostra uma arte com caracteres ASCII
     * que pode servir como cabeça de menu
     */
    public static void mostrarASCII(){
        System.out.println( "  ____       _____   ____  _____ ___ ____  \n" +
                            " |  _ \\ ___ | ____| |  _ \\| ____|_ _/ ___|\n" +
                            " | |_) / _ \\|  _|   | | | |  _|  | |\\___ \\ \n" +
                            " |  __/ (_) | |___  | |_| | |___ | | ___) |\n" +
                            " |_|   \\___/|_____| |____/|_____|___|____/ \n");
    }
}
