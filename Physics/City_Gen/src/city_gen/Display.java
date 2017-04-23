package city_gen;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Display {
    
    private static JFrame frame;
    private static Canvas canvas;
    private int width, height;
    private String title;
    
    public Display(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        
        createFrame();
        createCanvas();
    }
    
    public void createFrame() {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public void createCanvas() {
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false); 

        frame.add(canvas);
        frame.pack();
    }
    
    public static Canvas getCanvas() {
        return canvas;
    }
    
    public static JFrame getFrame() {
        return frame;
    }
}
