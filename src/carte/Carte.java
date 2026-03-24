package carte;

public abstract class Carte {
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Carte carte) {
			return (obj != null) && (getClass() == carte.getClass());
		}
		return false;
	}
	
}
