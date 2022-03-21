

import java.awt.*;

public class Menu {

    public void render(Graphics g){


        Graphics2D g2d = (Graphics2D) g;

        Color myColor = new Color (75, 49, 76);
        g.setFont(Game.bubbleFont);
        g.setColor(myColor);
        g.drawString("CHICKEN GAME", Game.WIDTH/2-28,150);

        g.setFont(Game.bubbleFont);
        g.drawString("PLAY",Game.WIDTH/2+97,250+35);

        g.drawString("HELP",Game.WIDTH/2 +97,350+35);

        g.drawString("QUIT",Game.WIDTH/2 +105,450+35);


    }
}
