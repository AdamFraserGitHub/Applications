package city_gen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Arrays;
import java.util.Random;

public class GFX {
    private static BufferStrategy bs;
    private static Graphics g;
    private static Display display;
    private static int width, height;
    private static float cityCentre, superBlockPixelLength, unitPixelLength;
    private static boolean[][] superBlockMap, parentCandidate;
    private static int[][] candidate;
    private static int newBlockID;
    int candidateID;
    Random random = new Random();
    
    public GFX(Display display, int width, int height) {
        this.display = display;
        this.width = width;
        this.height = height;
        
        cityCentre = width/8 + ((width/2 + width/4)/2);
        superBlockPixelLength = ((height/2 + height/4)/2) / Init.maxCityRadius; 
        unitPixelLength = superBlockPixelLength/10;
        superBlockMap = new boolean[2*Init.maxCityRadius][2*Init.maxCityRadius];
        parentCandidate = new boolean[2*Init.maxCityRadius][2*Init.maxCityRadius];
        candidate = new int[2*Init.maxCityRadius][2*Init.maxCityRadius];
        
        superBlockMap[Init.maxCityRadius][Init.maxCityRadius] = true;
        
        for (int k = 0; k < Init.maxSuperBlocks; k++){
        //this loop cycles through all positions and finds which where there are blocks which are unsurrounded
        for(int i = 0; i < 2*Init.maxCityRadius; i++) {
            for(int j = 0; j < 2*Init.maxCityRadius; j++) {
                
                if(j < 2*Init.maxCityRadius - 1){ 
                    if(superBlockMap[i][j] && !superBlockMap[i][j+1]) {
                        parentCandidate[i][j] = true;
                    }
                }
                    
                if(j > 0){
                    if(superBlockMap[i][j] && !superBlockMap[i][j-1]) {
                        parentCandidate[i][j] = true;
                    }
                }
                
                if (i < 2*Init.maxCityRadius - 1){
                    if(superBlockMap[i][j] && !superBlockMap[i+1][j]) {
                        parentCandidate[i][j] = true;
                    }
                }
                
                if(i > 0){
                    if(superBlockMap[i][j] && !superBlockMap[i-1][j]) {
                        parentCandidate[i][j] = true;
                    }
                }   
            }
        }
        
        candidateID = 0;
        
        for(int i = 0; i < 2*Init.maxCityRadius; i++) {
            for(int j = 0; j < 2*Init.maxCityRadius; j++) {
                
                if(j < 2*Init.maxCityRadius - 1) {
                    if(parentCandidate[i][j] && !superBlockMap[i][j+1]) {
                        candidateID++;
                        candidate[i][j+1] = candidateID;
                    }
                }
                
                if(j > 0){
                    if(parentCandidate[i][j] && !superBlockMap[i][j-1]) {
                        candidateID++;
                        candidate[i][j-1] = candidateID;
                    }
                }
                
                if(i < 2*Init.maxCityRadius - 1) {
                    if(parentCandidate[i][j] && !superBlockMap[i+1][j]) {
                        candidateID++;
                        candidate[i+1][j] = candidateID;
                    }
                }
                
                if(i > 0) {
                    if(parentCandidate[i][j] && !superBlockMap[i-1][j]) {
                        candidateID++;
                        candidate[i-1][j] = candidateID;
                    }
                }
            }
        }
        
        if(candidateID != 0){
            newBlockID = random.nextInt(candidateID) + 1;
        } else {
            newBlockID = 0;
        }
        System.out.println("newBlockID:" + newBlockID);
       
        for(int i = 0; i < 2*Init.maxCityRadius; i++) {
            for(int j = 0; j < 2*Init.maxCityRadius; j++) {
                if(candidate[i][j] == newBlockID) {
                    
                    System.out.println("candidateID:" + candidate[i][j] + "\nNEwBlockID: " + newBlockID);
                    System.out.println("X: " + i + "\nY:" + j + "\n");
                    superBlockMap[i][j] = true;
                }
            }
        }
        }
       
    }
    
    public static void render() {
        
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3); //creates buffer strategy 3 deep
            return;
        }
        g = bs.getDrawGraphics();
        
        g.setColor(Color.magenta);
        g.drawOval(height/8, width/8, height/2 + height/4, width/2 + width/4);
        
        g.setColor(Color.red);
//        g.drawRect((int) (cityCentre - (superBlockPixelLength / 2)),(int) (cityCentre - (superBlockPixelLength / 2)), (int) superBlockPixelLength, (int) superBlockPixelLength);
        
        float XPos = width/8;
        float YPos = height/8;
        
        for(int i = 0; i < 2*Init.maxCityRadius; i++) {
            for(int j = 0; j < 2*Init.maxCityRadius; j++) {
                if(superBlockMap[i][j]) {
                    g.drawRect((int) (XPos - superBlockPixelLength/2), (int) (YPos - superBlockPixelLength/2), (int) superBlockPixelLength, (int) superBlockPixelLength);
                    
                }
                XPos += superBlockPixelLength;
            }
            YPos += superBlockPixelLength;
            XPos = width/8;
        }
//        g.drawRect((int) (newBlockXIndex * superBlockPixelLength), (int) (newBlockYIndex * superBlockPixelLength), (int) superBlockPixelLength, (int) superBlockPixelLength);
        bs.show();
        g.dispose();
    }
}
