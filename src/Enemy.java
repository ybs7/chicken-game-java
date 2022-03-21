

import java.awt.*;
import java.util.Random;

public class Enemy {

    private double x,y;

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    private final int HEIGHT=128,WIDTH=82;
    private int speed;

    Random r = new Random();

    private Textures tex;

    Animation anim;

    public Enemy(double x, double y , Textures tex){
        this.x = x;
        this.y = y;
        this.tex = tex;
        this.speed = r.nextInt(3) + 1;

        anim = new Animation(500,tex.enemy);
    }

    public void tick(){
        y-=speed;

        if(y <0){
            y=720;
            x= r.nextInt(480);
            speed = r.nextInt(3) + 1;
        }

        anim.tick();
    }

    public void render(Graphics g){
        g.drawImage(anim.getCurrentFrame(),(int)x,(int)y,82,128,null);

    }

    public boolean isCollide(double x,double y , int height, int width){
        if((y <= this.y+HEIGHT && y >= this.y && x <= this.x+WIDTH && x >= this.x) || ( y+height <= this.y+HEIGHT && y+height>= this.y
        && x+width <= this.x+WIDTH && x+width >= this.x) || ( y+height/2 <= this.y+HEIGHT && y+height/2 >= this.y
                && x+width/2 < this.x+WIDTH && x+width/2 > this.x)){
            return true;
        }
        return false;
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

    public void setY(double y){
       this.y = y;
    }
}
