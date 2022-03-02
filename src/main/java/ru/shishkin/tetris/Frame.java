package ru.shishkin.tetris;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame() {
        Panel p = new Panel();
        Container cont = getContentPane();
        cont.add(p);

        setTitle("Tetris");

        setBounds(0, 0, 1000, 700);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);

        setVisible(true);
    }
}
