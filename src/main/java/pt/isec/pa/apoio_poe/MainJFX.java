package pt.isec.pa.apoio_poe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainJFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new Pane(), 400, 200);
        stage.setScene(scene);
        stage.setTitle("PA-DEIS-ISEC");
        stage.show();
    }
}
