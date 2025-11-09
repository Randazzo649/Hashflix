package ra.catalogo.estruturas;

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
            if (opcoes.contains("ano")) bubblesortPorAno(filmes);
            else if (opcoes.contains("duracao")) bubblesortPorDuracao(filmes);
            else if (opcoes.contains("titulo")) bubblesortPorTitulo(filmes);
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

    public static void bubblesortPorAno(ObservableList<Filme> filmes){
        System.out.println("Realizando BubbleSort por ano");
    }

    public static void bubblesortPorDuracao(ObservableList<Filme> filmes){
        System.out.println("Realizando BubbleSort por duração");
    }

    public static void bubblesortPorTitulo(ObservableList<Filme> filmes){
        System.out.println("Realizando BubbleSort por título");
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
