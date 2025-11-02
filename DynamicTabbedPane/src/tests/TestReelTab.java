package tests;

import java.awt.Checkbox;
import java.awt.Component;

import javax.swing.*;

import component.DynamicTabbedPane;
import component.tabheader.SimpleTabHeader;
import event.TabItemEvent;
import event.TabItemListener;

public class TestReelTab
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(() ->
		{
			OutilsTest.generateFrame( TestReelTab.getDynamicTabbedPane() );
		});
	}

	public static DynamicTabbedPane getDynamicTabbedPane()
	{
		DynamicTabbedPane tabbedPane = new DynamicTabbedPane();
		tabbedPane.setTabItemListener( new TabItemListener() {
			@Override
			public void tabItemAdded(TabItemEvent e)
			{
				Component component = e.getTabItem().getTabContent();
				
				//TODO: cette partie est un peu bizarre : je peux caster en JPanel pcq je sais que de base c'en est un (quand je créé par défaut) mais faut trouver un autre moyen pour mettre du contenu
				//Par exemple mettre un "setContent" comme le "setTitle" du headerTab
				if( component instanceof JPanel )
				{
					((JPanel) component).add( TestReelTab.getContent() );
				}
				//fin

				this.updateTitles();
			}

			@Override
			public void tabItemClosed(TabItemEvent e)
			{
				this.updateTitles();
			}

			private void updateTitles()
			{
				int cptTab=1;
				for(int cpt=0; cpt<tabbedPane.getTabCount(); cpt++)
				{
					Component component = tabbedPane.getTabComponentAt(cpt);
					if( component instanceof SimpleTabHeader )
					{
						((SimpleTabHeader)component).setTitle("Onglet " + cptTab++);
					}
				}
			}
		});

		return tabbedPane;
	}

	public static JPanel getContent()
	{
		JPanel panel = new JPanel();
		panel.setLayout( new BoxLayout(panel, BoxLayout.Y_AXIS) );
		panel.add( new JLabel("Saisie de texte : ") );
		panel.add( new JTextField() );
		panel.add( new Checkbox() );
		return panel;
	}
}
