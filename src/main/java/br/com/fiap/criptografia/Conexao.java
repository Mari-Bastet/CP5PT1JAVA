package br.com.fiap.criptografia;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;

public class Conexao {

    public static String receber(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        byte[] infoBytes = new byte[256];
        int bytesLidos = in.read(infoBytes);

        System.out.println("Bytes lidos: " + bytesLidos);

        if (bytesLidos > 0) {
            return new String(infoBytes, 0, bytesLidos, StandardCharsets.UTF_8);
        } else {
            return "";
        }
    }

    public static PublicKey receberChave(Socket socket) throws Exception {
        InputStream in = socket.getInputStream();
        byte[] infoBytes = new byte[2048];
        int bytesLidos = in.read(infoBytes);

        if (bytesLidos > 0) {
            return CriptografiaClienteServidorComRSA.bytesParaChave(infoBytes);
        } else {
            return null;
        }
    }

    public static void enviarChave(Socket socket, PublicKey chave) throws IOException {
        OutputStream out = socket.getOutputStream();
        out.write(chave.getEncoded());
        out.flush();
    }

    public static void enviar(Socket socket, String textoRequisicao) throws IOException {
        System.out.println("Enviando...");

        byte[] bytesRequisicao = textoRequisicao.getBytes(StandardCharsets.UTF_8);
        System.out.println("Bytes da mensagem: " + bytesRequisicao.length);

        OutputStream out = socket.getOutputStream();
        out.write(bytesRequisicao);
        out.flush();
    }
}
