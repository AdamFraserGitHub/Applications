package city_gen;

import java.util.Scanner;

public class Init {

    static int maxCityRadius, maxSuperBlocks;
    static Scanner scanner;
    
    public static void main(String[] args) {
        getUserValues();
        
        Display display = new Display(800, 800, "City Gen");
        GFX gfx = new GFX(display, 800, 800);
        
        RenderLoop renderLoop = new RenderLoop();
        renderLoop.start();
    }
    
    public static void getUserValues() {
        scanner = new Scanner(System.in);
        getMaxCityRadius();
        getMaxSuperBlocks();
    }
    
    public static void getMaxCityRadius() {
        System.out.println("enter the maximum radius of the city in super-blocks (integer)");
        maxCityRadius = scanner.nextInt();
    }
    
    public static void getMaxSuperBlocks() {
        int maxPossible = (int) (Math.PI * (maxCityRadius * maxCityRadius));
        System.out.println("enter the maximum number of super-blocks the city should have (integer) \n"
                           + "this should be less than " + maxPossible + " becasue that is the area of your city in super-blocks \n"
                           + "a good target would be 75%");
        maxSuperBlocks = scanner.nextInt();
    }
    
}
