package com.maximeuuu.outils.mesure;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestNombreDecimal {
	
	private NombreDecimal nombreEntier;
	private NombreDecimal nombreDecimalPositif;
	private NombreDecimal nombreDecimalNegatif;

	@Before
	public void setUp(){
		this.nombreEntier = new NombreDecimal( 250 );
		this.nombreDecimalPositif = new NombreDecimal( 3.1415 );
		this.nombreDecimalNegatif = new NombreDecimal( 9.99 );
	}

	@After
	public void tearDown(){
		this.nombreEntier = null;
		this.nombreDecimalPositif = null;
		this.nombreDecimalNegatif = null;
	}

	@Test
	public void testGetValeur() {
		assertEquals(250.0, this.nombreEntier.getValeur(), 0.01);
		assertEquals(3.1415, this.nombreDecimalPositif.getValeur(), 0.01);
		assertEquals(9.99, this.nombreDecimalNegatif.getValeur(), 0.01);
	}

	@Test
	public void testGetSetUnite() {
		NombreDecimal test = new NombreDecimal(100.0);
		assertEquals("", test.getUnite());
		test.setUnite("mm");
		assertEquals("mm", test.getUnite());
	}

	@Test
	public void testToString(){
		assertEquals("250.0", this.nombreEntier.toString());
		assertEquals("3.1415", this.nombreDecimalPositif.toString());
		assertEquals("9.99", this.nombreDecimalNegatif.toString());
	}
}
