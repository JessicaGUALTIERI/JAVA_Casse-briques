package jeu;

import java.awt.event.*;

import jeu.models.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.MouseEvent;


public class CasseBrique extends Canvas implements KeyListener, MouseListener {

    public static final int LARGEUR = 500;
    public static final int HAUTEUR = 600;
    public int compteurBrique = 0;

    protected ArrayList<Balle> listeBalle = new ArrayList<>();
    protected ArrayList<Brique> listeBrique = new ArrayList<>();
    protected ArrayList<Brique> listeBriqueSuppr = new ArrayList<>();
    protected ArrayList<Bonus> listeBonus = new ArrayList<>();
    protected ArrayList<Bonus> listeBonusChute = new ArrayList<>();
    protected ArrayList<Bonus> listeBonusSuppr = new ArrayList<>();
    protected Barre barre = new Barre();
    protected ArrayList<CanvasBouton> listeBouton = new ArrayList<>();
    protected boolean pause = false;


    public CasseBrique() {

        JFrame fenetre = new JFrame();

        this.setSize(LARGEUR, HAUTEUR);
        this.setBounds(0,0, LARGEUR, HAUTEUR);
        this.setIgnoreRepaint(true);
        this.setFocusable(false);

        fenetre.pack();
        fenetre.setSize(LARGEUR, HAUTEUR);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setResizable(false);
        fenetre.requestFocus();
        fenetre.addKeyListener(this);
        this.addMouseListener(this);

        Container panneau = fenetre.getContentPane();
        panneau.add(this);


        fenetre.setVisible(true);
        this.createBufferStrategy(2);

        CanvasBouton boutonPause = new CanvasBouton(LARGEUR/2-120,HAUTEUR-60,Color.BLACK,100,30,"PAUSE");

        boutonPause.addEvenementBouton(() -> {
            pause = !pause;
        });

        CanvasBouton boutonRecommencer = new CanvasBouton(LARGEUR/2+20, HAUTEUR-60, Color.BLACK, 100, 30, "RECOMMENCER");

        boutonRecommencer.addEvenementBouton(() -> {
            recommencer();
        });

        listeBouton.add(boutonRecommencer);
        listeBouton.add(boutonPause);

        recommencer();
        demarrer();
    }

    public void recommencer() {

        pause = false;
        listeBalle.clear();
        listeBrique.clear();
        listeBonus.clear();
        listeBriqueSuppr.clear();
        listeBonusSuppr.clear();
        listeBonusChute.clear();

        // CRÉATION DE(S) BALLE(S) :
        for(int i = 0 ; i < 1 ; i++) {
            listeBalle.add(new Balle(LARGEUR/2,HAUTEUR-110,20));
        }
        // CRÉATION DES BRIQUES
        int yRandom = 0;
        for (int i=0;i<10;i++) {
            int xRandom = 0;
            for(int j=0;j<5;j++) {
                listeBrique.add(new Brique(xRandom,yRandom,Color.darkGray,LARGEUR/5,20));
                for (Brique brique : listeBrique) {
                    listeBonus.add(new Bonus(brique.getCentreX(), brique.getCentreY(), 10));
                }
                xRandom+= LARGEUR/5 + 2;
            }
            yRandom += 22;
        }
    }

    public void demarrer() {
        while(true) {
            try {
                // DÉBUT DU DESSIN
                Graphics2D dessin = (Graphics2D) this.getBufferStrategy().getDrawGraphics();
                if (!pause) {

                    // CE QUI RESET TOUT EN BLANC ENTRE DEUX IMAGES
                    dessin.setColor(Color.WHITE);
                    dessin.fillRect(0,0, LARGEUR, HAUTEUR);

                    // AFFICHAGE DE LA BARRE
                    barre.dessiner(dessin);

                    // AFFICHAGE DES BRIQUES
                    for (Brique brique : listeBrique) {
                        brique.dessiner(dessin);
                    }

                    // AFFICHAGE DES BOUTONS
                    for (CanvasBouton bouton: listeBouton) {
                        bouton.dessiner(dessin);
                    }

                    // DÉPLACEMENT DE LA BALLE ET CE QUE ÇA IMPLIQUE (COLLISION, RETRAIT DE BRIQUES ETC)
                    for(Balle balle : listeBalle) {
                        balle.dessiner(dessin);
                        balle.deplacement();

                        // SI LA BALLE TAPE LA BARRE, ON INVERSE LE DÉPLACEMENT DE LA BALLE
                        if (barre.collision(balle)) {
                            balle.setVitesseVertical(-balle.getVitesseVertical());
                        }

                        // SI LA BALLE TAPE UNE BRIQUE,
                        // ON INVERSE LE DEPLACEMENT DE LA BALLE
                        // ON AJOUTE CETTE BRIQUE A LA LISTE DES BRIQUES A SUPPRIMER
                        // ON INCRÉMENTE LE COMPTEUR POUR LA FIN DU PROGRAMME
                        for (Brique brique : listeBrique) {
                            if (brique.collision(balle)) {
                                balle.setVitesseVertical(-balle.getVitesseVertical());
                                listeBriqueSuppr.add(brique);
                                compteurBrique++;
                            }
                        }

                        // ET ICI ON RETIRE LA BRIQUE TOUCHÉE
                        for (Brique brique : listeBriqueSuppr) {
                            listeBrique.remove(brique);
                            for (Bonus bonus : listeBonus) {
                                if (brique.getCentreX() == bonus.getCentreX() - bonus.getDiametre() / 2 && brique.getCentreY() == bonus.getCentreY() - bonus.getDiametre() / 2) {
                                    listeBonusChute.add(bonus);
                                }
                            }
                        }

                        for (Bonus bonus : listeBonusChute) {
                            if (bonus.getChance20() > 8) { // Condition de 20% de chance qu'un bonus apparaisse
                                bonus.dessiner(dessin);
                                bonus.chute();
                                if (barre.collision(bonus)) {
                                    listeBonusSuppr.add(bonus); // Si collision, on arrêtera de dessiner ce bonus (voir ci-dessous)
                                    barre.barreBonus();
                                }
                            }
                        }

                        for (Bonus bonus : listeBonusSuppr) { // On stoppe le dessin du bonus si collision
                            listeBonusChute.remove(bonus);
                        }

                        // ICI C'EST LES DEUX CONDITIONS DE FIN DU PROGRAMME (PAS FINIE)
                        // VICTOIRE SI LES 50 BRIQUES SONT DÉTRUITES
                        if (compteurBrique == 50) {
                            pause = true;
                            Thread.sleep(1000);
                            dessin.setColor(Color.WHITE);
                            dessin.fillRect(0,0, LARGEUR, HAUTEUR);
                            dessin.setColor(Color.BLACK);
                            dessin.drawString("VICTOIRE !", 220, 260);
                            break;
                        }
                            // DÉFAITE SI LA BALLE PASSE SOUS LA BARRE
                        if (balle.getCentreY()>HAUTEUR-50) {
                            pause = true;
                            Thread.sleep(1000);
                            dessin.setColor(Color.WHITE);
                            dessin.fillRect(0,0, LARGEUR, HAUTEUR);
                            dessin.setColor(Color.BLACK);
                            dessin.drawString("DÉFAITE !", 220, 260);
                            break;
                        }
                    }
                }
                // FIN DU DESSIN
                dessin.dispose();
                this.getBufferStrategy().show();

                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                System.out.println("processus arreté");
            }
        }
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        for (CanvasBouton bouton : listeBouton) {
            if (bouton.collision(e.getX(),e.getY())) {
                bouton.clic();
            }
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

    @Override
    public void keyPressed(KeyEvent e) {
        //quand la touche gauche est enfoncée
        if(e.getKeyCode() == 37) {
            barre.deplacerGauche();
        } else if (e.getKeyCode() == 39) {
            //quand la touche droite est enfoncée
            barre.deplacerDroite();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) {
        new CasseBrique();
    }
}