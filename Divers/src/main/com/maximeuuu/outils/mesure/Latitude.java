package com.maximeuuu.outils.mesure;

public final class Latitude extends AbstractCoordonneeGeographique {

	public Latitude( double degresDecimaux ){
		super( degresDecimaux );
	}

	/**
	 * Coordonnee au format dddmm.mmmm
	 */
	@Override
	protected String getFormatDegresMinutesDecimales() {
		return "%02d%07.4f";
	}

	@Override
	public char getHemisphere() {
		return (super.getDegresDecimaux() >= 0) ? 'N' : 'S';
	}
}
