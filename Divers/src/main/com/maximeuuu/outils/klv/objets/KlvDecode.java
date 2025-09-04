package com.maximeuuu.outils.klv.objets;

public class KlvDecode {
	private int    key;
	//private int    length
	private double value;

	public KlvDecode( int key, double value ){
		this.key    = key;
		this.value  = value;
	}

	public KlvDecode(){
		this(0, 0);
	}

	/*------------------------------------------------------------*/
	/*                         Accesseurs                         */
	/*------------------------------------------------------------*/

	public int    getKey()    { return key;    }
	public double getValue()  { return value;  }

	/*------------------------------------------------------------*/
	/*                         Modifieurs                         */
	/*------------------------------------------------------------*/

	public void setKey   (int key)      { this.key    = key;    }
	public void setValue (double value) { this.value  = value;  }

	/*------------------------------------------------------------*/
	/*                          Méthodes                          */
	/*------------------------------------------------------------*/

	@Override
	public boolean equals( Object o ){
		boolean attributsEgaux = false;
		if( o instanceof KlvDecode ) {
			KlvDecode klv = (KlvDecode)o;
			attributsEgaux = true;
			attributsEgaux &= this.getKey() == klv.getKey();
			attributsEgaux &= this.getValue() == klv.getValue();
		}
		return attributsEgaux;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Key: ").append(this.key).append(", ");
		sb.append("Value: ").append(this.value);
		return sb.toString();
	}
}
