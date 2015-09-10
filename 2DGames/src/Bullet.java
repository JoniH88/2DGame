import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * Created by JoniH on 07.09.2015.
 */
public class Bullet {

    private float           speed;
    private BufferedImage   image;
    private float           bullet[][];
    private int             count = 29;

    Bullet(BufferedImage image, float speed){

        this.speed  = speed;
        this.image  = image;
        bullet = new float[100][3];
        destroy();
    }

    public void add(int x, int y){
        if(count > 20) {
            count = 0;
            for (int i = 0; i < 100; i++) {
                if (bullet[i][0] == 0) {
                    bullet[i][0] = 1;
                    bullet[i][1] = x;
                    bullet[i][2] = y;
                    break;
                }
            }
        }
    }

    public void update(){
        count ++;
        for(int i = 0; i < 100; i ++) {
            if(bullet[i][0] > 0) bullet[i][2] -= speed;
            if(bullet[i][2] < -10) bullet[i][0] = 0;
        }
    }

    public void render(Graphics2D g){

        for(int i = 0; i < 100; i ++) {
            if(bullet[i][0] > 0)
                g.drawImage(image, (int)bullet[i][1], (int) bullet[i][2], (int) (image.getWidth() * 2.0),
                    (int) (image.getHeight() * 2.0), null);
        }
    }

    private void destroy(){
        for(int i = 0; i < 100; i ++){
            bullet[i][0] = 0;
        }
    }
}
