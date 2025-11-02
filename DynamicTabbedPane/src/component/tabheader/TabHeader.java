package component.tabheader;

import java.awt.FlowLayout;
import javax.swing.JPanel;

/** Classe de base pour tous les onglets */
public abstract class TabHeader extends JPanel
{
	public TabHeader()
	{
		super(new FlowLayout(FlowLayout.LEFT, 0, 0));
		setOpaque(false);
	}
}