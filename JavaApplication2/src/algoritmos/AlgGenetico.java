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
    

    public AlgGenetico(Puzzle rompCabezas) {
        this.rompCabezas = rompCabezas;
    }
    
    
    public static void algGenetico(rompCabezas, int limNumerico){
        //De locos
        int poblacionInicial = 30;
        if (rompCabezas.getSize()<30){
            poblacionInicial=rompCabezas.getSize();
        } 
        
        ArrayList<Puzzle> puzzleList= new ArrayList<>();
        for (int individuos = 0; individuos < poblacionInicial; individuos++) {
            Puzzle puzzle = new Puzzle(rompCabezas.getSize());
            puzzle = PuzzleFactory.createRandom(rompCabezas.getSize(), limNumerico);
            puzzle= PuzzleFactory.desordenarPuzzle(puzzle);
            puzzleList.add(puzzle);
        }
        
        for (int i = 0; i < 10; i++) {
            //Generaciones aqui......
            
            
            
            
            
            
            
            
            
            
        }
         
        return;
        
    }
    
    
    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle(7);
        puzzle = PuzzleFactory.createRandom(7, 9);
        puzzle.print();
        algGenetico(puzzle, 9);
        
    }
}
