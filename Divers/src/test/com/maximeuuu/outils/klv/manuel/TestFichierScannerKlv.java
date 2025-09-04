package com.maximeuuu.outils.klv.manuel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import com.maximeuuu.outils.klv.io.ScannerKlv;
import com.maximeuuu.outils.klv.io.validation.IValideur;
import com.maximeuuu.outils.klv.io.validation.ValideurMisb0601;
import com.maximeuuu.outils.klv.objets.KlvEncode;

public class TestFichierScannerKlv {
	public static void main(String[] args) {
		lireKLV();
	}

	private static void lireKLV(){
		final String CHEMIN = "DOSSIER";
		final String TYPE = "DOSSIER";
		final String FICHIER = "FICHIER.KLV";

		IValideur valideur = new ValideurMisb0601();

		try (InputStream is = creerInputStream( CHEMIN + TYPE + FICHIER);
			ScannerKlv scanner = new ScannerKlv(is)) {

			final byte[] UL = new byte[]{0x06, 0x0E, 0x2B, 0x34, 0x02, 0x0B, 0x01, 0x01, 0x0E, 0x01, 0x03, 0x01, 0x01, 0x00, 0x00, 0x00};
			scanner.setExpectedKey(UL);
			scanner.setExpectedLengthSize(1);

			KlvEncode klv;
			while ((klv = scanner.next()) != null) {
				System.out.println("KLV trouvé : " + klv.toString() + "\n");
				Set<KlvEncode> ensTlv = lireTLV( klv.getValue() );
				if( valideur.estValide(klv, ensTlv) ){
					for( KlvEncode tlv : ensTlv ){
						System.out.println("  * " + tlv.toString());
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Erreur lors de la lecture : " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static Set<KlvEncode> lireTLV( byte[] data ){
		Set<KlvEncode> ensTlv = new HashSet<KlvEncode>();

		try (InputStream is = new ByteArrayInputStream( data );
			ScannerKlv scanner = new ScannerKlv(is)) {

			scanner.setExpectedKeySize(1);
			scanner.setExpectedLengthSize(1);

			KlvEncode klv;
			while ((klv = scanner.next()) != null) {
				ensTlv.add(klv);
			}
		} catch (IOException e) {
			System.err.println("Erreur lors de la lecture : " + e.getMessage());
			e.printStackTrace();
		}

		return ensTlv;
	}

	protected static InputStream creerInputStream(String chemin) throws IOException {
		byte[] bitsFichier = fichierToBits(chemin);
		return new ByteArrayInputStream(bitsFichier);
	}

	private static byte[] fichierToBits(String cheminFichier) throws IOException {
		File fichier = new File(cheminFichier);
		byte[] bitsFichier = new byte[(int) fichier.length()];
		
		try (FileInputStream fis = new FileInputStream(fichier);
			ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			
			byte[] buffer = new byte[1024];
			int bitsLus;
			
			while ((bitsLus = fis.read(buffer)) != -1) {
				bos.write(buffer, 0, bitsLus);
			}
			
			bitsFichier = bos.toByteArray();
		}
		
		return bitsFichier;
	}
}
