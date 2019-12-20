package com.shanemaglangit.view;

import com.shanemaglangit.util.DrawPanel;
import com.sun.source.doctree.SeeTree;

import javax.swing.*;
import java.awt.*;

public class TypingManiacView {
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;

    private JFrame frame = new JFrame("Typing Maniac For Babies");
    private JPanel pnlTop = new JPanel();
    private DrawPanel pnlGameArea = new DrawPanel();
    private JPanel pnlBottom = new JPanel();
    private JButton btnStart = new JButton("Start");
    private JTextField txtTypingField = new JTextField();
    private JLabel lblScores = new JLabel("Scores");
    private JLabel lblLives = new JLabel("Lives");

    public TypingManiacView() {
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        pnlTop.setMaximumSize(new Dimension(WINDOW_WIDTH, 40));
        btnStart.setPreferredSize(new Dimension(WINDOW_WIDTH - 24, 34));
        pnlTop.add(btnStart);

        pnlGameArea.setMaximumSize(new Dimension(WINDOW_WIDTH, 320));
        pnlGameArea.setBackground(Color.LIGHT_GRAY);

        pnlBottom.setLayout(new GridLayout(1, 3));
        pnlBottom.setMaximumSize(new Dimension(WINDOW_WIDTH, 40));
        lblScores.setHorizontalAlignment(SwingConstants.CENTER);
        lblLives.setHorizontalAlignment(SwingConstants.CENTER);
        pnlBottom.add(lblScores);
        pnlBottom.add(txtTypingField);
        pnlBottom.add(lblLives);

        frame.add(pnlTop);
        frame.add(pnlGameArea);
        frame.add(pnlBottom);
    }

    public JButton getBtnStart() {
        return btnStart;
    }

    public JTextField getTxtTypingField() {
        return txtTypingField;
    }

    public JLabel getLblScores() {
        return lblScores;
    }

    public JLabel getLblLives() {
        return lblLives;
    }
}
