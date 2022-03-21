

import java.awt.*;

public class Help {

    public void render(Graphics g) {


        Graphics2D g2d = (Graphics2D) g;

        Color myColor = new Color(75, 49, 76);
        g.setFont(Game.bubbleFont);
        g.setColor(myColor);
        g.drawString("CHICKEN GAME", Game.WIDTH / 2 - 35, 150);

        g.setFont(Game.bubbleFont);
        g.drawString("'A - D' TO MOVE", Game.WIDTH / 2 -58, 250 + 35);

        g.drawString("'SPACE' TO FIRE", Game.WIDTH / 2 -58, 350 + 35);

        g.drawString("BACK", Game.WIDTH / 2 + 105, 450 + 35);
    }
}
