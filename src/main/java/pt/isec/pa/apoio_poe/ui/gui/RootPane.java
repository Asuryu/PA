package pt.isec.pa.apoio_poe.ui.gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import pt.isec.pa.apoio_poe.model.ModelManager;

public class RootPane extends BorderPane {
    final ModelManager model;

    public RootPane(ModelManager model) {
        this.model = model;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        StackPane stackPane = new StackPane(
                new ConfigUI(model),
                new ApplicationOptUI(model),
                new PropAttributionUI(model),
                new OriAttributionUI(model),
                new ReviewUI(model)
        );
        stackPane.setStyle("-fx-background-color: #9297C4;");
        this.setCenter(stackPane);
        this.setTop(new Header(model));
        this.setBottom(new StatusBar(model));
    }

    private void registerHandlers() { }

    private void update() { }

}
