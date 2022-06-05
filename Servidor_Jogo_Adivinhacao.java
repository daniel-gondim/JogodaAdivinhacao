import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Random;

public class Servidor_Jogo_Adivinhacao {
    public static void main(String[] args) throws Exception {
        
    	// cria um socket e aguarda os pacotes enviados pelo cliente na porta (1234)
        DatagramSocket conexao = new DatagramSocket(12345);
        System.out.println("Aguardando pacotes na porta 12345...");
               
        // cria um número aleatório de 1 a 100 para ser adivinhado pelo usuario
        Random rdm = new Random();
        int numero = rdm.nextInt(100);
                
        while(true) { 
            // criar um buffer (array) para armazenar os bytes recebidos (max.: 1024)
            byte[] dados = new byte[1024];
           
            //Criar o pacote de dados
            DatagramPacket pacote = new DatagramPacket(dados, dados.length);
            
            //Receber os dados e armazenar no pacote criado anteriormente:
            conexao.receive(pacote);
            System.out.println("Pacote recebido!");
            
            //Extrair os dados do pacote com o método getData() do pacote
            dados = pacote.getData();
            
            //Exibir a mensagem na tela
            System.out.println(pacote.getSocketAddress() + " >>> " + new String(dados));
            
            
        }
    }

}