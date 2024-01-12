package jeu.models;

import jeu.CasseBrique;

import java.awt.*;

public class Brique extends Rectangle{

    public Brique(int x, int y, Color couleur, int largeur, int hauteur) {
        super(x, y, couleur, largeur, hauteur);
    }

    public int getCentreX() {
        return x + (largeur / 2 - 5); // idem qu'en-dessous
    }

    public int getCentreY() {
        return y + (hauteur / 2 - 5); // -5 car 5 est la moitié de la largeur du bonus, permet de bien centrer
    }


}