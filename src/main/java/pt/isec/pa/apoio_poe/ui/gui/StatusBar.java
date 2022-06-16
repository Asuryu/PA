package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import pt.isec.pa.apoio_poe.model.ModelManager;

/**
 * A classe StatusBar é uma classe que representa uma barra de estado da aplicação gráfica
 */
public class StatusBar extends HBox {
    final ModelManager model;
    Label lbMsg, lbNumber;

    /**
     * Construtor da classe StatusBar
     * @param model ModelManager da aplicação
     */
    public StatusBar(ModelManager model) {
        this.model = model;

        createViews();
        registerHandlers();
        update();
    }

    /**
     * Método que cria as vistas da barra de estado
     */
    private void createViews() {
        Label lbMsgTitle = new Label("Fase: ");
        lbMsgTitle.setPrefWidth(Integer.MAX_VALUE);
        lbMsgTitle.setAlignment(Pos.CENTER_RIGHT);
        lbMsg = new Label();
        lbMsg.setPrefWidth(Integer.MAX_VALUE);
        lbMsg.setAlignment(Pos.CENTER_LEFT);
        Label lbNumberTitle = new Label("Estado: ");
        lbNumberTitle.setPrefWidth(Integer.MAX_VALUE);
        lbNumberTitle.setAlignment(Pos.CENTER_RIGHT);
        lbNumber = new Label();
        lbNumber.setPrefWidth(Integer.MAX_VALUE);
        lbNumber.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(lbMsgTitle, lbMsg, lbNumberTitle, lbNumber);
        this.setStyle("-fx-background-color: #FFFFFF;");
    }

    /**
     * Método que regista os handlers da barra de estado
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
     * Método que atualiza a barra de estado
     */
    private void update() {
        lbMsg.setText(String.valueOf(model.getState()));
        lbNumber.setText(model.isClosed() ? "FECHADA" : "ABERTA");
    }
}
