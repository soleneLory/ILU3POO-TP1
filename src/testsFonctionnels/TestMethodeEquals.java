package testsFonctionnels;

import carte.Attaque;
import carte.Borne;
import carte.Carte;
import carte.Parade;
import carte.Type;

public class TestMethodeEquals {
	public static void main(String[] args) {
		Carte carte25Bornes1 = new Borne(25);
		Carte carte25Bornes2 = new Borne(25);
		System.out.println("Deux cartes de 25km sont identiques ? " + carte25Bornes1.equals(carte25Bornes2));
	
		Carte carteFeuxRouge1 = new Attaque(Type.FEU);
		Carte carteFeuxRouge2 = new Attaque(Type.FEU);
		System.out.println("Deux cartes de feux rouge sont identiques ? " + carteFeuxRouge1.equals(carteFeuxRouge2));
		
		Carte carteFeuxVert = new Parade(Type.FEU);
		System.out.println("La carte feu rouge et la carte feu vert sont identiques ? " + carteFeuxRouge1.equals(carteFeuxVert));
	}
}
