package carte;

public class Attaque extends Bataille {

	public Attaque(Type type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		String renvoi =  "Attaque [";
		switch(getType()) {
			case FEU:
				renvoi = renvoi + "Feu Rouge]";
				break;
			case ESSENCE:
				renvoi = renvoi + "Panne d'essence]";
				break;
			case CREVAISON:
				renvoi = renvoi + "Crevaison]";
				break;
			case ACCIDENT:
				renvoi = renvoi + "Accident]";
				break;
			
		}
		return renvoi;
	}

}
