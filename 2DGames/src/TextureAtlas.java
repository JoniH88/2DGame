import java.awt.image.BufferedImage;

/**
 * Created by JoniH on 06.09.2015.
 */
public class TextureAtlas {

    BufferedImage image;

    TextureAtlas(String imageName){
        image = ResourceLoader.loadImage(imageName);
    }

    public BufferedImage cut(int x, int y, int width, int height){
        return image.getSubimage(x, y, width, height);
    }
}
