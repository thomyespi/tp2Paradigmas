package tpCriptomonedas;

import java.io.IOException;

import org.junit.Test;

public class GestorCriptoTest {

	@Test
	public void agregarCripto() {

		GestorCripto gestor1 = new GestorCripto();

		System.out.println(gestor1.getCriptomonedas());

		Criptomoneda cripto1 = new Criptomoneda("Shiba Inu", "SHIB", 0.00002818);

		try {
			gestor1.agregarCriptomoneda(cripto1);
			;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
