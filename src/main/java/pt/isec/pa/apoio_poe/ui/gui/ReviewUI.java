package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.model.fsm.PoEState;

public class ReviewUI extends BorderPane {
    final ModelManager model;
    Button btnPrev;

    public ReviewUI(ModelManager model) {
        this.model = model;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        ActionButtons buttons = new ActionButtons(model);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setBottom(buttons);
    }

    private void registerHandlers() {
        model.addPropertyChangeListener(ModelManager.PROP_STATE, evt -> {
            update();
        });
    }

    private void update() {
        this.setVisible(model != null && model.getState() == PoEState.REVIEW);
    }
}
