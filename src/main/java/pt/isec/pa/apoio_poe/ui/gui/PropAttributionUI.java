package pt.isec.pa.apoio_poe.ui.gui;

import com.sun.javafx.scene.control.InputField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.model.data.PoEAluno;
import pt.isec.pa.apoio_poe.model.data.PoECandidatura;
import pt.isec.pa.apoio_poe.model.data.PoEProposta;
import pt.isec.pa.apoio_poe.model.fsm.PoEState;
import pt.isec.pa.apoio_poe.ui.gui.resources.CSSManager;
import pt.isec.pa.apoio_poe.utils.Utils;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.Scanner;

/**
 * A classe PropAttributionUI é uma classe que representa a interface gráfica
 * da fase de atribuição de propostas
 */
public class PropAttributionUI extends BorderPane {
    final ModelManager model;

    Button autoPropAttributionWith, autoPropAttributionWithout, manualPropAttribution, manualAttributionRemoval, listStudents, listProps, exportToCSV;
    Button removeAll, removeById, btnBackRemoval;
    Button haveAutopropAssociated, haveApplicationRegistered, havePropAttributed, haveNotPropAttributed, btnBackListStudents;
    Button studentsAutoProps, teachersProps, availableProps, givenProps, btnBackListProps;
    VBox listStudentsSubmenu, listPropsSubmenu, manualRemovalSubmenu;
    VBox mainBtns;
    Text fileText, info;
    String inputText;
    TextField textfield;
    Button submitBtn, searchBtn;
    ScrollPane scrollPane;
    VBox content;

    public PropAttributionUI(ModelManager model) {
        this.model = model;

        autoPropAttributionWith = new Button("Atribuição automática das propostas\n com aluno associado");
        autoPropAttributionWith.setTextAlignment(TextAlignment.CENTER);
        autoPropAttributionWithout = new Button("Atribuição automática de uma proposta\n (alunos sem atribuições)");
        autoPropAttributionWithout.setTextAlignment(TextAlignment.CENTER);
        manualPropAttribution = new Button("Atribuição manual de propostas \n(alunos sem atribuição)");
        manualAttributionRemoval = new Button("Remoção manual de uma atribuição");
        manualPropAttribution.setTextAlignment(TextAlignment.CENTER);
        listStudents = new Button("Listas de Alunos");
        listProps = new Button("Listas de Propostas");
        exportToCSV = new Button("Exportar alunos para um ficheiro CSV");
        scrollPane = new ScrollPane();
        content = new VBox();

        listStudentsSubmenu = new VBox();
        listPropsSubmenu = new VBox();
        manualRemovalSubmenu = new VBox();

        removeAll = new Button("Todos");
        removeById = new Button("Nrº Aluno");
        btnBackRemoval = new Button("Voltar");

        haveAutopropAssociated = new Button("Têm proposta associada");
        haveApplicationRegistered = new Button("Têm candidatura registada");
        havePropAttributed = new Button("Têm proposta atribuída");
        haveNotPropAttributed = new Button("Não têm qualquer proposta atribuída");
        btnBackListStudents = new Button("Voltar");

        studentsAutoProps = new Button("Autopropostas de alunos");
        teachersProps = new Button("Propostas de docentes");
        availableProps = new Button("Propostas disponíveis");
        givenProps = new Button("Propostas atribuídas");
        btnBackListProps = new Button("Voltar");

        fileText = new Text();
        info = new Text(); //Texto de informação
        info.setFill(javafx.scene.paint.Color.WHITE);
        info.setId("defaultText");

        textfield = new TextField();
        submitBtn = new Button();

        textfield = new TextField();
        textfield.setPrefWidth(220);
        textfield.setId("textField");
        textfield.getText();
        inputText = new String();
        inputText = textfield.getText();

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

        mainBtns = new VBox(autoPropAttributionWith, autoPropAttributionWithout, manualPropAttribution, manualAttributionRemoval, listStudents, listProps, exportToCSV);
        manualRemovalSubmenu = new VBox(removeAll, removeById, btnBackRemoval);
        listStudentsSubmenu = new VBox(haveAutopropAssociated, haveApplicationRegistered, havePropAttributed, haveNotPropAttributed, btnBackListStudents);
        listPropsSubmenu = new VBox(studentsAutoProps, teachersProps, availableProps, givenProps, btnBackListProps);
        mainBtns.setSpacing(5);
        manualRemovalSubmenu.setSpacing(5);
        listStudentsSubmenu.setSpacing(5);
        listPropsSubmenu.setSpacing(5);

        for (Node btn : mainBtns.getChildren()) {
            btn.setId("MenuButton");
        }
        for (Node btn : manualRemovalSubmenu.getChildren()) {
            btn.setId("MenuButton");
        }
        for (Node btn : listStudentsSubmenu.getChildren()) {
            btn.setId("MenuButton");
        }
        for (Node btn : listPropsSubmenu.getChildren()) {
            btn.setId("MenuButton");
        }

        autoPropAttributionWith.setId("MenuButtonMax");
        autoPropAttributionWithout.setId("MenuButtonMax");
        manualPropAttribution.setId("MenuButtonMax");

        content.setSpacing(5);
        content.setPadding(new Insets(0, 0, 0, 0));
        content.setStyle("-fx-background: #212121; -fx-border-color: #212121;");
        content.setPrefWidth(350);
        scrollPane.setContent(content);
        scrollPane.setPrefWidth(320);
        scrollPane.setPadding(new Insets(0, 0, 0, 0));
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setStyle("-fx-background: #212121; -fx-border-color: #212121;");

        Header header = new Header(model);
        StatusBar statusBar = new StatusBar(model);
        mainBtns.setPadding(new Insets(0, 0, 0, 10));
        manualRemovalSubmenu.setPadding(new Insets(0, 0, 0, 10));
        listStudentsSubmenu.setPadding(new Insets(0, 0, 0, 10));
        listPropsSubmenu.setPadding(new Insets(0, 0, 0, 10));

        VBox bottomBox = new VBox(buttons, statusBar);
        bottomBox.setSpacing(10);
        bottomBox.setPadding(new Insets(10, 10, 0, 10));

        textfield.setId("textField");
        submitBtn.setId("subMenuButton");
        submitBtn.setPrefSize(310, 25);
        submitBtn.setMinSize(310, 25);
        submitBtn.setMaxSize(310, 25);
        submitBtn.setPadding(new Insets(0, 20, 0, 0));

        this.setTop(header);
        this.setLeft(mainBtns);
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
        manualPropAttribution.setOnAction(evt -> {
            //TODO: Não aparece nada
            content.getChildren().clear();

            ArrayList<PoEAluno> alunosSemProposta = new ArrayList<>();
            for (PoEAluno aluno : model.getAlunos()) {
                if (aluno.getPropostaAtribuida() == null)
                    alunosSemProposta.add(aluno);
            }
            Collections.shuffle(alunosSemProposta);
            for (PoEAluno aluno : alunosSemProposta) {
                ArrayList<PoEProposta> propostasDisponiveis = new ArrayList<>();
                for (PoEProposta proposta : model.getPropostas()) {
                    if (proposta.getNrAlunoAtribuido() == null)
                        propostasDisponiveis.add(proposta);
                }
                info.setText("Propostas disponíveis (" + propostasDisponiveis.size() + ")");
                StringBuilder idsPropostasDisponiveis = new StringBuilder();
                for (PoEProposta prop : propostasDisponiveis) {
                    idsPropostasDisponiveis.append(prop.getId()).append(" ");
                }
                idsPropostasDisponiveis.append("\n");
                Text text = new Text(idsPropostasDisponiveis.toString());
                content.getChildren().add(info);
                content.getChildren().add(text);
                content.getChildren().add(
                        new Card(aluno.getNome(),
                                aluno.getNrEstudante().toString(),
                                aluno.getCurso() + " | " + aluno.getRamo()
                        )
                );
                textfield.clear();
                textfield.setPromptText("Introduza a proposta que pretende atribuir a este aluno:");
                content.getChildren().addAll(textfield, searchBtn);
                if (textfield.getText().equals("")) {
                    info.setText("Não foi selecionada nenhuma proposta.");
                } else {
                    if (propostasDisponiveis.contains(model.getPropostasByID(textfield.getText()))) {
                        PoEProposta proposta = model.getPropostasByID(textfield.getText());
                        proposta.setNrAlunoAtribuido(aluno.getNrEstudante());
                        aluno.setPropostaAtribuida(proposta);
                        info.setText("Proposta com o ID" + proposta.getId()
                                + " atribuída ao aluno com o número " + aluno.getNrEstudante() + ".");
                    } else {
                        info.setText("A proposta selecionada não existe ou já foi atribuída a outro aluno.");
                    }
                }
            }

            this.setLeft(manualRemovalSubmenu);
            this.setRight(scrollPane);
        });
        listStudents.setOnAction(evt -> {
            content.getChildren().clear();
            this.setLeft(listStudentsSubmenu);
            this.setRight(null);
        });
        listProps.setOnAction(evt -> {
            content.getChildren().clear();
            this.setLeft(listPropsSubmenu);
            this.setRight(null);
        });
        manualAttributionRemoval.setOnAction(evt -> {
            content.getChildren().clear();
            this.setLeft(manualRemovalSubmenu);
            this.setRight(null);
        });
        btnBackRemoval.setOnAction(evt -> {
            content.getChildren().clear();
            this.setLeft(mainBtns);
            this.setRight(null);
        });
        btnBackListStudents.setOnAction(evt -> {
            content.getChildren().clear();
            this.setLeft(mainBtns);
            this.setRight(null);
        });
        btnBackListProps.setOnAction(evt -> {
            content.getChildren().clear();
            this.setLeft(mainBtns);
            this.setRight(null);
        });
        autoPropAttributionWith.setOnAction(evt -> {
            content.getChildren().clear();
            ArrayList<PoEProposta> propostas = model.getPropostas();
            for(PoEProposta proposta : propostas){
                Long nrAlunoAtribuido = proposta.getNrAlunoAtribuido();
                if(nrAlunoAtribuido != null){
                    PoEAluno aluno = model.getAlunoByID(nrAlunoAtribuido);
                    if(aluno.getPropostaAtribuida() != null){
                        continue;
                    }
                    aluno.setPropostaAtribuida(proposta);
                    content.getChildren().add(new Card(
                        "Proposta #" + proposta.getId(),
                            "Atribuída ao aluno",
                            aluno.getNome()
                    ));
                }
            }
            this.setRight(scrollPane);
        });
        autoPropAttributionWithout.setOnAction(evt -> {
            content.getChildren().clear();
            ArrayList<PoEProposta> propostas = model.getPropostas();
            ArrayList<PoEAluno> alunos = model.getAlunos();
            for(PoEProposta proposta : propostas){
                ArrayList<PoEAluno> alunosSemProposta = new ArrayList<>();
                for(PoEAluno aluno : alunos){
                    if(aluno.getPropostaAtribuida() == null){
                        alunosSemProposta.add(aluno);
                    }
                }
                if(alunosSemProposta.size() == 0){
                    return;
                }
                if(proposta.getNrAlunoAtribuido() != null){
                    continue;
                }
                ArrayList<PoECandidatura> candidaturas = proposta.getCandidaturas();
                if(candidaturas.size() == 0){
                    Random r = new Random();
                    int index = r.nextInt(alunosSemProposta.size());
                    PoEAluno aluno = alunosSemProposta.get(index);
                    aluno.setPropostaAtribuida(proposta);
                    proposta.setNrAlunoAtribuido(aluno.getNrEstudante());
                    content.getChildren().add(new Card(
                            "Proposta #" + proposta.getId(),
                            "Atribuída ao aluno",
                            aluno.getNome()
                    ));
                } else if(candidaturas.size() == 1) {
                    PoECandidatura candidatura = candidaturas.get(0);
                    PoEAluno aluno = model.getAlunoByID(candidatura.getNrEstudante());
                    aluno.setPropostaAtribuida(proposta);
                    proposta.setNrAlunoAtribuido(aluno.getNrEstudante());
                    content.getChildren().add(new Card(
                        "Proposta #" + proposta.getId(),
                            "Atribuída ao aluno",
                            aluno.getNome()
                    ));
                } else {
                    //TODO
                }
            }

            this.setRight(scrollPane);
        });
        manualPropAttribution.setOnAction(evt -> {
            content.getChildren().clear();
            //TODO
            this.setRight(scrollPane);
        });
        haveApplicationRegistered.setOnAction(evt -> {
            content.getChildren().clear();
            ArrayList<PoEAluno> alunos = model.getAlunos();
            for (PoEAluno aluno : alunos) {
                if (aluno.getCandidatura() != null) {
                    content.getChildren().add(new Card(
                            aluno.getNome(),
                            aluno.getNrEstudante() + "",
                            aluno.getCandidatura().getPreferencias().toString()
                    ));
                }
            }
            this.setRight(scrollPane);
        });
        havePropAttributed.setOnAction(evt -> {
            content.getChildren().clear();
            ArrayList<PoEAluno> alunos = model.getAlunos();
            for (PoEAluno aluno : alunos) {
                if (aluno.getCandidatura() != null && aluno.getCandidatura().getPreferencias() != null) {
                    if(aluno.getPropostaAtribuida() != null){
                        int ordemPreferencia = aluno.getCandidatura().getPreferencias().indexOf(aluno.getPropostaAtribuida().getId());
                        if(ordemPreferencia == -1){
                            content.getChildren().add(new Card(
                                    aluno.getNome(),
                                    aluno.getNrEstudante() + "",
                                    aluno.getPropostaAtribuida().getId() + " (Ordem de preferencia: " + ordemPreferencia + ")"
                            ));
                        } else {
                            content.getChildren().add(new Card(
                                    aluno.getNome(),
                                    aluno.getNrEstudante() + "",
                                    aluno.getPropostaAtribuida().getId()
                            ));
                        }
                    }
                }
            }
            this.setRight(scrollPane);
        });
        haveNotPropAttributed.setOnAction(evt -> {
            content.getChildren().clear();
            ArrayList<PoEAluno> alunos = model.getAlunos();
            for(PoEAluno aluno : alunos){
                if(aluno.getPropostaAtribuida() == null) {
                    content.getChildren().add(new Card(
                            aluno.getNome(),
                            aluno.getNrEstudante() + "",
                            "Sem proposta atribuída"
                    ));
                }
            }
            this.setRight(scrollPane);
        });
        teachersProps.setOnAction(evt -> {
            content.getChildren().clear();
            ArrayList<PoEProposta> propostas = model.getPropostasByType("T2");
            for(PoEProposta proposta : propostas){
                content.getChildren().add(new Card(
                        proposta.getTitulo(),
                        proposta.getDocente() == null ? "Sem docente" : proposta.getDocente().getNome(),
                        proposta.getId() + ""
                ));
            }
            this.setRight(scrollPane);
        });
        availableProps.setOnAction(evt -> {
            content.getChildren().clear();
            ArrayList<PoEProposta> propostas = model.getPropostas();
            for(PoEProposta proposta : propostas){
                if(proposta.getNrAlunoAtribuido() == null){
                    content.getChildren().add(new Card(
                            proposta.getTitulo(),
                            proposta.getId() + "",
                            "Proposta disponível"
                    ));
                }
            }
            this.setRight(scrollPane);
        });
        givenProps.setOnAction(evt -> {
            content.getChildren().clear();
            ArrayList<PoEProposta> propostas = model.getPropostas();
            for(PoEProposta proposta : propostas){
                if(proposta.getNrAlunoAtribuido() != null){
                    content.getChildren().add(new Card(
                            proposta.getTitulo(),
                            proposta.getId() + "",
                            proposta.getAluno() == null ? "Proposta disponível" : proposta.getAluno().getNome()
                    ));
                }
            }
            this.setRight(scrollPane);
        });
        removeAll.setOnAction(evt -> {
            content.getChildren().clear();
            ArrayList<PoEAluno> alunos = model.getAlunos();
            for(PoEAluno aluno : alunos){
                PoEProposta proposta = aluno.getPropostaAtribuida();
                if(proposta != null){
                    if(proposta.getNrAlunoAtribuido() != null){
                        aluno.setPropostaAtribuida(null);
                        proposta.setNrAlunoAtribuido(null);
                        content.getChildren().add(new Card(
                                "Proposta removida",
                                aluno.getNrEstudante() + "",
                                proposta.getId()
                        ));
                    }
                }
            }
            this.setRight(scrollPane);
        });
        removeById.setOnAction(evt -> {
            content.getChildren().clear();
            textfield.clear();
            textfield.setPromptText("Número do aluno");
            submitBtn.setText("Remover");
            content.getChildren().addAll(textfield, submitBtn);
            this.setRight(scrollPane);
        });
        exportToCSV.setOnAction(evt -> {
            content.getChildren().clear();
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                model.saveAlunosCSV(file.getAbsolutePath());
                fileText.setText("Alunos exportados para o \nficheiro " + file.getName());
                fileText.setFill(Color.WHITE);
                fileText.setId("defaultText");
                content.getChildren().add(fileText);
                this.setRight(content);
            }
        });
        submitBtn.setOnAction(evt -> {
            content.getChildren().clear();
            PoEAluno aluno = model.getAlunoByID(Long.parseLong(textfield.getText()));
            PoEProposta proposta = aluno.getPropostaAtribuida();
            if(proposta != null){
                if(proposta.getNrAlunoAtribuido() != null){
                    aluno.setPropostaAtribuida(null);
                    proposta.setNrAlunoAtribuido(null);
                    content.getChildren().add(new Card(
                            "Proposta removida",
                            aluno.getNrEstudante() + "",
                            proposta.getId()
                    ));
                }
            }
            this.setRight(scrollPane);
        });
    }

    /**
     * Método que atualiza a interface gráfica
     */
    private void update() {
        this.setVisible(model != null && model.getState() == PoEState.PROP_ATTRIBUTION);
        if(model.isClosed() && model.getState() == PoEState.PROP_ATTRIBUTION){
            autoPropAttributionWith.setDisable(true);
            autoPropAttributionWithout.setDisable(true);
            manualPropAttribution.setDisable(true);
            manualAttributionRemoval.setDisable(true);
        }
    }
}
