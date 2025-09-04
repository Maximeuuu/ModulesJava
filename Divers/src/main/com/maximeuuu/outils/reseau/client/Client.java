package com.maximeuuu.outils.reseau.client;

import java.io.IOException;
import java.net.Socket;

public class Client extends Communication
{
	public Client( String adresse, int port ) throws IOException
	{
		super( new Socket(adresse, port) );
	}
}
