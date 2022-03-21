

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {

    private final int WIDTH=64,HEIGHT=83;

    private double x;
    private double y;
    private boolean dead = false;

    private double velX = 0;
    private double velY = 0;

    private BufferedImage player;
    private Textures tex;

    Animation animsag;
    Animation animsol;
    Game game;

    public Player(Game game,double x,double y , Textures tex){
        this.x = x;
        this.y = y;
        this.tex = tex;
        this.game = game;

        animsag = new Animation(250, tex.playersag);
        animsol = new Animation(250, tex.playersol);

    }
    public boolean isDead(double x, double y,int height,int width){
        if((y < this.y+HEIGHT && y > this.y && x < this.x + WIDTH && x > this.x) || (y+height < this.y+HEIGHT && y+height > this.y &&
        x+width < this.x + WIDTH && x+width > this.x)
        || (y+height/2 < this.y+HEIGHT && y+height/2 > this.y &&
                x+width/2 < this.x + WIDTH && x+width/2 > this.x))
            return true;

        return false;
    }
    public void tick(){
        x+= velX;
        y+= velY;

        if(x <= 0)
            x=0;
        if(x >= 640-WIDTH)
            x = 640-WIDTH;
        if(game.getCurrentDirection())
        animsag.tick();

        if(!game.getCurrentDirection())
            animsol.tick();


        if(y<= 0)
            y=0;
        if(y>=800-HEIGHT)
            y=800-HEIGHT;
    }

    public void render(Graphics g){
        if(!game.getCurrentDirection()) {
            g.drawImage(animsol.getCurrentFrame(), (int) x, (int) y, WIDTH, HEIGHT, null);
        }
        if(game.getCurrentDirection()) {
            g.drawImage(animsag.getCurrentFrame(), (int) x, (int) y, WIDTH, HEIGHT, null);
        }
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public void setX(double x){
       this.x = x;
    }

    public void setDEAD(boolean b){
        this.dead = b;
    }

    public boolean getDEAD(){
        return dead;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setVelX(double velX){
        this.velX = velX;
    }

    public void setVelY(double velXY){
        this.velY = velY;
    }

    public void setTex(Textures tex) {
        this.tex = tex;
    }
}
