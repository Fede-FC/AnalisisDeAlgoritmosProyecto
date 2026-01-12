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
        porFuerza solverFuerza = new porFuerza();
        
        System.out.println("---------Prueba 1---------");
        Puzzle puzzle = new Puzzle(3);
        puzzle = PuzzleFactory.createRandom(3, 9);
        puzzle.print();
        System.out.println("--------Deordenado---------");
        puzzle = PuzzleFactory.desordenarPuzzle(puzzle);
        puzzle.print();
        System.out.println("----Ordenado por fuerza----");
        solverFuerza.resolver(puzzle);
        puzzle.print();
        
        System.out.println("---------Prueba 2--------");
        Puzzle puzzle2 = new Puzzle(5);
        puzzle2 = PuzzleFactory.createRandom(5, 9);
        puzzle2.print();
        System.out.println("--------Deordenado---------");
        puzzle2 = PuzzleFactory.desordenarPuzzle(puzzle2);
        puzzle2.print();
        System.out.println("----Ordenado por fuerza----");
        solverFuerza.resolver(puzzle2);
        puzzle2.print();
        
    }

}
