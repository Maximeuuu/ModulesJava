package component.tabheader;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import component.DynamicTabbedPane;
import event.TabCloseListener;

/** Onglet closable avec bouton ✖ */
public class ClosableTabHeader extends SimpleTabHeader
{
	public ClosableTabHeader(DynamicTabbedPane pane, String title)
	{
		super(pane, title);
		
		final String CROSS_SYMBOL = "\u2716";
		JButton closeButton = new JButton( CROSS_SYMBOL );
		closeButton.setBorder(BorderFactory.createEmptyBorder());
		closeButton.setContentAreaFilled(false);
		closeButton.setFocusPainted(false);
		closeButton.setOpaque(false);
		closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closeButton.setForeground(Color.BLACK);
		closeButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 1));

		closeButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e) { closeButton.setForeground(Color.RED); }

			@Override
			public void mouseExited(MouseEvent e) { closeButton.setForeground(Color.BLACK); }
		});

		closeButton.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int i = pane.indexOfTabComponent(ClosableTabHeader.this);
				if (i != -1)
				{
					Component component = pane.getComponentAt(i); // <- recuperer avant suppression
					pane.remove(i);
					TabCloseListener listener = pane.getTabCloseListener();
					if (listener != null) listener.onClose(i, component); // passer le component reel
				}
			}
		});

		super.add(closeButton);
	}
}