package ra.catalogo;

import javafx.application.Application;
import javafx.stage.Stage;
import ra.catalogo.interfaces.JanelaPrincipal;

public class Main extends Application{

    private final String TITULO_JANELA = "HashFlix";

    public void start(Stage stg){
        stg.setScene(JanelaPrincipal.getInterface());
        stg.setTitle(TITULO_JANELA);
        stg.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
