package com.shanemaglangit.model;

public class WordRun extends Thread {
    private String text;
    private float xPos;
    private float yPos;

    public WordRun(String text, float xPos, float yPos) {
        this.text = text;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    @Override
    public void run() {
        super.run();

        while(yPos > 0) {
            try {
                yPos -= 2;
                System.out.println(yPos);
                Thread.sleep(10);
            } catch(InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }
}
