package carte;

public class Borne extends Carte {
	private int km;
	
	
	public Borne(int km) {
		super();
		this.km = km;
	}


	@Override
	public String toString() {
		return km + "KM";
	}
	

	@Override 
	public boolean equals(Object obj) {
		if( obj instanceof Borne borne) {
			return (km!=0) && (km == borne.getKm());
		}
		return false;
	}


	private int getKm() {
		return km;
	}
	
	

	
}
