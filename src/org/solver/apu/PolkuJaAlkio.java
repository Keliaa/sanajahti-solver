package org.solver.apu;

import org.solver.matriisi.Alkio;

public class PolkuJaAlkio {
	
	private Alkio alkio;
	
	private Polku polku;
	
	public PolkuJaAlkio(Polku polku, Alkio alkio) {
		this.alkio = alkio;
		this.setPolku(polku);
	}

	public Alkio getAlkio() {
		return alkio;
	}

	public Polku getPolku() {
		return polku;
	}

	public void setPolku(Polku polku) {
		this.polku = polku;
	}

}
