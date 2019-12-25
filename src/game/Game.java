package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame {

    static long tickCount = 0;

    Image blackBeetleDead;
    Image SandTile;
    Image GravelTile;
    Image StartGameButtonGraphic;
    Image EditMapButtonGraphic;
    
    public Music mainMusic;

    public static final int menuScreen = 0;
    public static final int playScreen = 1;
    public static final int modoHistoria = 2;
    public static final int mapSelectScreen = 3;

    public Game(String title) {
        super(title);
        this.addState(new MenuScreen(menuScreen));
        this.addState(new PlayScreen(playScreen));
        this.addState(new modoHistoria(modoHistoria));
        this.addState(new MapSelectScreen(mapSelectScreen));
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        mainMusic = new Music("graficos/Musica/mainMusic1.ogg");
        
        mainMusic.loop();
        
        this.enterState(0);
    }

    public static void main(String[] args) throws SlickException {

        AppGameContainer app = new AppGameContainer(new Game("God vs Titans v.2"));
        
        app.setIcon("graficos/icon.png");
       
        int screen_width = app.getScreenWidth();
        int screen_height = app.getScreenHeight();
        
        //screen_width = 1360;
        //screen_height = 768;
        
        app.setDisplayMode(screen_width * (1 - screen_width/3840) - 200, screen_height * (1 - screen_height / 2160) - 140, false); //es para la imagen
        //Pantalla completa
        //app.setDisplayMode(screen_width, screen_height, true);
        app.setShowFPS(true);
        
        String x = System.getProperty("user.dir");
        System.out.println(x);

        app.setMinimumLogicUpdateInterval(20);
        app.setMaximumLogicUpdateInterval(21);
        app.start();
    }
}

