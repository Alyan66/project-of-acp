package treasuregame;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize=16;
    final int scale=3;
    final int tileSize=originalTileSize*scale;
    final int maxCol=16;
    final int maxRow=12;
    final int screenWidth=tileSize*maxCol;
    final int screenHieght=tileSize*maxRow;
    int FPS=60;
    keyHandler kh = new keyHandler();
    Thread gameThread;
    int playerX=100;
    int playerY=100;
    int playerspeed=4;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHieght));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
    }
 public void startGameThread(){
        gameThread=new Thread(this);
        gameThread.start();
 }

    @Override
    public void run() {
        double drawinterval=1000000000/FPS;
        double delta=0;
        long lasttime=System.nanoTime();
        long currenttime;
        long timer=0;
        long drawcount=0;
        while (gameThread!=null){
            currenttime=System.nanoTime();
            delta+=(currenttime-lasttime)/drawinterval;
            timer=(currenttime-lasttime);
            lasttime=currenttime;
            if(delta>=1){
                update();
                repaint();
                delta--;
                drawcount++;
            }
        }

    }
    public void update(){
       if(kh.upPressed==true){
           playerY -=playerspeed;
       } else if (kh.downPressed==true) {
           playerY+=playerspeed;
           
       } else if (kh.leftPressed==true) {
           playerX -=playerspeed;

       } else if (kh.rightPressed==true) {
           playerX+=playerspeed;
       }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }

}
