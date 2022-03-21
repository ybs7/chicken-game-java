
import java.awt.*;

public class Bullet {

    private double x;
    private double y;

    private final int WIDTH = 17,HEIGHT = 25;

    private Textures tex;

    Animation anim;

    public Bullet(double x, double y, Textures tex){
        this.x = x;
        this.y = y;
        this.tex = tex;

        anim = new Animation(500,tex.bullet);
    }

    public void tick(){
        y+=3;
        anim.tick();
    }

    public void render(Graphics g){
        g.drawImage(anim.getCurrentFrame(), (int)x+10,(int) y+70,WIDTH,HEIGHT,null);
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }
}
