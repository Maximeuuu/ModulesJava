package com.maximeuuu.outils.utilitaire.image;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Outils utiles pour la gestion des images
 * @author Maximeuuu
 */
public class UtilitaireImage
{
	private UtilitaireImage(){}

	public static ImageIcon getImageIconWithClassPath( String cheminImage )
	{
		ImageIcon icon = null;

		try
		{
			URL url = UtilitaireImage.class.getResource( cheminImage );
			icon = new ImageIcon( url );
		}
		catch ( Exception e )
		{
			System.out.println( "Problème avec l'image : " + e.getMessage() );
		}

		return icon;
	}

	public static ImageIcon getResizeImageWithClassPath( Dimension dimension, String cheminImage )
	{
		ImageIcon icon = null;

		try
		{
			URL url = UtilitaireImage.class.getResource( cheminImage );
			BufferedImage imgOrigine = ImageIO.read( url );
			Image imgResize = imgOrigine.getScaledInstance((int) dimension.getWidth(), (int) dimension.getHeight(), Image.SCALE_SMOOTH);
			icon = new ImageIcon(imgResize);
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}

		return icon;
	}
}
