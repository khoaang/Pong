import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Paddle extends Rectangle {

    int id;
    int yVelocity;
    int speed = 10; // speed of the paddle
    boolean botPlaying = true;

    // paddle constructor
    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT); // call to rectangle constructor
        this.id = id;
    }

    public void keyPressed(KeyEvent e) {
        // controlling the paddle
        switch (id) {
            case 1: // w and s keys
                // go up if w is  pressed
                if(e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-speed);
                    move();
                    botPlaying = false;
                }
                // go down if s is pressed
                if(e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(speed);
                    move();
                    botPlaying = false;
                }
            break;
            case 2: // up and down keys
                // go up if up is  pressed
                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(-speed);
                    move();
                    botPlaying = false;
                }
                // go down if down is pressed
                if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(speed);
                    move();
                    botPlaying = false;
                }
            break;
        }
    }
    public void keyReleased(KeyEvent e) { // stop motion on key up
        switch (id) {
            case 1: // w and s keys
                if(e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                    move();
                }
                break;
            case 2: // up and down keys
                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                    move();
                }
                break;
        }
    }
    // update velocity value
    public void setYDirection(int yDirection) {
        yVelocity = yDirection; // updates yVelocity
    }
    // update movement
    public void move() {
        y = y + yVelocity; // really basic way of moving
    }
    // function for sending graphics to be rendered
    public void draw(Graphics g) {
        if(id==id) {
            g.setColor(Color.white);
        }
        g.fillRect(x, y, width, height);
    }
}
