package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.*;
import org.newdawn.slick.Sound;


public class MenuScreen extends BasicGameState {

    Image BackgroundGraphic;
    Image TitleGraphic;
    Image ArcadeGameButtonGraphic;
    Image MapEditorButtonGraphic;
    Image HistoryButtonGraphic;
    Image ExitButtonGraphic;
    Image VolumeOnGraphic;
    Image VolumeOffGraphic;
    
    Rectangle TitleButton;
    Rectangle ArcadeGameButton;
    Boton MapEditorButton;
    Rectangle HistoryButton;
    Rectangle ExitButton;
    Rectangle EditMapButton;
    Rectangle VolumeButton;
    
    Sound click;
   

    private final int mouseClickDelay = 200;
    private long lastClick = (-1 * mouseClickDelay);

    public MenuScreen(int state) {
    }

    @Override
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
        loadContents();
        createRectangleButtons(container);
    }

    @Override
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        if (Mouse.isButtonDown(0)) {
            mouseClicked(Mouse.getX(), container.getHeight() - Mouse.getY(), sbg, container);
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
        drawGraphics(container);
        g.setColor(Color.black);
    }
    

    public void createRectangleButtons(GameContainer container) {
        
        float backgroundScale = (float) container.getWidth() / 3540;
        float imagesScale = (float) container.getWidth() / 3840;
        float graphicsScale = imagesScale + 0.8f;
        float graphicScaleAugment = graphicsScale * 1.04f;
        float graphicScaleAugmentIcons = graphicsScale * 1.2f;
        
        
        TitleButton = new Rectangle(
                (TitleGraphic.getWidth() / 4 + 26) * backgroundScale, 
                container.getHeight() - (TitleGraphic.getHeight() + 200) * backgroundScale, 
                TitleGraphic.getWidth() * backgroundScale, TitleGraphic.getHeight() * backgroundScale);
        HistoryButton = new Rectangle(
                container.getWidth()  - (HistoryButtonGraphic.getWidth() + 16) * graphicsScale,
                container.getHeight() / 2 - HistoryButtonGraphic.getHeight() * graphicsScale,
                HistoryButtonGraphic.getWidth() * graphicsScale, HistoryButtonGraphic.getHeight() * graphicsScale);
        ArcadeGameButton = new Rectangle(
                HistoryButton.getCenterX() - ArcadeGameButtonGraphic.getWidth() / 2 * graphicsScale, 
                HistoryButton.getMaxY() + ArcadeGameButtonGraphic.getHeight() * graphicsScale / 3, 
                ArcadeGameButtonGraphic.getWidth() * graphicsScale, ArcadeGameButtonGraphic.getHeight() * graphicsScale);
        
        MapEditorButton = new Boton(
                ArcadeGameButton.getCenterX() - MapEditorButtonGraphic.getWidth() / 2 * graphicsScale, 
                ArcadeGameButton.getMaxY() + MapEditorButtonGraphic.getHeight() * graphicsScale / 3, 
                MapEditorButtonGraphic.getWidth() * graphicsScale, MapEditorButtonGraphic.getHeight() * graphicsScale,
                ArcadeGameButtonGraphic, graphicsScale, graphicScaleAugment, 1);
        
        /*
        
        MapEditorButton = new Rectangle(
                ArcadeGameButton.getCenterX() - MapEditorButtonGraphic.getWidth() / 2 * graphicsScale, 
                ArcadeGameButton.getMaxY() + MapEditorButtonGraphic.getHeight() * graphicsScale / 3, 
                MapEditorButtonGraphic.getWidth() * graphicsScale, MapEditorButtonGraphic.getHeight() * graphicsScale);
        */
        ExitButton = new Rectangle(
                container.getWidth() - (ExitButtonGraphic.getWidth() + 100) * graphicsScale, 
                container.getHeight() - (ExitButtonGraphic.getHeight() + 10) * graphicsScale, 
                ExitButtonGraphic.getWidth() * graphicsScale, ExitButtonGraphic.getHeight() * graphicsScale);
        VolumeButton = new Rectangle(
                ExitButton.getMinX() - (VolumeOnGraphic.getWidth() + 20) * graphicsScale, 
                ExitButton.getMinY(),
                VolumeOnGraphic.getWidth() * graphicsScale, VolumeOnGraphic.getHeight() * graphicsScale);
    }
    
    public void drawGraphics(GameContainer container) {
        float backgroundScale = (float) container.getWidth() / 3540;
        float imagesScale = (float) container.getWidth() / 3840;
        float graphicsScale = imagesScale + 0.8f;
        float graphicScaleAugment = graphicsScale * 1.04f;
        float graphicScaleAugmentIcons = graphicsScale * 1.2f;
        
        int x = Mouse.getX();
        int y = container.getHeight() - Mouse.getY();
        
        
        BackgroundGraphic.draw(
                ((container.getWidth() - BackgroundGraphic.getWidth() * backgroundScale) / 2) + (container.getWidth() / 2 - x) * ((BackgroundGraphic.getWidth() * backgroundScale - container.getWidth())) / container.getWidth() / 2, 
                ((container.getHeight() - BackgroundGraphic.getHeight() * backgroundScale) / 2) + (container.getHeight() / 2 - y) * ((BackgroundGraphic.getHeight() * backgroundScale - container.getHeight())) / container.getHeight() / 2, 
                backgroundScale);
                
        TitleGraphic.draw(TitleButton.getMinX(), TitleButton.getMinY(), imagesScale);
        
        if (ArcadeGameButton.contains(x, y)) {
            ArcadeGameButtonGraphic.draw(
                    ArcadeGameButton.getMinX() - (ArcadeGameButtonGraphic.getWidth() * graphicScaleAugment - ArcadeGameButtonGraphic.getWidth() * graphicsScale) / 2, 
                    ArcadeGameButton.getMinY() - (ArcadeGameButtonGraphic.getHeight() * graphicScaleAugment - ArcadeGameButtonGraphic.getHeight() * graphicsScale) / 2, 
                    graphicScaleAugment);
        } else {
            ArcadeGameButtonGraphic.draw(ArcadeGameButton.getMinX(), ArcadeGameButton.getMinY(), graphicsScale);
        }   
        /*
        if (MapEditorButton.contains(x, y)) {
            MapEditorButtonGraphic.draw(
                    MapEditorButton.getMinX() - (MapEditorButtonGraphic.getWidth() * graphicScaleAugment - MapEditorButtonGraphic.getWidth() * graphicsScale) / 2, 
                    MapEditorButton.getMinY() - (MapEditorButtonGraphic.getHeight() * graphicScaleAugment - MapEditorButtonGraphic.getHeight() * graphicsScale) / 2, 
                    graphicScaleAugment);
        } else {
            MapEditorButtonGraphic.draw(MapEditorButton.getMinX(), MapEditorButton.getMinY(), graphicsScale);
        } 
        */
        if (HistoryButton.contains(x, y)) {
            HistoryButtonGraphic.draw(
                    HistoryButton.getMinX() - (HistoryButtonGraphic.getWidth() * graphicScaleAugment - HistoryButtonGraphic.getWidth() * graphicsScale) / 2, 
                    HistoryButton.getMinY() - (HistoryButtonGraphic.getHeight() * graphicScaleAugment - HistoryButtonGraphic.getHeight() * graphicsScale) / 2, 
                    graphicScaleAugment);
        } else {
            HistoryButtonGraphic.draw(HistoryButton.getMinX(), HistoryButton.getMinY(), graphicsScale);
        }
        if (ExitButton.contains(x, y)) {
            ExitButtonGraphic.draw(ExitButton.getMinX() - (ExitButtonGraphic.getWidth() * graphicScaleAugmentIcons - ExitButtonGraphic.getWidth() * graphicsScale) / 2, 
                    ExitButton.getMinY() - (ExitButtonGraphic.getHeight() * graphicScaleAugmentIcons - ExitButtonGraphic.getHeight() * graphicsScale) / 2, 
                    graphicScaleAugmentIcons); 
        } else {
            ExitButtonGraphic.draw(ExitButton.getMinX(), ExitButton.getMinY(), graphicsScale);
        }
        if (VolumeButton.contains(x, y)) {
            if (container.isSoundOn()) {
                VolumeOnGraphic.draw(
                        VolumeButton.getMinX() - (VolumeButton.getWidth() * graphicScaleAugmentIcons - VolumeButton.getWidth() * graphicsScale) / 2, 
                        VolumeButton.getMinY() - (VolumeButton.getHeight() * graphicScaleAugmentIcons - VolumeButton.getHeight() * graphicsScale) / 2, 
                        graphicScaleAugmentIcons); 
            } else {
                VolumeOffGraphic.draw(
                        VolumeButton.getMinX() - (VolumeButton.getWidth() * graphicScaleAugmentIcons - VolumeButton.getWidth() * graphicsScale) / 2, 
                        VolumeButton.getMinY() - (VolumeButton.getHeight() * graphicScaleAugmentIcons - VolumeButton.getHeight() * graphicsScale) / 2, 
                        graphicScaleAugmentIcons);
            }
        } else {
            if (container.isSoundOn()) {
                VolumeOnGraphic.draw(VolumeButton.getMinX(), VolumeButton.getMinY(), graphicsScale);
            } else {
                VolumeOffGraphic.draw(VolumeButton.getMinX(), VolumeButton.getMinY(), graphicsScale);
            }
        }
    }

    public void loadContents() throws SlickException {
        BackgroundGraphic = new Image("graficos/background.png");
        TitleGraphic = new Image("graficos/title.png");
        ArcadeGameButtonGraphic = new Image("graficos/modoArcade.png");
        MapEditorButtonGraphic = new Image("graficos/modoArcade.png");
        HistoryButtonGraphic = new Image("graficos/Historia.png");
        ExitButtonGraphic = new Image("graficos/Icons/exit.png");
        VolumeOnGraphic = new Image("graficos/Icons/volumeOn.png");
        VolumeOffGraphic = new Image("graficos/Icons/volumeOff.png");
        click = new Sound("graficos/sounds/click.ogg");
    }
    
    public void mouseClicked(float x, float y, StateBasedGame sbg, GameContainer container) throws SlickException {

        //protection against multiple click registration
        if (lastClick + mouseClickDelay > System.currentTimeMillis()) {
            return;
        }
        lastClick = System.currentTimeMillis();

        if (ArcadeGameButton.contains(x, y)) {
            click.play(1, 0.6f);
            modoHistoria s = (modoHistoria) sbg.getState(Game.modoHistoria);
            s.setHistoria(false);
            s.init(container, sbg);

            container.sleep(200);
            
            leave(container, sbg);
            sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
        }
        else if (HistoryButton.contains(x, y)) {
            click.play(1, 0.6f);
            modoHistoria s = (modoHistoria) sbg.getState(Game.modoHistoria);
            s.setHistoria(true);
            s.init(container, sbg);
            
            container.sleep(200);

            leave(container, sbg);
            sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());        
        } 
        else if (VolumeButton.contains(x, y)) {
            if (container.isSoundOn()) {
                container.setSoundOn(false);
                container.setMusicOn(false);
            }
            else {
                container.setSoundOn(true);
                container.setMusicOn(true);
            }
        }
        else if (ExitButton.contains(x, y)) {
            leave(container, sbg);
            container.exit();
        }

    }

    @Override
    public int getID() { 
        // TODO Auto-generated method stub
        return 0;
    }
}
