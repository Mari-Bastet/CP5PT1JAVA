package br.com.fiap.criptografia;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Scanner;

public class Server {
	Socket socketClient;
	ServerSocket serversocket;

	public boolean connect() {
		try {
			socketClient = serversocket.accept();
			return true;
		} catch (IOException e) {
			System.err.println("Não fez conexão" + e.getMessage());
			return false;
		}
	}

	public static void main(String[] args) {
		try {
			Server servidor = new Server();
			servidor.rodarServidor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void rodarServidor() throws Exception {
		String textoRecebido = "";
		String textoEnviado = "Olá Cliente";
		String textoDecifrado;
		String textoCifrado;

		Scanner input = new Scanner(System.in);
		serversocket = new ServerSocket(9600);
		System.out.println("Servidor iniciado!");

		while (true) {
			if (connect()) {

				textoRecebido = Conexao.receber(socketClient);
				System.out.println("\nMensagem criptografada recebida: " + textoRecebido);
				textoDecifrado = CriptografiaClienteServidorComRSA.decifrar(textoRecebido);

				System.out.println("Cliente enviou: "+ textoDecifrado);
				System.out.print("\nDigite a sua mensagem: ");
				textoEnviado = input.nextLine();

				textoCifrado = CriptografiaClienteServidorComRSA.cifrar(textoEnviado);

				System.out.println("Mensagem criptografada enviada: " + textoCifrado);
				Conexao.enviar(socketClient, textoCifrado);

				socketClient.close();
			}
		}
	}
}
