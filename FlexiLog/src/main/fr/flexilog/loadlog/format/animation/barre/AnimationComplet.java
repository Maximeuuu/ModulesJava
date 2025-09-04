package fr.flexilog.loadlog.format.animation.barre;

import fr.flexilog.loadlog.format.animation.AbstractAnimation;
import fr.flexilog.loadlog.format.animation.Direction;

/**
 * Style : [    ] [.   ] [..  ] [ .. ] [  ..] [   .]
 */
public class AnimationComplet extends AbstractAnimation implements IAnimationBarre
{
	public AnimationComplet( Direction direction )
	{
		super(direction);
	}

	public AnimationComplet()
	{
		super();
	}

	@Override
	public void applyNextAnimation(StringBuilder sb, long etape, int longueur, String caracteres )
	{
		String caracteresOrdonnes = caracteres;
		if( super.direction == Direction.RIGHT_TO_LEFT)
		{
			caracteresOrdonnes = new StringBuilder(caracteres).reverse().toString();
		}

		int etapeModule = (int) (etape % (longueur + caracteresOrdonnes.length() ));
		for( int cptCara=0; cptCara<caracteresOrdonnes.length(); cptCara++ )
		{
			int posAffichage = etapeModule - caracteresOrdonnes.length() + cptCara;
			if( posAffichage >= 0 && posAffichage < longueur )
			{
				sb.replace(posAffichage, posAffichage+1, ""+caracteresOrdonnes.charAt(cptCara));
			}
		}

		if( super.direction == Direction.RIGHT_TO_LEFT)
		{
			sb.reverse();
		}
	}
}
