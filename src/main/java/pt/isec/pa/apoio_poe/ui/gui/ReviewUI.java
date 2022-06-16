package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.model.data.PoEAluno;
import pt.isec.pa.apoio_poe.model.data.PoEProposta;
import pt.isec.pa.apoio_poe.model.fsm.PoEState;

/**
 * A classe ReviewUI é uma classe que representa a interface gráfica
 * da fase de consulta
 */
public class ReviewUI extends BorderPane {
    final ModelManager model;
    Button btnPrev, listStudentsWithProps, listStudentsWithoutProps, exportToCSV, availableProps, takenProps, statistics;
    ScrollPane scrollPane;
    VBox content;

    public ReviewUI(ModelManager model) {
        this.model = model;

        listStudentsWithProps = new Button("Alunos com proposta atribuída");
        listStudentsWithoutProps = new Button("Alunos sem proposta atribuída");
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

        VBox vbox = new VBox(listStudentsWithProps, listStudentsWithoutProps, exportToCSV, availableProps, takenProps, statistics);
        vbox.setSpacing(5);
        content.setSpacing(5);
        content.setPadding(new Insets(0, 3, 0, 0));
        content.setStyle("-fx-background: #212121; -fx-border-color: #212121;");
        scrollPane.setContent(content);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setStyle("-fx-background: #212121; -fx-border-color: #212121;");

        VBox stats = new VBox();


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
        listStudentsWithProps.setOnAction(evt -> {
            content.getChildren().clear();
            for(PoEAluno aluno : model.getAlunosWithProps()){
                content.getChildren().add(new StudentCard(aluno));
            }
        });
        listStudentsWithoutProps.setOnAction(evt -> {
            content.getChildren().clear();
            for(PoEAluno aluno : model.getAlunosWithoutProps()){
                content.getChildren().add(new StudentCard(aluno));
            }
        });
        availableProps.setOnAction(evt -> {
            content.getChildren().clear();
            for(PoEProposta proposta : model.getPropostas()){
                if(proposta.getNrAlunoAtribuido() == null) {
                    content.getChildren().add(new PropCard(proposta));
                }
            }
        });
        takenProps.setOnAction(evt -> {
            content.getChildren().clear();
            for(PoEProposta proposta : model.getPropostas()){
                if(proposta.getNrAlunoAtribuido() != null) {
                    content.getChildren().add(new PropCard(proposta));
                }
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
