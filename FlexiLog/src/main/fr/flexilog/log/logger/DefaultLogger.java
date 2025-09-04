package fr.flexilog.log.logger;

public class DefaultLogger implements ILogger
{
	@Override
	public void info(String message)
	{
		System.out.println(message);
	}

	@Override
	public void debug(String message)
	{
		System.out.println(message);
	}

	@Override
	public void warn(String message)
	{
		System.out.println(message);
	}

	@Override
	public void erreur(String message)
	{
		System.out.println(message);
	}
}
