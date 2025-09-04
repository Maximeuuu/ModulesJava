package com.maximeuuu.outils.klv.conversion.misb;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.io.InputStream;

public class ChargeurMisb {
	private ChargeurMisb() { /* Classe utilitaire */ };

	private static final String CHEMIN_FICHIER = "misb/misb0601.csv";
	private static final Map<Integer, Conversion> mapConversions = new HashMap<Integer, Conversion>();

	/*
	* Lit le fichier misb.csv qui contient la façon dont sont stockées le éléments de la normes et les enregistre
	*/
	static{		
		//System.out.println("Chemin du fichier : " + ChargeurMisb.class.getClassLoader().getResource(CHEMIN_FICHIER));
		try ( InputStream inputStream = ChargeurMisb.class.getClassLoader().getResourceAsStream(CHEMIN_FICHIER);
			Scanner scanner = new Scanner(inputStream, "UTF-8")) {
			int cpt = 0;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				boolean ligneContientDonnees = cpt > 1; //evite les lignes de declaration au debut du document
				if ( ligneContientDonnees ) {					
					String[] values = line.split(",");
					boolean nombreDeChampsValide = values.length >= 7;

					if ( nombreDeChampsValide ) {
						Conversion conversion = new Conversion();
						conversion.setFormatSoftware(new Conversion.Format(Double.parseDouble(values[1]), Double.parseDouble(values[2]),Double.parseDouble(values[3])));
						conversion.setFormatKlv(new Conversion.Format(Double.parseDouble(values[4]), Double.parseDouble(values[5]),Double.parseDouble(values[6])));
						mapConversions.put(Integer.parseInt(values[0]), conversion);
					}
				}
				cpt++;
			}
		} catch (FileNotFoundException e) {
			System.err.println("Fichier " + CHEMIN_FICHIER + " non trouvé !");
		}
		catch( Exception e ){
			System.err.println("Erreur lors du chargement du fichier : " + e.getMessage() );
		}
	}

	public static Conversion getConversionById( int id ){
		if( conversionExiste(id) )
			return ChargeurMisb.mapConversions.get( id );
		else
			return null;
	}

	private static boolean conversionExiste( int id ){
		return ChargeurMisb.mapConversions.containsKey( id );
	}
}
