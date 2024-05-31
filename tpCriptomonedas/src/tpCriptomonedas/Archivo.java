package tpCriptomonedas;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Archivo {

	private static File file;
	private static Scanner scanner = null;

	public static List<Criptomoneda> leerArchivoCriptomonedas() throws IOException {

		List<Criptomoneda> criptos = new ArrayList<Criptomoneda>();

		try {
			file = new File("archivos/in/Criptomonedas.in");

			scanner = new Scanner(file);

			scanner.useLocale(Locale.ENGLISH);

			String linea = scanner.nextLine();

			while (scanner.hasNextLine()) {
				linea = scanner.nextLine();
				String[] partes = linea.split("\\|");

				String nombre = partes[0];
				String simbolo = partes[1];
				double precioDolar = Double.parseDouble(partes[2]);

				Criptomoneda datoLeido = new Criptomoneda(nombre, simbolo, precioDolar);

				criptos.add(datoLeido);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		return criptos;
	}

	public static List<Mercado> leerArchivoMercados(List<Criptomoneda> criptos) throws IOException {

		List<Mercado> mercado = new ArrayList<Mercado>();

		try {
			file = new File("archivos/in/Mercados.in");

			scanner = new Scanner(file);

			scanner.useLocale(Locale.ENGLISH);

			String linea = scanner.nextLine();

			while (scanner.hasNextLine()) {
				linea = scanner.nextLine();
				String[] partes = linea.split("\\|");

				String simbolo = partes[0];
				String capacidad = partes[1];
				String vol24h = partes[2];
				String volTotal = partes[3];
				String var24h = partes[4];
				String var7d = partes[5];

				for (Criptomoneda cripto : criptos) {
					if (cripto.getSimbolo().equals(simbolo)) {
						Mercado datoLeido = new Mercado(cripto, capacidad, vol24h, volTotal, var24h, var7d);
						mercado.add(datoLeido);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		return mercado;
	}

}
