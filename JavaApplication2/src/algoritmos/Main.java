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
    static int limNumerico= 9;
   
    
    public static void main(String[] args) {
        porFuerza solverFuerza = new porFuerza();
        Greedy greedySolve = new Greedy();
        
        System.out.println("---------Prueba 1---------");
        Puzzle puzzle = new Puzzle(3);
        puzzle = PuzzleFactory.createRandom(3, 9);
        puzzle.print();
        System.out.println("--------Deordenado 1---------");
        puzzle = PuzzleFactory.desordenarPuzzle(puzzle);
        puzzle.print();
        System.out.println("----Ordenado por fuerza----");
        solverFuerza.resolver(puzzle);
        puzzle.print();
        
        System.out.println("---------Prueba 2--------");
        Puzzle puzzle2 = new Puzzle(5);
        puzzle2 = PuzzleFactory.createRandom(5, 9);
        puzzle2.print();
        System.out.println("--------Deordenado 2---------");
        puzzle2 = PuzzleFactory.desordenarPuzzle(puzzle2);
        puzzle2.print();
        System.out.println("----Ordenado por fuerza----");
        solverFuerza.resolver(puzzle2);
        puzzle2.print();
        
        System.out.println("---------Prueba 3---------");
        Puzzle puzzle3aux = PuzzleFactory.createRandom(3, 9);
        puzzle3aux.print();
        Puzzle puzzle3 = new Puzzle(3);
        System.out.println("---------Desordenado 3---------");
        puzzle3aux = PuzzleFactory.desordenarPuzzle(puzzle3aux);
        puzzle3aux.print();
        System.out.println("----Ordenado de avance rapido----");
        puzzle3.setPiezas(puzzle3aux.getPiezas());
        greedySolve.solve(puzzle3);
        puzzle3.print();
        
        System.out.println("---------Prueba 4---------");
        Puzzle puzzle4aux = PuzzleFactory.createRandom(5, 9);
        puzzle4aux.print();
        Puzzle puzzle4 = new Puzzle(5);
        System.out.println("---------Desordenado 3---------");
        puzzle4aux = PuzzleFactory.desordenarPuzzle(puzzle4aux);
        puzzle4aux.print();
        System.out.println("----Ordenado de avance rapido----");
        puzzle4.setPiezas(puzzle4aux.getPiezas());
        greedySolve.solve(puzzle4);
        puzzle4.print();
    }
}