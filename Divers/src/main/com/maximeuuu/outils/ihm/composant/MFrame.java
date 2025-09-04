package com.maximeuuu.outils.ihm.composant;

import java.awt.Component;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

/** Classe principale pour la génération de fenetres
  * @author : Maxime Lemoine
  * @version : 1.2 - 23/12/2023
  * @date : 15/12/2023
  */
public abstract class MFrame extends JFrame
{
	private Frame   accueil;

	/**
	 * Constructeur général
	 */
	public MFrame( JMenuBar barreMenu, Frame accueil )
	{
		this.accueil   = accueil;

		this.setJMenuBar( barreMenu );
		this.setDefaultCloseOperation( EXIT_ON_CLOSE );
	}

	/**
	 * Récupérer la fenetre utilisée pour le composant
	 */
	public static void fermerDepuis( Component composant )
	{
		java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor( composant );

		if ( window instanceof Frame )
		{
			Frame parentFrame = ( Frame ) window;
			parentFrame.dispose ( );
		}
	}

	/**
	 * Retourner à l'accueil
	 */
	public abstract void retourAccueil ( );

	/**
	 * Récupérer la fenetre d'accueil
	 */
	public Frame getAccueil ( )
	{
		return this.accueil;
	}

	/**
	 * Aller vers une page
	 * @param c : classe de la page
	 */
	//TODO: cette méthode n'a pas été vérifiée
	public void allerVersPage ( Class<?> c )
	{
		try
		{
			this.dispose ( );
			
			// Obtenir le constructeur
			java.lang.reflect.Constructor<?> constructor = c.getDeclaredConstructor ( );

			// Instanciez la nouvelle classe
			constructor.newInstance ( );
		}
		catch ( Exception e )
		{
			System.out.println( "Erreur génération de la page " +c.getClass().getName()+ ": " + e );
		}
	}
}
