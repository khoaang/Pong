import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame {

    GamePanel panel;
    // sets up game frame (the game window)
    GameFrame() {
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(new Color(193,66,66)); //  set bg color
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closing behavior
        this.pack(); // sizes screen properly
        this.setVisible(true);
        this.setLocationRelativeTo(null); // game will appear on middle of screen
    }
}
