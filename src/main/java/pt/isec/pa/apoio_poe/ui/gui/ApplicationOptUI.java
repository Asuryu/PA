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
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.model.fsm.PoEState;
import pt.isec.pa.apoio_poe.ui.gui.resources.CSSManager;

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
    Button applicationsBtn, studentsListBtn, propsListBtn;
    VBox subMenu, applications, studentList, propList, content, leftBox, viewApplicationsBox;
    HBox subMenusBox;
    ScrollPane scrollPane;
    ComboBox comboBox;
    Text fileText, info;
    TextField applicationsTextField, studentsListTextField, propsListTextField;
    String applicationsText, studentsListText, propsListText;


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

        comboBox.setOnAction(e -> {
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
        studentsListBtn = new Button("Procurar");
        studentsListBtn.setId("subMenuButton");
        propsListBtn = new Button("Procurar");
        propsListBtn.setId("subMenuButton");

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

        // Menu de gestão de candidaturas

        importApplications.setOnAction(e -> {
            //model.importApplications();
        });

        exportApplications.setOnAction(e -> {
            //model.exportApplications();
        });

        viewApplications.setOnAction(e -> {
            //content.getChildren().clear();
            //content.getChildren().add(viewApplicationsBox);
        });

        editApplications.setOnAction(e -> {
            //content.getChildren().clear();
            //content.getChildren().add(editApplicationsBox);
        });

        removeApplications.setOnAction(e -> {
            //content.getChildren().clear();
            //content.getChildren().add(removeApplicationsBox);
        });

        applicationsBtn.setOnAction(e -> {
            //content.getChildren().clear();
            //content.getChildren().add(applicationsBox);
        });

        // Menu de lista de alunos

        studentSelfProposalList.setOnAction(e -> {
            //content.getChildren().clear();
            //content.getChildren().add(studentSelfProposalList);
        });

        studentRegistedApplicationsList.setOnAction(e -> {
            //content.getChildren().clear();
            //content.getChildren().add(studentRegistedApplicationsList);
        });

        studentNoApplicationList.setOnAction(e -> {
            //content.getChildren().clear();
            //content.getChildren().add(studentUnregistedApplicationsList);
        });

        studentsListBtn.setOnAction(e -> {
            //content.getChildren().clear();
            //content.getChildren().add(studentsListBox);
        });

        // Menu de lista de propostas

        selfPropList.setOnAction(e -> {
            //content.getChildren().clear();
            //content.getChildren().add(selfPropList);
        });

        teachersPropList.setOnAction(e -> {
            //content.getChildren().clear();
            //content.getChildren().add(teachersPropList);
        });

        propWithApplication.setOnAction(e -> {
            //content.getChildren().clear();
            //content.getChildren().add(propWithApplicationList);
        });

        propWithoutApplication.setOnAction(e -> {
            //content.getChildren().clear();
            //content.getChildren().add(propWithoutApplicationList);
        });

        propsListBtn.setOnAction(e -> {
            //content.getChildren().clear();
            //content.getChildren().add(propsListBox);
        });
    }

    /**
     * Método que atualiza a interface gráfica
     */
    private void update() {
        this.setVisible(model != null && model.getState() == PoEState.APPLICATION_OPT);
    }
}
