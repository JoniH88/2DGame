import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by JoniH on 05.09.2015.
 */
public class Engine implements Runnable{

    private boolean         running;
    private Thread          engineThread;
    private Display         display;
    private Graphics2D      graphics;
    private Input           input;
    private TextureAtlas    atlas;
    private SpriteSheet     sheet;
    private Sprite          sprite;
    private Bullet          bullet;
    private Ii              ii;

    //temp
    private float   d       = 0;
    private int     step    = 5;
    private int     x       = 375;
    private int     y       = 275;
    //temp end
    Engine(){
        running = false;
        display = new Display(Const.FRAME_WIDTH, Const.FRAME_HEIGHT, Const.FRAME_TITLE,
                                                            Const.CLEAR_COLOR, Const.NUM_BUFFER);
        graphics    = display.getGraphics();
        input       = new Input();
        display.addInputListener(input);

        atlas   = new TextureAtlas(Const.ATLAS_FILE_NAME);
        sheet   = new SpriteSheet(atlas.cut(31, 61, 18, 36), 1, 18);
        sprite  = new Sprite(sheet, 2);
        bullet  = new Bullet(atlas.cut(362, 66, 3, 8), Const.BULLET_SPEED);
        ii      = new Ii(atlas);

    }

    public synchronized void start(){

        if(running) return;

        running         = true;
        engineThread    = new Thread(this);
        engineThread.start();
    }

    public synchronized void stop(){

        if(!running) return;

        running = false;
        try{
            engineThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        clearUp();
    }

    private void update(){

        if(input.getKey(KeyEvent.VK_UP)){
            y -= step;
            if(y < 0) y = Const.FRAME_HEIGHT - 5;
        }
        if(input.getKey(KeyEvent.VK_DOWN)){
            y += step;
        }
        if(input.getKey(KeyEvent.VK_LEFT)){
            x -= step;
        }
        if(input.getKey(KeyEvent.VK_RIGHT)){
            x += step;
        }
        if(input.getKey(KeyEvent.VK_SPACE)){
            bullet.add(x + 12, y - 2);
        }
        bullet.update();
        ii.update();
    }

    private void render(){

        display.clear();
        bullet.render(graphics);
        ii.render(graphics);
        sprite.render(graphics, x, y);
        display.swapBuffers();
    }

    public void run(){

        int fps     = 0;
        int upd     = 0;
        int updl    = 0;

        long count  = 0;

        float delta = 0;
        long lastTime = Time.get();
        while(running){
            long now = Time.get();
            long elapsedTime = now - lastTime;

            count += elapsedTime;

            lastTime = now;
            boolean render = false;
            delta += elapsedTime / Const.UPDATE_INTERVAL;
            while(delta > 1.0){
                update();
                upd ++;
                if(render){
                    updl ++;
                }else{
                    render = true;
                }
                delta --;
            }

            if(render){
                render();
                fps ++;
            }else{
                try{
                    Thread.sleep(Const.IDLE_TIME);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

            if(count >= Const.SECOND){

                display.setTitle(Const.FRAME_TITLE + " || Fps: " + fps + " | Upd: " + upd + " | Updl: " + updl);

                count   = 0;
                fps     = 0;
                upd     = 0;
                updl    = 0;
            }
        }
    }

    private void clearUp(){
        display.destroy();
    }
}