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
    
    
    public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	@Override
    public String toString() {
        return simbolo + "|" + cantidad;
    }
}
