package tpCriptomonedas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class GestorUsuarios {

	private List<Usuario> usuarios;
	private Scanner scanner;

	public GestorUsuarios() {

		try {
			this.usuarios = Archivo.leerArchivoUsuarios();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.scanner = new Scanner(System.in);
	}

	public Usuario buscarUsuario(String nombreUsuario) {
		for (Usuario usuario : usuarios) {
			if (usuario.getNombre().equals(nombreUsuario)) {
				return usuario;
			}
		}
		return null;
	}

	public void registrarTrader(String nombreUsuario) throws IOException {

		System.out.println("\nIngrese su numero de cuenta bancaria:");
		String numeroCuentaBancaria = scanner.nextLine();

		System.out.println("\nIngrese el nombre de su banco:");
		String nombreBanco = scanner.nextLine();

		System.out.println("\nIngrese el saldo de su cuenta bancaria:");
		Double saldoCuenta = Double.parseDouble(scanner.nextLine());

		Trader trader = new Trader(nombreUsuario, numeroCuentaBancaria, nombreBanco, saldoCuenta);

		this.usuarios.add(trader);

		actualizarArchivoUsuarios();
	}

	public void actualizarSaldoUsuario(String usuario, double monto) throws IOException {
    	for(Usuario user:this.usuarios) {
    		if(user.getNombre().equals(usuario)) {
    			 if (user instanceof Trader){
    				 Trader trader = (Trader) user; 
    				 trader.restarSaldo(monto);
    				 break;
    			 }
    		}
    	}
        
        actualizarArchivoUsuarios();
    }

	public boolean validarSaldoUsuario(String usuario, double monto) {
		for (Usuario user : this.usuarios) {
			if (user.getNombre().equals(usuario)) {
				if (user instanceof Trader) {
					Trader trader = (Trader) user;
					return trader.puedeComprar(monto);
				} else {
					return false;
				}
			}
		}
		return false;
	}
	
	public void registrarCompra(String usuario, String simbolo, double cantidad) throws IOException {
		String archivoHistorico = "archivos/out/" + usuario + "_historico.out";
		
		FileWriter fileWriter = new FileWriter(archivoHistorico, true);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.println(simbolo + "|" + cantidad);
		printWriter.close();
	}


	public void registrarVenta(String nombreUsuario, String simbolo, double cantidad) throws IOException {
	    for (Usuario user : usuarios) {
	        if (user.getNombre().equals(nombreUsuario)) {
	            if (user instanceof Trader) {
	//                Trader trader = (Trader) user;
//	                trader.restarCantidadCripto(simbolo, cantidad);
	                actualizarArchivoUsuarios();
	                return;
	            }
	        }
	    }
	}
	
	public double obtenerCantidadMaxima(String nombreUsuario, String simbolo) {
	    for (Usuario user : usuarios) {
	        if (user.getNombre().equals(nombreUsuario)) {
	            if (user instanceof Trader) {
	                Trader trader = (Trader) user;
	                return trader.getCantidadCripto(simbolo);
	            }
	        }
	    }
	    return 0;
	}
	
    public void consultarHistorico(String usuario, int ordenamiento) throws IOException {
        String archivoHistorico = "archivos/out/" + usuario + "_historico.out";
        
        List<Transaccion> transacciones = leerArchivoHistorico(archivoHistorico);

        if (ordenamiento == 1) {
            transacciones.sort(Comparator.comparing(Transaccion::getSimbolo));
        } else if (ordenamiento == 2) {
            transacciones.sort(Comparator.comparing(Transaccion::getCantidad).reversed());
        } else {
            System.out.println("Opción no válida.");
            return;
        }

        for (Transaccion transaccion : transacciones) {
            System.out.println(transaccion);
        }
    }

    private List<Transaccion> leerArchivoHistorico(String archivoHistorico) throws IOException {
        List<Transaccion> transacciones = new ArrayList<>();
        File file = new File(archivoHistorico);
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
            scanner.useLocale(Locale.ENGLISH);

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] partes = linea.split("\\|");

                String simbolo = partes[0];
                double cantidad = Double.parseDouble(partes[1]);

                Transaccion transaccion = new Transaccion(simbolo, cantidad);
                transacciones.add(transaccion);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return transacciones;
    }

	private synchronized void actualizarArchivoUsuarios() throws IOException {

		FileWriter fileWriter = new FileWriter("archivos/in/Usuarios.in");
		PrintWriter printWriter = new PrintWriter(fileWriter);

		for (Usuario usuario : usuarios) {
			if (usuario instanceof Trader) {
				Trader trader = (Trader) usuario;
				printWriter.println(trader.getNombre() + "|" + trader.getNumeroCuentaBancaria() + "|"
						+ trader.getNombreBanco() + "|" + trader.getSaldoActual());
			} else if (usuario instanceof Administrador) {
				Administrador administrador = (Administrador) usuario;
				printWriter.println(administrador.getNombre() + "|" + administrador.getPerfil());
			}
		}

		printWriter.close();
	}
}