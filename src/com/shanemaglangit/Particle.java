package com.shanemaglangit;

import java.awt.*;
import java.util.Random;

public class Particle {
    private Random random = new Random();

    private Color color;
    private int dimension;
    private int x;
    private int y;

    public Particle(int textWidth, int x, int y) {
        this.color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
        this.dimension = random.nextInt(10) + 2;
        this.x = random.nextInt(textWidth + 20) + (x - 10);
        this.y = random.nextInt(40) + (y - 30);
    }

    public Color getColor() {
        return color;
    }

    public int getDimension() {
        return dimension;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
