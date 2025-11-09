package ra.catalogo;

import java.util.Optional;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import ra.catalogo.estruturas.TabelaHashFilme;
import ra.catalogo.interfaces.JanelaPrincipal;
import ra.catalogo.models.Arquivista;

public class Main extends Application{

    private final String TITULO_JANELA = "HashFlix";
    private final Arquivista ARQUIVISTA = Arquivista.getArquivista();

    public void start(Stage stg){
        JanelaPrincipal main = new JanelaPrincipal();
        rotinaInicio(main);
        stg.setOnCloseRequest(e -> { rotinaEncerramento(main.getTabelaHash()); });
        stg.setScene(main.getInterface());
        stg.setTitle(TITULO_JANELA);
        stg.show();
    }

    private void rotinaEncerramento(TabelaHashFilme tabela) {
        String mensagem = "deseja salvar os dados atuais?";
        Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle("Guardar dados atuais ?");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);

        alerta.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> resultado = alerta.showAndWait();
        boolean persistir = resultado.isPresent() && resultado.get() == ButtonType.YES;
        if (persistir)
            ARQUIVISTA.persistirTabelaHash(tabela);
    }

    private void rotinaInicio(JanelaPrincipal main){
        TabelaHashFilme filmes = ARQUIVISTA.carregarDadosPersistidos();
        if (filmes != null)
            main.setTabelaHash(filmes);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
