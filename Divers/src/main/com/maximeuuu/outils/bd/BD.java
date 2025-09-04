package com.maximeuuu.outils.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.maximeuuu.outils.utilitaire.objet.UtilitaireObjet;

/** 
 * @apiNote : Page de gestion de la base de données
 * @author : Maximilien Lesterlin, Maxime Lemoine
 * @version : 2.0 - 23/12/2023
 * @date : 06/12/2023
 * @implNote : cette classe nécessite impérativement une librairie de SGBD tel que postgresql-42.2.5.jar
 */

public class BD
{
	protected static final String JDBC      = "";
	protected static final String LOGIN     = "";
	protected static final String PASSWORD  = /* mot de passe ---> */																																					"";
	protected static final String URL_DISTANT = "" + LOGIN;
	protected static final String URL_LOCAL   = "" + LOGIN;

	private static BD dbInstance;
	Connection co;
	PreparedStatement ps;


	/*---------------------------------------*/
	/*               CONNEXION               */
	/*---------------------------------------*/

	/**
	 * Constructeutr de la classe BD
	 * Permet d'initialiser la connexion à la base de données
	 * @see getInstance ( )
	 * @see JDBC type de driver pour la connexion
	 * @see LOGIN login de connexion
	 * @see PASSWORD mot de passe de la base de données
	 * @see URL_DISTANT 1ere url à tester sur un serveur distant
	 * @see URL_LOCAL 2eme url à tester sur un serveur local
	 */
	private BD ( )
	{
		try
		{
			Class.forName ( JDBC );
			co = DriverManager.getConnection ( URL_DISTANT , LOGIN, PASSWORD );
		}
		catch ( ClassNotFoundException | SQLException e1 )
		{
			System.out.println ( "Erreur de connexion à la base de données " + URL_DISTANT + " : " + e1 );

			try
			{
				Class.forName ( JDBC );
				co = DriverManager.getConnection( URL_LOCAL, LOGIN, PASSWORD );
			}
			catch ( ClassNotFoundException | SQLException e2 )
			{
				System.out.println ( "Erreur de connexion à la base de données " + URL_LOCAL + " : " + e2 );
			}
		}
	}

	/**
	 * Création d'une instance de BD si elle n'existe pas encore
	 * @see BD ( )
	 */
	public static BD getInstance ( )
	{
		return dbInstance != null ? dbInstance : new BD ( );
	}


	/*---------------------------------------*/
	/*         RECUPERATION GENERIQUE        */
	/*---------------------------------------*/

	/**
	 * Permet de récupérer le nombre de valeur d'une table
	 * @param table
	 * @return nbLignes
	 */
	public int getNbTuple ( String table )
	{
		int nbTuple = 0;

		try
		{
			Statement st = co.createStatement ( );
			ResultSet rs = st.executeQuery ( "select count(*) from " + table );
			while ( rs.next ( ) )
			{
				nbTuple = rs.getInt ( 1 );
			}
		}
		catch ( SQLException e )
		{
			System.out.println ( "Erreur getNbTuple ( String table ) : " + e );
		}

		return nbTuple;
	}

	/** 
	 * Méthode générique pour la récupération de table
	 * Exemple d'utilisation : ArrayList<Semestre> ensS= new ArrayList<> (getTable ( Semestre.class ) ) ;
	 */
	public <T> List<T> getTable ( Class<T> type )
	{
		ArrayList<T> lst = new ArrayList<>();

		try
		{
			Statement st = co.createStatement ( );
			ResultSet rs = st.executeQuery ( "SELECT * FROM " + type.getSimpleName ( ) );

			while ( rs.next ( ) )
			{
				try
				{
					/*//Exemple d'utilisation
					if ( type.equals ( Semestre.class )     )
						lst.add ( type.cast ( new Semestre ( rs.getInt ( 1 ), rs.getInt ( 2 ), rs.getInt ( 3 ),rs.getInt ( 4 ), rs.getInt ( 5 ) ) ) );*/

					// Ajouter d'autres conditions pour d'autres classes si nécessaire
				}
				catch ( Exception e )
				{
					e.printStackTrace ( );
				}

			}

		}
		catch ( SQLException e )
		{
			System.out.println ( "Erreur getTable : " + e );
		}

		return lst;
	}

	/**
	 * Permet de récupérer un tableau de données
	 * @param type
	 * @return tableau de données
	 */
	public Object[][] getTableau ( Class<?> type )
	{
		List<?> lst = this.getTable ( type );

		int nbAttributs = type.getDeclaredFields ( ).length;
		Object[][] object = new Object[ lst.size ( )][nbAttributs];

		for ( int lig = 0; lig < object.length-1; lig ++ )
		{
			Object[] tmp = UtilitaireObjet.toArray ( lst.get ( lig ) );

			for ( int col = 0 ; col < nbAttributs; col ++ )
			{
				object[lig][col] = tmp[col];
			}
		}

		return object;
	}

	/**
	 * Permet de récupérer un tableau de données à partir d'une vue, d'une table ou d'une fonction
	 * @param nomRecherche nom de la vue, de la table ou de la fonction
	 * @return tableau de données
	 */
	public Object[][] getTableauParticulier ( String nomRecherche )
	{
		Object[][] tabObjet = null;

		try
		{
			Statement st = co.createStatement ( );
			ResultSet rs = st.executeQuery ( "select * from " + nomRecherche );
			ResultSetMetaData rsmd = rs.getMetaData ( );

			int nbAttributs = rsmd.getColumnCount ( );
			tabObjet = new Object[this.getNbTuple ( nomRecherche )][nbAttributs];

			int cpt = 0;
			while ( rs.next ( ) )
			{
				for ( int i = 0; i < tabObjet[cpt].length-1; i++ )
				{
					try
					{
						Object valeur = rs.getObject ( i );

						tabObjet[cpt][i] = valeur;
					}
					catch ( Exception e )
					{
						System.out.println ( "Problème de conversion : getTableauParticulier( ) " + e );
					}
				}
				cpt++;
			}
		}
		catch ( SQLException e )
		{
			System.out.println ( "Erreur getTableauParticulier ( String nomRecherche ) : " + e );
		}

		return tabObjet;
	}
}
