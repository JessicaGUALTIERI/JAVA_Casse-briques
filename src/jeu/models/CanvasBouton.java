package jeu.models;

import java.awt.*;

public class CanvasBouton {

    protected int x = 0;
    protected int y = 0;
    protected int largeur = 100;
    protected int hauteur = 30;
    protected String texte = "";
    protected EvenementBouton evenement;

    public CanvasBouton(int x, int y, String texte) {
        this.x = x;
        this.y = y;
        this.texte = texte;
    }

    public void dessiner(Graphics2D dessin) {
        dessin.setColor(Color.black);
        dessin.fillRect(x,y,largeur,hauteur);
        dessin.setColor(Color.white);
        dessin.drawString(texte, x+25, y+20);
    }

    public void clic() {
        evenement.declenche();
    }

    public boolean collision(int xSouris, int ySouris) {
        return xSouris > this.x && xSouris < this.x + largeur && ySouris > this.y && ySouris < this.y + hauteur;
    }

    public void addEvenementBouton(EvenementBouton evenement) {
        this.evenement = evenement;
    }
}