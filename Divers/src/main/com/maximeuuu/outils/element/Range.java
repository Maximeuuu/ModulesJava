package com.maximeuuu.outils.element;

/**
 * Permet de stocker une plage de valeurs numériques
 * @author Maximeuuu
 */
public class Range
{
	private double min;
	private double max;

	public static Range creerRange( double[] valeurs )
	{
		if ( valeurs.length != 2 ) { throw new IllegalArgumentException( "Le tableau doit contenir exactement 2 valeurs." ); }
		return new Range( valeurs[0], valeurs[1] );
	}

	public static Range creerRange( double valA, double valB )
	{
		if( valA < valB )
			return new Range( valA, valB );
		else
			return new Range( valB, valA );
	}

	private Range( double min, double max )
	{
		this.min = min;
		this.max = max;
	}

	public double getMin()
	{
		return this.min;
	}

	public double getMax()
	{
		return this.max;
	}

	public boolean estDansIntervalle( double valeur )
	{
		return valeur >= this.min && valeur <= this.max;
	}

	public double genererValeur()
	{
		return Math.random()*(this.max-this.min) + this.min;
	}

	public String toString()
	{
		return "Range : "+this.min+" : "+this.max;
	}
}