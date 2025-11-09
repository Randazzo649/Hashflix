package ra.catalogo.models;

import java.io.Serializable;

public class Filme implements Serializable{
    private String titulo;
    private String ano;
    private String duracao;

    public Filme(String titulo, String ano, String duracao) {
        this.titulo = titulo;
        this.ano = ano;
        this.duracao = duracao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAno() {
        return ano;
    }

    public int getAnoAsInt(){
        return Integer.parseInt(ano);
    }

    public int getDuracaoAsInt(){
        return Integer.parseInt(duracao);
    }

    public String getDuracao() {
        return duracao;
    }

    @Override
    public String toString() {
        return titulo + " (" + ano + ", " + duracao + " min)";
    }

    @Override
    public int hashCode() {
        return titulo.toLowerCase().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Filme outro = (Filme) obj;
        return titulo.equalsIgnoreCase(outro.titulo);
    }
}
