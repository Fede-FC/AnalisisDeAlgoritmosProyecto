package algoritmos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;
import java.util.PriorityQueue;
import conceptos.Pieza;
import conceptos.Puzzle;

public class Greedy{
    Puzzle puzzle;
    
    HashMap<Integer, ArrayList<Pieza>> topMap = new HashMap<>();
    HashMap<Integer, ArrayList<Pieza>> rightMap = new HashMap<>();
    HashMap<Integer, ArrayList<Pieza>> bottomMap = new HashMap<>();
    HashMap<Integer, ArrayList<Pieza>> leftMap = new HashMap<>();
    
    int[] frecuencias;
    
    
    public Greedy(){}
        
    public void solve(Puzzle puzzle){
        this.puzzle = puzzle;
        ArrayList<Pieza> piezas = puzzle.getPiezas();
        buildHashMaps(piezas);
        frecuencias = new int[10];
        countFrequencies(piezas, frecuencias);
        solveGreedy();
    }
    
    public void buildHashMaps(ArrayList<Pieza> piezas){
        for(Pieza p : piezas){
            topMap.computeIfAbsent(p.getTop(), k -> new ArrayList<>()).add(p);
            rightMap.computeIfAbsent(p.getRight(), k -> new ArrayList<>()).add(p);
            bottomMap.computeIfAbsent(p.getBottom(), k -> new ArrayList<>()).add(p);
            leftMap.computeIfAbsent(p.getLeft(), k -> new ArrayList<>()).add(p);
        }
    }
    
    public void countFrequencies(ArrayList<Pieza> piezas, int[] frecuencias){
        for(Pieza p : piezas){
            frecuencias[p.getTop()]++; frecuencias[p.getBottom()]++;
            frecuencias[p.getRight()]++; frecuencias[p.getLeft()]++;
        }
    }
    
    private int scoreFun(Pieza p, int row, int col) {
        int s = 0, n = puzzle.getSize();
        // solo lados que quedan abiertos
        if(col < n - 1) s += frecuencias[p.getRight()];
        if(row < n - 1)s += frecuencias[p.getBottom()];
        return s;
    }
    
    private Comparator<Pieza> createComparator(int i, int j) {
        return (Pieza a, Pieza b) -> {
            int A = scoreFun(a, i, j);
            int B = scoreFun(b, i, j);
            return Integer.compare(A, B);
        };
    }
    
    public void solveGreedy(){
        int n = puzzle.getSize();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                // Guarda el numero de abajo de la pieza de arriba
                Integer topPiece = (i > 0 && puzzle.getPieza(i - 1, j) != null) ? 
                        puzzle.getPieza(i - 1, j).getBottom() : null;
                // Lo mismo pero para la izquierda
                Integer leftPiece = (j > 0 && puzzle.getPieza(i, j - 1) != null) ? 
                        puzzle.getPieza(i, j - 1).getRight() : null;
                // Pq
                PriorityQueue<Pieza> pq = new PriorityQueue<>(createComparator(i, j));  
                
                // Agrega candidatos a la pq segun donde este la pieza que se quiera colocar
                
                // Empieza por arriba a la izquierda, medio tonto porque agrega todas
                // pero necesita saber cual es la de mayor rareza
                if(leftPiece == null && topPiece == null){
                    for(Pieza p : puzzle.getPiezas()){
                        if(!puzzle.conocerUsed(p.getId())){
                            pq.add(p);
                            //System.out.println("Pieza agregada = " + p + " " + scoreFun(p, i, j));
                        }
                    }
                }
                // Primera fila
                else if(leftPiece != null && topPiece == null){
                    for(Pieza p : leftMap.getOrDefault(leftPiece, new ArrayList<>())){
                        if(!puzzle.conocerUsed(p.getId())){
                            pq.add(p);
                            //System.out.println("Pieza agregada = " + p);
                        }
                    }
                }
                // Primera columna
                else if(leftPiece == null && topPiece != null){
                    for(Pieza p : topMap.getOrDefault(topPiece, new ArrayList<>())){
                        if(!puzzle.conocerUsed(p.getId())){
                            pq.add(p);
                            //System.out.println("Pieza agregada = " + p);
                        }
                    }
                }
                // Interior
                else{ // topPiece != null && leftPiece != null
                    for(Pieza p : leftMap.getOrDefault(leftPiece, new ArrayList<>())){
                        if(!puzzle.conocerUsed(p.getId())
                            && p.getTop() == topPiece){
                            pq.add(p);
                            //System.out.println("Pieza agregada = " + p);
                        }
                    }
                }
                if(pq.isEmpty()){
                    //System.out.println("Fallo greedy en (" + i + "," + j + ")"); 
                    return;
                }

                Pieza p = pq.poll();
                //System.out.println("PQ = " + p + " " + scoreFun(p, i, j));
                puzzle.colocarPieza(i, j, p);
                puzzle.definirUsed(p.getId(), true);
            }
        }
    }
}