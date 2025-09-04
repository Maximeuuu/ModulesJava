package com.maximeuuu.outils.mesure;

import java.util.Locale;

public abstract class AbstractCoordonneeGeographique extends Angle {

	private static final double MINUTES = 60.0;

	/*------------------------------------------------------------*/
	/*                        CONSTRUCTEUR                        */
	/*------------------------------------------------------------*/

	public AbstractCoordonneeGeographique( double degresDecimaux ) {
		super(degresDecimaux);
	}

	/*------------------------------------------------------------*/
	/*                         ACCESSEURS                         */
	/*------------------------------------------------------------*/

	public final double getMinutesDecimales(){
		return ( this.getDegresDecimaux() - this.getDegres()) * MINUTES;
	}

	public final int getMinutes(){
		return tronquer( this.getMinutesDecimales() );
	}

	public final double getSecondesDecimales(){
		return ( this.getMinutesDecimales() - this.getMinutes()) * MINUTES;
	}

	public final int getSecondes(){
		return tronquer( this.getSecondesDecimales() );
	}

	public abstract char getHemisphere();

	/*------------------------------------------------------------*/
	/*            VALEURS NUMERIQUES NORMEES EN CHAINE            */
	/*------------------------------------------------------------*/

	/**
	 * Coordonnee au format D.mmmmmm
	 */
	public final String getStringDegresDecimaux() {
		return getStringDegresDecimaux(6);
	}

	protected final String getStringDegresDecimaux(int nbDigit) {
		final String FORMAT = "%."+ nbDigit + "f";
		return String.format(Locale.US, FORMAT, Math.abs(this.getDegresDecimaux()));
	}

	/**
	 * Coordonnee au format getFormatDegresMinutesDecimales()
	 */
	public final String getStringDegresMinutesDecimales() {
		final String FORMAT = this.getFormatDegresMinutesDecimales();
		return String.format(Locale.US, FORMAT, Math.abs(this.getDegres()), Math.abs(this.getMinutesDecimales()));
	}

	/**
	 * format DM.M
	 */
	protected abstract String getFormatDegresMinutesDecimales();

	/*------------------------------------------------------------*/
	/*         VALEURS NUMERIQUES POUR L'AFFICHAGE HUMAIN         */
	/*------------------------------------------------------------*/

	/**
	 * Coordonnee au format D° M' S'',sss H
	 */
	public final String toStringDegresSexagésimaux() {
		final String FORMAT = "%d° %d' %.3f'' %c";
		return String.format(Locale.FRANCE, FORMAT, Math.abs(this.getDegres()),  Math.abs(this.getMinutes()),  Math.abs(this.getSecondesDecimales()), this.getHemisphere() );
	}

	/**
	 * Coordonnee au format D,D° H
	 */
	public final String toStringDegresDecimaux(){
		final String FORMAT = "%s° %c";
		return String.format(Locale.FRANCE, FORMAT, this.getStringDegresDecimaux(), this.getHemisphere());
	}

	/**
	 * Coordonnee au format D° M,mmmm' H
	 */
	public String toStringDegresMinutesDecimales() {
		final String FORMAT = "%d° %.4f' %c";
		return String.format(Locale.FRANCE, FORMAT, Math.abs(this.getDegres()), Math.abs(this.getMinutesDecimales()), this.getHemisphere());
	}

	@Override
	public String toString(){
		return this.toStringDegresMinutesDecimales();
	}
}
