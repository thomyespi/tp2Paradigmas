package tpCriptomonedas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Trader extends Usuario {

	private String numeroCuentaBancaria;
	private String nombreBanco;
	private double saldoActual;
	private List<Transaccion> historico;

	public Trader(String nombre, String numeroCuentaBancaria, String nombreBanco, double saldoActual) {
		super(nombre);
		this.numeroCuentaBancaria = numeroCuentaBancaria;
		this.nombreBanco = nombreBanco;
		this.saldoActual = saldoActual;

		List<Transaccion> aux = new ArrayList<>();

		String archivoHistorico = "archivos/out/" + nombre + "_historico.out";

		File archivo = new File(archivoHistorico);
		if (archivo.exists() && archivo.isFile()) {
			try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
				String linea;
				while ((linea = br.readLine()) != null) {
					if (esValida(linea)) {
						String[] partes = linea.split("\\|");
						String simbolo = partes[0];
						double cantidad = Double.parseDouble(partes[1]);
						Transaccion trx = new Transaccion(simbolo.toUpperCase(), cantidad);
						aux.add(trx);
					}
				}
				this.setHistorico(aux);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.setHistorico(new ArrayList<>());
		}

	}

	private boolean esValida(String linea) {

		return linea != null && !linea.trim().isEmpty();
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

	public boolean puedeComprar(double monto) {
		return saldoActual >= monto;
	}

	public void restarSaldo(double monto) {
		if (puedeComprar(monto)) {
			setSaldoActual(saldoActual - monto);
		} else {
			throw new IllegalArgumentException("Saldo insuficiente");
		}
	}

	public void sumarSaldo(double monto) {
		this.saldoActual += monto;
	}

	public double getCantidadCripto(String simbolo) {
		for (Transaccion cripto : getHistorico()) {
			if (cripto.getSimbolo().equals(simbolo)) {
				return cripto.getCantidad();
			}
		}
		return 0;
	}

	public boolean buscarSimboloHistorico(String simbolo) {

		for (Transaccion trx : getHistorico()) {
			if (trx.getSimbolo().equals(simbolo)) {
				return true;
			}
		}
		return false;
	}

	public void actualizarHistorico(String simbolo, double cantidad, String type) {

		for (Transaccion trx : getHistorico()) {

			if (trx.getSimbolo().equals(simbolo)) {

				if (type.equals("compra")) {
					double cant = trx.getCantidad() + cantidad;
					trx.setCantidad(cant);
				} else {
					double cant = trx.getCantidad() - cantidad;
					trx.setCantidad(cant);
				}

				try {
					actualizarArchivoSalida(this.getNombre());
				} catch (IOException e) {
					System.out.println("Error al actualizar el archivo");
					e.printStackTrace();
				}
				return;
			}

		}
		Transaccion tran = new Transaccion(simbolo, cantidad);
		getHistorico().add(tran);

		try {
			actualizarArchivoSalida(this.getNombre());
		} catch (IOException e) {
			System.out.println("Error al actualizar el archivo");
			e.printStackTrace();
		}
	}

	private void actualizarArchivoSalida(String usuario) throws IOException {

		String archivoHistorico = "archivos/out/" + usuario + "_historico.out";

		File archivo = new File(archivoHistorico);

		if (archivo.exists()) {
			archivo.delete();
		}

		FileWriter fileWriter = new FileWriter(archivoHistorico, true);
		PrintWriter printWriter = new PrintWriter(fileWriter);

		for (Transaccion trx : getHistorico()) {
			printWriter.println(trx.getSimbolo() + "|" + trx.getCantidad());
		}
		printWriter.close();

	}

	public void mostrarHistorico(int ordenamiento) {
		if (ordenamiento == 1) {
			getHistorico().sort(Comparator.comparing(Transaccion::getSimbolo));
		} else if (ordenamiento == 2) {
			getHistorico().sort(Comparator.comparing(Transaccion::getCantidad).reversed());
		} else {
			System.out.println("Opción no válida.");
			return;
		}

		for (Transaccion transaccion : getHistorico()) {
			System.out.println(transaccion);
		}
	}

	@Override
	public String toString() {
		return "Trader{" + "nombre='" + getNombre() + '\'' + ", numeroCuentaBancaria='" + numeroCuentaBancaria + '\''
				+ ", nombreBanco='" + nombreBanco + '\'' + ", saldoActual=" + saldoActual + '}';
	}

	public List<Transaccion> getHistorico() {
		return historico;
	}

	public void setHistorico(List<Transaccion> historico) {
		this.historico = historico;
	}

}