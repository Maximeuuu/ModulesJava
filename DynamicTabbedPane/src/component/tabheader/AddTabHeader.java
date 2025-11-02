package component.tabheader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/** Onglet spécial "+" */
public class AddTabHeader extends TabHeader //implements AddTabHeaderListener
{
	public AddTabHeader( )
	{
		super();

		setLayout(new BorderLayout()); // occupe tout l'espace de l'onglet

		JLabel plusIcon = new JLabel("+", SwingConstants.CENTER);
		plusIcon.setFont(new Font("Dialog", Font.BOLD, 16));
		plusIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		plusIcon.setHorizontalAlignment(SwingConstants.CENTER);
		plusIcon.setVerticalAlignment(SwingConstants.CENTER);

		/*this.setBackground(Color.black); //DEBUG
		this.setOpaque(true);
		this.setBorder(null);
		this.setPreferredSize(null);*/

		super.add(plusIcon, BorderLayout.CENTER);
	}

	/*@Override
	public java.awt.Dimension getPreferredSize()
	{
		return new java.awt.Dimension(24, 20);
	}*/

	@Override
	public void doLayout()
	{
		if(getComponentCount() > 0)
		{
			getComponent(0).setBounds(0, 0, getWidth(), getHeight());
		}
	}

	@Override
	public Insets getInsets()
	{
		return new Insets(0, 0, 0, 0);
	}

}