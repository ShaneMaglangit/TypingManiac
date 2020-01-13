package com.shanemaglangit;

import javax.swing.*;
import java.util.ArrayList;

public class WordRun implements Runnable {
    private volatile boolean isRunning;
    private volatile ArrayList<Word> words;
    private TypingManiac typingManiac;

    /**
     * Default constructor
     * @param typingManiac frame where the thread runs.
     * @param words list of words.
     */
    public WordRun(TypingManiac typingManiac, ArrayList<Word> words) {
        this.typingManiac = typingManiac;
        this.words = words;
        this.isRunning = false;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    /**
     * Executes when thread is started.
     */
    @Override
    public void run() {
        while(isRunning) {
            updateWord();
            repaintDrawPanel();
            sleep();
        }
    }

    /**
     * Used to update the words position.
     */
    private void updateWord() {
        for(Word word: words) {
            word.descend();
        }
    }

    /**
     * Used to update the game area.
     */
    private void repaintDrawPanel() {
        SwingUtilities.invokeLater(() -> typingManiac.repaintGame());
    }

    /**
     * Pause the thread's execution.
     */
    private void sleep() {
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to run or stop the thread.
     * @param isRunning
     */
    public synchronized void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
        repaintDrawPanel();
    }

    public boolean isRunning() {
        return isRunning;
    }
}