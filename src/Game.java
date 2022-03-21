//AHMET MUSA ÇATAK - 18290088
//YAHYA BATURAY SARAÇOĞLU - 18290121
//YASİN KÜÇÜKLER - 18290114

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Game extends Canvas implements Runnable{

    //URL fireSoundURL,bgMusicURL,loseSoundURL,vupSoundURL;


    public static final int WIDTH = 320;
    public static final int HEIGHT = 400;
    public static final int SCALE = 2;
    public final String TITLE = "Chicken Game";

    public static Font littleBubbleFont;
    public static Font bubbleFont;
    public static Font bigBubbleFont;

    private boolean running = false;
    private Thread thread;


    public static URL fireSoundURL = Game.class.getResource("fire.wav");
    public static URL bgMusicURL = Game.class.getResource("background.wav");
    public static URL loseSoundURL = Game.class.getResource("lose.wav");
    public static URL vupSoundURL = Game.class.getResource("vup.wav");

    public  static PlayMusic bgMusic = new PlayMusic(bgMusicURL);
    public  static PlayMusic fireSound= new PlayMusic(fireSoundURL);
    public  static PlayMusic loseSound = new PlayMusic(loseSoundURL);
    public  static PlayMusic vupSound = new PlayMusic(vupSoundURL);

    //sound on--of
    public static boolean  sound = true;
    public static boolean  push = false;
    // false sağ -- true sol
    private boolean currentDirection = false;

    private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheetCat = null;
    private BufferedImage spriteSheetEgg = null;
    private BufferedImage spriteSheetChicken = null;
    private BufferedImage background = null;
    public BufferedImage soundOn = null;
    public BufferedImage soundOff = null;

    static final int DEFAULT_ENEMY_COUNT=1;
    static int enemy_count = 1;
    static int enemy_killed = 0;

    public static int getDefaultEnemyCount() {
        return DEFAULT_ENEMY_COUNT;
    }

    long lastShotTime;
    private Player p;
    private Controller controller;
    private Textures tex;
    private Menu menu;
    private Level level;
    private GameOver gameOver;
    private Help help;
    boolean loop = true;

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public static int lvl=1;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static int score=0;

    public static enum STATE{
        MENU,
        LEVEL,
        GAMEOVER,
        HELP,
        GAME
    };

    public static STATE State = STATE.MENU;



    public void init(){
            URL font_path = getClass().getResource("/bubblefont.TTF");

        //FONTS
        try{
            littleBubbleFont = Font.createFont(Font.TRUETYPE_FONT, font_path.openStream()).deriveFont(23f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,font_path.openStream()));
        }catch(IOException | FontFormatException e ){}
        try{
            bubbleFont = Font.createFont(Font.TRUETYPE_FONT, font_path.openStream()).deriveFont(35f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,font_path.openStream()));
        }catch(IOException | FontFormatException e ){}

        try{
            bigBubbleFont = Font.createFont(Font.TRUETYPE_FONT, font_path.openStream()).deriveFont(55f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,font_path.openStream()));
        }catch(IOException | FontFormatException e ){}


        BufferedImageLoader loader = new BufferedImageLoader();
       try{
           spriteSheetCat = loader.loadImage("/cat.png");
           spriteSheetEgg = loader.loadImage("/eggSheet.png");
           spriteSheetChicken = loader.loadImage("/chickenSheet.png");
           soundOff = loader.loadImage("/soundOff.png");
           soundOn = loader.loadImage("/soundOn.png");
           background = loader.loadImage("/betabg.png");

        }catch(IOException e){
           e.printStackTrace();
       }

       this.addKeyListener(new KeyInput(this));
       this.addMouseListener(new MouseInput() );

       tex = new Textures(this);
       p = new Player(this,WIDTH,70,tex);
       controller = new Controller(this,tex,p);
       menu = new Menu();
       level = new Level();
        gameOver = new GameOver();
        help = new Help();

       controller.createEnemy(enemy_count);
    }

    private synchronized void start(){
        if(running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop(){
        try {
            if(!running)
                return;

            running = false;
            thread.join();
            System.exit(1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.exit(1);

    }

    public void run() {
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(running){
            //GAME LOOP
            long now = System.nanoTime();
            delta +=(now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            //chicken dead
            if(p.getDEAD()){
                p.setDEAD(false);
                State = STATE.GAMEOVER;
                setEnemy_killed(0);
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println(updates + " Ticks, Fps " + frames);
                updates = 0;
                frames = 0;

            }
        }
        stop();
    }

    private void tick(){
        if(State == STATE.GAME){
            p.tick();
            controller.tick();
        }
    }

    private void render(){

        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        ////////////////////////////////////

        g.drawImage(image,0,0,getWidth(),getHeight(),this);
        g.drawImage(background,0,0,null);

        if(State == STATE.GAME){
            p.setTex(tex);
            Color myColor = new Color (75, 49, 76);
            g.setFont(Game.littleBubbleFont);
            g.setColor(myColor);
            int tempScore = score*100;
            g.drawString("SCORE " + tempScore, Game.WIDTH-305,35);

            g.drawString("LEVEL " + lvl, Game.WIDTH*2-170,35);

            p.render(g);
            controller.render(g);
        }else if(State== STATE.MENU){
            if(sound)
                g.drawImage(soundOn,560,720,64,64,null);
            else if(!sound)
                g.drawImage(soundOff,560,720,64,64,null);

            menu.render(g);

        }else if(State == STATE.LEVEL){
            level.render(g);
        }else if(State == STATE.GAMEOVER){
            if(sound)
                g.drawImage(soundOn,560,720,64,64,null);
            else if(!sound) {
                g.drawImage(soundOff, 560, 720, 64, 64, null);
                bgMusic.stop();
            }
            gameOver.render(g);
        }else if(State == STATE.HELP){
            help.render(g);
        }
        ////////////////////////////////////
        g.dispose();
        bs.show();
    }
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(State == STATE.GAME){
            if(key == KeyEvent.VK_A){
                p.setVelX(-3);
                setCurrentDirection(true);
            }
            if(key == KeyEvent.VK_D){
                p.setVelX(3);
                setCurrentDirection(false);
            }
            if(key == KeyEvent.VK_SPACE && (System.currentTimeMillis() - lastShotTime >= 600)){
                controller.addBullet(new Bullet(p.getX(), p.getY(),tex));
                lastShotTime = System.currentTimeMillis();
            }
        }
        if(key == KeyEvent.VK_SPACE && State == STATE.LEVEL){
            setEnemy_count(getEnemy_count()+3);
            State = STATE.GAME;
            lvl+=1;
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_A){
            p.setVelX(0);
        }
        if(key == KeyEvent.VK_D){
            p.setVelX(0);
        }
        if(key == KeyEvent.VK_SPACE){
        }
    }

    public int getEnemy_count() {
        return enemy_count;
    }

    public void setEnemy_count(int enemy_count) {
        this.enemy_count = enemy_count;
    }

    public int getEnemy_killed() {
        return enemy_killed;
    }

    public void setEnemy_killed(int enemy_killed) {
        this.enemy_killed = enemy_killed;
    }

    public static void main(String args[]){
        Game game = new Game();

        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE,HEIGHT * SCALE));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        game.start();

    }
    public BufferedImage getSpriteSheetCat(){
        return spriteSheetCat;
    }

    public BufferedImage getSpriteSheetEgg(){
        return spriteSheetEgg;
    }

    public BufferedImage getSpriteSheetChicken(){
        return spriteSheetChicken;
    }

    public boolean getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(boolean currentDirection) {
        this.currentDirection = currentDirection;
    }


}