package component.tabheader;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/** Classe de base pour tous les onglets */
public abstract class TabHeader extends JPanel
{
	protected final JTabbedPane pane;

	public TabHeader(JTabbedPane pane)
	{
		super(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.pane = pane;
		setOpaque(false);
	}
}