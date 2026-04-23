package testsFonctionnels;

import jeu.Jeu;
import jeu.Joueur;
import jeu.MainJoueur;
import jeu.ZoneDeJeu;

public class TestJeu {
    public static void main(String[] args) {
        Jeu milleBornes = new Jeu();

        Joueur luffy = new Joueur("Luffy", new ZoneDeJeu(), new MainJoueur());
        Joueur jack = new Joueur("Jack", new ZoneDeJeu(), new MainJoueur());
        Joueur bill = new Joueur("Bill", new ZoneDeJeu(), new MainJoueur());

        milleBornes.inscrire(luffy, jack, bill);

        milleBornes.distribuerCartes();

        System.out.println("debut de la partie\n");
        
        String resultatPartie = milleBornes.jouerTour(jack);
        System.out.println(resultatPartie);
        resultatPartie = milleBornes.jouerTour(bill);
        System.out.println(resultatPartie);
        resultatPartie = milleBornes.jouerTour(luffy);
        System.out.println(resultatPartie);
        
        System.out.println("fin de la partie \n");
        
   
    }
}
