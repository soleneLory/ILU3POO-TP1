package jeu;

import carte.Carte;

public class Joueur {
	private String name;
	private ZoneDeJeu zoneDeJeu;
	private MainJoueur mainJoueur;
	
	public Joueur(String name) {
		this.name = name;
		zoneDeJeu = new ZoneDeJeu();
		mainJoueur = new MainJoueur();
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
	
	
	
}
