package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.model.data.PoEAluno;

/**
 * A classe ActionButtons é uma classe que representa os botões
 * de ação da aplicação gráfica (alteração da fase)
 */
public class StudentCard extends VBox {
    final private PoEAluno aluno;

    public StudentCard(PoEAluno aluno) {
        this.aluno = aluno;

        createViews();
        registerHandlers();
        update();
    }

    /**
     * Método que cria as vistas dos botões de ação
     * Cria os botões para avançar, retroceder e fechar a fase
     */
    private void createViews() {
        Text name = new Text(aluno.getNome());
        Text nrEstudante = new Text(aluno.getNrEstudante().toString());
        this.setStyle("""
                -fx-background-color: #b4b8d9;
                -fx-border-radius: 10;
                -fx-background-radius: 10;
                """);
        this.setAlignment(Pos.CENTER);
        this.setPrefSize(230, 70);
        this.getChildren().addAll(name, nrEstudante);
    }

    /**
     * Método que regista os handlers dos botões de ação
     * Regista os handlers dos botões para avançar, retroceder e fechar a fase
     * Regista os handlers para hover dos botões
     */
    private void registerHandlers() {

    }

    /**
     * Método que atualiza o estado da interface
     * Atualiza o estado dos botões de acordo com o estado do modelo
     */
    private void update() {

    }

}
