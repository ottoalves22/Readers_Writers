package ep;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        LeitorTxt leitor_txt = new LeitorTxt();
        String[] texto = leitor_txt.lerBd();
        for (int i=0; i<100; i++){
            System.out.println(texto[i]);
        }
	// write your code here
    }
}
