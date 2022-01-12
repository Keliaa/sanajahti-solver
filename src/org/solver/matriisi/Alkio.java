package org.solver.matriisi;

import org.solver.apu.Sijainti;

public class Alkio {
	
	private final Sijainti sijainti;
	private final char kirjain;
	
	public Alkio(char kirjain, Sijainti sijainti) {
		this.kirjain = kirjain;
		this.sijainti = sijainti;
	}
	
	public Sijainti annaSijainti() {
		return sijainti;
	}
	
	public char annaKirjain() {
		return kirjain;
	}
	
	@Override
	public String toString() {
		return ""+kirjain;
	}
	
	@Override
	public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
 
        if (!(o instanceof Alkio)) {
            return false;
        }
         
        Alkio a = (Alkio) o;
         
        return annaSijainti().equals(a.annaSijainti());
	}
	
}
