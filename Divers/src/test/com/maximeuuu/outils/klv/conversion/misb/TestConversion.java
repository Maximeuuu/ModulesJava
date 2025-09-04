package com.maximeuuu.outils.klv.conversion.misb;

import static org.junit.Assert.assertEquals;

import javax.xml.bind.DatatypeConverter;

import org.junit.Before;
import org.junit.Test;

public class TestConversion {
	
	private static final String HEXA_TIMESTAMP = "000459F4A6AA4AA8";
	private static final byte[] DATA_TIMESTAMP = DatatypeConverter.parseHexBinary(HEXA_TIMESTAMP);
	private static final long   KLV_TIMESTAMP  = 1224807209913000l;
	private static final double SOFT_TIMESTAMP = 1224807209913000l;

	private static final String HEXA_LATITUDE  = "F101A229";
	private static final byte[] DATA_LATITUDE  = DatatypeConverter.parseHexBinary(HEXA_LATITUDE);
	private static final long   KLV_LATITUDE   = -251551191l;
	private static final double SOFT_LATITUDE  = -10.542388633146132;

	private static final String HEXA_LONGITUDE = "5B5360C4";
	private static final byte[] DATA_LONGITUDE = DatatypeConverter.parseHexBinary(HEXA_LONGITUDE);
	private static final long   KLV_LONGITUDE  = 1532190916l;
	private static final double SOFT_LONGITUDE = 128.42675904204452;

	private static final String HEXA_ALTITUDE  = "C221";
	private static final byte[] DATA_ALTITUDE  = DatatypeConverter.parseHexBinary(HEXA_ALTITUDE);
	private static final long   KLV_ALTITUDE   = 49697l;
	private static final double SOFT_ALTITUDE  = 14190.7195;

	private Conversion conversionIncomplete;
	private Conversion conversionTimestamp;
	private Conversion conversionLatitude;
	private Conversion conversionLongitude;
	private Conversion conversionAltitude;
	
	@Before
	public void setUp(){
		this.conversionIncomplete = new Conversion();
		this.conversionIncomplete.setFormatKlv(null);
		this.conversionIncomplete.setFormatSoftware(null);

		this.conversionTimestamp = new Conversion();
		this.conversionTimestamp.setFormatKlv( new Conversion.Format(0, 65535, 0) );
		this.conversionTimestamp.setFormatSoftware( new Conversion.Format(0, 65535, 0) );

		this.conversionLatitude = new Conversion();
		this.conversionLatitude.setFormatKlv( new Conversion.Format(-2147483647, 2147483647, 0) );
		this.conversionLatitude.setFormatSoftware( new Conversion.Format(-90, 90, 0) );

		this.conversionLongitude = new Conversion();
		this.conversionLongitude.setFormatKlv( new Conversion.Format(-2147483647, 2147483647, 0) );
		this.conversionLongitude.setFormatSoftware( new Conversion.Format(-180, 180, 0) );

		this.conversionAltitude = new Conversion();
		this.conversionAltitude.setFormatKlv( new Conversion.Format(0, 65535, -900) );
		this.conversionAltitude.setFormatSoftware( new Conversion.Format(-900, 19000, 0) );
	}

	@Test
	public void testSetFormatKlvSoftware(){
		this.conversionIncomplete.setFormatKlv( new Conversion.Format(0, 0, 0));
		this.conversionIncomplete.setFormatSoftware( new Conversion.Format(0, 0, 0));
	}

	@Test
	public void testValeurSoftwareToValeurKlv(){
		assertEquals(KLV_TIMESTAMP, this.conversionTimestamp.valeurSofwareToValeurKlv(SOFT_TIMESTAMP));
		assertEquals(KLV_LATITUDE, this.conversionLatitude.valeurSofwareToValeurKlv(SOFT_LATITUDE));
		assertEquals(KLV_LONGITUDE, this.conversionLongitude.valeurSofwareToValeurKlv(SOFT_LONGITUDE));
		assertEquals(KLV_ALTITUDE, this.conversionAltitude.valeurSofwareToValeurKlv(SOFT_ALTITUDE));
	}


	@Test
	public void testValeurKlvToValeurSoftware(){
		assertEquals(SOFT_TIMESTAMP, this.conversionTimestamp.valeurKlvToValeurSoftware(KLV_TIMESTAMP), 0.0001);
		assertEquals(SOFT_LATITUDE, this.conversionLatitude.valeurKlvToValeurSoftware(KLV_LATITUDE), 0.0001);
		assertEquals(SOFT_LONGITUDE, this.conversionLongitude.valeurKlvToValeurSoftware(KLV_LONGITUDE), 0.0001);
		assertEquals(SOFT_ALTITUDE, this.conversionAltitude.valeurKlvToValeurSoftware(KLV_ALTITUDE), 0.0001);
	}
}
