package ep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BD{
    String[] texto = new String[36242];

    public void lerBd() throws IOException{
        //armazena o texto no atributo texto
        FileReader nomeArquivo;
		for(int i = 1; i < 36243; i++){

            nomeArquivo = new FileReader("EP2/bd.txt");

            BufferedReader buffer = new BufferedReader(nomeArquivo);
            int contador = 0;
            String aux;
            while ((aux = buffer.readLine()) != null) {
                texto[contador] = aux;
                contador++;
            }
            buffer.close();
		}
    }

    public void acessosAleatoriosReader(){

    }

    public void acessosAleatoriosWriter(){

    }

}