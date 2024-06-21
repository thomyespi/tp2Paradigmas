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

		this.criptomonedas.add(nuevaCripto);
		Mercado nuevoMercado = new Mercado(nuevaCripto, "500", "500", "+1.00%");
		this.mercados.add(nuevoMercado);

		actualizarArchivoCriptomonedas();
		actualizarArchivoMercados();
		return true;
	}

	public synchronized boolean modificarNombreCriptomoneda(String simbolo, String nuevoNombre) throws IOException {

		for (Criptomoneda cripto : this.criptomonedas) {
			if (cripto.getSimbolo().equals(simbolo)) {
				cripto.setNombre(nuevoNombre);
				actualizarArchivoCriptomonedas();
				return true;
			}
		}
		return false;
	}

	public synchronized boolean modificarSimboloCriptomoneda(String simbolo, String nuevoSimbolo) throws IOException {

		for (Criptomoneda cripto : this.criptomonedas) {
			if (cripto.getSimbolo().equals(simbolo)) {
				cripto.setSimbolo(nuevoSimbolo);

				for (Mercado mercado : this.mercados) {
					if (mercado.getCripto().getSimbolo().equals(simbolo)) {
						mercado.setSimboloCripto(nuevoSimbolo);
					}
				}
				actualizarArchivoCriptomonedas();
				actualizarArchivoMercados();
				return true;
			}
		}
		return false;
	}

	public synchronized boolean modificarPrecioCriptomoneda(String simbolo, double nuevoPrecio) throws IOException {

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

	public synchronized void mostrarUnaCripto(String simbolo) {

		for (Criptomoneda cripto : this.criptomonedas) {
			if (cripto.getSimbolo().equals(simbolo)) {

				System.out.println("Criptomoneda: ");
				System.out.println(cripto);

				for (Mercado mercado : this.mercados) {
					if (mercado.getCripto().getSimbolo().equals(simbolo)) {
						System.out.println("Mercado: ");
						System.out.println(mercado);
					}
				}
			}
		}
	}

	public synchronized void comprarCripto(String simbolo, double monto) throws IOException {
		for (Mercado mercado : this.mercados) {
			if (mercado.getCripto().getSimbolo().equals(simbolo)) {

				mercado.modificarVolumenVariacionCapacidad(monto);

				for (Criptomoneda cripto : this.criptomonedas) {
					if (cripto.getSimbolo().equals(simbolo)) {

						if (cripto.getCompras() > 1000) {

							double nuevoPrecio = cripto.getPrecioDolar() * 1.10;
							cripto.setPrecioDolar(nuevoPrecio);
							break;
						}
						break;
					}
				}
				actualizarArchivoCriptomonedas();
				actualizarArchivoMercados();
				return;
			}
		}
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

		printWriter.println("Simbolo" + "|" + "Capacidad" + "|" + "Volumen24h" + "|" + "Variacion7d");

		for (Mercado mercado : this.mercados) {
			printWriter.println(mercado.getCripto().getSimbolo() + "|" + mercado.getCapacidad() + "|"
					+ mercado.getVolumen24h() + "|" + mercado.getVariacion7d());
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
