package pt.isec.pa.apoio_poe.ui.gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import pt.isec.pa.apoio_poe.model.ModelManager;

/**
 * A classe RootPane é uma classe que representa o painel raiz da aplicação gráfica
 */
public class RootPane extends BorderPane {
    final ModelManager model;

    public RootPane(ModelManager model) {
        this.model = model;

        createViews();
        registerHandlers();
        update();
    }

    /**
     * Método que cria as vistas do painel raiz
     */
    private void createViews() {
        Header header = new Header(model);
        StatusBar statusBar = new StatusBar(model);
        MenuUI menu = new MenuUI(model, header, statusBar);
        StackPane stackPane = new StackPane(
                menu
                //new ConfigUI(model),
                //new ApplicationOptUI(model),
                //new PropAttributionUI(model),
                //new OriAttributionUI(model),
                //new ReviewUI(model)
        );
        stackPane.setStyle("-fx-background-color: #9297C4;");
        this.setCenter(stackPane);
        this.setTop(header);
        this.setBottom(statusBar);
    }

    /**
     * Método que regista os handlers do painel raiz
     */
    private void registerHandlers() { }

    /**
     * Método que atualiza o painel raiz
     */
    private void update() { }

}
