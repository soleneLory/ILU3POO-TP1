package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import carte.Carte;
import carte.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;

	public Jeu() {
		JeuDeCartes jeuDeCartes = new JeuDeCartes();
		List<Carte> listCartes = new ArrayList<>();
		
		Collections.addAll(listCartes, jeuDeCartes.donnerCartes());
		listCartes = GestionCartes.melanger(listCartes);
		
		sabot = new Sabot((Carte[]) listCartes.toArray());
		
	}

}
