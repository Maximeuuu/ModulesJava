package com.maximeuuu.outils.element;

import com.maximeuuu.outils.utilitaire.image.UtilitairePolygone;

import java.awt.Polygon;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

/**
 * Masques de collision
 * @author Maximeuuu
 */
public class MasqueCollision extends Polygon
{
	private static final double TOLERANCE_SIMPLIFICATION = 2.0;

	private BufferedImage bImage;
	private int centreX;
	private int centreY;

	public MasqueCollision( ImageIcon iicon )
	{
		super();

		this.bImage = new BufferedImage( iicon.getIconWidth(), iicon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);

		this.ajouterPointsBasVersHautEtDroiteVersGauche();
		this.ajouterPointsHautVersBasEtGaucheVersDroite();

		this.centreX = iicon.getIconWidth() / 2;
		this.centreY = iicon.getIconHeight() / 2;
		super.translate( -this.centreX, -this.centreY ); //translater parce qu'on gère les images par rapport à leur centre
	}

	private void ajouterPointsBasVersHautEtDroiteVersGauche( )
	{
		for( int y = 0; y < bImage.getHeight(); y++ )
		{
			for( int x = bImage.getWidth() - 1; x >= 0; x-- )
			{
				if( this.pointExiste( x, y ) )
					super.addPoint( x, y );
				else
					break;
			}
		}
	}

	private void ajouterPointsHautVersBasEtGaucheVersDroite( )
	{
		for( int y = this.bImage.getHeight() - 1; y >= 0; y-- )
		{
			for( int x = 0; x < this.bImage.getWidth(); x++ )
			{
				if( this.pointExiste( x, y ) )
					super.addPoint( x, y );
				else
					break;
			}
		}
	}

	private boolean pointExiste( int x, int y )
	{
		return this.bImage.getRGB( x, y ) != 0;
	}

	public void simplifier()
	{
		Polygon polygoneSimplifie = UtilitairePolygone.simplifierPolygone( this, TOLERANCE_SIMPLIFICATION );
		
		if( !polygoneSimplifie.equals( this ) )
		{
			super.reset();
			for( int i = 0; i < polygoneSimplifie.npoints; i++ )
			{
				super.addPoint( polygoneSimplifie.xpoints[i], polygoneSimplifie.ypoints[i] );
			}
		}
	}
}
