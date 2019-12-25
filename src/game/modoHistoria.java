/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import map.*;
import niveles.*;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.transition.*;

/**
 *
 * @author antonio
 */
public class modoHistoria extends BasicGameState {

    Image SandTile;
    Image BackgroundGraphic;
    Image ExitButtonGraphic;
    Image VolumeOnGraphic;
    Image VolumeOffGraphic;
    Image ModeGraphic;
    
    Image ArtemisaGraphic;
    Image HefestoGraphic;
    Image ApoloGraphic;
    Image PoseidonGraphic;
    Image HadesGraphic;
    Image ZeusGraphic;
    
    Rectangle ExitButton;
    Rectangle VolumeButton;
    Rectangle ModeRectangle;
    
    Rectangle ArtemisaButton;
    Rectangle HefestoButton;
    Rectangle ApoloButton;
    Rectangle PoseidonButton;
    Rectangle HadesButton;
    Rectangle ZeusButton;
    
    Sound enterChapter;
    Sound click2;

    
    private final int mouseClickDelay = 200;
    private long lastClick = (-1 * mouseClickDelay);

    ArrayList<Map> historyArray;
    ArrayList<Rectangle> buttonArray;

    LoadFile loading;
    private boolean historia;
    private int lvl = 5;

    public modoHistoria(int state) {

    }

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
        initializeAndLoadMaps();
        loadContents();
        createRectangleButtons(container);       
    }
    
   
    public void initializeAndLoadMaps() {
        historyArray = new ArrayList<>();
        loading = new LoadFile();        
        historyArray.addAll(loading.getAllMap());
    }
    
    public void loadContents() throws SlickException {
        BackgroundGraphic = new Image("graficos/background.png");
        ExitButtonGraphic = new Image("graficos/Icons/exit.png");
        VolumeOnGraphic = new Image("graficos/Icons/volumeOn.png");
        VolumeOffGraphic = new Image("graficos/Icons/volumeOff.png");
        enterChapter = new Sound("graficos/sounds/enterChapter.ogg");
        click2 = new Sound("graficos/sounds/click2.ogg");
                        
        loadButtonsGraphics();
    }
    
    public void loadButtonsGraphics() throws SlickException {
        if (this.historia) {
            this.ModeGraphic = new Image("graficos/Historia.png");
            if (this.lvl <= 5) {
                ArtemisaGraphic = new Image("graficos/Botones/ArtemisaR.png");
            } else {
                ArtemisaGraphic = new Image("graficos/Botones/ArtemisaA.png");
            }
            if (this.lvl <= 4) {
                HefestoGraphic = new Image("graficos/Botones/HefestoR.png");
            } else {
                HefestoGraphic = new Image("graficos/Botones/HefestoA.png");
            }
            if (this.lvl <= 3) {
                ApoloGraphic = new Image("graficos/Botones/ApoloR.png");
            } else {
                ApoloGraphic = new Image("graficos/Botones/ApoloA.png");
            }
            if (this.lvl <= 2) {
                PoseidonGraphic = new Image("graficos/Botones/PoseidonR.png");
            } else {
                PoseidonGraphic = new Image("graficos/Botones/PoseidonA.png");
            }
            if (this.lvl <= 1) {
                HadesGraphic = new Image("graficos/Botones/HadesR.png");
            } else {
                HadesGraphic = new Image("graficos/Botones/HadesA.png");
            }
            if (this.lvl <= 0) {
                ZeusGraphic = new Image("graficos/Botones/ZeusR.png");
            } else {
                ZeusGraphic = new Image("graficos/Botones/ZeusA.png");
            }   
        }
        else {
            this.ModeGraphic = new Image("graficos/modoArcade.png");
            ArtemisaGraphic = new Image("graficos/Botones/ArtemisaG.png");
            HefestoGraphic = new Image("graficos/Botones/HefestoG.png");            
            ApoloGraphic = new Image("graficos/Botones/ApoloG.png");
            PoseidonGraphic = new Image("graficos/Botones/PoseidonG.png");            
            HadesGraphic = new Image("graficos/Botones/HadesG.png");
            ZeusGraphic = new Image("graficos/Botones/ZeusG.png");
        }
    }
    
    
    public void createRectangleButtons(GameContainer container) {
        
        
        buttonArray = new ArrayList<>();
        buttonArray.add(ArtemisaButton);
        buttonArray.add(HefestoButton);
        buttonArray.add(ApoloButton);
        buttonArray.add(PoseidonButton);
        buttonArray.add(HadesButton);
        buttonArray.add(ZeusButton);
        
                
        float backgroundScale = (float) container.getWidth() / 3840;
        float graphicsScale = backgroundScale + 0.8f;
        
        ModeRectangle = new Rectangle(
                (ModeGraphic.getWidth() / 4 + 20) * graphicsScale, 
                container.getHeight() - (ModeGraphic.getHeight() + 50) * graphicsScale, 
                ModeGraphic.getWidth() * graphicsScale, ModeGraphic.getHeight() * graphicsScale);
        
        ExitButton = new Rectangle(
                container.getWidth() - (ExitButtonGraphic.getWidth() + 100) * graphicsScale, 
                container.getHeight() - (ExitButtonGraphic.getHeight() + 10) * graphicsScale, 
                ExitButtonGraphic.getWidth() * graphicsScale, ExitButtonGraphic.getHeight() * graphicsScale);
        VolumeButton = new Rectangle(
                ExitButton.getMinX() - (VolumeOnGraphic.getWidth() + 20) * graphicsScale, 
                ExitButton.getMinY(),
                VolumeOnGraphic.getWidth() * graphicsScale, VolumeOnGraphic.getHeight() * graphicsScale);
    
        ArtemisaButton = new Rectangle(
                container.getWidth()  - (ArtemisaGraphic.getWidth() + 45) * graphicsScale,
                (ArtemisaGraphic.getHeight() + 16) * graphicsScale,
                ArtemisaGraphic.getWidth() * graphicsScale, ArtemisaGraphic.getHeight() * graphicsScale);
        HefestoButton = new Rectangle(
                container.getWidth()  - (HefestoGraphic.getWidth() + 45) * graphicsScale,
                (HefestoGraphic.getHeight() + 16) * 2 * graphicsScale,
                HefestoGraphic.getWidth() * graphicsScale, HefestoGraphic.getHeight() * graphicsScale);
        ApoloButton = new Rectangle(
                container.getWidth()  - (ApoloGraphic.getWidth() + 45) * graphicsScale,
                (ApoloGraphic.getHeight() + 16) * 3 * graphicsScale,
                ApoloGraphic.getWidth() * graphicsScale, ApoloGraphic.getHeight() * graphicsScale);
        PoseidonButton = new Rectangle(
                container.getWidth()  - (PoseidonGraphic.getWidth() + 45) * graphicsScale,
                (PoseidonGraphic.getHeight() + 16) * 4 * graphicsScale,
                PoseidonGraphic.getWidth() * graphicsScale, PoseidonGraphic.getHeight() * graphicsScale);
        HadesButton = new Rectangle(
                container.getWidth()  - (HadesGraphic.getWidth() + 45) * graphicsScale,
                (HadesGraphic.getHeight() + 16) * 5 * graphicsScale,
                HadesGraphic.getWidth() * graphicsScale, HadesGraphic.getHeight() * graphicsScale);
        ZeusButton = new Rectangle(
                container.getWidth()  - (ZeusGraphic.getWidth() + 45) * graphicsScale,
                (ZeusGraphic.getHeight() + 16) * 6 * graphicsScale,
                ZeusGraphic.getWidth() * graphicsScale, ZeusGraphic.getHeight() * graphicsScale);
    }

    @Override
    public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
        drawGraphics(container);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if (Mouse.isButtonDown(0)) {
            mouseClicked(Mouse.getX(), gc.getHeight() - Mouse.getY(), sbg, gc);
        }
    }

    public void drawGraphics(GameContainer container) {
        float backgroundScale = (float) container.getWidth() / 3540;
        float imagesScale = (float) container.getWidth() / 3840;
        float graphicsScale = imagesScale + 0.8f;
        float graphicScaleAugment = graphicsScale * 1.04f;
        float graphicScaleAugmentIcons = graphicsScale * 1.2f;
        
        int x = Mouse.getX();
        int y = container.getHeight() - Mouse.getY();
        
        
        BackgroundGraphic.draw(((container.getWidth() - BackgroundGraphic.getWidth() * backgroundScale) / 2) + (container.getWidth() / 2 - x) * ((BackgroundGraphic.getWidth() * backgroundScale - container.getWidth())) / container.getWidth() / 2, 
                ((container.getHeight() - BackgroundGraphic.getHeight() * backgroundScale) / 2) + (container.getHeight() / 2 - y) * ((BackgroundGraphic.getHeight() * backgroundScale - container.getHeight())) / container.getHeight() / 2, 
                backgroundScale);
        
        ModeGraphic.draw(ModeRectangle.getMinX(), ModeRectangle.getMinY(), graphicsScale);
        
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
        
        //Estás aqúí para meter el resto de botones crack
        if (ArtemisaButton.contains(x, y)) {
            ArtemisaGraphic.draw(
                ArtemisaButton.getMinX() - (ArtemisaGraphic.getWidth() * graphicScaleAugment - ArtemisaGraphic.getWidth() * graphicsScale) / 2, 
                ArtemisaButton.getMinY() - (ArtemisaGraphic.getHeight() * graphicScaleAugment - ArtemisaGraphic.getHeight() * graphicsScale) / 2, 
                graphicScaleAugment);
        } else {
            ArtemisaGraphic.draw(ArtemisaButton.getMinX(), ArtemisaButton.getMinY(), graphicsScale);
        }
        if (this.lvl <= 4|| !this.historia) {
            if (HefestoButton.contains(x, y)) {
                HefestoGraphic.draw(
                    HefestoButton.getMinX() - (HefestoGraphic.getWidth() * graphicScaleAugment - HefestoGraphic.getWidth() * graphicsScale) / 2, 
                    HefestoButton.getMinY() - (HefestoGraphic.getHeight() * graphicScaleAugment - HefestoGraphic.getHeight() * graphicsScale) / 2, 
                    graphicScaleAugment);
            } else {
                HefestoGraphic.draw(HefestoButton.getMinX(), HefestoButton.getMinY(), graphicsScale);
            }
        }
        if (this.lvl <= 3|| !this.historia) {
            if (ApoloButton.contains(x, y)) {
                ApoloGraphic.draw(
                    ApoloButton.getMinX() - (ApoloGraphic.getWidth() * graphicScaleAugment - ApoloGraphic.getWidth() * graphicsScale) / 2, 
                    ApoloButton.getMinY() - (ApoloGraphic.getHeight() * graphicScaleAugment - ApoloGraphic.getHeight() * graphicsScale) / 2, 
                    graphicScaleAugment);
            } else {
                ApoloGraphic.draw(ApoloButton.getMinX(), ApoloButton.getMinY(), graphicsScale);
            }
        }
        if (this.lvl <= 2|| !this.historia) {
            if (PoseidonButton.contains(x, y)) {
                PoseidonGraphic.draw(
                    PoseidonButton.getMinX() - (PoseidonGraphic.getWidth() * graphicScaleAugment - PoseidonGraphic.getWidth() * graphicsScale) / 2, 
                    PoseidonButton.getMinY() - (PoseidonGraphic.getHeight() * graphicScaleAugment - PoseidonGraphic.getHeight() * graphicsScale) / 2, 
                    graphicScaleAugment);
            } else {
                PoseidonGraphic.draw(PoseidonButton.getMinX(), PoseidonButton.getMinY(), graphicsScale);
            }
        }
        if (this.lvl <= 1|| !this.historia) {
            if (HadesButton.contains(x, y)) {
                HadesGraphic.draw(
                    HadesButton.getMinX() - (HadesGraphic.getWidth() * graphicScaleAugment - HadesGraphic.getWidth() * graphicsScale) / 2, 
                    HadesButton.getMinY() - (HadesGraphic.getHeight() * graphicScaleAugment - HadesGraphic.getHeight() * graphicsScale) / 2, 
                    graphicScaleAugment);
            } else {
                HadesGraphic.draw(HadesButton.getMinX(), HadesButton.getMinY(), graphicsScale);
            }
        }
        if (this.lvl <= 0|| !this.historia) {
            if (ZeusButton.contains(x, y)) {
                ZeusGraphic.draw(
                    ZeusButton.getMinX() - (ZeusGraphic.getWidth() * graphicScaleAugment - ZeusGraphic.getWidth() * graphicsScale) / 2, 
                    ZeusButton.getMinY() - (ZeusGraphic.getHeight() * graphicScaleAugment - ZeusGraphic.getHeight() * graphicsScale) / 2, 
                    graphicScaleAugment);
            } else {
                ZeusGraphic.draw(ZeusButton.getMinX(), ZeusButton.getMinY(), graphicsScale);
            }
        }
    }

    public void mouseClicked(int x, int y, StateBasedGame sbg, GameContainer container) throws SlickException {
        
        //protection against multiple click registration
        if (lastClick + mouseClickDelay > System.currentTimeMillis()) {
            return;
        }
        lastClick = System.currentTimeMillis();
        
        int h;
        if (this.historia){
            h = 1;
        }
        else {
            h = 0;
        }
        
        for (int button = 0; button < (buttonArray.size() - lvl * h); button++) {
            //compare if the click occurred inside one of the rectangle buttons,
            //if it did, load that map, change the frame size to match the map size, and switch states to the PlayScreen state
            if (buttonArray.get(button).contains(x, y)) {

                capitulo cap = new Nivel_1_Artemisa();

                switch (button) {
                    case 0:
                        cap = new Nivel_1_Artemisa();
                        break;
                    case 1:
                        cap = new Nivel_2_Hefesto();
                        break;
                    case 2:
                        cap = new Nivel_3_Apolo();
                        break;
                    case 3:
                        cap = new Nivel_4_Poseidon();
                        break;
                    case 4:
                        cap = new Nivel_5_Hades();
                        break;
                    case 5:
                        cap = new Nivel_6_Zeus();
                        break;
                }                
                enterChapter.play(1, 0.9f);

                PlayScreen s = (PlayScreen) sbg.getState(Game.playScreen);
                s.setMap(historyArray.get(button));
                s.setHistoria(historia);
                System.out.println(cap.getMapa());
                s.setNivel(cap);
                AppGameContainer gameContainer = (AppGameContainer) container;
                s.init(container, sbg);
                
                leave(container, sbg);
                sbg.enterState(Game.playScreen, new FadeOutTransition(), new FadeInTransition());        
            }
            else if (ExitButton.contains(x, y)){
                click2.play(1, 0.6f);
                MenuScreen s = (MenuScreen) sbg.getState(Game.menuScreen);
                s.init(container, sbg);
                
                container.sleep(200);
                
                leave(container, sbg);
                sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
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
        }
    }

    public void setHistoria(boolean historia) {
        this.historia = historia;
    }

    public void setCompleto(int i) {
        this.lvl = 5 - i;
    }
}
