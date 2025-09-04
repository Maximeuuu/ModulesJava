package fr.flexilog.log;

import java.io.PrintWriter;
import java.io.StringWriter;

import fr.flexilog.log.logger.DefaultLogger;
import fr.flexilog.log.logger.ILogger;

public final class Log
{
	private static ILogger loggerActif = new DefaultLogger();

	public static void setLogger( ILogger logger )
	{
		loggerActif = logger;
	}

	public static void info(String message)
	{
		loggerActif.info(message);
	}

	public static void debug(String message)
	{
		loggerActif.debug(message);
	}

	public static void warn(String message)
	{
		loggerActif.warn(message);
	}

	public static void erreur(String message)
	{
		loggerActif.erreur(message);
	}

	public static void erreur(Throwable exception)
	{
		StringWriter tampon = new StringWriter();
		exception.printStackTrace(new PrintWriter(tampon));
		erreur( tampon.toString() );
	}
}
