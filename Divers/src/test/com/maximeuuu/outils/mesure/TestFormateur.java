package com.maximeuuu.outils.mesure;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestFormateur {
	private NombreDecimal entierNaturel;

	@Before
	public void setUp(){
		this.entierNaturel = new EntierRelatif( 374_000 );
	}

	@After
	public void tearDown(){
		this.entierNaturel = null;
	}

	@Test
	public void testGetValeur(){
		assertEquals(374_000, this.entierNaturel.getValeur(), 0.0000001);
	}

	@Test
	public void testToString(){
		assertEquals("374000", this.entierNaturel.toString());
	}
}
