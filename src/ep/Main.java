package ep;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BD leitor_txt = new BD();
        leitor_txt.lerBd();
        for (int i=0; i<100; i++){
            System.out.println(leitor_txt.texto[i]);
        }
	// write your code here
    }
}
