package com.maximeuuu.outils.klv.objets;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestKlvEncode {
	
	private KlvEncode klv;
	private KlvEncode klvSimilaire;
	private KlvEncode klvDifferent;

	@Before
	public void setUp(){
		this.klv = new KlvEncode();
		this.klvSimilaire = new KlvEncode();
		this.klvDifferent = new KlvEncode();
	}

	@After
	public void tearDown(){
		this.klv = null;
		this.klvSimilaire = null;
		this.klvDifferent = null;
	}

	@Test
	public void testGetSetKey(){
		final byte[] data = {1};

		assertArrayEquals(new byte[0], this.klv.getKey());
		this.klv.setKey( data );
		assertArrayEquals(data, this.klv.getKey());
	}

	@Test
	public void testGetSetLength(){
		final int data = 4;
		
		assertEquals( 0, this.klv.getLength() );
		this.klv.setLength(data);
		assertEquals(data, this.klv.getLength());
	}

	@Test
	public void testGetSetValue(){
		final byte[] data = {0, 17, 34, 51};
		
		assertArrayEquals( new byte[0], this.klv.getValue() );
		this.klv.setValue(data);
		assertArrayEquals(data, this.klv.getValue());
	}

	@Test
	public void testIsValid(){
		final int longueur = 4;
		final byte[] valeur = {0, 17, 34, 51};

		assertTrue( this.klv.isValid() );

		this.klv.setLength(longueur);

		assertFalse( this.klv.isValid() );

		this.klv.setValue(valeur);

		assertTrue( this.klv.isValid() );
	}

	@Test
	public void testEquals(){
		final byte[] cleData = {1};
		final int longueur = 4;
		final byte[] valeurData = {0, 17, 34, 51};

		this.klv.setKey(cleData);
		this.klv.setLength(longueur);
		this.klv.setValue(valeurData);

		this.klvSimilaire.setKey(cleData);
		this.klvSimilaire.setLength(longueur);
		this.klvSimilaire.setValue(valeurData);

		assertTrue( this.klv.equals( this.klvSimilaire ) );

		this.klvDifferent.setKey( new byte[]{5});
		this.klvDifferent.setLength(longueur);
		this.klvDifferent.setValue(valeurData);

		assertFalse( this.klv.equals( this.klvDifferent ) );
	}
}
