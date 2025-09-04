package fr.flexilog.loadlog.format.animation.barre;

import fr.flexilog.loadlog.format.animation.AbstractAnimation;
import fr.flexilog.loadlog.format.animation.Direction;

/**
 * Style : [   ] [.  ] [.. ] [...] [ ..] [  .]
 */
public class AnimationIncrementReduit extends AbstractAnimation implements IAnimationBarre
{
	public AnimationIncrementReduit( Direction direction )
	{
		this.direction = direction;
	}

	public AnimationIncrementReduit()
	{
		this( Direction.LEFT_TO_RIGHT );
	}

	@Override
	public void applyNextAnimation(StringBuilder sb, long etape, int longueur, String caracteres)
	{
		// Construction du motif répétitif
		StringBuilder motif = new StringBuilder();
		while(motif.length() < longueur)
		{
			motif.append(caracteres);
		}

		// Étape dans le cycle
		int cycle = 2 * longueur;
		int etapeMod = (int) etape % cycle;

		// Nombre de caractères à afficher
		int nbAffiches;
		int position;

		if(etapeMod < longueur) // Phase d'incrémentation (aligné à gauche)
		{
			nbAffiches = etapeMod;
			position = 0;
		}
		else // Phase de réduction (aligné à droite)
		{
			nbAffiches = cycle - 1 - etapeMod;
			position = longueur - nbAffiches;
		}

		// Afficher les caractères du motif
		String affichage = motif.substring(0, nbAffiches);
		for(int i = 0; i < nbAffiches; i++)
		{
			sb.setCharAt(position + i, affichage.charAt(i));
		}
	}
}
