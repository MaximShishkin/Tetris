package ru.shishkin.maxim.tetris;

import javax.swing.*;

public class Frame extends JFrame {
    public Frame() {
        add(new Panel());
        setTitle("Tetris");
        setBounds(0, 0, 1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
