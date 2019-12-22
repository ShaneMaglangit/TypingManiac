package com.shanemaglangit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawPanel extends JPanel {
    private ArrayList<Word> words;
    private TypingManiac typingManiac;

    public DrawPanel(TypingManiac typingManiac, ArrayList<Word> words) {
        this.typingManiac = typingManiac;
        this.words = words;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        ArrayList<Word> wordsToRemove = new ArrayList<>();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setFont(new Font("Montserrat", Font.BOLD, 18));

        for(Word word: words) {
            if(word.getY() < this.getHeight()) {
                word.draw(g2);
            } else {
                wordsToRemove.add(word);
            }
        }

        for(Word word: wordsToRemove) {
            typingManiac.replaceWord(word);
        }
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
        repaint();
    }
}
