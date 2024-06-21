package tpCriptomonedas;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class ArchivoTest {

	@Test
	public void testLeerArchivoCriptomonedas() throws IOException {
		List<Criptomoneda> criptomonedas = Archivo.leerArchivoCriptomonedas();

		for (Criptomoneda cripto : criptomonedas) {
			System.out.println(cripto);
		}
	}


	@Test
	public void testLeerArchivoMercados() throws IOException {
		List<Criptomoneda> criptomonedas = Archivo.leerArchivoCriptomonedas();
		List<Mercado> mercados = Archivo.leerArchivoMercados(criptomonedas);

		for (Mercado mercado : mercados) {
			System.out.println(mercado);
		}
	}
	
	@Test
	public void testLeerArchivoUsuarios() throws IOException {
		List<Usuario> usuarios = Archivo.leerArchivoUsuarios();

		for (Usuario user : usuarios) {
			System.out.println(user);
		}
	}

}
