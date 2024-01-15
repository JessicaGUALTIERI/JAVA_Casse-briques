package jeu.models;

import jeu.CasseBrique;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class Bouton extends Rectangle implements MouseListener {
    public Bouton() {
        super(30,CasseBrique.HAUTEUR-50,Color.PINK,50,20);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton () == MouseEvent.BUTTON1) {
            System.out.println("clic");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
