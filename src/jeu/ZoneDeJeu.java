package jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import carte.Bataille;
import carte.Borne;
import carte.Carte;
import carte.Cartes;
import carte.DebutLimite;
import carte.FinLimite;
import carte.Limite;
import carte.Parade;

public class ZoneDeJeu {
	private List<Limite> pileLimites = new ArrayList<>();
	private List<Bataille> pileBataille = new ArrayList<>();
	private Collection<Borne> collectionBornes = new ArrayList<>();
	
	public int donnerLimitationVitesse() {
		if(!pileLimites.isEmpty() && (pileLimites.remove(pileLimites.size() -1) instanceof DebutLimite)) {
			return 50;
		}
		return 200;
	}
	
	public int donnerKmParcourus() {
		int sumBorne = 0;
		
		for(Iterator<Borne> itBorne = collectionBornes.iterator(); itBorne.hasNext();) {
			sumBorne += itBorne.next().getKm();
		}
		return sumBorne;	
	}
	
	public void deposer(Carte carte) {
		if(carte instanceof Borne borne) {
			collectionBornes.add(borne);
		}if(carte instanceof Limite limite) {
			pileLimites.add(limite);
		}if(carte instanceof Bataille bataille) {
			pileBataille.add(bataille);
		}
	}
	
	public boolean peutAvancer() {
		if(!pileBataille.isEmpty() && pileBataille.remove(pileBataille.size() -1).equals(Cartes.FEU_VERT)) {
			return true;
		}
		return false;
	}
	
	public boolean estDepotfeuVertAutorise() {
		if(!pileBataille.isEmpty()) {
			Bataille sommet = pileBataille.remove(pileBataille.size() -1);
			if(!sommet.equals(Cartes.FEU_ROUGE) &&  (!(sommet instanceof Parade) || sommet.equals(Cartes.FEU_VERT))) {
				return false;
			}
		}
		return true;
	}

}
