package pt.isec.pa.apoio_poe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.fsm.PoEContext;
import pt.isec.pa.apoio_poe.ui.text.PoEUI;

public class Main extends Application {
    //public static void main(String[] args) {
    //    PoEContext fsm = new PoEContext();
    //    PoEUI ui = new PoEUI(fsm);
    //    ui.start();
    //}

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new Pane(), 400, 200);
        stage.setScene(scene);
        stage.setTitle("PA-DEIS-ISEC");
        stage.show();
    }
}