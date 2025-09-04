package fr.flexilog.loadlog;

import fr.flexilog.loadlog.format.FormatBarre;
import fr.flexilog.loadlog.format.animation.Direction;
import fr.flexilog.loadlog.format.animation.barre.AnimationComplet;
import fr.flexilog.loadlog.format.animation.barre.AnimationIncrement;
import fr.flexilog.loadlog.format.animation.barre.AnimationIncrementDecrement;
import fr.flexilog.loadlog.format.animation.barre.AnimationIncrementReduit;
import fr.flexilog.loadlog.format.animation.barre.AnimationRebond;
import fr.flexilog.loadlog.format.animation.barre.AnimationTorique;

public class VisualTestLoadLogBarre
{
	private FormatBarre format;
	private LoadLog loadLog;

	private static final int TAILLE = 15;
	private static final int FREQUENCE = 1;

	public static void main(String[] args) throws Exception
	{
		System.out.println("Lancement des tests visuels pour le LoadLog au format Barre");
		new VisualTestLoadLogBarre();
	}

	public VisualTestLoadLogBarre()
	{
		this.testAnimationComplet();
		this.testAnimationIncrement();
		this.testAnimationIncrementDecrement();
		this.testAnimationIncrementReduit();
		this.testAnimationRebond();
		this.testAnimationTorique();
	}

	private void testAnimationComplet()
	{
		this.format = new FormatBarre( TAILLE, "▓▓▓", '▒', new AnimationComplet());
		this.loadLog = new LoadLog(5, format);
		this.testMethodes();
	}

	private void testAnimationIncrement()
	{
		this.format = new FormatBarre( 3, ".", ' ', new AnimationIncrement());
		this.format.setEncadrement("","");
		this.loadLog = new LoadLog(8, format);
		this.testMethodes();
	}

	private void testAnimationIncrementDecrement()
	{
		this.format = new FormatBarre( TAILLE, "█", '▒', new AnimationIncrementDecrement());
		this.format.setEncadrement("", "");
		this.loadLog = new LoadLog(FREQUENCE, format);
		this.testMethodes();
	}

	private void testAnimationIncrementReduit()
	{
		this.format = new FormatBarre( TAILLE, ".", ' ', new AnimationIncrementReduit());
		this.loadLog = new LoadLog(FREQUENCE, format);
		this.testMethodes();
	}

	private void testAnimationRebond()
	{
		this.format = new FormatBarre( TAILLE, "▓▓▓▓▓▓", '▒', new AnimationRebond());
		this.loadLog = new LoadLog(FREQUENCE, format);
		this.testMethodes();
	}

	private void testAnimationTorique()
	{
		this.format = new FormatBarre( TAILLE, "Chargement !", ' ', new AnimationTorique(Direction.RIGHT_TO_LEFT) );
		this.loadLog = new LoadLog(FREQUENCE, format);
		this.testMethodes();
	}

	private void testMethodes()
	{
		for( int etapes=0; etapes<100; etapes++ )
		{
			this.loadLog.processing("Avancement du test");
			try{ Thread.sleep(50); }catch( InterruptedException ie ){}
		}
		this.loadLog.end("Traitement termine ");
	}
}
