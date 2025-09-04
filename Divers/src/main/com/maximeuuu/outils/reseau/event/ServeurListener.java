package com.maximeuuu.outils.reseau.event;

import com.maximeuuu.outils.reseau.serveur.GerantDeClient;

public interface ServeurListener
{
	public void onClientConnected( GerantDeClient gdc );
	//public void onClientDisconnected( GerantDeClient gdc );
}
