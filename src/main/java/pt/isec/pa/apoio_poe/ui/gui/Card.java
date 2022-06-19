package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import pt.isec.pa.apoio_poe.model.data.PoEAluno;

/**
 * A classe Card é uma classe que representa uma carta
 * da aplicação gráfica (alteração da fase)
 */
public class Card extends VBox {
    String header, content, footer;

    public Card(String header, String content, String footer){
        this.header = header;
        this.content = content;
        this.footer = footer;
        createViews();
        registerHandlers();
        update();
    }

    /**
     * Método que cria as vistas dos botões de ação
     * Cria os botões para avançar, retroceder e fechar a fase
     */
    private void createViews() {
        Label headerLabel = new Label(header);
        headerLabel.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 20));
        headerLabel.setStyle("-fx-text-fill: #e3e3e3;");

        Label contentLabel = new Label(content);
        contentLabel.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 15));
        contentLabel.setStyle("-fx-text-fill: #adadad;");

        Label propAtribuida = new Label(footer);
        propAtribuida.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        propAtribuida.setStyle("-fx-text-fill: #f3dbff;");

        this.setStyle("""
                -fx-background-color: #424242;
                -fx-border-radius: 10;
                -fx-background-radius: 10;
                """);
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPadding(new Insets(0, 0, 0, 10));
        this.setPrefSize(200, 70);
        this.setMaxSize(300, 70);
        this.setMinSize(170, 70);
        this.getChildren().addAll(headerLabel, contentLabel, propAtribuida);
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
