package tests;

import javax.swing.*;
import component.DynamicTabbedPane;

public class TestTabNonConfigure
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
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.addTab("onglet 1", new JLabel("Ajout manuel depuis le code"));
		tabbedPane.addTab("onglet 2", new JLabel("Ajout manuel depuis le code"));
		
		return tabbedPane;
	}
}
