package carte;

public class Parade extends Bataille {

	public Parade(Type type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		String renvoi = null;
		switch(getType()) {
			case FEU:
				renvoi ="Feu Vert";
				break;
			case ESSENCE:
				renvoi = "Essence";
				break;
			case CREVAISON:
				renvoi = "Roue de secours";
				break;
			case ACCIDENT:
				renvoi = "Réparations";
				break;
			
		}
		return renvoi;
	}
	
	

}
