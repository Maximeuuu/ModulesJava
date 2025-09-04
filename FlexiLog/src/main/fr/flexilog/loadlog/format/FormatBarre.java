package fr.flexilog.loadlog.format;

import fr.flexilog.loadlog.format.animation.barre.*;

public class FormatBarre implements IFormat
{
	private int longueur;
	private String caracteres;
	private char charBackground;
	private IAnimationBarre animation;
	private String start;
	private String end;

	/* CONSTRUCTEURS */

	public FormatBarre( int longueur, String caracteres, char charBackground, IAnimationBarre animation )
	{
		this.longueur = longueur;
		this.caracteres = caracteres;
		this.charBackground = charBackground;
		this.animation = animation;

		this.start = "[";
		this.end = "]";
	}

	public FormatBarre( int longueur, String caracteres, IAnimationBarre animation )
	{
		this(longueur,caracteres,' ',animation);
	}

	public FormatBarre()
	{
		this(20,".",new AnimationComplet());
	}

	public void setEncadrement( String start, String end )
	{
		this.start = start;
		this.end = end;
	}

	/* METHODES */

	@Override
	public String getNext( long etape )
	{
		StringBuilder sb = new StringBuilder();
		this.generateBackground(sb);
		this.animation.applyNextAnimation(sb, etape, longueur, caracteres);
		sb.insert(0, this.start);
		sb.insert(sb.length(), this.end);
		return sb.toString();
	}

	private void generateBackground( StringBuilder sb )
	{
		for( int i = 0; i < this.longueur; i++ )
		{
			sb.append(this.charBackground);
		}
	}
}
