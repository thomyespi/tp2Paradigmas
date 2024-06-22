package tpCriptomonedas;

import org.junit.Assert;
import org.junit.Test;


public class TransaccionTest {

	@Test
	public void testGetSimbolo() {
		
		Transaccion trx = new Transaccion ("BTC",800);
		
		String obtenido = trx.getSimbolo();
		
		String esperado = "BTC";
		
		Assert.assertEquals(esperado,obtenido);
		
	}
	
	@Test
	public void testGetCantidad() {
		
		Transaccion trx = new Transaccion ("BTC",800);
		
		double obtenido = trx.getCantidad();
		
		double esperado = 800;
		
		Assert.assertEquals(esperado,obtenido,0.01);
		
	}
	
	@Test
	public void testsetCantidad() {
		
		Transaccion trx = new Transaccion ("BTC",800);
		
		trx.setCantidad(500);
		
		double obtenido = trx.getCantidad();
		
		double esperado = 500;
		
		Assert.assertEquals(esperado,obtenido,0.01);
		
	}
	
	@Test
	public void testsetSimbolo() {
		
		Transaccion trx = new Transaccion ("BTC",800);
		
		trx.setSimbolo("ADA");
		
		String obtenido = trx.getSimbolo();
		
		String esperado = "ADA";
		
		Assert.assertEquals(esperado,obtenido);
		
	}
	
	@Test
	public void testImprimir() {
		
		Transaccion trx = new Transaccion ("BTC",800);
		
		System.out.println(trx);
		
	}

}
