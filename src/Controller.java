

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Controller {

    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    Random r = new Random();

    Bullet TempBullet;
    Enemy TempEnemy;

    boolean key=false;

    Game game;
    Textures tex;
    Player player;

    public Controller(Game game,Textures tex, Player player){
        this.game = game;
        this.tex = tex;
        this.player = player;
    }

    public void createEnemy(int enemy_count){
        for(int i = 0; i < enemy_count ; i++){
            addEnemy(new Enemy(r.nextInt(640-82),1000,tex));

        }
    }

    public void tick(){
        for(int i = 0 ; i < bullets.size(); i++){
            TempBullet = bullets.get(i);

            if(TempBullet.getY() > 640){
                removeBullet(TempBullet);
            }
            TempBullet.tick();
        }
        for(int i = 0 ; i < enemies.size(); i++){
            TempEnemy = enemies.get(i);
            TempEnemy.tick();
        }

    }

    public void render(Graphics g){

        if(key){
            createEnemy(game.getEnemy_count());
            key=false;
        }
        int j = 0;
        int i=0;
      while(j < enemies.size()){
          if(player.isDead(enemies.get(j).getX(),enemies.get(j).getY(),enemies.get(j).getHEIGHT(),enemies.get(j).getWIDTH())){
                    player.setDEAD(true);

                    i=0;
                    while(i < enemies.size()){
                        removeEnemy(enemies.get(i));
                    }
                    i =0;
                     while(i < bullets.size()){
                          removeBullet(bullets.get(i));
                     }
                    game.setEnemy_count(game.getDefaultEnemyCount());
                    createEnemy(game.getEnemy_count());

                    if(Game.sound)
                        game.loseSound.sound();

                }
          j++;
       }
        j=0;
        while(j < bullets.size()){
            i = 0;
            while(i < enemies.size() && j < bullets.size()){
                if(enemies.get(i).isCollide(bullets.get(j).getX()+ (bullets.get(j).getWIDTH()/2) ,bullets.get(j).getY()+bullets.get(j).getHEIGHT()*2,bullets.get(j).getWIDTH(),bullets.get(j).getHEIGHT()) ){
                    removeEnemy(enemies.get(i));
                    removeBullet(bullets.get(j));

                    game.setScore(game.getScore()+1);

                    if(Game.sound)
                        game.vupSound.sound();

                    game.setEnemy_killed(game.getEnemy_killed()+1);

                    if(game.getEnemy_count() == game.getEnemy_killed()){
                        i=0;
                        while(i < bullets.size()){
                            removeBullet(bullets.get(i));
                            i++;
                        }
                        Game.State = Game.STATE.LEVEL;
                        game.setEnemy_killed(0);
                        key=true;

                    }
                }
                else {
                    i++;
                }
            }
            j++;
        }

        for(i = 0 ; i < bullets.size(); i++){
            TempBullet = bullets.get(i);

            TempBullet.render(g);
        }

        for(i = 0 ; i < enemies.size(); i++){
            TempEnemy = enemies.get(i);

            TempEnemy.render(g);
        }

    }

    public void addBullet(Bullet block){
        bullets.add(block);
        if(Game.sound)
            game.fireSound.sound();
    }

    public void removeBullet(Bullet block){
        bullets.remove(block);
    }

    public void addEnemy(Enemy block){
        enemies.add(block);
    }

    public void removeEnemy(Enemy block){
        enemies.remove(block);
    }
}
