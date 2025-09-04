package com.maximeuuu.outils.ihm.modele;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/** Filtre pour JTextField
 * @author : Maxime Lemoine
 * @version : 2.0 - 23/12/2023
 * @date : 14/12/2023
 */
public class FiltreSaisie extends DocumentFilter
{
	private String regex;
	
	/**
	 * @apiNote : Filtre pour JTextField
	 * @param regex : expression régulière à respecter
	 */
	public FiltreSaisie ( String regex )
	{
		super ( );
		this.regex = regex;
	}

	/**
	 * @apiNote : Par défaut le filtre est un filtre d'entier
	 */
	public FiltreSaisie ( )
	{
		this ( "\\d+" );
	}

	@Override
	public void insertString ( FilterBypass fb, int offset, String string, AttributeSet attr ) throws BadLocationException
	{
		if ( string.matches ( this.regex ) )
		{
			super.insertString ( fb, offset, string, attr );
		}
	}

	@Override
	public void replace ( FilterBypass fb, int offset, int length, String text, AttributeSet attrs ) throws BadLocationException
	{
		if ( text.matches ( this.regex ) )
		{
			super.replace ( fb, offset, length, text, attrs );
		}
	}

	public static void appliquer ( JTextField txt )
	{
		( ( AbstractDocument ) txt.getDocument ( ) ).setDocumentFilter ( new FiltreSaisie ( ) );
	}
}