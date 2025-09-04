package fr.flexilog.log.logger;

public class NullLogger implements ILogger
{
	@Override
	public void info(String message) { /*ne rien faire*/ }

	@Override
	public void debug(String message) { /*ne rien faire*/ }

	@Override
	public void warn(String message) { /*ne rien faire*/ }

	@Override
	public void erreur(String message) { /*ne rien faire*/ }
}
