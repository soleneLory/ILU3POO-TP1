package carte;

public class Attaque extends Bataille {

	public Attaque(Type type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		String renvoi =  null ;
		switch(getType()) {
			case FEU:
				renvoi = "Feu Rouge";
				break;
			case ESSENCE:
				renvoi = "Panne d'essence";
				break;
			case CREVAISON:
				renvoi = "Crevaison";
				break;
			case ACCIDENT:
				renvoi =  "Accident";
				break;
			
		}
		return renvoi;
	}

}
