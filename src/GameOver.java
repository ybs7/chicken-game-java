

import java.awt.*;

public class GameOver {
        public void render(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            Color myColor = new Color (75, 49, 76);
            g.setFont(Game.bigBubbleFont);
            g.setColor(myColor);

            FontMetrics fm =g2d.getFontMetrics();
            int x ;

            int tempScore = Game.score*100;

            g.setFont(Game.bigBubbleFont);

            x = fm.stringWidth("GAME OVER");

            g.drawString("GAME OVER",Game.WIDTH-(x/2),125);

            g.setFont(Game.bubbleFont);

            g.drawString("SCORE " +tempScore, Game.WIDTH-150,Game.HEIGHT-175);

            g.drawString("REPLAY",Game.WIDTH/2+65,Game.HEIGHT-50);

            g.drawString("QUIT",Game.WIDTH/2 +105,Game.HEIGHT+50);
        }
}
