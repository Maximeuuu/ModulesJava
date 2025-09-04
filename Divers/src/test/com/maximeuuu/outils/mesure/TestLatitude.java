package com.maximeuuu.outils.mesure;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLatitude {

	private Latitude latitudeMisb;
	private Latitude latitudeDrone;

	//TODO: ajouter tests avec -MIN, -MAX, 0

	@Before
	public void setUp() {
		this.latitudeMisb = new Latitude(60.176);
		this.latitudeDrone = new Latitude(47.000);
	}

	@After
	public void tearDown(){
		this.latitudeMisb = null;
		this.latitudeDrone = null;
	}

	@Test
	public void testGetFormatDegresMinutesDecimales(){
		assertEquals( "%02d%07.4f", this.latitudeMisb.getFormatDegresMinutesDecimales() );
		assertEquals( "%02d%07.4f", this.latitudeDrone.getFormatDegresMinutesDecimales() );
	}

	@Test
	public void testGetHemisphere(){
		assertEquals( 'N', this.latitudeMisb.getHemisphere() );
		assertEquals( 'N', this.latitudeDrone.getHemisphere() );
	}

	/* TESTS ABSTRACT COORDONNEE GEOGRAPHIQUE */

	@Test
	public void testGetMinutesDecimales(){
		assertEquals( 10.560, this.latitudeMisb.getMinutesDecimales(), 0.001 );
		assertEquals( 0.000, this.latitudeDrone.getMinutesDecimales(), 0.001 );
	}

	@Test
	public void testGetMinutes(){
		assertEquals( 10, this.latitudeMisb.getMinutes() );
		assertEquals( 0, this.latitudeDrone.getMinutes() );
	}

	@Test
	public void testGetSecondesDecimales(){
		assertEquals( 33.6, this.latitudeMisb.getSecondesDecimales(), 0.001 );
		assertEquals( 0.0, this.latitudeDrone.getSecondesDecimales(), 0.001 );
	}

	@Test
	public void testGetSecondes(){
		assertEquals( 33, this.latitudeMisb.getSecondes());
		assertEquals( 0, this.latitudeDrone.getSecondes());
	}

	@Test
	public void testGetStringDegresDecimaux(){
		assertEquals( "60.176000", this.latitudeMisb.getStringDegresDecimaux() );
		assertEquals( "47.000000", this.latitudeDrone.getStringDegresDecimaux() );
	}

	@Test
	public void testGetStringDegresMinutesDecimales(){
		assertEquals( "6010.5600", this.latitudeMisb.getStringDegresMinutesDecimales() );
		assertEquals( "4700.0000", this.latitudeDrone.getStringDegresMinutesDecimales() );
	}

	@Test
	public void testToStringDegresSexagésimaux(){
		assertEquals( "60° 10' 33,600'' N", this.latitudeMisb.toStringDegresSexagésimaux() );
		assertEquals( "47° 0' 0,000'' N", this.latitudeDrone.toStringDegresSexagésimaux() );
	}

	@Test
	public void testToStringDegresDecimaux(){
		assertEquals( "60.176000° N", this.latitudeMisb.toStringDegresDecimaux() );
		assertEquals( "47.000000° N", this.latitudeDrone.toStringDegresDecimaux() );
	}

	@Test
	public void testToStringDegresMinutesDecimales(){
		assertEquals( "60° 10,5600' N", this.latitudeMisb.toStringDegresMinutesDecimales() );
		assertEquals( "47° 0,0000' N", this.latitudeDrone.toStringDegresMinutesDecimales() );
	}

	@Test
	public void testToString(){
		assertEquals( this.latitudeMisb.toStringDegresMinutesDecimales(), this.latitudeMisb.toString() );
		assertEquals( this.latitudeDrone.toStringDegresMinutesDecimales(), this.latitudeDrone.toString() );
	}
}
