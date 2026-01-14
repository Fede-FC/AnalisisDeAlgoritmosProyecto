/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos;
import conceptos.Puzzle;
import conceptos.PuzzleFactory;
import java.util.ArrayList;

/**
 *
 * @author araya
 */
public class AlgGenetico {
    ArrayList<Puzzle> puzzlelist;
    static int tamano;

    public AlgGenetico(ArrayList<Puzzle> puzzlelist) {
        this.puzzlelist = puzzlelist;
        this.tamano = puzzlelist.get(0).getSize();
    }
    
    public static void algGenetico(){
        
        //Cada ciclo representa una generacion
        for (int i = 0; i < 10; i++) {
            
            
            
            
            
            
            
            
            
            
            
        }
         
        return;
        
    }
    
    
    public static void main(String[] args) {
        Puzzle rompCabezas = new Puzzle(7);
        ArrayList<Puzzle> puzzleList= new ArrayList<>();
        rompCabezas = PuzzleFactory.createRandom(7, 9);
        rompCabezas.print();
        //De locos
        int poblacionInicial = 30;
        if (rompCabezas.getSize()<30){
            poblacionInicial=rompCabezas.getSize();
        } 
        System.out.println("1-----------------------------------1");
        puzzleList.add(rompCabezas);
      
        for (int individuos = 0; individuos < poblacionInicial-1; individuos++) {
            Puzzle puzzle = new Puzzle(rompCabezas.getSize());
            puzzle = PuzzleFactory.createRandom(rompCabezas.getSize(), 9);
            puzzle= PuzzleFactory.desordenarPuzzle(puzzle);
            puzzleList.add(puzzle);
            puzzle.print();
            System.out.println("1-----------------------------------1");
        }
        
    }
}
