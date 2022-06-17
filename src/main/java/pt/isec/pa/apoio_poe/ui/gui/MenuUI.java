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
    private int selected = 2;
    final ToggleGroup group = new ToggleGroup();
    ModelManager model;
    Button btnStart, btnNext, fcBtn, btnPrev = new Button("Voltar");
    Text stateText, questionText, fileText;
    RootPane root;
    StackPane stackPane;
    VBox menu, load, select, textBox;
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

        textBox = new VBox();
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
                            -fx-cursor: hand;
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

        btnStart = new Button("Iniciar");
        btnStart.setId("btnStart");
        btnStart.setPrefSize(120, 25);
        btnStart.setStyle("""
                            -fx-font-family: Arial;
                            -fx-text-fill: #FFFFFF;
                            -fx-background-color: #79b332;
                            -fx-border-radius: 10px;
                            -fx-font-size: 15px;
                            -fx-cursor: hand;
                            """);
        btnStart.setAlignment(Pos.BOTTOM_CENTER);

        btnNext = new Button("Avançar");
        btnNext.setId("btnNext");
        btnNext.setPrefSize(120, 25);
        btnNext.setStyle("""
                            -fx-font-family: Arial;
                            -fx-text-fill: #FFFFFF;
                            -fx-background-color: #79b332;
                            -fx-border-radius: 10px;
                            -fx-font-size: 15px;
                            -fx-cursor: hand;
                            """);
        btnNext.setAlignment(Pos.BOTTOM_CENTER);

        menu = new VBox(5); // 5 pixels de espaçamento entre os elementos
        menu.getChildren().addAll(image, textBox, btnStart);
        menu.setStyle("-fx-background-color: #212121;");
        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(20);

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

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle newValue) {
                if (group.getSelectedToggle() == rb)
                    selected = 1;
            }
        });

        btnStart.setOnAction(evt -> {
            rb2.setSelected(true);
            selected = 2;
            menu.getChildren().add(buttonsBox);
            menu.getChildren().remove(btnStart);
            menu.getChildren().add(btnNext);
            questionText.setText("Deseja carregar um ficheiro de estado?");
        });

        fcBtn.setOnAction(evt -> {
            selectedFile = fileChooser.showOpenDialog(this.getScene().getWindow());
            if (selectedFile != null) {
                fileText.setVisible(true);
                fileText.setText("Ficheiro selecionado: " + selectedFile.getName());
                selected = 3;
            }
        });

        btnNext.setOnAction(evt -> {
             if (selected == 1) {
                menu.setVisible(false);
                VBox textBox = new VBox();

                stateText.setText("Ficheiro de estado:");
                questionText.setText("Selecione um ficheiro de estado caso queira retomar um processo anterior");

                textBox.getChildren().addAll(stateText, questionText);
                textBox.setAlignment(Pos.CENTER);
                textBox.setSpacing(10);
                textBox.getChildren().get(0).setId("stateText");
                textBox.getChildren().get(1).setId("questionText");

                fileText = new Text("");
                fileText.setFill(javafx.scene.paint.Color.WHITE);
                fileText.setStyle("""
                            -fx-font-family: "Arial";
                            -fx-text-fill: #FFFFFF;
                            -fx-border-radius: 10px;
                            -fx-font-size: 15px;
                            """);
                fileText.setVisible(false);
                select = new VBox(fcBtn, fileText);
                select.setAlignment(Pos.CENTER);
                select.setSpacing(10);

                btnPrev.setId("btnPrev");
                btnPrev.setPrefSize(120, 25);
                btnPrev.setStyle("""
                            -fx-font-family: Arial;
                            -fx-text-fill: #FFFFFF;
                            -fx-background-color: #af2821;
                            -fx-border-radius: 10px;
                            -fx-font-size: 15px;
                            -fx-cursor: hand;
                            """);
                btnPrev.setAlignment(Pos.BOTTOM_CENTER);

                HBox buttons = new HBox(10);
                buttons.getChildren().addAll(btnPrev, btnNext);

                buttons.setAlignment(Pos.CENTER);

                load = new VBox(5); // 5 pixels de espaçamento entre os elementos
                load.getChildren().addAll(image, textBox, select, buttons);
                load.setStyle("-fx-background-color: #212121;");
                load.setAlignment(Pos.CENTER);
                load.setSpacing(20);
                this.setCenter(load);
             }
             if(selected == 2)
                root.setCenter(stackPane);
             if(selected == 3) {
                 model.load(selectedFile.getName());
                 root.setCenter(stackPane);
             }
        });

        btnPrev.setOnAction(evt -> {
            load.setVisible(false);
            menu.setVisible(true);
            stateText.setText("Bem-vindo ao PoEDEIS");
            stateText.setFill(javafx.scene.paint.Color.WHITE);
            stateText.setStyle("""
                            -fx-font-family: "Arial";
                            -fx-text-fill: #FFFFFF;
                            -fx-border-radius: 10px;
                            -fx-font-size: 20px;
                            -fx-font-weight: bold;
                            """);
            questionText.setText("Software de Gestão de projetos e estágios");
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
            menu = new VBox(5); // 5 pixels de espaçamento entre os elementos
            menu.getChildren().addAll(image, textBox, btnStart);
            menu.setStyle("-fx-background-color: #212121;");
            menu.setAlignment(Pos.CENTER);
            menu.setSpacing(20);
            this.setCenter(menu);
        });

    }

    /**
     * Método que atualiza a interface gráfica
     */
    private void update() {
    }
}
