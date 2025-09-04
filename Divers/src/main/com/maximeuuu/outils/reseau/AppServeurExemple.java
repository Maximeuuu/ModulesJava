package com.maximeuuu.outils.reseau;

import java.io.IOException;

import com.maximeuuu.outils.reseau.event.CommunicationListener;
import com.maximeuuu.outils.reseau.event.ServeurListener;
import com.maximeuuu.outils.reseau.serveur.GerantDeClient;
import com.maximeuuu.outils.reseau.serveur.Serveur;

public class AppServeurExemple implements ServeurListener, CommunicationListener
{
	public static final int PORT = 5555;

	private Serveur serveur;

	public AppServeurExemple()
	{
		try
		{
			this.serveur = new Serveur( PORT );
			this.serveur.addListener( this );
			this.serveur.attendreConnexions();
			this.serveur.close();
		}
		catch( IOException ioe )
		{
			System.err.println( "Serveur : " + ioe.getMessage() );
			ioe.printStackTrace();
		}
	}

	@Override
	public void onClientConnected( GerantDeClient gdc )
	{
		System.out.println( "AppServeur : Connexion du client " + this.serveur.getNbGerantDeClient() );
		gdc.addListener( this );
		gdc.envoyerMessage( "Bienvenue sur le serveur" );
		this.serveur.envoyerMessageGlobal( "Le client " + this.serveur.getNbGerantDeClient() + " a rejoint le serveur" );
	}

	@Override
	public void onMessageReceived( String message )
	{
		System.out.println( "AppServeur : Message reçu : " + message );
	}

	public static void main(String[] args) throws Exception
	{
		new AppServeurExemple();
	}
}
