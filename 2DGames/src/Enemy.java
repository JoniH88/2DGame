import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by JoniH on 09.09.2015.
 */
public class Enemy extends EntityMy{

    private int             level   = 0;

    Enemy(BufferedImage image, int level){
        this.image = image;
        this.level = level;
        x   = 0.0f;
        y   = Const.ENEMY_Y_START;
        speed = setSpeed(level);
        scale = 2.0f;
    }

    @Override
    protected void render(Graphics2D g){
        if(activ)
            g.drawImage(image, (int)x, (int)y, (int)(image.getWidth() * scale), (int)(image.getHeight() * scale), null);
    }

    @Override
    public void destroy(){

        activ   = false;
        y       = Const.ENEMY_Y_START;
    }

    @Override
    public void update(){
        if(activ) {
            y += speed;
            if (y > Const.FRAME_HEIGHT) destroy();
        }
    }

    public float setSpeed(int level){
        if(level == 1) return Const.ENEMY_SPEED_1;
        else if(level == 2) return Const.ENEMY_SPEED_2;
        else if(level == 3) return Const.ENEMY_SPEED_3;
        else return Const.ENEMY_SPEED_0;
    }

    public float getSpeed(){return speed;}

    public boolean getActiv(){return activ;}

    public void setActiv(boolean activ){this.activ = activ;}

    public void setImage(BufferedImage image){this.image = image;}

    public void setLevel(int level){this.level =  level;}
}
