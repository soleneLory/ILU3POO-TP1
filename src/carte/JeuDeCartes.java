package carte;

public class JeuDeCartes {
	private Configuration[] typesDeCartes = new Configuration[19];
	
	public String affichageJeuDeCartes() {
		String affichage = "JEU:\n";
		for (int i = 0; i < typesDeCartes.length; i++) {
			affichage += "\n" + typesDeCartes[i].getNbExemplaires() + " " + typesDeCartes[i].getCarte().toString();
		}
		
		return affichage;
	}
	
	private class Configuration{
		
		private int nbExemplaires;
		private Carte carte;
		
		public Configuration( Carte carte, int nbExemplaires) {
			this.nbExemplaires = nbExemplaires;
			this.carte = carte;
		}

		public int getNbExemplaires() {
			return nbExemplaires;
		}

		public Carte getCarte() {
			return carte;
		}
		
		
		
		
	}
	
	public Carte[] donnerCartes() {
		int nbCartes = 0;
		for (int i = 0; i < typesDeCartes.length; i++) {
			nbCartes += typesDeCartes[i].getNbExemplaires();
		}
		Carte[] cartes= new Carte[nbCartes];
		int iCartes = 0;
		
		for (int i = 0; i < typesDeCartes.length; i++) {
			for(int j=0; j<typesDeCartes[i].nbExemplaires; j++) {
				//TOFINISH
			}
		}
	}

}
