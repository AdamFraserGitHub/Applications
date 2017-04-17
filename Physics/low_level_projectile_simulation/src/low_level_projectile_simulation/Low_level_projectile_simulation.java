/*  this is a program to simulate the arc path that a projectile
    will take when fired within an atmosphere.

    it will take into account...
    gravitational field strength (g)
    mass of projectile (m)
    initial velocity of projectile (v)
    drag factor of projectile (d)
        drag factor is calculated using...
        drag coeffiecnt (c)
        cross-sectional area of projectile (a)
        pressure of atmosphere (p)
*/
package low_level_projectile_simulation;
import java.util.Scanner;

public class Low_level_projectile_simulation {
    
    static float mass, x_velocity, y_velocity, area, density, dragCoefficent, gravity, dragFactor;
    static float instantX_velocity, instantY_velocity;
    static int deltaT = 1000; //time between frames in micro-secconds;
    static Scanner userInput = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        getValuesFromUser();
        calculateDragFactor();
        
        test();
    }
    
    //method to get values for neccerary variables (see top of code)
    public static void getValuesFromUser() {
        System.out.println("input mass of projectile in Kg");
        mass = userInput.nextFloat();
        
        System.out.println("input initial x-velocity of projectile in meters per seccond");
        x_velocity = userInput.nextFloat();
        
        System.out.println("input initial y-velocity of projectile in meters per seccond");
        y_velocity = userInput.nextFloat();
        
        System.out.println("input the cross-sectional area of the projectile in meters squared");
        area = userInput.nextFloat();
        
        System.out.println("input the atmospheric density in Kg/m3 (enter -1 for earth sea level: 1.225 kg/m3)");
        density = userInput.nextFloat();
        
        System.out.println("input the drag coefficeint of the projectile");
        dragCoefficent = userInput.nextFloat();
        
        System.out.println("input the gravity of the system in meters per seccond squared (enter -1 for earth: 9.807ms2)");
        gravity = userInput.nextFloat();
        
        if (density == -1){
            density = 1.225f;
        }
        if (gravity == -1) {
            gravity = 9.807f;
        }
        
        instantX_velocity = x_velocity;
        instantY_velocity = y_velocity;
    }
    
    
    //method to calculate the drag factor of the projectile
    public static void calculateDragFactor() {
        dragFactor = (density * dragCoefficent * area) / 2;
    }
    
    public static void test() {
        float f = 
    }
}
