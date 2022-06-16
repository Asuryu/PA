package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.model.data.*;

import java.util.jar.Attributes;

/**
 * A classe ActionButtons é uma classe que representa os botões
 * de ação da aplicação gráfica (alteração da fase)
 */
public class PropCard extends VBox {
    final private PoEProposta proposta;

    public PropCard(PoEProposta proposta) {
        this.proposta = proposta;

        createViews();
        registerHandlers();
        update();
    }

    /**
     * Método que cria as vistas dos botões de ação
     * Cria os botões para avançar, retroceder e fechar a fase
     */
    private void createViews() {
        Text name = new Text(proposta.getTitulo());
        name.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 20));
        name.setFill(Color.valueOf("#e3e3e3"));
        Text idProp = new Text(proposta.getId());
        idProp.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 15));
        idProp.setFill(Color.valueOf("#adadad"));
        System.out.println(proposta.getRamosDestino());
        Text ramosDestino = new Text(String.join("  ", proposta.getRamosDestino()));
        ramosDestino.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        ramosDestino.setFill(Color.valueOf("#f3dbff"));
        this.setStyle("""
                -fx-background-color: #424242;
                -fx-border-radius: 10;
                -fx-background-radius: 10;
                """);
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPadding(new Insets(0, 10, 0, 10));
        this.setPrefSize(230, 70);
        this.getChildren().addAll(name, idProp, ramosDestino);
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
