package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;


public class Boton extends Rectangle {
    
    private Image graphic;
    private float scale;
    private float augmentedScale;
    private int type;
    // 0 = background
    // 1 = imagen
    // 2 = icono

    public Boton(float x, float y, float width, float height, Image graphic, float scale, float augmentedScale, int type) {
        super(x, y, width, height);
        this.graphic = graphic;
        this.scale = scale;
        this.augmentedScale = augmentedScale;
        this.type = type;
    }
       
    public void drawNormal() {
        
    }
    
    public void drawBigger() {
        /*MapEditorButton.getMinX() - (MapEditorButtonGraphic.getWidth() * graphicScaleAugment - MapEditorButtonGraphic.getWidth() * graphicsScale) / 2, 
                    MapEditorButton.getMinY() - (MapEditorButtonGraphic.getHeight() * graphicScaleAugment - MapEditorButtonGraphic.getHeight() * graphicsScale) / 2, 
                    graphicScaleAugment);*/
    }
}
