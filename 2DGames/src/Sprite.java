import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by JoniH on 06.09.2015.
 */
public class Sprite {

    private SpriteSheet sheet;
    private float scale;

    Sprite(SpriteSheet sheet, float scale){
        this.sheet = sheet;
        this.scale = scale;
    }

    public void render(Graphics2D g, float x, float y){

        BufferedImage image = sheet.getSprite(0);
        g.drawImage(image, (int)x, (int)y, (int)(image.getWidth() * scale),
                                        (int)(image.getHeight() * scale), null);
    }
}
