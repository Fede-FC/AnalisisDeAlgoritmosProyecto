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
        Long inicio, fin, tiempo, memoriaAntes, memoriaDespues, memoriaUsada;
        
        System.out.println("---------Prueba 1---------");
        Puzzle puzzle = new Puzzle(3);
        puzzle = PuzzleFactory.createFixed3x3();
        puzzle.print();
        System.out.println("--------Deordenado---------");
        PuzzleFactory.desordenarPuzzle(puzzle);
        puzzle.print();
        puzzle.limpiarTablero();
        System.out.println("----Ordenado por fuerza----");
        
        inicio = System.nanoTime();
        Runtime rt = Runtime.getRuntime();
        memoriaAntes = rt.totalMemory() - rt.freeMemory();
        solverFuerza.resolver(puzzle);
        memoriaDespues = rt.totalMemory() - rt.freeMemory();
        memoriaUsada = memoriaDespues- memoriaAntes;
        
        fin = System.nanoTime();
        solverFuerza.printInfo();
        tiempo = fin - inicio;
        
        System.out.println("Tiempo: " + tiempo + " nanosegundos");
        System.out.println("Memoria usada (bytes): " + memoriaUsada);
        System.out.println("Memoria usada (KB): " + (memoriaUsada / 1024.0));
        System.out.println("Memoria usada (MB): " + (memoriaUsada / 1024.0 / 1024.0));
        puzzle.print();
        System.out.println("");
        
        System.out.println("---------Prueba 2--------");
        Puzzle puzzle2 = new Puzzle(5);
        puzzle2 = PuzzleFactory.createRandom(5, limNumerico);
        puzzle2.print();
        System.out.println("--------Deordenado---------");
        PuzzleFactory.desordenarPuzzle(puzzle2);
        puzzle2.print();
        puzzle2.limpiarTablero();
        System.out.println("----Ordenado por fuerza----");
        inicio = System.nanoTime();

        memoriaAntes = rt.totalMemory() - rt.freeMemory();
        solverFuerza.resolver(puzzle2);
        memoriaDespues = rt.totalMemory() - rt.freeMemory();
        memoriaUsada = memoriaDespues- memoriaAntes;
        
        fin = System.nanoTime();
        solverFuerza.printInfo();
        tiempo = fin - inicio;
        
        System.out.println("Tiempo: " + tiempo + " nanosegundos");
        System.out.println("Memoria usada (bytes): " + memoriaUsada);
        System.out.println("Memoria usada (KB): " + (memoriaUsada / 1024.0));
        System.out.println("Memoria usada (MB): " + (memoriaUsada / 1024.0 / 1024.0));
        puzzle2.print();
        System.out.println("");
        
        System.out.println("---------Prueba 3---------");
        Puzzle puzzle3 = new Puzzle(10);
        puzzle3 = PuzzleFactory.createRandom(10, limNumerico);
        puzzle3.print();
        System.out.println("--------Deordenado---------");
        PuzzleFactory.desordenarPuzzle(puzzle3);        
        puzzle3.print();
        puzzle3.limpiarTablero();
        System.out.println("----Ordenado por fuerza----");
        inicio = System.nanoTime();
        memoriaAntes = rt.totalMemory() - rt.freeMemory();
        solverFuerza.resolver(puzzle3);
        memoriaDespues = rt.totalMemory() - rt.freeMemory();
        memoriaUsada = memoriaDespues- memoriaAntes;
        
        fin = System.nanoTime();
        solverFuerza.printInfo();
        tiempo = fin - inicio;
        
        System.out.println("Tiempo: " + tiempo + " nanosegundos ");
        System.out.println("Memoria usada (bytes): " + memoriaUsada);
        System.out.println("Memoria usada (KB): " + (memoriaUsada / 1024.0));
        System.out.println("Memoria usada (MB): " + (memoriaUsada / 1024.0 / 1024.0));
        puzzle3.print();
        System.out.println("");
        
        
        
        
        System.out.println("---------Prueba 4---------");
        Puzzle puzzle4 = new Puzzle(15);
        puzzle4 = PuzzleFactory.createRandom(15, limNumerico);
        System.out.println("Print del main");
        puzzle4.print();
        System.out.println("--------Deordenado---------");
        PuzzleFactory.desordenarPuzzle(puzzle4);
        puzzle4.print();
        puzzle4.limpiarTablero();
        System.out.println("----Ordenado por fuerza----");
        inicio = System.nanoTime();
        memoriaAntes = rt.totalMemory() - rt.freeMemory();
        solverFuerza.resolver(puzzle4);
        memoriaDespues = rt.totalMemory() - rt.freeMemory();
        memoriaUsada = memoriaDespues- memoriaAntes;
        
        fin = System.nanoTime();
        solverFuerza.printInfo();
        tiempo = fin - inicio;
        
        System.out.println("Tiempo: " + tiempo + " nanosegundos " + " Memoraia: " + memoriaUsada);
        puzzle4.print();
        System.out.println("");
        
    }
    
    public static void main(String[] args) {
        //imprimirPuzzles();
        imprimirPorFuerza();
        
    }
    
}
