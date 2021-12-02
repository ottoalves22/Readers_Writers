package ep;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class BD{
    String[] texto = new String[36242];
    String[] backup = new String[36242];

    public void lerBd() throws IOException{
        //armazena o texto no atributo texto
        FileReader nomeArquivo;
		for(int i = 1; i < 36242; i++){

            nomeArquivo = new FileReader("EP2/bd.txt");

            BufferedReader buffer = new BufferedReader(nomeArquivo);
            int contador = 0;
            String aux;
            while ((aux = buffer.readLine()) != null) {
                texto[contador] = aux;
                backup[contador] = aux;
                contador++;
            }
            buffer.close();
		}
    }

    public void acessosAleatoriosReader(){
        Random rand = new Random();
        for(int i=0; i<100; i++){
            int aux = rand.nextInt(36242);
            String read_line = this.texto[aux];
        }
    }

    public void acessosAleatoriosWriter(){
        Random rand = new Random();
        for(int i=0; i<100; i++){
            int aux = rand.nextInt(36242);
            this.texto[aux] = "MODIFICADO";
        }
    }

    public void resetText() {
        this.texto = backup;
    }
}