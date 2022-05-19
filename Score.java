import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Score extends Rectangle {

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1;
    int player2;
    boolean over = false;
    // get height + width of screen in constructor
    Score(int GAME_WIDTH, int GAME_HEIGHT) {
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        // write game text
        g.setFont(new Font("Monospaced", Font.PLAIN, 20));
        g.drawString("khoa nguyen compsci a", 20, 40);
        g.drawString("press arrow keys", GAME_WIDTH*2/3, GAME_HEIGHT-50);
        g.drawString("to take over bot", GAME_WIDTH*2/3, GAME_HEIGHT-30);
        g.drawString("press w / s keys", 40, GAME_HEIGHT-50);
        g.drawString("to take over bot", 40, GAME_HEIGHT-30);
        // draw a line down the middle cause why not
        g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
        // write score
        g.setFont(new Font("Monospaced", Font.PLAIN, 40));
        g.drawString(String.valueOf(player1), GAME_WIDTH/2-60, 70);
        g.drawString(String.valueOf(player2), GAME_WIDTH/2+20, 70);
        // game win text
        if(player1>4) {
            g.drawString("P1 WINS!", 600, 100);
            g.setFont(new Font("Monospaced", Font.PLAIN, 30));
            g.drawString("Press space to play again", 600, 150);
            over = true;
        }
        if(player2>4) {
            g.drawString("P2 WINS!", 100, 100);
            g.setFont(new Font("Monospace", Font.PLAIN, 30));
            g.drawString("Press space to play again", 100, 150);
            over = true;
        }
    }

}
