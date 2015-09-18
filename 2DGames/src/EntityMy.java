import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by JoniH on 18.09.2015.
 */
public abstract class EntityMy {

    protected float         speed;
    protected float         x;
    protected float         y;
    protected float         scale;
    protected boolean       activ = false;
    protected BufferedImage image;

    protected void add(int x, int y){

            this.x = x;
            this.y = y;
            activ = true;
    }

    protected void add(int x){

            this.x = x;
            activ = true;
    }

    protected float getX(){return x;}

    protected float getY(){return y;}

    protected float getWidth(){return image.getWidth() * scale;}

    protected float getHeight(){return image.getHeight() * scale;}

    protected void setX(float x){this.x = x;}

    protected void setY(float y){this.y = y;}

    protected abstract void destroy();

    protected abstract void update();

    protected abstract void render(Graphics2D g);
}
