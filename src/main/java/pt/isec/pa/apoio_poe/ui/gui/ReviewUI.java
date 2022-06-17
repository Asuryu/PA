package pt.isec.pa.apoio_poe.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.model.data.PoEAluno;
import pt.isec.pa.apoio_poe.model.data.PoEOrientador;
import pt.isec.pa.apoio_poe.model.data.PoEProposta;
import pt.isec.pa.apoio_poe.model.fsm.PoEState;
import pt.isec.pa.apoio_poe.ui.gui.resources.CSSManager;

import java.io.File;
import java.util.*;

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
    Button btnBack, distRamosBtn, propsBtn, topEmpresasBtn, topDocentesBtn;

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

        distRamosBtn = new Button("Distribuição por ramos");
        propsBtn = new Button("Propostas");
        topEmpresasBtn = new Button("Empresas com mais estágios");
        topDocentesBtn = new Button("Docentes com mais orientações");

        createViews();
        registerHandlers();
        update();
    }

    /**
     * Método que cria as vistas da interface gráfica
     */
    private void createViews() {
        CSSManager.applyCSS(this, "style.css");
        ActionButtons buttons = new ActionButtons(model);
        btnBack = new Button("Voltar");

        mainBtns = new VBox(listStudentsWithProps, listStudentsWithoutProps, exportToCSV, availableProps, takenProps, statistics);
        submenuStatistics = new VBox(
                distRamosBtn,
                propsBtn,
                topEmpresasBtn,
                topDocentesBtn,
                btnBack
        );
        for (Node btn : submenuStatistics.getChildren()) {
            btn.setId("mainMenuBtn");
        }
        submenuStatistics.setSpacing(5);
        mainBtns.setSpacing(5);

        for (Node btn : mainBtns.getChildren()) {
            btn.setId("mainMenuBtn");
        }

        content.setSpacing(5);
        content.setPadding(new Insets(0, 3, 0, 0));
        content.setStyle("-fx-background: #212121; -fx-border-color: #212121;");
        scrollPane.setContent(content);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setStyle("-fx-background: #212121; -fx-border-color: #212121;");

        VBox stats = new VBox();

        Header header = new Header(model);
        StatusBar statusBar = new StatusBar(model);
        mainBtns.setPadding(new Insets(0, 0, 0, 10));
        submenuStatistics.setPadding(new Insets(0, 0, 0, 10));

        VBox bottomBox = new VBox(buttons, statusBar);
        bottomBox.setSpacing(10);
        bottomBox.setPadding(new Insets(10, 10, 0, 10));

        this.setTop(header);
        this.setLeft(mainBtns);
        this.setRight(scrollPane);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setBottom(bottomBox);
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
        exportToCSV.setOnAction(evt -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                model.saveAlunosCSV(file.getAbsolutePath());
            }
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
            content.getChildren().clear();
            this.setLeft(submenuStatistics);
        });
        distRamosBtn.setOnAction(evt -> {
            content.getChildren().clear();
            createPieGraphRamos();
            content.getChildren().add(chart);
            this.setRight(chart);
        });
        propsBtn.setOnAction(evt -> {
            content.getChildren().clear();
            createPieGraphPropostas();
            content.getChildren().add(chart);
            this.setRight(chart);
        });
        topEmpresasBtn.setOnAction(evt -> {
            content.getChildren().clear();
            BarChart bc = createBarGraphEmpresas();
            content.getChildren().add(bc);
            this.setRight(bc);
        });
        topDocentesBtn.setOnAction(evt -> {
            content.getChildren().clear();
            BarChart bc = createBarGraphDocentes();
            content.getChildren().add(bc);
            this.setRight(bc);
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
    }

    private void createPieGraphPropostas(){
        int nrPropostasAtribuidas = (int) model.getPropostas().stream().filter(p -> p.getNrAlunoAtribuido() != null).count();
        int nrPropostasNaoAtribuidas = (int) model.getPropostas().stream().filter(p -> p.getNrAlunoAtribuido() == null).count();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Atribuídas", nrPropostasAtribuidas),
                new PieChart.Data("Não-Atribuídas", nrPropostasNaoAtribuidas)
        );
        chart = new PieChart(pieChartData);
        chart.setTitle("Propostas");
        chart.setPrefSize(380, 200);
        chart.setMaxSize(380, 200);
        chart.setMinSize(380, 200);
    }

    private void createPieGraphRamos(){
        int nrPropostasSI = model.getPropostasByRamo("SI").size();
        int nrPropostasDA = model.getPropostasByRamo("DA").size();
        int nrPropostasRAS = model.getPropostasByRamo("RAS").size();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("SI", nrPropostasSI),
                new PieChart.Data("DA", nrPropostasDA),
                new PieChart.Data("RAS", nrPropostasRAS)
        );
        chart = new PieChart(pieChartData);
        chart.setTitle("Propostas por ramos");
        chart.setPrefSize(380, 200);
        chart.setMaxSize(380, 200);
        chart.setMinSize(380, 200);
    }

    private BarChart createBarGraphEmpresas(){
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Empresas");
        xAxis.setTickLabelFill(Color.WHITE);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Nrº de alunos");
        yAxis.setTickLabelFill(Color.WHITE);
        BarChart bc = new BarChart(xAxis,yAxis);
        bc.setTitle("Top 5 - Empresas com mais estágios");

        XYChart.Series series = new XYChart.Series();
        series.setName("Nrº de alunos");

        // get every empresa
        List<String> list = new ArrayList<>();

        for(PoEProposta proposta : model.getPropostas()){
            if(proposta.getEntidade() != null){
                list.add(proposta.getEntidade());
            }
        }

        Set<String> unique = new HashSet<String>(list);

        if(unique.size() <= 5){
            for(String key : unique){
                series.getData().add(new XYChart.Data(key, Collections.frequency(list, key)));
            }
        } else {
            for(int i = 0; i < 5; i++){
                series.getData().add(new XYChart.Data(unique.toArray()[i].toString(), Collections.frequency(list, unique.toArray()[i].toString())));
            }
        }


        bc.setStyle("""
                        -fx-background-color: #212121;
                        -fx-fill: #FFFFFF;
                    """);
        // set bar title font to white

        bc.getData().add(series);
        return bc;

    }

    private BarChart createBarGraphDocentes(){
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Docentes");
        xAxis.setTickLabelFill(Color.WHITE);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Alunos");
        yAxis.setTickLabelFill(Color.WHITE);
        BarChart bc = new BarChart(xAxis,yAxis);
        bc.setTitle("Top 5 - Docentes com mais orientações");

        XYChart.Series series = new XYChart.Series();
        series.setName("Número de orientações");
        ArrayList<PoEOrientador> orientadores = model.getOrientadores();
        Collections.sort(orientadores);

        if(orientadores.size() <= 5){
            for (PoEOrientador orientadore : orientadores) {
                series.getData().add(new XYChart.Data(orientadore.getDocente().getNome(), orientadore.getPropostas().size()));
            }
        } else {
            for(int i = 0; i < 5; i++){
                series.getData().add(new XYChart.Data(orientadores.get(i).getDocente().getNome(), orientadores.get(i).getPropostas().size()));
            }
        }

        bc.setStyle("""
                        -fx-background-color: #212121;
                        -fx-fill: #FFFFFF;
                    """);

        bc.getData().add(series);
        return bc;

    }

}
