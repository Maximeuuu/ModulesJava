package component.tabheader;

import javax.swing.*;

public class SimpleTabHeader extends TabHeader
{
	private String title;

	public SimpleTabHeader(JTabbedPane pane, String title)
	{
		super(pane);
		this.title = title;

		JLabel label = new JLabel(title);
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		super.add(label);
	}

	public String getTitle()
	{
		return this.title;
	}
}
