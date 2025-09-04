package fr.flexilog.loadlog.format.animation.barre;

import fr.flexilog.loadlog.format.animation.AbstractAnimation;
import fr.flexilog.loadlog.format.animation.Direction;

/**
 * Style : [..  ] [ .. ] [  ..]
 */
public class AnimationRebond extends AbstractAnimation implements IAnimationBarre
{
	public AnimationRebond( Direction direction )
	{
		super(direction);
	}

	public AnimationRebond()
	{
		super();
	}

	@Override
	public void applyNextAnimation(StringBuilder sb, long etape, int longueur, String caracteres)
	{
		String caracteresOrdonnes = caracteres;
		int nbPositions = longueur - caracteres.length();
		int cycle = nbPositions * 2;

		// Étape courante dans le cycle aller-retour
		int etapeMod = (int) (etape % cycle);
		int position;

		if(etapeMod < nbPositions) // Phase aller : gauche -> droite
		{
			position = etapeMod;
		}
		else // Phase retour : droite -> gauche
		{
			position = cycle - etapeMod;
		}

		if (super.direction == Direction.RIGHT_TO_LEFT)
		{
			caracteresOrdonnes = new StringBuilder(caracteres).reverse().toString();
		}

		// Placement des caractères à la bonne position
		for( int i = 0; i < caracteresOrdonnes.length(); i++ )
		{
			sb.setCharAt(position + i, caracteresOrdonnes.charAt(i));
		}

		if( super.direction == Direction.RIGHT_TO_LEFT )
		{
			sb.reverse();
		}
	}
}
