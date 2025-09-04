package com.maximeuuu.outils.klv.objets;

import java.util.Arrays;

public class KlvEncode {
	private byte[] key;
	private int    length;
	private byte[] value;

	public KlvEncode( byte[] key, int length, byte[] value){
		this.key    = key;
		this.length = length;
		this.value  = value;
	}

	public KlvEncode(){
		this(new byte[0], 0, new byte[0]);
	}

	/*------------------------------------------------------------*/
	/*                         Accesseurs                         */
	/*------------------------------------------------------------*/

	public byte[] getKey()    { return key;    }
	public int    getLength() { return length; }
	public byte[] getValue()  { return value;  }

	/*------------------------------------------------------------*/
	/*                         Modifieurs                         */
	/*------------------------------------------------------------*/

	public void setKey   (byte[] key)   { this.key    = key;    }
	public void setLength(int length)   { this.length = length; }
	public void setValue (byte[] value) { this.value  = value;  }

	/*------------------------------------------------------------*/
	/*                          Testeurs                          */
	/*------------------------------------------------------------*/

	public boolean isValid(){
		return this.value.length == this.length;
	}

	/*------------------------------------------------------------*/
	/*                          Méthodes                          */
	/*------------------------------------------------------------*/

	@Override
	public boolean equals( Object o ){
		boolean attributsEgaux = false;
		if( o instanceof KlvEncode ) {
			KlvEncode klv = (KlvEncode)o;
			attributsEgaux = true;
			attributsEgaux &= Arrays.equals( this.getKey(), klv.getKey() );
			attributsEgaux &= this.length == klv.getLength();
			attributsEgaux &= Arrays.equals( this.getValue(), klv.getValue() );
		}
		return attributsEgaux;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Key: ").append(Arrays.toString( this.key )).append(", ");
		sb.append("Length: ").append(this.length).append(", ");
		sb.append("Value: ").append(Arrays.toString( this.value ));
		return sb.toString();
	}
}
