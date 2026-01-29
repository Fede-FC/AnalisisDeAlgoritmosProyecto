package algoritmos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;
import conceptos.Pieza;
import conceptos.Puzzle;

public class Greedy{

    Puzzle puzzle;
    
    long a, c;

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
    public void solve(Puzzle puzzle, int cantNums){
        this.puzzle = puzzle;
        ArrayList<Pieza> piezas = puzzle.getPiezas();
        buildHashMaps(piezas);
        frecuencias = new int[cantNums+1];
        a += 3;
        countFrequencies(piezas, frecuencias);
        solveGreedy4Dir();
    }
    
    public void variables(){
        System.out.println("Realizo "+ this.c+" comparaciones y " + this.a+" asignaciones");
        long b = a + c;
        System.out.println("Total: " + b);
    }

    // Se crean hashmaps con las piezas segun el numero en cada posicion
    public void buildHashMaps(ArrayList<Pieza> piezas){
        for(Pieza p : piezas){ c++; a++;
            topMap.computeIfAbsent(p.getTop(), k -> new ArrayList<>()).add(p);
            rightMap.computeIfAbsent(p.getRight(), k -> new ArrayList<>()).add(p);
            bottomMap.computeIfAbsent(p.getBottom(), k -> new ArrayList<>()).add(p);
            leftMap.computeIfAbsent(p.getLeft(), k -> new ArrayList<>()).add(p);
            a += 4;
        }
    }

    // Se cuentan las veces que aparece cada numero para ver cuales son mas raros
    public void countFrequencies(ArrayList<Pieza> piezas, int[] frecuencias){
        for(Pieza p : piezas){ c++; a++;
            frecuencias[p.getTop()]++;
            frecuencias[p.getBottom()]++;
            frecuencias[p.getRight()]++;
            frecuencias[p.getLeft()]++;
            a += 4;
        }
    }

    // Se calcula la rareza de una pieza segun los numeros de los lados
    private int scoreFun(Pieza p, int row, int col){
        int s = 0, n = puzzle.getSize();
        if(col < n - 1) s += frecuencias[p.getRight()];
        if(row < n - 1) s += frecuencias[p.getBottom()];
        if(col > 0) s += frecuencias[p.getLeft()];
        if(row > 0) s += frecuencias[p.getTop()];
        a += 6;
        c += 4;
        return s;
    }

    // Comparador de la pq en base a la rareza
    private Comparator<Pieza> createComparator(int i, int j){
        return (a, b) -> Integer.compare(scoreFun(a, i, j), scoreFun(b, i, j));
    }

    // Revisa si la posicion actual es adecuada 
    // (para no poner una pieza en medio de la nada)
    private boolean isActivePosition(int i, int j){
        if(puzzle.getPieza(i, j) != null){ c++; return false; }
        if(i > 0){ c++;
            if(puzzle.getPieza(i - 1, j) != null){ c++;
                return true;
            }
        }
        if(i < puzzle.getSize() - 1){ c++;
            if(puzzle.getPieza(i + 1, j) != null){ c++;
                return true;
            }
        }
        if(j > 0){ c++;
            if(puzzle.getPieza(i, j - 1) != null){ c++;
                return true;
            }
        }
        if(j < puzzle.getSize() - 1){ c++;
            if(puzzle.getPieza(i, j + 1) != null){ c++;
                return true;
            }
        }
        return false;
    }

    // Para ver si la pieza encaja con los vecinos
    private boolean fitsAll(Pieza p, int i, int j){
        if(i > 0){ c++;
            if(puzzle.getPieza(i - 1, j) != null){ c++;
                if(puzzle.getPieza(i - 1, j).getBottom() != p.getTop()){ c++;
                    return false;
                }
            }
        }
        if(i < puzzle.getSize() - 1){ c++;
            if(puzzle.getPieza(i + 1, j) != null){ c++;
                if(puzzle.getPieza(i + 1, j).getTop() != p.getBottom()){ c++;
                    return false;
                }
            }
        }
        if(j > 0){ c++;
            if(puzzle.getPieza(i, j - 1) != null){ c++;
                if(puzzle.getPieza(i, j - 1).getRight() != p.getLeft()){ c++;
                    return false;
                }
            }
        }
        if(j < puzzle.getSize() - 1){ c++;
            if(puzzle.getPieza(i, j + 1) != null){ c++;
                if(puzzle.getPieza(i, j + 1).getLeft() != p.getRight()){ c++;
                    return false;
                }
            }
        }
        return true;
    }

    // Se obtienen las piezas adecuadas para colocar en vez de probarlas todas
    private ArrayList<Pieza> getCandidates(Integer top, Integer bottom, Integer left, Integer right){
        if(top != null){ c++; return topMap.getOrDefault(top, new ArrayList<>()); }
        if(left != null){ c++; return leftMap.getOrDefault(left, new ArrayList<>()); }
        if(bottom != null){  c++; return bottomMap.getOrDefault(bottom, new ArrayList<>()); }
        if(right != null){ c++; return rightMap.getOrDefault(right, new ArrayList<>()); }
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
        a+=2;
        Pieza start = puzzle.getPiezas().get(0);
        puzzle.colocarPieza(0, 0, start);
        puzzle.definirUsed(start.getId(), true);
        colocadas++; a+=2;

        while(colocadas < n * n){ c++;
            Move best = null; a++;
            for(int i = 0; i < n; i++){ a++; c++;
                for(int j = 0; j < n; j++){ a++; c++;
                    if(!isActivePosition(i, j)){ c++; continue; }

                    Integer top = (i > 0 && puzzle.getPieza(i-1, j) != null)
                            ? puzzle.getPieza(i-1, j).getBottom() : null; a++; c+=2;
                    Integer bottom = (i < n-1 && puzzle.getPieza(i+1, j) != null)
                            ? puzzle.getPieza(i+1, j).getTop() : null; a++; c+=2;
                    Integer left = (j > 0 && puzzle.getPieza(i, j-1) != null)
                            ? puzzle.getPieza(i, j-1).getRight() : null; a++; c+=2;
                    Integer right = (j < n-1 && puzzle.getPieza(i, j+1) != null)
                            ? puzzle.getPieza(i, j+1).getLeft() : null; a++; c+=2;

                    PriorityQueue<Pieza> pq = new PriorityQueue<>(createComparator(i, j)); a++;

                    for(Pieza p : getCandidates(top, bottom, left, right)){ a++; c++;
                        if(!puzzle.conocerUsed(p.getId())){ c++;
                            if(fitsAll(p, i, j)){
                                pq.add(p); a++;
                            }
                        }
                    }

                    if(!pq.isEmpty()){ c++;
                        Pieza p = pq.peek(); a++;
                        int score = scoreFun(p, i, j); a++;
                        if(best == null || score < best.score){ c+=2;
                            best = new Move(i, j, p, score); a++;
                        }
                    }
                }
            }

            if(best == null){ c++;
                return;
            }

            puzzle.colocarPieza(best.row, best.col, best.pieza);
            puzzle.definirUsed(best.pieza.getId(), true);
            a++;
            colocadas++; 
        }
    }
}