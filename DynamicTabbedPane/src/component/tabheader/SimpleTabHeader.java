package component.tabheader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseEvent;

import event.TabHeaderListener;
import event.TabEvent;

public class SimpleTabHeader extends TabHeader
{
	private TabHeaderListener tabHeaderListener;
	private String title;
	private JLabel lblTitle;

	private boolean isCloseable;
	private JButton btnClose;

	public SimpleTabHeader(String title)
	{
		super();
		this.title = title;
	
		this.lblTitle = new JLabel(title);
		this.lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		super.add(this.lblTitle);
		
		/*JPanel outer = new JPanel(new BorderLayout());
		outer.setBorder(new EmptyBorder(10,10,10,10));

		outer.add(this.lblTitle);

		super.setBorder(new EmptyBorder(5,5,5,5));
		super.setBackground(Color.red);
		super.setOpaque(true);
		super.add( outer );*/ //DEBUG

		this.btnClose = this.generateBtnClose();
		super.add( this.btnClose );
	}

	private JButton generateBtnClose()
	{
		final String CROSS_SYMBOL = "\u2716";
		JButton closeButton = new JButton( CROSS_SYMBOL );
		closeButton.setBorder(BorderFactory.createEmptyBorder());
		closeButton.setContentAreaFilled(false);
		closeButton.setFocusPainted(false);
		closeButton.setOpaque(false);
		closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closeButton.setForeground(Color.BLACK);
		closeButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 1));
	
		/*closeButton.setBackground(Color.blue); //DEBUG
		closeButton.setOpaque(true);*/

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
				System.out.println("Close action (tab header)");

				if( SimpleTabHeader.this.tabHeaderListener != null )
				{
					SimpleTabHeader.this.tabHeaderListener.tabClosing( new TabEvent(SimpleTabHeader.this) );
				}
			}
		});
		return closeButton;
	}

	public void setTabHeaderListener( TabHeaderListener listener )
	{
		this.tabHeaderListener = listener;
	}

	public String getTitle()
	{
		return this.title;
	}

	public void setTitle( String title )
	{
		this.title = title;
		this.lblTitle.setText(this.title);
	}

	public void setCloseable( boolean isCloseable )
	{
		this.isCloseable = isCloseable;
		this.btnClose.setVisible(isCloseable);
	}

	public boolean isCloseable()
	{
		return this.isCloseable;
	}
}
