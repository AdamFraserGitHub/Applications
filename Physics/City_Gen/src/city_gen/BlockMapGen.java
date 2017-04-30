package city_gen;

import java.util.Random;

public class BlockMapGen {
    
    private Random random = new Random();
    private int[][] blockMapHorizontal, blockMapVertical;
    private int maxSuperBlocks;
            
    public BlockMapGen(int maxSuperBlocks) {
        this.maxSuperBlocks = maxSuperBlocks;
        
        blockMapHorizontal = new int[maxSuperBlocks][5];
        blockMapVertical = new int[maxSuperBlocks][5];
        
        newSuperBlock();
    }
    
    public void newSuperBlock() {
        int blockSize;
        
        for(int i = 0; i < maxSuperBlocks; i++) {
            int remainder = 10;
            int index = 0;
            while (remainder > 2){
                blockSize = random.nextInt(remainder - remainder/2) + 2;
                remainder -= blockSize;
                blockMapHorizontal[i][index] = blockSize;
                index++;
            }
            
            remainder = 10;
            index = 0;
            while (remainder > 2){
                blockSize = random.nextInt(remainder - remainder/2) + 2;
                remainder -= blockSize;
                blockMapVertical[i][index] = blockSize;
                index++;
            }
        }
        
        System.out.println("done");
    }
}
