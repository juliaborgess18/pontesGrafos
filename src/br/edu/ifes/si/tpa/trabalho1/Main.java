package br.edu.ifes.si.tpa.trabalho1;

import br.edu.ifes.si.tpa.trabalho1.algoritmos.AlgoritmoPonte;
import br.edu.ifes.si.tpa.trabalho1.estruturas.In;
import br.edu.ifes.si.tpa.trabalho1.estruturas.Vertice;
import br.edu.ifes.si.tpa.trabalho1.estruturas.Aresta;
import br.edu.ifes.si.tpa.trabalho1.estruturas.Grafo;
import java.io.File;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    public static AlgoritmoPonte ap;
    public static Grafo G;
    Group componentes = new Group();
    Group componentesGrafos = new Group();
    Button btnExecutar = new Button("Executar");
    Button btnGrafo_1 = new Button("Grafo 1");
    Button btnGrafo_2 = new Button("Grafo 2");

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage palco) throws Exception {

        // Adicionando a ação ao button de executar o cálculo das pontes
        btnExecutar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ap = new AlgoritmoPonte(G);
                desenharAP(ap);
            }
        });

        // Adicionando a ação ao button de desenhar o primeiro grafo
        btnGrafo_1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                componentesGrafos.getChildren().clear();
                File file = new File("_dados\\Grafo-Ponte_1.txt");
                In in = new In(file);
                G = new Grafo(in);
                System.out.println("Objeto 'Grafo' criado");
                desenharGrafo(G);
            }
        });

        // Adicionando a ação ao button de desenhar o segundo grafo
        btnGrafo_2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                componentesGrafos.getChildren().clear();
                File file = new File("_dados\\Grafo-Ponte_2.txt");
                In in = new In(file);
                G = new Grafo(in);
                System.out.println("Objeto 'Grafo' criado");
                for (int i = 0; i < G.V(); i++) {
                    System.out.println("Vertice: " + i);
                    for (Aresta a : G.adj(i)) {
                        System.out.println(a);
                    }
                }
                desenharGrafo(G);
            }
        });

        btnGrafo_1.setLayoutX(1);
        btnGrafo_1.setLayoutY(532);
        btnGrafo_2.setLayoutX(56);
        btnGrafo_2.setLayoutY(532);

        componentes.getChildren().add(btnExecutar);
        componentes.getChildren().add(btnGrafo_1);
        componentes.getChildren().add(btnGrafo_2);

        componentes.getChildren().addAll(componentesGrafos);

        Scene cena = new Scene(componentes, 600, 550);
        palco.setTitle("IFES - SI - TPA - Trabalho 01: Pontes em Grafos");
        palco.setResizable(false);
        palco.setScene(cena);
        palco.show();
    }

    public void desenharGrafo(Grafo G) {
        List<Vertice> vertices = G.getVertices();
        
        System.out.println("--- Iniciado desenho do Grafo ---");
        
        System.out.println("info: Iniciando desenho das arestas");
        try {
            for (Aresta a : G.arestas()) {
                Line line = new Line();
                line.setStartX(vertices.get(a.getV1()).getX());
                line.setStartY(vertices.get(a.getV1()).getY());
                line.setEndX(vertices.get(a.getV2()).getX());
                line.setEndY(vertices.get(a.getV2()).getY());
                line.setStroke(Color.BLUE);
                componentesGrafos.getChildren().add(line);
            }
            System.out.println("info: Finalizado o desenho das arestas");
        } catch (Exception e) {
            System.out.println("erro: Erro ao desenhar arestas");
            e.printStackTrace();
        }

        System.out.println("info: Iniciando desenho dos vértices");
        try {
            Image image = new Image("file:src/br/edu/ifes/si/tpa/trabalho1/images/computer.png");
            for (int v = 0; v < G.V(); v++) {
                Circle circle = new Circle();
                circle.setCenterX(vertices.get(v).getX());
                circle.setCenterY(vertices.get(v).getY());
                circle.setRadius(25.0f);
                circle.setStroke(Color.TRANSPARENT);
                circle.setFill(new ImagePattern(image));
                Text text = new Text(circle.getCenterX() - 4, circle.getCenterY() + 31, String.valueOf(v));
                text.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
                componentesGrafos.getChildren().add(circle);
                componentesGrafos.getChildren().add(text);
            }
            System.out.println("info: Finalizado o desenho dos vértices");
        } catch (Exception e) {
            System.out.println("erro: Erro ao desenhar vérticies");
            e.printStackTrace();
        }

        System.out.println("--- Finalizado o desenho do Grafo ---");
    }

    public void desenharAP(AlgoritmoPonte ap) {
        List<Vertice> vertices = G.getVertices();

        System.out.println("info: Iniciando desenho das pontes");
        try {
            for (Aresta a : ap.getListaPontes()) {
                Line line = new Line();
                line.setStartX(vertices.get(a.getV1()).getX());
                line.setStartY(vertices.get(a.getV1()).getY());
                line.setEndX(vertices.get(a.getV2()).getX());
                line.setEndY(vertices.get(a.getV2()).getY());
                line.setStroke(Color.RED);
                line.setStrokeWidth(5);
                line.toBack();
                componentesGrafos.getChildren().add(line);
            }
            System.out.println("info: Finalizado o desenho das pontes");
        } catch (Exception e) {
            System.out.println("erro: Erro ao desenhar ponte");
            e.printStackTrace();
        }
        System.out.println("info: Redesenhando os vértices");
        try {
            Image image = new Image("file:src/br/edu/ifes/si/tpa/trabalho1/images/computer.png");
            for (int v = 0; v < G.V(); v++) {
                Circle circle = new Circle();
                circle.setCenterX(vertices.get(v).getX());
                circle.setCenterY(vertices.get(v).getY());
                circle.setRadius(25.0f);
                circle.setStroke(Color.TRANSPARENT);
                circle.setFill(new ImagePattern(image));
                Text text = new Text(circle.getCenterX() - 4, circle.getCenterY() + 31, String.valueOf(v));
                text.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
                componentesGrafos.getChildren().add(circle);
                componentesGrafos.getChildren().add(text);
            }
            System.out.println("info: Finalizado o redesenho dos vértices");
        } catch (Exception e) {
            System.out.println("erro: Erro ao desenhar vérticies");
            e.printStackTrace();
        }
        
    }

}

