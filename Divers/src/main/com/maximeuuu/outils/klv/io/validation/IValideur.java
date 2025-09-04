package com.maximeuuu.outils.klv.io.validation;

import java.util.Collection;

import com.maximeuuu.outils.klv.objets.KlvEncode;

public interface IValideur {
	public boolean estValide( KlvEncode klv, Collection<KlvEncode> ensTlv );
}
