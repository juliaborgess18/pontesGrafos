package br.edu.ifes.si.tpa.trabalho1;

import br.edu.ifes.si.tpa.trabalho1.algoritmos.AlgoritmoPonte;
import br.edu.ifes.si.tpa.trabalho1.estruturas.In;
import br.edu.ifes.si.tpa.trabalho1.estruturas.Vertice;
import br.edu.ifes.si.tpa.trabalho1.estruturas.Aresta;
import br.edu.ifes.si.tpa.trabalho1.estruturas.Grafo;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    public static AlgoritmoPonte ap;
    public static Grafo G;
    Group componentes = new Group();
    Button button = new Button("Executar");


    public static void main(String[] args) {
        // String arquivo = args[0].substring(9);
        File file = new File("_dados\\Grafo-Ponte.txt");
        In in = new In(file);
        G = new Grafo(in);
        System.out.println("Grafo criado");
        //System.out.println(G);
        
        // AlgoritmoPonte ponte = new AlgoritmoPonte(G);
        // for (Aresta a : ponte.getListaPontes()) {
        //     System.out.println(a.getV1() + "-" + a.getV2());
        // }
        // System.out.println("Pontes = " + ponte.pontes());

        launch();
    }

    @Override
    public void start(Stage palco) throws Exception {

        //Adicionando a ação ao button
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ap = new AlgoritmoPonte(G);
                desenharAP(ap);
            }
        });

        componentes.getChildren().add(button); //Adicionando o button a tela principal

        //desenharGrafo(G);

        Scene cena = new Scene(componentes, 600, 550);
        palco.setTitle("IFES - SI - TPA - Trabalho 01: Pontes em Grafos");
        palco.setScene(cena);
        palco.show();
    }

    // public void desenharGrafo(Grafo G) {
    //     List<Vertice> vertices = G.vertices();

    //     //Desenhando as arestas
    //     for (Aresta a : G.arestas()) {
    //         Line line = new Line();
    //         line.setStartX(vertices.get(a.getV1()).getX());
    //         line.setStartY(vertices.get(a.getV1()).getY());
    //         line.setEndX(vertices.get(a.getV2()).getX());
    //         line.setEndY(vertices.get(a.getV2()).getY());
    //         line.setStroke(Color.BLACK);
    //         componentes.getChildren().add(line);
    //     }

    //     //Desenhando os vértices
    //     for (int v = 0; v < G.V(); v++) {
    //         Circle circle = new Circle();
    //         circle.setCenterX(vertices.get(v).getX());
    //         circle.setCenterY(vertices.get(v).getY());
    //         circle.setRadius(15.0f);
    //         circle.setStroke(Color.BLACK);
    //         circle.setFill(Color.WHITE);
    //         Text text = new Text(circle.getCenterX() - 4, circle.getCenterY() + 4, String.valueOf(v));
    //         componentes.getChildren().add(circle);
    //         componentes.getChildren().add(text);
    //     }
    // }

    public void desenharAP(AlgoritmoPonte ap) {
        //List<Vertice> vertices = G.vertices();
        // Iniciando Desenho do grafo
        final int WIDTH_SCREEN_SIZE = 500;
        final int HEIGHT_SCREEN_SIZE = 450;
        List<Double> lista_x = new ArrayList<>();
        List<Double> lista_y = new ArrayList<>();

        // Desenhando os vértices
        for (int v = 1; v < G.V(); v++) {
            Circle circle = new Circle();
            Double x = new Random().nextDouble() * WIDTH_SCREEN_SIZE;
            Double y = new Random().nextDouble() * WIDTH_SCREEN_SIZE;
            circle.setCenterX(x);
            circle.setCenterY(y);
            circle.setRadius(15.0f);
            circle.setStroke(Color.BLACK);
            circle.setFill(Color.WHITE);
            Text text = new Text(circle.getCenterX() - 4, circle.getCenterY() + 4, String.valueOf(v));
            componentes.getChildren().add(circle);
            componentes.getChildren().add(text);
        }

        // for (int i = 0; i < G.V(); i++) {
        //     Line line = new Line();
        //     line.setStartX(lista_x.get(i));
        //     line.setStartY(lista_x.get(y));
        //     line.setEndX();
        //     line.setEndY();
        //     line.setStroke(Color.RED);
        //     line.setStrokeWidth(5);
        //     componentes.getChildren().add(line);
        // }
   

        // Desenhando a árvore geradora mínima - MST
        // for (Aresta a : ap.getListaPontes()) {
        //     Line line = new Line();
        //     line.setStartX();
        //     line.setStartY();
        //     line.setEndX();
        //     line.setEndY();
        //     line.setStroke(Color.RED);
        //     line.setStrokeWidth(5);
        //     componentes.getChildren().add(line);
        // }
    }
}

