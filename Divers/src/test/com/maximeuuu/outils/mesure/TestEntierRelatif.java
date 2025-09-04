package com.maximeuuu.outils.mesure;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestEntierRelatif {

	private EntierRelatif entierMisb;
	private EntierRelatif entierAvecDoubleInferieur;
	private EntierRelatif entierAvecDoubleSuperieur;

	@Before
	public void setUp(){
		this.entierMisb = new EntierRelatif(36077);
		this.entierAvecDoubleInferieur = new EntierRelatif(3.1415);
		this.entierAvecDoubleSuperieur = new EntierRelatif(9.99);
	}

	@After
	public void tearDown(){
		this.entierMisb = null;
		this.entierAvecDoubleInferieur = null;
		this.entierAvecDoubleSuperieur = null;
	}

	@Test
	public void testGetValeur(){
		assertEquals(36077, this.entierMisb.getValeur(), 0.0000001);
		assertEquals(3, this.entierAvecDoubleInferieur.getValeur(), 0.0000001);
		assertEquals(10, this.entierAvecDoubleSuperieur.getValeur(), 0.0000001);
	}

	@Test
	public void testToString(){
		assertEquals("36077", this.entierMisb.toString());
		assertEquals("3", this.entierAvecDoubleInferieur.toString());
		assertEquals("10", this.entierAvecDoubleSuperieur.toString());
	}
}
