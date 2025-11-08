package ra.catalogo.estruturas;

import java.util.LinkedList;
import ra.catalogo.models.Filme;

public class TabelaHashFilmes {
    private LinkedList<Filme>[] tabela;
    private int tamanho;

    @SuppressWarnings("unchecked")
    public TabelaHashFilmes(int capacidade) {
        tabela = new LinkedList[capacidade];
        tamanho = capacidade;
    }

    private int hash(String chave) {
        return Math.abs(chave.toLowerCase().hashCode()) % tamanho;
    }

    // inserção
    public void inserir(Filme filme) {
        int indice = hash(filme.getTitulo());
        if (tabela[indice] == null) {
            tabela[indice] = new LinkedList<>();
        }

        // evita "clones"
        for (Filme f : tabela[indice]) {
            if (f.equals(filme)) return;
        }

        tabela[indice].add(filme);
    }

    // remoção
    public boolean remover(String titulo) {
        int indice = hash(titulo);
        if (tabela[indice] != null) {
            return tabela[indice].removeIf(f -> f.getTitulo().equalsIgnoreCase(titulo));
        }
        return false;
    }

    // busca
    public Filme buscarPorNome(String nome) {
        int indice = hash(nome);
        if (tabela[indice] != null) {
            for (Filme f : tabela[indice]) {
                if (f.getTitulo().equalsIgnoreCase(nome)) {
                    return f;
                }
            }
        }
        return null;
    }

    // lista todos no console
    public void listarTodos() {
        for (int i = 0; i < tamanho; i++) {
            if (tabela[i] != null) {
                for (Filme f : tabela[i]) {
                    System.out.println(f);
                }
            }
        }
    }
}
