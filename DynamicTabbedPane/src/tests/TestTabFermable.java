package tests;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.*;

import component.DynamicTabbedPane;
import component.TabItem;
import component.tabheader.ClosableTabHeader;
import event.TabAddListener;
import event.TabCloseListener;

public class TestTabFermable
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

				return new TabItem( new ClosableTabHeader(tabbedPane, "Onglet " + id), panel);
			}
		});

		// Listener sur fermeture
			tabbedPane.setTabCloseListener(new TabCloseListener()
			{
				@Override
				public void onClose(int tabIndex, Component component)
				{
					String content = "null";
					if( component != null )
					{
						content = component.getClass().getSimpleName();
					}

					System.out.println("Fermeture de l’onglet à l'index " + tabIndex + " (contenu = " + content + ")");
				}
			});
		
		return tabbedPane;
	}
}
