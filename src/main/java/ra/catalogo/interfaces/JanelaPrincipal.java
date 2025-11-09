package ra.catalogo.interfaces;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ra.catalogo.models.Filme;
import ra.catalogo.estruturas.TabelaHashFilme;
import ra.catalogo.estruturas.Vetor;

public class JanelaPrincipal {

    private static Scene cena;
    private static final double W = 600.0;
    private static final double H = 400.0;
    
    //principais atributos da janela principal
    private static TabelaHashFilme tabelaHash = new TabelaHashFilme(20); //tabela para busca dos dados
    private static final ObservableList<Filme> filmes = FXCollections.observableArrayList(); //dados que aparecem na interface
    private TableView<Filme> tabela = new TableView<>(filmes);
    TextField campoBusca = new TextField();

    @SuppressWarnings("unchecked")
    public Scene getInterface() {
        //_____________________________CRIANDO COLUNAS DA TABELA______________________________
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Filme, String> colTitulo = new TableColumn<>("Título");
        colTitulo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTitulo()));
        TableColumn<Filme, String> colAno = new TableColumn<>("Ano");
        colAno.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAno()));
        TableColumn<Filme, String> colDuracao = new TableColumn<>("Duração (min)");
        colDuracao.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDuracao()));
        tabela.getColumns().addAll(colTitulo, colAno, colDuracao);

        //_____________________________CAIXA VERTICAL SUPERIOR______________________________
        campoBusca.setPromptText("Pesquisar por título...");
        Button btnBuscar = new Button("Buscar");
        Button btnOrdenar = new Button("Ordenar");
        Button btnMostrarTodos = new Button("Mostrar Todos");
        HBox barraBusca = new HBox(10, campoBusca, btnBuscar, btnMostrarTodos, btnOrdenar);
        barraBusca.setAlignment(Pos.CENTER);
        
        //_____________________________CAIXA VERTICAL INFERIOR______________________________
        // adicionar/remover
        Button btnAdicionar = new Button("Adicionar Filme");
        Button btnRemover = new Button("Remover Selecionado");
        HBox barraBotoes = new HBox(10, btnAdicionar, btnRemover);
        barraBotoes.setAlignment(Pos.CENTER);
        
        //_____________________________ADICIONA TUDO A CENA______________________________
        VBox layout = new VBox(10, barraBusca, tabela, barraBotoes);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));
        
        
        // DEFINIÇÃO DAS FUNCIONALIDADES
        btnAdicionar.setOnAction(e -> abrirJanelaAdicionar());
        btnRemover.setOnAction(e -> { removerFilme(); });
        btnBuscar.setOnAction(e -> {buscarPorTitulo();});
        btnOrdenar.setOnAction(e -> {abrirJanelaOrdenação();});
        btnMostrarTodos.setOnAction(e -> {
            tabela.setItems(filmes);
            campoBusca.clear();
        });
        cena = new Scene(layout, W, H);
        return cena;
    }





    
    //_________________________________METODOS PRIVADOS______________________________

    private void abrirJanelaAdicionar() {
        Stage janela = new Stage();
        JanelaAdicaoDeFilme layout = new JanelaAdicaoDeFilme();

        janela.initModality(Modality.APPLICATION_MODAL);
        janela.setTitle("Adicionar Filme");        
        layout.getBtnSalvar().setOnAction(e -> {layout.adicionarFilme(filmes, tabelaHash);});
        janela.setScene(layout.getInterface());
        janela.showAndWait();
    }

    private void abrirJanelaOrdenação(){
        Stage janela = new Stage();
        JanelaOrdenacao layout = new JanelaOrdenacao();
        layout.getOrdenar().setOnAction(e -> {
            String opcoes = layout.getOpcoes();
            Vetor.ordenar(filmes, opcoes);
        });
        layout.getCancelar().setOnAction(e -> {janela.close();});
        janela.setScene(layout.getScene());
        janela.showAndWait();
    }

    private void removerFilme(){
        Filme selecionado = tabela.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            filmes.remove(selecionado);
            tabelaHash.remover(selecionado.getTitulo());
        } else {
            mostrarAlerta("Nenhum filme selecionado para remover.");
        }
    }

    private void buscarPorTitulo(){
            String titulo = campoBusca.getText().trim();
            if (titulo.isEmpty()) {
                mostrarAlerta("Digite um título para buscar.");
                return;
            }

            Filme encontrado = tabelaHash.buscar(titulo);
            if (encontrado != null) {
                // Mostra o filme encontrado
                tabela.setItems(FXCollections.observableArrayList(encontrado));
                mostrarAlerta("Filme encontrado: " + encontrado.getTitulo());
            } else {
                mostrarAlerta("Filme não encontrado!");
                tabela.setItems(FXCollections.observableArrayList());
            }
    }

    private static void mostrarAlerta(String msg) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(msg);
        alerta.showAndWait();
    }

    public void setTabelaHash(TabelaHashFilme tabelaHash) {
        Filme[] filmesNaTabela =  tabelaHash.getTabela();
        for(Filme f : filmesNaTabela){
            if ( f != null )
                filmes.add(f);
        }
        JanelaPrincipal.tabelaHash = tabelaHash;
    }

    public TabelaHashFilme getTabelaHash() {
        return tabelaHash;
    }

}
