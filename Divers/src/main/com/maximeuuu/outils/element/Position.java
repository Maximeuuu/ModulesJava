package com.maximeuuu.outils.element;

/**
 * Permet de regrouper des coordonnées dans une classe Position
 * @author Maximeuuu
 */
public class Position
{
	private static final int ORIGINE = 0;
	private int posX;
	private int posY;

	public Position( int x, int y )
	{
		this.posX = x;
		this.posY = y;
	}

	public Position( Position pos )
	{
		this( pos.posX, pos.posY );
	}

	public Position()
	{
		this( ORIGINE, ORIGINE );
	}

	public int getPosX(){ return this.posX; }
	public int getPosY(){ return this.posY; }
	public int getLigne(){ return this.posY; }
	public int getColonne(){ return this.posX; }

	public void setPosX( int x ){ this.posX = x; }
	public void setPosY( int y ){ this.posY = y; }
	public void setLigne( int indiceLigne ){ this.posY = indiceLigne; }
	public void setColonne( int indiceColonne ){ this.posX = indiceColonne; }

	@Override
	public String toString()
	{
		return "Position : x="+posX + " " + "y="+posY;
	}
}
