package com.maximeuuu.outils.klv.objets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestKlvDecode {
	
	private KlvDecode klv;
	private KlvDecode klvSimilaire;
	private KlvDecode klvDifferent;

	@Before
	public void setUp(){
		this.klv = new KlvDecode();
		this.klvSimilaire = new KlvDecode();
		this.klvDifferent = new KlvDecode();
	}

	@After
	public void tearDown(){
		this.klv = null;
		this.klvSimilaire = null;
		this.klvDifferent = null;
	}

	@Test
	public void testGetSetKey(){
		final int cle = 12;

		assertEquals(0, this.klv.getKey());
		this.klv.setKey( cle );
		assertEquals(cle, this.klv.getKey());
	}

	@Test
	public void testGetSetValue(){
		final double valeur = 1234.56;
		
		assertEquals(0, this.klv.getValue(), 0.001);
		this.klv.setValue(valeur);
		assertEquals(valeur, this.klv.getValue(), 0.001);
	}

	@Test
	public void testEquals(){
		final int cle = 12;
		final double valeur = 123.45;

		this.klv.setKey(cle);
		this.klv.setValue(valeur);

		this.klvSimilaire.setKey(cle);
		this.klvSimilaire.setValue(valeur);

		assertTrue( this.klv.equals( this.klvSimilaire ) );

		this.klvDifferent.setKey( 36 );
		this.klvDifferent.setValue( valeur );

		assertFalse( this.klv.equals( this.klvDifferent ) );

		this.klvDifferent.setKey( cle );
		this.klvDifferent.setValue( 100 );

		assertFalse( this.klv.equals( this.klvDifferent ) );
	}
}
