package com.maximeuuu.outils.klv.conversion.misb;

public class Conversion {
	private Format formatKlv;
	private Format formatSoftware;
	
	public Conversion(){
	}

	public void setFormatKlv( Format format ){
		this.formatKlv = format;
	}

	public void setFormatSoftware( Format format ){
		this.formatSoftware = format;
	}

	public Format getFormatKlv(){
		return this.formatKlv;
	}

	public Format getFormatSoftware(){
		return this.formatSoftware;
	}

	public long valeurSofwareToValeurKlv( double valeur ){
		return Math.round(this.getTotalPlageKlv() / this.getTotalPlageSofware() * (valeur - this.formatKlv.getDecalage())); //+ this.formatSoftware.decalage
	}

	public double valeurKlvToValeurSoftware( long valeur ){
		return this.getTotalPlageSofware() / this.getTotalPlageKlv() * valeur + this.formatKlv.getDecalage(); //+ this.formatSoftware.decalage
	}

	private double getTotalPlageSofware(){
		return Math.abs(this.formatSoftware.getMin()) + Math.abs(this.formatSoftware.getMax());
	}

	private double getTotalPlageKlv(){
		return Math.abs(this.formatKlv.getMin()) + Math.abs(this.formatKlv.getMax());
	}

	public static class Format {
	
		private double min;
		private double max;
		private double decalage;
	
		public Format( double min, double max, double decalage ){
			this.min = min;
			this.max = max;
			this.decalage = decalage;
		}
	
		public boolean estSigne(){
			return min < 0;
		}
	
		public double getMin(){
			return this.min;
		}
	
		public double getMax(){
			return this.max;
		}
	
		public double getDecalage(){
			return this.decalage;
		}
	}
}
