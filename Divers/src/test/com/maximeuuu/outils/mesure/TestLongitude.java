package com.maximeuuu.outils.mesure;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLongitude {

	private Longitude longitudeMisb;
	private Longitude longitudeDrone;

	//TODO: ajouter tests avec -MIN, -MAX, 0

	@Before
	public void setUp() {
		this.longitudeMisb = new Longitude(128.427);
		this.longitudeDrone = new Longitude(-1.999);
	}

	@After
	public void tearDown(){
		this.longitudeMisb = null;
		this.longitudeDrone = null;
	}

	@Test
	public void testGetFormatDegresMinutesDecimales(){
		assertEquals( "%03d%07.4f", this.longitudeMisb.getFormatDegresMinutesDecimales() );
		assertEquals( "%03d%07.4f", this.longitudeDrone.getFormatDegresMinutesDecimales() );
	}

	@Test
	public void testGetHemisphere(){
		assertEquals( 'E', this.longitudeMisb.getHemisphere() );
		assertEquals( 'W', this.longitudeDrone.getHemisphere() );
	}

	/* TESTS ABSTRACT COORDONNEE GEOGRAPHIQUE */

	@Test
	public void testGetMinutesDecimales(){
		assertEquals( 25.6200, this.longitudeMisb.getMinutesDecimales(), 0.001 );
		assertEquals( -59.9400, this.longitudeDrone.getMinutesDecimales(), 0.001 );
	}

	@Test
	public void testGetMinutes(){
		assertEquals( 25, this.longitudeMisb.getMinutes() );
		assertEquals( -59, this.longitudeDrone.getMinutes() );
	}

	@Test
	public void testGetSecondesDecimales(){
		assertEquals( 37.200, this.longitudeMisb.getSecondesDecimales(), 0.001 );
		assertEquals( -56.400, this.longitudeDrone.getSecondesDecimales(), 0.001 );
	}

	@Test
	public void testGetSecondes(){
		assertEquals( 37, this.longitudeMisb.getSecondes());
		assertEquals( -56, this.longitudeDrone.getSecondes());
	}

	@Test
	public void testGetStringDegresDecimaux(){
		assertEquals( "128.427000", this.longitudeMisb.getStringDegresDecimaux() );
		assertEquals( "1.999000", this.longitudeDrone.getStringDegresDecimaux() );
	}

	@Test
	public void testGetStringDegresMinutesDecimales(){
		assertEquals( "12825.6200", this.longitudeMisb.getStringDegresMinutesDecimales() );
		assertEquals( "00159.9400", this.longitudeDrone.getStringDegresMinutesDecimales() );
	}

	@Test
	public void testToStringDegresSexagésimaux(){
		assertEquals( "128° 25' 37,200'' E", this.longitudeMisb.toStringDegresSexagésimaux() );
		assertEquals( "1° 59' 56,400'' W", this.longitudeDrone.toStringDegresSexagésimaux() );
	}

	@Test
	public void testToStringDegresDecimaux(){
		assertEquals(  "128.427000° E", this.longitudeMisb.toStringDegresDecimaux() );
		assertEquals( "1.999000° W", this.longitudeDrone.toStringDegresDecimaux() );
	}

	@Test
	public void testToStringDegresMinutesDecimales(){
		assertEquals( "128° 25,6200' E", this.longitudeMisb.toStringDegresMinutesDecimales() );
		assertEquals( "1° 59,9400' W", this.longitudeDrone.toStringDegresMinutesDecimales() );
	}

	@Test
	public void testToString(){
		assertEquals( this.longitudeMisb.toStringDegresMinutesDecimales(), this.longitudeMisb.toString() );
		assertEquals( this.longitudeDrone.toStringDegresMinutesDecimales(), this.longitudeDrone.toString() );
	}
}
