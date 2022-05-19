import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle {

    Random random;
    int xVelocity;
    int yVelocity;
    int initialSpeed=3;
    // ball constructor
    Ball(int x, int y, int width, int height) {
        super(x,y,width,height); // call to rectangle superclass constructor
        random = new Random();
        // get a random movement of the ball
        int randomXDirection = random.nextInt(2);
        if(randomXDirection == 0)
            randomXDirection--; // either 0 or 1, ball go left if negative
        setXDirection(randomXDirection*initialSpeed);
        // now y dir
        int randomYDirection = random.nextInt(2);
        if(randomYDirection == 0)
            randomYDirection--; // either 0 or 1, ball go up if negative
        setYDirection(randomYDirection*initialSpeed);
    }

    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }
    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }
    public void move() {
        x += xVelocity;
        y+= yVelocity;
    }
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x,y,height,width);
    }
}
