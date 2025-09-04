package com.maximeuuu.outils.utilitaire.tableau;

import java.util.Arrays;

//TODO: voir à passer par une instance de classe pour limiter le nombre de paramètres
//TODO: certaines méthodes devraient être revues / améliorées / approfondies
//TODO: certaines méthodes n'ont pas été divisées / simplifiées en plusieurs sous méthodes / utilisation de UtilitaireArrays

/**
 * Classe utilitaire permettant de manipuler des tableaux
 * @author Maxime Lemoine
 * @version 2.0 20/12/23
 */

public abstract class UtilitaireTableau
{
	public static final String SEP_COL_DEFAUT = " ";
	public static final String SEP_LIG_DEFAUT = "\n";
	public static final String ALTERNATIVE_NULL_DEFAUT = "null";

	/**
	 * Classe utilitaire sans constructeur
	 */
	private UtilitaireTableau () {}


	/*---------------------------------------*/
	/*      METHODES TOSTRING TABLEAUX       */
	/*---------------------------------------*/


	/**
	 * toString
	 */
	public static String afficherValeurs ( Object[][] tableau )
	{
		return UtilitaireTableau.afficherValeurs( tableau, SEP_COL_DEFAUT, SEP_LIG_DEFAUT, ALTERNATIVE_NULL_DEFAUT );
	}

	/**
	 * toString
	 */
	public static String afficherValeurs ( Object[][] tableau, String separateurColonnes, String separateurLignes, String alternativeValeurNulle )
	{
		String s="";

		for ( int cptLig = 0; cptLig < tableau.length; cptLig++ )
		{
			for ( int cptCol = 0; cptCol < tableau[cptLig].length; cptCol++ )
			{
				if( tableau[cptLig][cptCol] == null )
					s += alternativeValeurNulle;
				else
					s += tableau[cptLig][cptCol].toString ( );

				s += separateurColonnes;
			}
			s+=separateurLignes;
		}

		return s;
	}

	/**
	 * toString des types
	 */
	public static String afficherTypes ( Object[][] tableau )
	{
		String s="";

		for ( int cptLig = 0; cptLig < tableau.length; cptLig++ )
		{
			for ( int cptCol = 0; cptCol < tableau[cptLig].length; cptCol++ )
			{
				if( tableau[cptLig][cptCol] == null )
					s += "null ";
				else
					s += tableau[cptLig][cptCol].getClass().toString ( ) + " ";
			}
			s+="\n";
		}

		return s;
	}


	/*---------------------------------------*/
	/*       METHODES COPIES TABLEAUX        */
	/*---------------------------------------*/

	/**
	 * Copie profonde d'un tableau avec ajout d'une copie d'une nouvelle ligne
	 */
	public static Object[][] copier ( Object[][] tableau, Object[] ligne )
	{
		Object[][] tableauTmp = new Object[tableau.length+1][ligne.length];

		for ( int lig = 0; lig < tableau.length; lig++ )
		{
			tableauTmp[lig] = Arrays.copyOf ( tableau[lig], tableau[lig].length );
		}
		tableauTmp[tableauTmp.length-1] = UtilitaireArrays.copier ( ligne );


		return tableauTmp;
	}

	/**
	 * Copie profonde d'un tableau avec une nouveau nombre de colonnes (supprime les dernières ou en rajoute des vides avec une valeur par défaut)
	 */
	public static Object[][] formater ( Object[][] tableau, int nbColonnes, Object valeurDefaut )
	{
		Object[][] tableauTmp = new Object[tableau.length][nbColonnes];

		for ( int lig = 0; lig < tableau.length; lig++ )
		{
			for ( int col = 0; col < nbColonnes; col++ )
			{
				if ( col < tableau[lig].length )
				{
					tableauTmp[lig][col] = tableau[lig][col];
				}
				else
				{
					// Ajoutez une valeur vide pour les nouvelles colonnes
					tableauTmp[lig][col] = valeurDefaut;
				}
			}
		}

		return tableauTmp;
	}

	/**
	 * Copie profonde d'un tableau avec une nouveau nombre de colonnes (supprime les dernières ou en rajoute des vides)
	 */
	public static Object[][] formater ( Object[][] tableau, int nbColonnes )
	{
		return UtilitaireTableau.formater ( tableau, nbColonnes, null );
	}

	/**
	 * Copie profonde d'un tableau
	 */
	public static Object[][] copier ( Object[][] tableau )
	{
		return UtilitaireTableau.copier ( tableau, 0 );
	}

	/**
	 * Copie profonde d'un tableau avec 1 à supprimer
	 */
	public static Object[][] copier ( Object[][] tableau, int index )
	{
		int cptLigTmp = 0;

		if ( index < 0              ) { index = 0;              }
		if ( index > tableau.length ) { index = tableau.length; }

		Object[][] tableauTmp = new Object[tableau.length - 1][tableau[0].length];

		for ( int lig = 0; lig < tableau.length; lig++ )
			if ( lig != index )
				tableauTmp[cptLigTmp++] = UtilitaireArrays.copier ( tableau[lig] );

		return tableauTmp;
	}


	/*---------------------------------------*/
	/*             METHODES AUTRES           */
	/*---------------------------------------*/


	/**
	 * Methode permettant d'affecter une valeur de base dans un tableau
	 */
	public static void remplirTableau( Object[][] tab, Object valeur )
	{
		for( int cptLig = 0; cptLig < tab.length; cptLig++ )
			for( int cptCol = 0; cptCol < tab[cptLig].length; cptCol++ )
				tab[cptLig][cptCol] = valeur;
	}

	/**
	 * Methode permettant de vérifier si un tableau est valide selon plusieurs critères
	 */
	public static final boolean estValide( Object[][] tab, boolean valeursNonNulles, boolean tableauRectangle )
	{	
		boolean tailleValide = tab.length != 0 && tab[0].length != 0;
		if( !tailleValide ){ return false; }

		int longueurReference;
		if( tableauRectangle ){ longueurReference = tab[0].length; }
		else{ longueurReference = -1; }

		for( int cptLig=0; cptLig<tab.length; cptLig++ )
		{
			boolean ligneValide = UtilitaireArrays.estValide(tab[cptLig], valeursNonNulles, longueurReference);
			if( !ligneValide ){ return false; }
		}

		return true;
	}

}
