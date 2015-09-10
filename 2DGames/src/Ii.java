import java.awt.*;
import java.util.Random;

/**
 * Created by JoniH on 09.09.2015.
 */
public class Ii {

    private Enemy[]         enemy;
    private int             count = 0;
    private TextureAtlas    atlas;
    private Random          random = new Random();

    Ii(TextureAtlas atlas){

        this.atlas = atlas;
        enemy = new Enemy[Const.ENEMY_NUM_MAX];
        for(int i = 0; i < Const.ENEMY_NUM_MAX; i ++){
            enemy[i] = new Enemy();
        }
    }

    public void update(){
        count ++;
        for(int i = 0; i < Const.ENEMY_NUM_MAX; i ++){
            if(enemy[i].activ){
                enemy[i].y += enemy[i].speed;
                if(enemy[i].y > Const.FRAME_HEIGHT) enemy[i].destroy();
            }
        }
        if(count > 30){add(); count = 0;}
    }

    public void add(){

            for (int i = 0; i < Const.ENEMY_NUM_MAX; i++) {
                if (!enemy[i].activ) {

                    enemy[i].activ  = true;
                    enemy[i].x      = random.nextInt(Const.FRAME_WIDTH - 20);
                    enemy[i].image  = atlas.cut(8, 108, 18, 18);
                    enemy[i].level  = 1;
                    enemy[i].speed  = Const.ENEMY_SPEED_1;
                    break;
                }
            }
    }

    public void render(Graphics2D g){

        for(int i = 0; i < Const.ENEMY_NUM_MAX; i ++){
            if(enemy[i].activ){
                enemy[i].render(g);
            }
        }
    }
}
