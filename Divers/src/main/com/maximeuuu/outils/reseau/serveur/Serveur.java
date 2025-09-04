package com.maximeuuu.outils.reseau.serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.maximeuuu.outils.reseau.event.ServeurListener;

public class Serveur extends ServerSocket
{
	private Set<ServeurListener> setofListener;
	private List<GerantDeClient> ensGerantDeClient;

	/* Constructeur */

	public Serveur( int port ) throws IOException
	{
		super( port );
		this.setofListener = new HashSet<>();
		this.ensGerantDeClient = new ArrayList<>();
		System.out.println("Serveur : Ouverture sur le port " + port );
	}

	/* Accesseurs */

	public void addListener( ServeurListener listener )
	{
		this.setofListener.add( listener );
	}

	public void removeListener( ServeurListener listener )
	{
		this.setofListener.remove( listener );
	}

	public GerantDeClient getGerantDeClient( int index )
	{
		return this.ensGerantDeClient.get( index );
	}

	public int getNbGerantDeClient()
	{
		return this.ensGerantDeClient.size();
	}

	/* Méthodes */

	public void attendreConnexions( int nbMax ) throws IOException
	{
		int nbNouvellesConnexions = 0;
		while( nbNouvellesConnexions < nbMax )
		{
			attendreUneConnexion();
			nbNouvellesConnexions++;
		}
	}

	public void attendreConnexions() throws IOException
	{
		while( true )
		{
			attendreUneConnexion();
		}
	}

	public void attendreUneConnexion() throws IOException
	{
		Socket socket = this.accept();
		System.out.println( "Serveur : Connexion d'un nouveau client" );
		this.gererNouvelleConnexion( socket );
	}

	private void gererNouvelleConnexion( Socket socket )
	{
		try
		{
			GerantDeClient gdc = new GerantDeClient( socket );
			Thread threadGerant = new Thread(gdc);

			threadGerant.start();
			this.ensGerantDeClient.add( gdc );
			System.out.println( "Serveur : Prise en charge du client" );

			this.notifierNouvelleConnexion( gdc );
		}
		catch( IOException ioe )
		{
			System.err.println("Impossible de gérer le client : " + ioe.getMessage() );
			ioe.printStackTrace();
		}
	}

	private void notifierNouvelleConnexion( GerantDeClient gdc )
	{
		for( ServeurListener listener : this.setofListener )
		{
			listener.onClientConnected( gdc );
		}
	}

	//TODO: mieux gérer les erreurs, pour pouvoir connaitre la déconnexion d'un client et de pouvoir utiliser les events

	public void envoyerMessageGlobal( String message )
	{
		for( GerantDeClient gdc : this.ensGerantDeClient )
		{
			gdc.envoyerMessage( message );
		}
	}
}
