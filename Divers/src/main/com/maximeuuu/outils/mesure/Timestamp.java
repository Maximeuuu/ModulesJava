package com.maximeuuu.outils.mesure;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public final class Timestamp extends EntierRelatif {

	//Note: il est possible qu'il y ait de mauvais arrondis à cause des String.format mais ils sont négligeables

	private static final long SECONDES_MICROSECONDES = 1_000l * 1_000l;
	private static final long HEURE_SECONDES = 60l * 60l;

	public Timestamp( double microsecondes ){
		super( microsecondes );
		super.setUnite("µs");
	}

	/*------------------------------------------------------------*/
	/* Opérations sur les valeurs                                 */
	/*------------------------------------------------------------*/

	/**
	 * Méthode interne pour convertir les microsecondes en Instant
	 */
	private Instant toInstant() {
		return Instant.ofEpochSecond(Math.round(this.getSecondesDecimales()), Math.round((super.getValeur() % SECONDES_MICROSECONDES) * 1000));
	}

	/**
	 * Convertit en secondes décimales
	 */
	public double getSecondesDecimales(){
		return (0.0 + super.getValeur()) / SECONDES_MICROSECONDES;
	}

	/**
	 * Convertit en heures decimales
	 */
	public double getHeuresDecimale(){
		return (0.0 + super.getValeur()) / HEURE_SECONDES / SECONDES_MICROSECONDES;
	}

	/*------------------------------------------------------------*/
	/* Méthodes affichages formatés                               */
	/*------------------------------------------------------------*/

	/**
	 * Méthode interne pour formatter un texte au bon format
	 */
	private String toDateWithPattern( String paterne ){
		return DateTimeFormatter
			.ofPattern(paterne)
			.withZone(ZoneOffset.UTC)
			.format( this.toInstant() );
	}

	@Override
	public String toString(){
		return this.toStringUTC();
	}

	/**
	 * Renvoie timestamp en microsecondes
	 */
	public String toStringMicrosecondes(){
		return super.getValeur() + super.getUnite();
	}

	/**
	 * Renvoie heures, minutes, secondes et millisecondes UTC au format hhmmss.sss
	 */
	public String toStringHeure(){
		return this.toDateWithPattern("HHmmss.SSS");
	}

	/**
	 * Renvoie la date UTC au format jj/mm/aa
	 */
	public String toStringDate() {
		return this.toDateWithPattern("dd/MM/yy");
	}

	/**
	 * Renvoie la date UTC au format dd/MM/yyyy - HH:mm:ss
	 */
	public String toStringDateHeure() {
		return this.toDateWithPattern("dd/MM/yyyy - HH:mm:ss");
	}

	/**
	 * Renvoie la date UTC au format HH:mm:ss.SSS | dd/MM/yy
	 */
	public String toStringUTC() {
		return this.toDateWithPattern("HH:mm:ss.SSS | dd/MM/yy");
	}

	/**
	 * Renvoie la date et l'heure au format ISO 8601
	 */
	public String toStringUnixTime() {
		return DateTimeFormatter.ISO_INSTANT.format( this.toInstant() );
	}
}
