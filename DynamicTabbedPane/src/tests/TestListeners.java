package tests;

import javax.swing.SwingUtilities;

import component.DynamicTabbedPane;
import event.TabItemEvent;
import event.TabItemListener;

public class TestListeners
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(() ->
		{
			OutilsTest.generateFrame( getDynamicTabbedPane() );
		});
	}

	public static DynamicTabbedPane getDynamicTabbedPane()
	{
		DynamicTabbedPane tabbedPane = new DynamicTabbedPane();
		tabbedPane.setTabItemListener( new MyTabItemListener() );
		return tabbedPane;
	}

	private static class MyTabItemListener implements TabItemListener
	{
		@Override
		public void tabItemAdded(TabItemEvent e)
		{
			System.out.println("Un onglet a été ajouté : " + e.getTabItem().getTabHeader().getTitle());
		}

		@Override
		public void tabItemClosed(TabItemEvent e)
		{
			System.out.println("Un onglet a été fermé : " + e.getTabItem().getTabHeader().getTitle());
		}
	}
}
