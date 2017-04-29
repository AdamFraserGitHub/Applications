package city_gen;

import static java.lang.Math.sqrt;
import java.util.Arrays;
import java.util.Random;

public class SuperBlockMapGen {
    
    public static boolean[][] superBlockMap; 
    private boolean[][] parentCandidate;
    private int[][] candidate;
    private int newBlockID;
    private int candidateID;
    int sumCompleted = 0, maxCityRadius, maxSuperBlocks, maxCentralNodes, subtractFromLoop;
    Random random = new Random();
    
    public SuperBlockMapGen(int maxCityRadius, int maxSuperBlocks, int maxCentralNodes) {
        this.maxCityRadius = maxCityRadius;
        this.maxSuperBlocks = maxSuperBlocks;
        this.maxCentralNodes = maxCentralNodes;
        
        generate();
    }
    
    public void generate() {
        System.out.println("ok");
        float startTime = System.nanoTime();
        superBlockMap = new boolean[2*maxCityRadius][2*maxCityRadius];
        parentCandidate = new boolean[2*maxCityRadius][2*maxCityRadius];
        candidate = new int[2*maxCityRadius][2*maxCityRadius];
        
        if (maxCentralNodes < 0) {
            superBlockMap[maxCityRadius][maxCityRadius] = true;
            subtractFromLoop = 1;
        }else if(maxCentralNodes > 0) {
            for(int i = 0; i < maxCentralNodes; i++){
                superBlockMap[(int) (random.nextInt(maxCityRadius) + 0.25*maxCityRadius)][(int) (random.nextInt(maxCityRadius) + 0.25*maxCityRadius)] = true;
                subtractFromLoop = maxCentralNodes;
            }
        }

        while(sumCompleted < maxSuperBlocks - subtractFromLoop){
            newBlockID = 0;
            candidateID = 0;
            for(int i = 0; i < 2*maxCityRadius; i++){
                Arrays.fill(candidate[i], 0);
            }
            
            for(int i = 0; i < 2*maxCityRadius; i++) {
                for(int j = 0; j < 2*maxCityRadius; j++) {

                    if(j < 2*maxCityRadius - 1){ 
                        if(superBlockMap[i][j] && !superBlockMap[i][j+1]) {
                            parentCandidate[i][j] = true;
                        }
                    }

                    if(j > 0){
                        if(superBlockMap[i][j] && !superBlockMap[i][j-1]) {
                            parentCandidate[i][j] = true;
                        }
                    }

                    if (i < 2*maxCityRadius - 1){
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

            for(int i = 0; i < 2*maxCityRadius; i++) {
                for(int j = 0; j < 2*maxCityRadius; j++) {

                    boolean isIndexInRadius = checkIndexInRadius(i, j);
                    
                    if(j < 2*maxCityRadius - 1 && isIndexInRadius) {
                        if(parentCandidate[i][j] && !superBlockMap[i][j+1]) {
                            candidateID++;
                            candidate[i][j+1] = candidateID;
                        }
                    }

                    if(j > 0 && isIndexInRadius){
                        if(parentCandidate[i][j] && !superBlockMap[i][j-1]) {
                            candidateID++;
                            candidate[i][j-1] = candidateID;
                        }
                    }

                    if(i < 2*maxCityRadius - 1 && isIndexInRadius) {
                        if(parentCandidate[i][j] && !superBlockMap[i+1][j]) {
                            candidateID++;
                            candidate[i+1][j] = candidateID;
                        }
                    }

                    if(i > 0 && isIndexInRadius) {
                        if(parentCandidate[i][j] && !superBlockMap[i-1][j]) {
                            candidateID++;
                            candidate[i-1][j] = candidateID;
                        }
                    }
                }
            }

            if(candidateID != 0){
                newBlockID = random.nextInt(candidateID) + 1;
            }
            
            for(int i = 0; i < 2*maxCityRadius; i++) {
                for(int j = 0; j < 2*maxCityRadius; j++) {
                    if(candidate[i][j] == newBlockID) {
                        superBlockMap[i][j] = true;
                        sumCompleted++;
                    }
                }
            }
        }
        float timeTaken = (System.nanoTime() - startTime)/1000000000;
        System.out.println(timeTaken + "s\n" + System.nanoTime());
    }
    
    public boolean checkIndexInRadius(int i, int j) {
        int xd = i - maxCityRadius;
        int yd = j - maxCityRadius;
        float XdYdsq = ((xd)*(xd)) + ((yd)*(yd)); 
        int d = (int) (sqrt(XdYdsq));
        
        if(d < maxCityRadius - 2) {
            return true;
        } else {
            return false;
        }
    }
}

