package fr.flexilog.log;

import fr.flexilog.log.logger.*;

import java.io.File;

public class VisualTestLog
{
	public static void main(String[] args)
	{
		new VisualTestLog();
	}

	public VisualTestLog()
	{
		this.testDefaultLogger();
		this.testNullLogger();
		this.testConsoleLogger();
		this.testFileLogger();
	}

	public void testDefaultLogger()
	{
		System.out.println(" = Test du logger par défaut");
		Log.setLogger(new DefaultLogger());
		this.testMethodes();
	}

	public void testNullLogger()
	{
		System.out.println(" = Test du logger null");
		Log.setLogger(new NullLogger());
		this.testMethodes();
	}

	public void testConsoleLogger()
	{
		System.out.println(" = Test du logger dans le terminal");
		Log.setLogger(new ConsoleLogger());
		this.testMethodes();
	}

	public void testFileLogger()
	{
		final File FICHIER = new File("src/test/logs.log");
		if( FICHIER.exists() ) //nettoyer les précédents tests
			FICHIER.delete();

		System.out.println(" = Test du logger dans le fichier : " + FICHIER.getAbsolutePath() );
		Log.setLogger(new FileLogger(FICHIER));
		this.testMethodes();

		System.out.println(" = Test du logger dans un fichier avec erreur" );
		Log.setLogger(new FileLogger(new File("")));
		this.testMethodes();
	}

	private void testMethodes()
	{
		Log.info("Ceci est une info.");
		Log.debug("Ceci est un message de debug.");
		Log.warn("Ceci est un warning.");
		Log.erreur("Ceci est un message d'erreur.");
		Log.erreur( new Exception("Message via objet Exception.") );
	}
}
