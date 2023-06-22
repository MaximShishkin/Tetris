package ru.shishkin.tetris;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel {
    private Game myGame;
    private Timer tmDraw, tmUpdate;
    public Image color1, color2, color3, color4, color5, color6, color7, fon, endg;
    private JLabel lb;
    private JButton btn1, btn2;
    private Panel pan;

    private class myKey implements KeyListener {
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) myGame.napr = 3;
            else if (key == KeyEvent.VK_UP) myGame.napr = 1;
            else if (key == KeyEvent.VK_RIGHT) myGame.napr = 2;
            else if (key == KeyEvent.VK_DOWN) myGame.napr = 5;
        }

        public void keyReleased(KeyEvent e) {
        }

        public void keyTyped(KeyEvent e) {
        }
    }

    public Panel() {
        pan = this;
        this.addKeyListener(new myKey());
        this.setFocusable(true);

        try {
            color1 = ImageIO.read(getClass().getClassLoader().getResource("1.png"));
            color2 = ImageIO.read(getClass().getClassLoader().getResource("2.png"));
            color3 = ImageIO.read(getClass().getClassLoader().getResource("3.png"));
            color4 = ImageIO.read(getClass().getClassLoader().getResource("4.png"));
            color5 = ImageIO.read(getClass().getClassLoader().getResource("5.png"));
            color6 = ImageIO.read(getClass().getClassLoader().getResource("6.png"));
            color7 = ImageIO.read(getClass().getClassLoader().getResource("7.png"));
            endg = ImageIO.read(getClass().getClassLoader().getResource("endg.png"));
            fon = ImageIO.read(getClass().getClassLoader().getResource("fon.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        myGame = new Game();
        myGame.start();

        tmDraw = new Timer(20, new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                repaint();
            }
        });
        tmDraw.start();

        tmUpdate = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (myGame.endg == false) {
                    myGame.perem();
                }
                lb.setText("SCORE : " + myGame.kol);
            }
        });
        tmUpdate.start();

        setLayout(null);

        lb = new JLabel("SCORE: " + myGame.kol);
        lb.setForeground(Color.BLACK);
        lb.setFont(new Font("serif", 0, 30));
        lb.setBounds(500, 150, 200, 100);
        add(lb);

        btn1 = new JButton();
        btn1.setText("RESTART");
        btn1.setForeground(Color.BLUE);
        btn1.setFont(new Font("serif", 0, 20));
        btn1.setBounds(700, 70, 200, 100);
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                myGame.start();
                btn1.setFocusable(false);
                btn2.setFocusable(false);
                pan.setFocusable(true);
            }
        });
        add(btn1);

        btn2 = new JButton();
        btn2.setText("EXIT");
        btn2.setForeground(Color.RED);
        btn2.setFont(new Font("serif", 0, 20));
        btn2.setBounds(700, 220, 200, 100);
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        add(btn2);
    }

    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        gr.drawImage(fon, 0, 0, 1000, 750, null);

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 20; j++) {
                if (myGame.mas[i][j] != 0) {
                    if (myGame.mas[i][j] == 1) {
                        if (myGame.color == 0) gr.drawImage(color1, 20 + j * 20, 10 + i * 20, 20, 20, null);
                        if (myGame.color == 1) gr.drawImage(color2, 20 + j * 20, 10 + i * 20, 20, 20, null);
                        if (myGame.color == 2) gr.drawImage(color3, 20 + j * 20, 10 + i * 20, 20, 20, null);
                        if (myGame.color == 3) gr.drawImage(color4, 20 + j * 20, 10 + i * 20, 20, 20, null);
                        if (myGame.color == 4) gr.drawImage(color5, 20 + j * 20, 10 + i * 20, 20, 20, null);
                        if (myGame.color == 5) gr.drawImage(color6, 20 + j * 20, 10 + i * 20, 20, 20, null);
                        if (myGame.color == 6) gr.drawImage(color7, 20 + j * 20, 10 + i * 20, 20, 20, null);
                    } else if (myGame.mas[i][j] == 2) gr.drawImage(color1, 20 + j * 20, 10 + i * 20, 20, 20, null);
                    else if (myGame.mas[i][j] == 3) gr.drawImage(color2, 20 + j * 20, 10 + i * 20, 20, 20, null);
                    else if (myGame.mas[i][j] == 4) gr.drawImage(color3, 20 + j * 20, 10 + i * 20, 20, 20, null);
                    else if (myGame.mas[i][j] == 5) gr.drawImage(color4, 20 + j * 20, 10 + i * 20, 20, 20, null);
                    else if (myGame.mas[i][j] == 6) gr.drawImage(color5, 20 + j * 20, 10 + i * 20, 20, 20, null);
                    else if (myGame.mas[i][j] == 7) gr.drawImage(color6, 20 + j * 20, 10 + i * 20, 20, 20, null);
                    else if (myGame.mas[i][j] == 8) gr.drawImage(color7, 20 + j * 20, 10 + i * 20, 20, 20, null);
                }
            }
        }

        gr.setColor(Color.gray);
        for (int i = 0; i <= 20; i++) {
            for (int j = 0; j <= 30; j++) {
                gr.drawLine(20 + i * 20, 10, 20 + i * 20, 610);
                gr.drawLine(20, 10 + j * 20, 420, 10 + j * 20);
            }
        }

        if (myGame.endg == true) {
            gr.drawImage(endg, 250, 300, 400, 200, null);
        }
    }
}

