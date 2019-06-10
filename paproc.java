import javax.swing.*;
import java.awt.*;

public class paproc extends JFrame {
    Graphics gr;
    int max_step = 1000000;
    float x, y;
    double r;
    public paproc(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(10, 10, 650, 650);
        this.setVisible(true);
        gr = this.getContentPane().getGraphics();
        malujPaproc();
    }
    void malujPaproc(){
        for(int n =0; n < max_step; n++){
            r = Math.random();
            if(r <= 0.01){
                x = 0;
                y = 0.16f*y;
            }else if(r <= 0.08){
                x = 0.2f*x-0.26f*y;
                y = 0.23f*x + 0.22f*y+1.2f;
            }else if(r <= 0.15){
                x = 0.15f*x + 0.28f*y;
                y = 0.26f*x + 0.24f*y+0.44f;
            }else {
                x = 0.85f*x + 0.04f*y;
                y = 0.04f*x + 0.85f*y + 1.6f;
            }
            gr.drawLine((int)(x*100)+220, 600 - (int)(y*60), (int)(x*100) + 220, 600 - (int)(y*60));
        }
    }
    public static void main(String[] args){
        new paproc();
    }
}
