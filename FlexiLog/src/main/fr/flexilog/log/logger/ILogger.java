package fr.flexilog.log.logger;

public interface ILogger
{
	void info(String message);
	void debug(String message);
	void warn(String message);
	void erreur(String message);
}