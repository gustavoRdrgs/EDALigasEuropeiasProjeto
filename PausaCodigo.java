package ProjetoGAP;

import java.util.concurrent.TimeUnit;

/* classe criada para métodos de pausa no código para que todos os arquivos não sejam simplesmente todos
criados de uma vez*/
public class PausaCodigo {

    // método para pausa de três segundos quando o arquivo estiver sendo criado
    public static void pausarCodigo3Sgnds() {

        try {

            PausaCodigo.pausarCodigo1Sgnd(); // delay de transição para o próximo arquivo
            System.out.printf("Criando arquivo ");
            for (int i = 0; i < 3; i++) {
                System.out.printf(". ");
                pausarCodigo1Sgnd();
            }
            System.out.println();
            
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }
    
    // método criado para pausa de um segundo para transição de um arquivo para outro
    public static void pausarCodigo1Sgnd() {

        TimeUnit timeU = TimeUnit.SECONDS;

        try {
            
            timeU.sleep(1);

        } catch (InterruptedException e) {
            System.out.println("Erro: " + e);
        }
    }
}