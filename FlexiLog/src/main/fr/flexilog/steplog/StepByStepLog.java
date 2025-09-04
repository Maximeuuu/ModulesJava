package fr.flexilog.steplog;

import fr.flexilog.steplog.format.IFormat;
import fr.flexilog.utils.Outils;

public class StepByStepLog
{
	private long actual; //cpt
	private long total; //limite
	private int frequency; //modulo
	private IFormat format;
	private int logLength;

	/**
	 * @param total Nombre total d'étapes
	 * @param frequency Frequence de rafraichissement de l'affichage
	 * @param format Format de l'affichage
	 */
	public StepByStepLog( long total, int frequency, IFormat format )
	{
		this.total = total;
		this.frequency = frequency;
		this.actual = 1;
		this.format = format;
		this.logLength = 0;
	}

	public void reset()
	{
		this.setActual(1);
	}

	public void start( String msg )
	{
		this.setActual(0);
		this.display(msg);
		this.actual++;
	}

	public void setActual( long actual )
	{
		this.actual = actual;
	}

	public void processing( String msg )
	{
		if( this.actual % frequency == 0 )
		{
			this.display(msg);
		}
		this.actual++;
	}

	private void display( String msg )
	{
		String log = String.format("%s %s", msg, format.getProgression( this.actual, this.total ) );
		System.out.print( this.getMaskForPrecLog() + log );
		this.logLength = log.length();
	}

	private String getMaskForPrecLog()
	{
		return '\r' + Outils.repeat(" ", this.logLength) + '\r';
	}

	public void end( String msg )
	{
		this.setActual( this.total );
		this.display( msg );
		System.out.println();
	}
}
