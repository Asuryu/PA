package pt.isec.pa.apoio_poe.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.model.data.PoEAluno;
import pt.isec.pa.apoio_poe.model.data.PoECandidatura;
import pt.isec.pa.apoio_poe.model.data.PoEProposta;
import pt.isec.pa.apoio_poe.model.fsm.PoEState;
import pt.isec.pa.apoio_poe.ui.gui.resources.CSSManager;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A classe ApplicationOptUI é uma classe que representa a interface gráfica
 * da fase de configuração de candidaturas
 */
public class ApplicationOptUI extends BorderPane {
    final ModelManager model;
    Button btnNext, btnPrev;
    Button importApplications, exportApplications, viewApplications, editApplications, removeApplications;
    Button studentSelfProposalList, studentRegistedApplicationsList, studentNoApplicationList;
    Button selfPropList, teachersPropList, propWithApplication, propWithoutApplication;
    Button allApplications, studentApplication, propIdApplication;
    Button applicationsBtn;
    VBox subMenu, applications, studentList, propList, content, leftBox, viewApplicationsBox;
    HBox subMenusBox;
    ScrollPane scrollPane;
    ComboBox comboBox;
    Text fileText, info;
    TextField applicationsTextField, studentsListTextField, propsListTextField;
    String applicationsText, studentsListText, propsListText;
    int applicationsMethod, studentsMethod, propsMethod;

    public ApplicationOptUI(ModelManager model) {
        this.model = model;

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

        // Gestão de candidaturas

        importApplications = new Button("Importar candidaturas de um ficheiro CSV");
        importApplications.setId("MenuButton");
        exportApplications = new Button("Exportar candidaturas para um ficheiro CSV");
        exportApplications.setId("MenuButton");
        viewApplications = new Button("Consultar candidaturas");
        viewApplications.setId("MenuButton");
        editApplications = new Button("Editar candidatura");
        editApplications.setId("MenuButton");
        removeApplications = new Button("Remover candidatura");
        removeApplications.setId("MenuButton");

        // Botões de consulta de candidaturas

        allApplications = new Button("Todas");
        allApplications.setId("subMenuButton");
        studentApplication = new Button("Aluno");
        studentApplication.setId("subMenuButton");
        propIdApplication = new Button("Proposta");
        propIdApplication.setId("subMenuButton");

        // Lista de alunos

        studentSelfProposalList = new Button("Auto-Propostas");
        studentSelfProposalList.setId("MenuButton");
        studentRegistedApplicationsList = new Button("Candidaturas registadas");
        studentRegistedApplicationsList.setId("MenuButton");
        studentNoApplicationList = new Button("Sem candidaturas");
        studentNoApplicationList.setId("MenuButton");

        // Lista de propostas

        selfPropList = new Button("Auto-Propostas do aluno");
        selfPropList.setId("MenuButton");
        teachersPropList = new Button("Propostas de docentes");
        teachersPropList.setId("MenuButton");
        propWithApplication = new Button("Propostas com candidatura");
        propWithApplication.setId("MenuButton");
        propWithoutApplication = new Button("Propostas sem candidatura");
        propWithoutApplication.setId("MenuButton");

        subMenu = new VBox();
        scrollPane = new ScrollPane();

        ObservableList<String> options = FXCollections.observableArrayList(
                "Gestão de Candidaturas",
                "Lista de Alunos",
                "Lista de Propostas"
        );

        applications = new VBox();
        applications.getChildren().addAll(importApplications, exportApplications, viewApplications, editApplications, removeApplications);
        applications.setSpacing(5);
        applications.setPadding(new Insets(5, 5, 5, 0));

        studentList = new VBox();
        studentList.getChildren().addAll(studentSelfProposalList, studentRegistedApplicationsList, studentNoApplicationList);
        studentList.setSpacing(5);
        studentList.setPadding(new Insets(5, 5, 5, 0));

        propList = new VBox();
        propList.getChildren().addAll(selfPropList, teachersPropList, propWithApplication, propWithoutApplication);
        propList.setSpacing(5);
        propList.setPadding(new Insets(5, 5, 5, 0));

        comboBox = new ComboBox();
        comboBox.setItems(options);
        comboBox.setId("comboBox");
        comboBox.getSelectionModel().selectFirst();

        Text text = new Text("Selecione uma opção");
        text.setFill(javafx.scene.paint.Color.WHITE);
        text.setId("defaultText");

        content = new VBox();
        content.setSpacing(5);
        content.setPadding(new Insets(10, 10, 10, 10));
        content.setStyle("-fx-background: #212121; -fx-border-color: #212121;");
        content.setPrefWidth(300);
        content.setMaxWidth(300);
        content.setMinWidth(300);

        scrollPane.setContent(content);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setStyle("-fx-background: #212121; -fx-border-color: #212121;");
        scrollPane.setPrefWidth(310);
        scrollPane.setMaxWidth(310);
        scrollPane.setMinWidth(310);


        subMenusBox = new HBox(subMenu);
        subMenusBox.setSpacing(10);
        subMenusBox.setPadding(new Insets(10, 10, 10, 0));
        subMenusBox.setStyle("-fx-background: #212121; -fx-border-color: #212121;");

        leftBox = new VBox(text, comboBox, subMenusBox);
        leftBox.setPadding(new Insets(10, 10, 10, 10));
        leftBox.setSpacing(10);
        leftBox.setAlignment(Pos.TOP_LEFT);

        fileText = new Text();
        info = new Text(); //Texto de informação
        info.setFill(javafx.scene.paint.Color.WHITE);
        info.setId("defaultText");

        applicationsBtn = new Button("Procurar");
        applicationsBtn.setId("subMenuButton");

        applicationsTextField = new TextField();
        applicationsTextField.setPrefWidth(220);
        applicationsTextField.setId("textField");
        applicationsTextField.getText();
        applicationsText = new String();
        applicationsText = applicationsTextField.getText();

        studentsListTextField = new TextField();
        studentsListTextField.setPrefWidth(220);
        studentsListTextField.setId("textField");
        studentsListTextField.getText();
        studentsListText = new String();
        studentsListText = studentsListTextField.getText();

        propsListTextField = new TextField();
        propsListTextField.setPrefWidth(220);
        propsListTextField.setId("textField");
        propsListTextField.getText();
        propsListText = new String();
        propsListText = propsListTextField.getText();

        viewApplicationsBox = new VBox(allApplications, studentApplication, propIdApplication);
        viewApplicationsBox.setSpacing(5);
        viewApplicationsBox.setPadding(new Insets(5, 5, 5, 5));
        viewApplicationsBox.setStyle("-fx-background: #212121; -fx-border-color: #212121;");

        Header header = new Header(model);
        StatusBar statusBar = new StatusBar(model);

        VBox bottomBox = new VBox(buttons, statusBar);
        bottomBox.setSpacing(10);
        bottomBox.setPadding(new Insets(10, 10, 0, 10));

        content.getChildren().clear();
        subMenu.getChildren().clear();
        subMenu.getChildren().add(applications);

        this.setLeft(leftBox);
        this.setRight(content);
        this.setBottom(bottomBox);
        this.setTop(header);
        this.setPadding(new Insets(10, 10, 10, 10));
    }

    /**
     * Método que regista os handlers da interface gráfica
     */
    private void registerHandlers() {
        model.addPropertyChangeListener(ModelManager.PROP_STATE, evt -> {
            update();
        });

        comboBox.setOnAction(e -> {
            subMenusBox.getChildren().remove(viewApplicationsBox);
            switch (comboBox.getValue().toString()){
                case "Gestão de Candidaturas":
                    content.getChildren().clear();
                    subMenu.getChildren().clear();
                    subMenu.getChildren().add(applications);
                    break;
                case "Lista de Alunos":
                    content.getChildren().clear();
                    subMenu.getChildren().clear();
                    subMenu.getChildren().add(studentList);
                    break;
                case "Lista de Propostas":
                    content.getChildren().clear();
                    subMenu.getChildren().clear();
                    subMenu.getChildren().add(propList);
                    break;
            }
        });

        // Menu de gestão de candidaturas

        importApplications.setOnAction(e -> {
            content.getChildren().clear();
            subMenusBox.getChildren().remove(fileText);
            subMenusBox.getChildren().remove(viewApplicationsBox);
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(this.getScene().getWindow());
            if (selectedFile != null && selectedFile.getName().endsWith(".csv")) {
                model.addCandidaturasCSV("csv/" + selectedFile.getName()); //Not working!! Não foi possível abrir o ficheiro
                fileText.setVisible(true);
                fileText.setText("Ficheiro " + selectedFile.getName() + " carregado com sucesso!"); //TODO: Verificar porque é que não aparece a mensagem
                fileText.setFill(javafx.scene.paint.Color.WHITE);
                fileText.setId("defaultText");
                subMenusBox.getChildren().add(info);
                subMenusBox.setAlignment(Pos.CENTER);
                subMenusBox.setSpacing(30);
            }else if(selectedFile != null && !selectedFile.getName().endsWith(".csv")){
                fileText.setVisible(true);
                fileText.setText("Extensão do ficheiro inválida!\nPor favor, selecione um ficheiro .CSV!");
                fileText.setId("defaultText");
                fileText.setFill(javafx.scene.paint.Color.WHITE);
                fileText.setTextAlignment(TextAlignment.CENTER);
                subMenusBox.getChildren().add(info);
                subMenusBox.setAlignment(Pos.CENTER);
                subMenusBox.setSpacing(30);
            }
        });

        exportApplications.setOnAction(e -> {
            content.getChildren().clear();
            subMenusBox.getChildren().remove(fileText);
            subMenusBox.getChildren().remove(viewApplicationsBox);
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                model.saveAlunosCSV(file.getAbsolutePath());
                fileText.setVisible(true);
                fileText.setText("Ficheiro " + file.getName() + " exportado com sucesso!");
                fileText.setId("defaultText");
                fileText.setFill(javafx.scene.paint.Color.WHITE);
                subMenusBox.getChildren().add(info);
                subMenusBox.setAlignment(Pos.CENTER);
                subMenusBox.setSpacing(30);
            }
        });

        viewApplications.setOnAction(e -> {
            content.getChildren().clear();
            viewApplications.setStyle("-fx-background: #af2821; -fx-border-color: #ffffff;");
            subMenusBox.getChildren().remove(fileText);
            if(!subMenusBox.getChildren().contains(viewApplicationsBox))
                subMenusBox.getChildren().add(viewApplicationsBox);
            subMenusBox.setAlignment(Pos.CENTER);
        });

        allApplications.setOnAction(e -> {
            content.getChildren().clear();
            info.setText("Não existem candidaturas registadas!");
            info.setId("defaultText");
            if(model.getCandidaturas().size() == 0) {
                content.getChildren().add(info);
                content.setAlignment(Pos.CENTER);
            }else{
                for(PoECandidatura candidatura : model.getCandidaturas()) {
                    String text;
                    if(model.getAlunoByID(candidatura.getNrEstudante()) != null){
                        text = model.getAlunoByID(candidatura.getNrEstudante()).getNome();
                    } else {
                        text = "Aluno nrº " + candidatura.getNrEstudante();
                    }
                    content.getChildren().add(
                            new Card("Candidatura",
                                    text,
                                    candidatura.getPreferencias().toString()
                            )
                    );
                }
                this.setRight(scrollPane);
            }
        });

        studentApplication.setOnAction(e -> {
            applicationsMethod = 1;
            content.getChildren().clear();
            applicationsTextField.clear();
            applicationsTextField.setPromptText("Nome do aluno:");
            content.getChildren().addAll(applicationsTextField, applicationsBtn);
        });

        propIdApplication.setOnAction(e -> {
            applicationsMethod = 2;
            content.getChildren().clear();
            applicationsTextField.clear();
            applicationsTextField.setPromptText("ID da proposta:");
            content.getChildren().addAll(applicationsTextField, applicationsBtn);
        });

        editApplications.setOnAction(e -> {
            subMenusBox.getChildren().remove(viewApplicationsBox);
            applicationsMethod = 3;
            editApplications.setStyle("-fx-background: #af2821; -fx-border-color: #ffffff;");
            applicationsTextField.clear();
            applicationsTextField.setPromptText("Nome do aluno:");
            content.getChildren().clear();
            content.getChildren().addAll(applicationsTextField, applicationsBtn);
            content.setAlignment(Pos.CENTER);
        });

        removeApplications.setOnAction(e -> {
            subMenusBox.getChildren().remove(viewApplicationsBox);
            applicationsMethod = 4;
            removeApplications.setStyle("-fx-background: #af2821; -fx-border-color: #ffffff;");
            applicationsTextField.clear();
            applicationsTextField.setPromptText("ID do aluno:");
            content.getChildren().clear();
            content.getChildren().addAll(applicationsTextField, applicationsBtn);
            content.setAlignment(Pos.CENTER);
        });

        applicationsBtn.setOnAction(e -> {
            ArrayList<PoECandidatura> candidaturas;
            switch (applicationsMethod) {
                case 1:
                    content.getChildren().clear();
                    candidaturas = model.getCandidaturaByAluno(Long.valueOf(applicationsTextField.getText()));
                    if(candidaturas.size() == 0) {
                        info.setText("Não foram encontrados alunos com esse ID");
                        if(!subMenusBox.getChildren().contains(info))
                            subMenusBox.getChildren().add(info);
                        subMenusBox.setAlignment(Pos.CENTER);
                        subMenusBox.setSpacing(30);
                    }else{
                        for(PoECandidatura candidatura : candidaturas) {
                            String text;
                            if(model.getAlunoByID(candidatura.getNrEstudante()) != null){
                                text = model.getAlunoByID(candidatura.getNrEstudante()).getNome();
                            } else {
                                text = "Aluno nrº " + candidatura.getNrEstudante();
                            }
                            content.getChildren().add(
                                    new Card("Candidatura",
                                            text,
                                            candidatura.getPreferencias().toString()
                                    )
                            );
                        }
                    }
                    this.setRight(scrollPane);
                    break;
                case 2:
                    content.getChildren().clear();
                    candidaturas = model.getCandidaturasByProposta(applicationsTextField.getText());
                    if(candidaturas.size() == 0) {
                        info.setText("Não foi encontrado nenhum aluno com esse ID");
                        if(!subMenusBox.getChildren().contains(info))
                            subMenusBox.getChildren().add(info);
                        subMenusBox.setAlignment(Pos.CENTER);
                        subMenusBox.setSpacing(30);
                    }else{
                        for(PoECandidatura candidatura : candidaturas) {
                            String text;
                            if(model.getAlunoByID(candidatura.getNrEstudante()) != null){
                                text = model.getAlunoByID(candidatura.getNrEstudante()).getNome();
                            } else {
                                text = "Aluno nrº " + candidatura.getNrEstudante();
                            }
                            content.getChildren().add(
                                    new Card("Candidatura",
                                            text,
                                            candidatura.getPreferencias().toString()
                                    )
                            );
                        }
                    }
                    this.setRight(scrollPane);
                    break;
                case 3:
                    break;
                case 4:
                    Long nrAluno = Long.parseLong(applicationsTextField.getText());
                    ArrayList<PoECandidatura> candidatura = model.getCandidaturaByAluno(nrAluno);
                    if(candidatura.size() == 0) {
                        info.setText("Não foi encontrada nenhuma\n candidatura para esse aluno");
                        if(!subMenusBox.getChildren().contains(info))
                            subMenusBox.getChildren().add(info);
                        subMenusBox.setAlignment(Pos.CENTER);
                        subMenusBox.setSpacing(30);
                    } else {
                        if(model.removeCandidatura(candidatura.get(0))) {
                            content.getChildren().clear();
                            info.setText("Aluno removido com sucesso");
                            if(!subMenusBox.getChildren().contains(info))
                                subMenusBox.getChildren().add(info);
                            subMenusBox.setAlignment(Pos.CENTER);
                            subMenusBox.setSpacing(30);
                        } else {
                            content.getChildren().clear();
                            info.setText("Não foi possível remover o aluno");
                            if(!subMenusBox.getChildren().contains(info))
                                subMenusBox.getChildren().add(info);
                            subMenusBox.setAlignment(Pos.CENTER);
                            subMenusBox.setSpacing(30);
                        }
                    }
                    this.setRight(scrollPane);
                    break;
                default:
                    info.setText("Proposta não encontrado!");
                    if(!subMenusBox.getChildren().contains(info))
                        subMenusBox.getChildren().add(info);
                    subMenusBox.setAlignment(Pos.CENTER);
                    subMenusBox.setSpacing(30);
                    break;
            }
        });

        // Menu de lista de alunos

        studentSelfProposalList.setOnAction(e -> {
            content.getChildren().clear();
            ArrayList<PoEProposta> propostas = model.getPropostasByType("T3");
            if(propostas.size() == 0) {
                info.setText("Alunos com autoproposta (" + model.getPropostas().size() + ")");
                if(!subMenusBox.getChildren().contains(info))
                    subMenusBox.getChildren().add(info);
                subMenusBox.setAlignment(Pos.CENTER);
                subMenusBox.setSpacing(30);
            }else{
                for(PoEProposta proposta : model.getPropostas()) {
                    PoEAluno aluno = model.getAlunoByID(propostas.get(0).getNrAlunoAtribuido());
                    content.getChildren().add(
                            new Card(aluno.getNome(),
                                    aluno.getNrEstudante() + "",
                                    proposta.getId()
                            )
                    );
                }
                this.setRight(scrollPane);
            }
        });

        studentRegistedApplicationsList.setOnAction(e -> {
            content.getChildren().clear();
            ArrayList<PoECandidatura> candidaturas = model.getCandidaturas();
            if(candidaturas.size() == 0) {
                info.setText("Alunos com candidatura registada (" + model.getPropostas().size() + ")");
                if(!subMenusBox.getChildren().contains(info))
                    subMenusBox.getChildren().add(info);
                subMenusBox.setAlignment(Pos.CENTER);
                subMenusBox.setSpacing(30);
            }else{
                for(PoECandidatura candidatura : candidaturas) {
                    PoEAluno aluno = model.getAlunoByID(candidatura.getNrEstudante());
                    content.getChildren().add(
                            new Card(aluno.getNome(),
                                    aluno.getNrEstudante() + "",
                                    aluno.getCandidatura().getPreferencias().toString()
                            )
                    );
                }
                this.setRight(scrollPane);
            }
        });

        studentNoApplicationList.setOnAction(e -> {
            content.getChildren().clear();
            ArrayList<PoEAluno> alunos = model.getAlunos();
            ArrayList<PoEAluno> alunosSemCandidatura = new ArrayList<>();
            if(alunos.size() == 0) {
                info.setText("Alunos sem autoproposta (" + model.getPropostas().size() + ")");
                if(!subMenusBox.getChildren().contains(info))
                    subMenusBox.getChildren().add(info);
                subMenusBox.setAlignment(Pos.CENTER);
                subMenusBox.setSpacing(30);
            }else{
                for(PoEAluno aluno : alunos){
                    if(aluno.getCandidatura() == null) {
                        alunosSemCandidatura.add(aluno);
                    }
                }
                for(PoEAluno aluno : alunosSemCandidatura) {
                    content.getChildren().add(
                            new Card(aluno.getNome(),
                                    aluno.getNrEstudante() + "",
                                    "Sem candidatura registada"
                            )
                    );
                }
                this.setRight(scrollPane);
            }
        });

        // Menu de lista de propostas

        selfPropList.setOnAction(e -> {
            ArrayList<PoEProposta> propostas;
            content.getChildren().clear();
            propostas = model.getPropostasByType("T3");
            if(propostas.size() == 0) {
                info.setText("Não foram encontradas autopropostas");
                if(!subMenusBox.getChildren().contains(info))
                    subMenusBox.getChildren().add(info);
                subMenusBox.setAlignment(Pos.CENTER);
                subMenusBox.setSpacing(30);
            }else{
                for(PoEProposta proposta : propostas) {
                    content.getChildren().add(
                            new Card("Autoproposta",
                                    proposta.getTitulo(),
                                    proposta.getId()
                            )
                    );
                }
            }
        });

        teachersPropList.setOnAction(e -> {
            ArrayList<PoEProposta> propostas;
            content.getChildren().clear();
            propostas = model.getPropostasByType("T2");
            if(propostas.size() == 0) {
                info.setText("Não foram encontradas \npropostas de docentes");
                if(!subMenusBox.getChildren().contains(info))
                    subMenusBox.getChildren().add(info);
                subMenusBox.setAlignment(Pos.CENTER);
                subMenusBox.setSpacing(30);
            }else{
                for(PoEProposta proposta : propostas) {
                    content.getChildren().add(
                            new Card("Proposta de docente",
                                    proposta.getTitulo(),
                                    proposta.getId()
                            )
                    );
                }
            }
        });

        propWithApplication.setOnAction(e -> {
            content.getChildren().clear();
            ArrayList<PoEProposta> propostas = model.getPropostas();
            ArrayList<PoEProposta> propostasComCandidatura = new ArrayList<>();
            for(PoEProposta proposta : propostas) {
                if(proposta.getCandidaturas().size() > 0) {
                    propostasComCandidatura.add(proposta);
                }
            }
            if(propostas.size() == 0) {
                info.setText("Não foram encontradas propostas");
                if(!subMenusBox.getChildren().contains(info))
                    subMenusBox.getChildren().add(info);
                subMenusBox.setAlignment(Pos.CENTER);
                subMenusBox.setSpacing(30);
            }else{
                for(PoEProposta proposta : propostasComCandidatura) {
                    content.getChildren().add(
                            new Card("Proposta",
                                    proposta.getTitulo(),
                                    proposta.getId()
                            )
                    );
                }
            }
        });

        propWithoutApplication.setOnAction(e -> {
            content.getChildren().clear();
            ArrayList<PoEProposta> propostas = model.getPropostas();
            ArrayList<PoEProposta> propostasSemCandidatura = new ArrayList<>();
            for(PoEProposta proposta : propostas) {
                if(proposta.getCandidaturas().size() == 0) {
                    propostasSemCandidatura.add(proposta);
                }
            }
            if(propostas.size() == 0) {
                info.setText("Não foram encontradas propostas");
                if(!subMenusBox.getChildren().contains(info))
                    subMenusBox.getChildren().add(info);
                subMenusBox.setAlignment(Pos.CENTER);
                subMenusBox.setSpacing(30);
            }else{
                for(PoEProposta proposta : propostasSemCandidatura) {
                    content.getChildren().add(
                            new Card("Proposta",
                                    proposta.getTitulo(),
                                    proposta.getId()
                            )
                    );
                }
            }
        });
    }

    /**
     * Método que atualiza a interface gráfica
     */
    private void update() {
        this.setVisible(model != null && model.getState() == PoEState.APPLICATION_OPT);
        if(model.isClosed() && model.getState() == PoEState.APPLICATION_OPT) {
            importApplications.setDisable(true);
            editApplications.setDisable(true);
            removeApplications.setDisable(true);
            subMenusBox.getChildren().remove(fileText);
        }
    }
}
