package pt.isec.pa.apoio_poe.ui.gui.resources;

import javafx.scene.text.Font;

import java.io.InputStream;

/**
 * Classe responsável por gerir as fontes.
 */
public class FontManager {
    private FontManager() {
    }

    /**
     * Carrega uma fonte a partir de um ficheiro
     * @param filename Nome do ficheiro da fonte.
     * @param size Tamanho da fonte.
     * @return Fonte carregada.
     */
    public static Font loadFont(String filename, int size) {
        try(InputStream inputStreamFont =
                FontManager.class.getResourceAsStream("fonts/" + filename)) {
            return Font.loadFont(inputStreamFont, size);
        } catch (Exception e) {
            return null;
        }
    }
}