/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos;

import conceptos.Puzzle;
import conceptos.PuzzleFactory;

/**
 *
 * @author araya
 */
public class AlgGenetico {
    
    public Puzzle AlgGenetico(){
        
        return null;
        
    }
    
    
    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle(3);
        puzzle.print();
        puzzle = PuzzleFactory.createRandom(3, 5);
        puzzle.print();
        
    }
}
