package tests;

import javax.swing.*;

import component.DynamicTabbedPane;

public class TestCompatibiliteSwing
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
		tabbedPane.setUI( new javax.swing.plaf.basic.BasicTabbedPaneUI() );
		//tabbedPane.setUI( new javax.swing.plaf.metal.MetalTabbedPaneUI() );

		tabbedPane.addTab("code", null);
		tabbedPane.addTab("code", new JLabel("Ajout manuel avec addtab"));

		tabbedPane.add(new JLabel("add component"));
		tabbedPane.add(new JLabel("add component at index 3"), 3); //tester index plus grand / égal à "+"
		tabbedPane.add("code", new JLabel("add component with title"));

		tabbedPane.addTab("addtab1", new JLabel("Ajout manuel avec addtab"));
		tabbedPane.addTab("addtab2", null, new JLabel("Ajout manuel avec addtab"));
		tabbedPane.addTab("addtab3", null, new JLabel("Ajout manuel avec addtab"), "indice");
		tabbedPane.insertTab("inserttab", null, new JLabel("Ajout manuel avec addtab"), "indice", 3);
		
		tabbedPane.setSelectedIndex(0); //TODO: regarder pourquoi de base l'affichage est "loin" (surement parce que les composants ne sont pas encore charges et que j'utilise "selectTab")

		return tabbedPane;
	}
}
