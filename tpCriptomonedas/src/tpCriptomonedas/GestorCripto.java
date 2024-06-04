package tpCriptomonedas;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GestorCripto {

	private List<Criptomoneda> criptomonedas;
	private List<Mercado> mercados;

	public GestorCripto() {

		try {
			this.criptomonedas = Archivo.leerArchivoCriptomonedas();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			this.mercados = Archivo.leerArchivoMercados(this.criptomonedas);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized boolean agregarCriptomoneda(Criptomoneda nuevaCripto) throws IOException {

		if (buscarCriptomoneda(nuevaCripto.getSimbolo())) {
			throw new IllegalArgumentException(
					"La criptomoneda con el símbolo " + nuevaCripto.getSimbolo() + " ya existe.");
		}

		this.criptomonedas.add(nuevaCripto);
		Mercado nuevoMercado = new Mercado(nuevaCripto, "0", "0", "0", "0", "0");
		this.mercados.add(nuevoMercado);

		actualizarArchivoCriptomonedas();
		actualizarArchivoMercados();
		return true;
	}

	public synchronized boolean modificarNombreCriptomoneda(String simbolo, String nuevoNombre) throws IOException {
		if (!buscarCriptomoneda(simbolo)) {
			throw new IllegalArgumentException("La criptomoneda con el símbolo " + simbolo + " NO existe.");
		}

		for (Criptomoneda cripto : this.criptomonedas) {
			if (cripto.getSimbolo().equals(simbolo)) {
				cripto.setNombre(nuevoNombre);
				actualizarArchivoCriptomonedas();
				return true;
			}
		}
		return false;
	}

	public synchronized boolean modificarPrecioCriptomoneda(String simbolo, double nuevoPrecio) throws IOException {
		if (!buscarCriptomoneda(simbolo)) {
			throw new IllegalArgumentException("La criptomoneda con el símbolo " + simbolo + " NO existe.");
		}

		for (Criptomoneda cripto : this.criptomonedas) {
			if (cripto.getSimbolo().equals(simbolo)) {
				cripto.setPrecioDolar(nuevoPrecio);
				actualizarArchivoCriptomonedas();
				return true;
			}
		}
		return false;
	}

	public synchronized boolean eliminarCriptomoneda(String simboloCriptoAEliminar) throws IOException {
		if (!buscarCriptomoneda(simboloCriptoAEliminar)) {
			throw new IllegalArgumentException(
					"La criptomoneda con el símbolo " + simboloCriptoAEliminar + " NO existe.");
		}

		criptomonedas.removeIf(cripto -> cripto.getSimbolo().equals(simboloCriptoAEliminar));

		mercados.removeIf(mercado -> mercado.getCripto().getSimbolo().equals(simboloCriptoAEliminar));

		actualizarArchivoCriptomonedas();
		actualizarArchivoMercados();
		return true;
	}

	public synchronized boolean buscarCriptomoneda(String simbolo) {
		for (Criptomoneda cripto : this.criptomonedas) {
			if (cripto.getSimbolo().equals(simbolo)) {
				return true;
			}
		}
		return false;
	}

	private synchronized void actualizarArchivoCriptomonedas() throws IOException {
		FileWriter fileWriter = new FileWriter("archivos/in/Criptomonedas.in");

		PrintWriter printWriter = new PrintWriter(fileWriter);

		printWriter.println("Nombre" + "|" + "Simbolo" + "|" + "PrecioDolar");

		for (Criptomoneda cripto : this.criptomonedas) {
			printWriter.println(cripto.getNombre() + "|" + cripto.getSimbolo() + "|" + cripto.getPrecioDolar());
		}

		printWriter.close();
	}

	private synchronized void actualizarArchivoMercados() throws IOException {
		FileWriter fileWriter = new FileWriter("archivos/in/Mercados.in");

		PrintWriter printWriter = new PrintWriter(fileWriter);

		printWriter.println("Simbolo" + "|" + "Capacidad" + "|" + "Volumen24h" + "|" + "VolumenTotal" + "|"
				+ "Variacion24h" + "|" + "Variacion7d");

		for (Mercado mercado : this.mercados) {
			printWriter.println(mercado.getCripto().getSimbolo() + "|" + mercado.getCapacidad() + "|"
					+ mercado.getVolumen24h() + "|" + mercado.getVolumenTotal() + "|" + mercado.getVariacion24h() + "|"
					+ mercado.getVariacion7d());
		}

		printWriter.close();
	}

	public List<Criptomoneda> getCriptomonedas() {
		return criptomonedas;
	}

	public List<Mercado> getMercados() {
		return mercados;
	}

}
