package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.model.data.PoEAluno;
import pt.isec.pa.apoio_poe.model.fsm.PoEState;

/**
 * A classe ReviewUI é uma classe que representa a interface gráfica
 * da fase de consulta
 */
public class ReviewUI extends BorderPane {
    final ModelManager model;
    Button btnPrev;
    Button listStudensWithProps;
    Button listStudensWithoutProps;
    Button exportToCSV;
    Button availableProps;
    Button takenProps;
    Button statistics;
    ScrollPane scrollPane;
    VBox content;

    public ReviewUI(ModelManager model) {
        this.model = model;

        listStudensWithProps = new Button("Alunos com proposta atribuída");
        listStudensWithoutProps = new Button("Alunos sem proposta atribuída");
        exportToCSV = new Button("Exportar alunos para ficheiro CSV");
        availableProps = new Button("Propostas disponíveis");
        takenProps = new Button("Propostas atribuídas");
        statistics = new Button("Estatísticas");
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

        VBox vbox = new VBox(listStudensWithProps, listStudensWithoutProps, exportToCSV, availableProps, takenProps, statistics);
        vbox.setSpacing(5);
        content.setSpacing(5);
        content.setStyle("-fx-background: #9297C4; -fx-border-color: #9297C4;");
        scrollPane.setContent(content);
        scrollPane.setPrefSize(250, 500);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        // transparent background for scrollpane
        scrollPane.setStyle("-fx-background: #9297C4; -fx-border-color: #9297C4;");
        this.setLeft(vbox);
        this.setRight(scrollPane);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setBottom(buttons);
    }

    /**
     * Método que regista os handlers da interface gráfica
     */
    private void registerHandlers() {
        model.addPropertyChangeListener(ModelManager.PROP_STATE, evt -> {
            update();
        });
        model.addAluno(new PoEAluno("Tomás", 2020145845L, "a2020143845@isec.pt", "LEI", "DA", 0.8, true));
        listStudensWithProps.setOnAction(evt -> {
            for(PoEAluno aluno : model.getAlunosWithProps()){
                content.getChildren().add(new StudentCard(aluno));
                System.out.println(aluno.getNome());
            }
        });
    }

    /**
     * Método que atualiza a interface gráfica
     */
    private void update() {
        this.setVisible(model != null && model.getState() == PoEState.REVIEW);
    }
}
