package com.maximeuuu.outils.element;

import java.awt.Color;

/**
 * Façade pour la classe FusionCouleurs
 * @author Maximeuuu
 */
public enum ModeFusion
{
	NORMAL( "normal" ),
	REMPLACEMENT( "remplacement" ),
	ADDITION_SPECIFIQUE( "addition spécifique" );

	private final String mode;

	private ModeFusion( String mode )
	{
		this.mode = mode;
	}

	public Color fusion( Color couleur1, Color couleur2 )
	{
		return switch( this )
		{
			case NORMAL -> FusionCouleurs.fusionNormaleCouleurs(couleur1, couleur2);
			case ADDITION_SPECIFIQUE -> FusionCouleurs.fusionAdditionSpecifique(couleur1, couleur2);
			case REMPLACEMENT -> FusionCouleurs.fusionRemplacementCouleurs(couleur1, couleur2);
			default -> FusionCouleurs.fusionNormaleCouleurs(couleur1, couleur2);
		};
	}

	public String getMode()
	{
		return this.mode;
	}
}