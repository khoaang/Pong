import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.*;

public class GamePanel extends JPanel implements Runnable{
    // set size of screen
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH*(5.0/9)); // ping pong tables are 5 feet by 9 feet
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    GamePanel() {
        // initialize paddles and ball
        newPaddles();
        newBall();
        // call to score constructor
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),(GAME_HEIGHT/2)-(BALL_DIAMETER/2),BALL_DIAMETER,BALL_DIAMETER);
    }
    public void newPaddles() {
        paddle1 = new Paddle(50,GAME_HEIGHT/2-(PADDLE_HEIGHT/2),PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH-50,GAME_HEIGHT/2-(PADDLE_HEIGHT/2),PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }
    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }
    public void move() { // updates position of objects
        paddle1.move();
        paddle2.move();
        ball.move();
    }
    public void checkCollision() {
        // stop paddles at window
        if(paddle1.y <= 0)
            paddle1.y = 0;
        if(paddle1.y >= GAME_HEIGHT-PADDLE_HEIGHT)
            paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
        if(paddle2.y <= 0)
            paddle2.y = 0;
        if(paddle2.y >= GAME_HEIGHT-PADDLE_HEIGHT)
            paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;

        // bounces ball off edges
        if(ball.y<=0 || ball.y>=GAME_HEIGHT-BALL_DIAMETER)
            ball.setYDirection(-ball.yVelocity);

        // ball and paddle collision
        if(ball.intersects(paddle1) || ball.intersects(paddle2)) {
            ball.xVelocity *= -1;

            // increase speed of ball after collision
            if (ball.xVelocity > 0)
                ball.xVelocity++;
            else
                ball.xVelocity--;
            if (ball.yVelocity > 0)
                ball.yVelocity++;
            else
                ball.yVelocity--;

            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
            // award points and creates new paddles and ball
            if(ball.x <= 0) { // if ball passes left boundary
                score.player2++;
                newPaddles();
                newBall();
            }
            if(ball.x >= GAME_WIDTH-BALL_DIAMETER) { // if ball passes right boundary
                score.player1++;
                newPaddles();
                newBall();
            }
    }
    public void bot (Paddle paddle, int side){
        // computer control of paddle
        // bots paddle only moves if ball within 3/4 of their screen, for variation in movement
        if (Math.abs(paddle.x-ball.x)<GAME_WIDTH*3/4) {
            // paddle follows ball until reaches limit speed (otherwise no one wins)
            int speed = ball.yVelocity;
            // speed + 2 to the bots to make it a little more fun
            if (speed > paddle.speed+2)
                speed = paddle.speed+2;
            if (paddle.y > ball.y)
                paddle.y -= speed;
            else
                paddle.y += speed;
        }
    }
    public void run() {
        // game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if(delta >= 1 && !score.over) {
                move();
                if(paddle2.botPlaying)
                    bot(paddle2, -1);
                if(paddle1.botPlaying)
                    bot(paddle1, 1);
                checkCollision();
                repaint();
                delta--;
            }
        }
    }
    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
            if(score.over && e.getKeyCode() == KeyEvent.VK_SPACE) {
                score = new Score(GAME_WIDTH, GAME_HEIGHT);
            }
        }
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
