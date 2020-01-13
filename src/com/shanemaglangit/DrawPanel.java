package com.shanemaglangit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class DrawPanel extends JPanel {
    private ArrayList<Word> words;
    private ArrayList<Explosion> explosions;
    private TypingManiac typingManiac;
    private Font font;

    /**
     * Default constructor
     * @param typingManiac frame where the thread runs.
     * @param words list of words.
     */
    public DrawPanel(TypingManiac typingManiac, ArrayList<Word> words, Font font) {
        this.typingManiac = typingManiac;
        this.words = words;
        this.font = font;
        this.explosions = new ArrayList<>();
    }

    /**
     * Used to update the ui of the game area.
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        ArrayList<Word> wordsToRemove = new ArrayList<>();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setFont(font);

        for(Word word: words) {
            if(word.getY() < this.getHeight()) word.draw(g2);
            else wordsToRemove.add(word);
        }

        for(Explosion explosion: explosions) {
            explosion.draw(g2);
        }

        for(Word word: wordsToRemove) typingManiac.replaceWord(word);
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
        repaint();
    }

    public void addExplosion(Word word) {
        explosions.add(new Explosion(word));
        for(Explosion explosion: explosions) {
            new Thread(() -> {
                try {
                    while(explosion.getParticleCount() > 0) {
                        explosion.generateParticles();
                        Thread.sleep(60);
                    }

                    this.explosions.remove(explosion);
                } catch (InterruptedException interruptedException) {
                    System.out.println("Explosion thread is interrupted.");
                } catch (ConcurrentModificationException concurrentModificationException) {
                    System.out.println("Concurrent Modification Exception thrown.");
                }

            }).start();
        }
    }
}
