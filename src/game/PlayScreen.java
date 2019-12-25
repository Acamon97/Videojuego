package game;

import Subdito.Proyectil;
import Subdito.Proyectil.TipoProyectil;
import towers.Towers;
import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import grid.*;
import map.*;
import Subdito.Subdito;
import Subdito.Subdito.direccion;
import Subdito.SubditoGenerator;
import niveles.capitulo;
import org.newdawn.slick.Sound;
import towers.Arrow;
import towers.Sniper;

public class PlayScreen extends BasicGameState {

    private static Queue<Subdito> subditoQueue = new LinkedList<Subdito>();
    private static Queue<Subdito> activeSubditoQueue = new LinkedList<Subdito>();
    private static ArrayList<Towers> towerList = new ArrayList<Towers>();
    private static ArrayList<Proyectil> projectileList = new ArrayList<Proyectil>();
    ArrayList<Proyectil> pToRemove;
    static long tickCount = 0;
    private capitulo nivel;
    private boolean historia;

    private final int mouseClickDelay = 200;
    private long lastClick = (-1 * mouseClickDelay);

    private SpriteSheet tankSpriteSheet;
    Animation tankAnimation;
    private SpriteSheet pathfinderSpriteSheet;
    Animation pathfinderAnimation;
    private SpriteSheet soldierSpriteSheet;
    Animation soldierAnimation;

    Image SandTileGraphic;
    Image GravelTileGraphic;
    Image BrickTileGraphic;
    Image SniperTowerGraphic;
    Image SniperBulletGraphic;

    Image BuyTowerTitleGraphic;
    Image TowerMenuOverlayGraphic;
    Image ExitButtonGraphic;
    Image VolumeOnGraphic;
    Image VolumeOffGraphic;
    Image UpgradeButtonGraphic;
    Image SellButtonGraphic;
    Image TileSelectGraphic;
    Image UpgradeSelectGraphic;
    Image SellSelectGraphic;
    Image CurrencyGraphic;
    Image WaveGraphic;
    Image NextWaveActiveGraphic;
    Image NextWaveNonActiveGraphic;
    Image HeartGraphic;

    Image TowerTileGraphic;
    Image BasicTowerGraphic;
    Image BasicTowerProjectileGraphic;
    Image ComingSoon;
    Image ComingProjectileGraphic;

    Sound shootsoundarrow;
    Sound shootsoundsniper;
    Sound deathsound;
    // Music mainMusic;

    Rectangle ExitButton;
    Rectangle VolumeButton;
    Rectangle NextWaveButton;
    Rectangle SellButton;
    Rectangle UpgradeButton;

    private static Map currentMap;

    private final int towerGraphicYStart = 58;
    private final int towerGraphicXStart = 20;
    private final int towerGraphicYOffset = 79;
    private final int towerGraphicXOffset = 83;
    private final int towerButtonWidth = 66;
    private final int towerButtonHeight = 66;
    private final int maximumNumberTowers = 3;

    private static int selectedTower = -1;

    private ArrayList<Rectangle> TowerGraphicButtonsList;

    private final int startingLevel = 1;
    private final int critterSpawnDelay = 20;
    SubditoGenerator generator;
    private static int currentLevel;
    private static boolean waveIsInProgress;
    private boolean gameOver = false;

    Font font;
    TrueTypeFont ttf;
    Image mapGraphic;
    private boolean complete;

    public PlayScreen(int state) {

    }

    @Override
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException {

        loadImages();
        loadAnimations();
        loadFonts();
        createRectangleButtons(container);

        restartGame();

    }
    
    @Override
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {

        if (historia && currentLevel == 11) {
            complete = true;
        }
        if (waveIsInProgress) {
            if (!subditoQueue.isEmpty()) {
                addCrittersToActiveCritterQueue();
            }
            if (!gameOver && !complete) {
                updateProjectiles();
                updateSubdito();
                targetSubdito();
                attackSubditos();
            }
        }

        if (Mouse.isButtonDown(0)) {
            mouseClicked(Mouse.getX(), container.getHeight() - Mouse.getY(), sbg, container);
        }

        if (Player.getPlayer().getLives() <= 0) {
            gameOver = true;
        }

        if (!gameOver && !complete) {
            tankAnimation.update(delta);
            pathfinderAnimation.update(delta);
            soldierAnimation.update(delta);
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {

        drawMapandOverlay(container, g);
        drawTowers(g);

        if (waveIsInProgress) {
            drawSubditos();
            drawProjectiles();
        }

        if (complete) {
            String s = "CHAPTER COMPLETE";
            if ("mapas/6.Zeus.png".equals(nivel.getMapa())) {
                s = "ENHORABUENA, HAS COMpLETADO EL JUEGO Y DERROTADO A LAS FUERZAS DE LOS TITANES";
            }
            font = new Font("Lithos Pro", Font.PLAIN, 20);
            ttf = new TrueTypeFont(font, true);
            ttf.drawString(20, 60, s, Color.yellow);
            font = new Font("Lithos Pro", Font.PLAIN, 26);
            ttf = new TrueTypeFont(font, true);
        }
        if (gameOver) {
            font = new Font("Lithos Pro", Font.PLAIN, 40);
            ttf = new TrueTypeFont(font, true);
            ttf.drawString(20, 60, "GAME OVER", Color.black);
            font = new Font("Lithos Pro", Font.PLAIN, 26);
            ttf = new TrueTypeFont(font, true);
        }

    }

    private void drawTowers(Graphics g) {
        for (Towers t : towerList) {

            Image img = BasicTowerGraphic;
            switch (t.getTowerType()) {
                case Arrow:
                    img = BasicTowerGraphic;
                    break;
                case Sniper:
                    img = SniperTowerGraphic;
                    break;
                /*case Freeze:
				img = FreezeTowerGraphic;
				break;*/
                default:
                    //do nothing
                    break;

            }

            img.setRotation((float) t.getAngleOfRotation());
            img.drawCentered((float) t.getxPos(), (float) t.getyPos());
            /*for(int count = 1; count < t.getLevel() ;count++){
				g.setColor(Color.blue);
				g.fillOval( (float) (t.getXLoc()- 5 +(count-1)*5), (float) (t.getYLoc())+12, (float) 5.0,(float) 5.0);
				
			}*/
        }
    }

    private void drawProjectiles() {
        for (Proyectil p : projectileList) {
            Image img;
            switch (p.getTipoProy()) {
                case NORMAL:
                    img = BasicTowerProjectileGraphic;
                    break;
                case SNIPER:
                    img = SniperBulletGraphic;
                    break;
                /*case BOMB:
				img = BasicTowerGraphic;
				break;*/
                default:
                    img = BasicTowerProjectileGraphic;
                    break;
            }
            img.setRotation((float) p.angleOfProjectileInDegrees());
            img.drawCentered((float) p.getxLocation(), (float) p.getyLocation());
        }
    }

    public void drawSubditos() {
        for (Subdito s : activeSubditoQueue) {
            if (s.isVisible() && s.isAlive()) {
                drawSubdito(s);

            }
        }

    }

    private void drawSubdito(Subdito s) {
        Animation a;
        int orientationOffset = 0;
        switch (s.getTipoSubdito()) {
            case Soldier:
                a = soldierAnimation;
                orientationOffset = 2;
                break;
            case Pathfinder:
                a = pathfinderAnimation;
                orientationOffset = 2;
                break;
            case Tank:
                a = tankAnimation;
                orientationOffset = 0;
                break;
            default:
                a = tankAnimation;
                break;

        }

        if (s.getDireccionSubdito() == direccion.RIGHT) {
            a.getCurrentFrame().setRotation(90 * ((3 + orientationOffset) % 4));
            a.getCurrentFrame().drawCentered((float) s.getXLoc(), (float) s.getYLoc());
        }
        if (s.getDireccionSubdito() == direccion.LEFT) {
            a.getCurrentFrame().setRotation(90 * ((1 + orientationOffset) % 4));
            a.getCurrentFrame().drawCentered((float) s.getXLoc(), (float) s.getYLoc());

        }
        if (s.getDireccionSubdito() == direccion.DOWN) {
            a.getCurrentFrame().setRotation(90 * ((0 + orientationOffset) % 4));
            a.getCurrentFrame().drawCentered((float) s.getXLoc(), (float) s.getYLoc());
        }

        if (s.getDireccionSubdito() == direccion.UP) {
            a.getCurrentFrame().setRotation(90 * ((2 + orientationOffset) % 4));
            a.getCurrentFrame().drawCentered((float) s.getXLoc(), (float) s.getYLoc());
        }

    }

    private void drawMapandOverlay(GameContainer container, Graphics g) throws SlickException {
        float backgroundScale = (float) container.getWidth() / 3540;
        float imagesScale = (float) container.getWidth() / 3840;
        float graphicsScale = imagesScale + 0.8f;
        float graphicScaleAugment = graphicsScale * 1.04f;
        float graphicScaleAugmentIcons = graphicsScale * 1.2f;
        
        int x = Mouse.getX();
        int y = container.getHeight() - Mouse.getY();
        
                
        //draw the hearts
        for (int h = 0; h < Player.getPlayer().getLives(); h++) {
            HeartGraphic.draw(
                    container.getWidth() / 2 + 30 + h * (5 + HeartGraphic.getWidth()), 
                    container.getHeight() /2,
                    graphicsScale);
        }
        
        //Dibujo de los (buttons)
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
        if (ExitButton.contains(x, y)) {
            ExitButtonGraphic.draw(ExitButton.getMinX() - (ExitButtonGraphic.getWidth() * graphicScaleAugmentIcons - ExitButtonGraphic.getWidth() * graphicsScale) / 2, 
                    ExitButton.getMinY() - (ExitButtonGraphic.getHeight() * graphicScaleAugmentIcons - ExitButtonGraphic.getHeight() * graphicsScale) / 2, 
                    graphicScaleAugmentIcons); 
        } else {
            ExitButtonGraphic.draw(ExitButton.getMinX(), ExitButton.getMinY(), graphicsScale);
        }
        if (NextWaveButton.contains(x, y)) {
            WaveGraphic.draw(NextWaveButton.getMinX() - (WaveGraphic.getWidth() * graphicScaleAugmentIcons - WaveGraphic.getWidth() * graphicsScale) / 2, 
                    NextWaveButton.getMinY() - (WaveGraphic.getHeight() * graphicScaleAugmentIcons - WaveGraphic.getHeight() * graphicsScale) / 2, 
                    graphicScaleAugmentIcons); 
        } else {
            WaveGraphic.draw(NextWaveButton.getMinX(), NextWaveButton.getMinY(), graphicsScale);
        }

        TowerMenuOverlayGraphic.draw(
                mapGraphic.getWidth() * graphicsScale,
                0,
                graphicsScale);
                
        CurrencyGraphic.draw(
                NextWaveButton.getMinX() - CurrencyGraphic.getWidth() - 60, 
                NextWaveButton.getMinY(), 
                graphicsScale);
        
        //cambiar la grafica del (botton) de la Wave
        if (!waveIsInProgress && !gameOver) {
            NextWaveActiveGraphic.draw(currentMap.getWidthInPixel() - WaveGraphic.getWidth(), currentMap.getHeightInPixel() + WaveGraphic.getHeight() + 10);
        } else {
            NextWaveNonActiveGraphic.draw(currentMap.getWidthInPixel() - WaveGraphic.getWidth(), currentMap.getHeightInPixel() + WaveGraphic.getHeight() + 10);
        }

        //dibujar las turres
        for (int i = 0; i < maximumNumberTowers; i++) {
            int xCorner = currentMap.getWidthInPixel() + towerGraphicXStart + ((i) % 2) * towerGraphicXOffset;
            int yCorner = towerGraphicYStart + (i / 2) * towerGraphicYOffset;
            Image img;
            switch (i) {
                case 0:
                    img = BasicTowerGraphic;
                    break;
                case 1:
                    img = SniperTowerGraphic;
                    break;

                default:
                    img = ComingSoon;
                    break;
            }
            img.setRotation(0);
            img.drawCentered(xCorner + towerButtonWidth / 2, yCorner + towerButtonHeight / 2);

        }
        
        mapGraphic.draw(0, 0, graphicsScale);

        
        
        //draw sell and upgrade buttons
        int xCorner = currentMap.getWidthInPixel() + towerGraphicXStart + ((4) % 2) * towerGraphicXOffset;
        int yCorner = towerGraphicYStart + (4 / 2) * towerGraphicYOffset;
        SellButtonGraphic.drawCentered(xCorner + towerButtonWidth / 2, yCorner + towerButtonHeight / 2);
        xCorner = currentMap.getWidthInPixel() + towerGraphicXStart + ((5) % 2) * towerGraphicXOffset;
        yCorner = towerGraphicYStart + (5 / 2) * towerGraphicYOffset;
        UpgradeButtonGraphic.drawCentered(xCorner + towerButtonWidth / 2, yCorner + towerButtonHeight / 2);

        // drawing/updating the currency and level
        ttf.drawString(CurrencyGraphic.getWidth() + 5, (container.getHeight() - 40), "" + Player.getPlayer().getCredits());
        ttf.drawString(currentMap.getWidthInPixel() - 48, currentMap.getHeightInPixel() + 15, currentLevel + "");

        //if the mouse is on the map, snap to map grid
        if (mouseOnMap(Mouse.getX(), container.getHeight() - Mouse.getY())) {
            Image img;
            switch (selectedTower) {
                case -3:
                    img = SellSelectGraphic;
                    break;
                case -2:
                    img = UpgradeSelectGraphic;
                    break;
                case -1:
                    img = TileSelectGraphic;
                    break;
                case 0:
                    img = BasicTowerGraphic;
                    break;
                case 1:
                    img = SniperTowerGraphic;
                    break;

                default:
                    img = TileSelectGraphic;
                    break;
            }
            img.drawCentered(getClosestTileCenter(Mouse.getX()), container.getHeight() - getClosestTileCenter(Mouse.getY()));
            //img.draw(getClosestTileCenter(Mouse.getX()), container.getHeight() - getClosestTileCenter(Mouse.getY()), graphicsScale);
        }
    }

    //for each tower in the tower list, find its closest target
    public void targetSubdito() {
        for (Towers t : towerList) {
            t.setSubdito(null);
            for (Subdito c : activeSubditoQueue) {
                if (c.isAlive() && c.isVisible()) {
                    //calculate distance
                    double xDist = Math.abs(c.getXLoc() - t.getxPos());
                    double yDist = Math.abs(c.getYLoc() - t.getyPos());
                    double dist = Math.sqrt((xDist * xDist) + (yDist * yDist));
                    if (dist < t.getRange() && t.getSubditoTravelDistanceMaximun() < c.getDistanciaRecorrida()) {
                        t.setSubdito(c);
                        t.setSubditoTravelDistanceMaximun(c.getDistanciaRecorrida());

                    }
                }

            }
            t.setSubditoTravelDistanceMaximun(0);
        }
    }

    //for each tower, attack the critter its suppose to attack, if it is able to
    public void attackSubditos() {
        for (Towers t : towerList) {
            if (t.getSubdito() != null && t.canAttack()) {
                attackSubdito(t);

                t.setTimeOfLastAttack(System.currentTimeMillis());
            }
        }
    }

    public void attackSubdito(Towers source) {
        TipoProyectil projType = TipoProyectil.NORMAL;
        switch (source.getTowerType()) {
            case Arrow:
                projType = TipoProyectil.NORMAL;
                shootsoundarrow.play(2, 0.4f);
                break;
            case Sniper:
                projType = TipoProyectil.SNIPER;
                shootsoundsniper.play(1, 0.4f);
                break;
            /*case Freeze:
			projType = projectileType.FREEZE;
			break;*/
            default:
                break;
        }
        Proyectil projectile = new Proyectil(source.getxPos(), source.getyPos(), (double) source.getSubdito().getXLoc(),
                (double) source.getSubdito().getYLoc(), source.getPower(), source.getSubdito(), projType);
        projectileList.add(projectile);
    }

    public void reloadTowers() {
        for (Towers t : towerList) {
            t.setTimeOfLastAttack(0);
        }
    }

    public void loadImages() throws SlickException {
        //initialize all graphics/images from graphics folder
        SandTileGraphic = new Image("graficos/cesped.png");
        GravelTileGraphic = new Image("graficos/ladrillos.png");
        BrickTileGraphic = new Image("graficos/ceramic.png");
        ExitButtonGraphic = new Image("graficos/Icons/exit.png");
        VolumeOnGraphic = new Image("graficos/Icons/volumeOn.png");
        VolumeOffGraphic = new Image("graficos/Icons/volumeOff.png");
        UpgradeButtonGraphic = new Image("graficos/UpgradeButtonGraphic.png");
        SellButtonGraphic = new Image("graficos/SellButtonGraphic.png");
        UpgradeSelectGraphic = new Image("graficos/UpgradeSelectGraphic.png");
        SellSelectGraphic = new Image("graficos/SellSelectGraphic.png");
        CurrencyGraphic = new Image("graficos/CurrencyGraphic.png");
        TileSelectGraphic = new Image("graficos/TileSelectGraphic.png");
        UpgradeSelectGraphic = new Image("graficos/UpgradeSelectGraphic.png");
        SellSelectGraphic = new Image("graficos/SellSelectGraphic.png");
        WaveGraphic = new Image("graficos/WaveGraphic.png");
        NextWaveActiveGraphic = new Image("graficos/NextWaveActive.png");
        NextWaveNonActiveGraphic = new Image("graficos/NextWaveNonActive.png");
        HeartGraphic = new Image("graficos/Heart.png");
        TowerMenuOverlayGraphic = new Image("graficos/TowerMenuGraphic.png");

        BasicTowerGraphic = new Image("graficos/torre_10.png");
        BasicTowerProjectileGraphic = new Image("graficos/SniperTowerProjectileGraphic.png");
        SniperTowerGraphic = new Image("graficos/SniperTower.png");
        SniperBulletGraphic = new Image("graficos/SniProj.png");

        ComingSoon = new Image("graficos/coming_soon.png");

    }

    public void loadAnimations() throws SlickException {
        //create sprite sheets and load them into the animation objects
        //batSpriteSheet = new SpriteSheet("graficos/spritefauno_3.png",100,100, 20);
        pathfinderSpriteSheet = new SpriteSheet("graficos/spritefauno_3.png", 99, 75, 0);
        pathfinderAnimation = new Animation(pathfinderSpriteSheet, 150);
        tankSpriteSheet = new SpriteSheet("graficos/spriteciclope_3.png", 113, 85, 0);
        tankAnimation = new Animation(tankSpriteSheet, 250);
        soldierSpriteSheet = new SpriteSheet("graficos/spritegorgona_3.png", 98, 74, 0);
        soldierAnimation = new Animation(soldierSpriteSheet, 150);
        shootsoundarrow = new Sound("graficos/sounds/Arrow.ogg");

        shootsoundsniper = new Sound("graficos/sounds/Cannon.ogg");

        deathsound = new Sound("graficos/sounds/muerteciclope.ogg");

    }

    public void loadFonts() {
        //create a new font for the credit and level display
        font = new Font("Lithos Pro", Font.PLAIN, 26);
        ttf = new TrueTypeFont(font, true);
    }

    public void createRectangleButtons(GameContainer container) {
        
        float backgroundScale = (float) container.getWidth() / 3840;
        float graphicsScale = backgroundScale + 0.8f;
        
        
        if (currentMap != null) {
            currentMap.setCellSize(mapGraphic.getWidth() * graphicsScale / 20);
        }
        
        //create the nextwave and exit rectangle buttons
        ExitButton = new Rectangle(
                        container.getWidth() - (ExitButtonGraphic.getWidth() + 100) * graphicsScale, 
                        container.getHeight() - (ExitButtonGraphic.getHeight() + 10) * graphicsScale, 
                        ExitButtonGraphic.getWidth() * graphicsScale, ExitButtonGraphic.getHeight() * graphicsScale); 
        VolumeButton = new Rectangle(
                        ExitButton.getMinX() - (VolumeOnGraphic.getWidth() + 20) * graphicsScale, 
                        ExitButton.getMinY(),
                        VolumeOnGraphic.getWidth() * graphicsScale, VolumeOnGraphic.getHeight() * graphicsScale);
        
        NextWaveButton = new Rectangle(
                        container.getWidth() - (WaveGraphic.getWidth() + 40) * graphicsScale, 
                        container.getHeight() - (WaveGraphic.getHeight() + 100) * graphicsScale, 
                        NextWaveActiveGraphic.getWidth() * graphicsScale, NextWaveActiveGraphic.getHeight() * graphicsScale);
        
        
        SellButton = new Rectangle(
                        container.getWidth() / 2 + 10, 
                        TowerMenuOverlayGraphic.getHeight() + 10, 
                        SellButtonGraphic.getWidth(), SellButtonGraphic.getHeight());
        
        UpgradeButton = new Rectangle(container.getWidth() / 2 + 10, TowerMenuOverlayGraphic.getHeight() + UpgradeButtonGraphic.getHeight() + 20, NextWaveActiveGraphic.getWidth(), NextWaveActiveGraphic.getHeight());

        //create tower buttons
        TowerGraphicButtonsList = new ArrayList<Rectangle>();
        for (int i = 0; i < maximumNumberTowers; i++) {
            int xCorner = container.getWidth() / 2 + towerGraphicXStart + ((i) % 2) * towerGraphicXOffset;
            int yCorner = towerGraphicYStart + (i / 2) * towerGraphicYOffset;
            TowerGraphicButtonsList.add(new Rectangle(xCorner, yCorner, towerButtonWidth, towerButtonHeight));

        }
        //sell and upgrade button
        int i = 4;
        int xCorner = container.getWidth() + towerGraphicXStart + ((i) % 2) * towerGraphicXOffset;
        int yCorner = towerGraphicYStart + (i / 2) * towerGraphicYOffset;
        SellButton = new Rectangle(xCorner, yCorner, towerButtonWidth, towerButtonHeight);

        i = 5;
        xCorner = container.getWidth() + towerGraphicXStart + ((i) % 2) * towerGraphicXOffset;
        yCorner = towerGraphicYStart + (i / 2) * towerGraphicYOffset;
        UpgradeButton = new Rectangle(xCorner, yCorner, towerButtonWidth, towerButtonHeight);
        
    }

    public void createCritterQueueforLevel() {
        int[][] locations = currentMap.getCornersList();

        generator = new SubditoGenerator(locations, currentLevel);
        generator.setSubditoStream(nivel.getSubditoStream());
        generator.createSubditoQueue();
        generator.RandomizeSubQueue();
        subditoQueue = generator.getSubditoQueue();
        activeSubditoQueue = new LinkedList<Subdito>();
        activeSubditoQueue.add(subditoQueue.poll());
    }

    public void addCrittersToActiveCritterQueue() {
        tickCount++;
        if (tickCount > critterSpawnDelay) {
            activeSubditoQueue.add(subditoQueue.poll());
            tickCount = 0;
        }
    }

    public void updateSubdito() {
        boolean crittersAreStillVisible = false;
        ArrayList<Subdito> crittersToRemove = new ArrayList<Subdito>();

        for (Subdito s : activeSubditoQueue) {

            if (s.isAlive()) {
                s.move();
            } else {
                Player.getPlayer().addCredits(s.getReward());
                deathsound.play(2, 0.4f);
                crittersToRemove.add(s);
            }
            if (s.isVisible()) {
                crittersAreStillVisible = true;
            }
            if (s.isArrive()) {
                Player.getPlayer().decreaseLife();
                crittersToRemove.add(s);
            }
        }

        for (Subdito s : crittersToRemove) {
            activeSubditoQueue.remove(s);
        }

        if (!crittersAreStillVisible && subditoQueue.size() == 0) {
            waveIsInProgress = false;
            currentLevel++;
        }
    }

    private void updateProjectiles() {

        pToRemove = new ArrayList<Proyectil>();
        for (Proyectil p : projectileList) {
            if (!p.isHit()) {
                p.proyectilMove();
            } else {
                pToRemove.add(p);
            }
        }

        for (Proyectil p : pToRemove) {
            projectileList.remove(p);
        }
    }

    private void mouseClicked(int x, int y, StateBasedGame sbg, GameContainer container) throws SlickException {

        //protection against multiple click registration
        if (lastClick + mouseClickDelay > System.currentTimeMillis()) {
            return;
        }

        lastClick = System.currentTimeMillis();

        if (ExitButton.contains(x, y)) {
            if (complete) {
                modoHistoria s = (modoHistoria) sbg.getState(Game.modoHistoria);
                s.init(container, sbg);
                s.setHistoria(historia);
                s.setCompleto(nivel.getLvl());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

                leave(container, sbg);
                sbg.enterState(Game.modoHistoria);
            } else {
                modoHistoria s = (modoHistoria) sbg.getState(Game.modoHistoria);
                s.init(container, sbg);
                s.setHistoria(historia);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                
                leave(container, sbg);
                sbg.enterState(Game.modoHistoria);
            }
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

        //no towers selected
        if (selectedTower == -1) {

            if (NextWaveButton.contains(x, y) && !waveIsInProgress) {
                waveIsInProgress = true;
                projectileList = new ArrayList<Proyectil>();
                reloadTowers();
                createCritterQueueforLevel();
            }
            if (UpgradeButton.contains(x, y)) {
                selectedTower = -2;
            }
            if (SellButton.contains(x, y)) {
                selectedTower = -3;
            }

            for (int i = 0; i < maximumNumberTowers; i++) {
                if (TowerGraphicButtonsList.get(i).contains(x, y)) {
                    selectedTower = i;

                }
            }
            return;
        } //tower selected
        else if (selectedTower >= 0) {
            if (mouseOnMap(x, y) && canPlaceTowerHere(x, y)) {
                Towers newTower = new Arrow(getClosestTileCenter(x), getClosestTileCenter(y));
                switch (selectedTower) {
                    case 0:
                        //
                        break;
                    case 1:
                        newTower = new Sniper(getClosestTileCenter(x), getClosestTileCenter(y));
                        break;
                    /*case 2:
					newTower= new Bomb(getClosestTileCenter(x),getClosestTileCenter(y));
					break;*/

                }

                towerList.add(newTower);
                Player.getPlayer().addCredits((-1) * newTower.getBuyingCost());

                if (Player.getPlayer().getCredits() < 0) {
                    //deny tower building due to insufficient funds or invalid tile
                    Player.getPlayer().addCredits(newTower.getBuyingCost());
                    towerList.remove(newTower);
                }
            }
        } else if (selectedTower == -2) {
            if (mouseOnMap(x, y)) {
                Towers t = getNearestTower(x, y);
                if (t != null) {
                    t.upgrade();
                }
            }
        }
        if (selectedTower == -3) {
            if (mouseOnMap(x, y)) {
                Towers t = getNearestTower(x, y);
                if (t != null) {
                    t.refundTower();
                    towerList.remove(t);
                }
            }

        }
        selectedTower = -1;
    }

    public boolean mouseOnMap(int x, int y) {
        if (x < (currentMap.getWidthInPixel()) && y < currentMap.getHeightInPixel()) {
            return true;
        } else {
            return false;
        }
    }

    public Towers getNearestTower(int x, int y) {
        double distanceApproximation = 100;
        Towers nearestTower = null;
        for (Towers t : towerList) {
            if (Math.abs(t.getxPos() - x) + Math.abs(t.getyPos() - y) < distanceApproximation) {
                nearestTower = t;
                distanceApproximation = Math.abs(t.getxPos() - x) + Math.abs(t.getyPos() - y);
            }

        }
        return nearestTower;
    }

    public boolean canPlaceTowerHere(float x, float y) {
        int xArrayPos = (int) (getClosestTileCenter(x) - (currentMap.getPixelSize() / 2)) / currentMap.getPixelSize();
        int yArrayPos = (int) (getClosestTileCenter(y) - (currentMap.getPixelSize() / 2)) / currentMap.getPixelSize();
        //check is not a pathTile
        if (currentMap.getTile(xArrayPos, yArrayPos) instanceof PathTile) {
            return false;

        }
        //check there are no tower here
        for (Towers t : towerList) {
            if (getClosestTileCenter((float) t.getxPos()) == getClosestTileCenter(x)
                    && getClosestTileCenter((float) t.getyPos()) == getClosestTileCenter(y)) {
                return false;
            }
        }
        return true;
    }

    public void restartGame() {
        currentLevel = startingLevel;

        Player.getPlayer().reset();

        waveIsInProgress = false;
        subditoQueue = new LinkedList<Subdito>();
        activeSubditoQueue = new LinkedList<Subdito>();
        towerList = new ArrayList<Towers>();
        gameOver = false;

    }

    public void setMap(Map pMap) {
        currentMap = pMap;
    }

    public float getClosestTileCenter(float X) {

        return (float) (Math.floor(X / currentMap.getPixelSize()) * currentMap.getPixelSize() + currentMap.getPixelSize() / 2);
    }

    @Override
    public int getID() {
        return Game.playScreen;
    }

    public void setNivel(capitulo nivel) throws SlickException {
        this.nivel = nivel;
        Player.getPlayer().setLives(nivel.getStartingLives());
        Player.getPlayer().setCredits(nivel.getStartingCoins());
        mapGraphic = new Image(nivel.getMapa());
    }

    public void setHistoria(boolean historia) {
        this.historia = historia;
    }

}
