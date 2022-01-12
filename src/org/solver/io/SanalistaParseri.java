package org.solver.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SanalistaParseri {
	
	private final String sijainti;
	private final Sanalistakuuntelija kuuntelija;
	
	public SanalistaParseri(String sijainti, Sanalistakuuntelija kuuntelija) {
		this.sijainti = sijainti;
		this.kuuntelija = kuuntelija;
	}
	
	public void lataaLista() {
		try(BufferedReader br = new BufferedReader(new FileReader(sijainti))) {
		    for(String sana; (sana = br.readLine()) != null;) {
		    	 kuuntelija.sanaParsittu(sana);
		    }
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
