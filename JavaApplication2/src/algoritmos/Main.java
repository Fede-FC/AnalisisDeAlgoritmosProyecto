/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package algoritmos;
import conceptos.Puzzle;
import conceptos.PuzzleFactory;

/**
 * @author: 
 * 
 * 
 */
public class Main {
    
   
    
    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle(3);
        puzzle.print();
        puzzle = PuzzleFactory.createRandom(3, 5);
        puzzle.print();
        System.out.println("---------------------------");
        puzzle = PuzzleFactory.desordenarPuzzle(puzzle);
        puzzle.print();
        System.out.println("---------------------------");
        porFuerza SolverFuerza = new porFuerza(puzzle);
        puzzle.print();

        
    }

}
