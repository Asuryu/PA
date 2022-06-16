package pt.isec.pa.apoio_poe.ui.gui;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import pt.isec.pa.apoio_poe.model.ModelManager;

/**
 * A classe MenuUI é uma classe que representa a interface gráfica
 * do menu inicial da aplicação
 */

public class MenuUI extends BorderPane {
    ModelManager model;
    Button btnStart;
    Text title;
    RootPane root;
    StackPane stackPane;

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
        stackPane = new StackPane(
                new ConfigUI(model),
                new ApplicationOptUI(model),
                new PropAttributionUI(model),
                new OriAttributionUI(model),
                new ReviewUI(model)
        );
        stackPane.setStyle("-fx-background-color: #212121;");
        btnStart = new Button("Start");
        this.setCenter(btnStart);
        //ActionButtons buttons = new ActionButtons(model);
        //this.setPadding(new Insets(10, 10, 10, 10));
        //this.setBottom(buttons);
    }

    /**
     * Método que regista os handlers da interface gráfica
     */
    private void registerHandlers() {
        model.addPropertyChangeListener(ModelManager.PROP_STATE, evt -> {
            update();
        });
        btnStart.setOnAction(evt -> {
            root.setCenter(stackPane);
        });

    }

    /**
     * Método que atualiza a interface gráfica
     */
    private void update() {
    }
}
