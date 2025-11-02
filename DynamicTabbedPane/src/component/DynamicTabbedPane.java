package component;

import javax.swing.*;

import component.tabheader.AddTabHeader;
import component.tabheader.SimpleTabHeader;
import component.tabheader.TabHeader;

import java.awt.*;
import java.awt.event.MouseEvent;

import event.AddTabHeaderListener;
import event.TabHeaderListener;
import event.TabEvent;
import event.TabItemEvent;
import event.TabItemListener;

//TODO: Ajouter raccourcis claviers (tab, fermer, nouvel onglet)

/**
 * JTabbedPane ameliore avec :
 * - Onglet special "+" pour ajouter dynamiquement des onglets
 * - Bouton de fermeture sur les onglets closables
 * - Sous-classes pour chaque type d'onglet
 */
public class DynamicTabbedPane extends JTabbedPane implements AddTabHeaderListener, TabHeaderListener
{
	private TabItemListener tabItemListener;

	/* ----------------- */
	/*   Constructeurs   */
	/* ----------------- */

	public DynamicTabbedPane()
	{
		this(JTabbedPane.TOP);
	}

	public DynamicTabbedPane(int tabPlacement)
	{
		this(tabPlacement, JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	public DynamicTabbedPane(int tabPlacement, int tabLayoutPolicy)
	{
		super(tabPlacement, tabLayoutPolicy);
		//this.setUI( new DynamicTabbedPaneUI() ); //TODO: pas encore pret (en enlevant toutes les marges intérieures, il faut en rajouter manuellement pour les onglets classiques)
		this.addDefaultAddTab();
	}

	/* NOUVEAUX */
	public TabItem getTabItemAt( int index )
	{
		Component tabHeader = super.getTabComponentAt(index);
		Component tabContent = super.getComponentAt(index);

		return new TabItem((SimpleTabHeader)tabHeader, tabContent); //TODO: vérifier si il ne peut pas y avoir d'erreur lors du cast
	}

	/* ---------------------- */
	/*   Onglet special "+"   */
	/* ---------------------- */

	private void addDefaultAddTab()
	{
		super.addTab(null, null);
		super.setTabComponentAt(this.getClosableTabCount(), new AddTabHeader());
		super.setEnabledAt(this.getClosableTabCount(), false); // important
	}

	/* ------------- */
	/*   Listeners   */
	/* ------------- */

	public void setTabItemListener( TabItemListener listener ){ this.tabItemListener = listener; }

	/* --------------- */
	/*   Utilitaires   */
	/* --------------- */

	/** Detection onglet AddTab */
	public boolean isAddTabHeaderIndex(int index)
	{
		if (index < 0 || index >= super.getTabCount()) return false;
		Component c = super.getTabComponentAt(index);
		return c instanceof AddTabHeader;
	}

	/** Nombre d’onglets closables */
	public int getClosableTabCount()
	{
		return super.getTabCount() - 1; //Remarque : on part du principe qu'il ne peut y avoir qu'un seul onglet "+"
	}

	/** Selection du dernier onglet closable */
	public void selectLastClosableTab()
	{
		if (this.getClosableTabCount() > 0)
		{
			super.setSelectedIndex(this.getClosableTabCount() - 1); //Remarque : on part du principe qu'il ne peut y avoir qu'un seul onglet "+"
		}
	}

	/* ----------------------- */
	/*   Gestion des onglets   */
	/* ----------------------- */

	public void addTab(TabItem tabItem)
	{
		this.insertTab(tabItem, this.getClosableTabCount());
		this.selectLastClosableTab();
	}

	public void insertTab(TabItem tabItem, int index)
	{
		int safeIndex = Math.min(index, Math.max(0, this.getClosableTabCount()));
		super.insertTab(null, null, tabItem.getTabContent(), null, safeIndex);

		SimpleTabHeader tabHeader = tabItem.getTabHeader();
		tabHeader.setTabHeaderListener(this);
		super.setTabComponentAt(safeIndex, tabHeader);
	}

	/* ---------------------------------- */
	/*   Gestion des onglets (Override)   */
	/* ---------------------------------- */
	
	/** Override pour inserer avant le "+" */
	@Override
	public void addTab(String title, Component component)
	{
		this.addTab(title, null, component);
	}

	/** Override pour inserer avant le "+" */
	@Override
	public void addTab(String title, Icon icon, Component component)
	{
		this.addTab(title, icon, component, "");
	}

	/** Override pour inserer avant le "+" */
	@Override
	public void addTab(String title, Icon icon, Component component, String tip)
	{
		this.insertTab(title, icon, component, tip, this.getClosableTabCount());
		this.selectLastClosableTab();
	}

	/** Override pour inserer avant le "+" */
	@Override
	public void insertTab(String title, Icon icon, Component component, String tip, int index)
	{
		final SimpleTabHeader defaultTabHeader = new SimpleTabHeader(title);
		TabItem tabItem = new TabItem( defaultTabHeader, component );
		this.insertTab(tabItem, index);
	}

	/** Remove uniquement si ce n’est pas l’onglet AddTab */
	@Override
	public void removeTabAt(int index)
	{
		if (this.isAddTabHeaderIndex(index)) return;
		super.removeTabAt(index);
	}

	/* ------------------------------------- */
	/*   Gestion des clics sur les onglets   */
	/* ------------------------------------- */

	/** Clic sur onglet "+" */
	@Override
	protected void processMouseEvent(MouseEvent e)
	{
		super.processMouseEvent(e);
		boolean isMouse = e.getID() == MouseEvent.MOUSE_PRESSED;
		if ( !isMouse ) return;

		int index = super.indexAtLocation(e.getX(), e.getY());
		if (!isAddTabHeaderIndex(index) ) return;

		TabHeader tabHeader = (TabHeader)this.getTabComponentAt(index);

		this.tabAdding( new TabEvent(tabHeader) );
	}

	@Override
	public void tabClosing(TabEvent e)
	{
		int index = super.indexOfTabComponent(e.getTabHeader());
		TabItem tabItem = this.getTabItemAt(index);
		this.remove(index);
		
		if( this.tabItemListener != null )
			this.tabItemListener.tabItemClosed( new TabItemEvent(tabItem) );
	}

	@Override
	public void tabAdding(TabEvent e)
	{
		SimpleTabHeader tabHeader = new SimpleTabHeader("new tab");
		TabItem tabItem = new TabItem(tabHeader, new JPanel());
		this.addTab( tabItem );

		if( this.tabItemListener != null )
			this.tabItemListener.tabItemAdded( new TabItemEvent(tabItem) );
	}
}
