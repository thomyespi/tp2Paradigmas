package tpCriptomonedas;

public class Mercado {

	private Criptomoneda cripto;
	private String capacidad;
	private String volumen24h;
	private String variacion7d;

	public Mercado(Criptomoneda cripto, String capacidad, String volumen24h, String variacion7d) {
		this.cripto = cripto;
		this.capacidad = capacidad;
		this.volumen24h = volumen24h;
		this.variacion7d = variacion7d;
	}

	@Override
	public String toString() {
		return "\n" + "[simbolo=" + cripto.getSimbolo() + ", capacidad=" + capacidad + ", volumen24h=" + volumen24h
				+ ", variacion7d=" + variacion7d + "]" + "\n";
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

	public String getVariacion7d() {
		return variacion7d;
	}

	public void setSimboloCripto(String simbolo) {
		this.cripto.setSimbolo(simbolo);
	}

	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}

	public void setVolumen24h(String vol) {
		this.volumen24h = vol;
	}

	public void setVariacion7d(String var) {
		this.variacion7d = var;
	}

	public void modificarVolumenVariacionCapacidadCompra(double monto) {
		this.volumen24h = aumentarVolumen(this.volumen24h);
		this.variacion7d = aumentarVariacion(this.variacion7d);
		restarCapacidad(monto);
	}

	public void modificarVolumenVariacionCapacidadVenta(double monto) {
		this.volumen24h = disminuirVolumen(this.volumen24h);
		this.variacion7d = disminuirVariacion(this.variacion7d);
		aumentarCapacidad(monto);
	}

	private String aumentarVolumen(String volumen) {
		double valor = 0;
		if (volumen.endsWith("B")) {
			valor = Double.parseDouble(volumen.replace("B", "")) * 1_000_000_000;
		} else if (volumen.endsWith("M")) {
			valor = Double.parseDouble(volumen.replace("M", "")) * 1_000_000;
		}

		valor *= 1.05;

		if (valor >= 1_000_000_000) {
			return String.format("%.2fB", valor / 1_000_000_000);
		} else {
			return String.format("%.2fM", valor / 1_000_000);
		}
	}

	private String disminuirVolumen(String volumen) {
		double valor = 0;
		if (volumen.endsWith("B")) {
			valor = Double.parseDouble(volumen.replace("B", "")) * 1_000_000_000;
		} else if (volumen.endsWith("M")) {
			valor = Double.parseDouble(volumen.replace("M", "")) * 1_000_000;
		}

		valor *= 0.93;

		if (valor >= 1_000_000_000) {
			return String.format("%.2fB", valor / 1_000_000_000);
		} else {
			return String.format("%.2fM", valor / 1_000_000);
		}
	}

	private String aumentarVariacion(String variacion) {
		double valor = Double.parseDouble(variacion.replace("%", ""));
		valor *= 1.05;
		return String.format("%.2f%%", valor);
	}

	private String disminuirVariacion(String variacion) {
		double valor = Double.parseDouble(variacion.replace("%", ""));
		valor *= 0.93;
		return String.format("%.2f%%", valor);
	}

	private void restarCapacidad(double monto) {

		double valor = Double.parseDouble(this.capacidad);
		valor = valor - monto;
		this.capacidad = String.valueOf(valor);
	}

	private void aumentarCapacidad(double monto) {

		double valor = Double.parseDouble(this.capacidad);
		valor = valor + monto;
		this.capacidad = String.valueOf(valor);
	}

}
