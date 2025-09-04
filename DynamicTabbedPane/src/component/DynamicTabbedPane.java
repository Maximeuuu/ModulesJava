package component;

import javax.swing.*;

import component.tabheader.AddTabHeader;
import component.tabheader.ClosableTabHeader;
import component.tabheader.SimpleTabHeader;

import java.awt.*;
import java.awt.event.MouseEvent;

import event.TabAddListener;
import event.TabCloseListener;

//TODO: Ajouter raccourcis claviers (tab, fermer, nouvel onglet)

/**
 * JTabbedPane ameliore avec :
 * - Onglet special "+" pour ajouter dynamiquement des onglets
 * - Bouton de fermeture sur les onglets closables
 * - Sous-classes pour chaque type d'onglet
 */
public class DynamicTabbedPane extends JTabbedPane
{
	private TabAddListener tabAddListener;
	private TabCloseListener tabCloseListener;

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
		this.addDefaultAddTab();
	}

	/* ---------------------- */
	/*   Onglet special "+"   */
	/* ---------------------- */

	private void addDefaultAddTab()
	{
		super.addTab(null, null);
		super.setTabComponentAt(this.getClosableTabCount(), new AddTabHeader(this));
	}

	/* ------------- */
	/*   Listeners   */
	/* ------------- */

	public void setTabAddListener(TabAddListener listener) { this.tabAddListener = listener; }
	public void setTabCloseListener(TabCloseListener listener) { this.tabCloseListener = listener; }

	public TabAddListener getTabAddListener(){ return this.tabAddListener; }
	public TabCloseListener getTabCloseListener(){ return this.tabCloseListener; }

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
		super.setTabComponentAt(safeIndex, tabItem.getTabHeader());
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
		final SimpleTabHeader defaultTabHeader = new ClosableTabHeader(this, title);
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

		int index = indexAtLocation(e.getX(), e.getY());
		if (!isAddTabHeaderIndex(index) ) return;

		boolean listenerDefined = tabAddListener != null;
		if ( !listenerDefined ) return;

		TabItem tabItem = tabAddListener.onAdd(getClosableTabCount());
		this.addTab(tabItem);
	}
}
