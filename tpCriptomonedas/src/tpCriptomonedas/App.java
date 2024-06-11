package tpCriptomonedas;

import java.io.IOException;
import java.util.List;

public class App {
	public static void main(String[] args) {
//
//		GestorSistema sistema = new GestorSistema();
//
//		sistema.mostrarMenu();
//
//		System.out.println("\nEl programa finalizo bien. Gracias por participar");
//	}

	try
	{
           List<Usuario> usuarios;
           
           usuarios = Archivo.leerArchivoUsuarios();
           
           for (Usuario usuario : usuarios) {
               System.out.println(usuario);
           }
       }catch(
	IOException e)
	{
		e.printStackTrace();
	}
}
}
