package com.maximeuuu.outils.element;

import java.awt.Color;

/**
 * Classe utilitaire pour la fusion de deux couleurs
 * @author Maximeuuu
 */
public class FusionCouleurs
{
	public static final Color fusionNormaleCouleurs( Color couleur1 , Color couleur2 )
	{
		double alpha1 = couleur1.getAlpha() / 255.0;
		double alpha2 = couleur2.getAlpha() / 255.0;
	
		int rougeFinal = fusionNormaleCouleursPrimaire( couleur1.getRed(), alpha1, couleur2.getRed(), alpha2 );
		int vertFinal = fusionNormaleCouleursPrimaire( couleur1.getGreen(), alpha1, couleur2.getGreen(), alpha2 );
		int bleuFinal = fusionNormaleCouleursPrimaire( couleur1.getBlue(), alpha1, couleur2.getBlue(), alpha2 );
		int alphaFinal = (int) Math.max(couleur1.getAlpha(), couleur2.getAlpha());
	
		return new Color(rougeFinal, vertFinal, bleuFinal, alphaFinal);
	}

	private static final int fusionNormaleCouleursPrimaire( int valeur1, double ratioAlpha1, int valeur2, double ratioAlpha2 )
	{
		double nouvelleCouleur = valeur1 * (1 - ratioAlpha2) * ratioAlpha1 + valeur2 * ratioAlpha2;
		return (int) nouvelleCouleur;
	}

	public static final Color fusionAdditionSpecifique( Color couleur1, Color couleur2 )
	{
		double ratioAlpha1 = couleur1.getAlpha() / 255.0;
		double ratioAlpha2 = couleur2.getAlpha() / 255.0;

		int nouveauAlpha = (int) (couleur1.getAlpha() + couleur2.getAlpha()) / 2;
		int nouveauRouge = fusionAdditionSpecifiqueCouleursPrimaire( couleur1.getRed(), ratioAlpha1, couleur2.getRed(), ratioAlpha2 );
		int nouveauVert = fusionAdditionSpecifiqueCouleursPrimaire( couleur1.getGreen(), ratioAlpha1, couleur2.getGreen(), ratioAlpha2 );
		int nouveauBleu = fusionAdditionSpecifiqueCouleursPrimaire( couleur1.getBlue(), ratioAlpha1, couleur2.getBlue(), ratioAlpha2 );

		return new Color( nouveauRouge, nouveauVert, nouveauBleu, nouveauAlpha );
	}

	private static final int fusionAdditionSpecifiqueCouleursPrimaire( int valeur1, double ratioAlpha1, int valeur2, double ratioAlpha2 )
	{
		double fusionAlpha = ratioAlpha1+ratioAlpha2;
		double nouvelleCouleur = (valeur1 * ratioAlpha1 + valeur2 * ratioAlpha2) / fusionAlpha;
		return (int)nouvelleCouleur;
	}

	public static final Color fusionRemplacementCouleurs( Color couleur1, Color couleur2 )
	{
		return new Color( couleur2.getRed(), couleur2.getGreen(), couleur2.getBlue(), couleur2.getAlpha() );
	}

	public static final String afficherCouleurAvecAlpha( Color couleur )
	{
		return couleur.toString() + " alpa : " + couleur.getAlpha();
	}
}
