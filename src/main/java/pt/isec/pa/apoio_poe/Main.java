package pt.isec.pa.apoio_poe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.fsm.PoEContext;
import pt.isec.pa.apoio_poe.ui.text.PoEUI;

public class Main {
    public static void main(String[] args) {
        PoEContext fsm = new PoEContext();
        PoEUI ui = new PoEUI(fsm);
        ui.start();
    }
}