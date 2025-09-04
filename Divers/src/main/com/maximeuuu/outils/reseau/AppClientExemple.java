package com.maximeuuu.outils.reseau;

import java.io.IOException;

import com.maximeuuu.outils.reseau.client.Client;

public class AppClientExemple
{
	private static final String ip = "localhost";
	public AppClientExemple()
	{
		try
		{
			Client client = new Client( ip, AppServeurExemple.PORT );

			while( true )
			{
				String message = client.attendreMessage();
				//System.out.println( message );
			}
		}
		catch( IOException ioe )
		{
			System.err.println( "Erreur de connexion avec le serveur " + ioe.getMessage() );
			ioe.printStackTrace();
		}
	}

	public static void main( String[] args )
	{
		new AppClientExemple();
	}
}
