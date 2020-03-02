
package CLOO;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;

public class Game extends JPanel implements Runnable {
    //frame size
    private static final int WIDTH=600;
    private static final int HEIGHT=600;
    //shapes
    public Shape player = new Shape(0,HEIGHT-100,100,30, Color.green);
    private Shape ball = new Shape(100,100,20,20, Color.green);
    private ArrayList<Shape> blocks = new ArrayList<>();
    //constructor for
    public Game(){
        setVisible(true);
        setBackground(Color.BLACK);
        new Thread(this).start();
        ball.setMovingRight();
    }
    public void update(Graphics window){
        paint(window);
    }
    //draws shapes
    public void paint(Graphics window){
        window.setColor(player.getColor());
        window.fillRect(player.getxPos(),player.getyPos(),player.getWidth(),player.getHeight());
        window.setColor(ball.getColor());
        window.fillOval(ball.getxPos(),ball.getyPos(),ball.getWidth(),ball.getHeight());
        for (Shape block: blocks) {
            window.setColor(block.getColor());
            window.fillRect(block.getxPos(),block.getyPos(),block.getWidth(),block.getHeight());
            window.setColor(Color.BLACK);
            window.drawRect(block.getxPos(),block.getyPos(),block.getWidth(),block.getHeight());
        }
    }
    //methods to access panel's size
    public int getWIDTH(){return WIDTH;}
    public int getHEIGHT(){return HEIGHT;}
    @Override
    public void run() {
        int x=0;
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //moving player
            if (player.isMovingRight()&&player.getxPos()<=WIDTH-player.getWidth()) {
                player.setxPos(player.getxPos()+20);
            }
            if (player.isMovingLeft()&&player.getxPos()>=0) {
                player.setxPos(player.getxPos()-20);
            }
            //moving ball
            if (ball.isMovingDown()){
                ball.setyPos(ball.getyPos()+10);
            }
            if (ball.isMovingUp()){
                ball.setyPos(ball.getyPos()-10);
            }
            if (ball.isMovingRight()){
                ball.setxPos(ball.getxPos()+10);
            }
            if (ball.isMovingLeft()){
                ball.setxPos(ball.getxPos()-10);
            }
            //player hit
            if (player.hitsLeft(ball)){
                ball.setMovingLeft();
            }
            if (player.hitsRight(ball)){
                ball.setMovingRight();
            }
            if (player.hitsTop(ball)){
                ball.setMovingUp();
            }
            if (player.hitsBottom(ball)){
                ball.setMovingDown();
            }
            //block hit
            for (int i=blocks.size()-1;i>=0;i--){
                if ((blocks.get(i)).hitsBottom(ball)) {
                    blocks.remove(i);
                    ball.setMovingDown();
                }
                if ((blocks.get(i)).hitsTop(ball)){
                    blocks.remove(i);
                    ball.setMovingUp();
                }
                if ((blocks.get(i)).hitsLeft(ball)) {
                    blocks.remove(i);
                    ball.setMovingLeft();
                }
                if ((blocks.get(i)).hitsRight(ball)){
                    blocks.remove(i);
                    ball.setMovingRight();
                }
            }
            x++;
            if(x==100){
                x=0;
                for (Shape block: blocks) {
                    block.setyPos(block.getyPos()+HEIGHT/16);
                }
                blocks.add(new Shape(0,0,WIDTH/6,HEIGHT/16, Color.green));
                blocks.add(new Shape(WIDTH/6,0,WIDTH/6,HEIGHT/16, Color.green));
                blocks.add(new Shape(2*WIDTH/6,0,WIDTH/6,HEIGHT/16, Color.green));
                blocks.add(new Shape(3*WIDTH/6,0,WIDTH/6,HEIGHT/16, Color.green));
                blocks.add(new Shape(4*WIDTH/6,0,WIDTH/6,HEIGHT/16, Color.green));
                blocks.add(new Shape(5*WIDTH/6,0,WIDTH/6,HEIGHT/16, Color.green));
            }
            //frame bounds
            if (ball.getyPos()==0){
                ball.setMovingDown();
            }
            if (ball.getyPos()==HEIGHT-2*ball.getHeight()){
                ball.setMovingUp();
            }
            if (ball.getxPos()==0){
                ball.setMovingRight();
            }
            if (ball.getxPos()==WIDTH-ball.getWidth()){
                ball.setMovingLeft();
            }
            repaint();
        } while(true);
    }
}
