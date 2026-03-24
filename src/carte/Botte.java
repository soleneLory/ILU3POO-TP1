package carte;

public class Botte extends Probleme {

	public Botte(Type type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		String renvoi = null;
		switch(getType()) {
			case FEU:
				renvoi = "Prioritaire";
				break;
			case ESSENCE:
				renvoi = "Citerne";
				break;
			case CREVAISON:
				renvoi = "Increvable";
				break;
			case ACCIDENT:
				renvoi = "As du volant";
				break;
			
		}
		return renvoi;
	}
	
	

	

}
