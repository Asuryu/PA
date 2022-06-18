package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.model.data.PoEAluno;
import pt.isec.pa.apoio_poe.model.data.PoEOrientador;
import pt.isec.pa.apoio_poe.model.data.PoEProposta;
import pt.isec.pa.apoio_poe.model.fsm.PoEState;
import pt.isec.pa.apoio_poe.ui.gui.resources.CSSManager;

import java.io.File;
import java.util.ArrayList;

/**
 * A classe OriAttributionUI é uma classe que representa a interface gráfica
 * da fase de atribuição de orientadores
 */
public class OriAttributionUI extends BorderPane {
    final ModelManager model;
    Button autoOriAttribution, oriManagement, manualOriAttribution, listData, exportToCSV;
    ScrollPane scrollPane;
    VBox content;
    VBox oriManagementSubmenu, listDataSubmenu, searchOriSubmenu;
    VBox mainBtns;
    Button btnBackOriMgmt, attrOriBtn, listOriBtn, changeOriBtn, removeOriBtn;
    Button btnBackListData, studentsWithOri, studentsWithoutOri, oriStatistics;
    Button btnBackSearchOri, searchAll, searchByName, searchByEmail;
    Text fileText;
    TextField textfield;
    Button submitBtn;
    int selectedSearchType = 0;

    public OriAttributionUI(ModelManager model) {
        this.model = model;

        autoOriAttribution = new Button("Atribuição automática de orientadores");
        oriManagement = new Button("Gestão de Orientadores");
        manualOriAttribution = new Button("Atribuição manual de orientadores");
        listData = new Button("Listagem de Dados");
        exportToCSV = new Button("Exportar alunos para um CSV");
        scrollPane = new ScrollPane();
        content = new VBox();
        oriManagementSubmenu = new VBox();
        listDataSubmenu = new VBox();
        searchOriSubmenu = new VBox();

        attrOriBtn = new Button("Atribuir orientadores");
        listOriBtn = new Button("Consultar orientadores");
        changeOriBtn = new Button("Alterar orientadores");
        removeOriBtn = new Button("Eliminar orientadores");
        btnBackOriMgmt = new Button("Voltar");

        studentsWithOri = new Button("Alunos com proposta e orientador");
        studentsWithoutOri = new Button("Alunos com proposta e sem orientador");
        oriStatistics = new Button("Estatísticas sobre os orientadores");
        btnBackListData = new Button("Voltar");

        searchAll = new Button("Todos");
        searchByName = new Button("Procurar por nome");
        searchByEmail = new Button("Procurar por email");
        btnBackSearchOri = new Button("Voltar");

        fileText = new Text();

        textfield = new TextField();
        submitBtn = new Button();

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
        btnBackOriMgmt = new Button("Voltar");

        mainBtns = new VBox(autoOriAttribution, oriManagement, manualOriAttribution, listData, exportToCSV);
        oriManagementSubmenu = new VBox(
                attrOriBtn,
                listOriBtn,
                changeOriBtn,
                removeOriBtn,
                btnBackOriMgmt
        );
        listDataSubmenu = new VBox(
                studentsWithOri,
                studentsWithoutOri,
                oriStatistics,
                btnBackListData
        );
        searchOriSubmenu = new VBox(
                searchAll,
                searchByName,
                searchByEmail,
                btnBackSearchOri
        );
        mainBtns.setSpacing(5);
        oriManagementSubmenu.setSpacing(5);
        listDataSubmenu.setSpacing(5);
        searchOriSubmenu.setSpacing(5);

        for (Node btn : mainBtns.getChildren()) {
            btn.setId("mainMenuBtn");
        }
        for (Node btn : oriManagementSubmenu.getChildren()) {
            btn.setId("mainMenuBtn");
        }
        for (Node btn : listDataSubmenu.getChildren()) {
            btn.setId("mainMenuBtn");
        }
        for (Node btn : searchOriSubmenu.getChildren()) {
            btn.setId("mainMenuBtn");
        }

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
        oriManagementSubmenu.setPadding(new Insets(0, 0, 0, 10));
        listDataSubmenu.setPadding(new Insets(0, 0, 0, 10));
        searchOriSubmenu.setPadding(new Insets(0, 0, 0, 10));

        VBox bottomBox = new VBox(buttons, statusBar);
        bottomBox.setSpacing(10);
        bottomBox.setPadding(new Insets(10, 10, 0, 10));

        //textfield.setId("textField");
        textfield.setPrefSize(320, 25);
        textfield.setMinSize(320, 25);
        textfield.setMaxSize(320, 25);
        textfield.setPadding(new Insets(0, 20, 0, 0));
        //submitBtn.setId("subMenuButton");
        submitBtn.setPrefSize(320, 25);
        submitBtn.setMinSize(320, 25);
        submitBtn.setMaxSize(320, 25);
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
        model.addPropertyChangeListener(ModelManager.PROP_DATA, evt -> {
            update();
        });
        oriManagement.setOnAction(evt -> {
            content.getChildren().clear();
            this.setLeft(oriManagementSubmenu);
            this.setRight(null);
        });
        btnBackOriMgmt.setOnAction(evt -> {
            this.setLeft(mainBtns);
            this.setRight(null);
        });
        listData.setOnAction(evt -> {
            content.getChildren().clear();
            this.setLeft(listDataSubmenu);
            this.setRight(null);
        });
        listOriBtn.setOnAction(evt -> {
            content.getChildren().clear();
            this.setLeft(searchOriSubmenu);
            this.setRight(null);
        });
        btnBackListData.setOnAction(evt -> {
            this.setLeft(mainBtns);
            this.setRight(null);
        });
        searchAll.setOnAction(evt -> {
            content.getChildren().clear();
            for(PoEOrientador ori : model.getOrientadores()) {
                content.getChildren().add(new Card(
                            ori.getDocente().getNome(),
                            ori.getDocente().getEmail(),
                            ori.getPropostas().size() + " orientações"
                ));
            }
            this.setRight(scrollPane);
        });
        searchByName.setOnAction(evt -> {
            content.getChildren().clear();
            textfield.clear();
            textfield.setPromptText("Nome do orientador:");
            submitBtn.setText("Procurar");
            selectedSearchType = 1;
            content.getChildren().addAll(textfield, submitBtn);
            this.setRight(scrollPane);
        });
        searchByEmail.setOnAction(evt -> {
            content.getChildren().clear();
            textfield.clear();
            textfield.setPromptText("Email do orientador:");
            submitBtn.setText("Procurar");
            selectedSearchType = 2;
            content.getChildren().addAll(textfield, submitBtn);
            this.setRight(scrollPane);
        });
        submitBtn.setOnAction(evt -> {
            if(selectedSearchType == 1){
                content.getChildren().clear();
                ArrayList<PoEOrientador> orientadores = model.getOrientadoresByName(textfield.getText());
                if(orientadores.size() != 0){
                    for(PoEOrientador ori : orientadores) {
                        content.getChildren().add(new Card(
                                ori.getDocente().getNome(),
                                ori.getDocente().getEmail(),
                                ori.getPropostas().size() + " orientações"
                        ));
                    }
                }
            }
            else if(selectedSearchType == 2){
                content.getChildren().clear();
                ArrayList<PoEOrientador> orientadores = model.getOrientadoresByEmail(textfield.getText());
                if(orientadores.size() != 0){
                    for(PoEOrientador ori : orientadores) {
                        content.getChildren().add(new Card(
                                ori.getDocente().getNome(),
                                ori.getDocente().getEmail(),
                                ori.getPropostas().size() + " orientações"
                        ));
                    }
                }
            }
        });
        btnBackSearchOri.setOnAction(evt -> {
            this.setLeft(oriManagementSubmenu);
            this.setRight(null);
        });
        studentsWithOri.setOnAction(evt -> {
            content.getChildren().clear();
            for(PoEAluno aluno : model.getAlunos()){
                if(aluno.getPropostaAtribuida() != null){
                    if(aluno.getPropostaAtribuida().getOrientador() != null){
                        content.getChildren().add(new Card(
                                aluno.getNome(),
                                aluno.getPropostaAtribuida().getTitulo(),
                                aluno.getPropostaAtribuida().getOrientador().getDocente().getNome()
                        ));
                    }
                }
            }
            this.setRight(scrollPane);
        });
        studentsWithoutOri.setOnAction(evt -> {
            content.getChildren().clear();
            for(PoEAluno aluno : model.getAlunos()){
                if(aluno.getPropostaAtribuida() != null){
                    if(aluno.getPropostaAtribuida().getOrientador() == null){
                        content.getChildren().add(new Card(
                                aluno.getNome(),
                                aluno.getPropostaAtribuida().getTitulo(),
                                "Sem orientador atribuído"
                        ));
                    }
                }
            }
            this.setRight(scrollPane);
        });
        oriStatistics.setOnAction(evt -> {
            content.getChildren().clear();
            for (PoEOrientador orientador : model.getOrientadores()) {
                StringBuilder propostasAtribuidas = new StringBuilder();
                for(PoEProposta proposta : orientador.getPropostas()){
                    propostasAtribuidas.append(proposta.getId()).append(", ");
                }
                if(!propostasAtribuidas.isEmpty()){
                    propostasAtribuidas = new StringBuilder(propostasAtribuidas.substring(0, propostasAtribuidas.length() - 2));
                }
                content.getChildren().add(new Card(
                                    orientador.getDocente().getNome(),
                                    propostasAtribuidas.toString(),
                            orientador.getPropostas().size() + " orientações"
                ));
            }
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
    }

    /**
     * Método que atualiza a interface gráfica
     */
    private void update() {
        this.setVisible(model != null && model.getState() == PoEState.ORI_ATTRIBUTION);
    }
}
