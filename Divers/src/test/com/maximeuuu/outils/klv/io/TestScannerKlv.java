package com.maximeuuu.outils.klv.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.maximeuuu.outils.klv.objets.KlvEncode;

public class TestScannerKlv {

	private ScannerKlv scannerValide;

	private ScannerKlv scannerSansFormat;
	private ScannerKlv scannerSansInput;

	private ScannerKlv scannerFormatCleInvalide;
	private ScannerKlv scannerFormatLongueurInvalide;
	private ScannerKlv scannerLongueurValeurInvalide;

	@Before
	public void setUp(){
		final byte[] klvData = new byte[]{0x0F, 0x04, 0x00, 0x0D, 0x08, 0x05}; //15 - 2 - 0D85

		this.scannerValide = new ScannerKlv( new ByteArrayInputStream(klvData) );
		this.scannerValide.setExpectedKeySize(1);
		this.scannerValide.setExpectedLengthSize(1);

		this.scannerSansFormat = new ScannerKlv( new ByteArrayInputStream(klvData) );

		this.scannerSansInput = new ScannerKlv(null);
		this.scannerSansInput.setExpectedKeySize(16);
		this.scannerSansInput.setExpectedLengthSize(1);

		this.scannerFormatCleInvalide = new ScannerKlv( new ByteArrayInputStream(klvData) );
		this.scannerFormatCleInvalide.setExpectedKeySize(16);
		this.scannerFormatCleInvalide.setExpectedLengthSize(1);

		this.scannerFormatLongueurInvalide = new ScannerKlv( new ByteArrayInputStream(klvData) );
		this.scannerFormatLongueurInvalide.setExpectedKeySize(3);
		this.scannerFormatLongueurInvalide.setExpectedLengthSize(16);

		this.scannerLongueurValeurInvalide = new ScannerKlv( new ByteArrayInputStream(klvData) );
		this.scannerLongueurValeurInvalide.setExpectedKeySize(3);
		this.scannerLongueurValeurInvalide.setExpectedLengthSize(1);
	}

	@Test
	public void testHasNext(){
		assertTrue( this.scannerValide.hasNext() );
		assertTrue( this.scannerSansFormat.hasNext() );
		assertFalse( this.scannerSansInput.hasNext() );
		assertFalse( this.scannerFormatCleInvalide.hasNext() );
		assertFalse( this.scannerFormatLongueurInvalide.hasNext() );
		assertTrue( this.scannerLongueurValeurInvalide.hasNext() );
	}

	@Test
	public void testNext(){
		KlvEncode klv = new KlvEncode();
		klv.setKey( new byte[]{0x0F} );
		klv.setLength(4);
		klv.setValue( new byte[]{0x00, 0x0D, 0x08, 0x05} );

		try{
			assertEquals(klv, this.scannerValide.next());
		}
		catch( Exception e){
			fail("Ne doit pas lever d'exception : " + e.getMessage());
		}

		try{
			assertEquals(new KlvEncode(), this.scannerSansFormat.next());
		}
		catch( Exception e){
			fail("Ne doit pas lever d'exception : " + e.getMessage());
		}

		try{
			assertEquals(new KlvEncode(), this.scannerSansInput.next());
		}
		catch( Exception e){
			fail("Ne doit pas lever d'exception : " + e.getMessage());
		}

		try{
			this.scannerFormatCleInvalide.next();
			fail("Doit lever une exception");
		}
		catch( Exception e){
			assertEquals( new IOException("Impossible de lire la clé complète").getMessage(), e.getMessage() );
		}

		try{
			this.scannerFormatLongueurInvalide.next();
			fail("Doit lever une exception");
		}
		catch( Exception e){
			assertEquals( new IOException("Impossible de lire le champ de longueur complet").getMessage(), e.getMessage() );
		}

		try{
			this.scannerLongueurValeurInvalide.next();
			fail("Doit lever une exception");
		}
		catch( Exception e){
			assertEquals( new IOException("Impossible de lire la valeur complète").getMessage(), e.getMessage() );
		}
		
		assertFalse( this.scannerValide.hasNext() );
		assertTrue( this.scannerSansFormat.hasNext() );
		assertFalse( this.scannerSansInput.hasNext() );
		assertFalse( this.scannerFormatCleInvalide.hasNext() );
		assertFalse( this.scannerFormatLongueurInvalide.hasNext() );
		assertFalse( this.scannerLongueurValeurInvalide.hasNext() );
	}
}
