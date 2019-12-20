package com.shanemaglangit.util;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
    }
}
