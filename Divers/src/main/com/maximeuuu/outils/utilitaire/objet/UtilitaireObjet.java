package com.maximeuuu.outils.utilitaire.objet;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitaire sur la gestion des objets
 * @author Maxime Lemoine
 */
public abstract class UtilitaireObjet
{
	/**
	 * Classe utilitaire sans constructeur
	 */
	private UtilitaireObjet () {}

	/**
	 * Conversion d'une instance en un tableau d'objet contenant tous ses attributs
	 * @param instance : Objet à convertir en tableau
	 */
	public static Object[] toArray( Object instance )
	{
		Class<?> classe = instance.getClass( );
		Field[] champs = classe.getDeclaredFields( );
		List<Object> attributs = new ArrayList<>( );

		try
		{
			for (Field champ : champs)
			{
				champ.setAccessible( true );
				attributs.add( champ.get( instance ) );
				champ.setAccessible( false );
			}
		}
		catch ( IllegalAccessException e )
		{
			e.printStackTrace();
		}

		return attributs.toArray();
	}

	/**
	 * Conversion d'un tableau d'objet contenant des attributs en une nouvelle instance du type passé en paramètre
	 * @param attributs : Tableau d'objet contenant les attributs
	 * @param classe : Classe de l'instance à créer
	 */
	public static <T> T toObject( Object[] attributs, Class<T> classe )
	{
		try
		{
			T instance = classe.getDeclaredConstructor().newInstance();

			Field[] champs = classe.getDeclaredFields();
			for( int i = 0; i < champs.length; i++ )
			{
				Field champ = champs[i];
				champ.setAccessible( true );
				champ.set( instance, attributs[i] );
				champ.setAccessible( false );
			}

			return instance;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
