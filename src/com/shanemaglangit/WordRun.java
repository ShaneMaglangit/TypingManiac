package com.shanemaglangit;

import java.util.ArrayList;

public class WordRun implements Runnable {
    private volatile boolean isRunning;
    private ArrayList<Word> words;
    private TypingManiac typingManiac;

    public WordRun(TypingManiac typingManiac, ArrayList<Word> words) {
        this.typingManiac = typingManiac;
        this.words = words;
        this.isRunning = true;
    }

    @Override
    public void run() {
        while(isRunning) {
            updateWord();
            typingManiac.repaintGame();
            sleep();
        }
    }

    private void updateWord() {
        for(Word word: words) {
            word.descend();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}