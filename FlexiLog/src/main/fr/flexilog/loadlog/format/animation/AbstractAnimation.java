package fr.flexilog.loadlog.format.animation;

public abstract class AbstractAnimation
{
	protected Direction direction;

	public AbstractAnimation( Direction direction )
	{
		this.direction = direction;
	}

	public AbstractAnimation()
	{
		this( Direction.LEFT_TO_RIGHT );
	}
}
