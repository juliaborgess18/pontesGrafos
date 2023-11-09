package br.edu.ifes.si.tpa.trabalho1.algoritmos;

import br.edu.ifes.si.tpa.trabalho1.estruturas.In;
import br.edu.ifes.si.tpa.trabalho1.estruturas.Grafo;
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

    public AlgoritmoPonte(Grafo G) {
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
        In in = new In(args[0]);
        Grafo G = new Grafo(in);
        System.out.println(G);
        
        /*
        // instanciando o Grafo G via código
        Grafo G = new Grafo(4);
        G.addAresta(new Aresta(0, 1));
        G.addAresta(new Aresta(0, 2));
        G.addAresta(new Aresta(1, 3));
        G.addAresta(new Aresta(2, 3));
        System.out.println(G);
        */

        AlgoritmoPonte ponte = new AlgoritmoPonte(G);
        System.out.println("Pontes = " + ponte.pontes());
    }


}

