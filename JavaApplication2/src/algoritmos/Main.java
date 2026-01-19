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
    public static void imprimirPuzzles(){
        System.out.println("---------Prueba 1---------");
        Puzzle puzzle = new Puzzle(3);
        puzzle = PuzzleFactory.createFixed3x3();
        puzzle.print();
        
        System.out.println("---------Prueba 2---------");
        Puzzle puzzle2 = new Puzzle(5);
        puzzle2 = PuzzleFactory.createRandom(5, 9);
        puzzle2.print();
        
        System.out.println("---------Prueba 3---------");
        Puzzle puzzle3 = new Puzzle(10);
        puzzle3 = PuzzleFactory.createRandom(10, 9);
        puzzle3.print();
        
        System.out.println("---------Prueba 4---------");
        Puzzle puzzle4 = new Puzzle(15);
        puzzle4 = PuzzleFactory.createRandom(15, 9);
        puzzle4.print();
        
        System.out.println("---------Prueba 5---------");
        Puzzle puzzle5 = new Puzzle(20);
        puzzle5 = PuzzleFactory.createRandom(20, 9);
        puzzle5.print();
        
        System.out.println("---------Prueba 6---------");
        Puzzle puzzle6 = new Puzzle(30);
        puzzle6 = PuzzleFactory.createRandom(30, 9);
        puzzle6.print();
        
        System.out.println("---------Prueba 7---------");
        Puzzle puzzle7 = new Puzzle(40);
        puzzle7 = PuzzleFactory.createRandom(40, 9);
        puzzle7.print();
        
        System.out.println("---------Prueba 8---------");
        Puzzle puzzle8 = new Puzzle(50);
        puzzle8 = PuzzleFactory.createRandom(50, 9);
        puzzle8.print();
        
        System.out.println("---------Prueba 9---------");
        Puzzle puzzle9 = new Puzzle(60);
        puzzle9 = PuzzleFactory.createRandom(60, 9);
        puzzle9.print();
    }
    public static void imprimirPorFuerza(){
        porFuerza solverFuerza = new porFuerza();
        
        System.out.println("---------Prueba 1---------");
        Puzzle puzzle = new Puzzle(3);
        puzzle = PuzzleFactory.createFixed3x3();
        puzzle.print();
        System.out.println("--------Deordenado---------");
        PuzzleFactory.desordenarPuzzle(puzzle);
        puzzle.limpiarTablero();
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
        PuzzleFactory.desordenarPuzzle(puzzle2);
        puzzle2.limpiarTablero();
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
        PuzzleFactory.desordenarPuzzle(puzzle3);
        puzzle3.limpiarTablero();
        puzzle3.print();
        System.out.println("----Ordenado por fuerza----");
        solverFuerza.resolver(puzzle3);
        solverFuerza.printInfo();
        puzzle3.print();
        System.out.println("");
        
        
        
        
        System.out.println("---------Prueba 4---------");
        Puzzle puzzle4 = new Puzzle(15);
        puzzle4 = PuzzleFactory.createRandom(15, 9);
        System.out.println("Print del main");
        puzzle4.print();
        System.out.println("--------Deordenado---------");
        PuzzleFactory.desordenarPuzzle(puzzle4);
        puzzle4.limpiarTablero();
        puzzle4.print();
        System.out.println("----Ordenado por fuerza----");
        solverFuerza.resolver(puzzle4);
        solverFuerza.printInfo();
        puzzle4.print();
        System.out.println("");
        
    }
    
    public static void main(String[] args) {
        imprimirPuzzles();
        //imprimirPorFuerza();
        
    }
    
}
