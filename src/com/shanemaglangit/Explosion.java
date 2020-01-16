package com.shanemaglangit;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Explosion {
    private final int MAX_PARTICLE = 5;

    private int textWidth;
    private int x;
    private int y;

    private ArrayList<Particle> particles = new ArrayList<>();
    private int particleCount = 1;
    private boolean isExpanding = true;

    /**
     * Default constructor
     * @param word to be used as a reference for the explosion.
     */
    public Explosion(Word word) {
        this.textWidth = word.getTextWidth();
        this.x = (int)word.getX();
        this.y = (int)word.getY();
    }

    /**
     * Method to generate explosion particles.
     */
    public void generateParticles() {
        particles.clear();

        for(int i = 0; i < particleCount; i++) {
            particles.add(new Particle(textWidth, x, y));
        }

        if(isExpanding) particleCount++;
        else particleCount--;

        if(particleCount > MAX_PARTICLE) isExpanding = false;
    }

    /**
     * Used to get the particle count.
     * @return number of particles.
     */
    public int getParticleCount() {
        return particleCount;
    }

    /**
     * Used to draw the explosion on the panel.
     * @param g2
     */
    public void draw(Graphics2D g2) {
        for(Particle particle: particles) {
            g2.setStroke(new BasicStroke(new Random().nextInt(2) + 1));
            g2.setColor(particle.getColor());
            g2.drawRect(particle.getX(), particle.getY(), particle.getDimension(), particle.getDimension());
        }
    }
}
