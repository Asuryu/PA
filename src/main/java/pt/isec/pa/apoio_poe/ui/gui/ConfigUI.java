package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.model.fsm.PoEState;

/**
 * A classe ConfigUI é uma classe que representa a interface gráfica
 * da fase de configuração
 */
public class ConfigUI extends BorderPane {
    final ModelManager model;
    Button btnNext, studentsMng, professorsMng, propsMng;
    ScrollPane scrollPane;
    VBox content;

    public ConfigUI(ModelManager model) {
        this.model = model;

        studentsMng = new Button("Gestão de Alunos");
        professorsMng = new Button("Gestão de Docentes");
        propsMng = new Button("Gestão de Propostas de Estágio ou Projeto");
        scrollPane = new ScrollPane();
        content = new VBox();

        createViews();
        registerHandlers();
        update();
    }

    /**
     * Método que cria as vistas da interface gráfica
     */
    private void createViews() {
        ActionButtons buttons = new ActionButtons(model);

        VBox box = new VBox(studentsMng, professorsMng, propsMng);
        box.setPadding(new Insets(10, 10, 10, 10));
        box.setSpacing(10);
        content.setSpacing(5);
        content.setPadding(new Insets(10, 10, 0, 10));
        scrollPane.setContent(content);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setStyle("-fx-background: #212121; -fx-border-color: #212121;");

        Header header = new Header(model);
        StatusBar statusBar = new StatusBar(model);

        VBox bottomBox = new VBox(buttons, statusBar);
        bottomBox.setSpacing(10);
        bottomBox.setPadding(new Insets(10, 10, 0, 10));

        this.setLeft(box);
        this.setRight(scrollPane);
        this.setPadding(new Insets(10, 10, 10, 10));
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
        this.setVisible(model != null && model.getState() == PoEState.CONFIG);
    }
}
