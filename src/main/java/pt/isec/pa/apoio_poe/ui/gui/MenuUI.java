package pt.isec.pa.apoio_poe.ui.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.CSSManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.ImageManager;

import java.io.File;

/**
 * A classe MenuUI é uma classe que representa a interface gráfica
 * do menu inicial da aplicação
 */

public class MenuUI extends BorderPane {
    final ToggleGroup group = new ToggleGroup();
    ModelManager model;
    Button btnStart, btnNext, fcBtn;
    Text stateText, questionText;
    RootPane root;
    StackPane stackPane;
    VBox menu, load, select;
    HBox buttonsBox;
    RadioButton rb, rb2;
    ImageView image;
    FileChooser fileChooser = new FileChooser();
    File selectedFile;

    public MenuUI(ModelManager model, RootPane root) {
        this.model = model;
        this.root = root;
        //this.title.setText("Menu");
        createViews();
        registerHandlers();
        update();
    }

    /**
     * Método que cria as vistas da interface gráfica
     */
    private void createViews() {
        image = new ImageView(ImageManager.getImage("icon.png"));
        image.setFitWidth(124);
        image.setFitHeight(124);
        image.setPreserveRatio(true);

        VBox textBox = new VBox();
        stateText = new Text("Bem-vindo ao PoEDEIS");
        stateText.setFill(javafx.scene.paint.Color.WHITE);
        stateText.setStyle("""
                            -fx-font-family: "Arial";
                            -fx-text-fill: #FFFFFF;
                            -fx-border-radius: 10px;
                            -fx-font-size: 20px;
                            -fx-font-weight: bold;
                            """);
        questionText = new Text("Software de Gestão de projetos e estágios");
        questionText.setFill(javafx.scene.paint.Color.WHITE);
        questionText.setStyle("""
                            -fx-font-family: "Arial";
                            -fx-text-fill: #FFFFFF;
                            -fx-border-radius: 10px;
                            -fx-font-size: 15px;
                            """);

        textBox.getChildren().addAll(stateText, questionText);
        textBox.setAlignment(Pos.CENTER);
        textBox.setSpacing(10);
        textBox.getChildren().get(0).setId("stateText");
        textBox.getChildren().get(1).setId("questionText");


        buttonsBox = new HBox(10);
        rb = new RadioButton();
        rb.setText("Sim");
        rb.setStyle("""
                            -fx-font-family: Arial;
                            -fx-text-fill: #FFFFFF;
                            -fx-border-radius: 10px;
                            -fx-font-size: 15px;
                            """);
        rb2 = new RadioButton();
        rb2.setText("Não");
        rb2.setStyle("""
                            -fx-font-family: Arial;
                            -fx-text-fill: #FFFFFF;
                            -fx-border-radius: 10px;
                            -fx-font-size: 15px;
                            """);
        rb.setToggleGroup(group);
        rb2.setToggleGroup(group);
        rb2.setSelected(true);

        buttonsBox.getChildren().addAll(rb, rb2); // Radio Buttons
        buttonsBox.setAlignment(Pos.CENTER);

        btnStart = new Button("Start");
        btnStart.setId("btnStart");
        btnStart.setStyle("""
                            -fx-font-family: Arial;
                            -fx-text-fill: #FFFFFF;
                            -fx-background-color: #af2821;
                            -fx-border-radius: 10px;
                            -fx-font-size: 15px;
                            """);
        btnStart.setAlignment(Pos.BOTTOM_CENTER);

        btnNext = new Button("Next");
        btnNext.setId("btnNext");
        btnNext.setStyle("""
                            -fx-font-family: Arial;
                            -fx-text-fill: #FFFFFF;
                            -fx-background-color: #af2821;
                            -fx-border-radius: 10px;
                            -fx-font-size: 15px;
                            """);
        btnNext.setAlignment(Pos.BOTTOM_CENTER);

        menu = new VBox(5); // 5 pixels de espaçamento entre os elementos
        menu.getChildren().addAll(image, textBox, buttonsBox, btnStart);
        menu.setStyle("-fx-background-color: #212121;");
        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(20);
        menu.getChildren().get(2).setVisible(false);

        stackPane = new StackPane(
                new ConfigUI(model),
                new ApplicationOptUI(model),
                new PropAttributionUI(model),
                new OriAttributionUI(model),
                new ReviewUI(model)
        );
        stackPane.setStyle("-fx-background-color: #212121;");

        fcBtn = new Button("Selecionar");

        this.setCenter(menu);
    }

    /**
     * Método que regista os handlers da interface gráfica
     */
    private void registerHandlers() {
        model.addPropertyChangeListener(ModelManager.PROP_STATE, evt -> {
            update();
        });

        btnStart.setOnAction(evt -> {
            menu.setVisible(false);
            VBox textBox = new VBox();

            stateText.setText("Ficheiro de estado:");
            questionText.setText("Pretende retomar o processo guardado?");

            textBox.getChildren().addAll(stateText, questionText);
            textBox.setAlignment(Pos.CENTER);
            textBox.setSpacing(10);
            textBox.getChildren().get(0).setId("stateText");
            textBox.getChildren().get(1).setId("questionText");

            select = new VBox(fcBtn);
            select.setAlignment(Pos.CENTER);

            load = new VBox(5); // 5 pixels de espaçamento entre os elementos
            load.getChildren().addAll(image, textBox, select, buttonsBox, btnNext);
            load.setStyle("-fx-background-color: #212121;");
            load.setAlignment(Pos.CENTER);
            load.setSpacing(20);
            load.getChildren().get(3).setVisible(true);

            this.setCenter(load);
        });

        fcBtn.setOnAction(evt -> {
            selectedFile = fileChooser.showOpenDialog(this.getScene().getWindow());
            System.out.println(selectedFile.getAbsolutePath());
        });

        btnNext.setOnAction(evt -> {
            root.setCenter(stackPane);
        });

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle old_toggle, Toggle new_toggle) {
                if (group.getSelectedToggle() == rb){ //Significa que o radio button selecionado é o "Sim"
                    model.load(selectedFile.getAbsolutePath());
                }
            }
        });

    }

    /**
     * Método que atualiza a interface gráfica
     */
    private void update() {
    }
}
