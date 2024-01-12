package jeu.models;

import java.awt.*;

public class Rectangle extends Sprite {

    public Rectangle(int x, int y, Color couleur, int largeur, int hauteur) {
        super(x, y, couleur);
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    protected int largeur;
    protected int hauteur;

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public boolean collision(Balle balle) {
        return (balle.getCentreX() > x
                && balle.getCentreX() < x + largeur
                && balle.getCentreY() > y
                && balle.getCentreY() < y + hauteur);
    }

    public boolean collision(Bonus bonus) {
        return (bonus.getCentreX() > x
                && bonus.getCentreX() < x + largeur
                && bonus.getCentreY() > y
                && bonus.getCentreY() < y + hauteur);
    }

    @Override
    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillRect(x, y,  largeur, hauteur);
    }
}