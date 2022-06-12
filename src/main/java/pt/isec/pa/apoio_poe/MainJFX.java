package pt.isec.pa.apoio_poe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.ui.gui.RootPane;

public class MainJFX extends Application {

    final ModelManager model;

    public MainJFX() {
        model = new ModelManager();
    }

    @Override
    public void start(Stage stage) {
        RootPane root = new RootPane(model);
        Scene scene = new Scene(root,800,450);
        stage.setScene(scene);
        stage.setTitle("PoEDEIS - Gestão de projetos e estágios");
        stage.setResizable(false);
        stage.show();
    }
}
