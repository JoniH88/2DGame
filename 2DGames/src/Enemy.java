import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by JoniH on 09.09.2015.
 */
public class Enemy {

    public boolean          activ   = false;
    public int              level   = 0;
    public BufferedImage    image;
    public float            x       = 0.0f;
    public float            y       = Const.ENEMY_Y_START;
    public float            speed;

    private float           scale = 2.0f;

    public void render(Graphics2D g){

                g.drawImage(image, (int)x, (int)y, (int)(image.getWidth() * scale),
                                    (int)(image.getHeight() * scale), null);
    }

    public void destroy(){
        activ = false;
        y = Const.ENEMY_Y_START;
    }
}
