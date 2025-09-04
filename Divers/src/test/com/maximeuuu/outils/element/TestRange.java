package com.maximeuuu.outils.element;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestRange
{
	private static final double DELTA_DOUBLES = 0.01;
	private Range range;

	@Before
	public void setUp() throws Exception
	{
		this.range = Range.creerRange(5.0, 2.0);
	}

	@After
	public void tearDown() throws Exception
	{
		this.range = null;
	}

	@Test
	public void testCreerRangeAvecTableau()
	{
		double[] valeurs = {3.0, 7.0};
		Range range = Range.creerRange(valeurs);

		assertEquals(3.0, range.getMin(), DELTA_DOUBLES);
		assertEquals(7.0, range.getMax(), DELTA_DOUBLES);
	}

	@Test
	public void testCreerRangeAvecValeurs()
	{
		assertEquals(2.0, range.getMin(), DELTA_DOUBLES);
		assertEquals(5.0, range.getMax(), DELTA_DOUBLES);
	}

	@Test
	public void testEstDansIntervalle()
	{
		assertTrue(range.estDansIntervalle(3.0));
		assertFalse(range.estDansIntervalle(1.0));
		assertFalse(range.estDansIntervalle(6.0));
	}

	@Test
	public void testGenererValeur()
	{
		double valeur = range.genererValeur();

		assertTrue(valeur >= 2.0 && valeur <= 5.0);
	}

}
