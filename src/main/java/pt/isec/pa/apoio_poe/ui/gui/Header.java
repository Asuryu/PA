package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.model.fsm.PoEState;

/**
 * A classe Header é uma classe que representa o cabeçalho da aplicação gráfica
 */
public class Header extends VBox {
    final ModelManager model;
    Text title, subtitle;

    public Header(ModelManager model) {
        this.model = model;

        createViews();
        registerHandlers();
        update();
    }

    /**
     * Método que cria as vistas do cabeçalho
     * Cabeçalho é composto por um título e um subtítulo
     * O título é um Text com o nome da fase
     * O subtítulo é um Text com a indicação do número da fase em que se encontra
     */
    private void createViews() {

        VBox topBox = new VBox();
        topBox.setPadding(new Insets(10, 0, 3, 10));
        VBox bottomBox = new VBox();
        bottomBox.setPadding(new Insets(0, 0, 20, 10));

        title = new Text();
        subtitle = new Text();
        title.setFont(Font.font("Arial", FontWeight.BOLD, 35));

        subtitle.setFont(Font.font("Arial", 17));
        title.setFill(Color.WHITE);
        subtitle.setFill(Color.LIGHTGRAY);

        topBox.getChildren().add(title);
        bottomBox.getChildren().add(subtitle);
        this.setStyle("-fx-background-color: #212121;");
        this.getChildren().addAll(topBox, bottomBox);
    }

    /**
     * Método que regista os handlers do cabeçalho
     */
    private void registerHandlers() {
        model.addPropertyChangeListener(ModelManager.PROP_DATA, evt -> {
            update();
        });
        model.addPropertyChangeListener(ModelManager.PROP_STATE, evt -> {
            update();
        });
    }

    /**
     * Método que atualiza o cabeçalho de acordo com o estado da aplicação
     */
    private void update() {
        title.setText(String.valueOf(model.getState()));
        subtitle.setText("Fase " + (model.getState().ordinal() + 1) + "/" + PoEState.values().length);
    }
}
