package algoritmos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;
import conceptos.Pieza;
import conceptos.Puzzle;

public class Greedy{

    Puzzle puzzle;

    HashMap<Integer, ArrayList<Pieza>> topMap = new HashMap<>();
    HashMap<Integer, ArrayList<Pieza>> rightMap = new HashMap<>();
    HashMap<Integer, ArrayList<Pieza>> bottomMap = new HashMap<>();
    HashMap<Integer, ArrayList<Pieza>> leftMap = new HashMap<>();

    int[] frecuencias;

    private static class Move{
        int row, col; Pieza pieza; int score;

        Move(int r, int c, Pieza p, int s){
            row = r;
            col = c;
            pieza = p;
            score = s;
        }
    }

    public Greedy(){}

    // Se llaman las funciones necesarias para el algoritmo
    public void solve(Puzzle puzzle){
        this.puzzle = puzzle;
        ArrayList<Pieza> piezas = puzzle.getPiezas();
        buildHashMaps(piezas);
        frecuencias = new int[16];
        countFrequencies(piezas, frecuencias);
        solveGreedy4Dir();
    }

    // Se crean hashmaps con las piezas segun el numero en cada posicion
    public void buildHashMaps(ArrayList<Pieza> piezas){
        for(Pieza p : piezas){
            topMap.computeIfAbsent(p.getTop(), k -> new ArrayList<>()).add(p);
            rightMap.computeIfAbsent(p.getRight(), k -> new ArrayList<>()).add(p);
            bottomMap.computeIfAbsent(p.getBottom(), k -> new ArrayList<>()).add(p);
            leftMap.computeIfAbsent(p.getLeft(), k -> new ArrayList<>()).add(p);
        }
    }

    // Se cuentan las veces que aparece cada numero para ver cuales son mas raros
    public void countFrequencies(ArrayList<Pieza> piezas, int[] frecuencias){
        for(Pieza p : piezas){
            frecuencias[p.getTop()]++;
            frecuencias[p.getBottom()]++;
            frecuencias[p.getRight()]++;
            frecuencias[p.getLeft()]++;
        }
    }

    // Se calcula la rareza de una pieza segun los numeros de los lados
    private int scoreFun(Pieza p, int row, int col){
        int s = 0, n = puzzle.getSize();
        if(col < n - 1) s += frecuencias[p.getRight()];
        if(row < n - 1) s += frecuencias[p.getBottom()];
        if(col > 0) s += frecuencias[p.getLeft()];
        if(row > 0) s += frecuencias[p.getTop()];
        return s;
    }

    // Comparador de la pq en base a la rareza
    private Comparator<Pieza> createComparator(int i, int j){
        return (a, b) -> Integer.compare(scoreFun(a, i, j), scoreFun(b, i, j));
    }

    // Revisa si la posicion actual es adecuada 
    // (para no poner una pieza en medio de la nada)
    private boolean isActivePosition(int i, int j){
        if(puzzle.getPieza(i, j) != null) return false;
        return (i > 0 && puzzle.getPieza(i-1, j) != null) ||
               (i < puzzle.getSize()-1 && puzzle.getPieza(i+1, j) != null) ||
               (j > 0 && puzzle.getPieza(i, j-1) != null) ||
               (j < puzzle.getSize()-1 && puzzle.getPieza(i, j+1) != null);
    }

    // Para ver si la pieza encaja con los vecinos
    private boolean fitsAll(Pieza p, int i, int j){
        if(i > 0 && puzzle.getPieza(i-1, j) != null &&
           puzzle.getPieza(i-1, j).getBottom() != p.getTop())
            return false;

        if(i < puzzle.getSize()-1 && puzzle.getPieza(i+1, j) != null &&
           puzzle.getPieza(i+1, j).getTop() != p.getBottom())
            return false;

        if(j > 0 && puzzle.getPieza(i, j-1) != null &&
           puzzle.getPieza(i, j-1).getRight() != p.getLeft())
            return false;

        if(j < puzzle.getSize()-1 && puzzle.getPieza(i, j+1) != null &&
           puzzle.getPieza(i, j+1).getLeft() != p.getRight())
            return false;

        return true;
    }

    // Se obtienen las piezas adecuadas para colocar en vez de probarlas todas
    private ArrayList<Pieza> getCandidates(Integer top, Integer bottom, Integer left, Integer right){
        if(top != null) return topMap.getOrDefault(top, new ArrayList<>());
        if(left != null) return leftMap.getOrDefault(left, new ArrayList<>());
        if(bottom != null) return bottomMap.getOrDefault(bottom, new ArrayList<>());
        if(right != null) return rightMap.getOrDefault(right, new ArrayList<>());
        return puzzle.getPiezas();
    }

    /*
    Va llenando el tablero, cada que pone una pieza, usa la pq
    para ver cuales son las piezas adecuadas segun la posicion actual
    hasta quedarse sin piezas optimas
    */
    public void solveGreedy4Dir(){
        int n = puzzle.getSize();
        int colocadas = 0;

        Pieza start = puzzle.getPiezas().get(0);
        puzzle.colocarPieza(0, 0, start);
        puzzle.definirUsed(start.getId(), true);
        colocadas++;

        while(colocadas < n * n){
            Move best = null;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(!isActivePosition(i, j)) continue;

                    Integer top = (i > 0 && puzzle.getPieza(i-1, j) != null)
                            ? puzzle.getPieza(i-1, j).getBottom() : null;
                    Integer bottom = (i < n-1 && puzzle.getPieza(i+1, j) != null)
                            ? puzzle.getPieza(i+1, j).getTop() : null;
                    Integer left = (j > 0 && puzzle.getPieza(i, j-1) != null)
                            ? puzzle.getPieza(i, j-1).getRight() : null;
                    Integer right = (j < n-1 && puzzle.getPieza(i, j+1) != null)
                            ? puzzle.getPieza(i, j+1).getLeft() : null;

                    PriorityQueue<Pieza> pq = new PriorityQueue<>(createComparator(i, j));

                    for(Pieza p : getCandidates(top, bottom, left, right)){
                        if(!puzzle.conocerUsed(p.getId()) && fitsAll(p, i, j)) pq.add(p);
                    }

                    if(!pq.isEmpty()){
                        Pieza p = pq.peek();
                        int score = scoreFun(p, i, j);
                        if(best == null || score < best.score) best = new Move(i, j, p, score);
                    }
                }
            }

            if(best == null){
                return;
            }

            puzzle.colocarPieza(best.row, best.col, best.pieza);
            puzzle.definirUsed(best.pieza.getId(), true);
            colocadas++;
        }
    }
}