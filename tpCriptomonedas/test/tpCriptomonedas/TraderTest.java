package tpCriptomonedas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TraderTest {

    private Trader trader;

    @Before
    public void inicio() {
       
        String archivoHistorico = "archivos/out/testTrader_historico.out";
        File archivo = new File(archivoHistorico);
        archivo.getParentFile().mkdirs();
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            writer.println("BTC|1.0");
            writer.println("ETH|2.0");
        } catch (IOException e) {
            e.printStackTrace();
        }

        trader = new Trader("testTrader", "123456", "TestBank", 1000.0);
    }

    @Test
    public void testGetNumeroCuentaBancaria() {
        Assert.assertEquals("123456", trader.getNumeroCuentaBancaria());
    }

    @Test
    public void testSetNumeroCuentaBancaria() {
        trader.setNumeroCuentaBancaria("654321");
        Assert.assertEquals("654321", trader.getNumeroCuentaBancaria());
    }

    @Test
    public void testGetNombreBanco() {
        Assert.assertEquals("TestBank", trader.getNombreBanco());
    }

    @Test
    public void testSetNombreBanco() {
        trader.setNombreBanco("NewBank");
        Assert.assertEquals("NewBank", trader.getNombreBanco());
    }

    @Test
    public void testGetSaldoActual() {
        Assert.assertEquals(1000.0, trader.getSaldoActual(), 0.001);
    }

    @Test
    public void testSetSaldoActual() {
        trader.setSaldoActual(2000.0);
        Assert.assertEquals(2000.0, trader.getSaldoActual(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetSaldoActualNegativo() {
        trader.setSaldoActual(-100.0);
    }

    @Test
    public void testPuedeComprar() {
        Assert.assertTrue(trader.puedeComprar(500.0));
        Assert.assertFalse(trader.puedeComprar(1500.0));
    }

    @Test
    public void testRestarSaldo() {
        trader.restarSaldo(500.0);
        Assert.assertEquals(500.0, trader.getSaldoActual(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRestarSaldoInsuficiente() {
        trader.restarSaldo(1500.0);
    }

    @Test
    public void testSumarSaldo() {
        trader.sumarSaldo(500.0);
        Assert.assertEquals(1500.0, trader.getSaldoActual(), 0.001);
    }

    @Test
    public void testGetCantidadCripto() {
        Assert.assertEquals(1.0, trader.getCantidadCripto("BTC"), 0.001);
        Assert.assertEquals(2.0, trader.getCantidadCripto("ETH"), 0.001);
        Assert.assertEquals(0.0, trader.getCantidadCripto("LTC"), 0.001);
    }

    @Test
    public void testBuscarSimboloHistorico() {
        Assert.assertTrue(trader.buscarSimboloHistorico("BTC"));
        Assert.assertTrue(trader.buscarSimboloHistorico("ETH"));
        Assert.assertFalse(trader.buscarSimboloHistorico("LTC"));
    }

    @Test
    public void testActualizarHistoricoCompra() {
        trader.actualizarHistorico("BTC", 1.0, "compra");
        Assert.assertEquals(2.0, trader.getCantidadCripto("BTC"), 0.001);
    }

    @Test
    public void testActualizarHistoricoVenta() {
        trader.actualizarHistorico("ETH", 1.0, "venta");
        Assert.assertEquals(1.0, trader.getCantidadCripto("ETH"), 0.001);
    }

    @Test
    public void testToString() {
        String esperado = "Trader{nombre='testTrader', numeroCuentaBancaria='123456', nombreBanco='TestBank', saldoActual=1000.0}";
        Assert.assertEquals(esperado, trader.toString());
    }

    @Test
    public void testMostrarHistoricoOrdenadoPorSimbolo() {
        trader.mostrarHistorico(1);
        List<Transaccion> historico = trader.getHistorico();
        Assert.assertEquals("BTC", historico.get(0).getSimbolo());
        Assert.assertEquals("ETH", historico.get(1).getSimbolo());
    }

    @Test
    public void testMostrarHistoricoOrdenadoPorCantidad() {
        trader.mostrarHistorico(2);
        List<Transaccion> historico = trader.getHistorico();
        Assert.assertEquals("ETH", historico.get(0).getSimbolo());
        Assert.assertEquals("BTC", historico.get(1).getSimbolo());
    }
}
