package org.solver.matriisi;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.solver.apu.Sijainti;

import com.google.common.base.Preconditions;

public class Neliömatriisi {
	
	private final int rivikoko;
	
	private final Alkio[][] alkiot;
	
	public Neliömatriisi(int rivikoko) {
		Preconditions.checkArgument(rivikoko > 0, "Matriisin rivikoko ei voi olla negatiivinen");
		this.rivikoko = rivikoko;
		this.alkiot = arvoAlkiot();
	}
	
	public Neliömatriisi(Alkio[][] alkiot) {
		Preconditions.checkArgument(alkiot[0].length == alkiot[1].length, "Matriisin täytyy olla neliömatriisi");
		Preconditions.checkNotNull(alkiot);
		this.rivikoko = alkiot[0].length;
		this.alkiot = alkiot;
	}
	
	public Neliömatriisi(char[][] kirjaimet) {
		Preconditions.checkArgument(kirjaimet[0].length == kirjaimet[1].length, "Matriisin täytyy olla neliömatriisi");
		Preconditions.checkNotNull(kirjaimet);
		this.rivikoko = kirjaimet[0].length;
		this.alkiot = luoAlkiot(kirjaimet);
	}
	
	public Set<Alkio> annaViereisetAlkiot(Alkio alkio) {
		int x = alkio.annaSijainti().annaX();
		int y = alkio.annaSijainti().annaY();
		Set<Alkio> viereiset = new HashSet<Alkio>();
		for (int i=-1; i<=1; i++) {
			for (int j=-1; j<=1; j++) {
				if (x+i>=0 && y+j>=0 && x+i<alkiot[0].length && y+j<alkiot.length) {
					viereiset.add(alkiot[x+i][y+j]);
				}
			}
		}
		viereiset.remove(alkiot[x][y]);
		return viereiset;
	}
	
	private Alkio[][] luoAlkiot(char[][] kirjaimet) {
		Alkio[][] matriisi = new Alkio[rivikoko][rivikoko];
		for(int i=0; i<rivikoko; i++) {
			for(int j=0; j<rivikoko; j++) {
				matriisi[i][j] = new Alkio(kirjaimet[i][j], new Sijainti(i,j));
			}
		}
		return matriisi;
	}
	
	public Alkio[][] arvoAlkiot() {
		Alkio[][] matriisi = new Alkio[rivikoko][rivikoko];
		
		Random random = new Random();
		
		for(int i=0; i<rivikoko; i++) {
			for(int j=0; j<rivikoko; j++) {
				char kirjain =  (char)(random.nextInt(26) + 'a');
				matriisi[i][j] = new Alkio(kirjain, new Sijainti(i, j));
			}
		}
		return matriisi;
	}
	
	public int annaRivikoko() {
		return rivikoko;
	}
	
	public Alkio[][] annaAlkiot() {
		return alkiot;
	}
	
	public void tulosta() {
		System.out.println("Neliömatriisi " + rivikoko + "x" + rivikoko);
		Arrays.stream(alkiot).map(Arrays::toString).forEach(System.out::println);
	}
	
}
