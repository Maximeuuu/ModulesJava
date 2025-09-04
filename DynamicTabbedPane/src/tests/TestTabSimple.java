package tests;

import java.awt.BorderLayout;
import javax.swing.*;

import component.DynamicTabbedPane;
import component.TabItem;
import component.tabheader.SimpleTabHeader;
import event.TabAddListener;

public class TestTabSimple
{
	private static int tabCounter = 1; // compteur global pour donner un ID unique a chaque onglet

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

		// Listener sur "+"
		tabbedPane.setTabAddListener(new TabAddListener()
		{
			@Override
			public TabItem onAdd(int index)
			{
				int id = tabCounter++;
				System.out.println("Ajout d'un onglet #" + id);
				
				JPanel panel = new JPanel(new BorderLayout());
				panel.add(new JLabel("Contenu de l’onglet " + id, SwingConstants.CENTER), BorderLayout.CENTER);

				return new TabItem( new SimpleTabHeader(tabbedPane, "Onglet " + id), panel);
			}
		});
		
		return tabbedPane;
	}
}