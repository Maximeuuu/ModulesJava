package fr.flexilog.loadlog.format.animation.barre;

import fr.flexilog.loadlog.format.animation.AbstractAnimation;
import fr.flexilog.loadlog.format.animation.Direction;

/**
 * Style : [   ] [.  ] [.. ] [...] [.. ] [.  ]
 */
public class AnimationIncrementDecrement extends AbstractAnimation implements IAnimationBarre
{
	public AnimationIncrementDecrement( Direction direction )
	{
		this.direction = direction;
	}

	public AnimationIncrementDecrement()
	{
		this( Direction.LEFT_TO_RIGHT );
	}

	@Override
	public void applyNextAnimation(StringBuilder sb, long etape, int longueur, String caracteres)
	{
		// Créer le motif répété pour remplir toute la barre si besoin
		StringBuilder motif = new StringBuilder();
		while(motif.length() < longueur)
		{
			motif.append(caracteres);
		}

		// Étape dans le cycle aller-retour
		int cycle = 2 * longueur;
		int etapeMod = (int) (etape % cycle);

		// Nombre de caractères à afficher à cette étape
		int nbAffiches = (etapeMod < longueur)
			? etapeMod
			: cycle - etapeMod;

		// Récupérer les caractères à afficher
		String affichage = motif.substring(0, nbAffiches);

		if(super.direction == Direction.LEFT_TO_RIGHT)
		{
			for(int i = 0; i < nbAffiches; i++)
			{
				sb.setCharAt(i, affichage.charAt(i));
			}
		}
		else
		{
			for(int i = 0; i < nbAffiches; i++)
			{
				sb.setCharAt(longueur - nbAffiches + i, affichage.charAt(i));
			}
		}
	}
}
