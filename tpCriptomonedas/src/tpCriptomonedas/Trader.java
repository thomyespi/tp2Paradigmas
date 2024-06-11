package tpCriptomonedas;

public class Trader extends Usuario {

	private String numeroCuentaBancaria;
	private String nombreBanco;
	private double saldoActual;

	public Trader(String nombre, String numeroCuentaBancaria, String nombreBanco, double saldoActual) {
		super(nombre);
		this.numeroCuentaBancaria = numeroCuentaBancaria;
		this.nombreBanco = nombreBanco;
		this.saldoActual = saldoActual;
	}

	public String getNumeroCuentaBancaria() {
		return numeroCuentaBancaria;
	}

	public void setNumeroCuentaBancaria(String numeroCuentaBancaria) {
		this.numeroCuentaBancaria = numeroCuentaBancaria;
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(double nuevoSaldo) {
		if (nuevoSaldo >= 0) {
			this.saldoActual = nuevoSaldo;
		} else {
			throw new IllegalArgumentException("El saldo no puede ser negativo");
		}
	}

	@Override
	public String toString() {
		return "Trader{" + "nombre='" + getNombre() + '\'' + ", numeroCuentaBancaria='" + numeroCuentaBancaria + '\''
				+ ", nombreBanco='" + nombreBanco + '\'' + ", saldoActual=" + saldoActual + '}';
	}
}
