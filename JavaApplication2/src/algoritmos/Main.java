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
    static int supNumerico= 15;
   
    public static void imprimirPorFuerza(){
        porFuerza solverFuerza = new porFuerza();
        
        System.out.println("---------Prueba 1---------");
        Puzzle puzzle = new Puzzle(3);
        puzzle = PuzzleFactory.createFixed3x3();
        puzzle.print();
        System.out.println("--------Deordenado---------");
        puzzle = PuzzleFactory.desordenarPuzzle(puzzle);
        puzzle.print();
        System.out.println("----Ordenado por fuerza----");
        solverFuerza.resolver(puzzle);
        solverFuerza.printInfo();
        puzzle.print();
        System.out.println("");
        
        System.out.println("---------Prueba 2--------");
        Puzzle puzzle2 = new Puzzle(5);
        puzzle2 = PuzzleFactory.createRandom(5, 9);
        puzzle2.print();
        System.out.println("--------Deordenado---------");
        puzzle2 = PuzzleFactory.desordenarPuzzle(puzzle2);
        puzzle2.print();
        System.out.println("----Ordenado por fuerza----");
        solverFuerza.resolver(puzzle2);
        solverFuerza.printInfo();
        puzzle2.print();
        System.out.println("");
        
        System.out.println("---------Prueba 3---------");
        Puzzle puzzle3 = new Puzzle(10);
        puzzle3 = PuzzleFactory.createRandom(10, 9);
        puzzle3.print();
        System.out.println("--------Deordenado---------");
        puzzle3 = PuzzleFactory.desordenarPuzzle(puzzle3);
        puzzle3.print();
        System.out.println("----Ordenado por fuerza----");
        solverFuerza.resolver(puzzle3);
        solverFuerza.printInfo();
        puzzle3.print();
        System.out.println("");
        
        System.out.println("---------Prueba 4---------");
        Puzzle puzzle4 = new Puzzle(15);
        puzzle4 = PuzzleFactory.createRandom(15, 9);
        puzzle4.print();
        System.out.println("--------Deordenado---------");
        puzzle4 = PuzzleFactory.desordenarPuzzle(puzzle4);
        puzzle4.print();
        System.out.println("----Ordenado por fuerza----");
        solverFuerza.resolver(puzzle4);
        solverFuerza.printInfo();
        puzzle4.print();
        System.out.println("");
    }
    
    public static void main(String[] args) {
        imprimirPorFuerza();
        
    }
    
}
