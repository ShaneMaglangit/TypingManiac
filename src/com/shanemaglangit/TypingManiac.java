package com.shanemaglangit;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class TypingManiac {
    // Constants
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 480;
    private static final Color PRIMARY = new Color(140, 122, 230);
    private static final Color TEXT_ON_PRIMARY = new Color(245, 246, 250);
    private static final Color BACKGROUND = new Color(47, 54, 64);
    private static final Color TEXT_ON_BACKGROUND = new Color(245, 246, 250);

    // Fonts
    private Font defaultFont = new Font("Montserrat", Font.PLAIN, 14);
    private Font defaultBoldFont = new Font("Montserrat", Font.BOLD, 14);

    // Game Properties
    private ArrayList<String> wordList = new ArrayList<>();
    private int score = 0;
    private int numberOfWordsLeft = 0;

    // Word
    private ArrayList<Word> words = new ArrayList<>();
    private DrawPanel drawGameArea = new DrawPanel(this, words, defaultBoldFont);
    private WordRun wordRun = new WordRun(this, words);

    // UI Components
    private JFrame frame = new JFrame("Typing Maniac For Babies");
    private JPanel pnlTop = new JPanel();
    private JPanel pnlBottom = new JPanel();
    private JButton btnStart = new JButton("START");
    private JTextField txtTypingField = new JTextField();
    private JLabel lblScores = new JLabel("Score: " + score);
    private JLabel lblLives = new JLabel("Words Left: " + numberOfWordsLeft);

    /**
     * Constructor to create the window.
     */
    public TypingManiac() {
        // Set main frame configurations.
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setBackground(BACKGROUND);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();
            }
        });

        // Top area components configuration.
        btnStart.setPreferredSize(new Dimension(WINDOW_WIDTH - 24, 34));
        btnStart.setFont(defaultBoldFont);
        btnStart.setBackground(PRIMARY);
        btnStart.setForeground(TEXT_ON_PRIMARY);
        btnStart.setFocusable(false);
        pnlTop.setBackground(BACKGROUND);
        pnlTop.setMaximumSize(new Dimension(WINDOW_WIDTH, 40));
        pnlTop.add(btnStart);

        // Middle area components configuration.
        drawGameArea.setMaximumSize(new Dimension(WINDOW_WIDTH, 400));
        drawGameArea.setBackground(BACKGROUND);

        // Bottom area components configuration.
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

        // Listeners for events.
        btnStart.addActionListener(e -> toggleState());
        txtTypingField.addActionListener(e -> txtTypingField.setText(""));
        txtTypingField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> submitWord(txtTypingField.getText()));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });

        // Add panels to the main frame.
        frame.add(pnlTop);
        frame.add(drawGameArea);
        frame.add(pnlBottom);

        // SHow window
        frame.setVisible(true);
    }

    /**
     * Used to toggle the game state.
     */
    public void toggleState() {
        if(wordRun.isRunning()) {
            btnStart.setText("START");
            wordRun.setRunning(false);
        } else {
            btnStart.setText("STOP");
            score = 0;
            generateWords();
            wordRun.setRunning(true);
            new Thread(wordRun).start();
        }
    }

    /**
     * Used to generate words.
     * Reads words from words.txt.
     */
    private void generateWords() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("words.txt"));
            String text;

            wordList.clear();
            words.clear();

            while((text = bufferedReader.readLine()) != null) {
                wordList.add(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 3; i++) {
            words.add(selectWord());
        }

        wordRun.setWords(words);
        drawGameArea.setWords(words);
    }

    /**
     * Used to select a random word from the word list.
     * @return selected word.
     */
    private Word selectWord() {
        String selectedWord = wordList.get((int) (Math.random() * wordList.size()));

        wordList.remove(selectedWord);
        lblLives.setText("Words Left: " + wordList.size());

        return new Word(selectedWord);
    }

    /**
     * Used to close the application.
     */
    private void exitProcedure() {
        wordRun.setRunning(false);
        frame.dispose();
        System.exit(0);
    }

    /**
     * Used to submit user input to try and match with the word list.
     * @param text inputted by user
     */
    public void submitWord(String text) {
        Word wordMatched = null;

        for(Word word: words) {
            if(word.getText().equalsIgnoreCase(text)) {
                txtTypingField.setText("");
                score += text.length();
                lblScores.setText("Score: " + score);
                wordMatched = word;
                break;
            }
        }

        if(wordMatched != null) {
            drawGameArea.addExplosion(wordMatched);
            replaceWord(wordMatched);
        }
    }

    /**
     * Used to replace the word correctly typed by the user.
     * @param word to be replaced.
     */
    public void replaceWord(Word word) {
        words.remove(word);
        if(words.isEmpty()) toggleState();
        if(!wordList.isEmpty()) words.add(selectWord());
    }

    /**
     * Used to repaint the game area.
     */
    public void repaintGame() {
        drawGameArea.repaint();
    }
}
