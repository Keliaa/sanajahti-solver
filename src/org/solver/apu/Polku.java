package org.solver.apu;

import java.util.ArrayList;
import java.util.List;

import org.solver.matriisi.Alkio;

public class Polku implements Comparable<Polku> {
	
	private List<Alkio> alkiot = new ArrayList<Alkio>();
	
	public Polku(Alkio juuri) {
		alkiot.add(juuri);
	}
	
	public Alkio annaEdellinen() {
		return alkiot.get(alkiot.size()-1);
	}
	
	public void lis‰‰(Alkio alkio) {
		alkiot.add(alkio);
	}
	
	public boolean sis‰lt‰‰(Alkio alkio) {
		if(alkiot.contains(alkio))
			return true;
		
		return false;
	}
	
	public String annaSana() {
		String sana = "";
		for (Alkio alkio : alkiot) {
			sana += alkio.annaKirjain();
		}
		return sana;
	}

	public void poistaViimeinen() {
		alkiot.remove(alkiot.size()-1);
		
	}

	@Override
	public int compareTo(Polku o) {
		return o.annaSana().length() - annaSana().length();
	}
}
