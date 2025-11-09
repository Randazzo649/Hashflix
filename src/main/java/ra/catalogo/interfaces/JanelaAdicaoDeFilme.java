package ra.catalogo.interfaces;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import ra.catalogo.estruturas.TabelaHashFilme;
import ra.catalogo.models.Filme;

public class JanelaAdicaoDeFilme {
    
    private static final double W = 300.0;
    private static final double H = 200.0;

    private TextField campoTitulo = new TextField();
    private TextField campoAno = new TextField();
    private TextField campoDuracao = new TextField();
    private Button btnSalvar = new Button("Salvar");

    //metodo que constroi o layout 
    public Scene getInterface(){
        campoTitulo.setPromptText("Título");
        campoAno.setPromptText("Ano de lançamento");
        campoDuracao.setPromptText("Duração (minutos)");
        
        VBox caixa = new VBox(10, campoTitulo, campoAno, campoDuracao, btnSalvar);
        caixa.setAlignment(Pos.CENTER);
        caixa.setPadding(new Insets(15));
         return new Scene(caixa, W, H);
    }

    // e o metodo que adiciona o filme com os dados informados na tabela
    // retorna um boolean caso a inserção seja feita
    public boolean adicionarFilme(ObservableList<Filme> filmes, TabelaHashFilme tabelaHash){
        String titulo = campoTitulo.getText().trim();
        String ano = campoAno.getText().trim();
        String duracao = campoDuracao.getText().trim();

        if (!titulo.isEmpty() && !ano.isEmpty() && !duracao.isEmpty()) {
            Filme novo = new Filme(titulo, ano, duracao);
            filmes.add(novo);
            tabelaHash.inserir(novo);
            //janela.close();
            return true;
        } else {
            mostrarAlerta("Preencha todos os campos!");
            return false;
        }
    }

    private static void mostrarAlerta(String msg) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(msg);
        alerta.showAndWait();
    }

    public Button getBtnSalvar() {
        return btnSalvar;
    }

}
