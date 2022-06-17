package pt.isec.pa.apoio_poe.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.model.data.PoEAluno;
import pt.isec.pa.apoio_poe.model.data.PoEProposta;
import pt.isec.pa.apoio_poe.model.fsm.PoEState;

import javax.swing.*;

/**
 * A classe ReviewUI é uma classe que representa a interface gráfica
 * da fase de consulta
 */
public class ReviewUI extends BorderPane {
    final ModelManager model;
    Button btnPrev, listStudentsWithProps, listStudentsWithoutProps, exportToCSV, availableProps, takenProps, statistics;
    ScrollPane scrollPane;
    VBox content;
    VBox submenuStatistics;
    VBox mainBtns;
    GridPane graphsGrid;
    PieChart chart = new PieChart();
    Button btnBack;

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
        submenuStatistics = new VBox();
        graphsGrid = new GridPane();

        createViews();
        registerHandlers();
        update();
    }

    /**
     * Método que cria as vistas da interface gráfica
     */
    private void createViews() {
        ActionButtons buttons = new ActionButtons(model);
        btnBack = new Button("Voltar");

        mainBtns = new VBox(listStudentsWithProps, listStudentsWithoutProps, exportToCSV, availableProps, takenProps, statistics);
        submenuStatistics = new VBox(
                new Button("Distribuição por ramos"),
                new Button("Propostas"),
                new Button("Empresas com mais estágios"),
                new Button("Docentes com mais orientações"),
                btnBack
        );
        for (Node btn : submenuStatistics.getChildren()) {
            btn.setStyle("""
                            -fx-background-color: #FFFFFF;
                            -fx-height: 22px;
                            -fx-pref-width: 220px;
                            -fx-text-fill: #111111;
                            -fx-font-size: 12px;
                            -fx-border-radius: 5px;
                            -fx-cursor: hand;
                            -fx-text-align: left;
                            """);
        }
        submenuStatistics.setSpacing(5);
        mainBtns.setSpacing(5);

        for (Node btn : mainBtns.getChildren()) {
            btn.setStyle("""
                            -fx-background-color: #FFFFFF;
                            -fx-height: 22px;
                            -fx-pref-width: 220px;
                            -fx-text-fill: #111111;
                            -fx-font-size: 12px;
                            -fx-border-radius: 5px;
                            -fx-cursor: hand;
                            -fx-text-align: left;
                            """);
        }

        content.setSpacing(5);
        content.setPadding(new Insets(0, 3, 0, 0));
        content.setStyle("-fx-background: #212121; -fx-border-color: #212121;");
        scrollPane.setContent(content);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setStyle("-fx-background: #212121; -fx-border-color: #212121;");

        VBox stats = new VBox();


        this.setLeft(mainBtns);
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
        model.addPropertyChangeListener(ModelManager.PROP_DATA, evt -> {
            update();
        });
        listStudentsWithProps.setOnAction(evt -> {
            content.getChildren().clear();
            for(PoEAluno aluno : model.getAlunosWithProps()){
                System.out.println(aluno);
                content.getChildren().add(new StudentCard(aluno));
            }
            this.setRight(scrollPane);
        });
        listStudentsWithoutProps.setOnAction(evt -> {
            content.getChildren().clear();
            for(PoEAluno aluno : model.getAlunosWithoutProps()){
                content.getChildren().add(new StudentCard(aluno));
            }
            this.setRight(scrollPane);
        });
        availableProps.setOnAction(evt -> {
            content.getChildren().clear();
            for(PoEProposta proposta : model.getPropostas()){
                if(proposta.getNrAlunoAtribuido() == null) {
                    content.getChildren().add(new PropCard(proposta));
                }
            }
            this.setRight(scrollPane);
        });
        takenProps.setOnAction(evt -> {
            content.getChildren().clear();
            for(PoEProposta proposta : model.getPropostas()){
                if(proposta.getNrAlunoAtribuido() != null) {
                    content.getChildren().add(new PropCard(proposta));
                }
            }
            this.setRight(scrollPane);
        });
        statistics.setOnAction(evt -> {
            this.setLeft(submenuStatistics);
            content.getChildren().clear();
            content.getChildren().add(chart);
            this.setRight(chart);
            //createBarGraph();
        });
        btnBack.setOnAction(evt -> {
            this.setLeft(mainBtns);
            this.setRight(scrollPane);
        });
    }

    /**
     * Método que atualiza a interface gráfica
     */
    private void update() {
        this.setVisible(model != null && model.getState() == PoEState.REVIEW);
        int nrPropostasAtribuidas = (int) model.getPropostas().stream().filter(p -> p.getNrAlunoAtribuido() != null).count();
        int nrPropostasNaoAtribuidas = (int) model.getPropostas().stream().filter(p -> p.getNrAlunoAtribuido() != null).count();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Atribuídas", nrPropostasAtribuidas),
                new PieChart.Data("Não-Atribuídas", nrPropostasNaoAtribuidas)
        );
        chart = new PieChart(pieChartData);
        chart.setTitle("Propostas");
        chart.setPrefSize(380, 380);
        chart.setMaxSize(380, 380);
        chart.setMinSize(380, 380);
    }

    private void createBarGraph(){
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Empresas");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Alunos");
        BarChart bc = new BarChart(xAxis,yAxis);
        bc.setTitle("Top 5 - Empresas com mais estágios");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Número de alunos");
        series1.getData().add(new XYChart.Data("Empresa X", 10));
        series1.getData().add(new XYChart.Data("Empresa Y", 20));
        series1.getData().add(new XYChart.Data("Empresa Z", 5));
        series1.getData().add(new XYChart.Data("Empresa W", 1));
        series1.getData().add(new XYChart.Data("Empresa K", 12));

        bc.getData().add(series1);

        this.setRight(bc);

    }

}
