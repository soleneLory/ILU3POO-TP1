package testsFonctionnels;

import java.util.Iterator;

import carte.Botte;
import carte.Carte;
import carte.JeuDeCartes;
import jeu.Sabot;
import carte.Type;

public class TestSabot {
	JeuDeCartes jeu = new JeuDeCartes();
	Sabot sabot = new Sabot(jeu.donnerCartes());

	// 4.2.a
	public void questionA() {

		while (!sabot.estVide()) {
			Carte carte = sabot.piocher();
			System.out.println("Je pioche " + carte);
		}
//		Console :
//		Je pioche Accident
//		Je pioche Accident
//		Je pioche Accident
//		Je pioche R�paration
//		Je pioche R�paration
//		Je pioche R�paration
//		Je pioche As du volant
	}

	// 4.2.b
	public void questionB() {
		for (Iterator<Carte> iterator = sabot.iterator(); iterator.hasNext();) {
			System.out.println("Je pioche B" + iterator.next());
			iterator.remove();
		}
	}

	// 4.2.c
	public void questionC() {
		Carte cartePiocheea = sabot.piocher();
		System.out.println("Je pioche " + cartePiocheea);
		for (Iterator<Carte> iterator = sabot.iterator(); iterator.hasNext();) {
			Carte carte = iterator.next();
			System.out.println("Je pioche " + carte);
			iterator.remove();
			Carte cartePiochee = sabot.piocher();
			sabot.ajouterCarte(new Botte(Type.ACCIDENT));
		}
		Iterator<Carte> iterator = sabot.iterator();
		System.out.println("\nLa pioche contient encore des cartes ? " + iterator.hasNext());
	}

	public static void main(String[] args) {
		TestSabot testPioche = new TestSabot();
		//testPioche.questionA();
		//testPioche.questionB();
		testPioche.questionC();
	}

}

