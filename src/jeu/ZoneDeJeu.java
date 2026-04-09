package jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import carte.Attaque;
import carte.Bataille;
import carte.Borne;
import carte.Botte;
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
	private Map<Type, Botte> bottes = new HashMap<>();
	
	public int donnerLimitationVitesse() {
		if(!estPrioritaire() && !pileLimites.isEmpty() && (pileLimites.getLast() instanceof DebutLimite)) {
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
		}if (carte instanceof Botte botte) {
			if(!bottes.containsKey(botte.getType())) {
				bottes.put(botte.getType(), botte);
			}
		}
	}
	
	public boolean peutAvancer() {
		if(!pileBataille.isEmpty()) {
			Bataille sommet = pileBataille.getLast();
			if(sommet.equals(Cartes.FEU_VERT)) {
				return true;
			}if(sommet instanceof Attaque attaque) {
				return bottes.containsKey(attaque.getType());
			}
		}
		return estPrioritaire() ;
	}
	
	private boolean estDepotfeuVertAutorise() {
		if(estPrioritaire()) {
			return false;
		}
		if(!pileBataille.isEmpty()) {
			Bataille sommet = pileBataille.getLast();
			if(sommet instanceof Parade) {
				return !sommet.equals(Cartes.FEU_VERT);
			}
			if(sommet instanceof Attaque attaque) {
				return sommet.equals(Cartes.FEU_ROUGE) || bottes.containsKey(attaque.getType());
			}//bottes.contains(new Botte(attaque.getType))
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
			return donnerLimitationVitesse() == 200 && !estPrioritaire();
		}
		return donnerLimitationVitesse() == 50 && !estPrioritaire();
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if(bottes.containsKey(bataille.getType())) {
			return false;
		}
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
	
	public boolean estPrioritaire() {
		if(bottes.containsKey(Type.FEU)) {
			return true;
		}
		return false;
	}

}
