package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.CSSManager;

/**
 * A classe ActionButtons é uma classe que representa os botões
 * de ação da aplicação gráfica (alteração da fase)
 */
public class ActionButtons extends HBox {
    final ModelManager model;
    HBox leftBox, rightBox;
    Button btnNext, btnPrev, closePhaseBtn;

    public ActionButtons(ModelManager model) {
        this.model = model;

        createViews();
        registerHandlers();
        update();
    }

    /**
     * Método que cria as vistas dos botões de ação
     * Cria os botões para avançar, retroceder e fechar a fase
     */
    private void createViews() {
        CSSManager.applyCSS(this, "style.css");
        leftBox = new HBox();
        leftBox.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(leftBox, Priority.ALWAYS);

        rightBox = new HBox();
        rightBox.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(rightBox, Priority.ALWAYS);

        btnNext = new Button("Próxima Fase");
        btnNext.setPrefSize(120, 25);
        btnNext.setId("btnNextIn");

        btnPrev = new Button("Fase Anterior");
        btnPrev.setPrefSize(120, 25);
        btnPrev.setId("btnPrevIn");

        closePhaseBtn = new Button("Fechar Fase");
        closePhaseBtn.setPrefSize(120, 25);
        closePhaseBtn.setId("closePhaseBtnIn");

        leftBox.getChildren().addAll(btnPrev);
        rightBox.getChildren().addAll(closePhaseBtn, btnNext);
        rightBox.setSpacing(5);
        this.setPadding(new Insets(7, 0, 0, 0));
        this.getChildren().addAll(leftBox, rightBox);

    }

    /**
     * Método que regista os handlers dos botões de ação
     * Regista os handlers dos botões para avançar, retroceder e fechar a fase
     * Regista os handlers para hover dos botões
     */
    private void registerHandlers() {
        model.addPropertyChangeListener(ModelManager.PROP_STATE, evt -> {
            update();
        });

        btnNext.setOnAction(actionEvent -> {
            model.next();
        });
        btnNext.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_ENTERED, event -> {
            btnNext.setId("btnNextIn");
        });
        btnNext.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_EXITED, event -> {
            btnNext.setId("btnNextOut");
        });
        btnPrev.setOnAction(actionEvent -> {
            model.previous();
        });
        btnPrev.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_ENTERED, event -> {
            btnPrev.setId("btnPrevIn");
        });
        btnPrev.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_EXITED, event -> {
            btnPrev.setId("btnPrevOut");
        });
        closePhaseBtn.setOnAction(actionEvent -> {
            model.close();
        });
        closePhaseBtn.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_ENTERED, event -> {
            closePhaseBtn.setId("closePhaseBtnIn");
        });
        closePhaseBtn.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_EXITED, event -> {
            closePhaseBtn.setId("closePhaseBtnOut");
        });
    }

    /**
     * Método que atualiza o estado da interface
     * Atualiza o estado dos botões de acordo com o estado do modelo
     */
    private void update() {

        closePhaseBtn.setDisable(model.isClosed());

        switch (model.getState()) {
            case CONFIG -> {
                btnNext.setDisable(false);
                btnPrev.setDisable(true);
            }
            case APPLICATION_OPT, PROP_ATTRIBUTION -> {
                btnNext.setDisable(false);
                btnPrev.setDisable(false);
            }
            case ORI_ATTRIBUTION -> {
                btnNext.setDisable(true);
                btnPrev.setDisable(false);
            }
            case REVIEW -> {
                btnNext.setDisable(true);
                btnPrev.setDisable(true);
                closePhaseBtn.setDisable(true);
            }
        }
    }

}
