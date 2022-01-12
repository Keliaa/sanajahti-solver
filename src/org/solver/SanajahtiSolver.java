package org.solver;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.solver.apu.Polku;
import org.solver.haku.Syvyyshaku;
import org.solver.io.SanalistaParseri;
import org.solver.io.Sanalistakuuntelija;
import org.solver.matriisi.Neliömatriisi;

public class SanajahtiSolver implements Sanalistakuuntelija {
	
	private final static String SIJAINTI = "./data/sanalista.txt";
	
	private final static boolean SUURUUSJÄRJESTYS = true; //järjestetäänkö löydetyt sanat suuruusjärjestykseen
	
	private final static boolean STRINGMUOTO = true; //voidaan syöttää kirjaimet String muodossa
	
	private final static char[][] MATRIISI = //sanajahdin taulukko 
		{ 
			{'l', 'u', 'i', 'k'}, 
			{'u', 'e', 'n', 'ä'},
			{'t', 'i', 's', 'p'},
			{'v', 'ä', 'e', 'v'},
		};
	
	private final static String kirjaimet = "xdveaiolmyaosmbn"; //vaihtoehtoinen kirjainten syöttötapa
	
	private char[][] uusiMatriisi = new char[4][4];
	
	private List<Polku> vastaukset = new ArrayList<Polku>();
	
	private Syvyyshaku haku;
	
	public void init() {
		
		long alku = System.currentTimeMillis();
		Neliömatriisi matriisi = null;
		
		if(STRINGMUOTO) {
			int j = -1;
			for (int i=0; i<kirjaimet.length(); i++) {
				char kirjain = kirjaimet.charAt(i);
				if(i%4==0)
					j++;
				uusiMatriisi[j][i%4] = kirjain;
				matriisi = new Neliömatriisi(uusiMatriisi);
			}
		} else {
			matriisi = new Neliömatriisi(MATRIISI);
		}
		
		if(matriisi == null)
			System.err.println("Matriisin konstruktoiminen epäonnistui.");
		
		haku = new Syvyyshaku(matriisi);
		haku.valmisteleSijainnit();
		
		SanalistaParseri parseri = new SanalistaParseri(SIJAINTI, this);
		parseri.lataaLista();
		
		if(SUURUUSJÄRJESTYS) {
			Collections.sort(vastaukset);
		}
	
		System.out.println("Löydetyt sanat");
		for(Polku sana : vastaukset) {
			if(sana.annaSana().length() > 2)
				System.out.println(sana.annaSana());
		}
		
		long loppu = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.print("Ohjelman suoritusaika: " + formatter.format((loppu - alku) / 1000d) + " sekuntia.");
		
	}

	@Override
	public void sanaParsittu(String sana) {
		Polku polku = haku.etsi(sana);
		if(polku != null) {
			if(polku.annaSana().length() > 2) {
				vastaukset.add(polku);
			}
		}
	}
}
