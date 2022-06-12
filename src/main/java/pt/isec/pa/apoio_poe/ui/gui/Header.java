package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.model.fsm.PoEState;

public class Header extends VBox {
    final ModelManager model;
    Text title, subtitle;

    public Header(ModelManager model) {
        this.model = model;

        createViews();
        registerHandlers();
        update();
    }

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
        this.setStyle("-fx-background-color: #9297C4;");
        this.getChildren().addAll(topBox, bottomBox);
    }

    private void registerHandlers() {
        model.addPropertyChangeListener(ModelManager.PROP_DATA, evt -> {
            update();
        });
        model.addPropertyChangeListener(ModelManager.PROP_STATE, evt -> {
            update();
        });
    }

    private void update() {
        title.setText(String.valueOf(model.getState()));
        subtitle.setText("Fase " + (model.getState().ordinal() + 1) + "/" + PoEState.values().length);
    }
}
