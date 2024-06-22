package tpCriptomonedas;

import org.junit.Assert;
import org.junit.Test;

public class CriptomonedasTest {

    @Test
    public void testGetNombre() {
        Criptomoneda cripto = new Criptomoneda("Bitcoin", "BTC", 35000.0);
        
        String obtenido = cripto.getNombre();
        String esperado = "Bitcoin";
        
        Assert.assertEquals(esperado, obtenido);
    }

    @Test
    public void testGetSimbolo() {
        Criptomoneda cripto = new Criptomoneda("Bitcoin", "BTC", 35000.0);
        
        String obtenido = cripto.getSimbolo();
        String esperado = "BTC";
        
        Assert.assertEquals(esperado, obtenido);
    }

    @Test
    public void testGetPrecioDolar() {
        Criptomoneda cripto = new Criptomoneda("Bitcoin", "BTC", 35000.0);
        
        double obtenido = cripto.getPrecioDolar();
        double esperado = 35000.0;
        
        Assert.assertEquals(esperado, obtenido, 0.001);
    }

    @Test
    public void testGetCompras() {
        Criptomoneda cripto = new Criptomoneda("Bitcoin", "BTC", 35000.0);
        
        int obtenido = cripto.getCompras();
        int esperado = 998;
        
        Assert.assertEquals(esperado, obtenido);
    }

    @Test
    public void testSetNombre() {
        Criptomoneda cripto = new Criptomoneda("Bitcoin", "BTC", 35000.0);
        
        cripto.setNombre("Ethereum");
        
        String obtenido = cripto.getNombre();
        String esperado = "Ethereum";
        
        Assert.assertEquals(esperado, obtenido);
    }

    @Test
    public void testSetSimbolo() {
        Criptomoneda cripto = new Criptomoneda("Bitcoin", "BTC", 35000.0);
        
        cripto.setSimbolo("ETH");
        
        String obtenido = cripto.getSimbolo();
        String esperado = "ETH";
        
        Assert.assertEquals(esperado, obtenido);
    }

    @Test
    public void testSetPrecioDolar() {
        Criptomoneda cripto = new Criptomoneda("Bitcoin", "BTC", 35000.0);
        
        cripto.setPrecioDolar(2500.0);
        
        double obtenido = cripto.getPrecioDolar();
        double esperado = 2500.0;
        
        Assert.assertEquals(esperado, obtenido, 0.001);
    }

    @Test
    public void testAumentarCompra() {
        Criptomoneda cripto = new Criptomoneda("Bitcoin", "BTC", 35000.0);
        
        cripto.aumentarCompra();
        
        int obtenido = cripto.getCompras();
        int esperado = 999;
        
        Assert.assertEquals(esperado, obtenido);
    }

    @Test
    public void testToString() {
        Criptomoneda cripto = new Criptomoneda("Bitcoin", "BTC", 35000.0);
        
        String obtenido = cripto.toString();
        String esperado = "\n[nombre= Bitcoin\nsimbolo= BTC\nprecioDolar= 35000.0]\n";
        
        Assert.assertEquals(esperado, obtenido);
    }
}
