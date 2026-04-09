package jeu;

import carte.Attaque;
import carte.Carte;
import carte.DebutLimite;

public class Coup {
	private Joueur joueurCourant;
	private Carte carteJoue;
	private Joueur joueurCible;
	
	public Coup(Joueur joueurCourant, Carte carteJoue, Joueur joueurCible) {
		super();
		this.joueurCourant = joueurCourant;
		this.carteJoue = carteJoue;
		this.joueurCible = joueurCible;
	}

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public Carte getCarteJoue() {
		return carteJoue;
	}

	public Joueur getJoueurCible() {
		return joueurCible;
	}
	
	public boolean estValide() {
		if(joueurCible!= null && joueurCible.estDepotAutorise(carteJoue)) {
			if(carteJoue instanceof Attaque || carteJoue instanceof DebutLimite) {
				return ! joueurCible.equals(joueurCourant) ;
			}else {
				return joueurCible.equals(joueurCourant); 
			}
			
		}
		return joueurCible == null;
	}
	
	
	
	

}
