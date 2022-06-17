package pt.isec.pa.apoio_poe.resources;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.util.HashMap;

/**
 * Classe responsável por gerir as imagens.
 */
public class ImageManager {

    private static final HashMap<String, Image> images = new HashMap<>();

    private ImageManager() { }

    /**
     * Obtém uma imagem existente no contexto do package.
     * @param filename Nome do ficheiro da imagem.
     * @return Imagem carregada ou null se ocorrer um erro.
     */
    public static Image getImage(String filename) {

        Image image = images.get(filename);

        if (image == null) {
            try (InputStream is = ImageManager.class.getResourceAsStream("images/" + filename)) {
                image = new Image(is);
                images.put(filename, image);
            } catch (Exception e) {
                return null;
            }
        }

        return image;
    }

    /**
     * Obtém uma imagem existente externamente ao package.
     * @param filename Nome do ficheiro da imagem.
     * @return Imagem carregada ou null se ocorrer um erro.
     */
    public static Image getExternalImage(String filename) {

        Image image = images.get(filename);

        if (image == null) {
            try {
                image = new Image(filename);
                images.put(filename, image);
            } catch (Exception e) {
                return null;
            }
        }

        return image;
    }

    /**
     * Remove a imagem do cache.
     * @param filename Nome do ficheiro da imagem.
     */
    public static void purgeImage(String filename) {
        images.remove(filename);
    }

    /**
     * Cria um ImageView com a imagem.
     * @param fileName Nome do ficheiro da imagem.
     * @param height Altura da imagem.
     * @return ImageView com a imagem.
     */
    public static ImageView getImageView(String fileName, double height){
        ImageView iv = new ImageView(getImage(fileName));
        iv.setPreserveRatio(true);
        iv.setFitHeight(height);
        return iv;
    }
}