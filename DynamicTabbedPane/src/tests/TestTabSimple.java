package tests;

import javax.swing.*;

import component.DynamicTabbedPane;

public class TestTabSimple
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(() ->
		{
			OutilsTest.generateFrame( new DynamicTabbedPane() );
		});
	}
}