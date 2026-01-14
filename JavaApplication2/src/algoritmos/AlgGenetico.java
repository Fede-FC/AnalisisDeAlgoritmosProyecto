/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos;
import conceptos.Puzzle;
import conceptos.PuzzleFactory;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author araya
 */
public class AlgGenetico {
    private ArrayList<Puzzle> puzzleList;
    private int tamano;
    private int poblacionInicial=30;

    public AlgGenetico(ArrayList<Puzzle> puzzlelist) {
        this.puzzleList = puzzlelist;
        this.tamano = puzzlelist.get(0).getSize();
        
        
        if (puzzlelist.get(0).getSize()<30){
            poblacionInicial=puzzlelist.get(0).getSize();
        } 
    }
    
    public void resolver(){
        
        //Cada ciclo representa una generacion
        for (int generation = 0; generation < 10; generation++) {
            Collections.sort(puzzleList, (a,b) -> b.evaluateFitness() - a.evaluateFitness());
            if (puzzleList.size()>poblacionInicial) 
                puzzleList.subList(poblacionInicial, puzzleList.size()).clear();

            
            
            
            
            
            
            
            
            
            
        }
         
        return;
        
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
