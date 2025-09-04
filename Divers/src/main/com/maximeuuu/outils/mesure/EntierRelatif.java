package com.maximeuuu.outils.mesure;

public class EntierRelatif extends NombreDecimal{

	public EntierRelatif( double valeur ){
		super( Math.round(valeur) );
	}

	@Override
	public String toString() {
		return Math.round( super.getValeur() ) + "";
	}
}
