
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

        if(mx >= Game.WIDTH/2+110 && mx <= Game.WIDTH / 2 +220 && Game.State == Game.STATE.MENU){
            if(my >= 250 && my <= 300){
                //pressed play button
                Game.score=0;
                Game.lvl=1;
                Game.State = Game.STATE.GAME;
                if(Game.sound && !Game.push)
                    Game.bgMusic.play();
            }
        }
        //HELP
        if(mx >= Game.WIDTH/2+110 && mx <= Game.WIDTH / 2 +220 && Game.State == Game.STATE.MENU){
            if(my >= 350 && my <= 400){
                Game.State = Game.STATE.HELP;
            }
        }
        //QUIT
        if(mx >= Game.WIDTH/2+110 && mx <= Game.WIDTH / 2 +220 && Game.State == Game.STATE.MENU){
            if(my >= 450 && my <= 500){
                //pressed quit button
                System.exit(1);
            }
        }
        if(mx >= Game.WIDTH/2+65 && mx <= Game.WIDTH/2+65+195 && Game.State == Game.STATE.GAMEOVER){
            if(my >= Game.HEIGHT-85 && my <= Game.HEIGHT-45){
                //replay button
                Game.score=0;
                Game.lvl=1;
                Game.State = Game.STATE.GAME;
            }
        }
        if(mx >= Game.WIDTH/2 +105 && mx <= Game.WIDTH/2 +105+115 && Game.State == Game.STATE.GAMEOVER){
            if(my >= Game.HEIGHT+15 && my <= Game.HEIGHT+55){
                //quit
                System.exit(1);
            }
        }
        if(mx >= Game.WIDTH/2+100 && mx <= Game.WIDTH / 2 +230 && Game.State == Game.STATE.HELP){
            if(my >= 450 && my <= 490){
                Game.State = Game.STATE.MENU;
            }
        }
        if(mx >= 560 && mx <= 640 && (Game.State == Game.STATE.GAMEOVER || Game.State == Game.STATE.MENU)){
            if(my >= 720 && my <= 800){
                Game.push = true;
                if(Game.sound){
                    Game.sound = false;
                    if(!Game.sound)
                        Game.bgMusic.stop();
                } else{
                    Game.sound = true;
                    if(Game.sound)
                        Game.bgMusic.play();
                }
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
