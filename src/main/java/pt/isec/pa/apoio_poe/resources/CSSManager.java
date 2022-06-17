package pt.isec.pa.apoio_poe.resources;

import javafx.scene.Parent;

/**
 * Classe responsável por gerir o css.
*/
public class CSSManager {
    private CSSManager() { }

    /**
     * Método responsável por aplicar o css a um parent de JavaFX.
     * @param parent Parent que receberá o css.
     * @param filename Nome do ficheiro css.
     */
    public static void applyCSS(Parent parent, String filename) {
        var url = CSSManager.class.getResource("css/" + filename);
        if (url == null){
            return;
        }
        String fileCSS = url.toExternalForm();
        parent.getStylesheets().add(fileCSS);
    }
}

