package com.maximeuuu.outils.klv.conversion;

import com.maximeuuu.outils.klv.objets.KlvDecode;
import com.maximeuuu.outils.klv.objets.KlvEncode;

public interface IDecodeurTlv {
	public KlvDecode decoder( KlvEncode klvEncode );
}
