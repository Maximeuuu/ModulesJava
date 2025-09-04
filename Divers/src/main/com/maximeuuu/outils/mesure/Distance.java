package com.maximeuuu.outils.mesure;

import java.util.Locale;

public class Distance extends NombreDecimal {

	public Distance( double distance ){
		super(distance);
		super.setUnite("m");
	}

	@Override
	public String toString(){
		return String.format(Locale.US,"%.4f %s", super.getValeur(), super.getUnite() );
	}

	public String getStringValeur(){
		return String.format(Locale.US,"%.4f", super.getValeur() );
	}
}
