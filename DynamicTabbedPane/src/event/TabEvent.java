package event;

import java.util.EventObject;

import component.tabheader.TabHeader;

public class TabEvent extends EventObject
{
	public TabEvent(TabHeader source)
	{
		super(source);
	}

	public TabHeader getTabHeader()
	{
		return (TabHeader)super.getSource();
	}
}
