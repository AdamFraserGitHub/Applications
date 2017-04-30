package city_gen;

import java.util.Scanner;

public class Init {

    static int maxCityRadius, maxSuperBlocks, maxCentralNodes = 0;
    static Scanner scanner;
    
    public static void main(String[] args) {
        getUserValues();
        
        BlockMapGen bmg = new BlockMapGen(maxSuperBlocks);
        
        SuperBlockMapGen superMapGen = new SuperBlockMapGen(maxCityRadius, maxSuperBlocks, maxCentralNodes);
        Display display = new Display(800, 800, "City Gen");
        GFX gfx = new GFX(display, 800, 800);
        
        RenderLoop renderLoop = new RenderLoop();
        renderLoop.start();
    }
    
    public static void getUserValues() {
        scanner = new Scanner(System.in);
        getMaxCityRadius();
        getMaxSuperBlocks();
        getMaxCentralNodes();
    }
    
    public static void getMaxCityRadius() {
        System.out.println("enter the maximum radius of the city in super-blocks (integer)");
        maxCityRadius = scanner.nextInt();
    }
    
    public static void getMaxSuperBlocks() {
        int maxPossible = (int) (Math.PI * (maxCityRadius * maxCityRadius));
        System.out.println("enter the maximum number of super-blocks the city should have (integer) \n"
                           + "this should be less than " + maxPossible + " becasue that is the area of your city in super-blocks \n"
                           + "a good target would be 40%(" + (int) (maxPossible*0.4) + ")");
        maxSuperBlocks = scanner.nextInt();
    }
    
    public static void getMaxCentralNodes() {
        System.out.println("enter the maximum number of generation centers you would like to have (integer) \n"
                           + "generally anything over 4 is excessive. \n"
                           + "if you want there to be one city center then enter -1.");
        while(maxCentralNodes == 0) {
            maxCentralNodes = scanner.nextInt();
        }
    }
    
}
