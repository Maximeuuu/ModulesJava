package fr.flexilog.log.logger;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger implements ILogger, AutoCloseable
{
	private PrintWriter writer;
	private static final DateTimeFormatter FORMATTEUR = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

	public FileLogger( File fichier )
	{
		try
		{
			this.writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fichier, true), "UTF-8")), true);
		}
		catch( IOException ioe )
		{
			System.err.println("Erreur lors de l'initialisation du fichier ; les logs deviennent silencieux.");
			this.writer = new NullPrintWriter();
		}
		this.setAutoCloseAtEnd(); //fermer le writer proprement à la fin du programme
	}

	private void setAutoCloseAtEnd()
	{
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try
			{
				this.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}));
	}

	@Override
	public void debug(String message)
	{
		this.ecrire("DEBUG", message);
	}

	@Override
	public void erreur(String message)
	{
		this.ecrire("ERROR", message);
	}

	@Override
	public void info(String message)
	{
		this.ecrire("INFO", message);
	}

	@Override
	public void warn(String message)
	{
		this.ecrire("WARN", message);
	}

	private synchronized void ecrire(String niveau, String message)
	{
		String timestamp = LocalDateTime.now().format(FORMATTEUR);
		this.writer.printf("%s [%s] %s%n", timestamp, niveau, message);
	}

	@Override
	public synchronized void close() throws IOException
	{
		this.writer.close();
	}

	private class NullPrintWriter extends PrintWriter
	{
		public NullPrintWriter()
		{
			super(new NullWriter());
		}
	}

	private static class NullWriter extends Writer
	{
		@Override public void write(char[] cbuf, int off, int len) {}
		@Override public void flush() {}
		@Override public void close() {}
	}
}
