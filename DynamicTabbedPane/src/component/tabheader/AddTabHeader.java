package component.tabheader;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import component.DynamicTabbedPane;

/** Onglet spécial "+" */
public class AddTabHeader extends TabHeader
{
	public AddTabHeader(DynamicTabbedPane pane)
	{
		super(pane);

		setLayout(new BorderLayout()); // occupe tout l'espace de l'onglet

		JLabel plusIcon = new JLabel("+", SwingConstants.CENTER);
		plusIcon.setFont(new Font("Dialog", Font.BOLD, 16));
		plusIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		super.add(plusIcon, BorderLayout.CENTER);
	}
}