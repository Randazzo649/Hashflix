package ra.catalogo.interfaces;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class JanelaOrdenacao {

    Button cancelar = new Button("Cancelar");
    Button ordenar = new Button("Ordenar");
    ComboBox<String> atributo = new ComboBox<>();
    ComboBox<String> metodos = new ComboBox<>();

    public Scene getScene(){
        VBox principal = new VBox(5);
        principal.setPadding(new Insets(9));

        HBox superior = new HBox(7);
        Label lblAtributo = new Label("Ordenar por: ");
        atributo.getItems().addAll("ano", "duracao", "titulo");
        superior.setAlignment(Pos.CENTER);
        superior.getChildren().addAll(lblAtributo, atributo);

        HBox meio = new HBox(14);
        Label lblMetodo = new Label("Metodo: ");
        metodos.getItems().addAll("bubblesort", "insertionsort", "quicksort");
        meio.setAlignment(Pos.CENTER);
        meio.getChildren().addAll(lblMetodo, metodos);

        HBox inferior = new HBox(3);
        inferior.setAlignment(Pos.CENTER);
        inferior.getChildren().addAll(cancelar, ordenar);


        principal.setAlignment(Pos.CENTER);
        principal.getChildren().addAll(superior, meio, inferior);
        return new Scene(principal);
    }

    public String getOpcoes(){
        return metodos.getValue() + " " + atributo.getValue();
    }

    public Button getOrdenar() {
        return ordenar;
    }

    public Button getCancelar() {
        return cancelar;
    }
}
