package jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

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
	private Set<Botte> bottes = new HashSet<>();
	

	public Set<Botte> getBottes(){
		return bottes;
	}
	
	public boolean presenceLimitation() {
		return donnerLimitationVitesse()==50;
	}
	
	public Bataille getSommeBataille() {
		if(pileBataille.isEmpty())
			return null;
		return pileBataille.getLast();
	}
	
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
			bottes.add(botte);
		}
	}
	
	public boolean peutAvancer() {
		if(!pileBataille.isEmpty()) {
			Bataille sommet = pileBataille.getLast();
			if(sommet.equals(Cartes.FEU_VERT)) {
				return true;
			}if(sommet instanceof Attaque attaque) {
				return bottes.contains(new Botte(attaque.getType()));
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
				return sommet.equals(Cartes.FEU_ROUGE) || bottes.contains(new Botte(attaque.getType()));
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
		if(bottes.contains(new Botte(bataille.getType()))) {
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
		return bottes.contains(Cartes.PRIORITAIRE);
	}

}
//package jeu;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.ListIterator;
//import java.util.Map;
//import java.util.Set;
//
//import carte.Attaque;
//import carte.Bataille;
//import carte.Borne;
//import carte.Botte;
//import carte.Cartes;
//import carte.Carte;
//import carte.DebutLimite;
//import carte.FinLimite;
//import carte.Limite;
//import carte.Parade;
//import carte.Type;
//
//public class ZoneDeJeu {
//	private List<Limite> pileLimite= new ArrayList<>();
//	private List<Bataille> pileBataille= new ArrayList<>();
//	private Collection<Borne> collectionBorne=new ArrayList<>();
//	private Set<Botte> bottes= new HashSet<>(); 
//	
//	public Set<Botte> getBottes(){
//		return bottes;
//	}
//	
//	public boolean presenceLimitation() {
//		return donnerLimitationVitesse()==50;
//	}
//	
//	public Bataille getSommeBataille() {
//		if(pileBataille.isEmpty())
//			return null;
//		return pileBataille.getLast();
//	}
//	
//	public int donnerLimitationVitesse() {
//		if(!estPrioritaire() && !pileLimite.isEmpty() && (pileLimite.getLast() instanceof DebutLimite)) {
//			return 50;
//		}
//		return 200;
//	}
//	
//	public int donnerKmParcourus() {
//		int accBorne=0;
//		for (Iterator<Borne> itCollection = collectionBorne.iterator(); itCollection.hasNext();) {
//			accBorne += itCollection.next().getKm();
//		}
//		return accBorne;
//	}
//	
//	public void deposer(Carte c) {
//		if(c instanceof Borne borne) {
//			collectionBorne.add(borne);
//		}
//		if(c instanceof Limite limite) {
//			pileLimite.add(limite);
//		}
//		if(c instanceof Bataille bataille) {
//			pileBataille.add(bataille);
//		}
//		if(c instanceof Botte botte) {
//			//grace au equals et au hashcode, le hashset sauura si'l contient drja une bottte de ce type precis
//			 bottes.add(botte);
//		}
//	}
//	public boolean peutAvancer() {
//	 	if (!pileBataille.isEmpty() && pileBataille.getLast().equals(Cartes.FEU_VERT)) {
//	        return true;
//	    }
//	 	
//	    if (!estPrioritaire()) {
//	        return false;
//	    }
//	    
//	    if (pileBataille.isEmpty() || pileBataille.getLast() instanceof Parade) {
//	        return true;
//	    }
//
//	    Bataille sommet = pileBataille.getLast();
//	    return (sommet instanceof Attaque && bottes.contains(new Botte(sommet.getType())));
//	}
//	
//	private boolean estDepotFeuVertAutorise() {
//		if(estPrioritaire())
//			return false;
//		if(!pileBataille.isEmpty()) {
//			Bataille sommet = pileBataille.getLast();
//			if(sommet instanceof Parade) {
//				return !sommet.equals(Cartes.FEU_VERT);
//			}
//			if(sommet instanceof Attaque attaque) {
//				return sommet.equals(Cartes.FEU_ROUGE) || bottes.contains(new Botte(attaque.getType()));
//			}
//		}
//		return  true;
//	}
//		
//	private boolean estDepotBorneAutorise(Borne borne) {
//		if (peutAvancer() && donnerLimitationVitesse() >= borne.getKm() 
//				&& donnerKmParcourus() < 1000) {
//			return true;
//		}
//		return false;
//	}
//	
//	private boolean estDepotLimiteAutorise(Limite limite) {
//		if(limite instanceof DebutLimite) {
//			return donnerLimitationVitesse()== 200 && !estPrioritaire() ;
//		}
//		return donnerLimitationVitesse() == 50 && !estPrioritaire();
//	}
//	
//	private boolean estDepotBatailleAutorise(Bataille bataille) {
//		if(bottes.contains(new Botte(bataille.getType()))) {
//			return false;
//		}
//		if(bataille instanceof Attaque) {
//			return peutAvancer();
//			
//		} if(bataille.getType() == Type.FEU ) {
//			return estDepotFeuVertAutorise() ;	
//			
//		} if(! pileBataille.isEmpty()){
//			Bataille sommet = pileBataille.getLast();
//			return sommet instanceof Attaque && sommet.getType() == bataille.getType();
//		}
//		
//		return false;
//	}
//	
//	
//	public boolean estDepotAutorise(Carte carte) {
//		if(carte instanceof Borne borne) {
//			return estDepotBorneAutorise(borne);
//		}
//		if(carte instanceof Limite limite) {
//			return estDepotLimiteAutorise(limite);
//		}
//		if(carte instanceof Bataille bataille) {
//			return estDepotBatailleAutorise(bataille);
//		}	
//		return true;
//	}
//	
//	
//	
//	public boolean estPrioritaire() {
//		return bottes.contains(Cartes.PRIORITAIRE);
//	}
//	
//	
//}