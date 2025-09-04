package com.maximeuuu.outils.mesure;

public class NombreDecimal implements IMesure {
	private double valeur;
	private String unite;

	public NombreDecimal( double valeur ){
		this.valeur = valeur;
		this.unite = "";
	}

	@Override
	public double getValeur(){
		return this.valeur;
	}

	@Override
	public String getUnite(){
		return this.unite;
	}

	protected void setUnite( String unite ){
		this.unite = unite;
	}

	@Override
	public String toString(){
		return this.getValeur() + "";
	}
}
