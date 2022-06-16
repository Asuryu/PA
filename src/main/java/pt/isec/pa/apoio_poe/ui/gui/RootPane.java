package pt.isec.pa.apoio_poe.ui.gui;

import javafx.scene.layout.BorderPane;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.css.CSSManager;

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
        CSSManager.applyCSS(this, "style.css");
        model.load("C:\\Users\\tomas\\Documents\\GitHub\\PA\\sadge.ser");
        MenuUI menu = new MenuUI(model, this);
        StatusBar statusBar = new StatusBar(model);
        this.setCenter(menu);
        this.setBottom(statusBar);
    }

    /**
     * Método que regista os handlers do painel raiz
     */
    private void registerHandlers() {

    }

    /**
     * Método que atualiza o painel raiz
     */
    private void update() { }

}
