package ep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LeitorTxt{

    public String[] lerBd() throws IOException{
        FileReader nomeArquivo;
        String[] texto = new String[36242];
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
        return texto;
	}
}