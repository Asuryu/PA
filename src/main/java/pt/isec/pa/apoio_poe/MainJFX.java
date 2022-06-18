package pt.isec.pa.apoio_poe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.CSSManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.ImageManager;
import pt.isec.pa.apoio_poe.ui.gui.RootPane;

import java.io.File;

public class MainJFX extends Application {

    final ModelManager model;

    public MainJFX() {
        model = new ModelManager();
    }

    @Override
    public void start(Stage stage) {
        RootPane root = new RootPane(model);
        Scene scene = new Scene(root,900,500);
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
                    -fx-background-color: #ffffff;
                    -fx-text-fill: #d31e13;
                    -fx-font-size: 12px;
                    -fx-border-radius: 10px;
                    -fx-cursor: hand;
                    """);
        noBtn.setStyle("""
                    -fx-background-color: #d31e13;
                    -fx-text-fill: #FFFFFF;
                    -fx-font-size: 12px;
                    -fx-border-radius: 10px;
                    -fx-cursor: hand;
                    """);
        HBox elements = new HBox(yesBtn, noBtn);
        elements.setSpacing(10);

        ImageView imageView = new ImageView(ImageManager.getImage("icon.png"));
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setPreserveRatio(true);

        Text title = new Text("Deseja guardar o processo atual?");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        title.setFill(javafx.scene.paint.Color.WHITE);
        VBox vbox = new VBox(imageView, title, elements);
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
