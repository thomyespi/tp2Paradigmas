package tpCriptomonedas;

import java.io.IOException;

public class App {
	public static void main(String[] args) {
		try {

			GestorSistema sistema = new GestorSistema();
			sistema.iniciarSesion();
			System.out.println("\nEl programa finalizó correctamente. Gracias por participar.");

		} catch (IOException e) {
			System.err.println("Ocurrió un error de entrada/salida: " + e.getMessage());

		} catch (Exception e) {
			System.err.println("Ocurrió un error inesperado: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
