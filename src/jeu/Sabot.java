package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import carte.Carte;

public class Sabot implements Iterable<Carte> {
	private Carte[] cartes;
	private int nbCartes;
	
	private int nbOperations = 0;
	
	public Iterator<Carte> iterator(){
		return new Iterateur();
	}
	
	private class Iterateur implements Iterator<Carte>{
		private int indiceIterateur = 0;
		private boolean nextEffectue = false;
		private int nbOpeRef = nbOperations;
	
		@Override
		public boolean hasNext() {
			return indiceIterateur < nbCartes;
		}
		
		@Override
		public Carte next() {
			verifConcu();
			if(hasNext()) {
				Carte carte = cartes[indiceIterateur];
				indiceIterateur ++;
				nextEffectue = true;
				return carte;
			}else {
				throw new NoSuchElementException("Fin de la pioche");
			}
		}
		
		@Override
		public void remove() {
			verifConcu();
			if(nbCartes< 1 || !nextEffectue) {
				throw new IllegalStateException();
			}
			for (int i = indiceIterateur -1 ; i < nbCartes - 1; i++) {
				cartes[i] = cartes[i+1];
			}
			nextEffectue = false;
			indiceIterateur --;
			nbCartes --;
			nbOperations ++;
			nbOpeRef ++;
		}
		
		private void verifConcu() {
			if(nbOperations != nbOpeRef) {
				throw new ConcurrentModificationException();
			}
		}
	}

	public Sabot(Carte[] cartes) {
		super();
		this.cartes = cartes;
		nbCartes = cartes.length;
	}
	
	public Boolean estVide() {
		return nbCartes == 0;
	}
	
	public void ajouterCarte(Carte carte) {
		if(nbCartes< cartes.length) {
			cartes[nbCartes] = carte;
			nbCartes ++;
			nbOperations ++;
		}else {
			throw new ArrayIndexOutOfBoundsException("Plus de place dans la pioche !!!");
		}
	}
	
	public Carte piocher() {
		Iterator<Carte> iterateur = iterator();
		
		Carte suivant = iterateur.next();
		iterateur.remove();
		return suivant;
	}
	
	
	
	

}
