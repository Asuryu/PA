package pt.isec.pa.apoio_poe.ui.gui;

import javafx.scene.layout.BorderPane;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.CSSManager;

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
        MenuUI menu = new MenuUI(model, this);
        this.setCenter(menu);
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
