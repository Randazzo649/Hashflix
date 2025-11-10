package ra.catalogo.estruturas;

import java.util.Collections;

import javafx.collections.ObservableList;
import ra.catalogo.models.Filme;

public class Vetor {
    
    public static void ordenar (ObservableList<Filme> filmes, String opcoes){
        if (opcoes == null || opcoes.isBlank()) {
            System.out.println("Nenhuma opção selecionada.");
            return;
        }

        opcoes = opcoes.toLowerCase(); // garantir consistência

        if (opcoes.contains("bubblesort")) {
            if (opcoes.contains("ano")) bubblesortPorAno(filmes, filmes.size());
            else if (opcoes.contains("duracao")) bubblesortPorDuracao(filmes, filmes.size());
            else if (opcoes.contains("titulo")) bubblesortPorTitulo(filmes, filmes.size());
        } 
        else if (opcoes.contains("insertionsort")) {
            if (opcoes.contains("ano")) insertionsortPorAno(filmes);
            else if (opcoes.contains("duracao")) insertionsortPorDuracao(filmes);
            else if (opcoes.contains("titulo")) insertionsortPorTitulo(filmes);
        } 
        else if (opcoes.contains("quicksort")) {
            if (opcoes.contains("ano")) quicksortPorAno(filmes);
            else if (opcoes.contains("duracao")) quicksortPorDuracao(filmes);
            else if (opcoes.contains("titulo")) quicksortPorTitulo(filmes);
        } 
        else {
            System.out.println("Método de ordenação inválido.");
        }
    }

    public static void bubblesortPorAno(ObservableList<Filme> filmes, int tamanho){
        if (tamanho == 1) return;

        // uma "passada" do BubbleSort: empurra o maior pro final
        for (int i = 0; i < tamanho - 1; i++) {
            if (filmes.get(i).getAnoAsInt() > filmes.get(i + 1).getAnoAsInt()) {
                Collections.swap(filmes, i, i + 1);
            }
        }

        // chamada recursiva ignorando o último elemento
        bubblesortPorAno(filmes, tamanho - 1);
    }

    public static void bubblesortPorDuracao(ObservableList<Filme> filmes, int tamanho){
        if (tamanho == 1) return;

        // uma "passada" do BubbleSort: empurra o maior pro final
        for (int i = 0; i < tamanho - 1; i++) {
            if (filmes.get(i).getDuracaoAsInt() > filmes.get(i + 1).getDuracaoAsInt()) {
                Collections.swap(filmes, i, i + 1);
            }
        }

        // chamada recursiva ignorando o último elemento
        bubblesortPorDuracao(filmes, tamanho - 1);
    }

    public static void bubblesortPorTitulo(ObservableList<Filme> filmes, int tamanho){
        if (tamanho == 1) return;

        // uma "passada" do BubbleSort: empurra o maior pro final
        for (int i = 0; i < tamanho - 1; i++) {
            if (filmes.get(i).getTitulo().compareTo( filmes.get(i + 1).getTitulo() ) > 0) {
                Collections.swap(filmes, i, i + 1);
            }
        }

        // chamada recursiva ignorando o último elemento
        bubblesortPorTitulo(filmes, tamanho - 1);
    }

    public static void insertionsortPorAno(ObservableList<Filme> filmes){
        System.out.println("Realizando InsertionSort por ano");
        ordenarPorAno(filmes, filmes.size());
    }

    private static void ordenarPorAno(ObservableList<Filme> filmes, int n){
        if(n <= 1) return;
        ordenarPorAno(filmes, n - 1);
        Filme ultimo = filmes.get(n - 1);
        inserirPorAno(filmes, n - 2, ultimo);
    }
    private static void inserirPorAno(ObservableList<Filme> filmes, int j, Filme ultimo){
        if(j < 0 || filmes.get(j).getAnoAsInt() <= ultimo.getAnoAsInt()){
            filmes.set(j + 1, ultimo);
            return;
        }
        filmes.set(j + 1, filmes.get(j));
        inserirPorAno(filmes, j - 1, ultimo);
    }

    public static void insertionsortPorDuracao(ObservableList<Filme> filmes){
        System.out.println("Realizando InsertionSort por duração");
        ordenarPorDuracao(filmes, filmes.size());
    }

    private static void ordenarPorDuracao(ObservableList<Filme> filmes, int n){
        if(n <= 1) return;
        ordenarPorDuracao(filmes, n - 1);
        Filme ultimo = filmes.get(n - 1);
        inserirPorDuracao(filmes, n - 2, ultimo);
    }
    private static void inserirPorDuracao(ObservableList<Filme> filmes, int j, Filme ultimo){
        if(j < 0 || filmes.get(j).getDuracaoAsInt() <= ultimo.getDuracaoAsInt()){
            filmes.set(j + 1, ultimo);
            return;
        }
        filmes.set(j + 1, filmes.get(j));
        inserirPorDuracao(filmes, j - 1, ultimo);
    }

    public static void insertionsortPorTitulo(ObservableList<Filme> filmes){
        System.out.println("Realizando InsertionSort por título");
        ordenarPorTitulo(filmes, filmes.size());
    }

    private static void ordenarPorTitulo(ObservableList<Filme> filmes, int n){
        if(n <= 1) return;
        ordenarPorTitulo(filmes, n - 1);
        Filme ultimo = filmes.get(n - 1);
        inserirPorTitulo(filmes, n - 2, ultimo);
    }
    private static void inserirPorTitulo(ObservableList<Filme> filmes, int j, Filme ultimo){
        if(j < 0 || filmes.get(j).getTitulo().compareToIgnoreCase(ultimo.getTitulo()) <= 0){
            filmes.set(j + 1, ultimo);
            return;
        }
        filmes.set(j + 1, filmes.get(j));
        inserirPorTitulo(filmes, j - 1, ultimo);
    }

    public static void quicksortPorAno(ObservableList<Filme> filmes){
        System.out.println("Realizando QuickSort por ano");
        quicksortAnoRec(filmes, 0, filmes.size() - 1);
    }

    private static void quicksortAnoRec(ObservableList<Filme> filmes, int inicio, int fim){
        if (inicio < fim) {
            int pivoIndex = particionarPorAno(filmes, inicio, fim);
            quicksortAnoRec(filmes, inicio, pivoIndex - 1);
            quicksortAnoRec(filmes, pivoIndex + 1, fim);
        }
    }

    private static int particionarPorAno(ObservableList<Filme> filmes, int inicio, int fim){
        int pivo = filmes.get(fim).getAnoAsInt();
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (filmes.get(j).getAnoAsInt() <= pivo) {
                i++;
                Collections.swap(filmes, i, j);
            }
        }
        Collections.swap(filmes, i + 1, fim);
        return i + 1;
    }

    public static void quicksortPorDuracao(ObservableList<Filme> filmes){
        System.out.println("Realizando QuickSort por duração");
        quicksortDuracaoRec(filmes, 0, filmes.size() - 1);
    }

    private static void quicksortDuracaoRec(ObservableList<Filme> filmes, int inicio, int fim){
        if (inicio < fim) {
            int pivoIndex = particionarPorDuracao(filmes, inicio, fim);
            quicksortDuracaoRec(filmes, inicio, pivoIndex - 1);
            quicksortDuracaoRec(filmes, pivoIndex + 1, fim);
        }
    }

    private static int particionarPorDuracao(ObservableList<Filme> filmes, int inicio, int fim){
        int pivo = filmes.get(fim).getDuracaoAsInt();
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (filmes.get(j).getDuracaoAsInt() <= pivo) {
                i++;
                Collections.swap(filmes, i, j);
            }
        }
        Collections.swap(filmes, i + 1, fim);
        return i + 1;
    }

    public static void quicksortPorTitulo(ObservableList<Filme> filmes){
        System.out.println("Realizando QuickSort por título");
        quicksortTituloRec(filmes, 0, filmes.size() - 1);
    }

    private static void quicksortTituloRec(ObservableList<Filme> filmes, int inicio, int fim){
        if (inicio < fim) {
            int pivoIndex = particionarPorTitulo(filmes, inicio, fim);
            quicksortTituloRec(filmes, inicio, pivoIndex - 1);
            quicksortTituloRec(filmes, pivoIndex + 1, fim);
        }
    }

    private static int particionarPorTitulo(ObservableList<Filme> filmes, int inicio, int fim){
        String pivo = filmes.get(fim).getTitulo().toLowerCase();
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (filmes.get(j).getTitulo().toLowerCase().compareTo(pivo) <= 0) {
                i++;
                Collections.swap(filmes, i, j);
            }
        }
        Collections.swap(filmes, i + 1, fim);
        return i + 1;
    }
}
