package fr.flexilog.loadlog.format.animation.barre;

import fr.flexilog.loadlog.format.animation.AbstractAnimation;
import fr.flexilog.loadlog.format.animation.Direction;

/**
 * Style : [   ] [.  ] [.. ] [...]
 */
public class AnimationIncrement extends AbstractAnimation implements IAnimationBarre
{
	public AnimationIncrement( Direction direction )
	{
		super(direction);
	}

	public AnimationIncrement()
	{
		super();
	}

	@Override
	public void applyNextAnimation(StringBuilder sb, long etape, int longueur, String caracteres)
	{
		// Étape dans le cycle : de 0 à longueur inclus (redémarre ensuite)
		int nbAffiches = (int) (etape % (longueur + 1));

		// Construire la chaîne répétée pour couvrir toute la barre
		StringBuilder motif = new StringBuilder();
		while(motif.length() < longueur)
		{
			motif.append(caracteres);
		}

		// Prendre seulement ce qu'on doit afficher
		String affichage = motif.substring(0, nbAffiches);

		// Insérer les caractères à gauche ou à droite selon la direction
		if(super.direction == Direction.LEFT_TO_RIGHT)
		{
			for(int i = 0; i < nbAffiches && i < longueur; i++)
			{
				sb.setCharAt(i, affichage.charAt(i));
			}
		}
		else
		{
			for (int i = 0; i < nbAffiches && i < longueur; i++)
			{
				sb.setCharAt(longueur - nbAffiches + i, affichage.charAt(i));
			}
		}
	}
}
