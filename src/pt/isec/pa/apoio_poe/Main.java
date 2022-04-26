package pt.isec.pa.apoio_poe;

import pt.isec.pa.apoio_poe.model.fsm.PoEContext;
import pt.isec.pa.apoio_poe.ui.text.PoEUI;

public class Main {
    public static void main(String[] args) {
        PoEContext fsm = new PoEContext();
        PoEUI ui = new PoEUI(fsm);
        ui.start();
    }
}