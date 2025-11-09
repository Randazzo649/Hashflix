package ra.catalogo.estruturas;

import java.io.Serializable;
import ra.catalogo.models.Filme;

public class TabelaHashFilme implements Serializable {

    private Filme[] tabela;
    private boolean[] ocupado;
    private int tamanho;
    private int quantidade;
    private static final double FATOR_CARGA = 0.75;

    public TabelaHashFilme(int tamanhoInicial) {
        this.tamanho = tamanhoInicial;
        this.tabela = new Filme[tamanhoInicial];
        this.ocupado = new boolean[tamanhoInicial];
        this.quantidade = 0;
    }

    private int hash(String titulo) {
        int h = titulo.toLowerCase().hashCode();
        if (h == Integer.MIN_VALUE) h = 0;
        return Math.abs(h % tamanho);
    }

    public void inserir(Filme filme) {
        if ((double) quantidade / tamanho >= FATOR_CARGA) {
            rehash();
        }
        int indice = hash(filme.getTitulo());
        inserirRec(filme, indice, 0);
    }

    private void inserirRec(Filme filme, int indice, int tentativas) {
        if (tentativas >= tamanho) {
            rehash();
            inserir(filme);
            return;
        }
        int novoIndice = (indice + tentativas) % tamanho;
        if (tabela[novoIndice] == null) {
            tabela[novoIndice] = filme;
            ocupado[novoIndice] = true;
            quantidade++;
            return;
        }
        if (tabela[novoIndice].getTitulo().equalsIgnoreCase(filme.getTitulo())) {
            tabela[novoIndice] = filme;
            return;
        }
        inserirRec(filme, indice, tentativas + 1);
    }

    public Filme buscar(String titulo) {
        int indice = hash(titulo);
        return buscarRec(titulo, indice, 0);
    }

    private Filme buscarRec(String titulo, int indice, int tentativa) {
        if (tentativa >= tamanho) return null;
        int pos = (indice + tentativa) % tamanho;
        if (!ocupado[pos]) return null;
        Filme f = tabela[pos];
        if (f != null && f.getTitulo().equalsIgnoreCase(titulo)) return f;
        return buscarRec(titulo, indice, tentativa + 1);
    }

    public boolean remover(String titulo) {
        int indice = hash(titulo);
        return removerRec(titulo, indice, 0);
    }

    private boolean removerRec(String titulo, int indice, int tentativa) {
        if (tentativa >= tamanho) return false;
        int pos = (indice + tentativa) % tamanho;
        if (!ocupado[pos]) return false;
        if (tabela[pos] != null && tabela[pos].getTitulo().equalsIgnoreCase(titulo)) {
            tabela[pos] = null;
            quantidade--;
            return true;
        }
        return removerRec(titulo, indice, tentativa + 1);
    }

    private void rehash() {
        int novoTamanho = tamanho * 2;
        Filme[] antiga = tabela;
        tabela = new Filme[novoTamanho];
        ocupado = new boolean[novoTamanho];
        tamanho = novoTamanho;
        quantidade = 0;
        for (Filme f : antiga) {
            if (f != null) inserir(f);
        }
    }

    public Filme[] getTabela() {
        return tabela;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getTamanho() {
        return tamanho;
    }
}
