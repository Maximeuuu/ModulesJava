package com.maximeuuu.outils.klv.io.validation;

import java.util.Arrays;
import java.util.Collection;

import com.maximeuuu.outils.klv.objets.KlvEncode;

public class ValideurMisb0601 implements IValideur{

	private static final int MIN_VALUE_KEY = 1;
	private static final int MAX_VALUE_KEY = 127;
	private static final byte[] CHECKSUM_KEY = new byte[]{0x01};

	@Override
	public boolean estValide(KlvEncode klv, Collection<KlvEncode> ensTlv) {
		int checksommeAttendu = 0;
		byte[] buffer = new byte[klv.getLength() + klv.getKey().length + 1];
		int index = 0;
	
		// Ajouter la clé du KLV principal au buffer
		System.arraycopy(klv.getKey(), 0, buffer, index, klv.getKey().length);
		index += klv.getKey().length;
	
		// Ajouter la longueur du KLV principal au buffer
		buffer[index++] = (byte) klv.getLength();
	
		for (KlvEncode tlv : ensTlv) {
			if( !tlv.isValid() ) {
				return false;
			}
	
			if( Arrays.equals(tlv.getKey(), CHECKSUM_KEY) ) {
				checksommeAttendu = bytesToShort(tlv.getValue());

				System.arraycopy(tlv.getKey(), 0, buffer, index, tlv.getKey().length);
				index += tlv.getKey().length;
				buffer[index++] = (byte) tlv.getLength();
				
			} else {
				// Ajouter la clé, la longueur et la valeur de chaque TLV au buffer
				System.arraycopy(tlv.getKey(), 0, buffer, index, tlv.getKey().length);
				index += tlv.getKey().length;
				buffer[index++] = (byte) tlv.getLength();
				System.arraycopy(tlv.getValue(), 0, buffer, index, tlv.getValue().length);
				index += tlv.getValue().length;
			}
		}
	
		int checksommeCalculee = calculerChecksum16(buffer, index);
	
		return (checksommeAttendu & 0xFFFF) == checksommeCalculee;

	}
	
	private int calculerChecksum16(byte[] buffer, int length) {
		int bcc = 0;
		for (int i = 0; i < length; i++) {
			bcc += (buffer[i] & 0xFF) << (8 * ((i + 1) % 2));
			bcc &= 0xFFFF; // Masquer pour garder seulement les 16 bits de poids faible
		}
		return bcc;
	}	
	
	private short bytesToShort(byte[] bytes) {
		if (bytes.length == 0) {
			return 0; // ou une autre valeur par défaut appropriée
		} else if (bytes.length == 1) {
			return (short) (bytes[0] & 0xFF);
		} else {
			return (short) (((bytes[0] & 0xFF) << 8) | (bytes[1] & 0xFF));
		}
	}	
}
