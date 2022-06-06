import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;

public class UsuarioJogoAdivinhacao {
    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);
        // Criar o socket UDP usando uma porta arbitrária definida pelo sistema operacional
        DatagramSocket conexao = new DatagramSocket();
        
        // Definir o endereço de destino e a porta de destino do pacote:
        InetAddress ipDestino = InetAddress.getByName("127.0.0.1");
        int porta = 12345;
        
        
        boolean running = true;
        
        while (running) {
            //Definir a mensagem a ser enviada:
        	System.out.print("Por favor, insira um número de 1 a 100:");
            String mensagem = teclado.nextLine();
            
            byte[] dados = mensagem.getBytes();
            //Criar o pacote a ser enviado
            DatagramPacket pacote  = new DatagramPacket(dados, dados.length, ipDestino, porta);
            //Enviar o pacote de dados
            conexao.send(pacote); 
            
            //           
            byte[] testeDados = new byte[1024];
            
            //Criar o pacote de dados
            DatagramPacket testePacote = new DatagramPacket(testeDados, testeDados.length);

            conexao.receive(testePacote);
            String valorRecebido = new String(testePacote.getData());
            System.out.println("pacote recebido");
            System.out.println("Dados do pacote: " + new String(testePacote.getData()));

            if(valorRecebido.trim().equals("Acertou(mizerávi)!")) {
                System.out.println("Encerrando");
                running = false;
            }  
        }
        
    }
}