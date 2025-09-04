package com.maximeuuu.outils.utilitaire.tableau;

import java.util.Arrays;
import java.util.List;

/**
 * Classe utilitaire permettant de manipuler des tableaux
 * @author Maxime Lemoine
 * @version 2.1 05/03/2024
 */

public abstract class UtilitaireArrays
{
	/**
	 * Classe utilitaire sans constructeur
	 */
	private UtilitaireArrays () {}


	/*---------------------------------------*/
	/*           METHODES TOSTRING           */
	/*---------------------------------------*/

	/**
	 * toString
	 */
	public static String afficherValeurs ( List<?> liste )
	{
		String s="";

		for ( Object o : liste )
			if( o == null )
				s += "null\n";
			else
				s += o.toString ( ) + "\n";

		return s;
	}

	/**
	 * toString
	 */
	public static String afficherValeurs ( Object[] liste )
	{
		String s="";

		for ( Object o : liste )
			if( o == null )
				s += "null\n";
			else
				s += o.toString ( ) + "\n";

		return s;
	}


	/*---------------------------------------*/
	/*            METHODES COPIES            */
	/*---------------------------------------*/

	/**
	 * Copie profonde d'un ensemble
	 */
	public static Object[] copier ( Object[] ensemble )
	{
		return Arrays.copyOf( ensemble, ensemble.length );
	}


	/*---------------------------------------*/
	/*             METHODES AUTRES           */
	/*---------------------------------------*/

	/**
	 * Methode permettant d'inverser deux objets d'un tableau
	 */
	public static void inverser( Object[] liste, int ind1, int ind2 )
	{
		Object tmp = liste[ind1];
		liste[ind1] = liste[ind2];
		liste[ind2] = tmp;
	}

	/**
	 * Methode permettant de vérifier si un ensemble est valide selon plusieurs critères
	 */
	public static final boolean estValide( Object[] ensemble, boolean valeursNonNulles, int longueurReference )
	{
		boolean tailleValide = ensemble.length != 0;
		if( !tailleValide ){ return false; }

		if( longueurReference >= 0 )
		{
			boolean longueurDifferente = ensemble.length != longueurReference;
			if( longueurDifferente ){ return false; }
		}

		if( valeursNonNulles )
		{	
			for( int cptCol=0; cptCol<longueurReference; cptCol++ )
			{
				boolean valeurValide = ensemble[cptCol] != null;
				if( !valeurValide ){ return false; }
			}
		}
		
		return true;
	}
}
