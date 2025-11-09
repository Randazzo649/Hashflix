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
    }

    public static void insertionsortPorDuracao(ObservableList<Filme> filmes){
        System.out.println("Realizando InsertionSort por duração");
    }

    public static void insertionsortPorTitulo(ObservableList<Filme> filmes){
        System.out.println("Realizando InsertionSort por título");
    }

    public static void quicksortPorAno(ObservableList<Filme> filmes){
        System.out.println("Realizando QuickSort por ano");
    }

    public static void quicksortPorDuracao(ObservableList<Filme> filmes){
        System.out.println("Realizando QuickSort por duração");
    }

    public static void quicksortPorTitulo(ObservableList<Filme> filmes){
        System.out.println("Realizando QuickSort por título");
    }
}
