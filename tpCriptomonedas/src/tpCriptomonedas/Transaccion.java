package tpCriptomonedas;

public class Transaccion {
    private String simbolo;
    private double cantidad;

    public Transaccion(String simbolo, double cantidad) {
        this.simbolo = simbolo;
        this.cantidad = cantidad;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public double getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return simbolo + "|" + cantidad;
    }
}
