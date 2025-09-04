package com.maximeuuu.outils.mesure;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDistance {
	
	private Distance distance;

	@Before
	public void setUp(){
		this.distance = new Distance(722.819867);
	}

	@After
	public void tearDown(){
		this.distance = null;
	}

	@Test
	public void testToString(){
		assertEquals("722.8199 m", this.distance.toString());
	}

	@Test
	public void testGetStringValeur(){
		assertEquals("722.8199", this.distance.getStringValeur());
	}
}
