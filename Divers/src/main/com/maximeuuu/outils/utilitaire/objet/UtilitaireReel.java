package com.maximeuuu.outils.utilitaire.objet;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Permet de gérer certaines propriétés pour les nombres réels
 * @author Maximeuuu
 */
public abstract class UtilitaireReel
{
	public UtilitaireReel(){}

	public static String formatterDouble( double valeur )
	{
		DecimalFormatSymbols symboleDecimal = DecimalFormatSymbols.getInstance();
		symboleDecimal.setDecimalSeparator( '.' );
		return new DecimalFormat( "0.00", symboleDecimal ).format( valeur );
	}
}