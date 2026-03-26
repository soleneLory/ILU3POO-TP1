package carte;

public abstract class Bataille extends Probleme {

	protected Bataille(Type type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Bataille bataille) {
			return (getType().equals(bataille.getType())) && (getClass() == bataille.getClass());
		}
		return false;
	}

}
