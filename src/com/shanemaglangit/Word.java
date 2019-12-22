package com.shanemaglangit;

import java.awt.*;

public class Word {
    private static final double START_X = 10;
    private static final double START_Y = 10;
    private static final double END_X = 550;
    private static final double MIN_SPEED = 1;
    private static final double MAX_ADDITIONAL_SPEED = 1;

    private String text;
    private double x;
    private double y;
    private double speed;
    private Color color;

    public Word(String text) {
        this.text = text;
        this.x = Math.random() * END_X + START_X;
        this.y = START_Y;
        this.speed = Math.random() * MAX_ADDITIONAL_SPEED + MIN_SPEED;

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

    public String getText() {
        return text;
    }

    public double getY() {
        return y;
    }
}
