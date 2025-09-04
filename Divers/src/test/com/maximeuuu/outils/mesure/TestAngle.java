package com.maximeuuu.outils.mesure;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestAngle {

	private Angle angleNul;
	private Angle angleNegatif;
	private Angle anglePositif;
	private Angle angleEntier;
	private Angle angleArrondiInf;
	private Angle angleArrondiSup;

	//angle null, negatif, positif, entier, decimal

	@Before
	public void setUp() {
		this.angleNul = new Angle(0.0);
		this.angleNegatif = new Angle(-45);
		this.anglePositif = new Angle(33.33);
		this.angleEntier = new Angle(10);
		this.angleArrondiInf = new Angle(10.1);
		this.angleArrondiSup = new Angle(10.9);
	}

	@After
	public void tearDown(){
		this.angleNul = null;
		this.angleNegatif = null;
		this.anglePositif = null;
		this.angleEntier = null;
		this.angleArrondiInf = null;
		this.angleArrondiSup = null;
	}

	@Test
	public void testGetDegresDecimaux(){
		assertEquals( 0.0, this.angleNul.getDegresDecimaux(), 0.001 );
		assertEquals( -45, this.angleNegatif.getDegresDecimaux(), 0.001 );
		assertEquals( 33.33, this.anglePositif.getDegresDecimaux(), 0.001 );
		assertEquals( 10.0, this.angleEntier.getDegresDecimaux(), 0.001 );
		assertEquals( 10.1, this.angleArrondiInf.getDegresDecimaux(), 0.001 );
		assertEquals( 10.9, this.angleArrondiSup.getDegresDecimaux(), 0.001 );
	}

	@Test
	public void testGetDegres(){
		assertEquals( 0, this.angleNul.getDegres() );
		assertEquals( -45, this.angleNegatif.getDegres() );
		assertEquals( 33, this.anglePositif.getDegres() );
		assertEquals( 10, this.angleEntier.getDegres() );
		assertEquals( 10, this.angleArrondiInf.getDegres() );
		assertEquals( 10, this.angleArrondiSup.getDegres() );
	}
}
