package tpCriptomonedas;

import java.io.IOException;
import java.util.List;

public class GestorUsuarios {

	private List<Usuario> usuarios;

	public GestorUsuarios() {

		try {
			this.usuarios = Archivo.leerArchivoUsuarios();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Usuario buscarUsuario(String nombreUsuario) {
		for (Usuario usuario : usuarios) {
			if (usuario.getNombre().equals(nombreUsuario)) {
				return usuario;
			}
		}
		return null;
	}

	public void registrarTrader(String nombreUsuario) {
		// Lógica para registrar un nuevo trader
		// ...
	}
}