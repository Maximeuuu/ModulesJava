package com.maximeuuu.outils.ihm.modele;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/** Style de rendu des cellules d'un tableau simple
  * @author : Maxime Lemoine
  * @version : 2.0 - 23/12/2023
  * @date : 19/12/2023
  */
//TODO: "Ligne sélectionnée" en bleu
public class RenduTableauSimple extends DefaultTableCellRenderer
{
	private static final Color COULEUR_FOND_1    = new Color ( 253,254,254 );
	private static final Color COULEUR_FOND_2    = new Color ( 248,249,249 );

	@Override
	public Component getTableCellRendererComponent ( JTable tbl, Object valeur, boolean estSelectionne, boolean focus, int lig, int col )
	{
		// Appeler la méthode de la classe parent pour obtenir le rendu par défaut
		Component cellule = super.getTableCellRendererComponent ( tbl, valeur, estSelectionne, focus, lig, col );
		
		if ( valeur instanceof Boolean )
		{
			JCheckBox checkBox = new JCheckBox ( );
			checkBox.setSelected            ( ( Boolean ) valeur );
			checkBox.setHorizontalAlignment ( JCheckBox.CENTER   );
			cellule = checkBox;
		}
		
		// par défaut il y a une alternance de couleurs
		if ( ! focus )
		{
			if ( lig % 2 == 0 )
				cellule.setBackground ( COULEUR_FOND_1 );
			else
				cellule.setBackground ( COULEUR_FOND_2 );
		}
		
		cellule.setForeground ( tbl.getForeground ( ) );
			
		// Aligner le texte à droite si la valeur est numérique
		if ( valeur instanceof Number )
		{
			setHorizontalAlignment ( RIGHT );
		}
		else
		{
			// Rétablir l'alignement par défaut pour le texte
			setHorizontalAlignment ( LEFT );
		}

		if (estSelectionne)
		{
			cellule.setBackground(tbl.getSelectionBackground());
			cellule.setForeground(tbl.getSelectionForeground());
		}

		return cellule;
	}
}
