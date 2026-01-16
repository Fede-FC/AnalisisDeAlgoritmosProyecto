/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos;
import conceptos.Puzzle;
import conceptos.PuzzleFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author araya
 */
public class AlgGenetico {
    private ArrayList<Puzzle> puzzleList;
    private int tamano;
    private int poblacionInicial=30;
    private ArrayList<Puzzle> hijos;

    public AlgGenetico(ArrayList<Puzzle> puzzlelist) {
        this.puzzleList = puzzlelist;
        this.tamano = puzzlelist.get(0).getSize();
        this.hijos= new ArrayList<>();
        
        if (puzzlelist.get(0).getSize()<30){
            poblacionInicial=puzzlelist.get(0).getSize();
        } 
    }
    
    
    public ArrayList<Puzzle> cruce(ArrayList<Puzzle> ancestros){
        Random random = new Random();
        //int aleatorio = random.nextInt(this.tamano);
        
        
        
        
        while (hijos.size() < 2*poblacionInicial) {
            Puzzle padre= ancestros.get(random.nextInt(this.tamano));
            Puzzle madre= ancestros.get(random.nextInt(this.tamano));
            
            if (padre==madre) continue;
            
            Puzzle pHijo =cruzarFilas(padre, madre);
            hijos.add(pHijo);
            pHijo =cruzarFilas(madre, padre);
            hijos.add(pHijo);
        }
        
        
        return hijos;
    }
    
    public Puzzle cruzarFilas(Puzzle padre, Puzzle madre) {
    Puzzle hijo = new Puzzle(tamano);

    for (int i = 0; i < tamano; i++) {
        for (int j = 0; j < tamano; j++) {
            if (i % 2 == 0) {
                hijo.colocarPieza(i, j, padre.getPieza(i, j));
            } else {
                hijo.colocarPieza(i, j, madre.getPieza(i, j));
            }
        }
    }
    return hijo;
}

    
    
    public void resolver(){
        ArrayList<Puzzle> hijos;
        for (int generation = 0; generation < 10; generation++) {
            //Ordenar
            Collections.sort(puzzleList, (a,b) -> b.evaluateFitness() - a.evaluateFitness());
            //Hijos=2*Poblacion inicial - BUA DE LOCOS
            
            //cruce
            this.hijos = cruce(puzzleList);
            
            //
            
            
            
            //Eliminar los de menos rendimiento
            puzzleList.subList(poblacionInicial, puzzleList.size()).clear();
            
            
            

        }
         
        return ;
        
    }
    
    
    public static void main(String[] args) {
        Puzzle rompCabezas = new Puzzle(4);
        ArrayList<Puzzle> puzzleList= new ArrayList<>();
        rompCabezas = PuzzleFactory.createRandom(4, 9);
        rompCabezas.print();
        //De locos
        int poblacionInicial = 30;
        if (rompCabezas.getSize()<30){
            poblacionInicial=rompCabezas.getSize();
        } 
        System.out.println("1-----------------------------------1");
        puzzleList.add(rompCabezas);
        Puzzle puzzle = new Puzzle(rompCabezas.getSize());
        for (int individuos = 0; individuos < poblacionInicial-1; individuos++) {
            puzzle = PuzzleFactory.createRandom(rompCabezas.getSize(), 9);
            puzzle= PuzzleFactory.desordenarPuzzle(puzzle);
            puzzleList.add(puzzle);
            puzzle.print();
            System.out.println(puzzle.evaluateFitness());
            System.out.println("1-----------------------------------1");
        }
    }
}
