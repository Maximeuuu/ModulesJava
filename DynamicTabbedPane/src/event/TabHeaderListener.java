package event;

import java.util.EventListener;

public interface TabHeaderListener extends EventListener
{
	public void tabClosing(TabEvent e);
}
