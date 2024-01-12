package jeu.models;

import jeu.CasseBrique;
import java.awt.*;

import static jeu.CasseBrique.HAUTEUR;

public class Bonus extends Sprite {

    private int diametre;
    private int vitesseChute;

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
        return x;
    }

    public int getCentreY() {
        return y;
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


    // AJOUT AUTOMATIQUE POUR FAIRE FONCTIONNER L'EXTEND DE SPRITE
    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillOval(x, y,  diametre, diametre);
    }
}
