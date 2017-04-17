package city_gen;

public class RenderLoop implements Runnable{

    private Thread thread;
    
    @Override
    public void run() {
        
        try{
        while(true) {
            GFX.render();
            thread.sleep((int) 1000/30);
        }
        } catch(Exception e) {
            System.out.println("RenderLoop: " + e);
        }
    }
    
    public void start() {
        if (thread == null) {
            thread = new Thread (this, "GameThread");
            thread.start();
        }
    }
}
