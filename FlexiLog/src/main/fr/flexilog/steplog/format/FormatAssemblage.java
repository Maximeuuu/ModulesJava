package fr.flexilog.steplog.format;

import java.util.Arrays;
import java.util.List;

public class FormatAssemblage implements IFormat
{
	private List<IFormat> ensFormats;

	public FormatAssemblage( IFormat... tabFormats )
	{
		this.ensFormats = Arrays.asList(tabFormats);
	}

	@Override
	public String getProgression(long actual, long total)
	{
		StringBuilder sb = new StringBuilder();
		for( IFormat format : this.ensFormats )
		{
			sb.append( format.getProgression(actual, total) ).append(" ");
		}
		return sb.toString();
	}
}
