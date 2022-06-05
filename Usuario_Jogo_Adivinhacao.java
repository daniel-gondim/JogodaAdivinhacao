import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;

public class Usuario_Jogo_Adivinhacao {
    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);
        // Criar o socket UDP usando uma porta arbitrária definida pelo SO
        DatagramSocket conexao = new DatagramSocket();
        
        // Definir o endereço de destino e a porta de destino do pacote:
        InetAddress ipDestino = InetAddress.getByName("172.16.221.50");
        int porta = 12345;
        
        
        while (true) {
            //Definir a mensagem a ser enviada:
        	System.out.print("Por favor, insira um número de 1 a 100:");
            String mensagem = teclado.nextLine();
            byte[] dados = mensagem.getBytes();
            //Criar o pacote a ser enviado
            DatagramPacket pacote  = new DatagramPacket(dados, dados.length, ipDestino, porta);
            //Enviar o pacote de dados
            conexao.send(pacote); 
            
        }
        
    }
}