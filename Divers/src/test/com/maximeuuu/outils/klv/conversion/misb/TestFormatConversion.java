package com.maximeuuu.outils.klv.conversion.misb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestFormatConversion {
	
	private Conversion.Format formatNegatif;
	private Conversion.Format formatPositif;

	@Before
	public void setUp(){
		this.formatNegatif = new Conversion.Format( -50, 50, 0);
		this.formatPositif = new Conversion.Format( 0, 100, 12);
	}

	@After
	public void tearDown(){
		this.formatNegatif = null;
		this.formatPositif = null;
	}

	@Test
	public void testEstSigne(){
		assertTrue( this.formatNegatif.estSigne() );
		assertFalse( this.formatPositif.estSigne() );
	}

	@Test
	public void testGetMin(){
		assertEquals( -50, this.formatNegatif.getMin(), 0.001 );
		assertEquals( 0, this.formatPositif.getMin(), 0.001 );
	}
	
	@Test
	public void testGetMax(){
		assertEquals( 50, this.formatNegatif.getMax(), 0.001 );
		assertEquals( 100, this.formatPositif.getMax(), 0.001 );
	}

	@Test
	public void testGetDecalage(){
		assertEquals( 0, this.formatNegatif.getDecalage(), 0.001 );
		assertEquals( 12, this.formatPositif.getDecalage(), 0.001 );
	}
}
