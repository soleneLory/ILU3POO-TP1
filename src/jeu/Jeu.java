package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import carte.Carte;
import carte.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;
	private Set<Joueur> joueurs = new HashSet<>();

	public Jeu() {
		JeuDeCartes jeuDeCartes = new JeuDeCartes();
		List<Carte> listCartes = new ArrayList<>();
		
		Collections.addAll(listCartes, jeuDeCartes.donnerCartes());
		listCartes = GestionCartes.melanger(listCartes);
		
		sabot = new Sabot((Carte[]) listCartes.toArray(new Carte[0]));
		
	}
	
	public void inscrire(Joueur... nouveauxJoueurs) {
		for(int i=0; i<nouveauxJoueurs.length; i++) {
			joueurs.add(nouveauxJoueurs[i]);
		}
	}
	
	public void distribuerCartes() {
		int NBCARTES = 6;
		for(Iterator<Joueur> itJoueur = joueurs.iterator(); itJoueur.hasNext();) {
			Joueur cJoueur = itJoueur.next();
			for(int i=0; i<NBCARTES; i++) {
				cJoueur.donner(sabot.piocher());
			}
		}
	}
	
	public String jouerTour(Joueur joueur) {
		Carte cartePiochee=joueur.prendreCarte(this.sabot);
		
		Set<Joueur> participants = new HashSet<>(this.joueurs);
		Coup coupChoisi= joueur.choisirCoup(participants);
		
		joueur.retirerDeLaMain(coupChoisi.getCarteJoue());
		
		if(coupChoisi.getJoueurCible() == null) {
			sabot.ajouterCarte(coupChoisi.getCarteJoue());
		}
		else {
			coupChoisi.getJoueurCible().deposer(coupChoisi.getCarteJoue());
		}
		
		StringBuilder chaine = new StringBuilder();
		chaine.append("Le joueur ").append(joueur.getName()).append(" a pioché ").append(cartePiochee).append("\n");
		chaine.append("\n");
		chaine.append(joueur.getName()).append(" ").append(coupChoisi.toString());
		chaine.append("\n");
		return chaine.toString();
	}

}
