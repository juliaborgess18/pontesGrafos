// // Classe responsável por startar a aplicação
package br.edu.ifes.si.tpa.trabalho1;

import java.io.IOException;
import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;
import com.brunomnsilva.smartgraph.graphview.SmartCircularSortedPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartPlacementStrategy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private static Scene scene;
    // public static Stage stage;
    private static Parent root;

    @Override
    public void start(Stage ignored) throws Exception {
        // root = FXMLLoader.load(getClass().getResource("FXMLTelaPrincipal.fxml"));
        // scene = new Scene(root, 350, 500 );

        // stage.setTitle("Pontes em Grafos");
        // stage.setScene(scene);
        // stage.setResizable(false);

        // Main.stage = stage;
        // stage.show();
        // Create the graph

        Graph<String, String> g = new GraphEdgeList<>();

        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");
        g.insertVertex("E");
        g.insertVertex("F");
        g.insertVertex("G");

        g.insertEdge("A", "B", "1");
        g.insertEdge("A", "C", "2");
        g.insertEdge("A", "D", "3");
        g.insertEdge("A", "E", "4");
        g.insertEdge("A", "F", "5");
        g.insertEdge("A", "G", "6");

        g.insertVertex("H");
        g.insertVertex("I");
        g.insertVertex("J");
        g.insertVertex("K");
        g.insertVertex("L");
        g.insertVertex("M");
        g.insertVertex("N");

        g.insertEdge("H", "I", "7");
        g.insertEdge("H", "J", "8");
        g.insertEdge("H", "K", "9");
        g.insertEdge("H", "L", "10");
        g.insertEdge("H", "M", "11");
        g.insertEdge("H", "N", "12");

        g.insertEdge("A", "H", "0");

        SmartPlacementStrategy strategy = new SmartCircularSortedPlacementStrategy();
        SmartGraphPanel<String, String> graphView = new SmartGraphPanel<>(g, strategy);
        Scene scene = new Scene(graphView, 350, 500);

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("JavaFXGraph Visualization");
        stage.setScene(scene);
        stage.show();

        // IMPORTANT! - Called after scene is displayed, so we can initialize the graph
        // visualization
        graphView.init();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
