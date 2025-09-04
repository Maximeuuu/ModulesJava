package com.maximeuuu.outils.ihm.modele;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;

/** Menu de l'application
  * @author : Maxime Lemoine
  * @version : 2.0 - 23/12/2023
  * @date : 19/12/2023
  */
public class RenduTableauOperation extends RenduTableauSimple
{
	private static final Color COULEUR_MODIFIER  = new Color ( 174,214,241 );
	private static final Color COULEUR_SUPPRIMER = new Color ( 230,176,170 );
	private static final Color COULEUR_AJOUTER   = new Color ( 171,235,198 );

	@Override
	public Component getTableCellRendererComponent ( JTable tbl, Object valeur, boolean estSelectionne, boolean focus, int lig, int col )
	{
		// Appeler la méthode de la classe parent pour obtenir le rendu par défaut
		Component cellule = super.getTableCellRendererComponent ( tbl, valeur, estSelectionne, focus, lig, col );

		Object premiereCelulleLigne = ( ( ModeleTableau ) ( tbl.getModel ( ) ) ).getObjet ( lig, 0 );
		if ( premiereCelulleLigne instanceof Character )
		{
			char operation = ( char ) premiereCelulleLigne;

			/*
			 * Ajouter des actions à réaliser sur la ligne en fonction de l'opération donnée
			*/
			switch ( operation )
			{
				case ModeleTableau.MODIFIER :
					cellule.setBackground ( COULEUR_MODIFIER  );
					cellule.setForeground ( Color.BLACK       );
					break;

				case ModeleTableau.IGNORER   :
				case ModeleTableau.SUPPRIMER :
					cellule.setBackground ( COULEUR_SUPPRIMER );
					cellule.setForeground ( Color.BLACK       );
					break;

				case ModeleTableau.AJOUTER :
					cellule.setBackground ( COULEUR_AJOUTER   );
					cellule.setForeground ( Color.BLACK       );
					break;

				default :
					break;
			}
		}

		return cellule;
	}
}
