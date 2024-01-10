package jeu.models;

import jeu.CasseBrique;

import java.awt.*;

public class Bonus extends Rectangle {
    private int diametre;
    private int vitesseChute;
    public Bonus(int x, int y, int diametre) {
        super(x, y, Color.ORANGE,10,10);
        this.diametre = diametre;
        this.vitesseChute = 4;
    }

    public void chute() {
        y += vitesseChute;
    }
}
