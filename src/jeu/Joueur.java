package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import carte.Borne;
import carte.Botte;
import carte.Carte;
import carte.Type;

public class Joueur {

	private String name;
	private ZoneDeJeu zoneDeJeu;
	private MainJoueur mainJoueur;
	
//	public Joueur(String name) {
//		this.name = name;
//		zoneDeJeu = new ZoneDeJeu();
//		mainJoueur = new MainJoueur();
//	}
//	
	public Joueur(String name, ZoneDeJeu zoneDeJeu, MainJoueur mainJoueur) {
		super();
		this.name = name;
		this.zoneDeJeu = zoneDeJeu;
		this.mainJoueur = mainJoueur;
	}

	
	public String getName() {
		return name;
	}


	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Joueur joueur) {
			return (obj != null) && (this.name == joueur.getName());
		}
		return false;
	}


	@Override
	public String toString() {
		return "Joueur " + name ;
	}


	public MainJoueur getMainJoueur() {
		return mainJoueur;
	}
	
	public void donner(Carte carte) {
		mainJoueur.prendre(carte);
	}
	
	public Carte prendreCarte(Sabot sabot) {
		Carte carte = sabot.piocher();
		donner(carte);
		return carte;
	}
	
	public int donnerKmParcourus() {
		return zoneDeJeu.donnerKmParcourus();
	}
	
	public void deposer(Carte carte) {
		zoneDeJeu.deposer(carte);
	}
	
	public boolean estDepotAutorise(Carte carte) {
		return zoneDeJeu.estDepotAutorise(carte);
	}
	
	public Set<Coup> coupsPossibles(Set<Joueur> participants){
		Set<Coup> coups = new HashSet<>();
		
		Iterator<Joueur> iteratorJ = participants.iterator();
		
		for(Iterator<Joueur> itJoueur = participants.iterator(); itJoueur.hasNext();) {
			Joueur jCourant = itJoueur.next();
			
			for(int i=0; i<mainJoueur.getNbCartes(); i++) {
				Carte cCourant = mainJoueur.getCarte(i);
				Coup coup = new Coup(this, cCourant, jCourant);
				if(coup.estValide()) {
					coups.add(coup);
				}
			}
		}
		return coups;
		
	}
	
	// Methodes necessaires à  l'utilisation d'un HashSet -> HashCode et equals
	// Refaire dans zone de jeu l'implementation de bottes par un set
	// 
	
	
	public Set<Coup> coupsDefausse(){
		Set<Coup> coups = new HashSet<>();
		
		for(int i=0; i<mainJoueur.getNbCartes(); i++) {
			Carte cCourant = mainJoueur.getCarte(i);
			Coup coup = new Coup(this, cCourant, null);
			if(coup.estValide()) {
				coups.add(coup);
			}
		}
		return coups;
	}
	
	
	public void retirerDeLaMain(Carte carte) {
		mainJoueur.jouer(carte);
	}
	
	private Coup choisirRandom(Set<Coup> coups) {
	    List<Coup> liste = new ArrayList<>(coups);
	    Random random = new Random();
	    int indexAleatoire = random.nextInt(liste.size());
	    return liste.get(indexAleatoire);
	}
	
	public Coup choisirCoup(Set<Joueur> participants) {
		Set<Coup> coupsPossibles = coupsPossibles(participants);
		if(!coupsPossibles.isEmpty()) {
			return choisirRandom(coupsPossibles);
		}else {
			return choisirRandom(coupsDefausse());
		}
	}
	
	public String afficherEtatJoueur() {
		StringBuilder chaine= new StringBuilder();
		chaine.append("Etat de ").append(name).append(":\n");
		chaine.append("- Bottes : ").append(zoneDeJeu.getBottes()).append("\n");
		chaine.append("- Limitation : ").append(zoneDeJeu.presenceLimitation()).append("\n");;
		chaine.append("- Somme bataille : ").append(zoneDeJeu.getSommeBataille()).append("\n");
		chaine.append("- Main : ").append(mainJoueur.toString()).append("\n");
		return chaine.toString();
	}
}
