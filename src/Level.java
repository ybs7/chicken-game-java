

import java.awt.*;

public class Level {

    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        Color myColor = new Color (75, 49, 76);
        g.setFont(Game.bubbleFont);
        g.setColor(myColor);

        g.drawString("CHICKEN GAME", Game.WIDTH/2-28,150);
        g.drawString("PRESS SPACE",Game.WIDTH/2-20,Game.HEIGHT-30);
        g.drawString("TO NEXT LEVEL",Game.WIDTH/2-47,Game.HEIGHT+30);
    }
}
