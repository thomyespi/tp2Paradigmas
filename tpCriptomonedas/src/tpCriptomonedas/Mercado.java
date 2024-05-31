package tpCriptomonedas;

public class Mercado {

	private Criptomoneda cripto;
	private String capacidad;
	private String volumen24h;
	private String volumenTotal;
	private String variacion24h;
	private String variacion7d;

	public Mercado(Criptomoneda cripto, String capacidad, String volumen24h, String volumenTotal, String variacion24h,
			String variacion7d) {
		this.cripto = cripto;
		this.capacidad = capacidad;
		this.volumen24h = volumen24h;
		this.volumenTotal = volumenTotal;
		this.variacion24h = variacion24h;
		this.variacion7d = variacion7d;
	}

	@Override
	public String toString() {
		return "\n" + "[simbolo=" + cripto.getSimbolo() + ", capacidad=" + capacidad + ", volumen24h=" + volumen24h
				+ ", volumenTotal=" + volumenTotal + ", variacion24h=" + variacion24h + ", variacion7d=" + variacion7d
				+ "]" + "\n";
	}

	public Criptomoneda getCripto() {
		return cripto;
	}

	public String getCapacidad() {
		return capacidad;
	}

	public String getVolumen24h() {
		return volumen24h;
	}

	public String getVolumenTotal() {
		return volumenTotal;
	}

	public String getVariacion24h() {
		return variacion24h;
	}

	public String getVariacion7d() {
		return variacion7d;
	}

	public void setCripto(Criptomoneda cripto) {
		this.cripto = cripto;
	}

}
