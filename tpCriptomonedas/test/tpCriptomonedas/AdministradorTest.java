package tpCriptomonedas;

import org.junit.Assert;
import org.junit.Test;

public class AdministradorTest {

	@Test
	public void testObtenerPerfil() {
		
		Administrador admin = new Administrador ("Juan","Administrador");
		
		String obtenido = admin.getPerfil();
		
		String esperado = "Administrador";
		
		Assert.assertEquals(esperado,obtenido);
	}
	
	@Test
	public void testSetearPerfil() {
		
		Administrador admin = new Administrador ("Juan","Administrador");
		
		admin.setPerfil("prueba");
		
		String obtenido = admin.getPerfil();
		String esperado = "prueba";
		
		Assert.assertEquals(esperado,obtenido);
	}
	
	@Test
	public void testSetearNombre() {
		
		Administrador admin = new Administrador ("Juan","Administrador");
		
		admin.setNombre("Thomas");
		
		String obtenido = admin.getNombre();
		String esperado = "Thomas";
		
		Assert.assertEquals(esperado,obtenido);
	}
	
	
	@Test
	public void testImprimir() {
		
		Administrador admin = new Administrador ("Juan","Administrador");
		
		System.out.println(admin);
	}

	
}
