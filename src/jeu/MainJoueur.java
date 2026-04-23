package jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import carte.Carte;

public class MainJoueur {
	private List<Carte> main = new ArrayList<>();
	
	void prendre(Carte carte) {
		main.add(carte);
	}
	
	void jouer(Carte carte) {
		assert(main.contains(carte));
		main.remove(carte);
	}
	
	public int getNbCartes() {
	    return main.size();
	}

	public Carte getCarte(int index) {
	    return main.get(index);
	}

	@Override
	public String toString() {
		StringBuilder stringMain = new StringBuilder();
		
		for(ListIterator<Carte> itMain = main.listIterator(); itMain.hasNext();) {
			stringMain.append(itMain.next());
		}
		return stringMain.toString();	
	}
}
