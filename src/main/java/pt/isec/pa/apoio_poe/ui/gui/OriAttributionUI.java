package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.model.fsm.PoEState;

/**
 * A classe OriAttributionUI é uma classe que representa a interface gráfica
 * da fase de atribuição de orientadores
 */
public class OriAttributionUI extends BorderPane {
    final ModelManager model;
    Button btnNext, btnPrev;

    public OriAttributionUI(ModelManager model) {
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
        this.setPadding(new Insets(10, 10, 10, 10));
        VBox bottomBox = new VBox(buttons);
        this.setBottom(buttons);
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
        this.setVisible(model != null && model.getState() == PoEState.ORI_ATTRIBUTION);
    }
}
