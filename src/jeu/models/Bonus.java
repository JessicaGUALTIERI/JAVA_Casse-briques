package jeu.models;

import jeu.CasseBrique;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static jeu.CasseBrique.HAUTEUR;

public class Bonus extends Sprite {

    private int diametre;
    private int vitesseChute;
    private int chance20 = new Random().nextInt(1,10);

    // CONSTRUCTEUR
    public Bonus(int x, int y, int diametre) {
        super(x, y, Color.ORANGE);
        this.diametre = diametre;
        this.vitesseChute = 5;
    }

    // METHODES
    public void chute() {
        y += vitesseChute;
    }

    public int getCentreX() {
        return x + diametre/2;
    }

    public int getCentreY() {
        return y + diametre/2;
    }

    public int getChance20() {
        return chance20;
    }


    // AJOUT AUTOMATIQUE POUR FAIRE FONCTIONNER L'EXTEND DE SPRITE
    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillOval(x, y,  diametre, diametre);
    }


    // GETTER ET SETTER
    public int getDiametre() {
        return diametre;
    }

    public void setDiametre(int diametre) {
        this.diametre = diametre;
    }

    public int getVitesseChute() {
        return vitesseChute;
    }

    public void setVitesseChute(int vitesseChute) {
        this.vitesseChute = vitesseChute;
    }
}
