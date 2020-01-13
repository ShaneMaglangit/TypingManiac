package com.shanemaglangit;

import java.awt.*;
import java.util.Random;

public class Word {
    private static final double START_X = 10;
    private static final double START_Y = 10;
    private static final double END_X = 580;
    private static final double MIN_SPEED = 1;
    private static final double MAX_ADDITIONAL_SPEED = 0.5;

    private String text;
    private double x;
    private double y;
    private double speed;
    private int textWidth;

    /**
     * Default constructor.
     * @param text
     */
    public Word(String text) {
        this.text = text;
        this.x = Math.random() * END_X + START_X;
        this.y = START_Y;
        this.speed = Math.random() * MAX_ADDITIONAL_SPEED + MIN_SPEED;
    }

    /**
     * Used to descend the position of the object.
     */
    public void descend() {
        this.y += speed;
    }

    /**
     * Used to draw the object on the panel.
     * @param g2
     */
    public void draw(Graphics2D g2) {
        textWidth = g2.getFontMetrics().stringWidth(text);
        if(textWidth + x > END_X) x -= (textWidth + x - END_X);

        g2.setColor(new Color(220, 236, 201));
        g2.drawString(text, (int) x, (int) y);
    }

    public int getTextWidth() {
        return textWidth;
    }

    public String getText() {
        return text;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
