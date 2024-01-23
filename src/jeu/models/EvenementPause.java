package jeu.models;

public class EvenementPause implements EvenementBouton {

    @Override
    public void declenche() {
        System.out.println("PAUSE");
    }
}

