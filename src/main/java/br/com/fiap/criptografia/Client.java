package br.com.fiap.criptografia;

import java.net.Socket;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Scanner;

public class Client {
    Socket socket;

    public void comunicarComServidor() throws Exception {
        String textoRequisicao = "Conexao estabelecida";
        String textoRecebido = "";
        String textoDecifrado = "";
        String textoCifrado = "";

        socket = new Socket("localhost", 9600);


        Scanner input = new Scanner(System.in);
     
        CriptografiaClienteServidorComRSA.gerarChavesPublicoPrivada();
        
        System.out.print("\nDigite a sua mensagem: ");
        textoRequisicao = input.nextLine();

        textoCifrado = CriptografiaClienteServidorComRSA.cifrar(textoRequisicao);

        System.out.println("Mensagem criptografada enviada: " + textoCifrado);
        Conexao.enviar(socket, textoCifrado);

        textoRecebido = Conexao.receber(socket);

        textoDecifrado = CriptografiaClienteServidorComRSA.decifrar(textoRecebido);

        System.out.println("\nMensagem criptografada recebida: " + textoRecebido);
        System.out.println("Servidor enviou: " + textoDecifrado);
    }


    public static void main(String[] args) {
        try {
            Client cliente = new Client();
            cliente.comunicarComServidor();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}

