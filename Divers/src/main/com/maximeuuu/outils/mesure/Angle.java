package com.maximeuuu.outils.mesure;

public class Angle extends NombreDecimal {

	public Angle( double degresDecimaux ) {
		super(degresDecimaux);
		super.setUnite("°");
	}

	public final double getDegresDecimaux(){
		return super.getValeur();
	}

	public final int getDegres() {
		return tronquer( this.getDegresDecimaux() );
	}

	protected final static int tronquer( double valeur ) {
		return (int)valeur; //on récupère que la valeur entière peut importe les décimales
	}
}
