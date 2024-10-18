package br.com.fiap.criptografia;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.*;

public class CriptografiaClienteServidorComRSA {

    private static BigInteger p = new BigInteger("83");
    private static BigInteger q = new BigInteger("47");
    private static BigInteger n = p.multiply(q);
    private static BigInteger e = new BigInteger("3");
    private static BigInteger d = e.modInverse((p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)));

    public static void gerarChavesPublicoPrivada(){
       
        System.out.println("p:" + p);
        System.out.println("q:" + q);
        System.out.println("n:" + n);
        System.out.println("e:" + e);
        System.out.println("d:" + d);
    }

    public static String cifrar(String mensagem) throws Exception {
        byte[] msgBytes = mensagem.getBytes(StandardCharsets.UTF_8);
        StringBuilder cifradaStringBuilder = new StringBuilder();
        
        for (byte b : msgBytes) {
            BigInteger msgBigInt = new BigInteger(1, new byte[]{b});
            BigInteger cifrada = msgBigInt.modPow(e, n);
            cifradaStringBuilder.append(cifrada.toString()).append(" ");
        }
        
        return cifradaStringBuilder.toString().trim();
    }

    public static String decifrar(String mensagem) throws Exception {
        String[] cifradaParts = mensagem.split(" ");
        StringBuilder decifradaStringBuilder = new StringBuilder();
        
        for (String part : cifradaParts) {
            try {
                BigInteger cifradaBigInt = new BigInteger(part);
                BigInteger decifrada = cifradaBigInt.modPow(d, n);
                
                byte[] byteArray = decifrada.toByteArray();
                if (byteArray.length > 0) {
                    decifradaStringBuilder.append((char) byteArray[byteArray.length - 1]);
                }
            } catch (NumberFormatException e) {
                System.err.println("Erro ao decifrar parte da mensagem: " + part);
            }
        }
        
        return decifradaStringBuilder.toString();
    }

    public static PublicKey bytesParaChave(byte[] bytesChave) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytesChave);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }
}
