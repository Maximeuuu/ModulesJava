package fr.flexilog.steplog.format;

public class FormatNumerique implements IFormat
{
	public FormatNumerique(){}

	@Override
	public String getProgression(long actual, long total)
	{
		int tMax = String.valueOf(total).length();
		return String.format("(%"+tMax+"d/%"+tMax+"d)", actual, total);
	}
}
