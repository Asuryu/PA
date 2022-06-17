package pt.isec.pa.apoio_poe.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import pt.isec.pa.apoio_poe.model.fsm.PoEState;
import pt.isec.pa.apoio_poe.ui.gui.resources.CSSManager;

import java.io.File;

/**
 * A classe ConfigUI é uma classe que representa a interface gráfica
 * da fase de configuração
 */
public class ConfigUI extends BorderPane {
    final ModelManager model;
    Button importStudents, exportStudents, viewStudents, editStudent, removeStudent;
    Button importTeachers, exportTeachers, viewTeachers, editTeacher, removeTeacher;
    Button importProps, exportPros, viewProps, editProp, removeProp;
    Button importStudentCSVBtn, exportStudentCSVBtn, importTeacherCSVBtn, exportTeacherCSVBtn, importPropCSVBtn, exportPropCSVBtn;
    Button allStudents, studentName, studentNumber, studentCourse, studentCourseBranch;
    ScrollPane scrollPane;
    VBox content, subMenu, students, teachers, props, leftBox, viewStudentsBox, viewTeachersBox, viewPropsBox;
    HBox subMenusBox;
    TextField importStudentCSV, exportStudentCSV;
    Text fileText;

    public ConfigUI(ModelManager model) {
        this.model = model;
        // Gestão de alunos
        importStudents = new Button("Importar alunos de um ficheiro CSV");
        exportStudents = new Button("Exportar alunos para um ficheiro CSV");
        viewStudents = new Button("Consultar alunos");
        editStudent = new Button("Editar aluno");
        removeStudent = new Button("Remover aluno");

        //Gestão de docentes
        importTeachers = new Button("Importar docentes de um ficheiro CSV");
        exportTeachers = new Button("Exportar docentes para um ficheiro CSV");
        viewTeachers = new Button("Consultar docentes");
        editTeacher = new Button("Editar docente");
        removeTeacher = new Button("Remover docente");

        //Gestão de propostas de estágio ou projeto
        importProps = new Button("Importar propostas de um ficheiro CSV");
        exportPros = new Button("Exportar propostas para um ficheiro CSV");
        viewProps = new Button("Consultar propostas");
        editProp = new Button("Editar proposta");
        removeProp = new Button("Remover proposta");

        subMenu = new VBox();
        scrollPane = new ScrollPane();

        createViews();
        registerHandlers();
        update();
    }

    /**
     * Método que cria as vistas da interface gráfica
     */
    private void createViews() {
        ActionButtons buttons = new ActionButtons(model);

        ObservableList<String> options = FXCollections.observableArrayList(
                "Gestão de Alunos",
                "Gestão de Docentes",
                "Gestão de Propostas de Estágio ou Projeto"
        );
        final ComboBox comboBox = new ComboBox(options);

        comboBox.setOnAction(e -> {
            switch (comboBox.getValue().toString()) {
                case "Gestão de Alunos":
                    subMenusBox.getChildren().remove(fileText);
                    subMenusBox.getChildren().remove(viewStudentsBox);
                    students = new VBox(importStudents, exportStudents, viewStudents, editStudent, removeStudent);
                    for(Node btn : students.getChildren()) {
                        btn.setId("comboBoxButton");
                    }
                    students.setSpacing(5);
                    students.setPadding(new Insets(5, 5, 5, 5));
                    subMenu.getChildren().clear();
                    subMenu.getChildren().add(students);

                    break;
                case "Gestão de Docentes":
                    subMenusBox.getChildren().remove(fileText);
                    subMenusBox.getChildren().remove(viewStudentsBox);
                    teachers = new VBox(importTeachers, exportTeachers, viewTeachers, editTeacher, removeTeacher);
                    for(Node btn : teachers.getChildren()) {
                        btn.setId("comboBoxButton");
                    }
                    teachers.setSpacing(5);
                    teachers.setPadding(new Insets(5, 5, 5, 5));
                    subMenu.getChildren().clear();
                    subMenu.getChildren().add(teachers);
                    break;
                case "Gestão de Propostas de Estágio ou Projeto":
                    subMenusBox.getChildren().remove(fileText);
                    subMenusBox.getChildren().remove(viewStudentsBox);
                    props = new VBox(importProps, exportPros, viewProps, editProp, removeProp);
                    for(Node btn : props.getChildren()) {
                        btn.setId("comboBoxButton");
                    }
                    props.setSpacing(5);
                    props.setPadding(new Insets(5, 5, 5, 5));
                    subMenu.getChildren().clear();
                    subMenu.getChildren().add(props);
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

        scrollPane.setContent(content);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setStyle("-fx-background: #212121; -fx-border-color: #212121;");


        subMenusBox = new HBox(subMenu);
        subMenusBox.setSpacing(10);
        subMenusBox.setPadding(new Insets(10, 10, 10, 10));
        subMenusBox.setStyle("-fx-background: #212121; -fx-border-color: #212121;");

        leftBox = new VBox(text, comboBox, subMenusBox);
        leftBox.setPadding(new Insets(10, 10, 10, 10));
        leftBox.setSpacing(10);
        leftBox.setAlignment(Pos.TOP_LEFT);

        importStudentCSV = new TextField();
        importStudentCSV.setPromptText("Introduza o nome do ficheiro CSV:");
        importStudentCSV.setPrefWidth(220);
        importStudentCSV.setId("textField");
        importStudentCSV.getText();
        importStudentCSVBtn = new Button("Importar");
        importStudentCSVBtn.setId("comboBoxButton");

        exportStudentCSV = new TextField();
        exportStudentCSV.setPromptText("Introduza o nome do ficheiro CSV:");
        exportStudentCSV.setPrefWidth(220);
        exportStudentCSV.setId("textField");
        exportStudentCSV.getText();
        exportStudentCSVBtn = new Button("Exportar");
        exportStudentCSVBtn.setId("comboBoxButton");

        allStudents = new Button("Todos os Alunos");
        studentName = new Button("Nome");
        studentNumber = new Button("Número de Aluno");
        studentCourse = new Button("Curso");
        studentCourseBranch = new Button("Ramo");

        viewStudentsBox = new VBox(allStudents, studentName, studentNumber, studentCourse, studentCourseBranch);
        for(Node btn : viewStudentsBox.getChildren()) {
            btn.setId("subMenuButton");
        }
        viewStudentsBox.setSpacing(5);
        viewStudentsBox.setPadding(new Insets(5, 5, 5, 5));
        viewStudentsBox.setStyle("-fx-background: #212121; -fx-border-color: #212121;");

        fileText = new Text();

        Header header = new Header(model);
        StatusBar statusBar = new StatusBar(model);

        VBox bottomBox = new VBox(buttons, statusBar);
        bottomBox.setSpacing(10);
        bottomBox.setPadding(new Insets(10, 10, 0, 10));

        this.setLeft(leftBox);
        this.setRight(content);
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

        importStudents.setOnAction(e -> {
            subMenusBox.getChildren().remove(fileText);
            subMenusBox.getChildren().remove(viewStudentsBox);
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(this.getScene().getWindow());
            if (selectedFile != null && selectedFile.getName().endsWith(".csv")) {
                model.addAlunosCSV("csv/" + selectedFile.getName()); //Not working!! Não foi possível abrir o ficheiro
                fileText.setVisible(true);
                fileText.setText("Ficheiro " + selectedFile.getName() + " carregado com sucesso!");
                fileText.setFill(javafx.scene.paint.Color.WHITE);
                fileText.setId("defaultText");
                subMenusBox.getChildren().add(fileText);
                subMenusBox.setAlignment(Pos.CENTER);
                subMenusBox.setSpacing(30);
            }else if(selectedFile != null && !selectedFile.getName().endsWith(".csv")){
                fileText.setVisible(true);
                fileText.setText("Extensão do ficheiro inválida!\nPor favor, selecione um ficheiro .CSV!");
                fileText.setId("defaultText");
                fileText.setFill(javafx.scene.paint.Color.WHITE);
                fileText.setTextAlignment(TextAlignment.CENTER);
                subMenusBox.getChildren().add(fileText);
                subMenusBox.setAlignment(Pos.CENTER);
                subMenusBox.setSpacing(30);
            }
        });

        importStudentCSVBtn.setOnAction(e -> {
            model.addAlunosCSV(importStudentCSV.getText());
        });

        exportStudents.setOnAction(e -> {
            subMenusBox.getChildren().remove(fileText);
            subMenusBox.getChildren().remove(viewStudentsBox);
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
                subMenusBox.getChildren().add(fileText);
                subMenusBox.setAlignment(Pos.CENTER);
                subMenusBox.setSpacing(30);
            }
        });

        exportStudentCSVBtn.setOnAction(e -> {
            model.saveAlunosCSV(exportStudentCSV.getText());
        });

        viewStudents.setOnAction(e -> {
            subMenusBox.getChildren().remove(fileText);
            if(!subMenusBox.getChildren().contains(viewStudentsBox))
                subMenusBox.getChildren().add(viewStudentsBox);
            subMenusBox.setAlignment(Pos.CENTER);

        });

        allStudents.setOnAction(e -> {
            content.getChildren().clear();
            for(PoEAluno aluno : model.getAlunos()) {
                content.getChildren().add(new StudentCard(aluno));
            }
            this.setRight(scrollPane);
        });

        studentName.setOnAction(e -> {
            content.getChildren().clear();
            for(PoEAluno aluno : model.getAlunos()) {
                content.getChildren().add(new StudentCard(aluno));
            }
            this.setRight(scrollPane);
        });

        studentNumber.setOnAction(e -> {
            content.getChildren().clear();
            for(PoEAluno aluno : model.getAlunos()) {
                content.getChildren().add(new StudentCard(aluno));
            }
            this.setRight(scrollPane);
        });

        studentCourse.setOnAction(e -> {
            content.getChildren().clear();
            for(PoEAluno aluno : model.getAlunos()) {
                content.getChildren().add(new StudentCard(aluno));
            }
            this.setRight(scrollPane);
        });

        studentCourseBranch.setOnAction(e -> {
            content.getChildren().clear();
            for(PoEAluno aluno : model.getAlunos()) {
                content.getChildren().add(new StudentCard(aluno));
            }
            this.setRight(scrollPane);
        });

        editStudent.setOnAction(e -> {
            content.getChildren().clear();
            for(PoEAluno aluno : model.getAlunos()) {
                content.getChildren().add(new StudentCard(aluno));
            }
            this.setRight(scrollPane);
        });

    }

    /**
     * Método que atualiza a interface gráfica
     */
    private void update() {
        this.setVisible(model != null && model.getState() == PoEState.CONFIG);
    }
}
