package org.solver.haku;

import java.util.Collection;
import java.util.Set;

import org.solver.apu.Polku;
import org.solver.matriisi.Alkio;
import org.solver.matriisi.Neli�matriisi;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class Syvyyshaku {
	
	private Neli�matriisi matriisi;
	
	private String sana;
	
	private Multimap<String, Alkio> kirjaintenSijainti = HashMultimap.create();
	
	public Syvyyshaku(Neli�matriisi matriisi) {
		this.matriisi = matriisi;
	}
	
	public void valmisteleSijainnit() {
		for (int i=0; i<matriisi.annaRivikoko(); i++) {
			for (int j=0; j<matriisi.annaRivikoko(); j++) {
				kirjaintenSijainti.put(String.valueOf(matriisi.annaAlkiot()[i][j].annaKirjain()), matriisi.annaAlkiot()[i][j]);
			}
		}
	}
	
	public Polku etsi(String sana) {
		this.sana = sana;
		
		Collection<Alkio> sijainnit = kirjaintenSijainti.get(String.valueOf(sana.charAt(0)));
		for(Alkio sijaintiAlkio : sijainnit) {
			for (Alkio viereinenAlkio : matriisi.annaViereisetAlkiot(matriisi.annaAlkiot()[sijaintiAlkio.annaSijainti().annaX()][sijaintiAlkio.annaSijainti().annaY()])) {
				Polku testattavaPolku = new Polku(matriisi.annaAlkiot()[sijaintiAlkio.annaSijainti().annaX()][sijaintiAlkio.annaSijainti().annaY()]);
				if(sana.startsWith(testattavaPolku.annaSana()+viereinenAlkio.annaKirjain())) {
					Polku vastausPolku = vieraile(testattavaPolku, viereinenAlkio);
					if(vastausPolku != null) {
						if(testattavaPolku.annaSana().equals(sana)) {
							return vastausPolku;
						}
					}
				}
			}
		}
		return null;
	}
	
	private Polku vieraile(Polku polku, Alkio alkio) {
		Set<Alkio> viereisetAlkiot = matriisi.annaViereisetAlkiot(alkio);
		String testattavaSana = polku.annaSana()+alkio.annaKirjain();
		if(sana.equalsIgnoreCase(testattavaSana)) {
			polku.lis��(alkio);
			return polku;
		}
		for(Alkio viereinen : viereisetAlkiot) {
			if(sana.startsWith(testattavaSana+viereinen.annaKirjain()) && !polku.sis�lt��(viereinen)) {
				if(!polku.sis�lt��(alkio))
					polku.lis��(alkio);
				vieraile(polku, viereinen);
			}
		}
		return polku;
	}

}
