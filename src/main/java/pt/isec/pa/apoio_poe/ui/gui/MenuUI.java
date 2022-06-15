package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import pt.isec.pa.apoio_poe.model.ModelManager;

/**
 * A classe MenuUI é uma classe que representa a interface gráfica
 * do menu inicial da aplicação
 */

public class MenuUI extends BorderPane {
    final StatusBar statusBar;
    final Header header;
    final ModelManager model;
    Button btnStart;
    Text title;

    public MenuUI(ModelManager model, Header header, StatusBar statusBar) {
        this.statusBar = statusBar;
        this.header = header;
        this.model = model;
        //this.title.setText("Menu");
        createViews();
        registerHandlers();
        update();
    }

    /**
     * Método que cria as vistas da interface gráfica
     */
    private void createViews() {
        statusBar.setVisible(false);
        header.setVisible(false);
        //ActionButtons buttons = new ActionButtons(model);
        //this.setPadding(new Insets(10, 10, 10, 10));
        //this.setBottom(buttons);
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
    }
}
