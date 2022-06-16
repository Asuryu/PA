package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.model.fsm.PoEState;

/**
 * A classe ApplicationOptUI é uma classe que representa a interface gráfica
 * da fase de configuração de candidaturas
 */
public class ApplicationOptUI extends BorderPane {
    final ModelManager model;
    Button btnNext, btnPrev;

    public ApplicationOptUI(ModelManager model) {
        this.model = model;

        createViews();
        registerHandlers();
        update();
    }

    /**
     * Método que cria as vistas da interface gráfica
     */
    private void createViews() {
        ActionButtons buttons = new ActionButtons(model);
        Header header = new Header(model);
        StatusBar statusBar = new StatusBar(model);
        this.setPadding(new Insets(10, 10, 10, 10));
        VBox bottomBox = new VBox(buttons, statusBar);
        bottomBox.setSpacing(10);
        bottomBox.setPadding(new Insets(10, 10, 0, 10));
        this.setBottom(bottomBox);
        this.setTop(header);
    }

    /**
     * Método que regista os handlers da interface gráfica
     */
    private void registerHandlers() {
        model.addPropertyChangeListener(ModelManager.PROP_STATE, evt -> {
            update();
        });
    }

    /**
     * Método que atualiza a interface gráfica
     */
    private void update() {
        this.setVisible(model != null && model.getState() == PoEState.APPLICATION_OPT);
    }
}
