package org.solver.apu;

public class Sijainti {
	
	private final int x;
	private final int y;
	
	public Sijainti(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int annaX() {
		return x;
	}
	
	public int annaY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "(X: " + x + " Y: " + y + ")";
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
        }
 
        if (!(o instanceof Sijainti)) {
            return false;
        }
        
        Sijainti s = (Sijainti) o;
         
        return Integer.compare(x, s.x) == 0 && Integer.compare(y, s.y) == 0;
	}
}
