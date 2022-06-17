package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
        name.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 20));
        name.setFill(Color.valueOf("#e3e3e3"));
        Text nrEstudante = new Text(aluno.getNrEstudante().toString());
        nrEstudante.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 15));
        nrEstudante.setFill(Color.valueOf("#adadad"));
        Text propAtribuida = new Text();
        if(aluno.getPropostaAtribuida() == null) {
            propAtribuida.setText("Nenhuma proposta atribuída");
        } else {
            propAtribuida.setText(aluno.getPropostaAtribuida().getTitulo());
        }
        propAtribuida.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        propAtribuida.setFill(Color.valueOf("#f3dbff"));
        this.setStyle("""
                -fx-background-color: #424242;
                -fx-border-radius: 10;
                -fx-background-radius: 10;
                """);
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPadding(new Insets(0, 10, 0, 10));
        this.setPrefSize(230, 70);
        this.setMaxSize(300, 70);
        this.setMinSize(300, 70);
        this.getChildren().addAll(name, nrEstudante, propAtribuida);
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
