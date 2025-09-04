package fr.flexilog.steplog.format;

public class FormatPourcentage implements IFormat
{
	public FormatPourcentage(){}

	@Override
	public String getProgression(long actual, long total)
	{
		return String.format("(%3d %%)", this.getPourcentage(actual, total));
	}

	private int getPourcentage( long actual, long total )
	{
		return (int)(100 * actual / total);
	}
}
