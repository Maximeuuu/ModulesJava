package com.maximeuuu.outils.mesure;

public final class Longitude extends AbstractCoordonneeGeographique {

	public Longitude( double degresDecimaux ){
		super(degresDecimaux);
	}

	/**
	 * Coordonnee au format dddmm.mmmm
	 */
	@Override
	public String getFormatDegresMinutesDecimales() {
		return "%03d%07.4f";
	}

	@Override
	public char getHemisphere() {
		return (super.getDegresDecimaux() >= 0) ? 'E' : 'W';
	}
}
