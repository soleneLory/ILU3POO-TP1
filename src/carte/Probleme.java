package carte;

public abstract class Probleme extends Carte {
	
	private Type type;
	
	protected Probleme(Type type) {
		super();
		this.type = type;
	}

	public Type getType() {
		return type;
	}
	
	@Override 
	public boolean equals(Object obj) {
		if( obj instanceof Probleme probleme) {
			return super.equals(obj) && getType() == probleme.getType();
		}
		return false;
	}

	@Override
	public String toString() {
		switch(type) {
		
		}
		return "Probleme [type=" + type + "]";
	}
}
