package city_gen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class GFX {
    private static BufferStrategy bs;
    private static Graphics g;
    private static Display display;
    private static int width, height;
    private static float superBlockPixelLength;
    Random random = new Random();
    
    public GFX(Display display, int width, int height) {
        this.display = display;
        this.width = width;    
        this.height = height;
        
        superBlockPixelLength = ((height/2 + height/4)/2) / Init.maxCityRadius; 
    }
    
    public static void render() {
        
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3); //creates buffer strategy 3 deep
            return;
        }
        g = bs.getDrawGraphics();
        
        g.setColor(Color.magenta);
        g.drawOval((int) (height/8 - superBlockPixelLength/2), (int) (width/8 - superBlockPixelLength/2), height/2 + height/4, width/2 + width/4);
        
        g.setColor(Color.red);
        
        float XPos = width/8;
        float YPos = height/8;
        for(int i = 0; i < 2*Init.maxCityRadius; i++) {
            for(int j = 0; j < 2*Init.maxCityRadius; j++) {
                if(SuperBlockMapGen.superBlockMap[i][j]) {
                    g.drawRect((int) (XPos - superBlockPixelLength/2), (int) (YPos - superBlockPixelLength/2), (int) superBlockPixelLength, (int) superBlockPixelLength);
                }
                XPos += superBlockPixelLength;
            }
            YPos += superBlockPixelLength;
            XPos = width/8;
        }
        bs.show();
        g.dispose();
    }
}
