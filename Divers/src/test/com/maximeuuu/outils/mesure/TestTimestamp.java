package com.maximeuuu.outils.mesure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestTimestamp {
	
	private Timestamp debutAnnee2025; //1735689600 = Wed Jan 01 2025 00:00:00 GMT +00:00
	private Timestamp unJourAnnee2025; //1738671633 = Tue Feb 04 2025 12:20:33 GMT +00:00
	private Timestamp debutAnnee1970; //0 = Thu Jan 01 1970 00:00:00 GMT +00:00
	private Timestamp heureModulo60; //générait une erreur si le modulo n'était pas bien placé

	/*
	 * TODO: retrouver pour vérifier l'erreur du %60 secondes
	 * ajouter un avec decimales en microsecondes
	 */

	@Before
	public void setUp(){
		this.debutAnnee2025 = new Timestamp( 1_735_689_600l * 1_000_000 );
		this.unJourAnnee2025 = new Timestamp( 1_738_671_633l * 1_000_000 );
		this.debutAnnee1970 = new Timestamp( 0 * 1_000_000 );
		this.heureModulo60 = new Timestamp(1_730_973_659_508_972l);
	}

	@After
	public void tearDown(){
		this.debutAnnee2025 = null;
		this.unJourAnnee2025 = null;
		this.debutAnnee1970 = null;
		this.heureModulo60 = null;
	}

	@Test
	public void testGetSecondesDecimales(){
		assertEquals(1_735_689_600, this.debutAnnee2025.getSecondesDecimales(),0.0001);
		assertEquals(1_738_671_633, this.unJourAnnee2025.getSecondesDecimales(),0.0001);
		assertEquals(0, this.debutAnnee1970.getSecondesDecimales(),0.0001);
		assertEquals(1_730_973_659.508_972, this.heureModulo60.getSecondesDecimales(),0.0001);
	}

	@Test
	public void testGetHeuresDecimales(){
		assertEquals(482_136, this.debutAnnee2025.getHeuresDecimale(),0.0001);
		assertEquals(482_964.3425, this.unJourAnnee2025.getHeuresDecimale(),0.0001);
		assertEquals(0, this.debutAnnee1970.getHeuresDecimale(),0.0001);
		assertEquals(480826.01653027005, this.heureModulo60.getHeuresDecimale(),0.0001);
	}

	@Test
	public void testToString(){
		assertEquals(this.debutAnnee2025.toStringUTC(), this.debutAnnee2025.toString());
		assertEquals(this.unJourAnnee2025.toStringUTC(), this.unJourAnnee2025.toString());
		assertEquals(this.debutAnnee1970.toStringUTC(), this.debutAnnee1970.toString());
		assertEquals(this.heureModulo60.toStringUTC(), this.heureModulo60.toString());
	}

	@Test
	public void testToMicroseconde(){
		assertTrue( this.debutAnnee2025.toStringMicrosecondes().contains("µs") );
		assertTrue( this.unJourAnnee2025.toStringMicrosecondes().contains("µs") );
		assertTrue( this.debutAnnee1970.toStringMicrosecondes().contains("µs") );
		assertTrue( this.heureModulo60.toStringMicrosecondes().contains("µs") );
	}
	
	@Test
	public void testToStringHeure(){
		assertEquals("000000.000", this.debutAnnee2025.toStringHeure());
		assertEquals("122033.000", this.unJourAnnee2025.toStringHeure());
		assertEquals("000000.000", this.debutAnnee1970.toStringHeure());
		assertEquals("100100.508", this.heureModulo60.toStringHeure());
	}

	@Test
	public void testToStringDate(){
		assertEquals("01/01/25", this.debutAnnee2025.toStringDate());
		assertEquals("04/02/25", this.unJourAnnee2025.toStringDate());
		assertEquals("01/01/70", this.debutAnnee1970.toStringDate());
		assertEquals("07/11/24", this.heureModulo60.toStringDate());
	}

	@Test
	public void testToStringDateHeure(){
		assertEquals("01/01/2025 - 00:00:00", this.debutAnnee2025.toStringDateHeure());
		assertEquals("04/02/2025 - 12:20:33", this.unJourAnnee2025.toStringDateHeure());
		assertEquals("01/01/1970 - 00:00:00", this.debutAnnee1970.toStringDateHeure());
		assertEquals("07/11/2024 - 10:01:00", this.heureModulo60.toStringDateHeure());
	}

	@Test
	public void testToStringUTC(){
		assertEquals("00:00:00.000 | 01/01/25", this.debutAnnee2025.toStringUTC());
		assertEquals("12:20:33.000 | 04/02/25", this.unJourAnnee2025.toStringUTC());
		assertEquals("00:00:00.000 | 01/01/70", this.debutAnnee1970.toStringUTC());
		assertEquals("10:01:00.508 | 07/11/24", this.heureModulo60.toStringUTC());
	}

	@Test
	public void testToStringUnixTime(){
		assertEquals("2025-01-01T00:00:00Z", this.debutAnnee2025.toStringUnixTime());
		assertEquals("2025-02-04T12:20:33Z", this.unJourAnnee2025.toStringUnixTime());
		assertEquals("1970-01-01T00:00:00Z", this.debutAnnee1970.toStringUnixTime());
		assertEquals("2024-11-07T10:01:00.508972Z", this.heureModulo60.toStringUnixTime());
	}
}
