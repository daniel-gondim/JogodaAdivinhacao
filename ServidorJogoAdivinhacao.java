import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Random;

public class ServidorJogoAdivinhacao {
    public static void main(String[] args) throws Exception {
        
    	// cria um socket e aguarda os pacotes enviados pelo cliente na porta (1234)
        DatagramSocket conexao = new DatagramSocket(12345);
        System.out.println("Aguardando pacotes na porta 12345...");
               
        // cria um número aleatório de 1 a 100 para ser adivinhado pelo usuario
        Random rdm = new Random();
        int numero = rdm.nextInt(100);
        System.out.printf("Numero sorteado: %d",numero);
                
        while(true) { 
            // criar um buffer (array) para armazenar os bytes recebidos (max.: 1024)
            byte[] dados = new byte[1024];
           
            //Criar o pacote de dados
            DatagramPacket pacote = new DatagramPacket(dados, dados.length);
            
            // Receber os dados e armazenar no pacote criado anteriormente:
            conexao.receive(pacote);
            System.out.println("Pacote recebido!");
            
            // Extrair os dados do pacote com o método getData() do pacote
            dados = pacote.getData();
            
             //System.out.println(pacote.getSocketAddress() + " >>> " + new String(dados));
            
            //            
            String dadosConvertidos = new String(dados);
            System.out.println("Dados é" + dadosConvertidos);
            Integer valor = Integer.valueOf(dadosConvertidos.trim());

            String resposta;
            if (valor < numero) {
                resposta = "Mais";
            } else if (valor > numero) {
                resposta = "Menos";
            } else {
                resposta = "Acertou(mizerávi)!";
            }

            byte[] respostaInBytes = resposta.getBytes();

            //System.out.printf("IP do remetente: %d",pacote.getAddress());
            //System.out.printf("Porta do remetente: %d",pacote.getPort());

            DatagramPacket pacoteResposta = new DatagramPacket(respostaInBytes,
            respostaInBytes.length, pacote.getAddress(), pacote.getPort());

            conexao.send(pacoteResposta);
            
        }
    }

}