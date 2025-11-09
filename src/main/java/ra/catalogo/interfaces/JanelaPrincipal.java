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

public class JanelaPrincipal {

    private static Scene cena;
    private static final double W = 600.0;
    private static final double H = 400.0;
    private static final ObservableList<Filme> filmes = FXCollections.observableArrayList();

    private TabelaHashFilme tabelaHash = new TabelaHashFilme(20);

    @SuppressWarnings("unchecked")
    public  Scene getInterface() {
        TableView<Filme> tabela = new TableView<>(filmes);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Filme, String> colTitulo = new TableColumn<>("Título");
        colTitulo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTitulo()));

        TableColumn<Filme, String> colAno = new TableColumn<>("Ano");
        colAno.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAno()));

        TableColumn<Filme, String> colDuracao = new TableColumn<>("Duração (min)");
        colDuracao.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDuracao()));

        tabela.getColumns().addAll(colTitulo, colAno, colDuracao);

        // search bar com tabela hash
        TextField campoBusca = new TextField();
        campoBusca.setPromptText("Pesquisar por título...");

        Button btnBuscar = new Button("Buscar");
        btnBuscar.setOnAction(e -> {
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
        });

        //  restaurar todos os filmes
        Button btnMostrarTodos = new Button("Mostrar Todos");
        btnMostrarTodos.setOnAction(e -> {
            tabela.setItems(filmes);
            campoBusca.clear();
        });

        HBox barraBusca = new HBox(10, campoBusca, btnBuscar, btnMostrarTodos);
        barraBusca.setAlignment(Pos.CENTER);

        // adicionar/remover
        Button btnAdicionar = new Button("Adicionar Filme");
        Button btnRemover = new Button("Remover Selecionado");

        btnAdicionar.setOnAction(e -> abrirJanelaAdicionar());
        btnRemover.setOnAction(e -> {
            Filme selecionado = tabela.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                filmes.remove(selecionado);
                tabelaHash.remover(selecionado.getTitulo());
            } else {
                mostrarAlerta("Nenhum filme selecionado para remover.");
            }
        });

        HBox barraBotoes = new HBox(10, btnAdicionar, btnRemover);
        barraBotoes.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10, barraBusca, tabela, barraBotoes);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));

        cena = new Scene(layout, W, H);
        return cena;
    }

    private void abrirJanelaAdicionar() {
        Stage janela = new Stage();
        janela.initModality(Modality.APPLICATION_MODAL);
        janela.setTitle("Adicionar Filme");

        TextField campoTitulo = new TextField();
        campoTitulo.setPromptText("Título");
        TextField campoAno = new TextField();
        campoAno.setPromptText("Ano de lançamento");
        TextField campoDuracao = new TextField();
        campoDuracao.setPromptText("Duração (minutos)");

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            String titulo = campoTitulo.getText().trim();
            String ano = campoAno.getText().trim();
            String duracao = campoDuracao.getText().trim();

            if (!titulo.isEmpty() && !ano.isEmpty() && !duracao.isEmpty()) {
                Filme novo = new Filme(titulo, ano, duracao);
                filmes.add(novo);
                tabelaHash.inserir(novo);
                janela.close();
            } else {
                mostrarAlerta("Preencha todos os campos!");
            }
        });

        VBox caixa = new VBox(10, campoTitulo, campoAno, campoDuracao, btnSalvar);
        caixa.setAlignment(Pos.CENTER);
        caixa.setPadding(new Insets(15));
        janela.setScene(new Scene(caixa, 300, 200));
        janela.showAndWait();
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
        this.tabelaHash = tabelaHash;
    }

    public TabelaHashFilme getTabelaHash() {
        return tabelaHash;
    }

}
