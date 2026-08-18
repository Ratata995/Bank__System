package bancario.util;

import bancario.model.Cliente;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ArquivoBinario {
    public static Map<String, Cliente> clientes = new HashMap<>();
    public static int proximoNumeroConta = 100001;

    private static final String ARQUIVO = "dados_bancarios.dat";

    public static void salvarDados() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            out.writeObject(clientes);
            out.writeInt(proximoNumeroConta);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static void carregarDados() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) return;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            clientes = (Map<String, Cliente>) in.readObject();
            proximoNumeroConta = in.readInt();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Não foi possível carregar os dados. Um novo banco será iniciado.");
            clientes = new HashMap<>();
            proximoNumeroConta = 100001;
        }
    }
}
