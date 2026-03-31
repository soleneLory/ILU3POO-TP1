package jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import carte.Attaque;
import carte.Bataille;
import carte.Borne;
import carte.Carte;
import carte.Cartes;
import carte.DebutLimite;
import carte.FinLimite;
import carte.Limite;
import carte.Parade;
import carte.Type;

public class ZoneDeJeu {
	private List<Limite> pileLimites = new ArrayList<>();
	private List<Bataille> pileBataille = new ArrayList<>();
	private Collection<Borne> collectionBornes = new ArrayList<>();
	
	public int donnerLimitationVitesse() {
		if(!pileLimites.isEmpty() && (pileLimites.getLast() instanceof DebutLimite)) {
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
		if(!pileBataille.isEmpty() && pileBataille.getLast().equals(Cartes.FEU_VERT)) {
			return true;
		}
		return false;
	}
	
	private boolean estDepotfeuVertAutorise() {
		if(!pileBataille.isEmpty()) {
			Bataille sommet = pileBataille.getLast();
			if(!sommet.equals(Cartes.FEU_ROUGE) &&  (!(sommet instanceof Parade) || sommet.equals(Cartes.FEU_VERT))) {
				return false;
			}
		}
		return true;
	}
	
	private boolean estDepotBorneAutorise(Borne borne) {
		if (peutAvancer() && donnerLimitationVitesse() >= borne.getKm() 
				&& donnerKmParcourus() < 1000) {
			return true;
		}
		return false;
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
		if(limite instanceof DebutLimite) {
			return donnerLimitationVitesse() == 200;
		}
		return donnerLimitationVitesse() == 50;
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if(bataille instanceof Attaque) {
			return peutAvancer();
		} if(bataille.getType() == Type.FEU) {
			return estDepotfeuVertAutorise() ;	
		} if(! pileBataille.isEmpty()){
			Bataille sommet = pileBataille.getLast();
			return sommet instanceof Attaque && sommet.getType() == bataille.getType();
		}
		return false;
	}
	
	public boolean estDepotAutorise(Carte carte) {
		if(carte instanceof Borne borne) {
			return estDepotBorneAutorise(borne);
		}
		if(carte instanceof Limite limite) {
			return estDepotLimiteAutorise(limite);
		}
		if(carte instanceof Bataille bataille) {
			return estDepotBatailleAutorise(bataille);
		}
		return true;
	}

}
