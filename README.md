<h2>Projeto de Comunicação Segura com Criptografia RSA</h2>

Este repositório contém uma implementação básica de um cliente e servidor utilizando sockets Java e criptografia RSA. O objetivo é demonstrar como estabelecer uma comunicação segura entre um cliente e um servidor, garantindo a confidencialidade das mensagens através da criptografia.

<h2>Funcionalidades</h2>
<h3>Geração de Chaves RSA:</h3> O servidor gera um par de chaves (pública e privada) para criptografar e decifrar mensagens.
<h3>Troca de Chaves:</h3> O cliente e o servidor trocam suas chaves públicas para permitir a criptografia mútua.
<h3>Criptografia de Mensagens:</h3> As mensagens enviadas pelo cliente são criptografadas com a chave pública do servidor antes de serem transmitidas.
<h3>Decifragem de Mensagens:</h3> O servidor decifra as mensagens recebidas usando sua chave privada e vice-versa.


<h2>Estrutura do Projeto</h2>
<h3>Client:</h3> Classe responsável por iniciar o cliente, estabelecer a conexão com o servidor, enviar mensagens criptografadas e receber respostas.
<h3>Server:</h3> Classe que representa o servidor, que aguarda conexões, recebe mensagens e responde ao cliente.
<h3>Conexao:</h3> Classe auxiliar para facilitar a comunicação entre cliente e servidor.
<h3>CriptografiaClienteServidor:</h3> Classe que contém métodos para gerar chaves e realizar a criptografia e decifragem de mensagens.
<h3>RSA_EC:</h3> Exemplo adicional de implementação de criptografia RSA com números primos.

<h2>Como Usar</h2>
<p>Compile o projeto usando um IDE de Java ou ferramentas de linha de comando.</p>
<p>Inicie o servidor (Server).</p>
<p>Em outra instância, inicie o cliente (Client).</p>
<p>Siga as instruções no terminal para enviar e receber mensagens.</p>
