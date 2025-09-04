package com.maximeuuu.outils.klv.conversion.misb;

import com.maximeuuu.outils.klv.conversion.IDecodeurTlv;
import com.maximeuuu.outils.klv.objets.KlvDecode;
import com.maximeuuu.outils.klv.objets.KlvEncode;

import java.math.BigInteger;

public class DecodeurMisb implements IDecodeurTlv{
	
	public DecodeurMisb( ){
		
	}

	private int bytesCleToInt( byte[] data ){
		return new BigInteger( data ).intValue();
	}

	private long bytesValeurToLong( byte[] data, Conversion conversion ){
		if (data == null || data.length == 0 || data.length > 8) {
			throw new IllegalArgumentException("Le tableau de bytes doit contenir entre 1 et 8 éléments.");
		}
	
		long resultat = 0;
		for (int i = 0; i < data.length; i++) {
			resultat = (resultat << 8) | (data[i] & 0xFF);
		}
	
		if( conversion.getFormatKlv().estSigne() ) {
			// Gestion du signe pour les nombres signés
			int bitsValides = data.length * 8;
			resultat = (resultat << (64 - bitsValides)) >> (64 - bitsValides);
		}
	
		return resultat;
	}

	@Override
	public KlvDecode decoder(KlvEncode klvEncode) {
		int cle = this.bytesCleToInt( klvEncode.getKey() );
		Conversion conversion = ChargeurMisb.getConversionById( cle );

		if( conversion == null ){
			return new KlvDecode( cle, 0.0);
		}
		else{
			long valeurKlv = this.bytesValeurToLong( klvEncode.getValue(), conversion );
			double valeurSoft = conversion.valeurKlvToValeurSoftware(valeurKlv);
			return new KlvDecode( cle, valeurSoft);
		}
	}
}
