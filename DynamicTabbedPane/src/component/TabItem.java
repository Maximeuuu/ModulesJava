package component;

import java.awt.Component;

import component.tabheader.SimpleTabHeader;

@Deprecated // Je pense que ce composant aurait tendance a disparaitre (plus (+) de transparence envers le DynamicTabbedPane ?)
public class TabItem
{
	private SimpleTabHeader tabHeader;
	private Component tabContent;
	//private Icon icon; //TODO: ajouter la possibilite de configurer ces parametres
	//private String tip

	public TabItem( String title, Component component )
	{
		this( new SimpleTabHeader(title), component);
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
