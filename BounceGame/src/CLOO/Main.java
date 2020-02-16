package CLOO;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JFrame implements KeyListener {
    //Game
    private Game g = new Game();
    //Constructor
    public Main(){
        super("Bounce");
        setSize(g.getWIDTH(),g.getHEIGHT());
        getContentPane().add(g);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        addKeyListener(this);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        //moves in that direction when left and right keys are pressed
        if (e.getKeyCode()==KeyEvent.VK_LEFT){
            g.player.setMovingLeft();
        }
        if (e.getKeyCode()==KeyEvent.VK_RIGHT){
            g.player.setMovingRight();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        //stops moving when no key is pressed
        g.player.setMoving();
    }
    public static void main(String[] args) {
        Main run = new Main();
    }
}
