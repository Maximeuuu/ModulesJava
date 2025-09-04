package com.maximeuuu.outils.reseau.serveur;

import java.io.IOException;
import java.net.Socket;

import com.maximeuuu.outils.reseau.client.Communication;

public class GerantDeClient extends Communication implements Runnable
{
	private boolean running;

	GerantDeClient( Socket socket ) throws IOException
	{
		super( socket );
		this.running = true;
	}

	public void close()
	{
		this.running = false;
	}

	@Override
	public void run()
	{
		while( this.running )
		{	
		}
	}
}
