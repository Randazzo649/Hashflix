package ra.catalogo.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ra.catalogo.estruturas.TabelaHashFilme;

public class Arquivista {
    
    private static final File REPOSITORIO = new File("./Repositorio/FilmesHash.SER");
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private static Arquivista arquivista;

    public static Arquivista getArquivista(){
        if (!REPOSITORIO.exists())
            new File(REPOSITORIO.getParent()).mkdirs();
        if (arquivista == null)
            arquivista = new Arquivista();
        return arquivista;
    }

    public void persistirTabelaHash(TabelaHashFilme tabela){
        try{
            FileOutputStream fout = new FileOutputStream(REPOSITORIO);
            out = new ObjectOutputStream(fout);
            out.writeObject(tabela);
            out.close();
            fout.close();
        } catch (Exception e){
            System.out.println("-Falha ao serializar tabela hash");
            System.out.println("_______________________________");
            e.printStackTrace();
            System.out.println("_______________________________");
        }
    }

    public TabelaHashFilme carregarDadosPersistidos(){
        TabelaHashFilme tabela = new TabelaHashFilme(1);
        if (!REPOSITORIO.exists())
            return tabela;
        if (REPOSITORIO.length() == 0)
            return tabela;
        try{
            FileInputStream fin = new FileInputStream(REPOSITORIO);
            in = new ObjectInputStream(fin);
            tabela = (TabelaHashFilme) in.readObject();
            in.close();
            fin.close();
        } catch (Exception e){
            System.out.println("-Falha recarregar tabela hash");
            System.out.println("_______________________________");
            e.printStackTrace();
            System.out.println("_______________________________");
        }
        return tabela;
    }

    public static String getCaminhoRepositorio() {
        return REPOSITORIO.getPath();
    }

}
