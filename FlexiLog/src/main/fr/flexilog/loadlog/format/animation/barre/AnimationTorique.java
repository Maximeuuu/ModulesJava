package fr.flexilog.loadlog.format.animation.barre;

import fr.flexilog.loadlog.format.animation.AbstractAnimation;
import fr.flexilog.loadlog.format.animation.Direction;

/**
 * style : [.   ] [..  ] [ .. ] [  ..] [   .]
 */
public class AnimationTorique extends AbstractAnimation implements IAnimationBarre
{
	public AnimationTorique( Direction direction )
	{
		super(direction);
	}

	public AnimationTorique()
	{
		super();
	}

	@Override
	public void applyNextAnimation(StringBuilder sb, long etape, int longueur, String caracteres)
	{
		String caracteresOrdonnes = caracteres;
		if( super.direction == Direction.RIGHT_TO_LEFT )
		{
			caracteresOrdonnes = new StringBuilder(caracteres).reverse().toString();
		}

		for( int i = 0; i < caracteresOrdonnes.length(); i++ )
		{
			int pos = (int) ((etape + i) % longueur);
			sb.setCharAt(pos, caracteresOrdonnes.charAt(i));
		}

		if( super.direction == Direction.RIGHT_TO_LEFT )
		{
			sb.reverse();
		}
	}
}
