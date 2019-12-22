package com.shanemaglangit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class TypingManiac {
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;
    private static final Color PRIMARY = new Color(140, 122, 230);
    private static final Color TEXT_ON_PRIMARY = new Color(245, 246, 250);
    private static final Color BACKGROUND = new Color(47, 54, 64);
    private static final Color TEXT_ON_BACKGROUND = new Color(245, 246, 250);

    private Font defaultFont = new Font("Montserrat", Font.PLAIN, 14);
    private Font defaultBoldFont = new Font("Montserrat", Font.BOLD, 14);

    private ArrayList<String> wordList = new ArrayList<>();
    private int score = 0;
    private int numberOfWordsLeft = 0;

    private ArrayList<Word> words = new ArrayList<>();
    private DrawPanel drawGameArea = new DrawPanel(this, new ArrayList<>());
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
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();
            }
        });
        frame.setBackground(BACKGROUND);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        btnStart.setPreferredSize(new Dimension(WINDOW_WIDTH - 24, 34));
        btnStart.setFont(defaultBoldFont);
        btnStart.setBackground(PRIMARY);
        btnStart.setForeground(TEXT_ON_PRIMARY);
        pnlTop.setBackground(BACKGROUND);
        pnlTop.setMaximumSize(new Dimension(WINDOW_WIDTH, 40));
        pnlTop.add(btnStart);

        drawGameArea.setMaximumSize(new Dimension(WINDOW_WIDTH, 320));
        drawGameArea.setBackground(BACKGROUND);

        pnlBottom.setBackground(BACKGROUND);
        pnlBottom.setLayout(new GridLayout(1, 3));
        pnlBottom.setMaximumSize(new Dimension(WINDOW_WIDTH, 40));
        lblScores.setHorizontalAlignment(SwingConstants.CENTER);
        lblScores.setFont(defaultFont);
        lblScores.setForeground(TEXT_ON_BACKGROUND);
        lblLives.setHorizontalAlignment(SwingConstants.CENTER);
        lblLives.setFont(defaultFont);
        lblLives.setForeground(TEXT_ON_BACKGROUND);
        txtTypingField.setHorizontalAlignment(JTextField.CENTER);
        txtTypingField.setFont(defaultFont);
        pnlBottom.add(lblScores);
        pnlBottom.add(txtTypingField);
        pnlBottom.add(lblLives);

        btnStart.addActionListener(e -> start());
        txtTypingField.addActionListener(e -> submitWord(txtTypingField.getText()));

        frame.add(pnlTop);
        frame.add(drawGameArea);
        frame.add(pnlBottom);
    }

    public void start() {
        if(wordRun.isRunning()) {
            wordRun.setRunning(false);
        } else {
            score = 0;
            generateWords();
            wordRun.setRunning(true);
            new Thread(wordRun).start();
        }
    }

    private void generateWords() {
        wordList.clear();
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

        words.clear();

        for(int i = 0; i < 3; i++) {
            words.add(selectWord());
        }

        wordRun.setWords(words);
        drawGameArea.setWords(words);
    }

    private Word selectWord() {
        String selectedWord = wordList.get((int) (Math.random() * wordList.size()));

        wordList.remove(selectedWord);
        lblLives.setText("Words Left: " + wordList.size());

        return new Word(selectedWord);
    }

    private void exitProcedure() {
        wordRun.setRunning(false);
        frame.dispose();
        System.exit(0);
    }

    private void submitWord(String text) {
        Word wordMatched = null;

        txtTypingField.setText("");

        for(Word word: words) {
            if(word.getText().equalsIgnoreCase(text)) {
                score += text.length();
                lblScores.setText("Score: " + score);
                wordMatched = word;
            }
        }

        if(wordMatched != null) replaceWord(wordMatched);
    }
    
    public void replaceWord(Word word) {
        words.remove(word);

        if(words.isEmpty()) {
            wordRun.setRunning(false);
        }

        if(!wordList.isEmpty()) {
            words.add(selectWord());
        }
    }

    public void repaintGame() {
        drawGameArea.repaint();
    }
}
