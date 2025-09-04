package fr.flexilog.loadlog.format;

import fr.flexilog.loadlog.format.animation.Direction;

public class FormatSpinner implements IFormat
{
	public enum StyleSpinner
	{
		DEGRADE( new char[]{' ','░','▒','▓','█'}),
		POINT( new char[]{'.',' '}),
		UNDERSCORE( new char[]{'_',' '} ),
		CADRE( "┌─┐│┘─└│".toCharArray() ),
		CROIX( new char[]{'|', '/', '-', '\\'} ),
		CROIX_BIS( new char[]{'+','x'}),
		CARTE( new char[]{'♠','♣','♥','♦'} ),
		CHIFFRE( new char[]{'0','1','2','3','4','5','6','7','8','9'}),
		ALTERANCE( new char[]{'▀','▄'}),
		VERTICAL( new char[]{' ','▄','█','▀'});

		private char[] caracteres;
		private StyleSpinner( char[] caracteres )
		{
			this.caracteres = caracteres;
		}

		public char getCharAt( int indice )
		{
			return this.caracteres[indice];
		}

		public int getNbCaracteres()
		{
			return this.caracteres.length;
		}
	}

	private StyleSpinner style;
	private Direction direction;

	public FormatSpinner( StyleSpinner style, Direction direction )
	{
		this.style = style;
		this.direction = direction;
	}

	public FormatSpinner()
	{
		this( StyleSpinner.CROIX, Direction.LEFT_TO_RIGHT );
	}

	@Override
	public String getNext(long etape)
	{
		int idx = (int) (etape % style.getNbCaracteres());
		if( this.direction == Direction.RIGHT_TO_LEFT )
		{
			idx = style.getNbCaracteres() - idx - 1;
		}

		return String.valueOf(style.getCharAt(idx));
	}
}
