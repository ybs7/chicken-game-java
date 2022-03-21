

import java.awt.image.BufferedImage;

public class Textures {

    public BufferedImage[] playersag = new BufferedImage[3];
    public BufferedImage[] playersol = new BufferedImage[3];
    public BufferedImage[] enemy = new BufferedImage[8];
    public BufferedImage[] bullet = new BufferedImage[4];

    private SpriteSheet ssCat;
    private SpriteSheet ssChicken;
    private SpriteSheet ssEgg;

    Game game;

    public Textures(Game game){
        ssCat = new SpriteSheet(game.getSpriteSheetCat());
        ssChicken = new SpriteSheet(game.getSpriteSheetChicken());
        ssEgg = new SpriteSheet(game.getSpriteSheetEgg());

        this.game = game;

        getTextures();
    }

    private void getTextures(){

        playersol[0] = ssChicken.grabImage(1, 1, 150, 190);
        playersol[1] = ssChicken.grabImage(2, 1, 150, 190);
        playersol[2] = ssChicken.grabImage(3, 1, 150, 190);

        playersag[0] = ssChicken.grabImage(1, 2, 150, 190);
        playersag[1] = ssChicken.grabImage(2, 2, 150, 190);
        playersag[2] = ssChicken.grabImage(3, 2, 150, 190);

        bullet[0] = ssEgg.grabImage(1,1,601,766);
        bullet[1] = ssEgg.grabImage(2,1,601,766);
        bullet[2] = ssEgg.grabImage(3,1,601,766);
        bullet[3] = ssEgg.grabImage(4,1,601,766);

        enemy[0] = ssCat.grabImage(1,1,300,468);
        enemy[1] = ssCat.grabImage(2,1,300,468);
        enemy[2] = ssCat.grabImage(3,1,300,468);
        enemy[3] = ssCat.grabImage(4,1,300,468);
        enemy[4] = ssCat.grabImage(1,2,300,468);
        enemy[5] = ssCat.grabImage(2,2,300,468);
        enemy[6] = ssCat.grabImage(3,2,300,468);
        enemy[7] = ssCat.grabImage(4,2,300,468);
    }
}
