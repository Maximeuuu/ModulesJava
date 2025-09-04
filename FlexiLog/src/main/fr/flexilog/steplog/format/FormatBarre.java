package fr.flexilog.steplog.format;

public class FormatBarre implements IFormat
{
	private char yes;
	private char no;
	private int length;

	public static final char ASCII = 'A';
	public static final char POINT = 'P';

	public static FormatBarre getBarre( char type )
	{
		switch( type )
		{
			case ASCII:
				return new FormatBarre('▓', '▒', 20);
		
			case POINT:
				return new FormatBarre('.', ' ', 20);

			default:
				return new FormatBarre();
		}
	}

	public FormatBarre()
	{
		this('.', ' ', 20);
	}

	public FormatBarre( char yes, char no, int length )
	{
		this.yes = yes;
		this.no = no;
		this.length = length;
	}

	@Override
	public String getProgression(long actual, long total)
	{
		int progression = (int) Math.round((actual / (double) total) * this.length);
		progression = Math.min(progression, length);

		StringBuilder bar = new StringBuilder();
		
		for (int i = 0; i < this.length; i++)
		{
			bar.append(i < progression ? this.yes : this.no);
		}
		return bar.toString();
	}
}
