package bancario.util;

import java.io.FileWriter;
import java.io.IOException;

public class ArquivoTexto {
    public static void escrever(String nomeArquivo, String conteudo) {
        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            writer.write(conteudo);
        } catch (IOException e) {
            System.out.println("Erro ao escrever arquivo: " + e.getMessage());
        }
    }
}
