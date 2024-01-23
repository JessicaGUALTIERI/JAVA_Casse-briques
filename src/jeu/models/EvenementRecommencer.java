package jeu.models;

public class EvenementRecommencer implements EvenementBouton {
    @Override
    public void declenche() {
        System.out.println("RECOMMENCER PARTIE");
    }
}
