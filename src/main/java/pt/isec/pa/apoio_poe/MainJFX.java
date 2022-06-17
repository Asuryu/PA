package pt.isec.pa.apoio_poe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.ui.gui.RootPane;
import pt.isec.pa.apoio_poe.ui.gui.resources.ImageManager;

import java.io.File;

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
        stage.getIcons().add(ImageManager.getImage("icon.png"));
        stage.setResizable(false);
        stage.show();

        stage.setOnCloseRequest(evt -> {
            evt.consume();
            stop();
        });
    }

    @Override
    public void stop(){
        Stage closeDialog = new Stage();
        closeDialog.getIcons().add(ImageManager.getImage("icon.png"));
        Button yesBtn = new Button("Sim");
        Button noBtn = new Button("Não");
        yesBtn.setStyle("""
                            -fx-font-family: Arial;
                            -fx-text-fill: #FFFFFF;
                            -fx-background-color: #79b332;
                            -fx-border-radius: 10px;
                            -fx-font-size: 15px;
                            """);
        noBtn.setStyle("""
                            -fx-font-family: Arial;
                            -fx-text-fill: #FFFFFF;
                            -fx-background-color: #af2821;
                            -fx-border-radius: 10px;
                            -fx-font-size: 15px;
                            """);
        HBox elements = new HBox(yesBtn, noBtn);
        elements.setSpacing(10);

        Text title = new Text("Deseja guardar o processo atual?");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        title.setFill(javafx.scene.paint.Color.WHITE);
        VBox vbox = new VBox(title, elements);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);
        elements.setAlignment(javafx.geometry.Pos.CENTER);
        vbox.setSpacing(10);

        BorderPane closeRoot = new BorderPane(vbox);
        closeRoot.setCenter(vbox);
        closeRoot.setStyle("-fx-background-color: #212121;");
        Scene dialogScene = new Scene(closeRoot,450,200);
        closeDialog.setScene(dialogScene);
        closeDialog.setTitle("Salvar");
        closeDialog.setResizable(false);
        closeDialog.show();

        yesBtn.setOnAction(evt -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Salvar");
            File selectedDirectory = directoryChooser.showDialog(closeDialog);
            if (selectedDirectory != null) {
                model.save(selectedDirectory.getAbsolutePath() + "/save.ser");
                closeDialog.close();
                System.exit(0);
            }
        });

        noBtn.setOnAction(evt -> {
            closeDialog.close();
            System.exit(0);
        });
    }
}
