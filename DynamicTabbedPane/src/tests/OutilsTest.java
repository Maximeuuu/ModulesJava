package tests;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class OutilsTest
{
	public static final void generateFrame( JTabbedPane pane )
	{
		JFrame frame = new JFrame("Test DynamicTabbedPane");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(pane, BorderLayout.CENTER);
		frame.setSize(600, 400);
		frame.setVisible(true);
	}
}
