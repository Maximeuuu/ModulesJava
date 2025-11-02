package event;

import java.util.EventObject;

import component.TabItem;

public class TabItemEvent extends EventObject
{
	//TODO: ajouter aussi "index" en plus de TabItem ?

	public TabItemEvent(TabItem source)
	{
		super(source);
	}

	public TabItem getTabItem()
	{
		return (TabItem)super.getSource();
	}
}
