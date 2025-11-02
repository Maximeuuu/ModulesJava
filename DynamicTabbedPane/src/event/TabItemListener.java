package event;

import java.util.EventListener;

public interface TabItemListener extends EventListener
{
	public void tabItemAdded(TabItemEvent e);
	public void tabItemClosed(TabItemEvent e);
}
