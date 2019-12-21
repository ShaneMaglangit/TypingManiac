package com.shanemaglangit;

import java.awt.*;

public class Word {
    private static final int START_X = 10;
    private static final int START_Y = 10;
    private static final int MAX_SPEED = 10;

    private String text;
    private double x;
    private double y;
    private double speed;
    private Color color;

    public Word(String text) {
        this.text = text;
        this.x = Math.random() * 600 + START_X;
        this.y = START_Y;
        this.speed = Math.random() * MAX_SPEED + 4;

        generateColor();
    }

    public void descend() {
        this.y += speed;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.drawString(text, (int)x, (int)y);
    }

    private void generateColor() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);

        this.color = new Color(r, g, b);
    }

    public double getY() {
        return y;
    }
}
