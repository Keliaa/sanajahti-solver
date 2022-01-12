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
import org.solver.matriisi.Neli�matriisi;

public class SanajahtiSolver implements Sanalistakuuntelija {
	
	private final static String SIJAINTI = "./data/sanalista.txt";
	
	private final static boolean SUURUUSJ�RJESTYS = true; //j�rjestet��nk� l�ydetyt sanat suuruusj�rjestykseen
	
	private final static boolean STRINGMUOTO = true; //voidaan sy�tt�� kirjaimet String muodossa
	
	private final static char[][] MATRIISI = //sanajahdin taulukko 
		{ 
			{'l', 'u', 'i', 'k'}, 
			{'u', 'e', 'n', '�'},
			{'t', 'i', 's', 'p'},
			{'v', '�', 'e', 'v'},
		};
	
	private final static String kirjaimet = "xdveaiolmyaosmbn"; //vaihtoehtoinen kirjainten sy�tt�tapa
	
	private char[][] uusiMatriisi = new char[4][4];
	
	private List<Polku> vastaukset = new ArrayList<Polku>();
	
	private Syvyyshaku haku;
	
	public void init() {
		
		long alku = System.currentTimeMillis();
		Neli�matriisi matriisi = null;
		
		if(STRINGMUOTO) {
			int j = -1;
			for (int i=0; i<kirjaimet.length(); i++) {
				char kirjain = kirjaimet.charAt(i);
				if(i%4==0)
					j++;
				uusiMatriisi[j][i%4] = kirjain;
				matriisi = new Neli�matriisi(uusiMatriisi);
			}
		} else {
			matriisi = new Neli�matriisi(MATRIISI);
		}
		
		if(matriisi == null)
			System.err.println("Matriisin konstruktoiminen ep�onnistui.");
		
		haku = new Syvyyshaku(matriisi);
		haku.valmisteleSijainnit();
		
		SanalistaParseri parseri = new SanalistaParseri(SIJAINTI, this);
		parseri.lataaLista();
		
		if(SUURUUSJ�RJESTYS) {
			Collections.sort(vastaukset);
		}
	
		System.out.println("L�ydetyt sanat");
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
