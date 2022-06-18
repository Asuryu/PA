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
import pt.isec.pa.apoio_poe.model.data.*;
import pt.isec.pa.apoio_poe.model.fsm.PoEState;
import pt.isec.pa.apoio_poe.ui.gui.resources.CSSManager;

import java.io.File;
import java.util.ArrayList;

/**
 * A classe ConfigUI é uma classe que representa a "interface" gráfica
 * da fase de configuração
 */
public class ConfigUI extends BorderPane {
    final ModelManager model;
    Button importStudents, exportStudents, viewStudents, editStudent, removeStudent;
    Button importTeachers, exportTeachers, viewTeachers, editTeacher, removeTeacher;
    Button importProps, exportProps, viewProps, editProp, removeProp;
    Button studentBtn, teacherBtn, propBtn;
    Button allStudents, studentName, studentNumber, studentCourse, studentCourseBranch;
    Button allTeachers, teacherName, teacherEmail;
    Button allProps, propID, propTitle, propType;
    ScrollPane scrollPane;
    VBox content, subMenu, students, teachers, props, leftBox, viewStudentsBox, viewTeachersBox, viewPropsBox;
    HBox subMenusBox;
    ComboBox comboBox;
    TextField studentTextField, teacherTextField, propTextField;
    String studentText, teacherText, propText;
    Text fileText, info;

    int studentMethod = 0, teacherMethod = 0, propMethod = 0;

    public ConfigUI(ModelManager model) {
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

        // Gestão de alunos
        importStudents = new Button("Importar alunos de um ficheiro CSV");
        importStudents.setId("MenuButton");
        exportStudents = new Button("Exportar alunos para um ficheiro CSV");
        exportStudents.setId("MenuButton");
        viewStudents = new Button("Consultar alunos");
        viewStudents.setId("MenuButton");
        editStudent = new Button("Editar aluno");
        editStudent.setId("MenuButton");
        removeStudent = new Button("Remover aluno");
        removeStudent.setId("MenuButton");

        //Gestão de docentes
        importTeachers = new Button("Importar docentes de um ficheiro CSV");
        importTeachers.setId("MenuButton");
        exportTeachers = new Button("Exportar docentes para um ficheiro CSV");
        exportTeachers.setId("MenuButton");
        viewTeachers = new Button("Consultar docentes");
        viewTeachers.setId("MenuButton");
        editTeacher = new Button("Editar docente");
        editTeacher.setId("MenuButton");
        removeTeacher = new Button("Remover docente");
        removeTeacher.setId("MenuButton");

        //Gestão de propostas de estágio ou projeto
        importProps = new Button("Importar propostas de um ficheiro CSV");
        importProps.setId("MenuButton");
        exportProps = new Button("Exportar propostas para um ficheiro CSV");
        exportProps.setId("MenuButton");
        viewProps = new Button("Consultar propostas");
        viewProps.setId("MenuButton");
        editProp = new Button("Editar proposta");
        editProp.setId("MenuButton");
        removeProp = new Button("Remover proposta");
        removeProp.setId("MenuButton");

        subMenu = new VBox();
        scrollPane = new ScrollPane();

        ObservableList<String> options = FXCollections.observableArrayList(
                "Gestão de Alunos",
                "Gestão de Docentes",
                "Gestão de Propostas"
        );

        students = new VBox();
        students.getChildren().addAll(importStudents, exportStudents, viewStudents, editStudent, removeStudent);
        students.setSpacing(5);
        students.setPadding(new Insets(5, 5, 5, 0));

        teachers = new VBox();
        teachers.getChildren().addAll(importTeachers, exportTeachers, viewTeachers, editTeacher, removeTeacher);
        teachers.setSpacing(5);
        teachers.setPadding(new Insets(5, 5, 5, 0));

        props = new VBox();
        props.getChildren().addAll(importProps, exportProps, viewProps, editProp, removeProp);
        props.setSpacing(5);
        props.setPadding(new Insets(5, 5, 5, 0));

        comboBox = new ComboBox();
        comboBox.setItems(options);
        comboBox.setId("comboBox");
        comboBox.getSelectionModel().selectFirst();

        comboBox.setOnAction(e -> {
            switch (comboBox.getValue().toString()) {
                case "Gestão de Alunos":
                    content.getChildren().clear();
                    subMenusBox.getChildren().remove(fileText);
                    subMenusBox.getChildren().remove(viewStudentsBox);
                    subMenusBox.getChildren().remove(viewTeachersBox);
                    subMenusBox.getChildren().remove(viewPropsBox);
                    subMenu.getChildren().clear();
                    subMenu.getChildren().add(students);
                    break;
                case "Gestão de Docentes":
                    content.getChildren().clear();
                    subMenusBox.getChildren().remove(fileText);
                    subMenusBox.getChildren().remove(viewStudentsBox);
                    subMenusBox.getChildren().remove(viewTeachersBox);
                    subMenusBox.getChildren().remove(viewPropsBox);
                    subMenu.getChildren().clear();
                    subMenu.getChildren().add(teachers);
                    break;
                case "Gestão de Propostas":
                    content.getChildren().clear();
                    subMenusBox.getChildren().remove(fileText);
                    subMenusBox.getChildren().remove(viewStudentsBox);
                    subMenusBox.getChildren().remove(viewTeachersBox);
                    subMenusBox.getChildren().remove(viewPropsBox);
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

        // Botões de gestão de alunos

        allStudents = new Button("Todos os Alunos");
        allStudents.setId("subMenuButton");
        studentName = new Button("Nome");
        studentName.setId("subMenuButton");
        studentNumber = new Button("Número de Aluno");
        studentNumber.setId("subMenuButton");
        studentCourse = new Button("Curso");
        studentCourse.setId("subMenuButton");
        studentCourseBranch = new Button("Ramo");
        studentCourseBranch.setId("subMenuButton");

        studentBtn = new Button("Procurar");
        studentBtn.setId("subMenuButton");

        studentTextField = new TextField();
        studentTextField.setPrefWidth(220);
        studentTextField.setId("textField");
        studentTextField.getText();
        studentText = new String();
        studentText = studentTextField.getText();

        viewStudentsBox = new VBox(allStudents, studentName, studentNumber, studentCourse, studentCourseBranch);
        viewStudentsBox.setSpacing(5);
        viewStudentsBox.setPadding(new Insets(5, 5, 5, 5));
        viewStudentsBox.setStyle("-fx-background: #212121; -fx-border-color: #212121;");

        // Botões de gestão de professores

        allTeachers = new Button("Todos os Docentes");
        allTeachers.setId("subMenuButton");
        teacherName = new Button("Nome");
        teacherName.setId("subMenuButton");
        teacherEmail = new Button("Email de Docente");
        teacherEmail.setId("subMenuButton");

        teacherBtn = new Button("Procurar");
        teacherBtn.setId("subMenuButton");

        teacherTextField = new TextField();
        teacherTextField.setPrefWidth(220);
        teacherTextField.setId("textField");
        teacherTextField.getText();
        teacherText = new String();
        teacherText = teacherTextField.getText();

        viewTeachersBox = new VBox(allTeachers, teacherName, teacherEmail);
        viewTeachersBox.setSpacing(5);
        viewTeachersBox.setPadding(new Insets(5, 5, 5, 5));
        viewTeachersBox.setStyle("-fx-background: #212121; -fx-border-color: #212121;");

        // Botões de gestão de propostas

        allProps = new Button("Todas as propostas");
        allProps.setId("subMenuButton");
        propID = new Button("ID");
        propID.setId("subMenuButton");
        propTitle = new Button("Título de proposta");
        propTitle.setId("subMenuButton");
        propType = new Button("Tipo de proposta");
        propType.setId("subMenuButton");

        propBtn = new Button("Procurar");
        propBtn.setId("subMenuButton");

        propTextField = new TextField();
        propTextField.setPrefWidth(220);
        propTextField.setId("textField");
        propTextField.getText();
        propText = new String();
        propText = propTextField.getText();

        viewPropsBox = new VBox(allProps, propID, propTitle, propType);
        viewPropsBox.setSpacing(5);
        viewPropsBox.setPadding(new Insets(5, 5, 5, 5));
        viewPropsBox.setStyle("-fx-background: #212121; -fx-border-color: #212121;");

        fileText = new Text();
        info = new Text(); //Texto de informação
        info.setFill(javafx.scene.paint.Color.WHITE);
        info.setId("defaultText");

        Header header = new Header(model);
        StatusBar statusBar = new StatusBar(model);

        VBox bottomBox = new VBox(buttons, statusBar);
        bottomBox.setSpacing(10);
        bottomBox.setPadding(new Insets(10, 10, 0, 10));

        this.setLeft(leftBox);
        this.setRight(content);
        this.setBottom(bottomBox);
        this.setTop(header);
        this.setPadding(new Insets(10, 20, 10, 10));
    }

    /**
     * Método que regista os handlers da interface gráfica
     */
    private void registerHandlers() {
        model.addPropertyChangeListener(ModelManager.PROP_STATE, evt -> {
            update();
        });

        // Menu de gestão de alunos

        importStudents.setOnAction(e -> {
            content.getChildren().clear();
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

        exportStudents.setOnAction(e -> {
            content.getChildren().clear();
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

        viewStudents.setOnAction(e -> {
            content.getChildren().clear();
            viewStudents.setStyle("-fx-background: #af2821; -fx-border-color: #ffffff;");
            subMenusBox.getChildren().remove(fileText);
            if(!subMenusBox.getChildren().contains(viewStudentsBox))
                subMenusBox.getChildren().add(viewStudentsBox);
            subMenusBox.setAlignment(Pos.CENTER);
        });

        allStudents.setOnAction(e -> {
            content.getChildren().clear();
            info.setText("Não existem alunos registados!");
            info.setId("defaultText");
            if(model.getAlunos().size() == 0) {
                content.getChildren().add(info);
                content.setAlignment(Pos.CENTER);
            }else{
                for(PoEAluno aluno : model.getAlunos()) {
                    content.getChildren().add(
                            new Card(aluno.getNome(),
                                     aluno.getNrEstudante().toString(),
                                     aluno.getCurso() + " | " + aluno.getRamo()
                                    )
                    );
                }
                this.setRight(scrollPane);
            }
        });

        studentName.setOnAction(e -> {
            studentMethod = 1;
            content.getChildren().clear();
            studentTextField.clear();
            studentTextField.setPromptText("Nome do aluno:");
            content.getChildren().addAll(studentTextField, studentBtn);
        });

        studentNumber.setOnAction(e -> {
            studentMethod = 2;
            content.getChildren().clear();
            studentTextField.clear();
            studentTextField.setPromptText("Número do aluno:");
            content.getChildren().addAll(studentTextField, studentBtn);
        });

        studentCourse.setOnAction(e -> {
            studentMethod = 3;
            content.getChildren().clear();
            studentTextField.clear();
            studentTextField.setPromptText("Curso:");
            content.getChildren().addAll(studentTextField, studentBtn);
        });

        studentCourseBranch.setOnAction(e -> {
            studentMethod = 4;
            content.getChildren().clear();
            studentTextField.clear();
            studentTextField.setPromptText("Ramo:");
            content.getChildren().addAll(studentTextField, studentBtn);
        });

        editStudent.setOnAction(e -> {
            subMenusBox.getChildren().remove(viewStudentsBox);
            studentMethod = 5;
            editStudent.setStyle("-fx-background: #af2821; -fx-border-color: #ffffff;");
            studentTextField.clear();
            studentTextField.setPromptText("Nome do aluno:");
            content.getChildren().clear();
            content.getChildren().addAll(studentTextField, studentBtn);
            content.setAlignment(Pos.CENTER);
        });

        removeStudent.setOnAction(e -> {
            subMenusBox.getChildren().remove(viewStudentsBox);
            studentMethod = 6;
            removeStudent.setStyle("-fx-background: #af2821; -fx-border-color: #ffffff;");
            studentTextField.clear();
            studentTextField.setPromptText("Nome do aluno:");
            content.getChildren().clear();
            content.getChildren().addAll(studentTextField, studentBtn);
            content.setAlignment(Pos.CENTER);
        });

        studentBtn.setOnAction(e -> {
            ArrayList<PoEAluno> alunos;
            switch(studentMethod) {
                case 1:
                    content.getChildren().clear();
                    alunos = model.getAlunosByName(studentTextField.getText());
                    if(alunos.size() == 0) {
                        content.getChildren().add(info);
                        info.setText("Não foram encontrados alunos com esse nome");
                        content.setAlignment(Pos.CENTER);
                    }else{
                        for(PoEAluno aluno : alunos) {
                            content.getChildren().add(
                                    new Card(aluno.getNome(),
                                            aluno.getNrEstudante().toString(),
                                            aluno.getCurso() + " | " + aluno.getRamo()
                                    )
                            );
                        }
                    }
                    break;
                case 2:
                    content.getChildren().clear();
                    alunos = model.getAlunoByID(Long.parseLong(studentTextField.getText()));
                    if(alunos.size() == 0) {
                        content.getChildren().add(info);
                        info.setText("Não foi encontrado nenhum aluno com esse ID");
                        content.setAlignment(Pos.CENTER);
                    }else{
                        for(PoEAluno aluno : alunos) {
                            content.getChildren().add(
                                    new Card(aluno.getNome(),
                                            aluno.getNrEstudante().toString(),
                                            aluno.getCurso() + " | " + aluno.getRamo()
                                    )
                            );
                        }
                    }
                    break;
                case 3:
                    content.getChildren().clear();
                    alunos = model.getAlunosByCurso(studentTextField.getText());
                    if(alunos.size() == 0) {
                        content.getChildren().add(info);
                        info.setText("Não foram encontrados alunos com esse curso");
                        content.setAlignment(Pos.CENTER);
                    }else{
                        for(PoEAluno aluno : alunos) {
                            content.getChildren().add(
                                    new Card(aluno.getNome(),
                                            aluno.getNrEstudante().toString(),
                                            aluno.getCurso() + " | " + aluno.getRamo()
                                    )
                            );
                        }
                    }
                    break;
                case 4:
                    content.getChildren().clear();
                    alunos = model.getAlunosByRamo(studentTextField.getText());
                    if(alunos.size() == 0) {
                        content.getChildren().add(info);
                        info.setText("Não foram encontrados alunos com esse ramo");
                        content.setAlignment(Pos.CENTER);
                    }else{
                        for(PoEAluno aluno : alunos) {
                            content.getChildren().add(
                                    new Card(aluno.getNome(),
                                            aluno.getNrEstudante().toString(),
                                            aluno.getCurso() + " | " + aluno.getRamo()
                                    )
                            );
                        }
                    }
                    break;
                case 5:
                    break; //TODO
                case 6:
                    Long nrAluno = Long.parseLong(studentTextField.getText());
                    if(model.removeAluno(nrAluno)){
                        content.getChildren().clear();
                        content.getChildren().add(info);
                        info.setText("Aluno removido com sucesso");
                        content.setAlignment(Pos.CENTER);
                    } else {
                        content.getChildren().clear();
                        content.getChildren().add(info);
                        info.setText("Não foi possível remover o aluno");
                        content.setAlignment(Pos.CENTER);
                    }
                    break;
                default:
                    info.setText("Aluno não encontrado!");
                    if(!content.getChildren().contains(info))
                        content.getChildren().add(info);
                    content.setAlignment(Pos.CENTER);
                    break;
            }
        });

        // Menu de gestão de professores

        importTeachers.setOnAction(e -> {
            content.getChildren().clear();
            subMenusBox.getChildren().remove(fileText);
            subMenusBox.getChildren().remove(viewTeachersBox);
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(this.getScene().getWindow());
            if (selectedFile != null && selectedFile.getName().endsWith(".csv")) {
                model.addDocentesCSV("csv/" + selectedFile.getName()); //Not working!! Não foi possível abrir o ficheiro
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

        exportTeachers.setOnAction(e -> {
            content.getChildren().clear();
            subMenusBox.getChildren().remove(fileText);
            subMenusBox.getChildren().remove(viewTeachersBox);
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

        viewTeachers.setOnAction(e -> {
            content.getChildren().clear();
            viewTeachers.setStyle("-fx-background: #af2821; -fx-border-color: #ffffff;");
            subMenusBox.getChildren().remove(fileText);
            if(!subMenusBox.getChildren().contains(viewTeachersBox))
                subMenusBox.getChildren().add(viewTeachersBox);
            subMenusBox.setAlignment(Pos.CENTER);
        });

        allTeachers.setOnAction(e -> {
            content.getChildren().clear();
            info.setText("Não existem docentes registados!");
            info.setId("defaultText");
            if(model.getAlunos().size() == 0) {
                content.getChildren().add(info);
                content.setAlignment(Pos.CENTER);
            }else{
                for(PoEDocente docente : model.getDocentes()) {
                    content.getChildren().add(
                            new Card(docente.getNome(),
                                    docente.getEmail(),
                                    ""
                            )
                    );
                }
                this.setRight(scrollPane);
            }
        });

        teacherName.setOnAction(e -> {
            teacherMethod = 1;
            content.getChildren().clear();
            teacherTextField.clear();
            teacherTextField.setPromptText("Nome do docente:");
            content.getChildren().addAll(teacherTextField, teacherBtn);
        });

        teacherEmail.setOnAction(e -> {
            teacherMethod = 2;
            content.getChildren().clear();
            teacherTextField.clear();
            teacherTextField.setPromptText("Email do docente:");
            content.getChildren().addAll(teacherTextField, teacherBtn);
        });

        editTeacher.setOnAction(e -> {
            subMenusBox.getChildren().remove(viewTeachersBox);
            teacherMethod = 3;
            editTeacher.setStyle("-fx-background: #af2821; -fx-border-color: #ffffff;");
            teacherTextField.clear();
            teacherTextField.setPromptText("Nome do docente:");
            content.getChildren().clear();
            content.getChildren().addAll(teacherTextField, teacherBtn);
            content.setAlignment(Pos.CENTER);
        });

        removeTeacher.setOnAction(e -> {
            subMenusBox.getChildren().remove(viewTeachersBox);
            teacherMethod = 4;
            removeTeacher.setStyle("-fx-background: #af2821; -fx-border-color: #ffffff;");
            teacherTextField.clear();
            teacherTextField.setPromptText("Nome do docente:");
            content.getChildren().clear();
            content.getChildren().addAll(teacherTextField, teacherBtn);
            content.setAlignment(Pos.CENTER);
        });

        teacherBtn.setOnAction(e -> {
            ArrayList<PoEDocente> docentes;
            switch(teacherMethod) {
                case 1:
                    content.getChildren().clear();
                    docentes = model.getDocentesByName(teacherTextField.getText());
                    if(docentes.size() == 0) {
                        content.getChildren().add(info);
                        info.setText("Não foram encontrados docentes com esse nome");
                        content.setAlignment(Pos.CENTER);
                    }else{
                        for(PoEDocente docente : docentes) {
                            content.getChildren().add(
                                    new Card(docente.getNome(),
                                            docente.getEmail(),
                                            ""
                                    )
                            );
                        }
                    }
                    break;
                case 2:
                    content.getChildren().clear();
                    docentes = model.getDocenteByEmail(teacherTextField.getText());
                    if(docentes.size() == 0) {
                        content.getChildren().add(info);
                        info.setText("Não foram encontrados docentes com esse email");
                        content.setAlignment(Pos.CENTER);
                    }else{
                        for(PoEDocente docente : docentes) {
                            content.getChildren().add(
                                    new Card(docente.getNome(),
                                            docente.getEmail(),
                                            ""
                                    )
                            );
                        }
                    }
                    break;
                case 3:
                    break; //TODO
                case 4:
                    String email = teacherTextField.getText();
                    ArrayList<PoEDocente> docente = model.getDocenteByEmail(email);
                    if(docente.size() == 0) {
                        content.getChildren().add(info);
                        info.setText("Não foi encontrado nenhum \ndocente com esse email");
                        content.setAlignment(Pos.CENTER);
                    }else{
                        if(model.removeDocente(docente.get(0))) {
                            content.getChildren().clear();
                            content.getChildren().add(info);
                            info.setText("Docente " + docente.get(0).getNome() + "\nremovido com sucesso!");
                            content.setAlignment(Pos.CENTER);
                        }else{
                            content.getChildren().clear();
                            content.getChildren().add(info);
                            info.setText("Não foi possível remover o docente \ncom o email" + email);
                            content.setAlignment(Pos.CENTER);
                        }
                    }
                    break;
                default:
                    info.setText("Docente não encontrado!");
                    if(!content.getChildren().contains(info))
                        content.getChildren().add(info);
                    content.setAlignment(Pos.CENTER);
                    break;
            }
        });

        // Menu de gestão de propostas

        importProps.setOnAction(e -> {
            content.getChildren().clear();
            subMenusBox.getChildren().remove(fileText);
            subMenusBox.getChildren().remove(viewPropsBox);
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

        exportProps.setOnAction(e -> {
            content.getChildren().clear();
            subMenusBox.getChildren().remove(fileText);
            subMenusBox.getChildren().remove(viewPropsBox);
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

        viewProps.setOnAction(e -> {
            content.getChildren().clear();
            viewProps.setStyle("-fx-background: #af2821; -fx-border-color: #ffffff;");
            subMenusBox.getChildren().remove(fileText);
            if(!subMenusBox.getChildren().contains(viewPropsBox))
                subMenusBox.getChildren().add(viewPropsBox);
            subMenusBox.setAlignment(Pos.CENTER);
        });

        allProps.setOnAction(e -> {
            content.getChildren().clear();
            info.setText("Não existem alunos registados!");
            info.setId("defaultText");
            if(model.getAlunos().size() == 0) {
                content.getChildren().add(info);
                content.setAlignment(Pos.CENTER);
            }else{
                for(PoEProposta proposta : model.getPropostas()) {
                    String text = "Proposta não atribuída";
                    if(proposta.getNrAlunoAtribuido() != null){
                        text = "Atribuída ao aluno: " + proposta.getNrAlunoAtribuido();
                    }
                    content.getChildren().add(new Card(proposta.getTitulo(),
                            proposta.getId(),
                            text
                    ));
                }
                this.setRight(scrollPane);
            }
        });

        propID.setOnAction(e -> {
            propMethod = 1;
            content.getChildren().clear();
            propTextField.clear();
            propTextField.setPromptText("ID de proposta:");
            content.getChildren().addAll(propTextField, propBtn);
        });

        propTitle.setOnAction(e -> {
            propMethod = 2;
            content.getChildren().clear();
            propTextField.clear();
            propTextField.setPromptText("Título de proposta:");
            content.getChildren().addAll(propTextField, propBtn);
        });

        propType.setOnAction(e -> {
            propMethod = 3;
            content.getChildren().clear();
            // Adicionar 3 opções Estágio / Projeto / Estágio±Projeto Autoproposto || Listing para cada um deles
            propTextField.clear();
            content.getChildren().addAll(propTextField, propBtn);
        });

        editProp.setOnAction(e -> {
            propMethod = 4;
            subMenusBox.getChildren().remove(viewPropsBox);
            editProp.setStyle("-fx-background: #af2821; -fx-border-color: #ffffff;");
            propTextField.clear();
            propTextField.setPromptText("ID de proposta:");
            content.getChildren().clear();
            content.getChildren().addAll(propTextField, propBtn);
            content.setAlignment(Pos.CENTER);
        });

        removeProp.setOnAction(e -> {
            propMethod = 5;
            subMenusBox.getChildren().remove(viewPropsBox);
            removeProp.setStyle("-fx-background: #af2821; -fx-border-color: #ffffff;");
            propTextField.clear();
            propTextField.setPromptText("ID de proposta:");
            content.getChildren().clear();
            content.getChildren().addAll(propTextField, propBtn);
            content.setAlignment(Pos.CENTER);
        });

        propBtn.setOnAction(e -> {
            ArrayList<PoEProposta> propostas;
            switch(propMethod) {
                case 1:
                    content.getChildren().clear();
                    propostas = model.getPropostasByID(propTextField.getText());
                    if(propostas.size() == 0) {
                        content.getChildren().add(info);
                        content.setAlignment(Pos.CENTER);
                    }else{
                        for(PoEProposta proposta : propostas) {
                            if(proposta instanceof PoEProjeto) {
                                content.getChildren().add(new Card(proposta.getTitulo(),
                                        proposta.getId(),
                                        "Projeto"
                                ));
                            }else if(proposta instanceof PoEEstagio) {
                                content.getChildren().add(new Card(proposta.getTitulo(),
                                        proposta.getId(),
                                        "Estágio"
                                ));
                            } else if(proposta instanceof PoEAutoproposto) {
                                content.getChildren().add(new Card(proposta.getTitulo(),
                                        proposta.getId(),
                                        "Autoproposta estágio/projeto"
                                ));
                            } else {
                                content.getChildren().add(new Card(proposta.getTitulo(),
                                        proposta.getId(),
                                        ""
                                ));
                            }
                        }
                        this.setRight(scrollPane);
                    }
                    break;
                case 2:
                    content.getChildren().clear();
                    propostas = model.getPropostasByTitle(propTextField.getText());
                    if(propostas.size() == 0) {
                        content.getChildren().add(info);
                        content.setAlignment(Pos.CENTER);
                    }else{
                        for(PoEProposta proposta : propostas) {
                            if(proposta instanceof PoEProjeto) {
                                content.getChildren().add(new Card(proposta.getTitulo(),
                                        proposta.getId(),
                                        "Projeto"
                                ));
                            }else if(proposta instanceof PoEEstagio) {
                                content.getChildren().add(new Card(proposta.getTitulo(),
                                        proposta.getId(),
                                        "Estágio"
                                ));
                            } else if(proposta instanceof PoEAutoproposto) {
                                content.getChildren().add(new Card(proposta.getTitulo(),
                                        proposta.getId(),
                                        "Autoproposta estágio/projeto"
                                ));
                            } else {
                                content.getChildren().add(new Card(proposta.getTitulo(),
                                        proposta.getId(),
                                        ""
                                ));
                            }
                        }
                        this.setRight(scrollPane);
                    }
                    break;
                case 3:
                    content.getChildren().clear();
                    String type = propTextField.getText();
                    if(type.equalsIgnoreCase("projetos")) {
                        type = "T1";
                    }else if(type.equalsIgnoreCase("estágios")) {
                        type = "T2";
                    } else if(type.equalsIgnoreCase("autopropostas")) {
                        type = "T3";
                    }
                    System.out.println(type);

                    propostas = model.getPropostasByType(type);
                    if(propostas.size() == 0) {
                        content.getChildren().add(info);
                        content.setAlignment(Pos.CENTER);
                    }else{
                        for(PoEProposta proposta : propostas) {
                            if(propTextField.getText().equalsIgnoreCase("projeto")) {
                                content.getChildren().add(new Card(proposta.getTitulo(),
                                        proposta.getId(),
                                        "Projeto"
                                ));
                            }
                        }
                        this.setRight(scrollPane);
                    }
                    break;
                case 4:
                    break; //TODO
                case 5:
                    String id = propTextField.getText();
                    ArrayList<PoEProposta> props = model.getPropostasByID(id);
                    if(props.size() == 0) {
                        content.getChildren().add(info);
                        info.setText("Não foi encontrado nenhuma\n proposta com esse ID");
                        content.setAlignment(Pos.CENTER);
                    }else{
                        if(model.removeProposta(props.get(0))) {
                            content.getChildren().clear();
                            content.getChildren().add(info);
                            info.setText("Proposta " + props.get(0).getId() + "\nremovida com sucesso!");
                            content.setAlignment(Pos.CENTER);
                        }else{
                            content.getChildren().clear();
                            content.getChildren().add(info);
                            info.setText("Não foi possível removera proposta\n com o ID " + id);
                            content.setAlignment(Pos.CENTER);
                        }
                    }
                    break;
                default:
                    info.setText("Proposta não encontrada!");
                    if(!content.getChildren().contains(info))
                        content.getChildren().add(info);
                    content.setAlignment(Pos.CENTER);
                    break;
            }

        });

    }

    /**
     * Método que atualiza a interface gráfica
     */
    private void update() {
        this.setVisible(model != null && model.getState() == PoEState.CONFIG);
    }
}
