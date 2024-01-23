package jeu.models;

import java.awt.*;

public class CanvasBouton extends Rectangle {
    protected String texte = "";
    protected EvenementBouton evenement;

    public CanvasBouton(int x, int y, Color couleur, int largeur, int hauteur, String texte) {
        super(x,y,couleur,largeur,hauteur);
        this.texte = texte;
    }

    public void dessiner(Graphics2D dessin) {
        super.dessiner(dessin);
        dessin.setColor(Color.white);
        dessin.drawString(texte, x+25, y+20);
    }

    public void clic() {
        if (evenement != null) {
            evenement.declenche();
        }
    }

    public boolean collision(int xSouris, int ySouris) {
        return xSouris > this.x && xSouris < this.x + largeur && ySouris > this.y && ySouris < this.y + hauteur;
    }

    public void addEvenementBouton(EvenementBouton evenement) {
        this.evenement = evenement;
    }
}