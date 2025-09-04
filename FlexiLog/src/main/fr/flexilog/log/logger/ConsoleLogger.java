package fr.flexilog.log.logger;

public class ConsoleLogger implements ILogger
{
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
		System.out.printf("[%s] %s%n", niveau, message);
	}
}
