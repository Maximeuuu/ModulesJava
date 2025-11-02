package event;

import java.util.EventListener;

public interface AddTabHeaderListener extends EventListener
{
	public void tabAdding(TabEvent e);
}
