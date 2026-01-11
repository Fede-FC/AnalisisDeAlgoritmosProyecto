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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle( 5);
        System.out.println("Puzle 5x5 vacio");
        puzzle.print();
        System.out.println("Puzle 5x5 resuelto; orden: top, right, bottom, left");
        puzzle = PuzzleFactory.createRandom(5, 9);
        puzzle.print();
        System.out.println("Falta mezclar");
    }

}
