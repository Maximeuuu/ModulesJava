package tests;

import javax.swing.SwingUtilities;

import component.DynamicTabbedPane;
import event.TabEvent;

public class TestTabLimites
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
		DynamicTabbedPane tabbedPane = new DynamicTabbedPane()
		{
			@Override public void tabAdding(TabEvent e)
			{
				System.out.println(super.getClosableTabCount());
				final int NB_MAX_TAB = 3;
				if( super.getClosableTabCount() < NB_MAX_TAB )
					super.tabAdding(e);
				else
					System.out.println(String.format("[TestTabLimites] nombre d'onglets max atteint (%d)", NB_MAX_TAB));
			}
		};
		return tabbedPane;
	}
}
