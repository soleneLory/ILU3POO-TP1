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
	
	
	@Override
	public int hashCode() {
	    int resultat = 31; 
	    
	    //multiplie et on ajoute le hashCode de chaque champ
	   // va creer un code unique 
	    resultat = 31 * resultat + joueurCourant.hashCode();
	    resultat = 31 * resultat + carteJoue.hashCode();
	    if (joueurCible != null) {
	        resultat = 31 * resultat + joueurCible.hashCode();
	    } else {
	        resultat = 31 * resultat + 0;
	    }
	    
	    return resultat;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Coup coup) {
			return joueurCourant.equals(coup.getJoueurCourant()) 
					&& ((joueurCible ==  null && coup.getJoueurCible() == null) || joueurCible.equals(coup.getJoueurCible()))
					&& carteJoue.equals(coup.getCarteJoue());
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder chaine = new StringBuilder();
		if(joueurCible == null) {
			chaine.append("défausse la carte ").append(carteJoue.toString());
		}else {
			chaine.append("dépose la carte ").append(carteJoue.toString())
			.append(" dans la zone de jeu de ").append(joueurCible.getName());
		}
		return chaine.toString();
	}
	
	
	
	

}
