package tpCriptomonedas;

import org.junit.Assert;
import org.junit.Test;

public class MercadoTest {

    @Test
    public void testGetCripto() {
        Criptomoneda cripto = new Criptomoneda("Bitcoin", "BTC", 35000.0);
        Mercado mercado = new Mercado(cripto, "1000", "1.00B", "5%");
        
        Criptomoneda obtenido = mercado.getCripto();
        Assert.assertEquals(cripto, obtenido);
    }

    @Test
    public void testGetCapacidad() {
        Criptomoneda cripto = new Criptomoneda("Bitcoin", "BTC", 35000.0);
        Mercado mercado = new Mercado(cripto, "1000", "1.00B", "5%");
        
        String obtenido = mercado.getCapacidad();
        String esperado = "1000";
        
        Assert.assertEquals(esperado, obtenido);
    }

    @Test
    public void testGetVolumen24h() {
        Criptomoneda cripto = new Criptomoneda("Bitcoin", "BTC", 35000.0);
        Mercado mercado = new Mercado(cripto, "1000", "1.00B", "5%");
        
        String obtenido = mercado.getVolumen24h();
        String esperado = "1.00B";
        
        Assert.assertEquals(esperado, obtenido);
    }

    @Test
    public void testGetVariacion7d() {
        Criptomoneda cripto = new Criptomoneda("Bitcoin", "BTC", 35000.0);
        Mercado mercado = new Mercado(cripto, "1000", "1.00B", "5%");
        
        String obtenido = mercado.getVariacion7d();
        String esperado = "5%";
        
        Assert.assertEquals(esperado, obtenido);
    }

    @Test
    public void testSetSimboloCripto() {
        Criptomoneda cripto = new Criptomoneda("Bitcoin", "BTC", 35000.0);
        Mercado mercado = new Mercado(cripto, "1000", "1.00B", "5%");
        
        mercado.setSimboloCripto("ETH");
        
        String obtenido = mercado.getCripto().getSimbolo();
        String esperado = "ETH";
        
        Assert.assertEquals(esperado, obtenido);
    }

    @Test
    public void testSetCapacidad() {
        Criptomoneda cripto = new Criptomoneda("Bitcoin", "BTC", 35000.0);
        Mercado mercado = new Mercado(cripto, "1000", "1.00B", "5%");
        
        mercado.setCapacidad("2000");
        
        String obtenido = mercado.getCapacidad();
        String esperado = "2000";
        
        Assert.assertEquals(esperado, obtenido);
    }

    @Test
    public void testSetVolumen24h() {
        Criptomoneda cripto = new Criptomoneda("Bitcoin", "BTC", 35000.0);
        Mercado mercado = new Mercado(cripto, "1000", "1.00B", "5%");
        
        mercado.setVolumen24h("2.00B");
        
        String obtenido = mercado.getVolumen24h();
        String esperado = "2.00B";
        
        Assert.assertEquals(esperado, obtenido);
    }

    @Test
    public void testSetVariacion7d() {
        Criptomoneda cripto = new Criptomoneda("Bitcoin", "BTC", 35000.0);
        Mercado mercado = new Mercado(cripto, "1000", "1.00B", "5%");
        
        mercado.setVariacion7d("10%");
        
        String obtenido = mercado.getVariacion7d();
        String esperado = "10%";
        
        Assert.assertEquals(esperado, obtenido);
    }

    @Test
    public void testModificarVolumenVariacionCapacidadCompra() {
        Criptomoneda cripto = new Criptomoneda("Bitcoin", "BTC", 35000.0);
        Mercado mercado = new Mercado(cripto, "1000", "1.00B", "5%");
        
        mercado.modificarVolumenVariacionCapacidadCompra(100);
        
        String obtenidoVolumen = mercado.getVolumen24h();
        String esperadoVolumen = "1.05B";
        
        String obtenidoVariacion = mercado.getVariacion7d();
        String esperadoVariacion = "5.25%"; 
        
        String obtenidoCapacidad = mercado.getCapacidad();
        String esperadoCapacidad = "900.0";
        
        Assert.assertEquals(esperadoVolumen, obtenidoVolumen);
        Assert.assertEquals(esperadoVariacion, obtenidoVariacion);
        Assert.assertEquals(esperadoCapacidad, obtenidoCapacidad);
    }

    @Test
    public void testModificarVolumenVariacionCapacidadVenta() {
        Criptomoneda cripto = new Criptomoneda("Bitcoin", "BTC", 35000.0);
        Mercado mercado = new Mercado(cripto, "1000", "1.00B", "5%");
        
        mercado.modificarVolumenVariacionCapacidadVenta(100);
        
        String obtenidoVolumen = mercado.getVolumen24h();
        String esperadoVolumen = "930.00M";
        
        String obtenidoVariacion = mercado.getVariacion7d();
        String esperadoVariacion = "4.65%";
        
        String obtenidoCapacidad = mercado.getCapacidad();
        String esperadoCapacidad = "1100.0";
        
        Assert.assertEquals(esperadoVolumen, obtenidoVolumen);
        Assert.assertEquals(esperadoVariacion, obtenidoVariacion);
        Assert.assertEquals(esperadoCapacidad, obtenidoCapacidad);
    }

    @Test
    public void testToString() {
        Criptomoneda cripto = new Criptomoneda("Bitcoin", "BTC", 35000.0);
        Mercado mercado = new Mercado(cripto, "1000", "1.00B", "5%");
        
        String obtenido = mercado.toString();
        String esperado = "\n[simbolo=BTC, capacidad=1000, volumen24h=1.00B, variacion7d=5%]\n";
        
        Assert.assertEquals(esperado, obtenido);
    }
}
