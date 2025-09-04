package com.maximeuuu.outils.ihm.composant;

import javax.swing.*;

import com.maximeuuu.outils.ihm.config.ConfigurationMaterielleEcran;

import java.awt.Color;
import java.awt.event.*;

/**
 * Base de fenetre principale d'un jeu
 * @autor : Maximeuuu
 */
public class FenetrePleinEcran extends JFrame implements KeyListener
{
	private static final Color BG_COLOR = new Color(0,0,0);

	public FenetrePleinEcran( ConfigurationMaterielleEcran configEcran )
	{
		/* Proprietes */
		super();
		super.setIgnoreRepaint(true);
		super.setUndecorated(true);
		super.setBackground( BG_COLOR );

		/* Position */
		super.setLocation(0,0);

		/* Dimensions */
		super.setResizable(false);
		super.setSize( configEcran.getDimensionEcran() );

		/* Activation */
		super.setVisible(true);
		super.setAlwaysOnTop(true);
		super.addKeyListener(this);
		super.setFocusable(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e)
	{
		if( echapPresse(e) || ctrlCPresse(e) )
			System.exit(0);
	}

	public boolean echapPresse( KeyEvent e )
	{
		return e.getKeyCode() == KeyEvent.VK_ESCAPE;
	}

	public boolean ctrlCPresse( KeyEvent e )
	{
		return e.getKeyCode() == KeyEvent.VK_C && (e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != 0;
	}
}