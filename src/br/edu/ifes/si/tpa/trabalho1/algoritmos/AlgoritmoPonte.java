package br.edu.ifes.si.tpa.trabalho1.algoritmos;

import br.edu.ifes.si.tpa.trabalho1.estruturas.In;
import javafx.scene.paint.Color;
import br.edu.ifes.si.tpa.trabalho1.estruturas.Grafo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifes.si.tpa.trabalho1.estruturas.Aresta;

/******************************************************************************
 *  Compilação:   javac AlgoritmoPonte.java
 *  Execução:     java  AlgoritmoPonte dados.txt
 *  Dependências: Grafo.java Aresta.java
 *  Identifica arestas ponte e imprime-as
 *
 *  Nota: este códgo assume a inexistência de arestas paralelas. Em caso de 
 *        arestas paralelas, estas seriam identificadas incorretamente como pontes. 
 ******************************************************************************/

public class AlgoritmoPonte {
    private int pontes;      // número de pontes
    private int cont;        // contador
    private int[] pre;       // pre[v] = o número de pré-ordem de v
    private int[] low;       // low[v] = é o valor de um caminho interessante de valor mínimo.
    private ArrayList<Aresta> listaArestas;

    public ArrayList<Aresta> getListaArestas() {
        return listaArestas;
    }

    public AlgoritmoPonte(Grafo G) {
        listaArestas = new ArrayList<Aresta>();
        low = new int[G.V()];
        pre = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            low[v] = -1;
        for (int v = 0; v < G.V(); v++)
            pre[v] = -1;
        
        for (int v = 0; v < G.V(); v++)
            if (pre[v] == -1)
                dfs(G, v, v);
    }

    public int componentes() { return pontes + 1; }
    
    public int pontes() { return pontes; }

    private void dfs(Grafo G, int u, int v) {
        pre[v] = cont++;
        low[v] = pre[v];
        for (Aresta a : G.adj(v)) {
            int w = a.getV2();
            if (pre[w] == -1) {
                dfs(G, v, w);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] == pre[w]) {
                    listaArestas.add(a);
                    System.out.println(v + "-" + w + " é uma ponte");
                    pontes++;
                }
            }

            // atualiza número low - ignora o reverso da aresta que leva a v
            else if (w != u)
                low[v] = Math.min(low[v], pre[w]);
        }
    }

    /**
     * Testa a classe AlgoritmoPonte.
     */
    public static void main(String[] args) {
        // instanciando o Grafo G via arquivo
        File file = new File("_dados\\Grafo-Ponte.txt");
        In in = new In(file);
        Grafo G = new Grafo(in);
        System.out.println(G);

        AlgoritmoPonte ponte = new AlgoritmoPonte(G);
        System.out.println("Pontes = " + ponte.pontes());
    }


}

