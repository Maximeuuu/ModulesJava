package fr.flexilog.loadlog;

import fr.flexilog.loadlog.format.FormatSpinner;
import fr.flexilog.loadlog.format.FormatSpinner.StyleSpinner;
import fr.flexilog.loadlog.format.animation.Direction;

public class VisualTestLoadLogSpinner
{
	private FormatSpinner format;
	private LoadLog loadLog;

	private static final int FREQUENCE = 5;

	public static void main(String[] args) throws Exception
	{
		System.out.println("Lancement des tests visuels pour le LoadLog au format Spinner");
		new VisualTestLoadLogSpinner();
	}

	public VisualTestLoadLogSpinner()
	{
		this.testDirection();

		for( StyleSpinner styleSpinner : StyleSpinner.values() )
		{
			this.test(styleSpinner);
		}
	}

	private void test( StyleSpinner styleSpinner )
	{
		this.format = new FormatSpinner( styleSpinner, Direction.LEFT_TO_RIGHT );
		this.loadLog = new LoadLog(FREQUENCE, format);
		this.testMethodes();
	}

	private void testDirection()
	{
		this.format = new FormatSpinner( StyleSpinner.DEGRADE, Direction.RIGHT_TO_LEFT );
		this.loadLog = new LoadLog(FREQUENCE, format);
		this.testMethodes();
	}

	private void testMethodes()
	{
		for( int etapes=0; etapes<50; etapes++ )
		{
			this.loadLog.processing("Avancement du test");
			try{ Thread.sleep(50); }catch( InterruptedException ie ){}
		}
		this.loadLog.end("Traitement termine ");
	}
}
