package component;

import java.awt.Component;

import javax.swing.JTabbedPane;

import component.tabheader.SimpleTabHeader;

public class TabItem
{
	private SimpleTabHeader tabHeader;
	private Component tabContent;
	//private Icon icon; //TODO: ajouter la possibilite de configurer ces parametres
	//private String tip

	public TabItem( JTabbedPane pane, String title, Component component )
	{
		this( new SimpleTabHeader(pane, title), component);
	}

	public TabItem( SimpleTabHeader tabHeader, Component tabContent)
	{
		this.tabHeader = tabHeader;
		this.tabContent = tabContent;
	}

	public SimpleTabHeader getTabHeader()
	{
		return this.tabHeader;
	}

	public Component getTabContent()
	{
		return this.tabContent;
	}
}
