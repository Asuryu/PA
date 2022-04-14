package pt.isec.pa.apoio_poe;

import pt.isec.pa.apoio_poe.model.fsm.PoEContext;
import pt.isec.pa.apoio_poe.ui.text.PoEUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        PoEContext fsm = new PoEContext();
        PoEUI ui = new PoEUI(fsm);

        Scanner sc = new Scanner(new File("src/pt/isec/pa/apoio_poe/csv/alunos.csv"));
        sc.useDelimiter(",");
        while(sc.hasNext()){
            System.out.print(sc.next() + "  ");
        }
        sc.close();
        System.out.println("\n");
        ui.start();
    }
}