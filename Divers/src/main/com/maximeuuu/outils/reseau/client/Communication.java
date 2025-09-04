package com.maximeuuu.outils.reseau.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import com.maximeuuu.outils.reseau.event.CommunicationListener;

public abstract class Communication
{
	private Set<CommunicationListener> setofListener;
	private BufferedReader entree;
	private PrintWriter sortie;
	private Socket socket;

	/* Constructeur */

	public Communication( Socket socket ) throws IOException
	{
		this.socket = socket;
		this.setofListener = new HashSet<>();
		this.initialiserEntreeSortie();
	}

	private void initialiserEntreeSortie( )
	{
		try
		{
			this.initialiserEntree();
			this.initialiserSortie();
		}
		catch( IOException ioe )
		{
			System.out.println( "Erreur initialisation entree/sortie : " + ioe.getMessage() );
			ioe.printStackTrace();
		}
	}

	private void initialiserEntree() throws IOException
	{
		InputStream is = this.socket.getInputStream();
		InputStreamReader isr = new InputStreamReader( is );
		BufferedReader br = new BufferedReader( isr );
		this.entree = br;
	}

	private void initialiserSortie() throws IOException
	{
		OutputStream os = this.socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter( os );
		PrintWriter pw = new PrintWriter( osw, true );
		this.sortie = pw;
	}

	/* Accesseurs */

	public void addListener( CommunicationListener listener )
	{
		this.setofListener.add( listener );
	}

	public void removeListener( CommunicationListener listener )
	{
		this.setofListener.remove( listener );
	}

	/* Méthodes */

	public void envoyerMessage( String message )
	{
		System.out.println( "Envois du message : " + message );
		this.sortie.println( message );
	}

	public String attendreMessage() throws IOException
	{
		System.out.println("Connexion : attente du message");
		String message = this.entree.readLine();
		System.out.println( "Connexion : Reception du message \"" + message + "\"" );
		this.notifierReceptionMessage( message );
		return message;
	}

	private void notifierReceptionMessage( String message )
	{
		for( CommunicationListener listener : this.setofListener )
		{
			listener.onMessageReceived( message );
		}
	}
}
