package tpCriptomonedas;

public class Criptomoneda {

	private String nombre;
	private String simbolo;
	private double precioDolar;

	public Criptomoneda(String nombre, String simbolo, double precioDolar) {
		this.nombre = nombre;
		this.simbolo = simbolo;
		this.precioDolar = precioDolar;
	}

	@Override
	public String toString() {
		return "\n" + "[nombre= " + nombre + "\n" + "simbolo= " + simbolo + "\n" + "precioDolar= " + precioDolar + "]"
				+ "\n";
	}

	public String getNombre() {
		return nombre;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public double getPrecioDolar() {
		return precioDolar;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public void setPrecioDolar(double precioDolar) {
		this.precioDolar = precioDolar;
	}

}
