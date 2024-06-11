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
				String var7d = partes[3];

				for (Criptomoneda cripto : criptos) {
					if (cripto.getSimbolo().equals(simbolo)) {
						Mercado datoLeido = new Mercado(cripto, capacidad, vol24h, var7d);
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

	public static List<Usuario> leerArchivoUsuarios() throws IOException {

		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			file = new File("archivos/in/Usuarios.in");

			scanner = new Scanner(file);

			scanner.useLocale(Locale.ENGLISH);

			String linea = scanner.nextLine();

			while (scanner.hasNextLine()) {
				String[] partes = linea.split("\\|");

				if (partes.length == 2) {

					String nombre = partes[0];
					String perfil = partes[1];

					Administrador admin = new Administrador(nombre, perfil);

					usuarios.add(admin);

				} else if (partes.length == 4) {

					String nombre = partes[0];
					String numeroCuentaBancaria = partes[1];
					String nombreBanco = partes[2];
					double saldoActual = Double.parseDouble(partes[3]);

					Trader trader = new Trader(nombre, numeroCuentaBancaria, nombreBanco, saldoActual);

					usuarios.add(trader);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		return usuarios;
	}

}
