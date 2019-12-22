package com.shanemaglangit;

import javax.swing.*;
import java.util.ArrayList;

public class WordRun implements Runnable {
    private volatile boolean isRunning;
    private volatile ArrayList<Word> words;
    private TypingManiac typingManiac;

    public WordRun(TypingManiac typingManiac, ArrayList<Word> words) {
        this.typingManiac = typingManiac;
        this.words = words;
        this.isRunning = false;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    @Override
    public void run() {
        while(isRunning) {
            updateWord();
            repaintDrawPanel();
            sleep();
        }
    }

    private void updateWord() {
        for(Word word: words) {
            word.descend();
        }
    }

    private void repaintDrawPanel() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                typingManiac.repaintGame();
            }
        });
    }

    private void sleep() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
        repaintDrawPanel();
    }

    public boolean isRunning() {
        return isRunning;
    }
}