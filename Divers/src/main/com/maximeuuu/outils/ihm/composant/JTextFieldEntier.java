package com.maximeuuu.outils.ihm.composant;

import javax.swing.JTextField;

import com.maximeuuu.outils.ihm.modele.FiltreSaisie;

/** JTextFieldEntier est un JTextField avec un filtre d'entier
 * @author : Maxime Lemoine
 * @version : 1.0 - 23/12/2023
 * @date : 23/12/2023
 * @see JTextField
 * @see FiltreSaisie
 */
public class JTextFieldEntier extends JTextField
{
	public JTextFieldEntier( )
	{
		super();
		FiltreSaisie.appliquer( this );
	}
}
