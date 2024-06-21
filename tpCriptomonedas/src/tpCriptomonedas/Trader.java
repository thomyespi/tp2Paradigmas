package tpCriptomonedas;

import java.util.ArrayList;
import java.util.List;

public class Trader extends Usuario {

    private String numeroCuentaBancaria;
    private String nombreBanco;
    private double saldoActual;
    private List<Criptomoneda> historico;

    public Trader(String nombre, String numeroCuentaBancaria, String nombreBanco, double saldoActual) {
        super(nombre);
        this.numeroCuentaBancaria = numeroCuentaBancaria;
        this.nombreBanco = nombreBanco;
        this.saldoActual = saldoActual;
        this.historico = new ArrayList<>();
    }

    public String getNumeroCuentaBancaria() {
        return numeroCuentaBancaria;
    }

    public void setNumeroCuentaBancaria(String numeroCuentaBancaria) {
        this.numeroCuentaBancaria = numeroCuentaBancaria;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double nuevoSaldo) {
        if (nuevoSaldo >= 0) {
            this.saldoActual = nuevoSaldo;
        } else {
            throw new IllegalArgumentException("El saldo no puede ser negativo");
        }
    }

    public boolean puedeComprar(double monto) {
        return saldoActual >= monto;
    }

    public void restarSaldo(double monto) {
        if (puedeComprar(monto)) {
            setSaldoActual(saldoActual - monto);
        } else {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
    }

  
    public double getCantidadCripto(String simbolo) {
        for (Criptomoneda cripto : historico) {
            if (cripto.getSimbolo().equals(simbolo)) {
                return cripto.getCompras();
            }
        }
        return 0;
    }
//
//    public void restarCantidadCripto(String simbolo, double cantidad) {
//        for (Criptomoneda cripto : historico) {
//            if (cripto.getSimbolo().equals(simbolo)) {
//                double nuevaCantidad = cripto.getCompras() - cantidad;
//                cripto.setCompras(nuevaCantidad);
//                return;
//            }
//        }
//    }

    public void agregarCriptomoneda(Criptomoneda cripto) {
        historico.add(cripto);
    }

    @Override
    public String toString() {
        return "Trader{" + "nombre='" + getNombre() + '\'' + ", numeroCuentaBancaria='" + numeroCuentaBancaria + '\''
                + ", nombreBanco='" + nombreBanco + '\'' + ", saldoActual=" + saldoActual + '}';
    }
}