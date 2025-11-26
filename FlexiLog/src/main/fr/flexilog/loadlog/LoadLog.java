package fr.flexilog.loadlog;

import fr.flexilog.loadlog.format.IFormat;
import fr.flexilog.utils.Outils;

public class LoadLog
{
	private long actual; // Compteur d'avancement
	private long cntDisplay; // Nombre de fois qu'un chargement a ete mis a jour // permet un affichage plus fluide pour les chargements
	private int frequency; // Modulo de rafraîchissement
	private IFormat format;
	private int logLength;

	/**
	 * @param frequency Frequence de rafraichissement de l'affichage
	 * @param format Format de l'affichage
	 */
	public LoadLog( int frequency, IFormat format )
	{
		this.frequency = frequency;
		this.actual = 0;
		this.cntDisplay = 0;
		this.format = format;
		this.logLength = 0;
	}

	public void reset()
	{
		this.actual = 0;
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
			this.cntDisplay++;
		}
		this.actual++;
	}

	private void display( String msg )
	{
		String log = String.format("%s %s", msg, format.getNext(this.cntDisplay) );
		this.clean();
		System.out.print(log);
		this.logLength = log.length();
	}

	public void clean()
	{
		System.out.print( this.getMaskForPrecLog() );
	}

	private String getMaskForPrecLog()
	{
		return '\r' + Outils.repeat(" ", this.logLength) + '\r';
	}

	public void end( String msg )
	{
		this.clean();
		System.out.println(msg);
	}
}
