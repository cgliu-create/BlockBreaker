package CLOO;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;

public class Game extends JPanel implements Runnable {
    //shapes
    public Shape player = new Shape(0,330,60,15, Color.green);
    private Shape ball = new Shape(100,100,20,20, Color.green);
    private ArrayList<Shape> blocks = new ArrayList<>();
    //frame size
    private static final int WIDTH=400;
    private static final int HEIGHT=400;
    //constructor for
    public Game(){
        setVisible(true);
        setBackground(Color.BLACK);
        new Thread(this).start();
        ball.setMovingRight();
        blocks.add(new Shape(0,0,WIDTH/6,HEIGHT/16, Color.green));
        blocks.add(new Shape(WIDTH/6,0,WIDTH/6,HEIGHT/16, Color.green));
        blocks.add(new Shape(2*WIDTH/6,0,WIDTH/6,HEIGHT/16, Color.green));
        blocks.add(new Shape(3*WIDTH/6,0,WIDTH/6,HEIGHT/16, Color.green));
        blocks.add(new Shape(4*WIDTH/6,0,WIDTH/6,HEIGHT/16, Color.green));
        blocks.add(new Shape(5*WIDTH/6,0,WIDTH/6,HEIGHT/16, Color.green));
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
            if (player.isMovingRight()&&player.getxPos()<=400-player.getWidth()) {
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
            if (player.hitsLR(ball)){
                boolean temp = true;
                if (ball.isMovingLeft()){
                    ball.setMovingRight();
                    temp=false;
                }
                if (ball.isMovingRight()&&temp){
                    ball.setMovingLeft();
                }
            }
            if (player.hitsTB(ball)){
                boolean temp = true;
                if (ball.isMovingDown()){
                    ball.setMovingUp();
                    temp=false;
                }
                if (ball.isMovingUp()&&temp){
                    ball.setMovingDown();
                }
                //hit while moving
                if (player.isMovingRight()){
                    ball.setMovingRight();
                }
                if (player.isMovingLeft()){
                    ball.setMovingLeft();
                }
            }
            //block hit
            for (int i=blocks.size()-1;i>=0;i--){
                if ((blocks.get(i)).hitsLR(ball)) {
                    boolean temp = true;
                    blocks.remove(i);
                    if (ball.isMovingLeft()) {
                        ball.setMovingRight();
                        temp = false;
                    }
                    if (ball.isMovingRight() && temp) {
                        ball.setMovingLeft();
                    }
                }
                if ((blocks.get(i)).hitsTB(ball)){
                    boolean temp = true;
                    blocks.remove(i);
                    if (ball.isMovingDown()){
                        ball.setMovingUp();
                        temp=false;
                    }
                    if (ball.isMovingUp()&&temp){
                        ball.setMovingDown();
                    }
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
