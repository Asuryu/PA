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
        ui.start();
    }
}