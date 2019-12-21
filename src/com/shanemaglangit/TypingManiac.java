package com.shanemaglangit;

import com.shanemaglangit.Word;
import com.shanemaglangit.WordRun;
import com.shanemaglangit.DrawPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TypingManiac {
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;

    private Font defaultFont = new Font("Montserrate", Font.PLAIN, 14);

    private ArrayList<String> wordList = new ArrayList<>();
    private int score = 0;
    private int numberOfWordsLeft = 0;

    private ArrayList<Word> words = new ArrayList<>();
    private DrawPanel drawGameArea = new DrawPanel(this, words);
    private WordRun wordRun = new WordRun(this, words);

    private JFrame frame = new JFrame("Typing Maniac For Babies");
    private JPanel pnlTop = new JPanel();
    private JPanel pnlBottom = new JPanel();
    private JButton btnStart = new JButton("START");
    private JTextField txtTypingField = new JTextField();
    private JLabel lblScores = new JLabel("Score: " + score);
    private JLabel lblLives = new JLabel("Words Left: " + numberOfWordsLeft);

    public TypingManiac() {
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        generateWords();

        btnStart.setPreferredSize(new Dimension(WINDOW_WIDTH - 24, 34));
        btnStart.setFont(defaultFont);
        btnStart.addActionListener(e -> start());
        pnlTop.setMaximumSize(new Dimension(WINDOW_WIDTH, 40));
        pnlTop.add(btnStart);

        drawGameArea.setMaximumSize(new Dimension(WINDOW_WIDTH, 320));

        pnlBottom.setLayout(new GridLayout(1, 3));
        pnlBottom.setMaximumSize(new Dimension(WINDOW_WIDTH, 40));
        lblScores.setHorizontalAlignment(SwingConstants.CENTER);
        lblScores.setFont(defaultFont);
        lblLives.setHorizontalAlignment(SwingConstants.CENTER);
        lblLives.setFont(defaultFont);
        txtTypingField.setFont(defaultFont);
        pnlBottom.add(lblScores);
        pnlBottom.add(txtTypingField);
        pnlBottom.add(lblLives);

        frame.add(pnlTop);
        frame.add(drawGameArea);
        frame.add(pnlBottom);
    }

    public void start() {
        new Thread(wordRun).start();
    }

    private void generateWords() {
        wordList.add("One");
        wordList.add("Two");
        wordList.add("Three");
        wordList.add("Four");
        wordList.add("Five");
        wordList.add("Six");
        wordList.add("Seven");
        wordList.add("Eight");
        wordList.add("Nine");
        wordList.add("Ten");
        
        for(int i = 0; i < 3; i++) {
            words.add(selectWord());
        }
    }

    private void setNumberOfWordsLeft(int value) {
        lblLives.setText("Words Left: " + value);
    }

    private Word selectWord() {
        String selectedWord = wordList.get((int) (Math.random() * wordList.size()));

        wordList.remove(selectedWord);
        setNumberOfWordsLeft(wordList.size());

        return new Word(selectedWord);
    }
    
    public void replaceWord(Word word) {
        words.remove(word);
        words.add(selectWord());
    }

    public void repaintGame() {
        drawGameArea.repaint();
    }
}
